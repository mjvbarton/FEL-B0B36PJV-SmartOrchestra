/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra.model.dao;

import cz.cvut.fel.dbs.smartorchestra.model.entities.Player;
import java.util.List;

/**
 * Class for reading information about {@link Player} entities from the database.
 * <b>WARNING: This class cannot be used in multiple threads simultaneously!</b>
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
public class PlayerReader extends DAO{
    
    /**
     * Creates new PlayerReader
     */
    public PlayerReader(){
        super();
    }
    
    /**
     * Gets {@link Player} entity from the database base on its primary key {@code uid}
     * @param uid - {@code Long} identifier
     * @return a {@link Player} entity from the database
     */
    public synchronized Player getPlayer(Long uid){
       return em.find(Player.class, uid);
    }
    
    /**
     * Gets all players from the database.
     * @return a {@code List} of {@link Player} entities
     */
    public List<Player> getPlayers() {
        return em.createNamedQuery("Player.findAll").getResultList();
    }
}
