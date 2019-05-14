/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra.model.dao;

import cz.cvut.fel.dbs.smartorchestra.model.entities.Player;

/**
 *
 * @author Matěj Bartoň
 */
public class PlayerReader extends DAO{
    public PlayerReader(){
        super();
    }
    public synchronized Player getPlayer(Long uid){
       return em.find(Player.class, uid);
    }
    
}
