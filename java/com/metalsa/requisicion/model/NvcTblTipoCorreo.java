/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.requisicion.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
 * @author APOJA5585
 */
@Entity
@Table(name = "NVC_TBL_TIPO_CORREO", catalog = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NvcTblTipoCorreo.findByDescripcion", query = "SELECT n FROM NvcTblTipoCorreo n WHERE n.descripcion = :descripcion")})
public class NvcTblTipoCorreo implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoCorreo")
    private Collection<NvcTblCorreo> nvcTblCorreoCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TIPO_CORREO")
    private Integer idTipoCorreo;
    @Size(max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;

    public NvcTblTipoCorreo() {
    }

    public NvcTblTipoCorreo(Integer idTipoCorreo) {
        this.idTipoCorreo = idTipoCorreo;
    }

    public Integer getIdTipoCorreo() {
        return idTipoCorreo;
    }

    public void setIdTipoCorreo(Integer idTipoCorreo) {
        this.idTipoCorreo = idTipoCorreo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoCorreo != null ? idTipoCorreo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcTblTipoCorreo)) {
            return false;
        }
        NvcTblTipoCorreo other = (NvcTblTipoCorreo) object;
        if ((this.idTipoCorreo == null && other.idTipoCorreo != null) || (this.idTipoCorreo != null && !this.idTipoCorreo.equals(other.idTipoCorreo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.cotizaciones.NvcTblTipoCorreo[ idTipoCorreo=" + idTipoCorreo + " ]";
    }

    @XmlTransient
    public Collection<NvcTblCorreo> getNvcTblCorreoCollection() {
        return nvcTblCorreoCollection;
    }

    public void setNvcTblCorreoCollection(Collection<NvcTblCorreo> nvcTblCorreoCollection) {
        this.nvcTblCorreoCollection = nvcTblCorreoCollection;
    }

}
