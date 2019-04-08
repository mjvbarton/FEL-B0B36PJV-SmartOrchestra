/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.pjv.mbRPG.model;

import cz.cvut.fel.pjv.mbRPG.Constants.DoorColor;
import cz.cvut.fel.pjv.mbRPG.model.items.Key;
import javafx.geometry.Dimension2D;
import javafx.scene.image.Image;

/**
 *
 * @author Matěj Bartoň
 */
public class Door extends Entity{
    private final Key validKey;
    private final DoorColor color;
    private boolean locked = true;
    
    private static final int SIZE_X = 0;
    private static final int SIZE_Y = 0;
    private static final String IMAGE_PATH = "resources/scenery/door.png";
    
    /**
     * 
     * @param position - position in the gamefield
     * @param validKey - pointer to the valid key
     * @param color - color to match the door to the key visually
     */
    public Door(Dimension2D position, Key validKey, DoorColor color) {
        super(position, new Dimension2D(SIZE_X, SIZE_Y), new Image(IMAGE_PATH));
        this.color = color;
        this.validKey = validKey;
    }
    
    /**
     * Tries to open door with given key
     * @param key
     * @return true if the door is opened
     */
    public boolean tryOpen(Key key){
        return false;
    }

    public DoorColor getColor() {
        return null;
    }
    
}
