/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.fel.pjv.mbRPG.model;

import javafx.geometry.Dimension2D;
import javafx.scene.image.Image;

/**
 *
 * @author Matěj Bartoň
 */
public class Entity {
    Dimension2D position;
    Dimension2D size;
    Image image;
    
    
    public Entity(Dimension2D position, Dimension2D size, Image image){
        this.position = position;
        this.image = image;
        this.size = size;
    }
}
