package DomeUtils;

import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;

//Class for handling the "Fire" operation.
public class Fire {
    private double x, y;
    private boolean isFiring = false;
    
    
    //constructor
    
    public Fire(double _x, double _y, boolean _firing){
        this.x = _x;
        this.y = _y;
        this.isFiring = _firing;
    }
    
    
}
