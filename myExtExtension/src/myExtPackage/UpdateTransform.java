package myExtPackage;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
 
public class UpdateTransform extends BaseClientRequestHandler{
    
    @Override
    public void handleClientRequest(User user, ISFSObject objIn)
    {
        int a = objIn.getInt("a");
        
        ISFSObject objOut = new SFSObject();
        objOut.putInt("a", a*a);
        send("TransformShip", objOut, user);
        
        /*
        //check input
        boolean inputUp = objIn.getBool("InputUp");
        boolean inputDown = objIn.getBool("InputDown");
        boolean inputLeft = objIn.getBool("InputLeft");
        boolean inputRight = objIn.getBool("InputRight");
        
        //add tu the current position
        if(inputUp)
            MainExtension.zPosition += 1;
        if(inputDown)
            MainExtension.zPosition -= 1;
        if(inputLeft)
            MainExtension.xPosition -= 1;
        if(inputRight)
            MainExtension.xPosition += 1;
            
        
        ISFSObject objOut = new SFSObject();
        
        objOut.putFloat("xPos", MainExtension.xPosition);
        objOut.putFloat("yPos", MainExtension.yPosition);
        objOut.putFloat("zPos", MainExtension.zPosition);
        
        send("TransformShip", objOut, user);*/
    }
}