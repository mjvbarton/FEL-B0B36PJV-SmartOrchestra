/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.pjv.mbRPG.model;

import javafx.geometry.Dimension2D;
import javafx.geometry.Rectangle2D;

/**
 *
 * @author Matěj Bartoň
 */
public class Element implements Collidable<LivingEntity>{
    private final Dimension2D size;
    private final Dimension2D position;
    
    public Element(Dimension2D size, Dimension2D position){
        this.size = size;
        this.position = position;
    }
    
    /**
     * Checks if the Living entity objects collides with living entity
     * @param entity - living entity
     * @return 
     */    
    @Override
    public boolean checkCollision(LivingEntity entity){
        return false;
    }
    
    @Override
    public Rectangle2D getBoundary(){
        return null;
    }
    
}
