package Scenes;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

import Libraries.MeshObjectLibrary;
import SceneGraph.Renderer;
import SceneGraph.Scene;
import SceneGraph.SceneGraphScene;

@SuppressWarnings("serial")
/**
 * template class for creating new scenes
 * Fill in the scene in the display method
 * -Didn't have time to do this one :(
 * @author Michael A.
 */
public class RobotScene extends SceneGraphScene implements GLEventListener {

	private Renderer renderer;
	private Scene scene_;

	public RobotScene() {
		super();
		currTransform_ = getCurrTransform();
		// create the drawing panel, with the default OpenGL capabilities
		setPreferredSize(new Dimension(700,700));

		// specify handlers for events on the panel
		addGLEventListener(this); // OpenGL events

		this.addGLEventListener(this);
	}


	public void init(GLAutoDrawable drawable) {
		GL2 gl2 = drawable.getGL().getGL2();
		//Add textures to the scene 
		MeshObjectLibrary.initDefualt();
		
		try {
			addTexture("snow.jpg");
			addTexture("waterpl.jpg");
			addTexture("glassblk.jpg");
		} catch (IOException e) {
			System.out.println("texture init failed");
			e.printStackTrace();
		}
		renderer = new Renderer();
		scene_ = new Scene();
		renderer.setup(gl2);
	}
	
	public static void main(String[] args) {
		// create the window
		JFrame window = new JFrame("Scene Name Here");

		// create the drawing panel
		Simple3DScene panel = new Simple3DScene();

		// add the drawing panel to the window, and finish window configuration
		window.setContentPane(panel);
		window.pack();

		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit program when
		// window is closed
		window.setVisible(true);

	}




	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub

	}


	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub

	}


	@Override
	public void display(GLAutoDrawable arg0) {
		
		
	}




}


	
