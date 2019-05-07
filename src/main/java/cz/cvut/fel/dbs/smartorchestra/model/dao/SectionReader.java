/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra.model.dao;

import cz.cvut.fel.dbs.smartorchestra.model.entities.Sections;
import java.util.List;


/**
 *
 * @author Matěj Bartoň
 */
public class SectionReader extends DAO{   
    public SectionReader() {
        super();
    }
    
    public List<Sections> getActiveSections(){
        return em.createQuery("SELECT s FROM Sections s WHERE s.aktivni = TRUE ORDER BY s.sectiontype, s.seid").getResultList();        
    }
}
