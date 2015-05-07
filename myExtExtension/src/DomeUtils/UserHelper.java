package DomeUtils;

import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;

import java.util.List;
import myExtPackage.MainExtension;

//! Helper methods to easily get socket channel list to send response message to clients
public class UserHelper {
    
    private static User engineer;
    private static User pilot;
    private static User gunner;
    private static User viewer;

    //! Get all users except 'exceptUser' of given room
    public static List<User> getRecipientsList(Room room, User exceptUser) {
        List<User> users = room.getUserList();
        if (exceptUser != null) {
            users.remove(exceptUser);
        }
        
        return users;
    }

    //! DEPRECATED - Get all user of a given room
    public static List<User> getRecipientsList(Room currentRoom) {
            return getRecipientsList(currentRoom, null);
    }
    
    //! Chain a player to a specific role
    public static void lockPlayerToRole(String role, User user) {
        if (role.equals("Engineer"))
            engineer = user;
        
        if (role.equals("Pilot"))
            pilot = user;
        
        if (role.equals("Gunner"))
            gunner = user;
        
        if (role.equals("DomeHandler"))
            viewer = user;
        
    }
    
    public static String freePlayerFromRole(String name) {
        if (engineer != null) {
            if (engineer.getName().equals(name)) {
                engineer = null;
                MainExtension.engineerSelected = false;
                return (name + " released from engineer duty!");
            }
        }
        if (gunner != null) {
            if (gunner.getName().equals(name)) {
                gunner = null;
                MainExtension.gunnerSelected = false;
                return (name + " released from gunner duty!");
            }
        }
        if (pilot != null) {
            if (pilot.getName().equals(name)) {
                pilot = null;
                MainExtension.pilotSelected = false;
                return (name + " released from pilot duty!");
            }
        }
        
        return (name + " wasn't locked to any role.");
    } 
}
