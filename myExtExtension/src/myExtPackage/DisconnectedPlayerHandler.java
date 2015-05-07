package myExtPackage;

import DomeUtils.UserHelper;
import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.extensions.BaseServerEventHandler;

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
        
    }
}
