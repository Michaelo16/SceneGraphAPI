package Animation;

import java.util.List;

import SceneGraph.Transform;

public class Animation {
	List<AnimationFrame> frames;
	boolean loop; // Determines whether the animation should loop
	int currentFrameIndex = 0;
	
	public Animation(List<AnimationFrame> frames, boolean loop) {
		this.frames = frames;
		this.loop = loop;
	}

	public Transform getNextFrame() {
		if(currentFrameIndex >= frames.size()) {
			if(loop) {
				currentFrameIndex = 0;
			} else {
				return null; 
			}
		}
		return frames.get(currentFrameIndex++).transform_;
	}
	
	public void addFrame(AnimationFrame frame) {
		frames.add(frame);
	}
	
	public void setFrames(List<AnimationFrame> frames) {
		this.frames = frames;
	}
	
}
