package SceneGraph;
import java.awt.Color;

/**
 * represents an object material 
 * has ambient, diffuse, and specular colors
 * as well as a shininess. Also keeps track of its texture
 */
public class Material {
	private Color ambientColor;
	private Color diffuseColor;
	private Color specularColor;
	private float shininess;
	private SceneTexture texture_;

	/**
	 * create a new material with default properties
	 * Gray-ish
	 */
	public Material() {
		// Default material properties
		this.ambientColor = Color.GRAY;
		this.diffuseColor = Color.WHITE;
		this.specularColor = Color.WHITE;
		this.shininess = 50.0f;
		this.texture_ = null;
	}
	/**
	 * create a new material and specify the colors 
	 * @param ambientColor
	 * @param diffuseColor
	 * @param specularColor
	 * @param shininess
	 */
	public Material(Color ambientColor, Color diffuseColor, Color specularColor, float shininess) {
		this.ambientColor = ambientColor;
		this.diffuseColor = diffuseColor;
		this.specularColor = specularColor;
		this.shininess = shininess;
	}
	/**
	 * set the ambient color
	 * @param rgb
	 */
	public void setAmbientColor(float[] rgb) {
		Color temp = new Color(rgb[0], rgb[1], rgb[2]);
		this.ambientColor = temp;
	}
	/**
	 * set the diffuse color
	 * @param rgb
	 */
	public void setDiffuseColor(float[] rgb) {
		Color temp = new Color(rgb[0], rgb[1], rgb[2]);
		this.diffuseColor = temp;
	}
	/**
	 * set the specular color
	 * @param rgb
	 */
	public void setSpecularColor(float[] rgb) {
		Color temp = new Color(rgb[0], rgb[1], rgb[2]);
		this.specularColor = temp;
	}
	/**
	 * set the texture
	 * @param tex
	 */
	public void setTexture(SceneTexture tex) {
		this.texture_ = tex;
	}
	/**
	 * return the texture of this material
	 * @return
	 */
	public SceneTexture getTexture() {
		return texture_;
	}
	/**
	 * return the ambient color of this material
	 * @return
	 */
	public Color getAmbientColor() {
		return ambientColor;
	}
	/**
	 * convert the ambient color to an array of rgb values
	 * @return
	 */
	public float[] getAmbientColorArray() {
		float red = (float) ambientColor.getRed() / 255;
		float green = (float) ambientColor.getGreen() / 255;
		float blue = (float) ambientColor.getBlue() / 255;
		float[] color = new float[] {red, green, blue,0};
		return color;
	}
	/**
	 * set the Amvient color
	 * @param ambientColor
	 */
	public void setAmbientColor(Color ambientColor) {
		this.ambientColor = ambientColor;
	}
	/**
	 * get the diffuse color
	 * @return
	 */
	public Color getDiffuseColor() {
		return diffuseColor;
	}
	/**
	 * convert the diffuse color into an array of rgb values
	 * @return
	 */
	public float[] getDiffuseColorArray() {
		float red = (float) diffuseColor.getRed() / 255;
		float green = (float) diffuseColor.getGreen() / 255;
		float blue = (float) diffuseColor.getBlue() / 255;
		float[] color = new float[] {red, green, blue,0};
		return color;
	}
	/**
	 * set the diffuse color
	 * @param diffuseColor
	 */
	public void setDiffuseColor(Color diffuseColor) {
		this.diffuseColor = diffuseColor;
	}
	/**
	 * get the specular color
	 * @return
	 */
	public Color getSpecularColor() {
		return specularColor;
	}
	/**
	 * convert the specular color into an array of rbg values
	 * @return
	 */
	public float[] getSpecularColorArray() {
		float red = (float) specularColor.getRed() / 255;
		float green = (float) specularColor.getGreen() / 255;
		float blue = (float) specularColor.getBlue() / 255;
		float[] color = new float[] {red, green, blue,0};
		return color;
	}
	/*
	 * set the specular color
	 */
	public void setSpecularColor(Color specularColor) {
		this.specularColor = specularColor;
	}
	/**
	 * get the shininess value
	 * @return
	 */
	public float getShininess() {
		return shininess;
	}
	/**
	 * set the shininess value
	 * @param shininess
	 */
	public void setShininess(float shininess) {
		this.shininess = shininess;
	}
}

