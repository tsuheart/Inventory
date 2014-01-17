/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.synergytech.ims.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUserUsername", query = "SELECT u FROM User u WHERE u.userUsername = :userUsername"),
    @NamedQuery(name = "User.findByUserUserpassword", query = "SELECT u FROM User u WHERE u.userUserpassword = :userUserpassword"),
    @NamedQuery(name = "User.findByUserFirstname", query = "SELECT u FROM User u WHERE u.userFirstname = :userFirstname"),
    @NamedQuery(name = "User.findByUserLastname", query = "SELECT u FROM User u WHERE u.userLastname = :userLastname"),
    @NamedQuery(name = "User.findByUserAddress", query = "SELECT u FROM User u WHERE u.userAddress = :userAddress"),
    @NamedQuery(name = "User.findByUserContact", query = "SELECT u FROM User u WHERE u.userContact = :userContact"),
    @NamedQuery(name = "User.findByUserStatus", query = "SELECT u FROM User u WHERE u.userStatus = :userStatus")})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "user_username")
    private String userUsername;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "user_userpassword")
    private String userUserpassword;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "user_firstname")
    private String userFirstname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "user_lastname")
    private String userLastname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "user_address")
    private String userAddress;
    @Size(max = 20)
    @Column(name = "user_contact")
    private String userContact;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "user_status")
    private String userStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "storeoutCreatedby")
    private Collection<Storeout> storeoutCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "storeinCreatedby")
    private Collection<Storein> storeinCollection;
    @JoinColumn(name = "user_office_officeid", referencedColumnName = "office_officeid")
    @ManyToOne(optional = false)
    private Office userOfficeOfficeid;

    public User() {
    }

    public User(String userUsername) {
        this.userUsername = userUsername;
    }

    public User(String userUsername, String userUserpassword, String userFirstname, String userLastname, String userAddress, String userStatus) {
        this.userUsername = userUsername;
        this.userUserpassword = userUserpassword;
        this.userFirstname = userFirstname;
        this.userLastname = userLastname;
        this.userAddress = userAddress;
        this.userStatus = userStatus;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    public String getUserUserpassword() {
        return userUserpassword;
    }

    public void setUserUserpassword(String userUserpassword) {
        this.userUserpassword = userUserpassword;
    }

    public String getUserFirstname() {
        return userFirstname;
    }

    public void setUserFirstname(String userFirstname) {
        this.userFirstname = userFirstname;
    }

    public String getUserLastname() {
        return userLastname;
    }

    public void setUserLastname(String userLastname) {
        this.userLastname = userLastname;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserContact() {
        return userContact;
    }

    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    @XmlTransient
    public Collection<Storeout> getStoreoutCollection() {
        return storeoutCollection;
    }

    public void setStoreoutCollection(Collection<Storeout> storeoutCollection) {
        this.storeoutCollection = storeoutCollection;
    }

    @XmlTransient
    public Collection<Storein> getStoreinCollection() {
        return storeinCollection;
    }

    public void setStoreinCollection(Collection<Storein> storeinCollection) {
        this.storeinCollection = storeinCollection;
    }

    public Office getUserOfficeOfficeid() {
        return userOfficeOfficeid;
    }

    public void setUserOfficeOfficeid(Office userOfficeOfficeid) {
        this.userOfficeOfficeid = userOfficeOfficeid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userUsername != null ? userUsername.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.userUsername == null && other.userUsername != null) || (this.userUsername != null && !this.userUsername.equals(other.userUsername))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.synergytech.ims.entities.User[ userUsername=" + userUsername + " ]";
    }
    
}
