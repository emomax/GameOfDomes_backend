package myExtPackage;

import DomeUtils.UserHelper;
import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.BaseServerEventHandler;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Max
 */
public class DisconnectedPlayerHandler extends BaseServerEventHandler {
    
    @Override
    public void handleServerEvent(ISFSEvent event) {
        User user = (User) event.getParameter(SFSEventParam.USER);
        
        //trace("Disconnect: '" + user.getName() + "':" + user.getIpAddress());
        
        trace (UserHelper.freePlayerFromRole(user.getName()));    
        
        ISFSObject output = new SFSObject();

        output.putBool("PilotTaken", MainExtension.pilotSelected);
        output.putBool("GunnerTaken", MainExtension.gunnerSelected);
        output.putBool("EngineerTaken", MainExtension.engineerSelected);

        //get user list
        List<User> userList = new ArrayList(this.getParentExtension().getParentZone().getUserList());//UserHelper.getRecipientsList(currentRoom);

        //send data to clients
        this.send("RoleUpdate", output, userList, false);
    }
}
