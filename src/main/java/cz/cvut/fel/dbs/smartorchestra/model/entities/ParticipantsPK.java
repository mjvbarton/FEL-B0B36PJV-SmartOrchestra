/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra.model.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Matěj Bartoň
 */
@Embeddable
public class ParticipantsPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "uid")
    private int uid;
    @Basic(optional = false)
    @Column(name = "evid")
    private int evid;

    public ParticipantsPK() {
    }

    public ParticipantsPK(int uid, int evid) {
        this.uid = uid;
        this.evid = evid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getEvid() {
        return evid;
    }

    public void setEvid(int evid) {
        this.evid = evid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) uid;
        hash += (int) evid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
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

    @Override
    public String toString() {
        return "cz.cvut.fel.dbs.smartorchestra.model.entities.ParticipantsPK[ uid=" + uid + ", evid=" + evid + " ]";
    }
    
}
