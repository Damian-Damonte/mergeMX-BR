/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.metalsa.core.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.EntityManager;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author APOPH5586
 */
@Entity
@Table(name = "NVC_TBL_OA_TAREAS_H", catalog = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NvcTblOaTareasH.findByIdTarea", query = "SELECT n FROM NvcTblOaTareasH n WHERE n.id = :idTarea")
})
public class NvcTblOaTareasH implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TAREA", nullable = false)
    private Long id;
    @Size(max = 75)
    @Column(name = "COD_TAREA", length = 75)
    private String codTarea;
    @Size(max = 60)
    @Column(name = "NOMBRE_TAREA", length = 60)
    private String nombreTarea;
    @Size(max = 120)
    @Column(name = "DESC_TAREA", length = 120)
    private String descTarea;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.DATE)
    private Date lastUpdateDate;
    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "COMPLETION_DATE")
    @Temporal(TemporalType.DATE)
    private Date completionDate;
    @JoinTable(name = "NVC_TBL_OA_PROYECTO_TAREA_H", joinColumns = {
        @JoinColumn(name = "ID_TAREA", referencedColumnName = "ID_TAREA", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "ID_PROYECTO", referencedColumnName = "ID_PROYECTO", nullable = false)})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<NvcTblProyectosH> nvcTblProyectosHList;

    public NvcTblOaTareasH() {
    }

    public NvcTblOaTareasH(Long idTarea) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setIdTarea(Long id) {
        this.id = id;
    }

    public String getCodTarea() {
        return codTarea;
    }

    public void setCodTarea(String codTarea) {
        this.codTarea = codTarea;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    public String getDescTarea() {
        return descTarea;
    }

    public void setDescTarea(String descTarea) {
        this.descTarea = descTarea;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
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

    @XmlTransient
    public List<NvcTblProyectosH> getNvcTblProyectosHList() {
        return nvcTblProyectosHList;
    }

    public void setNvcTblProyectosHList(List<NvcTblProyectosH> nvcTblProyectosHList) {
        this.nvcTblProyectosHList = nvcTblProyectosHList;
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
        if (!(object instanceof NvcTblOaTareasH)) {
            return false;
        }
        NvcTblOaTareasH other = (NvcTblOaTareasH) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.suite.NvcTblOaTareasH[ id=" + id + " ]";
    }
    
    public static NvcTblOaTareasH byId(Long id, EntityManager em) {
        return em.createNamedQuery("NvcTblOaTareasH.findByIdTarea", NvcTblOaTareasH.class)
                .setParameter("idTarea", id)
                .getSingleResult();
    }

}
