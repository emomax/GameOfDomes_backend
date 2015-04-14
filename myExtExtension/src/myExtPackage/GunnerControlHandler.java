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
        
        boolean inputUp = objIn.getBool("inputUp");
        boolean inputDown = objIn.getBool("inputDown");
        boolean inputLeft = objIn.getBool("inputLeft");
        boolean inputRight = objIn.getBool("inputRight");
        
        boolean isFiring = objIn.getBool("isFiring");
    }
    
    private void sendTransform(User fromUser, boolean _up, boolean _down,
            boolean _left, boolean _right, boolean _isFiring){
        ISFSObject output = new SFSObject();
        
        output.putBool("sgctUp", _up);
        output.putBool("sgctDown", _down);
        output.putBool("sgctLeft", _left);
        output.putBool("sgctRight", _right);
        output.putBool("sgctFire", _isFiring);
        
        
        Room currentRoom = RoomHelper.getCurrentRoom(this);
        
        if (currentRoom != null)
            trace("Room is: " + currentRoom.getName());
        else 
            trace("Room is NULL!");
        
        List<User> userList = UserHelper.getRecipientsList(currentRoom);
        
        this.send("GunnerEvent", output, userList); //replace fromUser with SGCTclient
        
    }
    
}
