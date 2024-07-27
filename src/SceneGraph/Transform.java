package SceneGraph;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.math.Matrix4f;
import com.jogamp.opengl.math.Vec3f;
/**
 * represents a given transform. Transforms are applied in 
 * Scale-Rotate-Translate order. 
 * Has methods for udpating Transform values
 * @author Michael A.
 */
public class Transform {

    private float tx, ty, tz; // Translation
    private float rx, ry, rz, angle; // Rotation
    private float sx, sy, sz; // Scaling

    /**
     * crate a new default Transform 
     */
    public Transform() {
        // Initialize transformations
        tx = ty = tz = 0;
        rx = ry = rz = 0; angle = 0;
        sx = sy = sz = 1;
    }

    // Setters for translations, rotations, and scaling
    /**
     * Translate
     * @param x
     * @param y
     * @param z
     */
    public void translate(float x, float y, float z) {
        tx = x; ty = y; tz = z;
    }
    /**
     * rotate
     * @param angle
     * @param x
     * @param y
     * @param z
     */
    public void rotate(float angle, float x, float y, float z) {
        this.angle = angle;
        rx = x; ry = y; rz = z;
    }
    /**
     * scale
     * @param x
     * @param y
     * @param z
     */
    public void scale(float x, float y, float z) {
        sx = x; sy = y; sz = z;
    }
    /**
     * return the x,y,z values of this translation
     * @return
     */
    public float[] getTranslate() {
    	float[] temp = new float[] {tx,ty,tz};
    	return temp;
    }
    /**
     * return the angle, x,y,z values for this rotation
     * @return
     */
    public float[] getRotate() {
    	float[] temp = new float[] {angle, rx,ry,rz};
    	return temp;
    }
    /**
     * return x,y,z values of this scaling
     * @return
     */
    public float[] getScale() {
    	float[] temp = new float[] {sx,sy,sz};
    	return temp;
    }
    
    // Apply the transformations using OpenGL
    /**
     * applies Transformations via OpenGL commands
     * Scale-Rotate-Translate
     * @param gl
     */
    public void applyTransformations(GL2 gl) {
        gl.glTranslatef(tx, ty, tz);
        gl.glRotatef(angle, rx, ry, rz);
        gl.glScalef(sx, sy, sz);
    }
}


