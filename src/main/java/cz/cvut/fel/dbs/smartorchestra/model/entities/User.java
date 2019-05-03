/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra.model.entities;

import java.util.Date;

/**
 *
 * @author Matěj Bartoň
 */
public class User {
    private int uid;
    private String email;
    private String passwd;
    
    private String firstName;
    private String familyName;
    private Date birthDate;
    private String phone;
    
    private String addrStreet;
    private String addrHouseNum;
    private String addrTown;
    private int addrZipCode;
        
}
