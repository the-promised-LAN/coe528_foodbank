/*
 * This work is open under no license.
 */
package foodBankApplication;

import java.util.Scanner;

/**
 * <b>OVERVIEW:</b> DeliveryMan is an implementation of AbstractPerson.
 * 
 * @author thepromisedLAN, luiszugasti, yashbhatty
 */
public class DeliveryMan extends AbstractPerson{
  
    /**
     * <b>OVERVIEW:</b> Creates a new DeliveryMan object, to be used 
     * in runtime of the program.
     * 
     * <b>EFFECTS:</b> Initializes all the instance variables and a FileManager
     * @param user
     * @param pass
     * @param data 
     */
    public DeliveryMan(String user, String pass, Container data) {
        setName(user);
        setPass(pass);
        setAllFood(data.getFood());
        setTicket(data.getTicket());
    }
    
     /**
     * <b>OVERVIEW:</b> Provides main menu for the DeliveryMan;
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
            System.out.println("\nPress 1 to initiate service alert."
                        + "\nPress 2 to initiate a delivery"
                        + "\nPress 3 to check current service ticket"
                        + "\nPress 4 to sign out.");
            
            try {
                choice = Integer.parseInt(input.nextLine());               
            } catch (Exception ex) {
                System.out.println("Entry should be a numeric value. Try again.");
                choice = 5;
            }
            
            switch(choice) {
                case 1:
                    CreateServiceTicket(); 
                    break;
                case 2:
                    DeliverFood();
                    break;
                case 3:
                    CheckEmergency();
                case 4:
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        } while (choice != 4);
        return new Container(getAllFood(),getTicket());
    }
    /**
     * OVERVIEW: Creates a new ServiceTicket
     * 
     * EFFECTS: Sets the 'ticket' instance to the newly created ServiceTicket
     *          object depending on user input
     */
    public void CreateServiceTicket(){
        //1 is small order 2 is left key in car 3 is vehicle accident 4 = no disruptions
        ServiceTicketFactory ticketFactory = new ServiceTicketFactory();
        Scanner input = new Scanner(System.in);
        int choice;
        System.out.println("\nSelect which type of ticket to raise:\n"
                + "1) Small order"
                + "\t2) Driver left key in the car"
                + "\t3) Vehicle accident"
                + "\t4) Cancel");
        choice = Integer.parseInt(input.nextLine());
        setTicket(ticketFactory.createServiceTicket(choice));
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
     * OVERVIEW: Creates new values and fills the Food[] according to user
     *           input
     * EFFECTS: Creates a new Food[] and fills it with Food objects, all 
     *          depending on the user input throughout the method
     */
    public void DeliverFood(){

        Scanner input = new Scanner(System.in);
        int choice;
        System.out.print("\nHow many distinct food items are in the delivery? (type '0' to exit): ");
        
        try {
            choice = input.nextInt();
            input.nextLine();
        } catch (Exception ex) {
            System.out.println("Number of food items must be a positive numeric value! Please try again!");
            return;
        }
        
        if (choice > 0) {
            setAllFood(new Food[choice]);
            initFood();
        } else {
            System.out.println("Cancelled!");
            return;
        }
  
        String fName,fOrigin;
        
        for (int i = 0; i < choice; i++) {
            System.out.print("Please enter the name of the food item: ");
            fName = input.nextLine();
            System.out.print("Please enter the country of origin for the food item: ");
            fOrigin = input.nextLine();
            
            if (fName.length() < 1) {
                System.out.println("Name of the food cannot be shorter that 1 character!"
                        + "Please try again!");
                return;
            } else if (fOrigin.length() < 1) {
                System.out.println("Country of origin cannot be shorter that 1 character!"
                        + "Please try again!");
                return;
            } else {
                getAllFood()[i].setName(fName);
                getAllFood()[i].setCountryOfOrigin(fOrigin);
            }
        }
    }
    
    /**
     * OVERVIEW: Initializes the Food[] properly.
     * 
     * EFFECTS: Populates the Food[] with empty Food objects
     */
    private void initFood() {
        for (int i = 0; i < getAllFood().length; i++) {
            getAllFood()[i] = new Food(null, null);
        }
    }
}
