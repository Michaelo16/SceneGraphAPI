package SceneGraph;
import com.jogamp.opengl.GL2;
/**
 * represents a 3D scene. Stored as a grpah data structure (DAG)
 * Contains nodes that represent objects (Geometries, Cameras, Lights)
 * @author Michael Anndrews
 */
public class Scene {

    private GroupNode rootNode;

    public Scene() {
        this.rootNode = new GroupNode();
    }

    // Method to add any nodes: Geometry, GroupNode, LightNode, CameraNode
    /**
     * add a geometry Node
     * @param node
     */
    public void addGeometryNode(Node node) {
    	if(node instanceof Geometry)
        this.rootNode.addGeometryChild(node);
    }
    /**
     * add a group Node
     * @param node
     */
    public void addGroupNode(Node node) {
    	if(node instanceof GroupNode) {
    		this.rootNode.addGroupChild(node);
    	}
    }
    /**
     * add a camera Node
     * @param node
     */
    public void addCameraNode(TransformableNode node) {
    	if(node instanceof CameraNode) {
    		this.rootNode.addCameraChild(node);
    	}
    }
    /**
     * add a light node
     * @param node
     */
    public void addLightNode(TransformableNode node) {
    	if(node instanceof LightNode) {
    		this.rootNode.addLightChild(node);
    	}
    }

    // Method to initialize OpenGL settings, and the initial setup of nodes
   /**
    * initialize GL settings
    * @param gl
    */
    public void init(GL2 gl) {
        rootNode.init(gl);
    }

    // Method to render the entire scene
    /**
     * renders the scene
     * @param gl
     */
    public void render(GL2 gl) {
        rootNode.draw(gl);
    }
}

