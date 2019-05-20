/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra.model.entities;

import java.util.InputMismatchException;
import javax.swing.JComboBox;

/**
 * This enum represents the participation state.
 * State {@code NOT_INVITED} means that there are no relation between user and the event, 
 * state {@code NOT_FILLED} meanns that user did not filled yet the information about participation,
 * state {@code NOT_COMING} means that the user will not participate at the event and 
 * state {@code COMING} means that the user will participate the event. 
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
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
    
    /**
     * Gets the index for comboBox in Gui.
     * @return 0 for {@code NOT_INVITED} or {@code NOT_FILLED}, 1 for {@code COMING} and 2 for {@code NOT_COMING}
     */
    public int intVal(){
        return comboBoxIndex;
    }
    
    /**
     * Converts the enum to {@code Boolean} object.
     * @return {@code null} for {@code NOT_INVITED} or {@code NOT_FILLED}, {@code true} for {@code COMING} and {@code false} for {@code NOT_COMING}
     */
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
    
    /**
     * Works as inverted function to {@link #toBoolean() }
     * @param state - {@code Boolean} object
     * @return proper {@link ParticipantState} enum
     */
    public static ParticipantState fromBoolean(Boolean state){
        if(state == null){
            return NOT_FILLED;
        } else if(state) {
            return COMING;
        } else {
            return NOT_COMING;
        }        
    }
    
    /**
     * Gets participant state from {@code JComboBox}. Works as inverted function to {@link #intVal() }
     * @param box - {@code JComboBox} object
     * @return proper {@link ParticipantState} enum
     */
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
