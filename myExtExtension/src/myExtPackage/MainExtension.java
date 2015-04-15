package myExtPackage;

// SFS-items
import com.smartfoxserver.v2.extensions.SFSExtension;

// Custom classes
import GameObjects.World;

//! MainExtension.java handles broadcasting of ingame logics.
public class MainExtension extends SFSExtension {

    private World world;
    
    public static boolean pilotSelected = false;
    public static boolean gunnerSelected = false;
    public static boolean engineerSelected = false;
    
    //! The init function adds the requesthandlers for our different broadcasted items.
    @Override
    public void init() {
        // Used for handling all objects and their functions.
        world = new World(this);
        
      //  addRequestHandler("RequestTransform", SendTransformHandler.class);
      //  addRequestHandler("Fire", FireHandler.class);
        
        //add all request handlers
        addRequestHandler("PilotControlEvent"   , PilotControlHandler.class);
        addRequestHandler("GunnerControlEvent"  , GunnerControlHandler.class);
        addRequestHandler("EngineerControlEvent", EngineerControlHandler.class);
        addRequestHandler("ChooseClassHandler"  , ChooseClassHandler.class);
        addRequestHandler("StartGameEvent"      , StartGameHandler.class);
        
    }
    
    public World getWorld() { return world; }
}