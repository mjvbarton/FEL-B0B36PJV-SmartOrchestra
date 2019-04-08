/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.pjv.mbRPG.model.items;

import cz.cvut.fel.pjv.mbRPG.Constants.DoorColor;
import cz.cvut.fel.pjv.mbRPG.model.Door;
import cz.cvut.fel.pjv.mbRPG.model.Item;
import javafx.geometry.Dimension2D;
import javafx.scene.image.Image;

/**
 *
 * @author Matěj Bartoň
 */
public class Key extends Item<Door> implements Comparable<Key>{
    private final Door validDoor;
    private final DoorColor color;
    
    private static final String IMAGE_PATH = "resources/items/Key.png";
    
    /**
     * 
     * @param position - position of the item in the gamefield
     * @param validDoor - pointer to the door which the key unlocks
     */
    public Key(Dimension2D position, Door validDoor) {
        super(position, new Image(IMAGE_PATH));
        this.color = validDoor.getColor();
        this.validDoor = validDoor;
    }
       
    /**
     * Use of the key by door entity
     * @param door 
     */
    @Override
    public void use(Door door) {
        
    }

    @Override
    /**
     * Check if key compares to another key
     */
    public int compareTo(Key o) {
        return 0;
    }
    
}
