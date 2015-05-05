package myExtPackage;
import DomeUtils.RoomHelper;
import DomeUtils.UserHelper;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
import java.util.List;

public class PilotControlHandler extends BaseClientRequestHandler{
    
    @Override
    public void handleClientRequest(User user, ISFSObject objIn) {
        
        
        // attempt to send data to clients. 
        try {
            sendData(user, objIn.getDouble("inputRotY"), 
                    objIn.getDouble("inputRotX"),  
                    objIn.getBool("inputForward"));
        } catch (Exception e) {
            trace("\nInvalid data received.. ignore sending it further.");
        }
    }
    
    private void sendData(User fromUser, double _rotY, double _rotX,
           boolean _forward){
        
        trace("Got variables and sending data from pilot: \n(" + _rotX + ", " + _rotY + ")");
        
        //create the outobject
        ISFSObject output = new SFSObject();
        
        //include the input logic
        output.putDouble("sgctRotX", _rotX);
        output.putDouble("sgctRotY", _rotY);
        output.putBool("sgctForward", _forward);
        
        //get current room
        Room currentRoom = RoomHelper.getCurrentRoom(this);
        
        //check that there is a room
        /*if (currentRoom != null)
            trace("Room is: " + currentRoom.getName());
        else 
            trace("Room is NULL!");*/
        
        //get user list
        List<User> userList = UserHelper.getRecipientsList(currentRoom);
        
        //send data to clients
        this.send("PilotEvent", output, userList, false); //replace userList with SGCTclient
    }
}
