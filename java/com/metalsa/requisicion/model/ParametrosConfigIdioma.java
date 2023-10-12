/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.requisicion.model;

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
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author APOPH5586
 */
@Entity
@Table(name = "PARAMETROS_CONFIG_IDIOMA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParametrosConfigIdioma.findAll", query = "SELECT p FROM ParametrosConfigIdioma p")
    ,
    @NamedQuery(name = "ParametrosConfigIdioma.findByIdParametro", query = "SELECT p FROM ParametrosConfigIdioma p WHERE p.parametrosConfigIdiomaPK.idParametro = :idParametro")
    ,
    @NamedQuery(name = "ParametrosConfigIdioma.findByIdIdioma", query = "SELECT p FROM ParametrosConfigIdioma p WHERE p.parametrosConfigIdiomaPK.idIdioma = :idIdioma")
    ,
    @NamedQuery(name = "ParametrosConfigIdioma.findByNombreParametro", query = "SELECT p FROM ParametrosConfigIdioma p WHERE p.nombreParametro = :nombreParametro")
    ,
    @NamedQuery(name = "ParametrosConfigIdioma.findByDescripcionParametro", query = "SELECT p FROM ParametrosConfigIdioma p WHERE p.descripcionParametro = :descripcionParametro")})
public class ParametrosConfigIdioma implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ParametrosConfigIdiomaPK parametrosConfigIdiomaPK;
    @Size(max = 100)
    @Column(name = "NOMBRE_PARAMETRO", length = 100)
    private String nombreParametro;
    @Size(max = 200)
    @Column(name = "DESCRIPCION_PARAMETRO", length = 200)
    private String descripcionParametro;
    @JoinColumn(name = "ID_PARAMETRO", referencedColumnName = "ID_PARAMETRO", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ParametrosConfiguracion parametrosConfiguracion;
    @JoinColumn(name = "ID_IDIOMA", referencedColumnName = "SC_ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DcIdioma dcIdioma;
    @Transient
    private String valor;
    @Transient
    private String tipoValor;

    public ParametrosConfigIdioma() {
    }

    public ParametrosConfigIdioma(ParametrosConfigIdiomaPK parametrosConfigIdiomaPK) {
        this.parametrosConfigIdiomaPK = parametrosConfigIdiomaPK;
    }

    public ParametrosConfigIdioma(long idParametro, String idIdioma) {
        this.parametrosConfigIdiomaPK = new ParametrosConfigIdiomaPK(idParametro, idIdioma);
    }

    public ParametrosConfigIdioma(long idParametro, String idIdioma, String nombreParametro,
            String descripcionParametro, ParametrosConfiguracion parametrosConfiguracion,
            DcIdioma dcIdioma, String valor, String tipoValor) {
        this.parametrosConfigIdiomaPK = new ParametrosConfigIdiomaPK(idParametro, idIdioma);
        this.nombreParametro = nombreParametro;
        this.descripcionParametro = descripcionParametro;
        this.parametrosConfiguracion = parametrosConfiguracion;
        this.dcIdioma = dcIdioma;
        this.valor = valor;
        this.tipoValor = tipoValor;
    }

    public ParametrosConfigIdiomaPK getParametrosConfigIdiomaPK() {
        return parametrosConfigIdiomaPK;
    }

    public void setParametrosConfigIdiomaPK(ParametrosConfigIdiomaPK parametrosConfigIdiomaPK) {
        this.parametrosConfigIdiomaPK = parametrosConfigIdiomaPK;
    }

    public String getNombreParametro() {
        return nombreParametro;
    }

    public void setNombreParametro(String nombreParametro) {
        this.nombreParametro = nombreParametro;
    }

    public String getDescripcionParametro() {
        return descripcionParametro;
    }

    public void setDescripcionParametro(String descripcionParametro) {
        this.descripcionParametro = descripcionParametro;
    }

    public ParametrosConfiguracion getParametrosConfiguracion() {
        return parametrosConfiguracion;
    }

    public void setParametrosConfiguracion(ParametrosConfiguracion parametrosConfiguracion) {
        this.parametrosConfiguracion = parametrosConfiguracion;
    }

    public DcIdioma getDcIdioma() {
        return dcIdioma;
    }

    public void setDcIdioma(DcIdioma dcIdioma) {
        this.dcIdioma = dcIdioma;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getTipoValor() {
        return tipoValor;
    }

    public void setTipoValor(String tipoValor) {
        this.tipoValor = tipoValor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parametrosConfigIdiomaPK != null ? parametrosConfigIdiomaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParametrosConfigIdioma)) {
            return false;
        }
        ParametrosConfigIdioma other = (ParametrosConfigIdioma) object;
        if ((this.parametrosConfigIdiomaPK == null && other.parametrosConfigIdiomaPK != null) || (this.parametrosConfigIdiomaPK != null && !this.parametrosConfigIdiomaPK.equals(other.parametrosConfigIdiomaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.admin.ParametrosConfigIdioma[ parametrosConfigIdiomaPK=" + parametrosConfigIdiomaPK + " ]";
    }

}
