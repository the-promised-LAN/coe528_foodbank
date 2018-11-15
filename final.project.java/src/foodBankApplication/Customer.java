/*
 * This work is open under no license.
 */
package foodBankApplication;

import java.util.Scanner;

/**
 * <b>OVERVIEW:</b> Implementation of AbstractPerson for a Customer user.
 *
 * @author thepromisedLAN, luiszugasti, yashbhatty
 */
public class Customer extends AbstractPerson {
    
    private int points;
    
    /**
     * <b>OVERVIEW:</b> Constructor of a Customer object
     * 
     * <b>EFFECTS:</b> Sets the instance variables according to the passed
     *             parameters and Container
     * @param name
     * @param pass
     * @param data 
     */
    public Customer(String name, String pass, Container data) {
        setName(name);
        setPass(pass);
        setAllFood(data.getFood());
        setTicket(data.getTicket());
        initPoints();
    }
    
    /**
     * <b>OVERVIEW:</b> Provides main menu for the Customer;
     * 
     * <b>MODIFIES:</b> food:Food[], points:int
     * <b>EFFECTS:</b> At the end of execution, packages 'food' and 'ticket'
     *             into a Container to return
     * @return Container
     */
    @Override
    public Container mainMenu() {
        Scanner input = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nPress 1 to choose from available food."
                            + "\nPress 2 to view current service alerts."
                            + "\nPress 3 to sign out. ");
            
            try {
                choice = Integer.parseInt(input.nextLine());               
            } catch (Exception ex) {
                System.out.println("Entry should be a numeric value. Try again.");
                choice = 5;
            }
            
            switch(choice) {
                case 1:
                    getFood(); 
                    break;
                case 2:
                    CheckEmergency();  
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        } while (choice != 3);
        return new Container(getAllFood(),getTicket());
    }
    
    /**
     * <b>OVERVIEW:</b> Checks if the ServiceTicket was raised
     * 
     * <b>EFFECTS:</b> Checks if a ServiceTicket was raised, and if true,
     *              prints the ticket content, otherwise informs the user
     *              that there are no service alerts
     */
    @Override
    public void CheckEmergency() {
        if (getTicket() == null) {
            System.out.println("\nThere are no alerts at this time!");
        } else {
            getTicket().FlagMessage();
        }
    }
    
    /**
     * <b>OVERVIEW:</b> Checks if the Food[] has been organized by the volunteers
     * 
     * <b>EFFECTS:</b> Returns 'true' if the Food array has been sorted as well
     *             as displays the contents; otherwise, returns 'false' and
     *             displays a message
     * @return boolean
     */
    private boolean CheckAvailableFood() {
        for (Food item: getAllFood()) {
            if (item != null && item.getPointValue() == 0) {
                System.out.println("\nFood has not been organized yet!"
                        + "Please let the volunteers organize it first!");
                return false;
            }
        }
        printFoodArray(getAllFood());
        return true;
    }
    
    /**
     * <b>OVERVIEW:</b> Selects and gives the Customer available Food items
     *              within their allocated amount of points
     * 
     * <b>EFFECTS:</b> Marks the selected Food items by the Customer for deletion;
     *                  and decrements Customer's points accordingly; 
     */
    private void getFood() {
        Food[] taken = new Food[getAllFood().length-1];//dont foret
        Scanner input = new Scanner(System.in);
        String choice;
        int i = 0;
        
        if (CheckAvailableFood()) {
            for (Food item: getAllFood()) {
                System.out.println("\n" + item);
                System.out.print("Would you like this item? (y/n)");
                choice = input.nextLine();
                if (choice.equalsIgnoreCase("y")) {
                    if (getPoints() >= item.getPointValue()) {
                        setPoints(getPoints()-item.getPointValue());
                        
                        taken[i] = new Food(item.getName(),item.getCountryOfOrigin());  //Deep copy
                        taken[i].setIsRefrigerated(item.isIsRefrigerated());
                        taken[i].setPointValue(item.getPointValue());
                        
                        System.out.println("Item saved!");
                        i++;
                        item.setName(null);
                    
                    } else {
                        System.out.println("\nSorry, you do not have enough points "
                                + "left to get this item");
                    }
                }
            } 
        } else {
            return;
        }
        
        System.out.println("You have checked out the following items in your basket");
        System.out.println("(" + getPoints() + " points left):");
        printFoodArray(taken);

        printFoodArray(getAllFood());
        setAllFood(reorganizeArray(getAllFood()));
        
        System.out.println("New allFood");
        for (Food item: getAllFood()) {
            System.out.println(item);
        }
    }
    
    /**
     * <b>OVERVIEW: </b>Reorganizes the Food[] to contain only valid entries
     * <b>MODIFIES: food:Food[]</b>
     * <b>EFFECTS: Returns new Food[] after removing any Food items with
     *             'name' attribute set to 'null', thus, leaving only un-taken
     *              and valid Food items.
     * @param array
     * @return food:Food[]
     */
    private Food[] reorganizeArray(Food[] array) {
        int num = 0, j = 0;
        int[] place = new int[array.length];    
        Food item;
        
        for (int i: place) {
            i = -1;
        }
        
        for (int i = 0; i < array.length; i++) {
            item = array[i];
            if (item.getName() == null) {
                num++;
            } else {
                place[j] = i;
                j++;
            }
        }
        
        Food[] orgArray = new Food[array.length - num];
        j = 0;        
        
        for (int index: place) {
            if (index > 0) {
                //deep copy
                orgArray[j] = new Food(array[index].getName(),array[index].getCountryOfOrigin());
                orgArray[j].setIsRefrigerated(array[index].isIsRefrigerated());
                orgArray[j].setPointValue(array[index].getPointValue());
                
                j++;
            }
        }
        return orgArray;
    }
    
    /**
     * <b>OVERVIEW:</b> Prints the contents of the Food[] object
     * 
     * <b>EFFECTS:</b> Checks if Food item is not 'null'; if successful prints
     *             prints the contents until all Food objects have been checked
     * @param array 
     */
    private void printFoodArray(Food[] array) {
        int i = 0;
        System.out.println("");
        for (Food item: array) {
            if (item != null) {
                System.out.print("Item #" + (i+1) + ": " );
                System.out.println(item);
                i++;
            } 
        }
    }
    
    /**
     * <b>OVERVIEW:</b> Gives the Customer the initial number of points
     * <b>EFFECTS:</b> Sets the 'points' instance to 10
     */
    private void initPoints() {
        setPoints(10);
    }
    
    /**
     * <b>OVERVIEW:</b> Accessor for the 'points' instance
     * <b>EFFECTS:</b> Returns int
     * @return points
     */
    private int getPoints() {
        return points;
    }
    
    /**
     * OVERVIEW:</b> Modifier for the 'points' instance
     * EFFECTS:</b> Sets 'points' to the value provided
     * @param points:int
     */   
    private void setPoints(int points) {
        this.points = points;
    }
}
