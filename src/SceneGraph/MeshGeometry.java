package SceneGraph;
import java.awt.Color;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.math.Matrix4f;

import Utility.LinAlg;
import Utility.Polyhedron;
	
/**
 * represents a geometry object represented by a polygonal mesh
 * uses an indexed face set representaion. 
 * can also draw colored edges and generate automatic texture coordinates
 * for a given polyhedron
 * @author Michael A.
 */
public class MeshGeometry extends Geometry {

	private double[][] vertices;
	private int[][] faces;
	private double[][] normals;
	private boolean smoothShading;
	private boolean drawEdges;
	private boolean generateTexture;
	private double[][] textureCoords;
	private boolean textured_;

	private int genMode_;
	private int texWrap_;

	private Color edgeColor_;



	/**
	 * create a new Mesh Geometry with vertices and faces
	 * normals will be generated if left null
	 * tex coords not required unless the object has a texture
	 * specifiy if texture coordinates are to generated, if edges are to be drawn
	 * and if flat or smooth shading is to be used
	 * @param vertices
	 * @param faces
	 * @param normals
	 * @param texCoords
	 * @param genTex
	 * @param edges
	 * @param smoothShading
	 */
	public MeshGeometry(double[][] vertices, int[][] faces, double[][] normals,
			double[][] texCoords, boolean genTex, boolean edges, boolean smoothShading) {
		this.vertices = vertices;
		this.faces = faces;
		//If the normals are provided use them, else calculate them
		if(normals != null)
			this.normals = normals;
		else
			this.normals = computeNormals(smoothShading);

		this.smoothShading = smoothShading;
		this.drawEdges = edges;
		this.textureCoords = texCoords;
		this.generateTexture = genTex;
		this.textured_ = false;
		this.edgeColor_ = Color.BLACK;
		this.genMode_ = GL2.GL_OBJECT_LINEAR;
		this.texWrap_ = GL2.GL_REPEAT;

	}
	/**
	 * set the texture coord generation flag
	 * @param texGen
	 */
	public void setTexGen(boolean texGen) {
		this.generateTexture = texGen;
	}
	/**
	 * set the texture parameter for texture wrapping
	 * @param wrap
	 */
	public void setTexWrap(int wrap) {
		this.texWrap_ = wrap;
	}
	/**
	 * set the function for generating tex coords
	 * @param mode
	 */
	public void setGenerationMode(int mode) {
		this.genMode_ = mode;
	}
	/**
	 * set the Texture coords manually
	 * @param coords
	 */
	public void setTextureCoords(double[][] coords) {
		this.textureCoords = coords;
	}
	/**
	 * specify color for drawn edges. Black by default
	 * @param color
	 */
	public void setEdgeColor(Color color) {
		edgeColor_ = color;
	}
	/**
	 * set the drawEdges flag
	 * @param edge
	 */
	public void setEdges(boolean edge) {
		this.drawEdges =  edge;
	}
	/**
	 * manually specify normals
	 * @param normals
	 */
	public void setNormals(double[][] normals) {
		this.normals = normals;
	}
	/**
	 * set the shading setting
	 * @param shade
	 */
	public void setShading(boolean shade) {
		this.smoothShading = shade;
	}
	/**
	 * get the vertices set
	 * @return
	 */
	public double[][] getVertices() {
		return vertices;
	}
	/**
	 * get the faces set
	 * @return
	 */
	public int[][] getFaces(){
		return faces;
	}

	@Override
	public void init(GL2 gl) {
		// TODO Auto-generated method stub

	}




