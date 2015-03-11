package myExtPackage;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;

import DomeUtils.RoomHelper;

//! Used for handling the fire events pushed by gunman.
public class FireHandler extends BaseClientRequestHandler {
    
    @Override
    public void handleClientRequest(User user, ISFSObject data) {
        RoomHelper.getWorld(this).processShot(user);
    }
}
