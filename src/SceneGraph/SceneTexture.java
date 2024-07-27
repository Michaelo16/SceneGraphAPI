package SceneGraph;

import java.io.File;
import java.io.IOException;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
/**
 * Essentially attaches a Texture object to it's string Filename
 * allows for streamlined adding and implementing of new textures
 * Can initialize and enable textures so the user doesn't have to. 
 * @author Michael Andrews
 */
public class SceneTexture {
	Texture texture_;
	String fileName_;
	
	public SceneTexture(String filename) {
       this.fileName_ = filename;
    }
    public void init(GL2 gl) {
    	 try {
             texture_ = createNewTexture(fileName_);
             bind(gl);
             enable(gl);
         } catch (IOException e) {
             e.printStackTrace();
         }
    	 
    }
	/**
	 * returns the texture object
	 * @return
	 */
    public Texture getTexture() {
    	return texture_;
    }
    /**
     * binds the texture object
     * @param gl
     */
    public void bind(GL2 gl) {
        texture_.bind(gl);
    }
    /**
     * enables the texture object
     * @param gl
     */
    public void enable(GL2 gl) {
    	texture_.enable(gl);
    }
    /**
     * creates a new texture object given a fileName
     * @param tex
     * @return
     * @throws IOException
     */
    private Texture createNewTexture(String tex) throws IOException {
		
    	File file = new File("Textures" + "\\" + tex);
		Texture texture = TextureIO.newTexture(file, true);
		
		return texture;
	}
    
}
