
package myExtPackage;

import DomeUtils.UserHelper;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Max
 */
public class UnChooseClassHandler extends BaseClientRequestHandler{
    
    @Override
    public void handleClientRequest(User user, ISFSObject objIn) {
        
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
