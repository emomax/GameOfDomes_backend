/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myExtPackage;

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
public class StartGameHandler extends BaseClientRequestHandler{
    
    @Override
    public void handleClientRequest(User user, ISFSObject objIn) {
        
        trace("Request for start game called from: " + user.getName());
        
        ISFSObject output = new SFSObject();        
        output.putBool("GameStarted", true);

        //get user list
        List<User> userList = new ArrayList(this.getParentExtension().getParentZone().getUserList());
        
        // send data to clients
        this.send("GameEvent", output, userList, false);
        
    }
}
