/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.Car;
import model.Client;
import model.Rent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kaszon
 */
public class DBConnectionTest {
  
  public DBConnectionTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }

  @Test
  public void testGetAllClients() {
    System.out.println("getAllClients");
    ArrayList<Client> expResult = null;
    ArrayList<Client> result = DBConnection.getAllClients();
    assertEquals(expResult, result);
    fail("The test case is a prototype.");
  }

  @Test
  public void testGetAllCars() {
    System.out.println("getAllCars");
    ArrayList<Car> expResult = null;
    ArrayList<Car> result = DBConnection.getAllCars();
    assertEquals(expResult, result);
    fail("The test case is a prototype.");
  }

  @Test
  public void testAddNewCar() {
    System.out.println("addNewCar");
    Car c = null;
    Car expResult = null;
    Car result = DBConnection.addNewCar(c);
    assertEquals(expResult, result);
    fail("The test case is a prototype.");
  }

  @Test
  public void testModifyACar() {
    System.out.println("modifyACar");
    Car c = null;
    Car expResult = null;
    Car result = DBConnection.modifyACar(c);
    assertEquals(expResult, result);
    fail("The test c.");
  }

  /**
   * Test of addNewClient method, of class DBConnection.
   */
  @Test
  public void testAddNewClient() {
    System.out.println("addNewClient");
    String name = "";
    String address = "";
    String phone = "";
    DBConnection.addNewClient(name, address, phone);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of finishRent method, of class DBConnection.
   */
  @Test
  public void testFinishRent() {
    System.out.println("finishRent");
    String date = "";
    String licNumb = "";
    DBConnection.finishRent(date, licNumb);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of addNewRent method, of class DBConnection.
   */
  @Test
  public void testAddNewRent() {
    System.out.println("addNewRent");
    Rent r = null;
    DBConnection.addNewRent(r);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getMaxIdFromClient method, of class DBConnection.
   */
  @Test
  public void testGetMaxIdFromClient() {
    System.out.println("getMaxIdFromClient");
    int expResult = 0;
    int result = DBConnection.getMaxIdFromClient();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getMaxIdFromRent method, of class DBConnection.
   */
  @Test
  public void testGetMaxIdFromRent() {
    System.out.println("getMaxIdFromRent");
    int expResult = 0;
    int result = DBConnection.getMaxIdFromRent();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getAvaibleClients method, of class DBConnection.
   */
  @Test
  public void testGetAvaibleClients() {
    System.out.println("getAvaibleClients");
    ArrayList<Client> expResult = null;
    ArrayList<Client> result = DBConnection.getAvaibleClients();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getAvaibleCars method, of class DBConnection.
   */
  @Test
  public void testGetAvaibleCars() {
    System.out.println("getAvaibleCars");
    ArrayList<Car> expResult = null;
    ArrayList<Car> result = DBConnection.getAvaibleCars();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getAllRents method, of class DBConnection.
   */
  @Test
  public void testGetAllRents() {
    System.out.println("getAllRents");
    ArrayList<Rent> expResult = null;
    ArrayList<Rent> result = DBConnection.getAllRents();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getClientByID method, of class DBConnection.
   */
  @Test
  public void testGetClientByID() {
    System.out.println("getClientByID");
    int id = 0;
    Client expResult = null;
    Client result = DBConnection.getClientByID(id);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getCarByLicenceNumber method, of class DBConnection.
   */
  @Test
  public void testGetCarByLicenceNumber() {
    System.out.println("getCarByLicenceNumber");
    String licenceNumb = "";
    Car expResult = null;
    Car result = DBConnection.getCarByLicenceNumber(licenceNumb);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getRentByID method, of class DBConnection.
   */
  @Test
  public void testGetRentByID() {
    System.out.println("getRentByID");
    int id = 0;
    Rent expResult = null;
    Rent result = DBConnection.getRentByID(id);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
