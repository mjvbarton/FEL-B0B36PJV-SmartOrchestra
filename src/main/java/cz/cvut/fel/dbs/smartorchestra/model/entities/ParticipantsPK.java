/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra.model.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This class represents the multivalued primary key for {@link Participants}
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
@Embeddable
public class ParticipantsPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "uid")
    private int uid;
    @Basic(optional = false)
    @Column(name = "evid")
    private int evid;

    /**
     * Creates new ParticipantsPK
     */
    public ParticipantsPK() {
    }
    
    /**
     * Creates new ParticipantsPK with given {@link Users} and {@link Events} entities
     * @param user a {@link Users} entity
     * @param event a {@link Events} entity
     */
    public ParticipantsPK(Users user, Events event){
        this.uid = user.getUid().intValue();
        this.evid = event.getEvid();
    }

    /**
     * Creates new ParticipantsPK with given {@code int} user ID and {@code int} event ID
     * @param uid an {@code int} user ID
     * @param evid an {@code int} event ID
     */
    public ParticipantsPK(int uid, int evid) {
        this.uid = uid;
        this.evid = evid;
    }
    
    /**
     * Gets the user ID of the primary key.
     * @return user ID as {@code int}
     */
    public int getUid() {
        return uid;
    }

    /**
     * Sets the user ID of the primary key.
     * @param uid user ID as {@code int}
     */
    public void setUid(int uid) {
        this.uid = uid;
    }

    /**
     * Gets the event ID of the primary key.
     * @return event ID as {@code int}
     */
    public int getEvid() {
        return evid;
    }
    
    /**
     * Sets the event ID of the primary key.
     * @param evid event ID as {@code int}
     */
    public void setEvid(int evid) {
        this.evid = evid;
    }
    
    /**
     * Creates hashCode for the object
     * @return {@code int} as hashCode
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) uid;
        hash += (int) evid;
        return hash;
    }

    /**
     * Comparable method of the object
     * @param object
     * @return {@code boolean} value according to the {@link Comparable} interface
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ParticipantsPK)) {
            return false;
        }
        ParticipantsPK other = (ParticipantsPK) object;
        if (this.uid != other.uid) {
            return false;
        }
        if (this.evid != other.evid) {
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
        return "cz.cvut.fel.dbs.smartorchestra.model.entities.ParticipantsPK[ uid=" + uid + ", evid=" + evid + " ]";
    }
    
}
