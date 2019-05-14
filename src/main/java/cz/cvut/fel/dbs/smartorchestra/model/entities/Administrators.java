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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Matěj Bartoň
 */
@Entity
@Table(name = "administrators")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Administrators.findAll", query = "SELECT a FROM Administrators a")
    , @NamedQuery(name = "Administrators.findByUid", query = "SELECT a FROM Administrators a WHERE a.uid = :uid")})
public class Administrators implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "uid")
    private Long uid;

    public Administrators() {
    }

    public Administrators(Long uid) {
        this.uid = uid;
    }

    public Long getUid() {
        return uid;
    }

    public synchronized void setUid(Long uid) {
        this.uid = uid;
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
        if (!(object instanceof Administrators)) {
            return false;
        }
        Administrators other = (Administrators) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cz.cvut.fel.dbs.smartorchestra.model.entities.Administrators[ uid=" + uid + " ]";
    }
    
}
