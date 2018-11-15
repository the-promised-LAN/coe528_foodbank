/*
 * This work is open under no license.
 */
package foodBankApplication;
import java.util.Scanner;

/**
 * <b>OVERVIEW:</b> Class for generating a command-line, user interface.
 *                  Contains the program's Main() method. Mutable.
 * 
 * <b>Abstraction Function</b>:
 *             A main interface has a valid AbstractPerson User, valid 
 *             userFactory,
 *             and a valid Food array.
 *             All using the toString methods of each class.
 *             For example, represented as (IN THIS CASE, JOHN AND THE FOODARRAY
 *             ARE NOT THE SAME):
 *             Name: john
 *             Password: admin
 *             ServiceTicket: Left keys in the car ticket
 *             Food:
 *             Food item: bananas, Origin: Russia, Refrigerated: true, 
 *             Point value: 1
 *             UserFactory object
 *             Food array:
 *             Food item: bananas, Origin: Russia, Refrigerated: true, 
 *             Point value: 1
 *             Food item: tamales, Origin: Prussia, Refrigerated: false,
 *             Point value: 8    
 * 
 * <b>Rep Invariant</b>: 
 *      c.user is a AbstractPerson &&
 *      c.userFactory is a userFactory &&
 *      c.ticket is a ServiceTicket &&
 *      c.food is an array of food objects
 * 
 * @author thepromisedLAN, luiszugasti, yashbhatty
 */
public class MainInterface {
    
    private static AbstractPerson user;
    private static UserFactory userFactory;
    private static ServiceTicket ticket;
    private static Food[] food;
    
    /**
     * OVERVIEW: The LogInMethod allows users to authenticate against the 
     * saved information in the foodbank.txt file. If successful, the user can
     * sign in and use their account. If unsuccessful, the user is prompted
     * to try again. The user also has the choice to close the application.
     * 
     * REQUIRES: -
     * MODIFIES: UserInputString, logInInfo[], role.
     * EFFECTS: Allows entry into MainMenu. Else, closes the application.
     * @param UserInputString
     */
    public static void LogIn(Scanner UserInputScanner){
        String UserInputString = null;
        String logInInfo[] = null;
        String role = null;
        
        FileManager fileManager = new FileManager();
        
        do {
            System.out.println("\nWelcome to Food Center App\n"
                + "Please enter your username, and password.\n"
                + "(type 'quit' to exit)\n"
                + "Sample:\n"
                + "admin admin");
            UserInputString = UserInputScanner.nextLine();
            
            //Splits the incoming String into parseable pieces.
            //So now, the username is at array 0 and the password is at array 1.
            logInInfo = UserInputString.split(" ", 3);
            
            if (UserInputString.equals("quit")) {
                System.out.println("Quitting application");
                UserInputScanner.close();
                CloseProgram();
            } else if (logInInfo.length < 2) {
                role = "fail";
                //Adding a try... catch statement here, for file reading
            } else {
                role = fileManager.authenticate(logInInfo);
            }
            
            if (role.equals("fail")) {
                System.out.println("Invalid input."
                        + "Please try again.\n");
            } else {
                menu(role, logInInfo, UserInputScanner);
                role = "continue";
            }

        } while (role.equals("fail") || role.equals("continue"));
    }
    
    
    /**
     * OVERVIEW: The menu allows the creation of a user object and passes 
     * program control to the main of that specific user. Otherwise, it will
     * flag to the user that the wrong user type has been selected.
     * 
     * REQUIRES: -
     * MODIFIES: - User object.
     * EFFECTS: Sets a user to use.
     * 
     * @param role
     * @param logInInfo
     * @param input 
     */
    private static void menu(String role, String[] logInInfo, Scanner input) {
        setUserFactory(new UserFactory());

        switch(role) {
            case "customer":
                setUser(getUserFactory().createUser(1, logInInfo[0], logInInfo[1], new Container(getFood(), getTicket())));
                break;
            case "volunteer":
                setUser(getUserFactory().createUser(2, logInInfo[0], logInInfo[1], new Container(getFood(), getTicket())));
                break;
            case "deliveryman":
                setUser(getUserFactory().createUser(3, logInInfo[0], logInInfo[1], new Container(getFood(), getTicket())));
                break;
            default:
                setUser(null);
                break;
        }
        
        if (getUser() == null) {
            System.out.println("Wrong choice of role selected!");
            return;
        } else {
            System.out.println("\nHello, " + logInInfo[0] + ". Please choose"
                    + "\nwhat you would like to do:");
            Container data = getUser().mainMenu();
            setFood(data.getFood());
            setTicket(data.getTicket());           
        }
    }
  
    /**
     * OVERVIEW: A call to closing the program.
     * 
     * REQUIRES: -
     * MODIFIES: -
     * EFFECTS: Termination of program.
     * Closes the program
     */
    public static void CloseProgram(){
        System.exit(0);
    }
    
    /**
     * Main class of the FoodBank Application.
     * @param args 
     */
    public static void main(String[] args) {
        Scanner userInputScanner = new Scanner(System.in);
        LogIn(userInputScanner);
    }

    /**
     * Accessor for the AbstractPerson instance
     * @return the user
     */
    public static AbstractPerson getUser() {
        return user;
    }

    /**
     * Modifier for the AbstractPerson instance
     * @param aUser the user to set
     */
    public static void setUser(AbstractPerson aUser) {
        user = aUser;
    }

    /**
     * Accessor for the UserFactory instance
     * @return the userFactory
     */
    public static UserFactory getUserFactory() {
        return userFactory;
    }

    /**
     * Modifier for the UserFactory instance
     * @param aUserFactory the userFactory to set
     */
    public static void setUserFactory(UserFactory aUserFactory) {
        userFactory = aUserFactory;
    }

    /**
     * Accessor for the ServiceTicket instance
     * @return the ticket
     */
    public static ServiceTicket getTicket() {
        return ticket;
    }

    /**
     * Modifier for the ServiceTicket instance
     * @param aTicket the ticket to set
     */
    public static void setTicket(ServiceTicket aTicket) {
        ticket = aTicket;
    }

    /**
     * Accessor for the Food[] instance
     * @return the food
     */
    public static Food[] getFood() {
        return food;
    }

    /**
     * Modifier for the Food[] instance
     * @param aFood the food to set
     */
    public static void setFood(Food[] aFood) {
        food = aFood;
    }
    
    /**
     * <b>OVERVIEW:</b> Implements the rep invariants of MainInterface;
     *             provides description of the object
     * <b>EFFECTS:</b> Returns object description in a String
     * @return String
     */
    public String toString() {
        String s = "";
        s += "User: " + user + ""
                + "\nUserFactory: " + userFactory + ""
                + "\nFood array: ";
        for (Food item: food) {
            s += "\n" + item; 
}
        return s;
    }
    public boolean repOK(){
        return (user == (AbstractPerson)user && userFactory == (UserFactory)userFactory && ticket == (ServiceTicket)ticket && food == (Food[])food);
    }
}
