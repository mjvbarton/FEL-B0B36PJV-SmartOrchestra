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
 * Represents an entity Sections from the database.
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
    , @NamedQuery(name = "Sections.findActiveBySectiontype", query = "SELECT s FROM Sections s WHERE s.sectiontype = :sectiontype AND s.active = TRUE ORDER BY s.seid")
    , @NamedQuery(name = "Sections.findByActive", query = "SELECT s FROM Sections s WHERE s.active = :aktivni")})
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
    
    /**
     * Creates new entity.
     */
    public Sections() {
    }

    /**
     * Creates new entity with {@code seid} as section ID
     * @param seid {@code Integer} as section ID
     */
    public Sections(Integer seid) {
        this.seid = seid;
    }
    
    /**
     * Gets the primary key of the entity
     * @return section ID as {@code Integer}
     */
    public Integer getSeid() {
        return seid;
    }

    /**
     * Sets the primary key of the entity
     * @param seid section ID as {@code Integer}
     */
    public synchronized void setSeid(Integer seid) {
        this.seid = seid;
    }

    /**
     * Gets the section name
     * @return section name as {@code String}
     */
    public String getSectionname() {
        return sectionname;
    }

    /**
     * Gets the section name
     * @param sectionname section name as {@code String}
     */
    public synchronized void setSectionname(String sectionname) {
        this.sectionname = sectionname;
    }

    /**
     * Gets the type of the section
     * @return type of the section as {@link SectionType}
     */
    public SectionType getSectiontype() {
        return sectiontype;
    }

    /**
     * Sets the type of the section
     * @param sectiontype type of the section name {@link SectionType}
     */
    public synchronized void setSectiontype(SectionType sectiontype) {
        this.sectiontype = sectiontype;
    }

    /**
     * Gets the {@code Boolean} value whether the section is active.
     * @return section is active as {@code Boolean}
     */
    public boolean getActive() {
        return active;
    }

    /**
     * Sets the {@code Boolean} value whether the section is active.
     * @param active section is active as {@code Boolean}
     */
    public synchronized void setActive(boolean active) {
        this.active = active;
    }
    
    /**
     * Creates hashCode for the object
     * @return {@code int} as hashCode
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seid != null ? seid.hashCode() : 0);
        return hash;
    }

    /**
     * Comparable method of the object
     * @param object
     * @return {@code boolean} value according to the {@link Comparable} interface
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Sections)) {
            return false;
        }
        Sections other = (Sections) object;
        if ((this.seid == null && other.seid != null) || (this.seid != null && !this.seid.equals(other.seid))) {
            return false;
        }
        return true;
    }
    /**
     * Converts entity to {@code String}. Displaying the value of {@code Sections.sectionname}
     * @return a {@code String} object
     */
    @Override
    public String toString() {
        return sectionname;
    }
    
}
