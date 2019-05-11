/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra.model.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Matěj Bartoň
 */
@Entity
@Table(name = "player")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Player.findAll", query = "SELECT p FROM Player p")
    , @NamedQuery(name = "Player.findByUid", query = "SELECT p FROM Player p WHERE p.uid = :uid")
    , @NamedQuery(name = "Player.findByConcertmaster", query = "SELECT p FROM Player p WHERE p.concertmaster = :concertmaster AND p.seid = :seid")})
    
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "uid")
    private Long uid;
    @Column(name = "concertmaster")
    private Boolean concertmaster;
    @JoinColumn(name = "seid", referencedColumnName = "seid")
    @ManyToOne(optional = false)
    private Sections seid;

    public Player() {
    }

    public Player(Long uid) {
        this.uid = uid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Boolean getConcertmaster() {
        return concertmaster;
    }

    public void setConcertmaster(Boolean concertmaster) {
        this.concertmaster = concertmaster;
    }

    public Sections getSeid() {
        return seid;
    }

    public void setSeid(Sections seid) {
        this.seid = seid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uid != null ? uid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Player)) {
            return false;
        }
        Player other = (Player) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Player[#" + uid + " --> " + seid + "]";
    }
    
}
