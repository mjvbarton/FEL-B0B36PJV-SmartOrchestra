/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.pjv.mbRPG.model.livingEntities;

import cz.cvut.fel.pjv.mbRPG.model.LivingEntity;
import javafx.geometry.Dimension2D;
import javafx.scene.image.Image;

/**
 *
 * @author Matěj Bartoň
 */
public class Monster extends LivingEntity<Player>{

    public Monster(Dimension2D position, Image image) {
        super(position, image);
    }
    
    /**
     * Attack the player
     * @param entity - pointer to the player
     * @return - returns true if the attack was successful
     */
    @Override
    public boolean attack(Player entity) {
        return false;
    }
}
