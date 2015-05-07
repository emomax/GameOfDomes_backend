package myExtPackage;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
import java.util.ArrayList;
import java.util.List;

public class GunnerControlHandler extends BaseClientRequestHandler{
    
    @Override
    public void handleClientRequest(User user, ISFSObject objIn) {
        
        //attempt to send data to clients
        try {
            sendData(user, objIn.getDouble("inputRotY"), 
                objIn.getDouble("inputRotX"),   
                objIn.getBool("isFiring"));
        } catch (Exception e) {
            trace("Invalid data received.. ignoring sending it further.");
        }
    }
    
    private void sendData(User fromUser, double _rotY, double _rotX, boolean _isFiring) {
        
        trace("Got variables and sending data from gunner: \n(" + _rotX + ", " + _rotY + ")");
        
        //create the out object
        ISFSObject output = new SFSObject();
        
        //include  the input  logic
        output.putDouble("sgctRotY", _rotY);
        output.putDouble("sgctRotX", _rotX);
        output.putBool("sgctFire", _isFiring);
        
        // get all users connected.
        List<User> userList = new ArrayList(this.getParentExtension().getParentZone().getUserList());
        
        //send data to clients
        this.send("GunnerEvent", output, userList, false); //replace userList with SGCTclient
    }
}
