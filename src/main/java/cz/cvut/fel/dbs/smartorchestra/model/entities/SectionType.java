/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra.model.entities;

import java.util.List;

/**
 *
 * @author Matěj Bartoň
 */
public enum SectionType{
        STRINGS(0, "STRINGS", "smyčce"),
        WINDS(1, "WINDS", "dechy"),
        OTHER(2, "OTHER", "ostatní");
        
        private List<Sections> sections;
        private final int index;
        private final String dbName;
        private final String appName;
        
        private SectionType(int index, String dbName, String appName){
            this.index = index;
            this.dbName = dbName;
            this.appName = appName;
        }

    public List<Sections> getSections() {
        return sections;
    }

    public void setSections(List<Sections> sections) {
        this.sections = sections;
    }
                
    @Override
    public String toString() {
        return dbName;
    }
    
    public String toString(boolean showAppName){
        return showAppName ? appName : toString();
    }
}
