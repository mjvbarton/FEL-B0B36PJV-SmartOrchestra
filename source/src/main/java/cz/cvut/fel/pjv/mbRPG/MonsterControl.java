/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.pjv.mbRPG;

import cz.cvut.fel.pjv.mbRPG.model.livingEntities.Monster;

/**
 *  This is the controller of monster
 * @author Matěj Bartoň
 */
public class MonsterControl extends PlayerControl implements Runnable{

    public MonsterControl(Monster monster) {
        super(monster);
    }
    
    /**
     * Starts calm sequence of monster
     */
    public void startCalmSequence(){}
    
    /**
     * Starts attack sequence of monster
     */
    public void startAttackSequence(){}

    /**
     * Controller workflow
     */
    @Override
    public void run() {
        
    }    
}
