package foodBankApplication;

/**
 * OVERVIEW: UserFactory class is used for creating AbstractPerson objects
 *           based on the passed parameters
 *
 * @author the-promised-LAN
 */
public class UserFactory {
    
    /**
     * <b>OVERVIEW:</b> Creates an AbstractPerson object based on the parameters
     * 
     * <b>EFFECTS:</b> Returns an AbstractPerson object depending on the parameters
     * @param choice
     * @param name
     * @param pass
     * @param data
     * @return person:AbstractPerson 
     */
    public AbstractPerson createUser(int choice, String name, String pass, Container data) {
        switch(choice) {
            case 1:
                return new Customer(name, pass, data);
            case 2:
                return new FoodVolunteer(name, pass, data);
            case 3:
                return new DeliveryMan(name, pass, data);
            default:
                return null;               
        }
    }
    
    /**
     * <b>OVERVIEW:</b> Implements the rep invariants of UserFactory; provides
     *                  description of the object
     * <b>EFFECTS:</b> Returns object description in a String
     * @return String
     */   
    @Override
    public String toString() {
        return "UserFactory object";
}
    
    //No repOK() method due to no representation invariants!
}
