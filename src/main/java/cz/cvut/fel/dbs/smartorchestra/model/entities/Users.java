/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra.model.entities;

import cz.cvut.fel.dbs.smartorchestra.exceptions.WrongInputException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Represents Users entity from the database. Performs validation when entering values.
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
@Entity
public class Users implements Serializable {

    //private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uid;
    
    private String email;
    private String passwd;
    
    private String firstName;
    private String familyName;
    private Date birthDate;
    private String phone;
    
    private String addrStreet;
    private String addrHouseNumber;
    private String addrTown;
    private Integer addrZipCode;
    
    /**
     * Gets the user ID
     * @return user ID as {@code Long} number
     */
    public Long getUid() {
        return uid;
    }
    
    /**
     * Sets the user's ID
     * @param uid user's ID as {@code Long} number
     */
    public synchronized void setUid(Long uid) {
        this.uid = uid;
    }
    
    /**
     * Gets the user's email
     * @return user's email as {@code String}
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Validates and sets the user's email.
     * @param email user's email as {@code String}
     * @throws WrongInputException if an empty string was given or the string does not match
     * email regex: {@code "^[-a-z0-9~!$%^&*_=+}{\\'?]+(\\.[-a-z0-9~!$%^&*_=+}{\\'?]+)*@([a-z0-9_][-a-z0-9_]*(\\.[-a-z0-9_]+)*\\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,5})?$}
     */
    public synchronized void setEmail(String email) throws WrongInputException{
        if(!email.matches("^[-a-z0-9~!$%^&*_=+}{\\'?]+(\\.[-a-z0-9~!$%^&*_=+}{\\'?]+)*@([a-z0-9_][-a-z0-9_]*(\\.[-a-z0-9_]+)*\\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,5})?$")){
            throw new WrongInputException("Neplatný email");
        }                   
        this.email = email;
    }

    /**
     * Gets the hashed user's password (See: {@link org.mindrot.jbcrypt.BCrypt})
     * @return hashed password as {@code String}
     */
    public String getPasswd() {
        return passwd;
    }

    /**
     * Validates and sets the user's password. (See: {@link org.mindrot.jbcrypt.BCrypt})
     * @param passwd hashed user's password as {@code String}
     * @throws WrongInputException if an empty string was given
     */
    public synchronized void setPasswd(String passwd) throws WrongInputException {
        if(passwd.isEmpty()){
            throw new WrongInputException("Nevyplnili jste toto pole");
        }
        this.passwd = passwd;
    }
    
    /**
     * Gets the user's first name
     * @return user's first name as {@code String}
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Validates and sets the user's first name.
     * @param firstName user's first name as {@code String}
     * @throws WrongInputException if an empty string was given
     */
    public synchronized void setFirstName(String firstName) throws WrongInputException {
        if(firstName.isEmpty()){
            throw new WrongInputException("Nevyplnili jste toto pole");
        }
        this.firstName = firstName;
    }

    /**
     * Gets the user's family name
     * @return user's family name as {@code String}
     */
    public String getFamilyName() {
        return familyName;
    }

    /**
     * Validates and sets the user's family name.
     * @param familyName user's family name as {@code String}
     * @throws WrongInputException if an empty string was given
     */
    public synchronized void setFamilyName(String familyName) throws WrongInputException {
        if(familyName.isEmpty()){
            throw new WrongInputException("Nevyplnili jste toto pole");
        }
        this.familyName = familyName;
    }
    
    /**
     * Gets the user's birth date
     * @return user's birth date as {@code Date}
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Gets the user's birth date
     * @param birthDate user's birth date as {@code Date}
     */
    public synchronized void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    
    /**
     * Validates and sets user's birth date.
     * @param date user's birth date as {@code String}
     * @throws WrongInputException if an empty string was given or the {@code date} does not
     * match date format {@code dd.MM.yyyy}
     */
    public synchronized void setBirthDate(String date) throws WrongInputException {
        if(date.isEmpty()){
            throw new WrongInputException("Nevyplnili jste toto pole");
        }
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try{
            setBirthDate(format.parse(date));
            
        } catch(ParseException err){
            throw new WrongInputException("Špatný formát data");
        }
    }

