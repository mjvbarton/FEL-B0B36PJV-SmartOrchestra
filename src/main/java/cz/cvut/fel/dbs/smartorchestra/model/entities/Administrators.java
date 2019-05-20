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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class represents the administrators entity in the database.
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
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
    
    /**
     * Creates new entity.
     */
    public Administrators() {
    }

    /**
     * Creates new entity with given uid.
     * @param uid - {@code Long} number
     */
    public Administrators(Long uid) {
        this.uid = uid;
    }
    
    /**
     * Gets the uid.
     * @return uid of the administrator
     */
    public Long getUid() {
        return uid;
    }
    
    /**
     * Sets the given uid to entity
     * @param uid - {@code Long} number
     */
    public synchronized void setUid(Long uid) {
        this.uid = uid;
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
        if (!(object instanceof Administrators)) {
            return false;
        }
        Administrators other = (Administrators) object;
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
        return "cz.cvut.fel.dbs.smartorchestra.model.entities.Administrators[ uid=" + uid + " ]";
    }
    
}
