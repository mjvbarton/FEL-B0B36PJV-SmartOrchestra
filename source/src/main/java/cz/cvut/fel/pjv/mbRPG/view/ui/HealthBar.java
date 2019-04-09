/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.pjv.mbRPG.view.ui;

import cz.cvut.fel.pjv.mbRPG.model.livingEntities.Player;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

/**
 *
 * @author Matěj Bartoň
 */
public class HealthBar {
    private int maxLives;
    private int health;
    private ProgressBar progressBar;
    private Label label;
    
    public static final String CAPTION = "Health";

    public void setMaxLives(Player player) {
        this.maxLives = player.getMaxLives();
    }
    
    public void update(Player player){
        
    }
        
}
