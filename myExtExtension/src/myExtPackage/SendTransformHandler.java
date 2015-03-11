package myExtPackage;

import java.util.ArrayList;
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
 
//! This handler makes sure that the position and orientation of the main ship is broadcasted properly.
public class SendTransformHandler extends BaseClientRequestHandler {
    
    //! The handler. Takes the sender (pilot) and the data object as inputs
    @Override
    public void handleClientRequest(User user, ISFSObject objIn) {
        
        Transform receivedTransform = Transform.fromSFSObject(objIn);
        
        World world = RoomHelper.getWorld(this);
        
        Transform resultTransform = world.movePlayer(user, receivedTransform);
        
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
        
        Room currentRoom;
        List<User> userList = new ArrayList<User>();
        this.send("transform", data, userList, true); // Use UDP = true
    }
    
    //! Send rejected trnasform message to speicifed user
    private void sendRejectedTransform(User user) {
        ISFSObject data = new SFSObject(); 
        // TODO set the transform in the data.. 
        
        data.putInt("id", user.getId());
        this.send("notransform", data, user, true); // Use UDP = true
    }
}