/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.pjv.mbRPG.view.ui;

import cz.cvut.fel.pjv.mbRPG.model.Inventory;
import cz.cvut.fel.pjv.mbRPG.model.ItemButtonable;
import javafx.scene.control.Button;


/**
 *
 * @author Matěj Bartoň
 * @param <T>
 */
public class ItemButton<T extends ItemButtonable> {
    private static final int SIZE_X = 0;
    private static final int SIZE_Y = 0;
    
    private int count;
    private Button btn;
    protected T item;
    
    /**
     * Creates an item button for an item
     * @param item 
     */
    public ItemButton(T item){
        this.item = item;
    }
    
    /**
     * Updates count of the item in inventory
     * @param inventory 
     */
    public void update(Inventory inventory){
        
    }
}
