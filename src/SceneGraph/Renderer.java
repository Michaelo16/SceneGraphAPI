package SceneGraph;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;

/**
 * takes a scene and sets up the GL settings
 * Background color can be manually specified. However for 
 * some reason it wasn't working so it is hardcoded in right now.
 * Draws a scene
 * 
 * @author Michael Andrews
 */
public class Renderer {

	private Scene scene;
	private GLU glu;
	private float[] background_;

	public Renderer() {
		this.glu = new GLU();
		this.background_ = new float[] {0.0f,0.0f,0.0f};
	}
	/**
	 * shoudl set the background color. But it doesn't seem to work
	 * @param back
	 */
	public void setBackground(float[] back) {
		this.background_ = back;
	}

	/**
	 * setup OpenGL settings
	 * @param gl
	 */
	public void setup(GL2 gl) {
		gl.glEnable(GL2.GL_DEPTH_TEST); // Enable depth testing
		gl.glDepthFunc(GL2.GL_LEQUAL); // Set depth function
		gl.glEnable(GL2.GL_LIGHTING);
		gl.glEnableClientState(GL2.GL_TEXTURE_COORD_ARRAY);
		gl.glShadeModel(GL2.GL_SMOOTH); // Enable smooth shading
		
	}
	/**
	 * set the scene to be rendered
	 * @param scene
	 */
	public void setScene(Scene scene) {
		this.scene = scene;
	}
	/**
	 * draw the scene and clear buffers
	 * @param gl
	 */
	public void draw(GL2 gl) {
		gl.glClearColor(.2666f,.7254f ,.9607f,1); // background color 
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
		gl.glClear(GL2.GL_DEPTH_BUFFER_BIT); // Clear buffers
		gl.glLoadIdentity(); // Load the identity matrix to reset transformations

		scene.render(gl); // Render the scene graph
	}






}

