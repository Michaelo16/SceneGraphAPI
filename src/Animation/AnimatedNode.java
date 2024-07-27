package Animation;

import com.jogamp.opengl.GL2;

import SceneGraph.Node;
import SceneGraph.Transform;
import SceneGraph.TransformableNode;

public abstract class AnimatedNode implements Node, TransformableNode {
	
	Animation animation_;
	
	public void update() {
        if(animation_ != null) {
            Transform nextTransform = animation_.getNextFrame();
            if(nextTransform != null) {
                this.setTransform(nextTransform);
            }
        }
    }
	
	 public void setAnimation(Animation animation) {
	        this.animation_ = animation;
	    }
	
	
	public abstract void draw(GL2 gl) ;
		
	

	
	public abstract void init(GL2 gl);

		
	


	
	public abstract void setTransform(Transform tran);


	
	public abstract Transform getTransform();
		

}
