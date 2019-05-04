/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra.model.entities;

import javax.persistence.*;
/**
 *
 * @author Matěj Bartoň
 */
@Entity
@Table
public class Administrator{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public int getId() {
        return id;
    }

  public void setId(int id) {
        this.id = id;
    }   
}
