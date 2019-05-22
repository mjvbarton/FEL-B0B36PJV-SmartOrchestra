/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra.model.entities;

import cz.cvut.fel.dbs.smartorchestra.exceptions.WrongInputException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author Matěj Bartoň
 */
@RunWith(Parameterized.class)
public class EventsSettersTest {
    Integer evid;    
    private String eventName;
    private Date begins;
    private String beginsStr;
    private Date ends;
    private String endsStr;
    private String addrInstitution;
    private String addrHouseNumber;
    private String addrStreet;
    private String addrTown;
    private String addrZipCode;
    private Boolean active;

    public EventsSettersTest(Integer evid, String eventName, Date begins, String beginsStr, Date ends, String endsStr, String addrInstitution, String addrHouseNumber, String addrStreet, String addrTown, String addrZipCode, Boolean active) {
        this.evid = evid;
        this.eventName = eventName;
        this.begins = begins;
        this.beginsStr = beginsStr;
        this.ends = ends;
        this.endsStr = endsStr;
        this.addrInstitution = addrInstitution;
        this.addrHouseNumber = addrHouseNumber;
        this.addrStreet = addrStreet;
        this.addrTown = addrTown;
        this.addrZipCode = addrZipCode;
        this.active = active;
    }
    
    @Parameterized.Parameters
    public static Collection getEventValues(){
        return Arrays.asList(new Object[][]{
            {1, "Koncert", new Date(), "22.05.2019 12:00", new Date(), "22.05.2019 12:30", "Koncertni sal ZUS", "12", "Umelecka", "Kocourkov", "12345", true},
            {null, "", null, "", null, "", "", "", "", "", "", false},
            {1, "Koncert", new Date(), "22a.05.2019 12:00", new Date(), "22.05.201as9 12:30", "Koncertni sal ZUS", "12", "Umelecka", "Kocourkov", "1234", null}
        });
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
    
    /**
     * Test of setEventname method, of class Events.
     */
    @Test
    public void testSetEventname_setsAddressStreetWithNoValidation_storesAddressStreetGiven() {
        System.out.println("setAddrStreet");
        try{
            Events instance = new Events();
            instance.setEventname(eventName);
            Assert.assertEquals(instance.getEventname(), eventName);
        } catch (WrongInputException ex){
            Assert.assertTrue("WrongInputException with message: " + ex.getMessage() + " caught for ends: " + eventName,
                    true);
        } catch (Exception ex){
            Assert.fail("An error occured during the test: " + ex);
        }
    }
    
    /**
     * Test of setBegins method, of class Events.
     */
    @Test
    public void testSetBegins_setsDateObjectAsBegins_storesBegins() {
        System.out.println("setBegins");
        try{
            Events instance = new Events();
            instance.setBegins(begins);
            Assert.assertEquals(instance.getBegins(), begins);
        } catch (Exception ex){
            Assert.fail("An error occured during the test: " + ex);
        }
    }
    
    /**
     * Test of setBegins method, of class Events
     */
    @Test
    public void testSetBegins_setsStringAsBegins_storesBeginsOrThrowsWrongInputException() {
        System.out.println("setBegins");
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        try{
            Events instance = new Events();
            instance.setBegins(beginsStr);
            Assert.assertEquals(instance.getBegins(), df.parse(beginsStr));
        } catch (WrongInputException ex){
            Assert.assertTrue("WrongInputException with message: " + ex.getMessage() + " caught for begins: " + beginsStr,
                    true);
        } catch (Exception ex){
            Assert.fail("An error occured during the test: " + ex);
        }
    }
    
    /**
     * Test of setEnds method, of class Events.
     */
    @Test
    public void testSetEnds_setsDateObjectAsEnds_storesEnds() {
        System.out.println("setEnds");
        try{
            Events instance = new Events();
            instance.setEnds(ends);
            Assert.assertEquals(instance.getEnds(), ends);
        } catch (Exception ex){
            Assert.fail("An error occured during the test: " + ex);
        }
    }
    
    /**
     * Test of setEnds method, of class Events
     */
    @Test
    public void testSetEnds_setsStringAsEnds_storesEndsOrThrowsWrongInputException() {
        System.out.println("setEnds");
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        try{
            Events instance = new Events();
            instance.setEnds(endsStr);
            Assert.assertEquals(instance.getEnds(), df.parse(endsStr));
        } catch (WrongInputException ex){
            Assert.assertTrue("WrongInputException with message: " + ex.getMessage() + " caught for ends: " + endsStr,
                    true);
        } catch (Exception ex){
            Assert.fail("An error occured during the test: " + ex);
        }
    }
    
    /**
     * Test of setAddrStreet method, of class Events.
     */
    @Test
    public void testSetAddrStreet_setsAddressStreetWithNoValidation_storesAddressStreetGiven() {
        System.out.println("setAddrStreet");
        try{
            Events instance = new Events();
            instance.setAddrstreet(addrStreet);
            Assert.assertEquals(instance.getAddrstreet(), addrStreet);
        } catch (WrongInputException ex){
            Assert.assertTrue("WrongInputException with message: " + ex.getMessage() + " caught for ends: " + addrStreet,
                    true);
        } catch (Exception ex){
            Assert.fail("An error occured during the test: " + ex);
        }
    }
    
    /**
     * Test of setAddrinstitution method, of class Events.
     */
    @Test
    public void testSetAddrinstitution_setsAddressStreetWithNoValidation_storesAddressStreetGiven() {
        System.out.println("setAddrinstitution");
        try{
            Events instance = new Events();
            instance.setAddrstreet(addrInstitution);
            Assert.assertEquals(instance.getAddrstreet(), addrInstitution);
        } catch (WrongInputException ex){
            Assert.assertTrue("WrongInputException with message: " + ex.getMessage() + " caught for ends: " + addrInstitution,
                    true);
        } catch (Exception ex){
            Assert.fail("An error occured during the test: " + ex);
        }
    }
    
    /**
     * Test of setAddrStreet method, of class Events.
     */
    @Test
    public void testSetAddrhousenumber_setsAddressHouseNumberWithNoValidation_storesAddressHouseNumberGiven() {
        System.out.println("setAddrStreet");
        try{
            Events instance = new Events();
            instance.setAddrhousenumber(addrHouseNumber);
            Assert.assertEquals(instance.getAddrhousenumber(), addrHouseNumber);
        } catch (WrongInputException ex){
            Assert.assertTrue("WrongInputException with message: " + ex.getMessage() + " caught for ends: " + addrHouseNumber,
                    true);
        } catch (Exception ex){
            Assert.fail("An error occured during the test: " + ex);
        }
    }
    
    /**
     * Test of setAddrtown method, of class Events.
     */
    @Test
    public void testSetAddrtown_setsAddressTownWithNoValidation_storesAddressTownGiven() {
        System.out.println("setAddrtown");
        try{
            Events instance = new Events();
            instance.setAddrtown(addrTown);
            Assert.assertEquals(instance.getAddrtown(), addrTown);
        } catch (WrongInputException ex){
            Assert.assertTrue("WrongInputException with message: " + ex.getMessage() + " caught for ends: " + addrTown,
                    true);
        } catch (Exception ex){
            Assert.fail("An error occured during the test: " + ex);
        }
    }
    
    /**
     * Test of setAddrZipCode method, of class Events. Ignored because the test is already 
     * included in {@link #testSetAddrZipCode_setsAStringToAddrZipCodeAndValidates_storesAddressZipCodeGivenOrThrowsWrongInputException() }
     */
    @Ignore
    @Test
    public void testSetAddrZipCode_setsAnIntegerToAddrZipCodeAndValidates_storesAddressZipCodeGivenOrThrowsWrongInputException() throws Exception {
        System.out.println("setAddrZipCode");
        try{
            Events instance = new Events();
            Integer zipCode = Integer.parseInt(addrZipCode);
            instance.setAddrZipCode(zipCode);
            Assert.assertEquals(instance.getAddrzipcode().toString(), zipCode.toString());
        } catch (WrongInputException ex){
            Assert.assertTrue("WrongInputException with message: " + ex.getMessage() + " caught for phone: " + addrZipCode,
                    true);
        } catch (Exception ex){
            Assert.fail("An error occured during the test: " + ex);
        }
    }

    /**
     * Test of setAddrZipCode method, of class Events.
     */
    @Test
    public void testSetAddrZipCode_setsAStringToAddrZipCodeAndValidates_storesAddressZipCodeGivenOrThrowsWrongInputException(){
        System.out.println("setAddrZipCode");
        try{
            Events instance = new Events();
            instance.setAddrZipCode(addrZipCode);
            if(instance.getAddrzipcode() != null){
                Assert.assertEquals(instance.getAddrzipcode().toString(), addrZipCode);
            } else {
                Assert.assertEquals("", addrZipCode);
            }
            
        } catch (WrongInputException ex){
            Assert.assertTrue("WrongInputException with message: " + ex.getMessage() + " caught for phone: " + addrZipCode,
                    true);
        } catch (Exception ex){
            Assert.fail("An error occured during the test: " + ex);
        }
    }
    /**
     * Test setActive method, of Events class.
     */
    @Test
    public void testSetActive_setsActiveBooleanValue_storesBooleanValueGiven(){
        System.out.println("setActive");
        try{
            Events instance = new Events();
            instance.setActive(active);
            Assert.assertEquals(instance.getActive(), active);                
        } catch (Exception ex){
            Assert.fail("An error occured during the test: " + ex);
        }
    }
}
