/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.model;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author apojm20015
 */
@Entity
@Table(name = "NVC_TBL_CONF_FAM")
@NamedQueries({
    @NamedQuery(name = "NvcTblConfFam.findAll", query = "SELECT n FROM NvcTblConfFam n")})
public class NvcTblConfFam implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(sequenceName = "NVC_TBL_CONF_FAM_SEQ", name = "confFamSeq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "confFamSeq")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CONF")
    private Integer idConf;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_SUBFAM")
    private BigInteger idSubfam;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TIPO")
    private BigInteger idTipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_UEN")
    private BigInteger idUen;

    public NvcTblConfFam() {
    }

    public NvcTblConfFam(Integer idConf) {
        this.idConf = idConf;
    }

    public Integer getIdConf() {
        return idConf;
    }

    public void setIdConf(Integer idConf) {
        this.idConf = idConf;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConf != null ? idConf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcTblConfFam)) {
            return false;
        }
        NvcTblConfFam other = (NvcTblConfFam) object;
        if ((this.idConf == null && other.idConf != null) || (this.idConf != null && !this.idConf.equals(other.idConf))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.aprobacion.model.NvcTblConfFam[ idConf=" + idConf + " ]";
    }

    public BigInteger getIdSubfam() {
        return idSubfam;
    }

    public void setIdSubfam(BigInteger idSubfam) {
        this.idSubfam = idSubfam;
    }

    public BigInteger getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(BigInteger idTipo) {
        this.idTipo = idTipo;
    }

    public BigInteger getIdUen() {
        return idUen;
    }

    public void setIdUen(BigInteger idUen) {
        this.idUen = idUen;
    }
    
}
