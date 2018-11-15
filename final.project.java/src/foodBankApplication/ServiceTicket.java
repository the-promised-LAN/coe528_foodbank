/*
 * This work is open under no license.
 */
package foodBankApplication;

/**
 * <b>OVERVIEW</b>: Abstract representation of a ServiceTicket. Mutable.
 * 
 * @author thepromisedLAN, luiszugasti, yashbhatty
 */
public abstract class ServiceTicket {
    
    public abstract void FlagMessage();
    
    public abstract String toString();
    
    //no repOK() method due to no representation invariants!
}
