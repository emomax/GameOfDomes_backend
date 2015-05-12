/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myExtPackage;

import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.BaseServerEventHandler;
/**
 *
 * @author Max
 */
public class PlayerLoginHandler extends BaseServerEventHandler {
    
    @Override
    public void handleServerEvent(ISFSEvent event) {
        
        User user = (User) event.getParameter(SFSEventParam.USER);
        ISFSObject output = new SFSObject();
       
        trace("Sending role info to '" + user.getName() + "'");
        
        output.putBool("PilotTaken", MainExtension.pilotSelected);
        output.putBool("GunnerTaken", MainExtension.gunnerSelected);
        output.putBool("EngineerTaken", MainExtension.engineerSelected);

        //send data to client
        this.send("RoleUpdate", output, user);
    }
}
