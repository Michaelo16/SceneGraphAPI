package Libraries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import SceneGraph.MeshGeometry;
import Utility.Polyhedron;
import Utility.TexturedShapes;
/**
 * provides a lirbary for defined polygonal mesh objects
 * The user can either initialize the provided default library of 
 * pre defined mesh objects, or they can create their own. 
 * @author Michael Andrews
 */
public class MeshObjectLibrary {

	private static final Map<String, MeshGeometry> meshObjects_ = new HashMap<>();

	/**
	 * Add a mesh to the library
	 * 
	 * @param id
	 * @param meshObject
	 */
	public static void addMeshObject(String id, MeshGeometry meshObject) {
		meshObjects_.put(id, meshObject);
	}

	/**
	 * get a mesh from the library
	 * @param id
	 * @return
	 */
	public static MeshGeometry getMeshObject(String id) {
		MeshGeometry temp = meshObjects_.get(id);
		return new MeshGeometry(temp.getVertices(), temp.getFaces(),
				null,null,false,false,false);
	}

	/**
	 * remove a mesh from the lirbary
	 * @param id
	 */
	public static void removeMeshObject(String id) {
		meshObjects_.remove(id);
	}

	/**
	 * init the default mesh objects
	 */
	public static void initDefualt() {

		//House
		Polyhedron House = Polyhedron.HOUSE;
		MeshGeometry house = new MeshGeometry(House.vertices,House.faces,
				null, null, false, false, false);
		addMeshObject("House", house);

		//Sphere - I made this more complicated than it needed to be
		@SuppressWarnings("unchecked")
		ArrayList<double[]> vertices1 = (ArrayList<double[]>) TexturedShapes.uvSphere(1, 32,32, true);
		@SuppressWarnings("unchecked")
		ArrayList<int[]> faces1 =  (ArrayList<int[]>) TexturedShapes.uvSphere(1, 32,32, false);
		
		//Yep totally unecessary. Shouldve just changed the method to return arrays instead
		double[][] vertices = new double[vertices1.size()][3];
		for(int i = 0; i < vertices1.size(); i++) {
			vertices[i] = vertices1.get(i);
		}
		int[][] faces = new int[faces1.size()][];
		for(int i = 0; i < faces1.size(); i++) {
			faces[i] = faces1.get(i);
		}
		
		MeshGeometry sphere = new MeshGeometry(vertices,faces,
				null, null, false, false, false);
		addMeshObject("Sphere", sphere);
		
		//Prism
		Polyhedron Prism = Polyhedron.PRISM;
		MeshGeometry prism = new MeshGeometry(Prism.vertices,Prism.faces,
				null, null, false, false, false);
		addMeshObject("Prism", prism);

		Polyhedron Cube = Polyhedron.CUBE;
		MeshGeometry cube = new MeshGeometry(Cube.vertices,Cube.faces,
				null, null, false, false, false);
		addMeshObject("Cube", cube);

		Polyhedron Dodecahedron = Polyhedron.DODECAHEDRON;
		MeshGeometry dodec = new MeshGeometry(Dodecahedron.vertices,Dodecahedron.faces,
				null, null, false, false, false);
		addMeshObject("Dodecahedron", dodec);

		Polyhedron Icosahedron = Polyhedron.ICOSAHEDRON;
		MeshGeometry ico = new MeshGeometry(Icosahedron.vertices,Icosahedron.faces,
				null, null, false, false, false);
		addMeshObject("Icosahedron", ico);

		Polyhedron Octahedron = Polyhedron.OCTAHEDRON;
		MeshGeometry octa = new MeshGeometry(Octahedron.vertices,Octahedron.faces,
				null, null, false, false, false);
		addMeshObject("Octahedron", octa);

		Polyhedron RhombicDodec = Polyhedron.RHOMBIC_DODECAHEDRON;
		MeshGeometry rombicd = new MeshGeometry(RhombicDodec.vertices,RhombicDodec.faces,
				null, null, false, false, false);
		addMeshObject("Rhombic Dodecahedron", rombicd);

		Polyhedron SoccerBall = Polyhedron.SOCCER_BALL;
		MeshGeometry soccerB = new MeshGeometry(SoccerBall.vertices,SoccerBall.faces,
				null, null, false, false, false);
		addMeshObject("Soccer Ball", soccerB);

		Polyhedron StellatedDodec = Polyhedron.STELLATED_DODECAHEDRON;
		MeshGeometry StellDo = new MeshGeometry(StellatedDodec.vertices,StellatedDodec.faces,
				null, null, false, false, false);
		addMeshObject("Stellated Dodecahedron", StellDo);

		Polyhedron StellatedIco = Polyhedron.STELLATED_ICOSAHEDRON;
		MeshGeometry StellIco = new MeshGeometry(StellatedIco.vertices,StellatedIco.faces,
				null, null, false, false, false);
		addMeshObject("Stellated Icosahedron", StellIco);

		Polyhedron StellatedOcta = Polyhedron.STELLATED_OCTAHEDRON;
		MeshGeometry StellOcta = new MeshGeometry(StellatedOcta.vertices,StellatedOcta.faces,
				null, null, false, false, false);
		addMeshObject("Stellated Octahedron", StellOcta);

		Polyhedron Tetrahedron = Polyhedron.TETRAHEDRON;
		MeshGeometry tetra = new MeshGeometry(Tetrahedron.vertices,Tetrahedron.faces,
				null, null, false, false, false);
		addMeshObject("Tetrahedron", tetra);

		Polyhedron TruncatedIco = Polyhedron.TRUNCATED_ICOSAHEDRON;
		MeshGeometry TruncIco = new MeshGeometry(TruncatedIco.vertices,TruncatedIco.faces,
				null, null, false, false, false);
		addMeshObject("Truncated Icosahedron", TruncIco);

		Polyhedron TruncRhomDodec = Polyhedron.TRUNCATED_RHOMBIC_DODECAHEDRON;
		MeshGeometry TRC = new MeshGeometry(TruncRhomDodec.vertices,TruncRhomDodec.faces,
				null, null, false, false, false);
		addMeshObject("Truncated Rhombic Dodecahedron", TRC);
	}

}