/*
 * This work is open under no license.
 */
package foodBankApplication;

/**
 * <b>OVERVIEW: </b> Container object contains the Food[] and ServiceTicket
 *              instances, and is used as a unified data transport method
 *              between classes. Mutable
 *
 * @author the-promised-LAN
 */
public class Container {
    
    private Food[] food;
    private ServiceTicket ticket;
    
    /**
     * <b>OVERVIEW:</b> Container constructor
     * 
     * <b>EFFECTS:</b> Sets the 'food' and 'ticket' instances to the ones
     *             passed in the parameters
     * @param food
     * @param ticket 
     */
    public Container(Food[] food, ServiceTicket ticket) {
        setFood(food);
        setTicket(ticket);
    }

    /**
     * <b>OVERVIEW:</b> Accessor for the 'food' instance
     * <b>EFFECTS:</b> Returns 'food':Food[]
     * @return food
     */
    public Food[] getFood() {
        return food;
    }

    /**
     * <b>OVERVIEW:</b> Modifier for the 'food' instance
     * <b>EFFECTS:</b> Sets 'food' to the value provided
     * @param food
     */
    public void setFood(Food[] food) {
        this.food = food;
    }

    /**
     * <b>OVERVIEW:</b> Accessor for the 'ticket' instance
     * <b>EFFECTS:</b> Returns 'ticket':ServiceTicket
     * @return ticket
     */
    public ServiceTicket getTicket() {
        return ticket;
    }

    /**
     * <b>OVERVIEW:</b> Modifier for the 'ticket' instance
     * <b>EFFECTS:</b> Sets 'ticket' to the value provided
     * @param ticket
     */
    public void setTicket(ServiceTicket ticket) {
        this.ticket = ticket;
    }
}
