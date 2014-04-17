/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cs5200.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Matt
 */
@Entity
@Table(name = "tower")
@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Tower implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    @XmlAttribute
    private Integer id;
    
    @Column(name = "name")
    @XmlAttribute
    private String name;
    
    @Column(name = "height")
    @XmlAttribute
    private Double height;
    
    @Column(name = "sides")
    @XmlAttribute
    private Integer sides;
    
    @JoinColumn(name = "siteId", referencedColumnName = "id")
    @ManyToOne
    @XmlTransient
    private Site siteId;
    
    @OneToMany(mappedBy = "towerId")
    @XmlElement(name = "equipment")
    private List<Equipment> equipmentList;

    public Tower() {
    }

    public Tower(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Integer getSides() {
        return sides;
    }

    public void setSides(Integer sides) {
        this.sides = sides;
    }

    public Site getSiteId() {
        return siteId;
    }

    public void setSiteId(Site siteId) {
        this.siteId = siteId;
    }

    @XmlTransient
    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<Equipment> equipmentList) {
        this.equipmentList = equipmentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tower)) {
            return false;
        }
        Tower other = (Tower) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cs5200.entity.Tower[ id=" + id + " ]";
    }
    
}
