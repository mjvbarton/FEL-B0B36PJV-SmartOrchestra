/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra.model.entities;

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
}
