/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.Car;
import model.Client;
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

  @Test
  public void testUjMethod() {
    System.out.println("ujMethod");
    DBConnection.ujMethod();
    fail("The test case is a prototype.");
  }
  
}
