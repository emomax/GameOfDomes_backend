package myExtPackage;


import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;

import DomeUtils.RoomHelper;

public class EngineerHandler extends BaseClientRequestHandler {
   
    @Override
    public void handleClientRequest(User user, ISFSObject objIn) {
        
        float _shieldVal = objIn.getFloat("shieldVal");
        float _turretVal = objIn.getFloat("turretVal");
        float _engineVal = objIn.getFloat("engineVal");
    }
    
}
