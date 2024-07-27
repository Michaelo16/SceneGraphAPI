package SceneGraph;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.math.Matrix4f;
import com.jogamp.opengl.math.Vec3f;
import com.jogamp.opengl.math.Vec4f;

import Animation.AnimatedNode;

/**
 * Represents a node of type Camera in the scene graph
 * knows its camera's transformation and is responsible for setting up
 * the camera 
 * @author Michael Andrews
 */
public class CameraNode extends AnimatedNode implements TransformableNode {
	private Camera camera_;
	private Transform transform_;
	private boolean isTransformed_; // Flag to indicate whether a new transformation is applied
	
	/**
	 * creates a new camera node with a new default transformation
	 * @param camera
	 */
	public CameraNode(Camera camera) {
		this.camera_ = camera;
		this.transform_ = new Transform();
		this.isTransformed_ = false;

	}
		
	/**
	 * return the cameras transform
	 */
	public Transform getTransform() {
		return this.transform_;
	}

	/**
	 * set the transform of this camera
	 */
	public void setTransform(Transform tran) {
		this.transform_ = tran;	
		camera_.transform(this.transform_);
		this.isTransformed_ = true;//Indicate that a new transform has been set
	}


	/**
	 * Handles drawing the cameara and applying its transformations
	 */
	@Override
	public void draw(GL2 gl) {
		if(isTransformed_) {
			camera_.transform(transform_);
			camera_.apply(gl);
			isTransformed_ = false;//Reset the flag after applying the transformation
		}
		else {
			camera_.apply(gl);
		}
	}

	@Override
	public void init(GL2 gl) {

	}





}



