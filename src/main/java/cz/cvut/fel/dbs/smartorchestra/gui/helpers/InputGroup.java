/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra.gui.helpers;

/**
 *
 * @author Matěj Bartoň
 * @param <T>
 * @param <Q>
 */
public class InputGroup<T, Q> {
    private T formField;
    private Q infoField;
    
    public InputGroup(T formField, Q infoField){
        this.formField = formField;
        this.infoField = infoField;
    }  
}
