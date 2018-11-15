/*
 * This work is open under no license.
 */
package foodBankApplication;

/**
 * <b>OVERVIEW: </b>An implementation of the ServiceTicket interface.
 * Here, the driver has been unfortunate enough to find himself in a car crash.
 * 
 * @author thepromisedLAN, luiszugasti, yashbhatty
 */
public class VehicleAccident extends ServiceTicket{

    /**
     * <b>OVERVIEW:</b> Prints out the message of the ServiceTicket
     */
    @Override
    public void FlagMessage() {
        System.out.println("\nService Alert: delivery vehicle got into an accident!");
    }
    
    public String toString() {
        return "Vehicle accident ticket";
}
}
