/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.pjv.mbRPG.view;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Matěj Bartoň
 */
public class InGameAlert extends Alert{
    public static final int SIZE_X = 0;
    public static final int SIZE_Y = 0;
    
    public InGameAlert(AlertType at, String contentText) {
        super(at, contentText, ButtonType.OK);
        super.setWidth(SIZE_X);
        super.setHeight(SIZE_Y);
    }
    
}
