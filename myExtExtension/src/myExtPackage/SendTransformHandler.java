package myExtPackage;

import java.util.Date;
import java.util.List;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
import com.smartfoxserver.v2.entities.Room;

// Custom class imports
import DomeUtils.Transform;
import GameObjects.World;
import DomeUtils.RoomHelper;
import DomeUtils.UserHelper;
 
//! This handler makes sure that the position and orientation of the main ship is broadcasted properly.
public class SendTransformHandler extends BaseClientRequestHandler {
    
    //! The handler. Takes the sender (pilot) and the data object as inputs
    @Override
    public void handleClientRequest(User user, ISFSObject objIn) {
        
        // This is to be used once all is live
        //Transform receivedTransform = Transform.fromSFSObject(objIn);
        
        //ISFSObject transformData = data.getSFSObject("transform");
        
        // set the variables from the data object
        int _rotX = objIn.getInt("rotX");
        int _rotY = objIn.getInt("rotY");
        int _thrust = objIn.getInt("thrust");
        trace("\nGot transform! rotX="+_rotX+", rotY="+_rotY+", thrust="+_thrust);
        
        Transform transform = new Transform(0, 0, 0, _rotX * 0.02, _rotY * 0.02, 0);
        
        //long timeStamp = objIn.getLong("t");
        
        // create a new transform and return it
        Transform toSend = transform;
        
        World world = RoomHelper.getWorld(this);
        
        trace("\nWe got the world!");
        
        Transform resultTransform = world.movePlayer(user, transform);
        
        trace("\nWe moved the player!");
        
        if (resultTransform != null) {
            // Server accepted transform - send to all clients
            sendTransform(user, resultTransform);
        } else {
            sendRejectedTransform(user);
        }
    }
    
    //! Send the transform to all clients
    private void sendTransform(User fromUser, Transform resultTransform) {
        ISFSObject data = new SFSObject();
        
        // Add server timestamp to transform;
        Long time = new Date().getTime();
        resultTransform.setTimeStamp(time);
        
        resultTransform.toSFSObject(data);
        data.putInt("id", fromUser.getId());
        
        Room currentRoom = RoomHelper.getCurrentRoom(this);
        
        if (currentRoom != null)
            trace("Room is: " + currentRoom.getName());
        else 
            trace("Room is NULL!");
        
        List<User> userList = UserHelper.getRecipientsList(currentRoom);
        this.send("ShipTransform", data, userList, true); // Use UDP = true
    }
    
    //! Send rejected trnasform message to speicifed user
    private void sendRejectedTransform(User user) {
        ISFSObject data = new SFSObject(); 
        // TODO set the transform in the data.. 
        
        data.putInt("id", user.getId());
        this.send("notransform", data, user, true); // Use UDP = true
    }
}