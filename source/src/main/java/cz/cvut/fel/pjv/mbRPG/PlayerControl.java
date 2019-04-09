/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.pjv.mbRPG;

import cz.cvut.fel.pjv.mbRPG.Constants.Controls;
import cz.cvut.fel.pjv.mbRPG.Constants.Move;
import cz.cvut.fel.pjv.mbRPG.model.LivingEntity;

/**
 *
 * @author Matěj Bartoň
 * @param <T> Living object
 * @param <C>
 */
public class PlayerControl<T extends LivingEntity> implements Runnable{
    private final T player;
    
    public PlayerControl(T player){
        this.player = player;
    }
    
    /**
     * Controller workflow
     */
    @Override
    public void run() {
        
    }
    
    /**
     * Perform step towards the selected direction
     * 
     * @param direction 
     */
    private void step(Move direction){
        
    }
    
    /**
     * Do selected action
     * 
     * @param control 
     */
    public void action(Controls control){
       
    }
}
