/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.pjv.mbRPG.model;

import cz.cvut.fel.pjv.mbRPG.model.livingEntities.Player;
import javafx.geometry.Dimension2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

/**
 *
 * @author Matěj Bartoň
 * @param <T> the entity affected by the item
 */
public abstract class Item<T> extends Entity implements ItemButtonable, Collidable<Player>{
    private boolean used = false;
    private boolean visible = true;
    
    private static final int SIZE_X = 0;
    private static final int SIZE_Y = 0;
    
    public Item(Dimension2D position, Image image) {
        super(position, new Dimension2D(SIZE_X, SIZE_Y), image);
    }
    
    /**
     * Use of the element for current entity
     * @param entity 
     */
    public abstract void use(T entity);

    /**
     * This removes the element from the gamefield.
     * This functionality is necessary for the proper work of the Inventory.
     */
    public void hide() {
        
    }
    
    @Override
    public Image getImage(){
        return image;
    }
    
    /**
     * Checks the collision with the player
     * @param player
     * @return true if collides
     */
    @Override
    public boolean checkCollision(Player player){
        return false;
    }
    
    @Override
    public Rectangle2D getBoundary(){
        return null;
    }
}
