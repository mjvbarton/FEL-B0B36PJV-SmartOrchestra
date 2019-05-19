/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra.model;

import cz.cvut.fel.dbs.smartorchestra.model.entities.Events;
import java.util.Date;
import java.util.InputMismatchException;
import javax.swing.JComboBox;

/**
 *
 * @author Matěj Bartoň
 */
public enum EventDateFilter {
    PAST,
    NEXT,
    ALL;
            
    public static EventDateFilter getFromComboBox(JComboBox comboBox){
        switch(comboBox.getSelectedIndex()){
            case 0:
                return NEXT;
            case 1:
                return PAST;
            case 2:
                return ALL;
            default:
                throw new InputMismatchException("Invalid enum value");
        }
    }
    
    public static EventDateFilter getFromEvent(Events event){
        Date now = new Date();
        if(now.after(event.getBegins())){
            return PAST;
        } else {
            return NEXT;
        }
    }
}
