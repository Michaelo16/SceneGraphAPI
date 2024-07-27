package SceneGraph;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.math.Matrix4f;
/**
 * interface for Scene graph nodes
 * Paramterizes Nodes
 */
public interface Node {
	void draw(GL2 gl);
	void init(GL2 gl);
	
}


