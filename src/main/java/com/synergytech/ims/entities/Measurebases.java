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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
 * @author Ujjwal
 */
@Entity
@Table(name = "measurebases")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Measurebases.findAll", query = "SELECT m FROM Measurebases m"),
    @NamedQuery(name = "Measurebases.findByMeasurebasesMeasureid", query = "SELECT m FROM Measurebases m WHERE m.measurebasesMeasureid = :measurebasesMeasureid"),
    @NamedQuery(name = "Measurebases.findByMeasurebasesName", query = "SELECT m FROM Measurebases m WHERE m.measurebasesName = :measurebasesName"),
//    @NamedQuery(name = "Measurebases.findByMeasurebasesUnit", query = "SELECT m FROM Measurebases m WHERE m.measurebasesUnit = :measurebasesUnit"),
    @NamedQuery(name = "Measurebases.findByMeasurebasesFactor", query = "SELECT m FROM Measurebases m WHERE m.measurebasesFactor = :measurebasesFactor"),
    @NamedQuery(name = "Measurebases.findByMeasurebasesHierarchy", query = "SELECT m FROM Measurebases m WHERE m.measurebasesHierarchy = :measurebasesHierarchy"),
    @NamedQuery(name = "Measurebases.findByMeasurebasesParentid", query = "SELECT m FROM Measurebases m WHERE m.measurebasesParentid= :measurebasesParentId"),
    @NamedQuery(name = "Measurebases.findByParentNullID", query = "SELECT m FROM Measurebases m WHERE m.measurebasesParentid IS NULL")})
public class Measurebases implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "measurebases_measureid")
    private Integer measurebasesMeasureid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "measurebases_name")
    private String measurebasesName;
//    @Size(max = 20)
//    @Column(name = "measurebases_unit")
//    private String measurebasesUnit;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "measurebases_factor")
    private Double measurebasesFactor;
    @Size(max = 2)
    @Column(name = "measurebases_hierarchy")
    private String measurebasesHierarchy;
    @OneToMany(mappedBy = "measurebasesParentid")
    private Collection<Measurebases> measurebasesCollection;
    @JoinColumn(name = "measurebases_parentid", referencedColumnName = "measurebases_measureid")
    @ManyToOne
    private Measurebases measurebasesParentid;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "itemMeasurebasesMeasureid", orphanRemoval = true)
    private Collection<Item> itemCollection;

    public Measurebases() {
    }

    public Measurebases(Integer measurebasesMeasureid) {
        this.measurebasesMeasureid = measurebasesMeasureid;
    }

    public Measurebases(Integer measurebasesMeasureid, String measurebasesName) {
        this.measurebasesMeasureid = measurebasesMeasureid;
        this.measurebasesName = measurebasesName;
    }

    public Integer getMeasurebasesMeasureid() {
        return measurebasesMeasureid;
    }

    public void setMeasurebasesMeasureid(Integer measurebasesMeasureid) {
        this.measurebasesMeasureid = measurebasesMeasureid;
    }

    public String getMeasurebasesName() {
        return measurebasesName;
    }

    public void setMeasurebasesName(String measurebasesName) {
        this.measurebasesName = measurebasesName;
    }

//    public String getMeasurebasesUnit() {
//        return measurebasesUnit;
//    }
//
//    public void setMeasurebasesUnit(String measurebasesUnit) {
//        this.measurebasesUnit = measurebasesUnit;
//    }

    public Double getMeasurebasesFactor() {
        return measurebasesFactor;
    }

    public void setMeasurebasesFactor(Double measurebasesFactor) {
        this.measurebasesFactor = measurebasesFactor;
    }

    public String getMeasurebasesHierarchy() {
        return measurebasesHierarchy;
    }

    public void setMeasurebasesHierarchy(String measurebasesHierarchy) {
        this.measurebasesHierarchy = measurebasesHierarchy;
    }

    @XmlTransient
    public Collection<Measurebases> getMeasurebasesCollection() {
        return measurebasesCollection;
    }

    public void setMeasurebasesCollection(Collection<Measurebases> measurebasesCollection) {
        this.measurebasesCollection = measurebasesCollection;
    }

    public Measurebases getMeasurebasesParentid() {
        return measurebasesParentid;
    }

    public void setMeasurebasesParentid(Measurebases measurebasesParentid) {
        this.measurebasesParentid = measurebasesParentid;
    }

    @XmlTransient
    public Collection<Item> getItemCollection() {
        return itemCollection;
    }

    public void setItemCollection(Collection<Item> itemCollection) {
        this.itemCollection = itemCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (measurebasesMeasureid != null ? measurebasesMeasureid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Measurebases)) {
            return false;
        }
        Measurebases other = (Measurebases) object;
        if ((this.measurebasesMeasureid == null && other.measurebasesMeasureid != null) || (this.measurebasesMeasureid != null && !this.measurebasesMeasureid.equals(other.measurebasesMeasureid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return measurebasesName;
    }
    
}
