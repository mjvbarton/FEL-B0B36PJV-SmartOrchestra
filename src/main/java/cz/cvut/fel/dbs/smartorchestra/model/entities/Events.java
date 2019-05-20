/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra.model.entities;

import cz.cvut.fel.dbs.smartorchestra.exceptions.WrongInputException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * This class represents entity of Event in the database.
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
@Entity
@Table(name = "events")
@NamedQueries({
    @NamedQuery(name = "Events.findAll", query = "SELECT e FROM Events e")
    , @NamedQuery(name = "Events.findByEvid", query = "SELECT e FROM Events e WHERE e.evid = :evid")
    , @NamedQuery(name = "Events.findByEvidList", query = "SELECT e FROM Events e WHERE e.evid IN :evids")    
})
public class Events implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "evid")
    private Integer evid;
    @Basic(optional = false)
    @Column(name = "eventname")
    private String eventname;
    @Basic(optional = false)
    @Column(name = "begins")
    @Temporal(TemporalType.TIMESTAMP)
    private Date begins;
    @Basic(optional = false)
    @Column(name = "ends")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ends;
    @Column(name = "addrinstition")
    private String addrinstitution;
    @Column(name = "addrstreet")
    private String addrstreet;
    @Column(name = "addrhousenumber")
    private String addrhousenumber;
    @Basic(optional = false)
    @Column(name = "addrtown")
    private String addrtown;
    @Column(name = "addrzipcode")
    private Integer addrZipCode;
    @Basic(optional = false)
    @Column(name = "active")
    private Boolean active;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "events")
    private Collection<Participants> participantsCollection;
    
    /**
     * Creates new Events with property active set to {@code true}
     */
    public Events() {
        this.active = true;
    }

    /**
     * Creates new Events with property active set to {@code true} and property evid set to evid given.
     * @param evid - an {@code Integer} number
     */
    public Events(Integer evid) {
        this.evid = evid;
        this.active = true;
    }
    
    /**
     * Gets the value of property {@code evid}
     * @return a {@code Integer} event ID
     */
    public Integer getEvid() {
        return evid;
    }
    
    /**
     * Sets the value of property {@code evid}
     * @param evid - an {@code Integer} number
     */
    public synchronized void setEvid(Integer evid) {
        this.evid = evid;
    }
    
    /**
     * Gets the value of property {@code eventname}
     * @return a {@code String} the name of the event
     */
    public String getEventname() {
        return eventname;
    }

    /**
     * Does the validation of value given and sets the value of property {@code eventname}.
     * @param eventname - a {@code String} of the event name
     * @throws WrongInputException if an empty string was given
     */
    public synchronized void setEventname(String eventname) throws WrongInputException{
        if(eventname.isEmpty()){
            throw new WrongInputException("Nevyplnili jste toto pole");
        }
        this.eventname = eventname;
    }

    /**
     * Gets the value of property {@code begins}
     * @return a {@code Date} the beginning date of the event
     */
    public Date getBegins() {
        return begins;
    }

    /**
     * Sets the value of property {@code begins}
     * @param begins - an {@code Date} object of the beginning date
     */
    public synchronized void setBegins(Date begins){
        this.begins = begins;
    }
    
    /**
     * Does the validation of value given and sets the value of property {@code begins}.
     * @param begins - a {@code String} of the event beginning date
     * @throws WrongInputException if an empty string was given or the string does not match date format 'dd.MM.yyyy HH:mm'.
     */
    public synchronized void setBegins(String begins) throws WrongInputException{
        if(begins.isEmpty()){
            throw new WrongInputException("Nevyplnili jste toto pole");
        }
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        try{
            setBegins(format.parse(begins));
            
        } catch(ParseException err){
            throw new WrongInputException("Špatný formát data");
        }
    }

    /**
     * Gets the value of property {@code ends}
     * @return a {@code Date} the ends date of the event
     */
    public Date getEnds() {
        return ends;
    }

    
    /**
     * Sets the value of property {@code ends}
     * @param ends - an {@code Date} object of the end date
     */
    public synchronized void setEnds(Date ends) {
        this.ends = ends;
    }
    
    /**
     * Does the validation of value given and sets the value of property {@code ends}.
     * @param ends - a {@code String} of the event beginning date
     * @throws WrongInputException if an empty string was given or the string does not match date format 'dd.MM.yyyy HH:mm'.
     */
    public synchronized void setEnds(String ends) throws WrongInputException{
        if(ends.isEmpty()){
            throw new WrongInputException("Nevyplnili jste toto pole");
        }
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        try{
            setEnds(format.parse(ends));
            
        } catch(ParseException err){
            throw new WrongInputException("Špatný formát data");
        }
    }

    /**
     * Gets the value of property {@code addrinstitution}
     * @return a {@code String} address name of the institution of the event (e. g. Royal Albert Hall)
     */
    public String getAddrinstitution() {
        return addrinstitution;
    }
    
    /**
     * Does the validation of value given and sets the value of property {@code addrinstitution}.
     * @param addrinstitution - a {@code String} of the name of the institution in the event address (e. g. Royal Albert Hall)
     * @throws WrongInputException if an empty string was given
     */
    public synchronized void setAddrinstitution(String addrinstitution) throws WrongInputException {
        if(addrinstitution.isEmpty()){
            throw new WrongInputException("Nevyplnili jste toto pole");
        }
        this.addrinstitution = addrinstitution;
    }

    /**
     * Gets the value of property {@code addrstreet}
     * @return a {@code String} address street of the event
     */
    public String getAddrstreet() {
        return addrstreet;
    }

    /**
     * Does the validation of value given and sets the value of property {@code addrstreet}.
     * @param addrstreet - a {@code String} of the address street of the event
     * @throws WrongInputException if an empty string was given
     */
    public synchronized void setAddrstreet(String addrstreet) throws WrongInputException {
        if(addrstreet.isEmpty()){
            throw new WrongInputException("Nevyplnili jste toto pole");
        }
        this.addrstreet = addrstreet;
    }

    /**
     * Gets the value of property {@code addrhousenumber}
     * @return a {@code String} address house number of the event
     */
    public String getAddrhousenumber() {
        return addrhousenumber;
    }
    
    /**
     * Does the validation of value given and sets the value of property {@code addrhousenumber}.
     * @param addrhousenumber - a {@code String} of address house number of the event
     * @throws WrongInputException if an empty string was given
     */
    public synchronized void setAddrhousenumber(String addrhousenumber) throws WrongInputException{
        if(addrhousenumber.isEmpty()){
            throw new WrongInputException("Nevyplnili jste toto pole");
        }
        this.addrhousenumber = addrhousenumber;
    }

    /**
     * Gets the value of property {@code addrtown}
     * @return a {@code String} address town of the event
     */
    public String getAddrtown() {
        return addrtown;
    }
    
    /**
     * Does the validation of value given and sets the value of property {@code addrtown}.
     * @param addrtown - a {@code String} of address town of the event
     * @throws WrongInputException if an empty string was given
     */
    public synchronized void setAddrtown(String addrtown) throws WrongInputException {
        if(addrtown.isEmpty()){
            throw new WrongInputException("Nevyplnili jste toto pole");
        }
        this.addrtown = addrtown;
    }

    /**
     * Gets the value of property {@code addrZipCode}
     * @return a {@code Integer} address zip code of the event
     */
    public Integer getAddrzipcode() {
        return addrZipCode;
    }
    
    /**
     * Does the validation of value given and sets the value of property {@code addrZipCode}.
     * @param addrZipCode - a {@code Integer} of address zip code of the event
     * @throws WrongInputException if the number is a member of interval [10000, 999999]
     */
    public synchronized void setAddrZipCode(Integer addrZipCode) throws WrongInputException {
        int zipCode = (int) addrZipCode;
        if(zipCode >= 10000 && zipCode <= 999999){
            this.addrZipCode = addrZipCode;
            return;
        }
        throw new WrongInputException("Špatný formát PSČ");
    }
    
    /**
     * Does the validation of value given and sets the value of property {@code eventname}.
     * @param addrZipCode - a {@code String} of address zip code of the event
     * @throws WrongInputException if an empty string was given or if the string cannot be converted to {@code Integer}
     */
    public synchronized void setAddrZipCode(String addrZipCode) throws WrongInputException{
        if(addrZipCode.isEmpty()){
            throw new WrongInputException("Nevyplnili jste toto pole");
        }
        try{
            Integer zipCode = Integer.parseInt(addrZipCode);
            setAddrZipCode(zipCode);
        } catch(NumberFormatException err){
            throw new WrongInputException("Špatný formát PSČ");
        }
                
    }

    /**
     * Gets the value of property {@code active}
     * @return a {@code Boolean} event is active
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * Sets the value of property {@code evid}
     * @param active - a {@code Boolean} value
     */
    public synchronized void setActive(Boolean active) {
        this.active = active;
    }
    
    /**
     * Creates hashCode for the object
     * @return {@code int} as hashCode
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (evid != null ? evid.hashCode() : 0);
        return hash;
    }

    /**
     * Comparable method of the object
     * @param object
     * @return {@code boolean} value according to the {@link Comparable} interface
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Events)) {
            return false;
        }
        Events other = (Events) object;
        if ((this.evid == null && other.evid != null) || (this.evid != null && !this.evid.equals(other.evid))) {
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
        return "cz.cvut.fel.dbs.smartorchestra.model.entities.Events[ evid=" + evid + " ]";
    }
    
}
