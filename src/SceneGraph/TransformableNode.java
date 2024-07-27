package SceneGraph;
/**
 * represents Nodes that require special transformation methods
 * @author Michael
 */
public interface TransformableNode extends Node	{
	
	public void setTransform(Transform tran);
	public Transform getTransform();
}
