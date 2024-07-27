package Scenes;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLJPanel;

import Libraries.MaterialLibrary;
import Libraries.MeshObjectLibrary;
import SceneGraph.Camera;
import SceneGraph.CameraNode;
import SceneGraph.Geometry;
import SceneGraph.Light;
import SceneGraph.LightNode;
import SceneGraph.Material;
import SceneGraph.MeshGeometry;
import SceneGraph.Renderer;
import SceneGraph.Scene;
import SceneGraph.SceneGraphScene;
import SceneGraph.Geometry.Cube;
import SceneGraph.Geometry.Sphere;
import Utility.Polyhedron;


@SuppressWarnings("serial")
/**
 * just creates a simple 3D scene with a camera and a Light node. 
 * Notice that the position and direction of the camera and light node are 
 * affected by transformations
 * @author Michael Andrews
 */
public class Simple3DScene extends SceneGraphScene implements GLEventListener {

	private Renderer renderer;
	private Scene scene_;

	public Simple3DScene() {
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
		//Add textures to the scene and init libraries
		MeshObjectLibrary.initDefualt();
		MaterialLibrary.initDefaults();
		
		try {
			addTexture("snow.jpg");
			addTexture("waterpl.jpg");

		} catch (IOException e) {
			System.out.println("texture init failed");
			e.printStackTrace();
		}
		renderer = new Renderer();
		scene_ = new Scene();
		renderer.setup(gl2);
	}


	public void display(GLAutoDrawable drawable) {
		//Create a new scene and set it as the scene to be rendered
		resetTransform();

		GL2 gl = drawable.getGL().getGL2();
		// Adding a camera and setting its properties
		Camera camera = new Camera();
		camera.setFovY(20);
		camera.setAspect(1);
		camera.setNear(1);
		camera.setFar(1000);
		float[] camPos = new float[] {0,0,20};
		float[] target = new float[] {0,0,0};
		camera.setPosition(camPos);
		camera.setTarget(target);
		camera.setUpVector(new float[] {0,1,0});
		//The view rotates!
		Rotate(5, 0,1,0);
		CameraNode cam1 = new CameraNode(camera);
		cam1.setTransform(currTransform_);
		//Adding the camera to the scene
		scene_.addCameraNode(cam1);
		
		
		// Adding a default light
		Light light1 = new Light(GL2.GL_LIGHT1);
		light1.enable(gl);
		float[] lightPos = new float[] {0,10,10,1};
		float[] amb = new float[] {1,1,1}; float[] dif = new float[] {1,1,1};float[] spc = new float[] {1,1,1};
		light1.setAmbient(amb);light1.setDiffuse(dif);light1.setSpecular(spc);
		light1.setPosition(lightPos);
		resetTransform();
		//The Light moves!!
		Translate(0,0,-15);
		LightNode l1 = new LightNode(light1);
		l1.setTransform(currTransform_);
		scene_.addLightNode( l1);
		
		//Adding materials
		Material waterMaterial = new Material();
		waterMaterial.setDiffuseColor(Color.BLUE);
		waterMaterial.setAmbientColor(Color.WHITE);
		waterMaterial.setSpecularColor(Color.BLUE);

		Material SnowMaterial = new Material();
		waterMaterial.setDiffuseColor(Color.WHITE);
		waterMaterial.setAmbientColor(Color.WHITE);
		waterMaterial.setSpecularColor(Color.WHITE);
		resetTransform();
		
		
		waterMaterial.setTexture(getTexture("waterpl.jpg"));
		SnowMaterial.setTexture(getTexture("snow.jpg"));
		
		//Testing out the Sphere with smooth shading
		MeshGeometry sphere = MeshObjectLibrary.getMeshObject("Sphere");
		sphere.setShading(true);
		sphere.setTexGen(false);
		sphere.setGenerationMode(GL2.GL_EYE_LINEAR);
		sphere.setMaterial(SnowMaterial);
		resetTransform();
		Translate(-3,0,0);
		sphere.setTransform(currTransform_);
		scene_.addGeometryNode(sphere);
		
		//Creating a Polyhedron or MeshGeometry and generating texture coords
		Polyhedron polySoccer = Polyhedron.SOCCER_BALL;
		MeshGeometry soccer = new MeshGeometry(polySoccer.vertices, polySoccer.faces,
				null,null,true,false, true) ;
		
		soccer.setGenerationMode(GL2.GL_OBJECT_LINEAR);
		soccer.setMaterial(waterMaterial);
		soccer.setEdgeColor(Color.GREEN);
		resetTransform();	
	
		soccer.setTransform(currTransform_);
		scene_.addGeometryNode(soccer);

		// Setting up the renderer
		
		renderer.setScene(scene_);
		GL2 gl2 = drawable.getGL().getGL2();
		renderer.draw(gl2);

	}

	public static void main(String[] args) {
		// create the window
		JFrame window = new JFrame("Simple3DScene");

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




}

