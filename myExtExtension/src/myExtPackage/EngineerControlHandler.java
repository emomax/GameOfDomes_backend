package myExtPackage;

import DomeUtils.RoomHelper;
import DomeUtils.UserHelper;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
import java.util.List;

public class EngineerControlHandler extends BaseClientRequestHandler {

    @Override
    public void handleClientRequest(User user, ISFSObject objIn) {

        //send data to clients        
        sendData(user, 
                objIn.getInt("inputShield"), 
                objIn.getInt("inputTurret"), 
                objIn.getInt("inputEngine"));
    }
     
    private void sendData(User fromUser, int _shield, int _turret, int _engine){

        trace("Got variables and sending data from engineer");
        
        //create out object
        ISFSObject output = new SFSObject();

        //include variables
        output.putInt("sgctShield", _shield);
        output.putInt("sgctTurret", _turret);
        output.putInt("sgctEngine", _engine);
        
        trace("Power variables are (" + _shield + ", " + _turret + ", " + _engine + ")");

        //get current room
        Room currentRoom = RoomHelper.getCurrentRoom(this);

        //check that room isn't null
        if (currentRoom != null)
            trace("Room is: " + currentRoom.getName());
        else 
            trace("Room is NULL!");

        //get user list
        List<User> userList = UserHelper.getRecipientsList(currentRoom);

        //send data to  clients
        this.send("EngineerEvent", output, userList, false); //replace fromUser with SGCTclient
    }
}
