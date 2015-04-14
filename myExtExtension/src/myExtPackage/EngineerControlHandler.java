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
         float _shieldVal = objIn.getFloat("shieldVal");
         float _turretVal = objIn.getFloat("turretVal");
         float _engineVal = objIn.getFloat("engineVal");
     }
     
      private void sendTransform(User fromUser, float _shield, float _turret, float _engine){
          
        ISFSObject output = new SFSObject();
        
        output.putFloat("sgctShield", _shield);
        output.putFloat("sgctTurret", _turret);
        output.putFloat("sgctEngine", _engine);
        
        Room currentRoom = RoomHelper.getCurrentRoom(this);
        
        if (currentRoom != null)
            trace("Room is: " + currentRoom.getName());
        else 
            trace("Room is NULL!");
        
        List<User> userList = UserHelper.getRecipientsList(currentRoom);
        
        this.send("EngineerEvent", output, userList); //replace fromUser with SGCTclient
        
      }

}
