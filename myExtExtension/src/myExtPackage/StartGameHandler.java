package myExtPackage;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;

public class StartGameHandler extends BaseClientRequestHandler {
    
    @Override
    public void handleClientRequest(User user, ISFSObject objIn) {
        
        if(MainExtension.pilotSelected == true)
        {
            //tell SGCT to start and clientsto update to role GUI
        }
        
    }
}
