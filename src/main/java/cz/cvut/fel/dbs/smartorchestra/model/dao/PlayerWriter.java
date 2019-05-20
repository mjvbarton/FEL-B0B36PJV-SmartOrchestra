/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra.model.dao;

import cz.cvut.fel.dbs.smartorchestra.model.entities.Player;
import javax.persistence.NoResultException;


/**
 * Class for writing information for {@link Player} entities from the database.
 * <b>WARNING: This class cannot be used in multiple threads simultaneously!</b>
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
public class PlayerWriter extends DAO{
    
    /**
     * Creates new PlayerWriter
     */
    public PlayerWriter() {
        super();
    }
    
    /**
     * Updates/creates a given {@link Player} entity to the database
     * @param player - a {@link Player} entity
     */
    public void write(Player player){
       em.getTransaction().begin();
       if(player.getConcertmaster() != null){
           try{
               Player concertMaster = em.createNamedQuery("Player.findByConcertmaster", Player.class)
                   .setParameter("seid", player.getSeid())
                   .setParameter("concertmaster", player.getConcertmaster()).getSingleResult();
                concertMaster.setConcertmaster(null);
                em.persist(concertMaster);
           } catch(NoResultException exp){
               
           }
       }
       em.persist(player);
       em.getTransaction().commit();
       
    }
    
    /**
     * Removes given player from the database.
     * @param player - a {@link Player} entity
     */
    public synchronized void remove(Player player) {
        em.getTransaction().begin();
        em.remove(player);
        em.getTransaction().commit();
    }
    
}
