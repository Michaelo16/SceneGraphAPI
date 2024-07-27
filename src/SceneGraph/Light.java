package SceneGraph;
import com.jogamp.opengl.GL2;

import Utility.LinAlg;
/**
 * represents a light in a scene. Has varaibles for its positon, direction
 * color elements, and its cutoff angle
 */
public class Light {
	private float[] position_;
	private float[] ambient_;
	private float[] diffuse_;
	private float[] specular_;
	private float[] direction_; // Direction for spotlights
	private float cutoff_; // Cutoff angle for spotlights
	private int lightId_;

	/**
	 * create a new Light with default values
	 * @param lightId
	 */
	public Light(int lightId) {
		this.lightId_ = lightId; // GL_LIGHT0, GL_LIGHT1, etc.

		// Default light properties
		this.position_ = new float[]{0.0f, 0.0f, 1.0f, 0.0f}; // Directional light
		this.ambient_ = new float[]{0.2f, 0.2f, 0.2f, 1.0f};
		this.diffuse_ = new float[]{0.8f, 0.8f, 0.8f, 1.0f};
		this.specular_ = new float[]{1.0f, 1.0f, 1.0f, 1.0f};
		this.direction_ = new float[]{0.0f, 0.0f, -1.0f}; // Default pointing down
		this.cutoff_ = 180.0f; // Default cutoff angle, no spotlight effect
	}

	// Setter methods
	/**
	 * set light position
	 * @param position
	 */
	public void setPosition(float[] position) {
		this.position_ = position;
	}
	/**
	 * set the ambient color of this light
	 * @param ambient
	 */
	public void setAmbient(float[] ambient) {
		this.ambient_ = ambient;
	}
	/**
	 * set the diffuse color
	 * @param diffuse
	 */
	public void setDiffuse(float[] diffuse) {
		this.diffuse_ = diffuse;
	}
	/**
	 * set the specular color
	 * @param specular
	 */
	public void setSpecular(float[] specular) {
		this.specular_ = specular;
	}
	/**
	 * set the direction
	 * @param direction
	 */
	public void setDirection(float[] direction) {
		this.direction_ = direction;
	}
	/**
	 * set the cutoff Angle
	 * @param cutoff
	 */
	public void setCutoff(float cutoff) {
		this.cutoff_ = cutoff;
	}

	/**
	 * enable this light
	 * @param gl
	 */
	public void enable(GL2 gl) {
		gl.glEnable(lightId_);
	}
	/**
	 * return the position of this light
	 * @return
	 */
	public float[] getPos() {
		return position_;
	}
	/*
	 * get the direction of this light
	 */
	public float[] getDirection() {
		return direction_;
	}

	// configure the light
	/**
	 * configure important light settings
	 * @param gl
	 */
	public void configure(GL2 gl) {

		gl.glLightfv(lightId_, GL2.GL_POSITION, position_, 0);
		gl.glLightfv(lightId_, GL2.GL_AMBIENT, ambient_, 0);
		gl.glLightfv(lightId_, GL2.GL_DIFFUSE, diffuse_, 0);
		gl.glLightfv(lightId_, GL2.GL_SPECULAR, specular_, 0);

		if (position_[3] != 0.0) { // If it is not a directional light
			gl.glLightf(lightId_, GL2.GL_CONSTANT_ATTENUATION, 1.0f);
			gl.glLightf(lightId_, GL2.GL_LINEAR_ATTENUATION, 0.0f);
			gl.glLightf(lightId_, GL2.GL_QUADRATIC_ATTENUATION, 0.0f);
		}

		if (cutoff_ < 180) { // If it is a spotlight
			gl.glLightfv(lightId_, GL2.GL_SPOT_DIRECTION, direction_, 0);
			gl.glLightf(lightId_, GL2.GL_SPOT_CUTOFF, cutoff_);
		}
	}

	public void disable(GL2 gl) {
		gl.glDisable(lightId_);
	}

	/**
	 * handles transforming the light 
	 * @param tran
	 */
	public void transform(Transform tran) {

		//Rotation
		float[] axis = new float[] {tran.getRotate()[1],
				tran.getRotate()[2], tran.getRotate()[3]};
		float angle = tran.getRotate()[0];


		//Rotating the target point
		if (angle != 0 && (axis[0] != 0 || axis[1] != 0 || axis[2] != 0)) { // Checking if rotation is specified
			float[] direction = new float[] {this.direction_[0] - position_[0], this.direction_[1] - position_[1], this.direction_[2] - position_[2]};
			direction = rotateDirectionVector(angle, axis, direction);

			// Updating target based on new direction
			direction[0] = position_[0] + direction[0];
			direction[1] = position_[1] + direction[1];
			direction[2] = position_[2] + direction[2];
		}

		//Translation
		Translate(tran);

		//Scaling - not really important. What would even happen if
		//you scaled a light bigger or smaller? Not worth implementing
	}

	//Translation Stuff

	private void Translate(Transform tran) {
		// Obtain the translation values from the Transform object
		float[] translate = tran.getTranslate();
		for(int i = 0; i < 3; i++) {
			System.out.println(tran.getTranslate()[i]);
		}

		position_ = new float[] {
				position_[0] + translate[0],
				position_[1] + translate[1],
				position_[2] + translate[2],
				position_[3]
		};

		// Also update the target (or direction) by applying the same translation
		direction_ = new float[] {
				direction_[0] + translate[0],
				direction_[1] + translate[1],
				direction_[2] + translate[2]
		};
	}


	//Rotation stuff
	private float[] rotateDirectionVector(float angle, float[] axis, float[] direction) {
		float[] normalizedAxis = LinAlg.normalize(axis);//Normalize axis
		float cosTheta = (float) Math.cos(Math.toRadians(angle));//Calculating cos and sin of rotation angle
		float sinTheta = (float) Math.sin(Math.toRadians(angle));//Then convert to radians

		//we need to find the cross product and dot product of the normalized axis and the direction vector
		float[] crossProduct = crossProduct(normalizedAxis, direction);
		float dotProduct = dotProduct(normalizedAxis, direction);

		//Simply apply the formula to each component of the direction vector
		for (int i = 0; i < 3; i++) {
			direction[i] = 
					direction[i] * cosTheta 
					+ crossProduct[i] * sinTheta 
					+ normalizedAxis[i] * dotProduct * (1 - cosTheta);
		}
		float[] result = direction;
		return result;
	}

	//Helper methods for vector math bleh
	private float[] crossProduct(float[] a, float[] b) {
		return new float[]{
				a[1] * b[2] - a[2] * b[1],
				a[2] * b[0] - a[0] * b[2],
				a[0] * b[1] - a[1] * b[0],
		};
	}

	private float dotProduct(float[] a, float[] b) {
		return a[0] * b[0] + a[1] * b[1] + a[2] * b[2];
	}



}

