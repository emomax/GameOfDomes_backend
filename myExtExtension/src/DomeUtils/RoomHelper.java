package DomeUtils;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
import com.smartfoxserver.v2.extensions.BaseServerEventHandler;
import com.smartfoxserver.v2.extensions.SFSExtension;

// Custom class imports
import myExtPackage.MainExtension;
import GameObjects.World;

//! Used for simpler handling of rooms.
public class RoomHelper {
    
    //! Returns current room from given client
    public static Room getCurrentRoom(BaseClientRequestHandler handler) {
        return handler.getParentExtension().getParentRoom();
    }
    
    //! Returns current room from given extension
    public static Room getCurrentRoom(SFSExtension extension) {
        return extension.getParentRoom();
    }
    
    //! Returns a reference to the world from given client
    public static World getWorld(BaseClientRequestHandler handler) {
        MainExtension ext = (MainExtension) handler.getParentExtension();
        return ext.getWorld();
    }
    
    //! Returns a refernece to the world from given extension
    public static World getWorld(BaseServerEventHandler handler) {
        MainExtension ext = (MainExtension) handler.getParentExtension();
        return ext.getWorld();
    }
}
