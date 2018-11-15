/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodBankApplication;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yashbhatty, the-promised-LAN, luiszugasti
 */
public class CasesTest {

    /**
     *
     */
    @Test
    public void testfoodConstructor(){
        String name="Pasta";
        String countryoforigin="Canada";
        Food f1= new Food(name,countryoforigin);
        assertEquals("Pasta",f1.getName());
        assertEquals("Canada",f1.getCountryOfOrigin());
    }

    /**
     *
     */
    @Test
    public void testContainerConstructor(){
        Food f1= new Food("Pasta","Canada");
        Food f2= new Food("Rice","China");
        Food f3= new Food("Soup","France");
        Food [] ff = {f1,f2,f3};
        ServiceTicket order = new SmallOrder();
        Container c = new Container(ff,order);
        Assert.assertArrayEquals(ff,c.getFood());
        assertEquals(order,c.getTicket());       
    }
    
    /**
     *
     */
    @Test
    public void testCustomerConstructor(){
        String name = "Luis";
        String pass = "Krill";
        Food f1= new Food("Pasta","Canada");
        Food f2= new Food("Rice","China");
        Food f3= new Food("Soup","France");
        Food [] ff = {f1,f2,f3};
        ServiceTicket order = new SmallOrder();
        Container c = new Container(ff,order);
        Customer cc = new Customer(name,pass,c);
        assertEquals("Luis",cc.getName());
        assertEquals("Krill",cc.getPass());
        Assert.assertArrayEquals(ff,cc.getAllFood());
        assertEquals(order,cc.getTicket());
    }
    
    /**
     *
     */
    @Test
    public void testDeliveryManConstructor(){
        String name = "Luis";
        String pass = "Krill";
        Food f1= new Food("Pasta","Canada");
        Food f2= new Food("Rice","China");
        Food f3= new Food("Soup","France");
        Food [] ff = {f1,f2,f3};
        ServiceTicket order = new SmallOrder();
        Container c = new Container(ff,order);
        DeliveryMan d = new DeliveryMan(name,pass,c);
        assertEquals("Luis",d.getName());
        assertEquals("Krill",d.getPass());
        Assert.assertArrayEquals(ff,d.getAllFood());
        assertEquals(order,d.getTicket());
    }
    
    /**
     *
     */
    @Test
    public void testFoodVolunteerConstructor(){
        String name = "Luis";
        String pass = "Krill";
        Food f1= new Food("Pasta","Canada");
        Food f2= new Food("Rice","China");
        Food f3= new Food("Soup","France");
        Food [] ff = {f1,f2,f3};
        ServiceTicket order = new SmallOrder();
        Container c = new Container(ff,order);
        FoodVolunteer v = new FoodVolunteer(name,pass,c);
        assertEquals("Luis",v.getName());
        assertEquals("Krill",v.getPass());
        Assert.assertArrayEquals(ff,v.getAllFood());
        assertEquals(order,v.getTicket());
    }
    
    /**
     *
     */
    @Test
    public void testsetFood(){
        Food f1= new Food("Pasta","Canada");
        Food f2= new Food("Rice","China");
        Food f3= new Food("Soup","France");
        Food [] ff = {f1,f2,f3};
        Food [] fh = {f2,f1,f3};
        ServiceTicket order = new SmallOrder();
        Container c = new Container(ff,order);
        c.setFood(fh);
        Assert.assertArrayEquals(fh,c.getFood());
    }
    
    /**
     *
     */
    @Test
    public void testsetTicket(){
        Food f1= new Food("Pasta","Canada");
        Food f2= new Food("Rice","China");
        Food f3= new Food("Soup","France");
        Food [] ff = {f1,f2,f3};
        //Food [] fh = {f2,f1,f3};
        ServiceTicket s = new LeftKeysInCar();
        ServiceTicket ss = new VehicleAccident();
        Container c = new Container(ff,s);
        c.setTicket(ss);
        assertEquals(ss,c.getTicket());
    }
    
    /**
     *
     */
    @Test
    public void testsetName(){
        String name="Pasta";
        String name2="Spicy Pasta";
        String countryoforigin="Canada";
        Food f1= new Food(name,countryoforigin);
        f1.setName(name2);
        assertEquals(name2,f1.getName());
    }
    
    /**
     *
     */
    @Test
    public void testsetCountryOfOrigin(){
        String country = "Canada";
        String country2 = "India";
        String name="Pasta";
        Food f1 = new Food(name,country);
        f1.setCountryOfOrigin(country2);
        assertEquals(country2,f1.getCountryOfOrigin());
    }
    
    /**
     *
     */
    @Test
    public void testUserFactory(){
        String name = "Luis";
        String pass = "Krill";
        Food f1= new Food("Pasta","Canada");
        Food f2= new Food("Rice","China");
        Food f3= new Food("Soup","France");
        Food [] ff = {f1,f2,f3};
        ServiceTicket order = new SmallOrder();
        Container c = new Container(ff,order);
        Customer cc = new Customer(name,pass,c);
        UserFactory u = new UserFactory();
        Assert.assertArrayEquals(cc.getAllFood(),u.createUser(1, name, pass, c).getAllFood());
        assertEquals(cc.getName(),u.createUser(1, name, pass, c).getName());
        assertEquals(cc.getPass(),u.createUser(1, name, pass, c).getPass());
        assertEquals(cc.getTicket(),u.createUser(1, name, pass, c).getTicket());
    }
    
}