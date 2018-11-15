/*
 * This work is open under no license.
 */
package foodBankApplication;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author thepromisedLAN, luiszugasti, yashbhatty
 */
public class FileManager {
    /*
     * OVERVIEW:
     * FileManager class is responsible for all IO with external
     * file of the program that contains the login information.
     * This class creates a new login information file, adds and
     * deletes new users, as well as authenticates users by
     * comparing the provided login and password information to
     * the ones present in the file. Saves and reads the data in 
     * in the following format:          UU LL RR
     *              where UU - username, LL - password, RR - role
     *
     *  <b>Immutable</b>
     * Note: This implementation is partly inspired by DataManger class 
     *       from Lab #5 of COE528. Used with permission.
    */
    
    private String fileName = "foodbank.txt";
    private String line = null;
    private String[] userInfo = null;
    
    /**
     * Constructor of a FileManager. Calls the start() method.
     */
    public FileManager() {
        start();
    }
    
    /**
     * Compares the username and password provided in the first two
     * elements of the provided String array with the entries in the
     * database file, and returns a boolean depending on the result.
     * 
     * @param info
     * @return String role
     * 
     * REQUIRES: String array with at least two items containing
     *           username and password in the format specified in
     *           the OVERVIEW clause
     * MODIFIES: - 
     * EFFECTS: Returns 'true' if username and password provided
     *          matches with the entry in the database file; returns
     *          'false' otherwise.
     * 
     */
    public String authenticate(String[] info) {
        String[] profile = null;
        if (info == null) {            
            System.err.println("The passed login information is empty or null!");
        }
        profile = getProfile(info[0]); 
        if (profile !=  null) {
            if (checkUsername(profile[0],info[0]) && checkPassword(profile[1], info[1])) {
                return profile[2];
            }  
        }  
        return "fail";
    }
    
    
    /**
     * @deprecated
     * Deletes a user profile entry from the file.
     * @param name 
     * REQUIRES: name is a single word String
     * MODIFIES: Database base file 'foodbank.txt'
     * EFFECTS: Deletes a user entry from the database file.
     */
    public void deleteProfile(String name) {
        String[] a = null;
        try {
            File tmp =  File.createTempFile("tmp",".tmp");
            BufferedWriter writer = new BufferedWriter(new FileWriter(tmp,true));
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            
            while ((line = reader.readLine()) != null) {
               a = line.split(" ",3);
               if (!a[0].equals(name)) {
                   writer.append(line);
                   writer.newLine();
               }
            }
            
            reader.close();
            writer.close();
            
            File oldFile = new File(fileName);
            if (oldFile.delete()) {
                tmp.renameTo(oldFile);
            }
        } catch(IOException ex) {
            System.err.println("Error writing to file" + fileName);
        }
    }
    
    /**
     * Creates a new entry in the database file for a new user.
     * @param info 
     * 
     * REQUIRES: String info should contain the username, password, and the
     *           role of the new user in the format described in the OVERVIEW
     *           clause
     * MODIFIES: Database file 'foodbank.txt'
     * EFFECTS: Creates a new entry in the database file for a new user.
     */
    public void addUser(String info) {
        try {
            BufferedWriter writer;
            writer = new BufferedWriter(new FileWriter(fileName,true));
           
            writer.append(info);
            writer.newLine();
            writer.close();
        } catch(IOException ex) {
            System.err.println("Error writing to file" + fileName);
        }
    }
    
   /**
     * Starts a new FileManager. Checks if the file exists and whether it is
     * empty. 
     * 
     * REQUIRES: -
     * MODIFIES: -
     * EFFECTS: Checks whether the file exists, if it's empty. If it is empty,
     *          adds two default entries for a volunteer and a delivery man.
     */
    private void start() {
        File f = new File(fileName);       
        if (!f.exists()) {
            newSession();
        } else if (f.length() == 0){
            addUser("john admin volunteer");
            addUser("joe password123 deliveryman");
        }
    }
    
    /**
     * If the login information file is non-existent, creates a new file
     * and adds 2 default users: a volunteer and deliveryman.
     * 
     * REQUIRES: -
     * MODIFIES: -
     * EFFECTS: Creates a new file "foodbank.txt" in the source directory with
     *          two default entries for a volunteer and a delivery man
     */
    private void newSession() {
        File file = new File(fileName);       
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(fileName));
            writer.write("john admin volunteer");
            writer.newLine();
            writer.write("joe password123 deliveryman");
            writer.newLine();
            writer.close();
        } catch(IOException ex) {
            System.err.println("Error writing to file" + fileName);
        }
    }
    
    /**
     * Searches the entries in the file for entries with a matching name
     * to the passed String parameter 'name'. If such entry exists, returns the
     * complete entry as a String array containing all information of that entry
     * @param name
     * @return String[] userData
     * 
     * REQUIRES: -
     * MODIFIES: -
     * EFFECTS: Checks if a user entry is present in the file, and returns the
     *          complete entry in a form of a String array with 3 items:
     *              name, password, role
     */
    private String[] getProfile(String name) {
        String[] data = null;
        try {
            FileReader file = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(file);
            
            while ((line = reader.readLine()) != null) {
               //System.out.println("\t\t" + line.substring(line.indexOf(' ')+1));  
               //System.out.println("\t\t" + line.substring(0,line.indexOf(' ')));
               if (line.substring(0,line.indexOf(' ')).equals(name)) {
                   data = line.split(" ",3);
                   //System.out.println("\t" + data[0] + data[1]);
               }
            }          
            reader.close();
        } catch(FileNotFoundException ex) {
            System.err.println("Cannot find file " + fileName);
        } catch (IOException ex) {
            System.err.println("Error reading file " + fileName);
        }
        return data;
    }
    
    /**
     * Compares whether two usernames are identical.
     * @param user
     * @param a
     * @return boolean
     * 
     * REQUIRES: String 'name' and String 'a'
     * MODIFIES: - 
     * EFFECTS: Returns 'true' if 'user' and 'a' match; 'false' otherwise
     */
    private boolean checkUsername(String user, String a) {
        //System.out.println("\t\t\t" + user + " " + user + " " + user.equals(a));
        if (a == null) {
            return false;
        }
        return user.equals(a);
    }
    
    /**
     * Compares whether two passwords are identical.
     * @param user
     * @param a
     * @return boolean
     * 
     * REQUIRES: String 'pass' and String 'a'
     * MODIFIES: - 
     * EFFECTS: Returns 'true' if 'pass' and 'a' match; 'false' otherwise
     */
    private boolean checkPassword(String pass, String a) {
        if (a == null) {
            return false;
        }
        return pass.equals(a);
    }

    /**
     * <b>OVERVIEW:</b> Implements the rep invariants of FileManager;
     *             provides description of the object
     * <b>EFFECTS:</b> Returns object description in a String
     * @return String
     */
    @Override
    public String toString() {
        ArrayList<String> users = new ArrayList<>();
        try {
            FileReader file = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(file);
            
            while ((line = reader.readLine()) != null) {
               //System.out.println(name);
               users.add(line.substring(' '));
            }
            
            reader.close();
        } catch(FileNotFoundException ex) {
            System.err.println("Cannot find file " + fileName);
        } catch (IOException ex) {
            System.err.println("Erro reading file " + fileName);
        }
        return users.toString();
    }
}
