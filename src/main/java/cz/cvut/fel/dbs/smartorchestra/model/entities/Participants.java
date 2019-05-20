/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra.model.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * This class represents participants entity in the database.
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
@Entity
@Table(name = "participants")
@NamedQueries({
    @NamedQuery(name = "Participants.findAll", query = "SELECT p FROM Participants p")
    , @NamedQuery(name = "Participants.findByUid", query = "SELECT p FROM Participants p WHERE p.participantsPK.uid = :uid")
    , @NamedQuery(name = "Participants.findByEvid", query = "SELECT p FROM Participants p WHERE p.participantsPK.evid = :evid "
            + "ORDER BY p.users.familyName, p.users.firstName")
    , @NamedQuery(name = "Participants.getEvidsByUid", query = "SELECT p.participantsPK.evid FROM Participants p WHERE p.participantsPK.uid = :uid")
    , @NamedQuery(name = "Participants.getEvidsBySection", query = "SELECT DISTINCT p.participantsPK.evid FROM Participants p WHERE p.seid = :seid")
    , @NamedQuery(name = "Participants.findByMessage", query = "SELECT p FROM Participants p WHERE p.message = :message")
    , @NamedQuery(name = "Participants.findByActive", query = "SELECT p FROM Participants p WHERE p.active = :active")    
})
public class Participants implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ParticipantsPK participantsPK;
    @JoinColumn(name = "seid", referencedColumnName = "seid")
    @ManyToOne(optional = false)
    private Sections seid;
    @Column(name = "message")
    private String message;
    @Column(name = "active")
    private Boolean active;
    @JoinColumn(name = "evid", referencedColumnName = "evid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Events events;
    @JoinColumn(name = "uid", referencedColumnName = "uid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;    

    /**
     * Creates new entity.
     */
    public Participants() {
    }

    /**
     * Creates new entity with given primary key.
     * @param participantsPK - an instance of {@link ParticipantsPK}
     */
    public Participants(ParticipantsPK participantsPK) {
        this.participantsPK = participantsPK;
    }
    
    /**
     * Creates new entity with specific given values to the primary key.
     * @param uid {@code int} user ID (from {@link Users} entity)
     * @param evid {@code int} event ID (from {@link Events} entity)
     */
    public Participants(int uid, int evid) {
        this.participantsPK = new ParticipantsPK(uid, evid);
    }    

    /**
     * Gets the entity primary key
     * @return an instance of {@link ParticipantsPk}
     */
    public ParticipantsPK getParticipantsPK() {
        return participantsPK;
    }

    /**
     * Sets the entity primary key to the value given.
     * @param participantsPK an instance of {@link ParticipantsPK}
     */
    public synchronized void setParticipantsPK(ParticipantsPK participantsPK) {
        this.participantsPK = participantsPK;
    }

    /**
     * Gets the message of the participation. Used mainly for submitting the reason of not participating the event.
     * @return {@code String} message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message of the participation. Used mainly for submitting the reason of not participating the event.
     * @param message {@code String} the message
     */
    public synchronized void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the state of the participation. Follows rules described in {@link ParticipantState#toBoolean() } enum.
     * @return {@code Boolean} ParticipantState that need to be converted to {@link PartcipantState} enum value
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * Gets the state of the participation. Follows rules described in {@link ParticipantState#toBoolean() } enum.
     * @param active {@code Boolean} value from {@link ParticipantState}
     */
    public synchronized void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * Gets the related {@link Events} entity
     * @return an {@link Events} entity
     */
    public Events getEvents() {
        return events;
    }
    
    /**
     * Sets the related {@link Events} entity
     * @param events an {@link Events} entity
     */
    public synchronized void setEvents(Events events) {
        this.events = events;
    }

    /**
     * Gets the related {@link Sections} entity
     * @return a {@link Sections} entity
     */
    public Sections getSeid() {
        return seid;
    }

    /**
     * Sets the related {@link Sections} entity
     * @param seid an {@link Sections} entity
     */
    public synchronized void setSeid(Sections seid) {
        this.seid = seid;
    }

    /**
     * Gets the related {@link Users} entity
     * @return a {@link Users} entity
     */
    public Users getUsers() {
        return users;
    }

    /**
     * Sets the related {@link Users} entity
     * @param users an {@link Users} entity
     */
    public synchronized void setUsers(Users users) {
        this.users = users;
    }
    
    /**
     * Creates hashCode for the object
     * @return {@code int} as hashCode
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (participantsPK != null ? participantsPK.hashCode() : 0);
        return hash;
    }

    /**
     * Comparable method of the object
     * @param object
     * @return {@code boolean} value according to the {@link Comparable} interface
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Participants)) {
            return false;
        }
        Participants other = (Participants) object;
        if ((this.participantsPK == null && other.participantsPK != null) || (this.participantsPK != null && !this.participantsPK.equals(other.participantsPK))) {
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
        return "cz.cvut.fel.dbs.smartorchestra.model.entities.Participants[ participantsPK=" + participantsPK + " ]";
    }
    
}
