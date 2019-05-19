/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 *
 * @author Matěj Bartoň
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

    public Events() {
        this.active = true;
    }

    public Events(Integer evid) {
        this.evid = evid;
        this.active = true;
    }

    public Events(Integer evid, String eventname, Date begins, Date ends, String addrtown) {
        this.evid = evid;
        this.eventname = eventname;
        this.begins = begins;
        this.ends = ends;
        this.addrtown = addrtown;
        this.active = true;
    }

    public Integer getEvid() {
        return evid;
    }

    public synchronized void setEvid(Integer evid) {
        this.evid = evid;
    }

    public String getEventname() {
        return eventname;
    }

    public synchronized void setEventname(String eventname) throws WrongInputException{
        if(eventname.isEmpty()){
            throw new WrongInputException("Nevyplnili jste toto pole");
        }
        this.eventname = eventname;
    }

    public Date getBegins() {
        return begins;
    }

    public synchronized void setBegins(Date begins){
        this.begins = begins;
    }
    
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

    public Date getEnds() {
        return ends;
    }

    public synchronized void setEnds(Date ends) {
        this.ends = ends;
    }
    
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

    public String getAddrinstitution() {
        return addrinstitution;
    }

    public synchronized void setAddrinstitution(String addrinstitution) throws WrongInputException {
        if(addrinstitution.isEmpty()){
            throw new WrongInputException("Nevyplnili jste toto pole");
        }
        this.addrinstitution = addrinstitution;
    }

    public String getAddrstreet() {
        return addrstreet;
    }

    public synchronized void setAddrstreet(String addrstreet) throws WrongInputException {
        if(addrstreet.isEmpty()){
            throw new WrongInputException("Nevyplnili jste toto pole");
        }
        this.addrstreet = addrstreet;
    }

    public String getAddrhousenumber() {
        return addrhousenumber;
    }

    public synchronized void setAddrhousenumber(String addrhousenumber) throws WrongInputException{
        if(addrhousenumber.isEmpty()){
            throw new WrongInputException("Nevyplnili jste toto pole");
        }
        this.addrhousenumber = addrhousenumber;
    }

    public String getAddrtown() {
        return addrtown;
    }

    public synchronized void setAddrtown(String addrtown) throws WrongInputException {
        if(addrtown.isEmpty()){
            throw new WrongInputException("Nevyplnili jste toto pole");
        }
        this.addrtown = addrtown;
    }

    public Integer getAddrzipcode() {
        return addrZipCode;
    }

    public synchronized void setAddrZipCode(Integer addrZipCode) throws WrongInputException {
        int zipCode = (int) addrZipCode;
        if(zipCode >= 10000 && zipCode <= 999999){
            this.addrZipCode = addrZipCode;
            return;
        }
        throw new WrongInputException("Špatný formát PSČ");
    }
    
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

    public Boolean getActive() {
        return active;
    }

    public synchronized void setActive(Boolean active) {
        this.active = active;
    }

    public Collection<Participants> getParticipantsCollection() {
        return participantsCollection;
    }

    public synchronized void setParticipantsCollection(Collection<Participants> participantsCollection) {
        this.participantsCollection = participantsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (evid != null ? evid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Events)) {
            return false;
        }
        Events other = (Events) object;
        if ((this.evid == null && other.evid != null) || (this.evid != null && !this.evid.equals(other.evid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cz.cvut.fel.dbs.smartorchestra.model.entities.Events[ evid=" + evid + " ]";
    }
    
}
