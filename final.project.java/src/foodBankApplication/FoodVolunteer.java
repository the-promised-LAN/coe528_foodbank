/*
 * This work is open under no license.
 */
package foodBankApplication;
import java.util.Scanner;

/**
 * <b>OVERVIEW:</b>
     * FoodVolunteer class is responsible for managing the foodbank. It has
     * three instance methods: RespondServiceTicket, OrganizeFood, and 
     * RecordPoints. In the body of the calling application, FoodVolunteer can
     * also add new food volunteers.
 * @author luiszugasti, yashbhatty, thepromiseLAN
    */
public class FoodVolunteer extends AbstractPerson {

    private FileManager fileManager;

    /**
     * <b>OVERVIEW:</b> Creates a new FoodVolunteer object, to be used 
     * in runtime of the program.
     * 
     * <b>EFFECTS:</b> Initializes all the instance variables and a FileManager
     * @param userName
     * @param password
     */
    public FoodVolunteer(String userName, String password, Container data) {
        setName(userName);
        setPass(password);
        setAllFood(data.getFood());
        setTicket(data.getTicket());
        fileManager = new FileManager();
    }
    
    /**
     * <b>OVERVIEW:</b> Provides main menu for the FoodVolunteer;
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
            System.out.println("\nPress 1 to organize food."
                        + "\nPress 2 to view current service alerts."
                        + "\nPress 3 to add a customer."
                        + "\nPress 4 to add a volunteer."
                        + "\nPress 5 to sign out:");
            try {
                choice = Integer.parseInt(input.nextLine());               
            } catch (Exception ex) {
                System.out.println("Entry should be a numeric value. Try again.");
                choice = 5;
            }
            
            switch(choice) {
                case 1:
                    setAllFood(OrganizeFood(getAllFood())); 
                    break;
                case 2:
                    CheckEmergency();
                    break;
                case 3:
                    CreateNewCustomer();
                    break;
                case 4:
                    CreateNewVolunteer();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("\nInvalid option. Please try again.");
                    break;
            }
        } while (choice != 5);
        return new Container(getAllFood(),getTicket());
    }
    
    /**
     * <b>OVERVIEW:</b> OrganizeFood accepts a food array sent by deliveryman.
     * With use of this array, FoodVolunteer is able to add FoodBank specific
     * properties, such as isRefrigerated and PointValue, one by one to each
     * item.
     * 
     * <b>REQUIRES:</b> A non-empty FoodArray[].
     * <b>MODIFIES:</b> The current FoodArray[] item. StringInput. 
     * <b>EFFECTS:</b> Returns the same FoodArray[] item, however, with modified
     * parameters as dictated by the Food object. Depending on user input, 
     * clobbers contents of StringInput.
     * @param FoodArray
     * @return FoodArray
     */
    public Food[] OrganizeFood(Food[] FoodArray){
        Scanner UserScanner = new Scanner(System.in);
        String UserInput;
        String values[] = new String[2];
        if (FoodArray != null) {
            for(Food iteration: FoodArray){
                System.out.print("\nEnter the following qualities for the following:\n"
                        + "\tfood item: " + iteration.getName() + " "
                        + "from " + iteration.getCountryOfOrigin() + "\n"
                        + "\tin the following format:\n"
                        + "isRefrigerated - true/false pointValue - 0/10 (e.g. true 2): ");
                
                UserInput = UserScanner.nextLine();
                values = UserInput.split(" ", 3);
                
                iteration.setIsRefrigerated(values[0].equals("true"));
                try {
                    iteration.setPointValue(Integer.parseInt(values[1]));
                } catch(Exception ex) {
                    System.out.println("Point value should be a numeric value between 0 and 10! Please try again!");
                    return FoodArray;
                }
                
                System.out.println("\nSuccessfully obtained food info.");
            }          
        } else {
            System.out.println("\nError: There is no food to sort!");
        }
        return FoodArray;
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
     * <b>OVERVIEW: </b>Creates a new Volunteer
     * <b>EFFECTS:</b> Creates a new entry for a Volunteer user in the database
     *              file
     */
    public void CreateNewVolunteer(){
        Scanner input = new Scanner(System.in);
        String info = "";
        String confirm = "";
        System.out.print("Type in the name for the new volunteer: ");
        info += input.nextLine() + " ";
        System.out.print("\nType in the password for the new password: ");
        info += input.nextLine() + " volunteer";
        System.out.print("Confirm creation of a new volunteer (username password role):"
                + " " + info + " : (y/n) ");
        confirm = input.nextLine();
        if (confirm.equalsIgnoreCase("y")) {
            fileManager.addUser(info);
        } else {
            System.out.println("\nCancelled!");
        }
    }
    
    /**
     * <b>OVERVIEW: </b>Creates a new Customer
     * <b>EFFECTS:</b> Creates a new entry for a Customer user in the database
     *              file
     */
    public void CreateNewCustomer(){
        Scanner input = new Scanner(System.in);
        String info = "";
        String confirm = "";
        System.out.print("Type in the name for the new customer: ");
        info += input.nextLine() + " ";
        System.out.print("\nType in the password for the new password: ");
        info += input.nextLine() + " customer";
        System.out.print("Confirm creation of a new customer (username password role):"
                + " " + info + " : (y/n) ");
        confirm = input.nextLine();
        if (confirm.equalsIgnoreCase("y")) {
            fileManager.addUser(info);
        } else {
            System.out.println("\nCancelled!");
        }
        System.out.println("");
    }
}
