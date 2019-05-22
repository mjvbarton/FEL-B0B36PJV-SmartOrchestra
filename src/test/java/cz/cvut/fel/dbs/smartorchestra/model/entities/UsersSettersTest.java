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
public class UsersSettersTest {
    Long uid;
    String email;
    private String passwd;
    private String firstName;
    private String familyName;
    private Date birthDate;
    private String birthDateStr;
    private String phone;
    private String addrHouseNumber;
    private String addrStreet;
    private String addrTown;
    private String addrZipCode;

    public UsersSettersTest(Long uid, String email, String passwd, String firstName, String familyName, Date birthDate, String birthDateStr, String phone, String addrHouseNumber, String addrStreet, String addrTown, String addrZipCode) {
        this.uid = uid;
        this.email = email;
        this.passwd = passwd;
        this.firstName = firstName;
        this.familyName = familyName;
        this.birthDate = birthDate;
        this.birthDateStr = birthDateStr;
        this.phone = phone;
        this.addrHouseNumber = addrHouseNumber;
        this.addrStreet = addrStreet;
        this.addrTown = addrTown;
        this.addrZipCode = addrZipCode;
    }
    @Parameterized.Parameters
    public static Collection getUserValues(){
        return Arrays.asList(new Object[][]{
            {1L, "admin@smartorchestra.net", "admin1234", "Josef", "Kulhavy", new Date(), "21.05.2019", "+420720180648", "12", "Slunecna", "Kocourkov", "12345"},
            {null, "", "", "", "", null, "", "", "", "", "", ""},
            {1L, "admin@smartorchestra", "admin1234", "Josef", "Kulhavy", new Date(), "21as.05.2019", "+420sadf720180648", "12", "Slunecna", "Kocourkov", "123"},
            {1L, "admin@smartorchestra", "admin1234", "Josef", "Kulhavy", new Date(), "21.05.2019", "+420720", "12", "Slunecna", "Kocourkov", "CZ12345"},
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
     * Test of setUid method, of class Users.
     */
    @Test
    public void testSetUid() {
        System.out.println("setUid");
        try{            
            Users instance = new Users();
            instance.setUid(uid);
            Assert.assertEquals(instance.getUid(), uid);
        } catch (Exception ex){
            Assert.fail("An error occured during the test: " + ex);
        }
        
    }

    /**
     * Test of setEmail method, of class Users.
     */
    @Test
    public void testSetEmail_validationAndSetOfEmailAttribute_emailAttrSetOrWrongInputExceptionThrown() throws Exception{
        System.out.println("setEmail");
        try{
            Users instance = new Users();
            instance.setEmail(email);
            Assert.assertEquals(instance.getEmail(), email);
        } catch (WrongInputException ex){
            Assert.assertTrue("An invalid or empty email caught for email: " + email, true);
        }
    }
    
    /**
     * Test of setPasswd method, of class Users.
     */
    @Test
    public void testSetPasswd_validatesAndSetsPasswd_storesPasswdOrThrowsWrongInputException() throws Exception {
        System.out.println("setEmail");
        try{
            Users instance = new Users();
            instance.setPasswd(passwd);
            Assert.assertEquals(instance.getPasswd(), passwd);
        } catch (WrongInputException ex){
            Assert.assertTrue("An empty password exception caught.", true);
        }
    }
    
    /**
     * Test of setFirstName method, of class Users.
     */
    @Test
    public void testSetFirstName_validateAndSetsFirstName_storesFirstNameOrThrowsInputException(){
        System.out.println("setFirstName");
        try{
            Users instance = new Users();
            instance.setFirstName(firstName);
            Assert.assertEquals(instance.getFirstName(), firstName);
        } catch (WrongInputException ex){
            Assert.assertTrue("An empty firstName exception was caught. For firstName: "+ firstName, true);
        } catch (Exception ex){
            Assert.fail("An error occured during the test: " + ex);
        }
    }
    
    /**
     * Test of setFamilyName method, of class Users
     */
    @Test
    public void testSetFamilyName_validateAndSetsFamilyName_storesFamilyNameOrThrowsWrongInputException(){
        System.out.println("setFamilyName");
        try{
            Users instance = new Users();
            instance.setFamilyName(familyName);
            Assert.assertEquals(instance.getFamilyName(), familyName);
        } catch (WrongInputException ex){
            Assert.assertTrue("An empty familyName exception was caught for familyName: "+ familyName, true);
        } catch (Exception ex){
            Assert.fail("An error occured during the test: " + ex);
        }
    }

    /**
     * Test of setBirthDate method, of class Users.
     */
    @Test
    public void testSetBirthDate_setsDateObjectAsBirthDate_storesBirthDate() {
        System.out.println("setBirthDate");
        try{
            Users instance = new Users();
            instance.setBirthDate(birthDate);
            Assert.assertEquals(instance.getBirthDate(), birthDate);
        } catch (Exception ex){
            Assert.fail("An error occured during the test: " + ex);
        }
    }
    
    /**
     * Test of setBirthDate method, of class Users
     */
    @Test
    public void testSetBirthDate_setsStringAsBirthDate_storesBirthDateOrThrowsWrongInputException() {
        System.out.println("setBirthDate");
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        try{
            Users instance = new Users();
            instance.setBirthDate(birthDateStr);
            Assert.assertEquals(instance.getBirthDate(), df.parse(birthDateStr));
        } catch (WrongInputException ex){
            Assert.assertTrue("WrongInputException with message: " + ex.getMessage() + " caught for birthDate: " + birthDateStr,
                    true);
        } catch (Exception ex){
            Assert.fail("An error occured during the test: " + ex);
        }
    }

    
    /**
     * Test of setPhone method, of class Users.
     */
    @Test
    public void testSetPhone_validatesAndsetsPhone_storesPhoneGivenOrThrowsWrongInputException() throws Exception {
        System.out.println("setPhone");
        try{
            Users instance = new Users();
            instance.setPhone(phone);
            Assert.assertEquals(instance.getPhone(), phone);
        } catch (WrongInputException ex){
            Assert.assertTrue("WrongInputException with message: " + ex.getMessage() + " caught for phone: " + phone,
                    true);
        } catch (Exception ex){
            Assert.fail("An error occured during the test: " + ex);
        }
    }
    
    /**
     * Test of setAddrStreet method, of class Users.
     */
    @Test
    public void testSetAddrStreet_setsAddressStreetWithNoValidation_storesAddressStreetGiven() {
        System.out.println("setAddrStreet");
        try{
            Users instance = new Users();
            instance.setAddrStreet(addrStreet);
            Assert.assertEquals(instance.getAddrStreet(), addrStreet);
        } catch (Exception ex){
            Assert.fail("An error occured during the test: " + ex);
        }
    }
    
    /**
     * Test of setAddrStreet method, of class Users.
     */
    @Test
    public void testSetAddrHouseNumber_setsAddressHouseNumberWithNoValidation_storesAddressHouseNumberGiven() {
        System.out.println("setAddrStreet");
        try{
            Users instance = new Users();
            instance.setAddrHouseNumber(addrHouseNumber);
            Assert.assertEquals(instance.getAddrHouseNumber(), addrHouseNumber);
        } catch (Exception ex){
            Assert.fail("An error occured during the test: " + ex);
        }
    }
    
    /**
     * Test of setAddrTown method, of class Users.
     */
    @Test
    public void testSetAddrTown_setsAddressTownWithNoValidation_storesAddressTownGiven() {
        System.out.println("setAddrTown");
        try{
            Users instance = new Users();
            instance.setAddrTown(addrTown);
            Assert.assertEquals(instance.getAddrTown(), addrTown);
        } catch (Exception ex){
            Assert.fail("An error occured during the test: " + ex);
        }
    }
    
    /**
     * Test of setAddrZipCode method, of class Users. Ignored because the test is already 
     * included in {@link #testSetAddrZipCode_setsAStringToAddrZipCodeAndValidates_storesAddressZipCodeGivenOrThrowsWrongInputException() }
     */
    @Ignore
    @Test
    public void testSetAddrZipCode_setsAnIntegerToAddrZipCodeAndValidates_storesAddressZipCodeGivenOrThrowsWrongInputException() throws Exception {
        System.out.println("setAddrZipCode");
        try{
            Users instance = new Users();
            Integer zipCode = Integer.parseInt(addrZipCode);
            instance.setAddrZipCode(zipCode);
            Assert.assertEquals(instance.getAddrZipCode().toString(), zipCode.toString());
        } catch (WrongInputException ex){
            Assert.assertTrue("WrongInputException with message: " + ex.getMessage() + " caught for phone: " + addrZipCode,
                    true);
        } catch (Exception ex){
            Assert.fail("An error occured during the test: " + ex);
        }
    }

    /**
     * Test of setAddrZipCode method, of class Users.
     */
    @Test
    public void testSetAddrZipCode_setsAStringToAddrZipCodeAndValidates_storesAddressZipCodeGivenOrThrowsWrongInputException(){
        System.out.println("setAddrZipCode");
        try{
            Users instance = new Users();
            instance.setAddrZipCode(addrZipCode);
            if(instance.getAddrZipCode() != null){
                Assert.assertEquals(instance.getAddrZipCode().toString(), addrZipCode);
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
}
