/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.pjv.mbRPG.Constants;

/**
 *
 * @author Matěj Bartoň
 */
public enum ItemType {
    DAMAGE_POTION(0),
    HEALTH_POTION(1),
    KEY(2);
    
    private final int key;
    
    private ItemType(int key){
        this.key = key;
    }
}
