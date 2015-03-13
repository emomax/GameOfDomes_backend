package GameObjects;

// utils
import DomeUtils.Transform;
import com.smartfoxserver.v2.entities.User;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

//! Custom class imports
import myExtPackage.MainExtension;

//! Class for handling everything in the game world
public class World {
    // Used for random spawning, timing, possible malfunctioning etc.
    private static Random rand = new Random();    
    
    // List of everything that should be handled by server.
    private List<GameObject> gameObjects = new ArrayList<GameObject>();
    
    // List of all interacting players
    private List<GameObject> players = new ArrayList<GameObject>();
    
    // Reference to the server extension
    private MainExtension extension;
    
    private GameObject motherShip;
    
    //! Used for moving given object
    public Transform movePlayer(User u, Transform t) {
        
        t = motherShip.move(t);        
        return t;        
    }
    
    //! Constructor for the world handler
    public World(MainExtension _extension) {
        // Set reference
        this.extension = _extension;
        // Set the seed to insure total randomness
        rand.setSeed((new Date()).getTime());
        // Init mothership
        motherShip = new GameObject();
    }
    
    public void processShot(User fromUser) {
        /*
        GameObject player = getPlayer(fromUser);
        
        if (player.isDead()) {
            return;
        } 
        if (!palyer.getWeapon().isReadyToFire()) {
            return;
        }*/
        
        System.out.println("Shot was fired!");
        
        //player.getWeapon().shoot();
        
    }
}
