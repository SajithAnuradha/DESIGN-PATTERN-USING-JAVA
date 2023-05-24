package FactoryMethod;

// This is a Generic/Abstract ABS Sensor module.
abstract class ABSSensor {
    public void install() {log("I’m a Generic ABS Sensor");}
    public void log(String s) { System.out.println(s); }
    }
    // This is a Concrete Car ABS Sensor module.
    // It overrides methods to specialize itself.
    class CarABSSensor extends ABSSensor {
    public void install() { log("I’m a Car ABS Sensor"); }
    }
    // This is a Concrete Truck ABS Sensor module.
    // It overrides methods to specialize itself.
    class TruckABSSensor extends ABSSensor {
    public void install() { log("I’m a Truck ABS Sensor"); }
    }
  
    // This is a Generic/Abstract ABS Controller module.
    // It will install an ABS Controller using generic ABS
    // sensor modules. This code at compile time is generic
    // and uses the Factory Method makeABSSensor().
    abstract class ABSController {
    private ABSSensor abss;
    // Factory Method
    public abstract ABSSensor makeABSSensor();
    public void log(String s) { System.out.println(s); }
    public void install() {
    log("Making an ABS Controller");
    abss = makeABSSensor();
    abss.install();
    }
    }
  
    // This Concrete Car ABS Controller module overrides
    // factory method, makeABSSensor() to specialize itself.
    class CarABSController extends ABSController {
    public ABSSensor makeABSSensor() {
    log("Making a Car ABS Sensor");
    return new CarABSSensor();
    }
    }
    // This Concrete Truck ABS Controller module overrides
    // factory method, makeABSSensor() to specialize itself.
    class TruckABSController extends ABSController {
    public ABSSensor makeABSSensor() {
    log("Making a Truck ABS Sensor");
    return new TruckABSSensor(); }
    }
   
    // This test code shows that the correct sensor is
    // installed in the specific controller. This decision on
    // which sensor to use is deferred to a subclass and
    // can be decided at runtime.
    // The ABSController class developer can work
    // independently.
    public class BenzABSController {
    public static void main(String [] args) {
    CarABSController carabsc = new CarABSController();
    carabsc.install();
    TruckABSController truckabsc = new TruckABSController();
    truckabsc.install();
    }
    }