/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra.model.dao;

import cz.cvut.fel.dbs.smartorchestra.model.entities.Events;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author Matěj Bartoň
 */
public class EventHandler extends DAO {

    public void saveEvent(Events event) {
        try{
            em.getTransaction().begin();
            em.persist(event);
            em.getTransaction().commit();
        } catch(Exception ex){
            em.getTransaction().rollback();
            throw ex;
        }
    }  

    public List<Events> getActiveEvents() throws NoResultException, Exception{
        Date now = new Date();
        return em.createQuery("SELECT e FROM Events e WHERE e.begins > :now AND e.active = TRUE ORDER BY e.begins, e.eventname", Events.class)
                .setParameter("now", now).getResultList();
    }
}
