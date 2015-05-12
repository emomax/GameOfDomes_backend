package myExtPackage;

// SFS-items
import com.smartfoxserver.v2.extensions.SFSExtension;

// Custom classes
import com.smartfoxserver.v2.core.SFSEventType;

//! MainExtension.java handles broadcasting of ingame logics.
public class MainExtension extends SFSExtension {
    
    public static boolean pilotSelected = false;
    public static boolean gunnerSelected = false;
    public static boolean engineerSelected = false;
 
    private enum Role {
        GUNNER,
        PILOT,
        ENGINEER,
        VIEWER // not used as of now
    }
    
    //! The init function adds the requesthandlers for our different broadcasted items.
    @Override
    public void init() {
        
        //add all request handlers
        addRequestHandler("PilotControlEvent"   , PilotControlHandler.class);
        addRequestHandler("GunnerControlEvent"  , GunnerControlHandler.class);
        addRequestHandler("EngineerControlEvent", EngineerControlHandler.class);
        addRequestHandler("ChooseClassHandler"  , ChooseClassHandler.class);
        addRequestHandler("StartGame"           , StartGameHandler.class);
        addRequestHandler("BenchMarking"        , BenchmarkingHandler.class);
        addRequestHandler("ImAwake"             , ImAwakeHandler.class);
        
        addEventHandler(SFSEventType.USER_DISCONNECT, DisconnectedPlayerHandler.class);
        addEventHandler(SFSEventType.USER_JOIN_ZONE, PlayerLoginHandler.class);
    }
    
    public static boolean setRole(String role) {
        
        Role _role = Role.valueOf(role);
        
        switch (_role) {
            case GUNNER:
                if (gunnerSelected)
                    return false;
                
                gunnerSelected = true;
                return true;
                
            case PILOT:
                if(pilotSelected)
                    return false;
                
                pilotSelected = true;
                return true;
                
            case ENGINEER:
                if (engineerSelected) 
                    return false;
                
                engineerSelected = true;
                return true;
             
            default:
                throw new RuntimeException("Role: " + role + " was not recognized by server.");
        }
    }
}