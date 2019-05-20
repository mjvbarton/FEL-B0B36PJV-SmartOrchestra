/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra.model;

import cz.cvut.fel.dbs.smartorchestra.exceptions.NotAPlayerException;
import cz.cvut.fel.dbs.smartorchestra.exceptions.PlayerManagerException;
import cz.cvut.fel.dbs.smartorchestra.model.dao.PlayerReader;
import cz.cvut.fel.dbs.smartorchestra.model.dao.PlayerWriter;
import cz.cvut.fel.dbs.smartorchestra.model.dao.SectionReader;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Player;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Sections;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Users;
import java.util.HashMap;
import java.util.List;

/**
 * This is model for accessing {@link Player} and {@link Sections} entities and the database.
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
public class PlayerManager {
    private List<Sections> activeSections;
    private PlayerWriter pw;
    private PlayerReader pr;
    
    /**
     * Creates new PlayerManager
     * @throws PlayerManagerException if there was no section found in the database
     */
    public PlayerManager() throws PlayerManagerException{
        SectionReader sr = new SectionReader();
        pw = new PlayerWriter();
        pr = new PlayerReader();
        // Loading sections from the database
        activeSections = sr.getActiveSections();
        if(activeSections.isEmpty()){
            throw new PlayerManagerException("Nebyla nalezena žádná sekce");
        }
    }

    /**
     * Gets the active sections.
     * @return a {@code List} of {@link Sections} entities
     */
    public List<Sections> getActiveSections() {
        return activeSections;
    }
    
    /**
     * Gets the index of player's section in activeSections for given player.
     * @param player - a {@link Player} entity
     * @return {@code int} value of player's section index in {@code List} sections
     * @throws PlayerManagerException if the section was not found
     */
    public int getIndexOfPlayerSection(Player player) throws PlayerManagerException{
        int ret = activeSections.indexOf(player.getSeid());
        if(ret == -1){
            throw new PlayerManagerException("Hledaná sekce nebyla nalezena");
        }
        return ret;
    }
    
    /**
     * Gets player entity for user entity given.
     * @param user - a {@link Users} entity
     * @return {@link Player} entity from the dataabase
     * @throws NotAPlayerException the user is not a player
     */
    public Player getPlayerInfo(Users user) throws NotAPlayerException{
        Player player = pr.getPlayer(user.getUid());
        if(player == null){
            throw new NotAPlayerException("Zadaný uživatel není hráčem");
        }
        return player;
    }

    /**
     * Updates player entity given to the database.
     * @param player - a {@link Player} entity
     * @return {@Player} entity from the database
     */
    public Player updatePlayerInfo(Player player){
        pw.write(player);
        return player;
    }

    /**
     * Creates new player relation in the database for user entity given.
     * @param user - a {@link Users} entity
     * @param activeSectionsIndex - a {@code int} value of index in {@code PlayerManagaer.activeSections}
     * @param concertMasterFlag - a {@code Boolean} flag if the user will become concert master
     * @return new {@link Player} entity
     */
    public Player createNewPlayer(Users user, int activeSectionsIndex, Boolean concertMasterFlag) {
        Player player = new Player();
        player.setUid(user.getUid());
        player.setConcertmaster(concertMasterFlag);
        player.setSeid(activeSections.get(activeSectionsIndex));
        pw.write(player);
        return player;
    }
    
    /**
     * Removes player entity from database for user entity given.
     * @param user - a {@link Users} entity
     * @return the removed {@link Player} entity
     * @throws NotAPlayerException when given user is not a player
     */
    public Player removePlayer(Users user) throws NotAPlayerException{
        Player player = pr.getPlayer(user.getUid());
        if(player == null){
            throw new NotAPlayerException("Zadaný uživatel není hráčem");
        }
        pw.remove(player);
        return player;
    }
    
    /**
     * Gets HashMap where {@code Users.uid} is the key and {@link Players} entity is the value.
     * In fact it is a 'hash-mapped' query.
     * @return 
     */
    public HashMap<Long, Player> getPlayers() {
        List<Player> players = pr.getPlayers();
        HashMap<Long, Player> map = new HashMap();
        if(players.isEmpty()){
            return map;
        }
        for(Player player : players){
            map.put(player.getUid(), player);
        }
        return map;
    }    
}
