/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.EntityManager;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juliocisneros
 */
@Entity
@Table(name = "NVC_TBL_PROYECTOS_H")
@XmlRootElement
@NamedQueries({
    @NamedQuery(
            name = "NvcTblProyectosH.findByIdProyecto",
            query = "SELECT n FROM NvcTblProyectosH n WHERE n.idProyecto = :id"
    )
})
public class NvcTblProyectosH implements Serializable {
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PROYECTO", nullable = false)
    private Long idProyecto;
    
    @Size(max = 90)
    @Column(name = "NOMBRE_PROYECTO", length = 90)
    private String nombreProyecto;
    
    @Size(max = 15)
    @Column(name = "COD_PROYECTO", length = 15)
    private String codProyecto;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_UEN", nullable = false)
    private long idUen;
    
    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    
    @Column(name = "COMPLETION_DATE")
    @Temporal(TemporalType.DATE)
    private Date completionDate;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "PROJECT_TYPE", nullable = false, length = 60)
    
    private String projectType;
    @Column(name = "ACTIVE")
    private BigInteger active;

    public NvcTblProyectosH() {
    }

    public NvcTblProyectosH(Long idProyecto) {
        this.idProyecto = idProyecto;
    }

    public NvcTblProyectosH(Long idProyecto, long idUen, String projectType) {
        this.idProyecto = idProyecto;
        this.idUen = idUen;
        this.projectType = projectType;
    }

    public Long getIdProyecto() {
        return idProyecto;
    }

    public void setId(Long idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getCodProyecto() {
        return codProyecto;
    }

    public void setCodProyecto(String codProyecto) {
        this.codProyecto = codProyecto;
    }

    public long getIdUen() {
        return idUen;
    }

    public void setIdUen(long idUen) {
        this.idUen = idUen;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public BigInteger getActive() {
        return active;
    }

    public void setActive(BigInteger active) {
        this.active = active;
    }

//    @XmlTransient
//    public List<CarroDeCompra> getCarroDeCompraList() {
//        return carroDeCompraList;
//    }
//
//    public void setCarroDeCompraList(List<CarroDeCompra> carroDeCompraList) {
//        this.carroDeCompraList = carroDeCompraList;
//    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProyecto != null ? idProyecto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcTblProyectosH)) {
            return false;
        }
        NvcTblProyectosH other = (NvcTblProyectosH) object;
        if ((this.idProyecto == null && other.idProyecto != null) || (this.idProyecto != null && !this.idProyecto.equals(other.idProyecto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.NvcTblProyectosH[ idProyecto=" + idProyecto + " ]";
    }
    
    public static NvcTblProyectosH byId(Long id, EntityManager em) {
        return em.createNamedQuery("NvcTblProyectosH.findByIdProyecto", NvcTblProyectosH.class)
                .setParameter("id", id)
                .getSingleResult();
    }

}
