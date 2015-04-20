package myExtPackage;
import DomeUtils.RoomHelper;
import DomeUtils.UserHelper;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
import java.util.List;

public class GunnerControlHandler extends BaseClientRequestHandler{
    
    @Override
    public void handleClientRequest(User user, ISFSObject objIn) {
        
        //send data to clients
        sendData(user, objIn.getDouble("inputRotY"), 
                objIn.getDouble("inputRotX"),   
                objIn.getBool("isFiring"));
    }
    
    private void sendData(User fromUser, double _rotY, double _rotX, boolean _isFiring) {
        
        trace("Got variables and sending data from gunner: \n(" + _rotX + ", " + _rotY + ")");
        
        //create the out object
        ISFSObject output = new SFSObject();
        
        //include  the input  logic
        //output.putBool("sgctUp", _up);
        //output.putBool("sgctDown", _down);
        //output.putBool("sgctLeft", _left);
        //output.putBool("sgctRight", _right);
        output.putDouble("sgctRotY", _rotY);
        output.putDouble("sgctRotX", _rotX);
        output.putBool("sgctFire", _isFiring);
        
        //get current room
        Room currentRoom = RoomHelper.getCurrentRoom(this);
        
        //check that there is a room
      /*  if (currentRoom != null)
            trace("Room is: " + currentRoom.getName());
        else 
            trace("Room is NULL!");
        */
        //get user list
        List<User> userList = UserHelper.getRecipientsList(currentRoom);
        
        //send data to clients
        this.send("GunnerEvent", output, userList, false); //replace userList with SGCTclient
    }
}
