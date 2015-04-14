package myExtPackage;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;

/**
 *
 * @author LasseB
 */
public class ChooseClassHandler extends BaseClientRequestHandler {
    
    @Override
    public void handleClientRequest(User user, ISFSObject objIn) {
        String role = objIn.getUtfString("selectedRole");
        
        if(role == "Pilot"){
            MainExtension.pilotSelected = true;
        }
        else if(role == "Gunner"){
            MainExtension.gunnerSelected = true;
        }
        else if(role == "Engineer"){
            MainExtension.engineerSelected = true;
        }
        else
            trace("No valid role selected");
        
        
    }
    
    
}
