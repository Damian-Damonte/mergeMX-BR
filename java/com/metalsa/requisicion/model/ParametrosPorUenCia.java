/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.requisicion.model;

import com.metalsa.aprobacion.model.NvcTblOrganizacionesH;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author APOPH5586
 */
@Entity
@Table(name = "PARAMETROS_POR_UEN_CIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParametrosPorUenCia.findAll", query = "SELECT p FROM ParametrosPorUenCia p")
    ,
    @NamedQuery(name = "ParametrosPorUenCia.findByIdUen", query = "SELECT p FROM ParametrosPorUenCia p WHERE p.parametrosPorUenCiaPK.idUen = :idUen")
    ,
    @NamedQuery(name = "ParametrosPorUenCia.findByIdUenIdParametro", query = "SELECT p FROM ParametrosPorUenCia p WHERE p.parametrosPorUenCiaPK.idUen = :idUen "
            + "and p.parametrosPorUenCiaPK.idParametro = :idParametro")
    ,
    @NamedQuery(name = "ParametrosPorUenCia.findByIdUenNombreParametro",
            query = "SELECT p "
            + "FROM ParametrosPorUenCia p "
            + "WHERE p.parametrosPorUenCiaPK.idUen = :idUen "
            + "and p.parametrosConfiguracion.nombreParametro = :nombreParametro ")
    ,
    @NamedQuery(name = "ParametrosPorUenCia.findByIdParametro", query = "SELECT p FROM ParametrosPorUenCia p WHERE p.parametrosPorUenCiaPK.idParametro = :idParametro")
    ,
    @NamedQuery(name = "ParametrosPorUenCia.findByValor", query = "SELECT p FROM ParametrosPorUenCia p WHERE p.valor = :valor")})
public class ParametrosPorUenCia implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ParametrosPorUenCiaPK parametrosPorUenCiaPK;
    @Size(max = 40)
    @Column(name = "VALOR", length = 40)
    private String valor;
    
    @JoinColumn(name = "ID_PARAMETRO", referencedColumnName = "ID_PARAMETRO", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ParametrosConfiguracion parametrosConfiguracion;
    
    @JoinColumn(name = "ID_UEN", referencedColumnName = "ID_UEN", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private NvcTblOrganizacionesH nvcTblOrganizacionesH;

    public ParametrosPorUenCia() {
    }

    public ParametrosPorUenCia(ParametrosPorUenCiaPK parametrosPorUenCiaPK) {
        this.parametrosPorUenCiaPK = parametrosPorUenCiaPK;
    }

    public ParametrosPorUenCia(long idUen, long idParametro) {
        this.parametrosPorUenCiaPK = new ParametrosPorUenCiaPK(idUen, idParametro);
    }

    public ParametrosPorUenCiaPK getParametrosPorUenCiaPK() {
        return parametrosPorUenCiaPK;
    }

    public void setParametrosPorUenCiaPK(ParametrosPorUenCiaPK parametrosPorUenCiaPK) {
        this.parametrosPorUenCiaPK = parametrosPorUenCiaPK;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public ParametrosConfiguracion getParametrosConfiguracion() {
        return parametrosConfiguracion;
    }

    public void setParametrosConfiguracion(ParametrosConfiguracion parametrosConfiguracion) {
        this.parametrosConfiguracion = parametrosConfiguracion;
    }

    public NvcTblOrganizacionesH getNvcTblOrganizacionesH() {
        return nvcTblOrganizacionesH;
    }

    public void setNvcTblOrganizacionesH(NvcTblOrganizacionesH nvcTblOrganizacionesH) {
        this.nvcTblOrganizacionesH = nvcTblOrganizacionesH;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parametrosPorUenCiaPK != null ? parametrosPorUenCiaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParametrosPorUenCia)) {
            return false;
        }
        ParametrosPorUenCia other = (ParametrosPorUenCia) object;
        if ((this.parametrosPorUenCiaPK == null && other.parametrosPorUenCiaPK != null) || (this.parametrosPorUenCiaPK != null && !this.parametrosPorUenCiaPK.equals(other.parametrosPorUenCiaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.admin.ParametrosPorUenCia[ parametrosPorUenCiaPK=" + parametrosPorUenCiaPK + " ]";
    }

}
