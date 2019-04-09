/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.pjv.mbRPG.model;

import javafx.geometry.Rectangle2D;

/**
 * This interface gives an object abillity to collide with another object
 * @author Matěj Bartoň
 * @param <T> collidable object
 */
public interface Collidable<T extends Collidable> {
    
    /**
     * Check object with selected object
     * @param obj - collidable object
     * 
     * @return 
     */
    public abstract boolean checkCollision(T obj);
    
    /**
     * Gets the boundary around the collidable object
     * 
     * @return 
     */
    public abstract Rectangle2D getBoundary();
    
}
