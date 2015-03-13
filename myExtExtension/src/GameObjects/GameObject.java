package GameObjects;

import DomeUtils.Transform;

//! Super-class for all entities of the game
public class GameObject {
    private Transform transform;
    
    //! Constructor for GameObject
    public GameObject() {
        //Set ships initial position and rotation to (0,0,0)
        transform = new Transform(0, 0, 0, 0, 0, 0);
    }   
    
    public Transform move(Transform t) {
        this.transform.add(t);
        
        return this.transform;
    }
}
