/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.pjv.mbRPG;

import java.io.File;

/**
 *
 * @author Matěj Bartoň
 */
public class LevelGenerator {
    private String levelName;
    private int order;

    public LevelGenerator(String levelName, int order, File levelFile) {
        this.levelName = levelName;
        this.order = order;
        
        loadFromFile(levelFile);
    }
    
    /**
     * Loads level data from given level file
     * @param levelFile - JSON file with level data
     */
    private void loadFromFile(File levelFile){}
    
    /**
     * Generates data from given level file
     */
    private void generate(){}
}
