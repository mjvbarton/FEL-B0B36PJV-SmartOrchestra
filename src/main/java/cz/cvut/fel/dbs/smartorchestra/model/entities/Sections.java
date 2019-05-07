/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra.model.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Matěj Bartoň
 */
@Entity
@Table(name = "sections")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sections.findAll", query = "SELECT s FROM Sections s")
    , @NamedQuery(name = "Sections.findBySeid", query = "SELECT s FROM Sections s WHERE s.seid = :seid")
    , @NamedQuery(name = "Sections.findBySectionname", query = "SELECT s FROM Sections s WHERE s.sectionname = :sectionname")
    , @NamedQuery(name = "Sections.findBySectiontype", query = "SELECT s FROM Sections s WHERE s.sectiontype = :sectiontype")
    , @NamedQuery(name = "Sections.findByAktivni", query = "SELECT s FROM Sections s WHERE s.aktivni = :aktivni")})
public class Sections implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "seid")
    private Integer seid;
    @Basic(optional = false)
    @Column(name = "sectionname")
    private String sectionname;
    @Basic(optional = false)
    @Column(name = "sectiontype")
    private String sectiontype;
    @Basic(optional = false)
    @Column(name = "aktivni")
    private boolean aktivni;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seid")
    private Collection<Player> playerCollection;

    public Sections() {
    }

    public Sections(Integer seid) {
        this.seid = seid;
    }

    public Sections(Integer seid, String sectionname, String sectiontype, boolean aktivni) {
        this.seid = seid;
        this.sectionname = sectionname;
        this.sectiontype = sectiontype;
        this.aktivni = aktivni;
    }

    public Integer getSeid() {
        return seid;
    }

    public void setSeid(Integer seid) {
        this.seid = seid;
    }

    public String getSectionname() {
        return sectionname;
    }

    public void setSectionname(String sectionname) {
        this.sectionname = sectionname;
    }

    public String getSectiontype() {
        return sectiontype;
    }

    public void setSectiontype(String sectiontype) {
        this.sectiontype = sectiontype;
    }

    public boolean getAktivni() {
        return aktivni;
    }

    public void setAktivni(boolean aktivni) {
        this.aktivni = aktivni;
    }

    @XmlTransient
    public Collection<Player> getPlayerCollection() {
        return playerCollection;
    }

    public void setPlayerCollection(Collection<Player> playerCollection) {
        this.playerCollection = playerCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seid != null ? seid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sections)) {
            return false;
        }
        Sections other = (Sections) object;
        if ((this.seid == null && other.seid != null) || (this.seid != null && !this.seid.equals(other.seid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cz.cvut.fel.dbs.smartorchestra.model.entities.Sections[ seid=" + seid + " ]";
    }
    
}
