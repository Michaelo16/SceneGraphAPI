package SceneGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.math.Matrix4f;

/**
 * represents any hierarchical collection of nodes
 * supports children of type Geometry, Camera Node, and Light Node
 */
public class GroupNode implements Node, TransformableNode {

	private Stack<Transform> transformHistory_;
    private List<Node> children_;
    private Transform transform_;
 

    public GroupNode() {
        this.children_ = new ArrayList<>();
        this.transform_ = new Transform();
        this.transformHistory_ = new Stack<Transform>();
    }
    
    /**
     * add a geometry child node
     * @param child
     */
    public void addGeometryChild(Node child) {
    	if(child instanceof Geometry)
        this.children_.add(child);
    }
    /**
     * add A mesh geometry node to the groupNode
     * @param child
     */
    public void addMeshGeometryChild(Node child) {
    	if(child instanceof MeshGeometry) {
    		this.children_.add(child);
    	}
    }
    
    //Whenever a groupNode is added to a groupNode
    //The child gets the parent GroupNode's transform added
    //as 'History' so it can remember the string of transforms
    //for camera and Light nodes
    /**
     * add a groupNode to this groupNode
     * @param child
     */
    public void addGroupChild(Node child) {
    	if(child instanceof GroupNode) {
    		((GroupNode) child).addHistory(transform_);
    		this.children_.add(child);
    	}
    }
    
    //When a camera or Light node is added to a group node, because
    //Accumulative OpenGL transforms don't apply, we go through each 
    //cumulative transform from previous GroupNodes and apply them to 
    //The node, updating their positions and directions once, in order, to 
    //avoid tearing
    /**
     * add a camera to this groupNode
     * @param child
     */
    public void addCameraChild(TransformableNode child) {
    	if(child instanceof CameraNode) {
    		Stack<Transform> history = transformHistory_;
    		Stack<Transform> reverse = new Stack<Transform>();
    		//Going in order of the first groupNode to the most recent
    		for(int i = 0; i < transformHistory_.size(); i++) {
    			reverse.push(history.pop());
    		}
    		//For every parent groupNode, starting from the top,
    		//Apply each transform to the node
    		for(int i = 0; i < reverse.size(); i++) {
    			
    			Transform temp = reverse.pop();
    			child.setTransform(temp);
    			
    		}
    		//Finally add the child node, with its position/direction aready updated to 
    		//The current groupNode transformation
    		this.children_.add(child);
    	}
    	
    	child.setTransform(transform_);//Setting the group Transform
    	
    }
    /**
     * add a Light node to this groupNode
     * @param child
     */
    public void addLightChild(TransformableNode child) {
    	if(child instanceof LightNode) {
    		Stack<Transform> history = transformHistory_;
    		Stack<Transform> reverse = new Stack<Transform>();
    		//Going in order of the first groupNode to the most recent
    		for(int i = 0; i < transformHistory_.size(); i++) {
    			reverse.push(history.pop());
    		}
    		//For every parent groupNode, starting from the top,
    		//Apply each transform to the node
    		for(int i = 0; i < reverse.size(); i++) {
    			
    			Transform temp = reverse.pop();
    			child.setTransform(temp);
    			
    		}
    		//Finally add the child node, with its position/direction aready updated to 
    		//The current groupNode transformation
    		this.children_.add(child);
    	}
    	
    	child.setTransform(transform_);//Setting the group Transform
    	
    }

    /**
     * set the trasform
     */
    public void setTransform(Transform tran) {
        this.transform_ = tran;
        
    }
    
    
    private void addHistory(Transform tran) {
    	this.transformHistory_.push(tran);
    }

    @Override
    public void draw(GL2 gl) {
        gl.glPushMatrix();
        //Applying group transform and updating GL transform state
        transform_.applyTransformations(gl); 
 
        for (Node child : children_) {
        System.out.println("drawing Child: " + child.toString());
        child.draw(gl);  // Draw the child node which might have its own local transformation
        
        }
        gl.glPopMatrix();
    }

	@Override
	public void init(GL2 gl) {
		for (Node child : children_) {
            child.init(gl);
        }
		
	}

	@Override
	public Transform getTransform() {

		return transform_;
	}

	
	
	
	
}

