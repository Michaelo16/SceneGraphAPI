package Scenes;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

import Libraries.MaterialLibrary;
import Libraries.MeshObjectLibrary;
import SceneGraph.Camera;
import SceneGraph.CameraNode;
import SceneGraph.Geometry.Cone;
import SceneGraph.Geometry.Torus;
import SceneGraph.GroupNode;
import SceneGraph.Light;
import SceneGraph.LightNode;
import SceneGraph.Material;
import SceneGraph.MeshGeometry;
import SceneGraph.Renderer;
import SceneGraph.Scene;
import SceneGraph.SceneGraphScene;
import SceneGraph.Geometry.Cylinder;

@SuppressWarnings("serial")
/**
 * Creates a more complex scene, demonstrating object hierarchy, more 
 * materials from the library and my semi-custom sphere mesh 
 * all to make some fun snowmen weeee!
 */
public class SnowmanScene extends SceneGraphScene implements GLEventListener {

	private Renderer renderer;
	private Scene scene_;

	public SnowmanScene() {
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
		MaterialLibrary.initDefaults();

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
		float[] back = new float[] {(float)(68/255),(float)(185/255), (float)(245/255)};
		renderer.setBackground(back);
		renderer.setup(gl2);

	}

	public static void main(String[] args) {
		// create the window
		JFrame window = new JFrame("SnowmanScene");

		// create the drawing panel
		SnowmanScene panel = new SnowmanScene();

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
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		// Adding a camera and setting its properties
		Camera camera = new Camera();
		camera.setFovY(20);
		camera.setAspect(1);
		camera.setNear(1);
		camera.setFar(1000);
		float[] camPos = new float[] {10,20,30};
		float[] target = new float[] {0,0,0};
		camera.setPosition(camPos);
		camera.setTarget(target);
		camera.setUpVector(new float[] {0,1,0});
		CameraNode cam1 = new CameraNode(camera);

		//Adding the camera to the scene
		scene_.addCameraNode(cam1);

		// Adding a default light
		Light light1 = new Light(GL2.GL_LIGHT1);
		light1.enable(gl);
		float[] lightPos = new float[] {0,10,10,1};
		float[] amb = new float[] {1,1,1}; float[] dif = new float[] {1,1,1};float[] spc = new float[] {1,1,1};
		light1.setAmbient(amb);light1.setDiffuse(dif);light1.setSpecular(spc);
		light1.setPosition(lightPos);
		scene_.addLightNode( new LightNode(light1));

		// Creating and adding geometries with materials and transformations
		Material snowMaterial = new Material();
		snowMaterial.setDiffuseColor(Color.WHITE);
		snowMaterial.setAmbientColor(Color.WHITE);
		snowMaterial.setSpecularColor(Color.WHITE);
		resetTransform();

		snowMaterial.setTexture(getTexture("snow.jpg"));
		
		GroupNode SnowMan1 = new GroupNode();
		GroupNode SnowMan2 = new GroupNode();
		GroupNode SnowMan3 = new GroupNode();
		GroupNode SnowMan4 = new GroupNode();

		resetTransform();
		Translate(5,3,0);
		Rotate(20,0,1,0);
		SnowMan1.setTransform(currTransform_);
		drawSnowMan(SnowMan1, snowMaterial);
		
		resetTransform();
		Translate(-3,1,0);
		Rotate(-20,0,1,0);
		SnowMan2.setTransform(currTransform_);
		drawSnowMan(SnowMan2, snowMaterial);
		
		resetTransform();
		Translate(0,0,0);
		SnowMan3.setTransform(currTransform_);
		drawSnowMan(SnowMan3, snowMaterial);

		resetTransform();
		Translate(2.5f,-2,0);
		Rotate(10,0,1,0);
		SnowMan4.setTransform(currTransform_);
		drawSnowMan(SnowMan4, snowMaterial);

		scene_.addGroupNode(SnowMan1);
		scene_.addGroupNode(SnowMan2);
		scene_.addGroupNode(SnowMan3);
		scene_.addGroupNode(SnowMan4);

		renderer.setScene(scene_);
		GL2 gl2 = drawable.getGL().getGL2();
		renderer.draw(gl2);
	}

