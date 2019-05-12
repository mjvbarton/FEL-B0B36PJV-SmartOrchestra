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
        STRINGS(0, "smyčce"),
        WINDS(1, "dechy"),
        OTHER(2, "ostatní");
        
        private List<Sections> sections;
        private final int index;
        private final String name;
        
        private SectionType(int index, String name){
            this.index = index;
            this.name = name;
        }

    public List<Sections> getSections() {
        return sections;
    }

    public void setSections(List<Sections> sections) {
        this.sections = sections;
    }
                
    @Override
    public String toString() {
        return name;
    }       
}
