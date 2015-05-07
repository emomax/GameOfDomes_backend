package myExtPackage;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
import java.util.ArrayList;
import java.util.List;

public class EngineerControlHandler extends BaseClientRequestHandler {

    @Override
    public void handleClientRequest(User user, ISFSObject objIn) {

        //send data to clients        
        sendData(user, 
                objIn.getFloat("inputShield"), 
                objIn.getFloat("inputTurret"), 
                objIn.getFloat("inputEngine"));
    }
     
    private void sendData(User fromUser, float _shield, float _turret, float _engine){

        trace("Got variables and sending data from engineer");
        
        //create out object
        ISFSObject output = new SFSObject();

        //include variables
        output.putFloat("sgctShield", _shield);
        output.putFloat("sgctTurret", _turret);
        output.putFloat("sgctEngine", _engine);
        
        trace("Power variables are (" + _shield + ", " + _turret + ", " + _engine + ")");

        //get user list
        List<User> userList = new ArrayList(this.getParentExtension().getParentZone().getUserList());

        //send data to  clients
        this.send("EngineerEvent", output, userList, false); //replace fromUser with SGCTclient
    }
}
