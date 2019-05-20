/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
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
 * This class represents the player entity from the database.
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
@Entity
@Table(name = "player")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Player.findAll", query = "SELECT p FROM Player p")
    , @NamedQuery(name = "Player.findByUid", query = "SELECT p FROM Player p WHERE p.uid = :uid")
    , @NamedQuery(name = "Player.findByConcertmaster", query = "SELECT p FROM Player p WHERE p.concertmaster = :concertmaster AND p.seid = :seid")
    , @NamedQuery(name = "Player.findBySeid", query = "SELECT p FROM Player p WHERE p.seid = :seid ORDER BY p.concertmaster, p.uid")
})    
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

    /**
     * Creates new entity
     */
    public Player() {
    }

    /**
     * Creates new entity with uid given.
     * @param uid {@code Long} number of user ID
     */
    public Player(Long uid) {
        this.uid = uid;
    }

    /**
     * Gets the user ID of the player.
     * @return user ID as {@code Long}
     */
    public Long getUid() {
        return uid;
    }

    /**
     * Sets the user ID of the player.
     * @param uid user ID as {@code Long}
     */
    public synchronized void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * Gets the concertMaster flag as {@code Boolean} object.
     * @return {@code null} if the player is not a concertmaster, {@code false} if the player is
     * the second concert master and {@code true} if the player is the concert master
     */
    public Boolean getConcertmaster() {
        return concertmaster;
    }

    /**
     * Sets the concertMaster flag as {@code Boolean} object.
     * @param concertmaster {@code Booelan} object, shall be {@code null} if the player is not a concertmaster, {@code false} if the player is
     * the second concert master and {@code true} if the player is the concert master
     */
    public synchronized void setConcertmaster(Boolean concertmaster) {
        this.concertmaster = concertmaster;
    }

    /**
     * Gets the related {@link Sections} entity of the player
     * @return a {@link Sections} entity
     */
    public Sections getSeid() {
        return seid;
    }

    /**
     * Sets the related {@link Sections} entity of the player
     * @parm seid {@link Sections} entity
     */
    public synchronized void setSeid(Sections seid) {
        this.seid = seid;
    }
    
    /**
     * Creates hashCode for the object
     * @return {@code int} as hashCode
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uid != null ? uid.hashCode() : 0);
        return hash;
    }

    /**
     * Comparable method of the object
     * @param object
     * @return {@code boolean} value according to the {@link Comparable} interface
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Player)) {
            return false;
        }
        Player other = (Player) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }
    
    /**
     * Converts entity to {@code String}
     * @return a {@code String} object
     */
    @Override
    public String toString() {
        return "Player[#" + uid + " --> " + seid + "]";
    }
    
}
