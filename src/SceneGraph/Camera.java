package SceneGraph;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.math.Vec3f;

import Utility.LinAlg;

/**
 * represents a camera object. Has methods for getting and setting
 * important cameara parameters such as position and target
 * Also has methods to handle local transformations. 
 * Used to create camera Nodes
 * @author Michael Andrews
 */
public class Camera {
	private float[] position_;//Camera position
	private float[] target_;//Camera target
	private float[] upVector_;//Up
	private float fovY_;//Field of view
	private float aspect_;//aspect ratio
	private float near_;
	private float far_;




	public Camera() {
		// Default camera configuration
		this.position_ = new float[]{0.0f, 0.0f, 10.0f};
		this.target_ = new float[]{0.0f, 0.0f, 0.0f};
		this.upVector_ = new float[]{0.0f, 1.0f, 0.0f};
		this.fovY_ = 45.0f;
		this.aspect_ = 1.0f;
		this.near_ = 0.1f;
		this.far_ = 1000.0f;


	}


	//Getters and setters for camera variables
	/**
	 * set the cameras position
	 * @param position
	 */
	public void setPosition(float[] position) {
		this.position_ = position;
	}

	/**
	 * get the cameras position as an array of float
	 * @return
	 */
	public float[] getPosition() {
		return this.position_;
	}
	/**
	 * set the cameras target
	 * @param target
	 */
	public void setTarget(float[] target) {
		this.target_ = target;
	}
	/**
	 * get the camera target as an array of float
	 * @return
	 */
	public float[] getTarget() {
		return this.target_;
	}

	/**
	 * set the up Vector for the camera
	 * @param upVector
	 */
	public void setUpVector(float[] upVector) {
		this.upVector_ = upVector;
	}
	/**
	 * return the upVector for the camera
	 * @return
	 */
	public float[] getUpVector() {
		return this.upVector_;
	}
	
	/**
	 * set the fov of the camera
	 * @param fovY
	 */
	public void setFovY(float fovY) {
		this.fovY_ = fovY;
	}

	/**
	 * get the fov of the camera
	 * @return
	 */
	public float getFovY() {
		return this.fovY_;
	}

	/**
	 * set the aspect ratio of the camera
	 * @param aspect
	 */
	public void setAspect(float aspect) {
		this.aspect_ = aspect;
	}

	/**
	 * get the aspect ratiof of the camera
	 * @return
	 */
	public float getAspect() {
		return this.aspect_;
	}
	
	/**
	 * set near value
	 * @param near
	 */
	public void setNear(float near) {
		this.near_ = near;
	}
	/**
	 * get near value
	 * @return near
	 */
	public float getNear() {
		return this.near_;
	}

	/**
	 * set far value
	 * @param far
	 */
	public void setFar(float far) {
		this.far_ = far;
	}

	/**
	 * return far value
	 * @return
	 */
	public float getFar() {
		return this.far_;
	}

	/**
	 * Handles transforming the camera
	 * requires a transform
	 * @param tran
	 */
	public void transform(Transform tran) {

		//Rotation
		float[] axis = new float[] {tran.getRotate()[1],
				tran.getRotate()[2], tran.getRotate()[3]};
		float angle = tran.getRotate()[0];
		System.out.println("angle: " + angle);
		System.out.println("axis: " + axis[0] + " " + axis[1] + 
				" " + axis[2]);


		//Rotating the target point
		if (angle != 0 && (axis[0] != 0 || axis[1] != 0 || axis[2] != 0)) { // Checking if rotation is specified
			float[] direction = new float[] {target_[0] - position_[0], target_[1] - position_[1], target_[2] - position_[2]};
			direction = rotateDirectionVector(angle, axis, direction);

			// Updating target based on new direction
			target_[0] = position_[0] + direction[0];
			target_[1] = position_[1] + direction[1];
			target_[2] = position_[2] + direction[2];
		}

		//Translation
		Translate(tran);

		//Scaling - not really important. What would even happen if
		//you scaled a camera bigger or smaller? Not worth implementing
	}

	//Translation Stuff

	/**
	 * updates the cameras position and target relative to a given
	 * translation
	 * @param tran
	 */
	public void Translate(Transform tran) {
		// Obtain the translation values from the Transform object
		float[] translate = tran.getTranslate();
		for(int i = 0; i < 3; i++) {
			System.out.println(tran.getTranslate()[i]);
		}

		position_ = new float[] {
				position_[0] + translate[0],
				position_[1] + translate[1],
				position_[2] + translate[2]
		};

		// Also update the target (or direction) by applying the same translation
		target_ = new float[] {
				target_[0] + translate[0],
				target_[1] + translate[1],
				target_[2] + translate[2]
		};
	}


	//Rotation stuff

	/**
	 * rotates the cameras target around the position
	 * @param angle
	 * @param axis
	 * @param direction
	 * @return
	 */
	public float[] rotateDirectionVector(float angle, float[] axis, float[] direction) {
		float[] normalizedAxis = LinAlg.normalize(axis);//Normalize axis
		float cosTheta = (float) Math.cos(Math.toRadians(angle));//Calculating cos and sin of rotation angle
		float sinTheta = (float) Math.sin(Math.toRadians(angle));//Then convert to radians

		//we need to find the cross product and dot product of the normalized axis and the direction vector
		float[] crossProduct = crossProduct(normalizedAxis, direction);
		float dotProduct = dotProduct(normalizedAxis, direction);

		//Simply apply the formula to each component of the direction vector
		for (int i = 0; i < 3; i++) {
			System.out.println(normalizedAxis[i]+ "test");
			direction[i] = 
					direction[i] * cosTheta 
					+ crossProduct[i] * sinTheta 
					+ normalizedAxis[i] * dotProduct * (1 - cosTheta);
		}
		float[] result = direction;
		return result;
	}

	//Helper methods for rotation
	/**
	 * computes the cross product between two vectors
	 * @param a
	 * @param b
	 * @return
	 */
	private float[] crossProduct(float[] a, float[] b) {
		return new float[]{
				a[1] * b[2] - a[2] * b[1],
				a[2] * b[0] - a[0] * b[2],
				a[0] * b[1] - a[1] * b[0],
		};
	}
	/**
	 * computes the dot product of two vectors
	 * @param a
	 * @param b
	 * @return
	 */
	private float dotProduct(float[] a, float[] b) {
		return a[0] * b[0] + a[1] * b[1] + a[2] * b[2];
	}






	public void apply(GL2 gl) {
		GLU glu = new GLU();
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluPerspective(fovY_, aspect_, near_, far_);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
		glu.gluLookAt(position_[0], position_[1], position_[2], 
				target_[0], target_[1], target_[2], 
				upVector_[0], upVector_[1], upVector_[2]);
	}
}