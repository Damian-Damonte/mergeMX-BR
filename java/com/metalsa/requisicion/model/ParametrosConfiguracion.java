package com.metalsa.requisicion.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * @author APOPH5586
 */
@Entity
@Table(name = "PARAMETROS_CONFIGURACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParametrosConfiguracion.findAll", query = "SELECT p FROM ParametrosConfiguracion p")
    ,
    @NamedQuery(name = "ParametrosConfiguracion.findByIdParametro", query = "SELECT p FROM ParametrosConfiguracion p WHERE p.idParametro = :idParametro")
    ,
    @NamedQuery(name = "ParametrosConfiguracion.findByDescripcionParametro", query = "SELECT p FROM ParametrosConfiguracion p WHERE p.descripcionParametro = :descripcionParametro")
    ,
    @NamedQuery(name = "ParametrosConfiguracion.findByNombreParametro", query = "SELECT p FROM ParametrosConfiguracion p WHERE p.nombreParametro = :nombreParametro")
    ,
    @NamedQuery(name = "ParametrosConfiguracion.findByTipo", query = "SELECT p FROM ParametrosConfiguracion p WHERE p.tipo = :tipo")
    ,
    @NamedQuery(name = "ParametrosConfiguracion.findByIdioma", query = "SELECT p FROM ParametrosConfiguracion p WHERE p.idioma = :idioma")
    ,
    @NamedQuery(name = "ParametrosConfiguracion.findByActivo", query = "SELECT p FROM ParametrosConfiguracion p WHERE p.activo = :activo")})
public class ParametrosConfiguracion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PARAMETRO", nullable = false)
    private Long idParametro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPCION_PARAMETRO", nullable = false, length = 100)
    private String descripcionParametro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE_PARAMETRO", nullable = false, length = 50)
    private String nombreParametro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TIPO", nullable = false, length = 100)
    private String tipo;
    @Size(max = 3)
    @Column(name = "IDIOMA", length = 3)
    private String idioma;
    @Column(name = "ACTIVO")
    private BigInteger activo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parametrosConfiguracion", fetch = FetchType.LAZY)
    private List<ParametrosPorUenCia> parametrosPorUenCiaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parametrosConfiguracion", fetch = FetchType.LAZY)
    private List<ParametrosConfigIdioma> parametrosConfigIdiomaList;
    @Size(max = 100)
    @Column(name = "TIPO_VALOR", length = 100)
    private String tipoValor;

    public ParametrosConfiguracion() {
    }

    public ParametrosConfiguracion(Long idParametro) {
        this.idParametro = idParametro;
    }

    public ParametrosConfiguracion(Long idParametro, String descripcionParametro, String nombreParametro, String tipo) {
        this.idParametro = idParametro;
        this.descripcionParametro = descripcionParametro;
        this.nombreParametro = nombreParametro;
        this.tipo = tipo;
    }

    public Long getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(Long idParametro) {
        this.idParametro = idParametro;
    }

    public String getDescripcionParametro() {
        return descripcionParametro;
    }

    public void setDescripcionParametro(String descripcionParametro) {
        this.descripcionParametro = descripcionParametro;
    }

    public String getNombreParametro() {
        return nombreParametro;
    }

    public void setNombreParametro(String nombreParametro) {
        this.nombreParametro = nombreParametro;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public BigInteger getActivo() {
        return activo;
    }

    public void setActivo(BigInteger activo) {
        this.activo = activo;
    }

    @XmlTransient
    public List<ParametrosPorUenCia> getParametrosPorUenCiaList() {
        return parametrosPorUenCiaList;
    }

    public void setParametrosPorUenCiaList(List<ParametrosPorUenCia> parametrosPorUenCiaList) {
        this.parametrosPorUenCiaList = parametrosPorUenCiaList;
    }

    @XmlTransient
    public List<ParametrosConfigIdioma> getParametrosConfigIdiomaList() {
        return parametrosConfigIdiomaList;
    }

    public void setParametrosConfigIdiomaList(List<ParametrosConfigIdioma> parametrosConfigIdiomaList) {
        this.parametrosConfigIdiomaList = parametrosConfigIdiomaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParametro != null ? idParametro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParametrosConfiguracion)) {
            return false;
        }
        ParametrosConfiguracion other = (ParametrosConfiguracion) object;
        if ((this.idParametro == null && other.idParametro != null) || (this.idParametro != null && !this.idParametro.equals(other.idParametro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.admin.ParametrosConfiguracion[ idParametro=" + idParametro + " ]";
    }

    public String getTipoValor() {
        return tipoValor;
    }

    public void setTipoValor(String tipoValor) {
        this.tipoValor = tipoValor;
    }

}
