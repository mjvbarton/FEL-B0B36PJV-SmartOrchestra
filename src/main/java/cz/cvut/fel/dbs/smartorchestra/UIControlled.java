/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra;

/**
 *
 * @author Matěj Bartoň
 * @param <UIController>
 */
public interface UIControlled<UIController> {
    public void setUIController(UIController controller);
    public UIController getUIController();
    
}
