package myExtPackage;

import DomeUtils.RoomHelper;
import DomeUtils.UserHelper;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
import java.util.List;

public class StartGameHandler extends BaseClientRequestHandler {
    
    @Override
    public void handleClientRequest(User user, ISFSObject objIn) {
        
        //if all roles are selected...
        if(MainExtension.pilotSelected == true 
                && MainExtension.gunnerSelected == true 
                && MainExtension.engineerSelected == true) {
            
            //tell SGCT to start and clients to update to role GUI
            sendData();
        }
    }
    
    private void sendData(){
        
        trace("starting game");
        
        //create the out object
        ISFSObject output = new SFSObject();
        
        //add a variable which tells clients game should start
        output.putBool("VerifyStart", true);
        
        //get current room
        Room currentRoom = RoomHelper.getCurrentRoom(this);
        
        //heck that there is a room
        if (currentRoom != null)
            trace("Room is: " + currentRoom.getName());
        else 
            trace("Room is NULL!");
        
        //get all users
        List<User> userList = UserHelper.getRecipientsList(currentRoom);
        
        //send info to clients
        this.send("PilotEvent", output, userList, false); //replace userList with SGCTclient
    }
}