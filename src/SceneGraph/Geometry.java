package SceneGraph;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.math.Matrix4f;
import com.jogamp.opengl.util.gl2.GLUT;

import Animation.AnimatedNode;
import Utility.TexturedShapes;
/**
 * represents a simple geometrial shape. Sub-classes implement specific
 * shapes such as cube, sphere, etc,. Knows its local transform and material
 * 
 */
public abstract class Geometry extends AnimatedNode implements Node,  TransformableNode {
	private Transform transform_;
	private Material material_;
	float[] defaultAmbient_ = {0.2f, 0.2f, 0.2f, 1.0f};
    float[] defaultDiffuse_ = {0.8f, 0.8f, 0.8f, 1.0f};
    float[] defaultSpecular_ = {0.0f, 0.0f, 0.0f, 1.0f};
    float defaultShininess_ = 0.0f;
    
    /**
     * creates a new Geometry with a default material and sets
     * transform to null
     */
	public Geometry() {
		this.material_ = new Material();//Default material
		this.transform_ = null;
	}

	//Getters and setters
	/**
	 * get the current transform for this geometry
	 */
	public Transform getTransform() {
		return transform_;
	}

	/**
	 * set this geometries transform 
	 */
	public void setTransform(Transform transform) {
		this.transform_ = transform;
	}
	
	/**
	 * get the material of this geometry
	 * @return
	 */
	public Material getMaterial() {
		return material_;
	}
	/**
	 * set the Material of this Geometry
	 * @param material
	 */
	public void setMaterial(Material material) {
		this.material_ = material;
	}
	
	/**
	 * handles drawing a given geometry
	 */
	@Override 
	public void draw(GL2 gl) {
		// Apply Transformations
		gl.glPushMatrix();
		if(transform_ != null) {// Check if a transformation is assigned
			transform_.applyTransformations(gl);// Apply the transformation(s)
		}

		resetMaterial(gl);
		// Set Material Properties
		if (material_ != null) { // Check if a material is assigned
			setMaterial(gl, material_); // Apply the material properties
		}
		if(material_.getTexture() != null) {//check if a texture has been applied
			gl.glEnable(GL2.GL_TEXTURE);
			material_.getTexture().init(gl);
		}
		
		// Draw the actual geometry
		drawGeometry(gl);
		gl.glDisable(GL2.GL_TEXTURE);//Textures are only enabled when they are being drawn

		gl.glPopMatrix();
	}

