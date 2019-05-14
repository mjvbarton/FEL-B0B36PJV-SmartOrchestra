/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 *
 * @author Matěj Bartoň
 */
public class SectionReader extends DAO{   
    public SectionReader() {
        super();
    }
    
    public synchronized List<Sections> getActiveSections(){
        return em.createQuery("SELECT s FROM Sections s WHERE s.aktivni = TRUE ORDER BY s.sectiontype, s.seid").getResultList();        
    }
    
    public synchronized List<Sections> getSectionsBySectionType(SectionType type){
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
