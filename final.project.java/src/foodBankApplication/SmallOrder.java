/*
 * This work is open under no license.
 */
package foodBankApplication;

/**
 * <b>OVERVIEW:</b> An implementation of the ServiceTicket interface.
 * Here, the order the driver is bringing is very small (At the driver's 
 * judgment).
 *
 * @author thepromisedLAN, luiszugasti, yashbhatty
 */
public class SmallOrder extends ServiceTicket{

    /**
     * <b>OVERVIEW:</b> Prints out the message of the ServiceTicket
     */
    @Override
    public void FlagMessage() {
        System.out.println("\nService Alert: small delivery!");
    }
    
    public String toString() {
        return "Small delivery ticket";
}
}
