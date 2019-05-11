/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra.model.dao;

import cz.cvut.fel.dbs.smartorchestra.model.entities.Player;
import javax.persistence.NoResultException;


/**
 *
 * @author Matěj Bartoň
 */
public class PlayerWriter extends DAO{

    public PlayerWriter() {
        super();
    }
    
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

    public void remove(Player player) {
        em.getTransaction().begin();
        em.remove(player);
        em.getTransaction().commit();
    }
    
}
