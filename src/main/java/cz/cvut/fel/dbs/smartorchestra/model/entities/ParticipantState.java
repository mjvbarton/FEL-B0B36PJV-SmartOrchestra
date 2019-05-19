/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra.model.entities;

import java.util.InputMismatchException;
import javax.swing.JComboBox;

/**
 *
 * @author Matěj Bartoň
 */
public enum ParticipantState {
    NOT_INVITED(0),
    NOT_FILLED(0),
    NOT_COMING(2),
    COMING(1);
    
    private final int comboBoxIndex;
    
    private ParticipantState(int comboBoxIndex){
        this.comboBoxIndex = comboBoxIndex;
    }
    
    public int intVal(){
        return comboBoxIndex;
    }
    
    public Boolean toBoolean(){
        switch(comboBoxIndex){
            case 0:
                return null;                
            case 1:
                return true;    
            case 2:
                return false; 
            default:
                throw new InputMismatchException("Wrong ParticipantState comboBoxIndex value.");
        }
        
    }
    
    public static ParticipantState fromBoolean(Boolean state){
        if(state == null){
            return NOT_FILLED;
        } else if(state) {
            return COMING;
        } else {
            return NOT_COMING;
        }        
    }
    
    public static ParticipantState getParticipantState(JComboBox box){
        int index = box.getSelectedIndex();
        switch(index){
            case 0:
                return NOT_FILLED;
            case 1:
                return COMING;
            case 2:
                return NOT_COMING;
            default:
                throw new InputMismatchException("The ComboBox index must under go ParticipantState enum int values");
        }
    }
}