	/**
	 * set the material of this geometry
	 */
	private void setMaterial(GL2 gl2,Material mat) {
		gl2.glMaterialfv(GL2.GL_FRONT,GL2.GL_AMBIENT,mat.getAmbientColorArray() ,0);
		gl2.glMaterialfv(GL2.GL_FRONT,GL2.GL_SPECULAR,mat.getSpecularColorArray(),0);
		gl2.glMaterialfv(GL2.GL_FRONT,GL2.GL_DIFFUSE,mat.getDiffuseColorArray(),0);
		gl2.glMaterialf(GL2.GL_FRONT, GL2.GL_SHININESS,mat.getShininess());
	}
	private void resetMaterial(GL2 gl) {
	    gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT, defaultAmbient_, 0);
	    gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_DIFFUSE, defaultDiffuse_, 0);
	    gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, defaultSpecular_, 0);
	    gl.glMaterialf(GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, defaultShininess_);
	}
	
	
	protected abstract void drawGeometry(GL2 gl); // Implemented in subclasses like Cube, Sphere etc.

	//Individual sub classes for each geometric shape
	
	/**
	 *represents a cube
	 *@author Michael Andrews
	 */
	public static class Cube extends Geometry {
        private float size;
        private boolean textured_;
        /**
         * creates a new cube with specified size
         * @param size
         */
        public Cube(float size) {
            this.size = size;
            this.textured_ = false;
        }
        
        @Override
		protected void drawGeometry(GL2 gl) {
        	
            SceneTexture tex = getMaterial().getTexture();
            if(tex != null) {//Bind and apply Texture
            	gl.glMatrixMode(GL2.GL_TEXTURE);
            	textured_ = true;
            	tex.enable(gl);
            	tex.bind(gl);
            }
            gl.glMatrixMode(GL2.GL_MODELVIEW);
            TexturedShapes.cube(gl,size, textured_);
        }

		@Override
		public void init(GL2 gl) {
			// TODO Auto-generated method stub
			
		}

		
    }

	/**
	 * represents a sphere
	 */
	public static class Sphere extends Geometry {

	    private float radius;
	    private int slices;
	    private int stacks;
	    private boolean textured_;
	    
	    /**
	     * create a new sphere with specified radius,
	     * slices, and stacks
	     * @param radius
	     * @param slices
	     * @param stacks
	     */
	    public Sphere(float radius, int slices, int stacks) {
	        this.radius = radius;
	        this.slices = slices;
	        this.stacks = stacks;
	        this.textured_ = false;
	    }

	    @Override
	    public void drawGeometry(GL2 gl) {
	        
	        SceneTexture tex = getMaterial().getTexture();
            if(tex != null) {//Bind and apply Texture
            	gl.glMatrixMode(GL2.GL_TEXTURE);
            	textured_ = true;
            	tex.enable(gl);
            	tex.bind(gl);
            }
            gl.glMatrixMode(GL2.GL_MODELVIEW);
	        TexturedShapes.uvSphere(gl,radius, slices, stacks, textured_);
	    }

		@Override
		public void init(GL2 gl) {
			// TODO Auto-generated method stub
			
		}

		
	}
	/**
	 * represetns a Cylinder
	 */
	public static class Cylinder extends Geometry {

	    private float baseRadius;
	    private float height;
	    private int slices;
	    private int stacks;
	    private int rings;
	    private boolean textured_;

	    /**
	     * create a new Cylinder with a base radius, height,
	     * number of slices, stacks, and rings
	     * @param baseRadius
	     * @param height
	     * @param slices
	     * @param stacks
	     * @param rings
	     */
	    public Cylinder(float baseRadius, float height, int slices, int stacks,int rings) {
	        this.baseRadius = baseRadius;
	        this.height = height;
	        this.slices = slices;
	        this.stacks = stacks;
	        this.rings = rings;
	        this.textured_ = false;
	    }

	    @Override
	    public void drawGeometry(GL2 gl) {
	        SceneTexture tex = getMaterial().getTexture();
            if(tex != null) {//Bind and apply Texture
            	gl.glMatrixMode(GL2.GL_TEXTURE);
            	textured_ =true;
            	tex.enable(gl);
            	tex.bind(gl);
            }
            gl.glMatrixMode(GL2.GL_MODELVIEW);
	        TexturedShapes.uvCylinder(gl,baseRadius, height, slices, stacks,rings,textured_);
	    }

		@Override
		public void init(GL2 gl) {
			// TODO Auto-generated method stub
			
		}

		
	}
	/**
	 * represents a cone
	 */
	public static class Cone extends Geometry {

	    private float baseRadius;
	    private float height;
	    private int slices;
	    private int stacks;
	    private int rings;
	    private boolean textured_;

	    /**
	     * create a cone with base radius, height, number of slices,stakcs, and rings
	     * @param baseRadius
	     * @param height
	     * @param slices
	     * @param stacks
	     * @param rings
	     */
	    public Cone(float baseRadius, float height, int slices, int stacks,int rings) {
	        this.baseRadius = baseRadius;
	        this.height = height;
	        this.slices = slices;
	        this.stacks = stacks;
	        this.rings = rings;
	        this.textured_ =false;
	    }

	    @Override
	    public void drawGeometry(GL2 gl) {
	        SceneTexture tex = getMaterial().getTexture();
            if(tex != null) {//Bind and apply Texture
            	gl.glMatrixMode(GL2.GL_TEXTURE);
            	textured_ = true;
            	tex.enable(gl);
            	tex.bind(gl);
            }
            gl.glMatrixMode(GL2.GL_MODELVIEW);
	        TexturedShapes.uvCone(gl,baseRadius, height, slices, stacks,rings, textured_);
	    }

		@Override
		public void init(GL2 gl) {
			// TODO Auto-generated method stub
			
		}

	}
	
	public static class Torus extends Geometry{

		private boolean textured_;
		private double outerRad_;
		private double innerRad_;
		private int rings_;
		private int slices_;
		
		/**
		 * create a new torus, with outter raidus, inner radius, number of slices
		 * rings, and boolean texture flag
		 * @param gl
		 * @param out
		 * @param inner
		 * @param slices
		 * @param rings
		 * @param tex
		 */
		public Torus(GL2 gl, double out, double inner, int slices, int rings, boolean tex) {
			this.outerRad_ = out;
			this.innerRad_ = inner;
			this.slices_ = slices;
			this.rings_ = rings;
			this.textured_ = tex;
		}
		
		@Override
		protected void drawGeometry(GL2 gl) {
			 SceneTexture tex = getMaterial().getTexture();
	            if(tex != null) {//Bind and apply Texture
	            	gl.glMatrixMode(GL2.GL_TEXTURE);
	            	textured_ = true;
	            	tex.enable(gl);
	            	tex.bind(gl);
	            }
	            gl.glMatrixMode(GL2.GL_MODELVIEW);
	            TexturedShapes.uvTorus(gl, outerRad_, innerRad_, slices_, rings_, textured_);
			
		}

		@Override
		public void init(GL2 gl) {
			// TODO Auto-generated method stub
			
		}
		
		
		
		
	}


}

