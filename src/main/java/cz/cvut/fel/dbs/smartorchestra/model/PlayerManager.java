/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matěj Bartoň
 */
public class PlayerManager {
    private List<Sections> activeSections;
    private PlayerWriter pw;
    private PlayerReader pr;
    
    public PlayerManager() throws PlayerManagerException{
        SectionReader sr = new SectionReader();
        pw = new PlayerWriter();
        pr = new PlayerReader();
        activeSections = sr.getActiveSections();
        if(activeSections.isEmpty()){
            throw new PlayerManagerException("Nebyla nalezena žádná sekce");
        }
    }

    public List<Sections> getActiveSections() {
        return activeSections;
    }
    
    public int getIndexOfPlayerSection(Player player) throws PlayerManagerException{
        int ret = activeSections.indexOf(player.getSeid());
        if(ret == -1){
            throw new PlayerManagerException("Hledaná sekce nebyla nalezena");
        }
        return ret;
    }
    
    public Player getPlayerInfo(Users user) throws NotAPlayerException{
        Player player = pr.getPlayer(user.getUid());
        if(player == null){
            throw new NotAPlayerException("Zadaný uživatel není hráčem");
        }
        return player;
    }

    public Player updatePlayerInfo(Player player){
        return player;
    }

    public Player createNewPlayer(Users user, int activeSectionsIndex, Boolean concertMasterFlag) {
        Player player = new Player();
        player.setUid(user.getUid());
        player.setConcertmaster(concertMasterFlag);
        player.setSeid(activeSections.get(activeSectionsIndex));
        pw.write(player);
        return player;
    }
    
    public Player removePlayer(Users user) throws NotAPlayerException{
        Player player = pr.getPlayer(user.getUid());
        if(player == null){
            throw new NotAPlayerException("Zadaný uživatel není hráčem");
        }
        pw.remove(player);
        return player;
    }
    
    
}
