package myExtPackage;
import com.smartfoxserver.v2.extensions.SFSExtension;
 
public class MainExtension extends SFSExtension{

    public static float xPosition = 0;
    public static float yPosition = 0;
    public static float zPosition = 0;
    
    @Override
    public void init()
    {
        addRequestHandler("TransformShip", UpdateTransform.class);
    }
}