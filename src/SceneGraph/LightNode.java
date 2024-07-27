package SceneGraph;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.math.Matrix4f;
import com.jogamp.opengl.math.Vec4f;

/**
 * represents a light node in the scene graph. Knows its light and the light's 
 * local transform
 * @author Michael A.
 */
public class LightNode implements TransformableNode {
	private Light light;
	private boolean isTransformed; // Flag to indicate whether a new transformation is applied
	private Transform transform_;

	/**
	 * create a new Light Node with a new transformation
	 * @param light
	 */
	public LightNode(Light light) {
		this.light = light;
		this.transform_ = new Transform();
		this.isTransformed = false;
		
	}

	@Override
	public void draw(GL2 gl) {
		if(isTransformed) {
			light.transform(transform_);
			light.configure(gl);
			isTransformed = false;//Reset the flag after applying the transformation
		}
		else {
			light.configure(gl);
		}
	}

	@Override
	public void init(GL2 gl) {
		// TODO Auto-generated method stub

	}

	@Override
	public Transform getTransform() {
		return this.transform_;
	}
	
	@Override
	public void setTransform(Transform tran) {
		this.transform_ = tran;	
		light.transform(this.transform_);
		this.isTransformed = true;//Indicate that a new transform has been set
	}


	
}

