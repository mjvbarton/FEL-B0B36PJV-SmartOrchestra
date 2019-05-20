/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra.model.dao;

import cz.cvut.fel.dbs.smartorchestra.model.entities.SectionType;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Sections;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NoResultException;


/**
 * Class for writing information for {@link Sections} entities from the database.
 * <b>WARNING: This class cannot be used in multiple threads simultaneously!</b>
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
public class SectionReader extends DAO{   
    /**
     * Creates new SectionReader
     */
    public SectionReader() {
        super();
    }
    
    /**
     * Gets all active sections from the database
     * @return a {@code List} of {@link Sections} entities.
     */
    public List<Sections> getActiveSections(){
        return em.createQuery("SELECT s FROM Sections s WHERE s.aktivni = TRUE ORDER BY s.sectiontype, s.seid").getResultList();        
    }
    
    /**
     * Gets all active sections of given type from the database.
     * @param type - a {@link SectionType} enum value
     * @return a {@code List} of {@link Sections} entities.
     */
    public List<Sections> getSectionsBySectionType(SectionType type){
        try{
            return em.createNamedQuery("Sections.findActiveBySectiontype", Sections.class)
                    .setParameter("sectiontype", type).getResultList();
        } catch(NoResultException ex){
            Logger.getLogger(SectionReader.class.getName()).log(Level.WARNING, "No sections found for section type: {0}", type);
            return new ArrayList();
            
        } catch(Exception ex){
            Logger.getLogger(SectionReader.class.getName()).log(Level.SEVERE, "Unable to read sections for section type: " + type, ex);
            throw ex;
        }
    }
}
