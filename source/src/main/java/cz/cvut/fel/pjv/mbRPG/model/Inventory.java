/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.pjv.mbRPG.model;

import cz.cvut.fel.pjv.mbRPG.Constants.ItemType;
import cz.cvut.fel.pjv.mbRPG.model.items.DamagePotion;
import cz.cvut.fel.pjv.mbRPG.model.items.HealthPotion;
import cz.cvut.fel.pjv.mbRPG.model.items.Key;
import java.io.File;
import java.util.LinkedList;

/**
 *
 * @author Matěj Bartoň
 */
public class Inventory {
    private LinkedList<DamagePotion> damagePotions;
    private LinkedList<HealthPotion> healthPotions;
    private LinkedList<Key> keys;
    
    /**
     * Initalizes the inventory list from file
     * @param inputFile 
     */
    public void initFromFile(File inputFile){
        
    }
    /**
     * Gets one item with selected itemType
     * @param itemType - value from ItemType enum
     * @return item
     */
    private Item get(ItemType itemType){
        return null;
    }
    
    /**
     * Counts all elements in the inventory
     * @return 
     */
    public int count(){
        return 0;
    }
    
    /**
     * Counts all elemets in the inventory with selected itemType
     * @param itemType - value form ItemType enum
     * @return (see reference)
     */
    public int count(ItemType itemType){
        return 0;
    }
    
    /**
     * Adds DamagePotion item to the inventory
     * @param item 
     */
    public void add(DamagePotion item){
        
    }
    
    /**
     * Adds HealthPotion item to the inventory
     * @param item 
     */
    public void add(HealthPotion item){
        
    }
    
    /**
     * Adds Key item to the inventory
     * @param item 
     */
    public void add(Key item){
        
    }
    
}
