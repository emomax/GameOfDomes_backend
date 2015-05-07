package DomeUtils;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
import com.smartfoxserver.v2.extensions.SFSExtension;

//! OBSERVE: THIS IS A DEPRECATED CLASS. Used for simpler handling of rooms.
public class RoomHelper {
    
    //! Returns current room from given client
    public static Room getCurrentRoom(BaseClientRequestHandler handler) {
        return handler.getParentExtension().getParentRoom();
    }
    
    //! Returns current room from given extension
    public static Room getCurrentRoom(SFSExtension extension) {
        return extension.getParentRoom();
    }
}
