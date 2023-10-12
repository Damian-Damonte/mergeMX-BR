/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.requisicion.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "DCP_CATEGORIAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DcpCategorias.findAll", query = "SELECT d FROM DcpCategorias d")
    ,
    @NamedQuery(name = "DcpCategorias.findByIdCat", query = "SELECT d FROM DcpCategorias d WHERE d.idCat = :idCat")
    ,
    @NamedQuery(name = "DcpCategorias.findByDescEs", query = "SELECT d FROM DcpCategorias d WHERE d.descEs = :descEs")
    ,
    @NamedQuery(name = "DcpCategorias.findByDescEn", query = "SELECT d FROM DcpCategorias d WHERE d.descEn = :descEn")
    ,
    @NamedQuery(name = "DcpCategorias.findByDescPt", query = "SELECT d FROM DcpCategorias d WHERE d.descPt = :descPt")
    ,
    @NamedQuery(name = "DcpCategorias.findByOrden", query = "SELECT d FROM DcpCategorias d WHERE d.orden = :orden")
    ,
    @NamedQuery(name = "DcpCategorias.findByActivo", query = "SELECT d FROM DcpCategorias d WHERE d.activo = :activo")})
public class DcpCategorias implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CAT", nullable = false, precision = 22)
    private BigDecimal idCat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "DESC_ES", nullable = false, length = 1000)
    private String descEs;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "DESC_EN", nullable = false, length = 1000)
    private String descEn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "DESC_PT", nullable = false, length = 1000)
    private String descPt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORDEN", nullable = false)
    private BigInteger orden;
    @Column(name = "ACTIVO")
    private BigInteger activo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dcpCategorias", fetch = FetchType.LAZY)
    private List<DcpCategoriasIdioma> dcpCategoriasIdiomaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCategoria", fetch = FetchType.LAZY)
    private List<DcpMenus> dcpMenusList;

    public DcpCategorias() {
    }

    public DcpCategorias(BigDecimal idCat) {
        this.idCat = idCat;
    }

    public DcpCategorias(BigDecimal idCat, String descEs, String descEn, String descPt, BigInteger orden) {
        this.idCat = idCat;
        this.descEs = descEs;
        this.descEn = descEn;
        this.descPt = descPt;
        this.orden = orden;
    }

    public BigDecimal getIdCat() {
        return idCat;
    }

    public void setIdCat(BigDecimal idCat) {
        this.idCat = idCat;
    }

    public String getDescEs() {
        return descEs;
    }

    public void setDescEs(String descEs) {
        this.descEs = descEs;
    }

    public String getDescEn() {
        return descEn;
    }

    public void setDescEn(String descEn) {
        this.descEn = descEn;
    }

    public String getDescPt() {
        return descPt;
    }

    public void setDescPt(String descPt) {
        this.descPt = descPt;
    }

    public BigInteger getOrden() {
        return orden;
    }

    public void setOrden(BigInteger orden) {
        this.orden = orden;
    }

    public BigInteger getActivo() {
        return activo;
    }

    public void setActivo(BigInteger activo) {
        this.activo = activo;
    }

    @XmlTransient
    public List<DcpCategoriasIdioma> getDcpCategoriasIdiomaList() {
        return dcpCategoriasIdiomaList;
    }

    public void setDcpCategoriasIdiomaList(List<DcpCategoriasIdioma> dcpCategoriasIdiomaList) {
        this.dcpCategoriasIdiomaList = dcpCategoriasIdiomaList;
    }

    @XmlTransient
    public List<DcpMenus> getDcpMenusList() {
        return dcpMenusList;
    }

    public void setDcpMenusList(List<DcpMenus> dcpMenusList) {
        this.dcpMenusList = dcpMenusList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCat != null ? idCat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DcpCategorias)) {
            return false;
        }
        DcpCategorias other = (DcpCategorias) object;
        if ((this.idCat == null && other.idCat != null) || (this.idCat != null && !this.idCat.equals(other.idCat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.admin.DcpCategorias[ idCat=" + idCat + " ]";
    }

}
