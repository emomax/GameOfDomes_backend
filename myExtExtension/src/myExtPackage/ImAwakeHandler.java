package myExtPackage;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;

/**
 *
 * @author Max
 * This is merely a void function used by the domehandler
 * to verify that it's still alive.
 * 
 */ 
public class ImAwakeHandler  extends BaseClientRequestHandler {
    
      @Override
    public void handleClientRequest(User user, ISFSObject objIn) {
        trace(user.getIpAddress()+ ":" + user.getName() + " sent an awake request.");
    }
    
}
