/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 *
 * @author Matěj Bartoň
 */
@Entity
@Table(name = "participants")
@NamedQueries({
    @NamedQuery(name = "Participants.findAll", query = "SELECT p FROM Participants p")
    , @NamedQuery(name = "Participants.findByUid", query = "SELECT p FROM Participants p WHERE p.participantsPK.uid = :uid")
    , @NamedQuery(name = "Participants.findByEvid", query = "SELECT p FROM Participants p WHERE p.participantsPK.evid = :evid")
    , @NamedQuery(name = "Participants.findByMessage", query = "SELECT p FROM Participants p WHERE p.message = :message")
    , @NamedQuery(name = "Participants.findByActive", query = "SELECT p FROM Participants p WHERE p.active = :active")})
public class Participants implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ParticipantsPK participantsPK;
    @Column(name = "message")
    private String message;
    @Column(name = "active")
    private Boolean active;
    @JoinColumn(name = "evid", referencedColumnName = "evid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Events events;

    public Participants() {
    }

    public Participants(ParticipantsPK participantsPK) {
        this.participantsPK = participantsPK;
    }

    public Participants(int uid, int evid) {
        this.participantsPK = new ParticipantsPK(uid, evid);
    }

    public ParticipantsPK getParticipantsPK() {
        return participantsPK;
    }

    public void setParticipantsPK(ParticipantsPK participantsPK) {
        this.participantsPK = participantsPK;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Events getEvents() {
        return events;
    }

    public void setEvents(Events events) {
        this.events = events;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (participantsPK != null ? participantsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Participants)) {
            return false;
        }
        Participants other = (Participants) object;
        if ((this.participantsPK == null && other.participantsPK != null) || (this.participantsPK != null && !this.participantsPK.equals(other.participantsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cz.cvut.fel.dbs.smartorchestra.model.entities.Participants[ participantsPK=" + participantsPK + " ]";
    }
    
}
