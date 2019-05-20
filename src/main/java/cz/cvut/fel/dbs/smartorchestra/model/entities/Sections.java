/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra.model.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

/**
 *
 * @author Matěj Bartoň
 */
@Entity
@Table(name = "sections")
@TypeDef(
        name = "section_type",
        typeClass = PostgreSQLEnumType.class
)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sections.findAll", query = "SELECT s FROM Sections s")
    , @NamedQuery(name = "Sections.findBySeid", query = "SELECT s FROM Sections s WHERE s.seid = :seid")
    , @NamedQuery(name = "Sections.findBySectionname", query = "SELECT s FROM Sections s WHERE s.sectionname = :sectionname")
    , @NamedQuery(name = "Sections.findActiveBySectiontype", query = "SELECT s FROM Sections s WHERE s.sectiontype = :sectiontype AND s.aktivni = TRUE ORDER BY s.seid")
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
    @Enumerated(EnumType.STRING)
    @Column(name = "sectiontype")
    @Type(type = "section_type")
    private SectionType sectiontype;
    @Basic(optional = false)
    @Column(name = "aktivni")
    private boolean active;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seid")
    private Collection<Player> playerCollection;

    public Sections() {
    }

    public Sections(Integer seid) {
        this.seid = seid;
    }

    public Sections(Integer seid, String sectionname, SectionType sectiontype, boolean aktivni) {
        this.seid = seid;
        this.sectionname = sectionname;
        this.sectiontype = sectiontype;
        this.active = aktivni;
    }

    public Integer getSeid() {
        return seid;
    }

    public synchronized void setSeid(Integer seid) {
        this.seid = seid;
    }

    public String getSectionname() {
        return sectionname;
    }

    public synchronized void setSectionname(String sectionname) {
        this.sectionname = sectionname;
    }

    public SectionType getSectiontype() {
        return sectiontype;
    }

    public synchronized void setSectiontype(SectionType sectiontype) {
        this.sectiontype = sectiontype;
    }

    public boolean getActive() {
        return active;
    }

    public synchronized void setActive(boolean aktivni) {
        this.active = aktivni;
    }

    @XmlTransient
    public Collection<Player> getPlayerCollection() {
        return playerCollection;
    }

    public synchronized void setPlayerCollection(Collection<Player> playerCollection) {
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
        return sectionname;
    }
    
}
