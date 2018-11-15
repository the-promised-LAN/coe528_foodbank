/*
 * This work is open under no license.
 */
package foodBankApplication;

/**
 * <b>OVERVIEW:</b> An abstract representation of a Person. Pivotal in 
 *                  Factory Method pattern. Mutable
 * 
 * <b>Abstraction Function:</b>
 *              A AbstractPerson is represented by  name,pass, food array and a ServiceTicket.
 *              Example: 
 *              AbstractPerson item: Luis, Krill,{Food1,Food2,Food3},ServiceTicket: SmallOrder
 * 
 * <b>Representation Invariant</b>:
 *             c.name is a String &&
 *             c.pass is a String &&
 *             c.allFood is an array of Food objects
 *             c.ticket is a ServiceTicket
 *      
 * @author thepromisedLAN, luiszugasti, yashbhatty
 */
public abstract class AbstractPerson {
    
    private String name;
    private String pass;
    private Food[] allFood;
    private ServiceTicket ticket;
    
    /**
     * <b>OVERVIEW:</b> The abstract representation of a class' mainMenu. 
     *                  All classes have a submethod that overrides this method.
     * 
     * <b>EFFECTS:</b> Returns a Container object
     * @return Container
     */
    public abstract Container mainMenu();
    
    
    /**
     * <b>OVERVIEW:</b> Checks if any ServiceTickets were raised; if so,
     *                  displays the content of the ticket
     */
    public abstract void CheckEmergency();
    
    /**
     * <b>OVERVIEW:</b> Accessor for the 'name' instance
     * <b>EFFECTS:</b> Returns 'name'
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * <b>OVERVIEW:</b> Modifier for the 'name' instance
     * <b>EFFECTS:</b> Sets 'name' to the value provided
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * <b>OVERVIEW:</b> Accessor for the 'pass' instance
     * <b>EFFECTS:</b> Returns 'pass'
     * @return pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * <b>OVERVIEW:</b> Modifier for the 'pass' instance
     * <b>EFFECTS:</b> Sets 'pass' to the value provided
     * @param pass
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * <b>OVERVIEW:</b> Accessor for the 'allFood' instance
     * <b>EFFECTS:</b> Returns 'allFood', array of Food objects
     * @return allFood
     */
    public Food[] getAllFood() {
        return allFood;
    }

    /**
     * OVERVIEW:</b> Modifier for the 'allFood' instance
     * EFFECTS:</b> Sets 'allFood' to the value provided
     * @param food:Food[]
     */
    public void setAllFood(Food[] food) {
        this.allFood = food;
    }
    
    /**
     * <b>OVERVIEW:</b> Accessor for the 'ticket' instance
     * <b>EFFECTS:</b> Returns ServiceTicket
     * @return ticket
     */
    public ServiceTicket getTicket() {
        return ticket;
    }

    /**
     * OVERVIEW:</b> Modifier for the 'ticket' instance
     * EFFECTS:</b> Sets 'ticket' to the value provided
     * @param ticket
     */
    public void setTicket(ServiceTicket ticket) {
        this.ticket = ticket;
    }
    
     /**
     * <b>OVERVIEW:</b> Implements the rep invariants of AbstractPerson; provides
     *                  description of the object
     * <b>EFFECTS:</b> Returns object description in a String
     * @return String
     */
    @Override
    public String toString() {
        String s;
        s = "Name: " + name + ""
                + "Password: " + pass + ""
                + "Service ticket: " + ticket + ""
                + "\nFood: ";
        for (Food item: allFood){
            s += "\n" + item;
}
        return s;
    }
    public boolean repOK(){
        return (name instanceof String && pass instanceof String && allFood instanceof Food[] && ticket instanceof ServiceTicket);
       }
            
}
