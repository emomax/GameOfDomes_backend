package myExtPackage;

import DomeUtils.RoomHelper;
import DomeUtils.UserHelper;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
import java.util.List;

/**
 *
 * @author LasseB
 * @cleanup-lady MaxJ
 */
public class ChooseClassHandler extends BaseClientRequestHandler {
    
    @Override
    public void handleClientRequest(User user, ISFSObject objIn) {
        String role = objIn.getUtfString("selectedRole");
        
        trace(user.getName() + " connected and wanted to choose role: " + role);
        
        ISFSObject output = new SFSObject();
        
        // If the role is not taken, the 'confirmed' parameter
        // of the SFSObject will be set to true. Else false.
        boolean roleAvailable = MainExtension.setRole(role.toUpperCase());
        
        // populate packet with info
        output.putBool("confirmed", roleAvailable);
        // Send the response to the user
        this.send("RoleConfirmation", output, user);
        
        // if the role was available, chain the user to the appropriate role.
        if (roleAvailable) {
            UserHelper.lockPlayerToRole(role, user);
             
            // Notify all users.
            sendRoleUpdate();
            trace("Role was available. Setting " + user.getName() + " as " + role);
        } 
        else {
            trace("Role " + role + " was already taken! Informing "+ user.getName() + " of this.");
            
        }
    }
    
    private void sendRoleUpdate() {
            Room currentRoom = RoomHelper.getCurrentRoom(this);

            ISFSObject output = new SFSObject();
            
            output.putBool("PilotTaken", MainExtension.pilotSelected);
            output.putBool("GunnerTaken", MainExtension.gunnerSelected);
            output.putBool("EngineerTaken", MainExtension.engineerSelected);
            
            //get user list
            List<User> userList = UserHelper.getRecipientsList(currentRoom);

            //send data to clients
            this.send("RoleUpdate", output, userList, false); //replace userList with SGCTclient
    }
}
