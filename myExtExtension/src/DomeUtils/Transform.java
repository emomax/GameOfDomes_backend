package DomeUtils;

import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import java.util.Random;

//! Class for handling transformation matrices.
public class Transform {
    private double x, y, z;
    private double rotx, roty, rotz;
    
    // Stored for possible inter- or extrapolation
    private long timeStamp = 0;
    
    // Used for random generation
    private static Random rand = new Random();
    
    //! Constructor of transform. Input translation and rotation.
    public Transform(double _x, double _y, double _z, 
                     double _rotx, double _roty, double _rotz) {
        this.x = _x;
        this.y = _y;
        this.z = _z;
        
        this.rotx = _rotx;
        this.roty = _roty;
        this.rotz = _rotz;
    }
    
    public void add(Transform t) {
        this.x += t.getX();
        this.y += t.getY();
        this.z += t.getZ();
        this.rotx += t.getRotx();
        this.roty += t.getRoty();
        this.rotz += t.getRotz();
    }
    
    // getters of everything
    public double getRotx() { return rotx; }
    public double getRoty() { return roty; }
    public double getRotz() { return rotz; }
    
    public double getX() { return x; }
    public double getY() { return y; }
    public double getZ() { return z; }
    
    //! Setting the current timestamp for interpolation or extrapolation
    public void setTimeStamp(long timeStamp) {
            this.timeStamp = timeStamp;
    }
    
    //! Fetch data and populate Transform object with it.
    public static Transform fromSFSObject(ISFSObject data) {
        ISFSObject transformData = data.getSFSObject("transform");
        
        // set the variables from the data object
        double _x = transformData.getDouble("x");
        double _y = transformData.getDouble("y");
        double _z = transformData.getDouble("z");
        
        double _rx = transformData.getDouble("rx");
        double _ry = transformData.getDouble("ry");
        double _rz = transformData.getDouble("rz");
        
        // For interpolation / extrapolation purposes
        long timeStamp = transformData.getLong("t");
        
        // create a new transform and return it
        Transform transform = new Transform(_x, _y, _z, _rx, _ry, _rz);
        transform.setTimeStamp(timeStamp);
        return transform;
    }
    
    //! Prepare this Transform object for broadcasting.
    public void toSFSObject(ISFSObject data) {
        data.putDouble("x", x);
        data.putDouble("y", y);
        data.putDouble("z", z);
        
        data.putDouble("rotX", rotx);
        data.putDouble("rotY", roty);
        data.putDouble("rotZ", rotz);
    }
}
