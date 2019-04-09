/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.pjv.mbRPG.model;

import cz.cvut.fel.pjv.mbRPG.Constants.Move;
import javafx.geometry.Dimension2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

/**
 *
 * @author Matěj Bartoň
 */
public abstract class LivingEntity<T> extends Entity implements Fallable, Collidable<Collidable>{
    
    protected int damage;
    protected int lives;
    protected int maxLives;
    private boolean isAlive = true;
    
    public static final double SIZE_X = 0;
    public static final double SIZE_Y = 0;
            
    public LivingEntity(Dimension2D position, Image image) {
        super(position, new Dimension2D(SIZE_X, SIZE_Y), image);
    }
    
    /**
     * Utoci na jakoukoliv
     * @param entity
     * @return 
     */
    
    public abstract boolean attack(T entity);
    
    public void harm(int damage){
        
    }
    
    public void move(Move direction){
        
    }

    @Override
    public void fall() {
        
    }

    @Override
    public void jump() {
       
    }
    
    @Override
    public boolean checkCollision(Collidable obj){
        return false;
    }
    
    @Override
    public Rectangle2D getBoundary(){
        return null;
    }
}