	public void drawSnowMan(GroupNode SnowMan, Material snowMaterial) {
		drawSnowmanBody(SnowMan, snowMaterial);
		drawEyes(SnowMan);
		drawNose(SnowMan);
		drawHat(SnowMan);
		
		
	}
	
	public void drawEyes(GroupNode SnowMan) {
		MeshGeometry eye1 = MeshObjectLibrary.getMeshObject("Sphere");
		eye1.setShading(false);
		eye1.setTexGen(true);
		eye1.setGenerationMode(GL2.GL_EYE_LINEAR);
		resetTransform();
		Scale(.1f,.1f,.1f);
		Translate(-.1f,2.65f,0.8f);
		eye1.setTransform(currTransform_);
		eye1.setMaterial(MaterialLibrary.getMaterial("obsidian"));
		SnowMan.addMeshGeometryChild(eye1);

		MeshGeometry eye2 = MeshObjectLibrary.getMeshObject("Sphere");
		eye2.setShading(false);
		eye2.setGenerationMode(GL2.GL_EYE_LINEAR);
		resetTransform();
		Scale(.1f,.1f,.1f);
		Translate(.64f,2.7f,.8f);
		eye2.setTransform(currTransform_);
		eye2.setMaterial(MaterialLibrary.getMaterial("obsidian"));
		SnowMan.addMeshGeometryChild(eye2);
	}

	public void drawSnowmanBody(GroupNode SnowMan, Material snowMaterial) {
		//Snow Man Base
		MeshGeometry base = MeshObjectLibrary.getMeshObject("Sphere");
		base.setShading(false);
		base.setTexGen(true);
		base.setGenerationMode(GL2.GL_EYE_LINEAR);
		resetTransform();
		Scale(1.1f,1,1);
		Translate(0,0,0);
		base.setTransform(currTransform_);
		base.setMaterial(snowMaterial);
		SnowMan.addMeshGeometryChild(base);

		//Middle section
		MeshGeometry middle = MeshObjectLibrary.getMeshObject("Sphere");
		middle.setShading(false);
		middle.setTexGen(true);
		middle.setGenerationMode(GL2.GL_EYE_LINEAR);
		middle.setMaterial(snowMaterial);
		resetTransform();
		Scale(.8f,.8f,.8f);
		Translate(0,1.3f,0);
		middle.setTransform(currTransform_);
		SnowMan.addMeshGeometryChild(middle);

		//Head section
		MeshGeometry head = MeshObjectLibrary.getMeshObject("Sphere");
		head.setShading(false);
		head.setTexGen(true);
		head.setGenerationMode(GL2.GL_EYE_LINEAR);
		head.setMaterial(snowMaterial);
		resetTransform();
		Scale(.7f,.7f,.7f);
		Translate(0,2.5f,0);
		head.setTransform(currTransform_);
		SnowMan.addGeometryChild(head);
	}

	public void drawNose(GroupNode SnowMan) {
		Cone nose = new Cone(.2f, 1.7f, 32,32,32);
		resetTransform();
		Rotate(20,0,1,0);
		Rotate(-10, 1,0,0);
		Translate(0.3f,2.4f,.8f);
		nose.setTransform(currTransform_);
		nose.setMaterial(MaterialLibrary.getMaterial("brass"));
		SnowMan.addGeometryChild(nose);
	}

	public void drawHat(GroupNode SnowMan) {
		Cylinder hat = new Cylinder(.8f,1.5f,32,32,32);
		resetTransform();
		Translate(.15f,4.7f,.5f);
		Rotate(90, 1,0,0);
		Scale(1f,1f,.8f);


		hat.setTransform(currTransform_);
		hat.setMaterial(MaterialLibrary.getMaterial("obsidian"));
		SnowMan.addGeometryChild(hat);

		Cylinder hatBrim = new Cylinder(1,2,32,32,32);
		resetTransform();


		Translate(0.05f,3.3f,0.1f);
		Rotate(90, 1,0,0);
		Scale(.9f,.9f,.1f);
		hatBrim.setTransform(currTransform_);
		hatBrim.setMaterial(MaterialLibrary.getMaterial("obsidian"));
		SnowMan.addGeometryChild(hatBrim);
	}

}



