package Libraries;

import java.util.HashMap;
import java.util.Map;

import SceneGraph.Material;

/**
 * Contains a Library of Object Materials. If desired, a library of 
 * default materials can be initialized for any given scene. Otherwise, 
 * The user can define their own materials and add them to the library
 * so they can be reused later
 * @Author Michael Andrews
 */

public class MaterialLibrary {

	private static final Map<String, Material> materials_ = new HashMap<>();

	/**
	 * Add a material to the library
	 * Rewuires an id and a material
	 * @param id
	 * @param material
	 */
	public static void addMaterial(String id, Material material) {
		materials_.put(id, material);
	}

	/**
	 * given an id, get the corresponding material
	 * @param id
	 * @return
	 */
	public static Material getMaterial(String id) {
		return materials_.get(id);
	}

	/**
	 * remove a material from the library
	 * @param id
	 */
	public static void removeMaterial(String id) {
		materials_.remove(id);
	}
	
	/**
	 * initialize the provided default materials
	 */
	public static void initDefaults() {

		//Emerald
		Material emerald = new Material();
		emerald.setAmbientColor(new float[] {0.0215f, 0.1745f, 0.0215f});
		emerald.setDiffuseColor(new float[] {0.07568f, 0.61424f, 0.07568f});
		emerald.setSpecularColor(new float[] {0.633f, 0.727811f, 0.633f});
		emerald.setShininess((float)(0.6 * 128));
		addMaterial("emerald", emerald);

		// Jade
		Material jade = new Material();
		jade.setAmbientColor(new float[] {0.135f, 0.2225f, 0.1575f});
		jade.setDiffuseColor(new float[] {0.54f, 0.89f, 0.63f});
		jade.setSpecularColor(new float[] {0.316228f, 0.316228f, 0.316228f});
		jade.setShininess((float)(0.1 * 128));
		addMaterial("jade", jade);

		// Obsidian
		Material obsidian = new Material();
		obsidian.setAmbientColor(new float[] {0.05375f, 0.05f, 0.06625f});
		obsidian.setDiffuseColor(new float[] {0.18275f, 0.17f, 0.22525f});
		obsidian.setSpecularColor(new float[] {0.332741f, 0.328634f, 0.346435f});
		obsidian.setShininess((float)(0.3 * 128));
		addMaterial("obsidian", obsidian);

		// Pearl
		Material pearl = new Material();
		pearl.setAmbientColor(new float[] {0.25f, 0.20725f, 0.20725f});
		pearl.setDiffuseColor(new float[] {1f, 0.829f, 0.829f});
		pearl.setSpecularColor(new float[] {0.296648f, 0.296648f, 0.296648f});
		pearl.setShininess((float)(0.088 * 128));
		addMaterial("pearl", pearl);

		// Ruby
		Material ruby = new Material();
		ruby.setAmbientColor(new float[] {0.1745f, 0.01175f, 0.01175f});
		ruby.setDiffuseColor(new float[] {0.61424f, 0.04136f, 0.04136f});
		ruby.setSpecularColor(new float[] {0.727811f, 0.626959f, 0.626959f});
		ruby.setShininess((float)(0.6 * 128));
		addMaterial("ruby", ruby);

		// Turquoise
		Material turquoise = new Material();
		turquoise.setAmbientColor(new float[] {0.1f, 0.18725f, 0.1745f});
		turquoise.setDiffuseColor(new float[] {0.396f, 0.74151f, 0.69102f});
		turquoise.setSpecularColor(new float[] {0.297254f, 0.30829f, 0.306678f});
		turquoise.setShininess((float)(0.1 * 128));
		addMaterial("turquoise", turquoise);

		// Brass
		Material brass = new Material();
		brass.setAmbientColor(new float[] {0.329412f, 0.223529f, 0.027451f});
		brass.setDiffuseColor(new float[] {0.780392f, 0.568627f, 0.113725f});
		brass.setSpecularColor(new float[] {0.992157f, 0.941176f, 0.807843f});
		brass.setShininess((float)(0.21794872 * 128));
		addMaterial("brass", brass);

		// Bronze
		Material bronze = new Material();
		bronze.setAmbientColor(new float[] {0.2125f, 0.1275f, 0.054f});
		bronze.setDiffuseColor(new float[] {0.714f, 0.4284f, 0.18144f});
		bronze.setSpecularColor(new float[] {0.393548f, 0.271906f, 0.166721f});
		bronze.setShininess((float)(0.2 * 128));
		addMaterial("bronze", bronze);

		// Chrome
		Material chrome = new Material();
		chrome.setAmbientColor(new float[] {0.25f, 0.25f, 0.25f});
		chrome.setDiffuseColor(new float[] {0.4f, 0.4f, 0.4f});
		chrome.setSpecularColor(new float[] {0.774597f, 0.774597f, 0.774597f});
		chrome.setShininess((float)(0.6 * 128));
		addMaterial("chrome", chrome);

		// Copper
		Material copper = new Material();
		copper.setAmbientColor(new float[] {0.19125f, 0.0735f, 0.0225f});
		copper.setDiffuseColor(new float[] {0.7038f, 0.27048f, 0.0828f});
		copper.setSpecularColor(new float[] {0.256777f, 0.137622f, 0.086014f});
		copper.setShininess((float)(0.1 * 128));
		addMaterial("copper", copper);

		// Gold
		Material gold = new Material();
		gold.setAmbientColor(new float[] {0.24725f, 0.1995f, 0.0745f});
		gold.setDiffuseColor(new float[] {0.75164f, 0.60648f, 0.22648f});
		gold.setSpecularColor(new float[] {0.628281f, 0.555802f, 0.366065f});
		gold.setShininess((float)(0.4 * 128));
		addMaterial("gold", gold);

		// Silver
		Material silver = new Material();
		silver.setAmbientColor(new float[] {0.19225f, 0.19225f, 0.19225f});
		silver.setDiffuseColor(new float[] {0.50754f, 0.50754f, 0.50754f});
		silver.setSpecularColor(new float[] {0.508273f, 0.508273f, 0.508273f});
		silver.setShininess((float)(0.4 * 128));
		addMaterial("silver", silver);

		// Black Plastic
		Material blackPlastic = new Material();
		blackPlastic.setAmbientColor(new float[] {0.0f, 0.0f, 0.0f});
		blackPlastic.setDiffuseColor(new float[] {0.01f, 0.01f, 0.01f});
		blackPlastic.setSpecularColor(new float[] {0.50f, 0.50f, 0.50f});
		blackPlastic.setShininess((float)(0.25 * 128));
		addMaterial("black plastic", blackPlastic);

		// Cyan Plastic
		Material cyanPlastic = new Material();
		cyanPlastic.setAmbientColor(new float[] {0.0f, 0.1f, 0.06f});
		cyanPlastic.setDiffuseColor(new float[] {0.0f, 0.50980392f, 0.50980392f});
		cyanPlastic.setSpecularColor(new float[] {0.50196078f, 0.50196078f, 0.50196078f});
		cyanPlastic.setShininess((float)(0.25 * 128));
		addMaterial("cyan plastic", cyanPlastic);

		// Green Plastic
		Material greenPlastic = new Material();
		greenPlastic.setAmbientColor(new float[] {0.0f, 0.0f, 0.0f});
		greenPlastic.setDiffuseColor(new float[] {0.1f, 0.35f, 0.1f});
		greenPlastic.setSpecularColor(new float[] {0.45f, 0.55f, 0.45f});
		greenPlastic.setShininess((float)(0.25 * 128));
		addMaterial("green plastic", greenPlastic);

		// Red Plastic
		Material redPlastic = new Material();
		redPlastic.setAmbientColor(new float[] {0.0f, 0.0f, 0.0f});
		redPlastic.setDiffuseColor(new float[] {0.5f, 0.0f, 0.0f});
		redPlastic.setSpecularColor(new float[] {0.7f, 0.6f, 0.6f});
		redPlastic.setShininess((float)(0.25 * 128));
		addMaterial("red plastic", redPlastic);

		// White Plastic
		Material whitePlastic = new Material();
		whitePlastic.setAmbientColor(new float[] {0.0f, 0.0f, 0.0f});
		whitePlastic.setDiffuseColor(new float[] {0.55f, 0.55f, 0.55f});
		whitePlastic.setSpecularColor(new float[] {0.70f, 0.70f, 0.70f});
		whitePlastic.setShininess((float)(0.25 * 128));
		addMaterial("white plastic", whitePlastic);

		// Yellow Plastic
		Material yellowPlastic = new Material();
		yellowPlastic.setAmbientColor(new float[] {0.0f, 0.0f, 0.0f});
		yellowPlastic.setDiffuseColor(new float[] {0.5f, 0.5f, 0.0f});
		yellowPlastic.setSpecularColor(new float[] {0.60f, 0.60f, 0.50f});
		yellowPlastic.setShininess((float)(0.25 * 128));
		addMaterial("yellow plastic", yellowPlastic);

		// Black Rubber
		Material blackRubber = new Material();
		blackRubber.setAmbientColor(new float[] {0.02f, 0.02f, 0.02f});
		blackRubber.setDiffuseColor(new float[] {0.01f, 0.01f, 0.01f});
		blackRubber.setSpecularColor(new float[] {0.4f, 0.4f, 0.4f});
		blackRubber.setShininess((float)(0.078125 * 128));
		addMaterial("black rubber", blackRubber);

		// Cyan Rubber
		Material cyanRubber = new Material();
		cyanRubber.setAmbientColor(new float[] {0.0f, 0.05f, 0.05f});
		cyanRubber.setDiffuseColor(new float[] {0.4f, 0.5f, 0.5f});
		cyanRubber.setSpecularColor(new float[] {0.04f, 0.7f, 0.7f});
		cyanRubber.setShininess((float)(0.078125 * 128));
		addMaterial("cyan rubber", cyanRubber);

		// Green Rubber
		Material greenRubber = new Material();
		greenRubber.setAmbientColor(new float[] {0.0f, 0.05f, 0.0f});
		greenRubber.setDiffuseColor(new float[] {0.4f, 0.5f, 0.4f});
		greenRubber.setSpecularColor(new float[] {0.04f, 0.7f, 0.04f});
		greenRubber.setShininess((float)(0.078125 * 128));
		addMaterial("green rubber", greenRubber);

		// Red Rubber
		Material redRubber = new Material();
		redRubber.setAmbientColor(new float[] {0.05f, 0.0f, 0.0f});
		redRubber.setDiffuseColor(new float[] {0.5f, 0.4f, 0.4f});
		redRubber.setSpecularColor(new float[] {0.7f, 0.04f, 0.04f});
		redRubber.setShininess((float)(0.078125 * 128));
		addMaterial("red rubber", redRubber);

		// White Rubber
		Material whiteRubber = new Material();
		whiteRubber.setAmbientColor(new float[] {0.05f, 0.05f, 0.05f});
		whiteRubber.setDiffuseColor(new float[] {0.5f, 0.5f, 0.5f});
		whiteRubber.setSpecularColor(new float[] {0.7f, 0.7f, 0.7f});
		whiteRubber.setShininess((float)(0.078125 * 128));
		addMaterial("white rubber", whiteRubber);

		// Yellow Rubber
		Material yellowRubber = new Material();
		yellowRubber.setAmbientColor(new float[] {0.05f, 0.05f, 0.0f});
		yellowRubber.setDiffuseColor(new float[] {0.5f, 0.5f, 0.4f});
		yellowRubber.setSpecularColor(new float[] {0.7f, 0.7f, 0.04f});
		yellowRubber.setShininess((float)(0.078125 * 128));
		addMaterial("yellow rubber", yellowRubber);
	}

}