    /**
     * Gets the user's phone number
     * @return user's phone number as {@code String}
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Validates user's phone number and sets it.
     * @param phone user's phone number as {@code String}
     * @throws WrongInputException when the {@code phone} is an empty string or, the {@code phone} does not match
     * the regex for mobile phone: {@code ^\\+[1-9]{1}[0-9]{3,14}$}
     */
    public synchronized void setPhone(String phone) throws WrongInputException{
        if(phone.isEmpty()){
            throw new WrongInputException("Nevyplnili jste toto pole");
        } else if(!phone.matches("^\\+[1-9]{1}[0-9]{3,14}$")){
            throw new WrongInputException("Špatný formát telefonního čísla");
        }
        this.phone = phone;
    }

    /**
     * Gets the user's address - street
     * @return user's address - street as {@code String}
     */
    public String getAddrStreet() {
        return addrStreet;
    }

    /**
     * Sets the user's address - street
     * @param addrStreet user's address - street as {@code String}
     */
    public synchronized void setAddrStreet(String addrStreet) {
        this.addrStreet = addrStreet;
    }
    
    /**
     * Gets the user's address - house number
     * @return user's address - house number as {@code String}
     */
    public String getAddrHouseNumber() {
        return addrHouseNumber;
    }

    /**
     * Gets the user's address - house number
     * @param addrHouseNumber user's address - house number as {@code String}
     */
    public synchronized void setAddrHouseNumber(String addrHouseNumber){
        this.addrHouseNumber = addrHouseNumber;
    }

    /**
     * Gets the user's address - town
     * @return user's address - town as {@code String}
     */
    public String getAddrTown() {
        return addrTown;
    }
    
    /**
     * Sets user's address - town
     * @param addrTown user's address - town as {@code String}
     */
    public synchronized void setAddrTown(String addrTown){
        this.addrTown = addrTown;
    }
    
    /**
     * Gets the user's address - zip code
     * @return user's address - zip code as {@code Integer}
     */
    public Integer getAddrZipCode() {
        return addrZipCode;
    }

    /**
     * Validates user's address zip code and sets it.
     * @param addrZipCode as {@code Integer}
     * @throws WrongInputException if the {@code addrZipCode} does not belong to interval [10000, 999999].
     */
    public synchronized void setAddrZipCode(Integer addrZipCode) throws WrongInputException {
        int zipCode = (int) addrZipCode;
        if(zipCode >= 10000 && zipCode <= 999999){
            this.addrZipCode = addrZipCode;
            return;
        }
        throw new WrongInputException("Špatný formát PSČ");
    }
    
    /**
     * Validates user's address zip code and set it
     * @param addrZipCode as {@code String}
     * @throws WrongInputException if the {@code addrZipCode} cannot be converted to {@code Integer} or
     * the {@code addrZipCode} does not belong to interval [10000, 999999].
     */
    public synchronized void setAddrZipCode(String addrZipCode) throws WrongInputException{
        if(addrZipCode.isEmpty()){
            this.addrZipCode = null;
            return;
        }
        try{
            Integer zipCode = Integer.parseInt(addrZipCode);
            setAddrZipCode(zipCode);
        } catch(NumberFormatException err){
            throw new WrongInputException("Špatný formát PSČ");
        }
                
    }
    
    /**
     * Creates hashCode for the object
     * @return {@code int} as hashCode
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uid != null ? uid.hashCode() : 0);
        return hash;
    }

    /**
     * Comparable method of the object
     * @param object
     * @return {@code boolean} value according to the {@link Comparable} interface
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    /**
     * Converts entity to {@code String}. Displaying the value of {@code Sections.sectionname}
     * @return a {@code String} object
     */
    @Override
    public String toString() {
        return "User['" + email + "']";
    }  
}
