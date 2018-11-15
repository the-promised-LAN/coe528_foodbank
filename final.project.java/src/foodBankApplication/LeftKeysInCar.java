/*
 * This work is open under no license.
 */
package foodBankApplication;

/**
 * <b>OVERVIEW:</b> An implementation of the ServiceTicket interface.
 * Here, the driver has locked himself out of the car (by leaving his keys 
 * inside).
 * @author thepromisedLAN, luiszugasti, yashbhatty
 */
public class LeftKeysInCar extends ServiceTicket {

    /**
     * <b>OVERVIEW:</b> Prints out the message of the ServiceTicket
     */
    @Override
    public void FlagMessage() {
        System.out.println("\nService Alert: Driver left his keys in the car!");
    }
    
    //Probably not required
    @Override
    public String toString() {
        return "Left keys in the car ticket";
}
}
