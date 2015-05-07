package myExtPackage;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;

/**
 *
 * @author Max
 */
public class BenchmarkingHandler  extends BaseClientRequestHandler{
        
    @Override
    public void handleClientRequest(User user, ISFSObject objIn) {
        
        
        // attempt to send data to clients. 
        try {
            sendData(user, objIn.getDouble("1"), 
                    objIn.getDouble("2"),  
                    objIn.getDouble("3"), 
                    objIn.getDouble("4"));
        } catch (Exception e) {
            trace("\nInvalid data received.. ignore sending it further.");
        }
    }
    
     private void sendData(User fromUser, double _1, double _2,
           double _3, double _4){
        
        //create the outobject
        ISFSObject output = new SFSObject();
        
        //include the 8*4=32 bytes of data
        output.putDouble("1", _1);
        output.putDouble("2", _2);
        output.putDouble("3", _3);
        output.putDouble("4", _4);
        
        //
        this.send("BenchMarking", output, fromUser);
        //this.send("PilotEvent", output, userList, false); //replace userList with SGCTclient
    }
}
