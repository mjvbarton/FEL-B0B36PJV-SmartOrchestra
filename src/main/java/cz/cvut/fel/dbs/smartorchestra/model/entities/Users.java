/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 *
 * @author Matěj Bartoň
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

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws WrongInputException{
        if(!email.matches("^[-a-z0-9~!$%^&*_=+}{\\'?]+(\\.[-a-z0-9~!$%^&*_=+}{\\'?]+)*@([a-z0-9_][-a-z0-9_]*(\\.[-a-z0-9_]+)*\\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,5})?$")){
            throw new WrongInputException("Neplatný email");
        }                   
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) throws WrongInputException {
        if(firstName.isEmpty()){
            throw new WrongInputException("Nevyplnili jste toto pole");
        }
        this.passwd = passwd;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws WrongInputException {
        if(firstName.isEmpty()){
            throw new WrongInputException("Nevyplnili jste toto pole");
        }
        this.firstName = firstName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) throws WrongInputException {
        if(familyName.isEmpty()){
            throw new WrongInputException("Nevyplnili jste toto pole");
        }
        this.familyName = familyName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    
    public void setBirthDate(String date) throws WrongInputException {
        if(date.isEmpty()){
            throw new WrongInputException("Nevyplnili jste toto pole");
        }
        DateFormat format = new SimpleDateFormat("dd.mm.yyyy");
        try{
            setBirthDate(format.parse(date));
            
        } catch(ParseException err){
            throw new WrongInputException("Špatný formát data");
        }
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) throws WrongInputException{
        if(phone.isEmpty()){
            throw new WrongInputException("Nevyplnili jste toto pole");
        } else if(!phone.matches("^\\+[1-9]{1}[0-9]{3,14}$")){
            throw new WrongInputException("Špatný formát telefonního čísla");
        }
        this.phone = phone;
    }

    public String getAddrStreet() {
        return addrStreet;
    }

    public void setAddrStreet(String addrStreet) {
        if(!addrStreet.isEmpty()){
            this.addrStreet = addrStreet;
        }
    }

    public String getAddrHouseNumber() {
        return addrHouseNumber;
    }

    public void setAddrHouseNumber(String addrHouseNumber){
        if(!addrHouseNumber.isEmpty()){
            this.addrHouseNumber = addrHouseNumber;
        }
    }

    public String getAddrTown() {
        return addrTown;
    }

    public void setAddrTown(String addrTown){
        if(!addrTown.isEmpty()){
            this.addrTown = addrTown;
        }
    }

    public Integer getAddrZipCode() {
        return addrZipCode;
    }

    public void setAddrZipCode(Integer addrZipCode) throws WrongInputException {
        int zipCode = (int) addrZipCode;
        if(zipCode >= 10000 && zipCode <= 999999){
            this.addrZipCode = addrZipCode;
            return;
        }
        throw new WrongInputException("Špatný formát PSČ");
    }
    
    public void setAddrZipCode(String addrZipCode) throws WrongInputException{
        if(addrZipCode.isEmpty()){
            return;
        }
        try{
            Integer zipCode = Integer.parseInt(addrZipCode);
            setAddrZipCode(zipCode);
        } catch(NumberFormatException err){
            throw new WrongInputException("Špatný formát PSČ");
        }
                
    }
            
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uid != null ? uid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cz.cvut.fel.dbs.smartorchestra.model.entities.Users[ id=" + uid + " ]";
    }
    
}
