/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra.model.entities;

import java.util.List;

/**
 * Represents section type of the {@link Sections} entity.
 * There are three common types: <ul>
 *      <li> {@code STRINGS} for section with string instruments </li>
 *      <li> {@code WINDS} for section with wind instruments </li>
 *      <li> {@code OTHER} for section with the uncategorised instruments </li></ul>
 * The enum itself can store a {@code List} of {@link Sections} entities for each type.
 * These values <b>must be loaded</b> by {@link #setSections(java.util.List) } before the usage.
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
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
    
    /**
     * Gets the list of sections which belongs to this type of enum.
     * @return {@code List} of {@link Sections}
     */
    public List<Sections> getSections() {
        return sections;
    }
    
    /**
     * Sets the list of sections which belongs to this type of enum.
     * @param sections {@code List} of {@link Sections}
     */
    public void setSections(List<Sections> sections) {
        this.sections = sections;
    }
    
    /**
     * Returns name for database when the default string conversion is called
     * @return database name of enum
     */
    @Override
    public String toString() {
        return dbName;
    }
    /**
     * Shows application name of the enum if the given parameter is {@code true}, otherwise it works as default string conversion.
     * @param showAppName {@code Boolean} flag
     * @return database or application name based on the flag
     */
    public String toString(boolean showAppName){
        return showAppName ? appName : toString();
    }
}
