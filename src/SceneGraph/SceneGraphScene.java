package SceneGraph;

import java.io.IOException;
import java.util.HashMap;

import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.util.texture.Texture;

@SuppressWarnings("serial")
/**
 * represents a Scene Graph. provides some utility to all scene graphs 
 * in keeping track of current transform and textures. All scenes created using
 * this API should extend SceneGraphScene
 */
public abstract class SceneGraphScene extends GLJPanel {

	protected Transform currTransform_;
	HashMap<String, SceneTexture> textures_;

	public SceneGraphScene() {
		super(new GLCapabilities(null));
		currTransform_ = new Transform();
		textures_ = new HashMap<String,SceneTexture>();
		
	}
	
	//Methods for handling scene Textures
	
	/**
	 * get a texture
	 * @param filename
	 * @return
	 */
	public SceneTexture getTexture(String filename) {
		return textures_.get(filename);
	}
	/**
	 * add a texture to the scene
	 * @param filename
	 * @throws IOException
	 */
	public void addTexture( String filename) throws IOException {
		textures_.put(filename,new SceneTexture(filename) );
	}
	
	
	
	
	
	//Methods for handling the current transform while constructing a scene

	/**
	 * resets the current transform to a new default transform 
	 */
	public void resetTransform() {
		currTransform_ = new Transform();
	}
	/**
	 * translate the current tranform 
	 * @param x
	 * @param y
	 * @param z
	 */
	public void Translate(float x, float y, float z) {
		currTransform_.translate(x,y,z);
	}
	/**
	 * rotate the current transform
	 * @param angle
	 * @param x
	 * @param y
	 * @param z
	 */
	public void Rotate(float angle, float x,float y,float z) {
		currTransform_.rotate(angle, x, y, z);
	}
	/**
	 * scale the current transform 
	 * @param x
	 * @param y
	 * @param z
	 */
	public void Scale(float x, float y, float z) {
		currTransform_.scale(x, y, z);
	}
	/**
	 * regturns the current transform 
	 * @return
	 */
	public Transform getCurrTransform() {
		return currTransform_;
	}
	
}
