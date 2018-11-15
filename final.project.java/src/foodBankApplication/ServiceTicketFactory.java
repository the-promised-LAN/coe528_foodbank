/*
 * This work is open under no license.
 */
package foodBankApplication;

/**
 * OVERVIEW: ServiceTicketFactory class is used for creating ServiceTicket
 *           objects based on the passed parameters
 *
 * @author the-promised-LAN
 */
public class ServiceTicketFactory {
    
    /**
     * <b>OVERVIEW:</b> Creates a ServiceTicket object based on the parameter
     * 
     * <b>EFFECTS:</b> Returns a ServiceTicket object depending on the 'choice'
     * @param choice
     * @return ServiceTicket
     */
    public ServiceTicket createServiceTicket(int choice) {
                //1 is small order 2 is left key in car 3 is vehicle accident 4 = no disruptions
        switch(choice) {
            case 1:
                return new SmallOrder();
            case 2:
                return new LeftKeysInCar();
            case 3:
                return new VehicleAccident();
            case 4:
            default:
                return null;
        }
    }
    
    public String toString() {
        return "ServiceTicketFactory object";
}

    //No repOK() method due to no representation invariants!
}
