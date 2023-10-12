/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author edgar.leal
 */
@Entity
@Table(name = "SPX_TIPO_DE_REQUISICION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SpxTipoDeRequisicion.findAll", query = "SELECT s FROM SpxTipoDeRequisicion s")
    , @NamedQuery(name = "SpxTipoDeRequisicion.findByClave", query = "SELECT s FROM SpxTipoDeRequisicion s WHERE s.clave = :clave")
    , @NamedQuery(name = "SpxTipoDeRequisicion.findByNombreEs", query = "SELECT s FROM SpxTipoDeRequisicion s WHERE s.nombreEs = :nombreEs")
    , @NamedQuery(name = "SpxTipoDeRequisicion.findByNombreUs", query = "SELECT s FROM SpxTipoDeRequisicion s WHERE s.nombreUs = :nombreUs")
    , @NamedQuery(name = "SpxTipoDeRequisicion.findByNombrePor", query = "SELECT s FROM SpxTipoDeRequisicion s WHERE s.nombrePor = :nombrePor")})

public class SpxTipoDeRequisicion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CLAVE")
    private String clave;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE_ES")
    private String nombreEs;
    @Size(max = 30)
    @Column(name = "NOMBRE_US")
    private String nombreUs;
    @Size(max = 30)
    @Column(name = "NOMBRE_POR")
    private String nombrePor;

    public SpxTipoDeRequisicion() {
    }

    public SpxTipoDeRequisicion(String clave) {
        this.clave = clave;
    }

    public SpxTipoDeRequisicion(String clave, String nombreEs) {
        this.clave = clave;
        this.nombreEs = nombreEs;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombreEs() {
        return nombreEs;
    }

    public void setNombreEs(String nombreEs) {
        this.nombreEs = nombreEs;
    }

    public String getNombreUs() {
        return nombreUs;
    }

    public void setNombreUs(String nombreUs) {
        this.nombreUs = nombreUs;
    }

    public String getNombrePor() {
        return nombrePor;
    }

    public void setNombrePor(String nombrePor) {
        this.nombrePor = nombrePor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clave != null ? clave.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpxTipoDeRequisicion)) {
            return false;
        }
        SpxTipoDeRequisicion other = (SpxTipoDeRequisicion) object;
        return !((this.clave == null && other.clave != null) || (this.clave != null && !this.clave.equals(other.clave)));
    }

    @Override
    public String toString() {
        return "com.metalsa.recibos.model.SpxTipoDeRequisicion[ clave=" + clave + " ]";
    }
    
}