	@Override
	protected void drawGeometry(GL2 gl) {

		SceneTexture tex = getMaterial().getTexture();
		if(tex != null) {//Check if the mesh has a texture
			gl.glMatrixMode(GL2.GL_TEXTURE);
			textured_ = true;
			tex.enable(gl);
			tex.bind(gl);
		}
		//Check if texture coordinates are to be generated
		if (generateTexture && textured_) {//Enable texture generation
			gl.glTexGeni(GL2.GL_S, GL2.GL_TEXTURE_GEN_MODE, genMode_);
			gl.glTexGeni(GL2.GL_T, GL2.GL_TEXTURE_GEN_MODE, genMode_);
			gl.glEnable(GL2.GL_TEXTURE_GEN_S);
			gl.glEnable(GL2.GL_TEXTURE_GEN_T);
			tex.getTexture().setTexParameteri(gl, GL2.GL_TEXTURE_WRAP_S, texWrap_);
			tex.getTexture().setTexParameteri(gl, GL2.GL_TEXTURE_WRAP_T, texWrap_);
		}
		gl.glMatrixMode(GL2.GL_MODELVIEW);

		// draw faces
		gl.glPolygonOffset(1,1);
		gl.glLineWidth(1);
		gl.glEnable(GL2.GL_POLYGON_OFFSET_FILL);
		for ( int i = 0 ; i < faces.length ; i++ ) {
			gl.glBegin(GL2.GL_POLYGON);
			for ( int j = 0 ; j < faces[i].length ; j++ ) {
				if (!generateTexture && textureCoords != null) {
					//If coords arent being generated and coords are supplied 
					gl.glTexCoord2d(textureCoords[i][j * 2], textureCoords[i][j * 2 + 1]);
				}		
				int vertexNum = faces[i][j];
				if (smoothShading) {
					
					gl.glNormal3dv(normals[vertexNum], 0); // Apply the normal to the vertex
				} else {
					double[] faceNormal = Polyhedron.computeFaceNormal(faces[i], vertices);
					gl.glNormal3dv(faceNormal, 0); // Apply the face normal
				}
				double[] vertexCoords = vertices[vertexNum];


				gl.glVertex3dv(vertexCoords,0);
			}
			gl.glEnd();
		}

		if (generateTexture) {//Disable
			gl.glDisable(GL2.GL_TEXTURE_GEN_S);
			gl.glDisable(GL2.GL_TEXTURE_GEN_T);
		}

		gl.glDisable(GL2.GL_POLYGON_OFFSET_FILL);

		// draw edges
		if(drawEdges) { // Checking whether edges should be drawn
			gl.glDisable(GL2.GL_LIGHTING); // Disable lighting to set a specific edge color
			gl.glLineWidth(3); // Setting the width of the lines
			gl.glColor3f((float)edgeColor_.getRed()/255,(float)edgeColor_.getGreen()/255,(float)edgeColor_.getBlue()/255); // Color of the edges
			for (int i = 0; i < faces.length; i++) {
				gl.glBegin(GL2.GL_LINE_LOOP);
				for (int j = 0; j < faces[i].length; j++) {

					int vertexNum = faces[i][j];
					double[] vertexCoords = vertices[vertexNum];
					gl.glVertex3dv(vertexCoords, 0);
				}
				gl.glEnd();
			}
			gl.glEnable(GL2.GL_LIGHTING); // Re-enable lighting for drawing
		}


	}

	private double[][] computeNormals(boolean smoothShading) {
		double[][] normals = new double[vertices.length][3];
		
		if (smoothShading) {//Smooth shading
			// Initialize normals with zeros
			for (int i = 0; i < normals.length; i++) {
				normals[i] = new double[] {0, 0, 0};
			}

			// Calculate face normals at vertices
			for (int[] face : faces) {
				double[] faceNormal = Polyhedron.computeFaceNormal(face,vertices);
				for (int vertexIndex : face) {//For each vertex in the face
					normals[vertexIndex][0] += faceNormal[0];
					normals[vertexIndex][1] += faceNormal[1];
					normals[vertexIndex][2] += faceNormal[2];
				}
			}

			// Normalize the normals
			for (int i = 0; i < normals.length; i++) {
				normals[i] = LinAlg.normalize(normals[i]);
			}
		} else {
			// Flat shading
			for (int[] face : faces) {
				double[] faceNormal = Polyhedron.computeFaceNormal(face,vertices);
				for (int vertexIndex : face) {
					normals[vertexIndex] = faceNormal;
				}
			}
		}

		return normals;
	}

	

	//Helper methods for shading


	//	/**
	//	 * Compute the outward pointing surface normal for a face, using an indexed
	//	 * face set representation. Requires face.length >=3
	//	 * 
	//	 * @param face
	//	 *          face vertices, as an array of indices into _vertices_ (in CCW
	//	 *          order when viewed from the front)
	//	 * @param vertices
	//	 *          vertex coordinates, as an array of 3-element arrays
	//	 * @return outward pointing surface normal (unit vector)
	//	 */
	//	private static float[] computeFaceNormal ( int[] face, float[][] vertices ) {
	//		float[][] poly = new float[face.length][];
	//		for ( int i = 0 ; i < face.length ; i++ ) {
	//			poly[i] = vertices[face[i]];
	//		}
	//		return computePolygonNormal(poly);
	//	}
	//
	//	/**
	//	 * Compute the outward pointing surface normal for a polygon. Requires
	//	 * poly.length >= 3
	//	 * 
	//	 * @param poly
	//	 *          vertex coordinates, as an array of 3-element arrays (in CCW order
	//	 *          when viewed from the front)
	//	 * @return outward pointing surface normal (unit vector)
	//	 */
	//	public static float[] computePolygonNormal ( float[][] poly ) {
	//		float[] p =
	//			{ poly[1][0] - poly[0][0], poly[1][1] - poly[0][1],
	//					poly[1][2] - poly[0][2] },
	//			q = { poly[poly.length - 1][0] - poly[0][0],
	//					poly[poly.length - 1][1] - poly[0][1],
	//					poly[poly.length - 1][2] - poly[0][2] };
	//		float[] n = { p[1] * q[2] - p[2] * q[1], p[2] * q[0] - p[0] * q[2],
	//				p[0] * q[1] - p[1] * q[0] };
	//		return LinAlg.normalize(n);
	//	}


}



