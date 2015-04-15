package DomeUtils;

//a class that contains variables and methods for the engineer
public class Engineer {
    
    float shieldPower;
    float turretPower;
    float enginePower;
    
    //constructor for inserting all power values
    public Engineer(float _shieldPow, float _turretPow, float _enginePow) {
        this.shieldPower = _shieldPow;
        this.turretPower = _turretPow;
        this.enginePower = _enginePow;
    }
    
    // getters of everything
    public double getRotx() { return this.shieldPower; }
    public double getRoty() { return this.turretPower; }
    public double getRotz() { return this.enginePower; }
}
