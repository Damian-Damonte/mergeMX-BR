/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.requisicion.model;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juliocisneros
 */
@Entity
@Table(name = "COMENTARIO_REQUISICION", catalog = "")
public class ComentarioRequisicion implements Serializable {
    @EmbeddedId
    protected ComentarioRequisicionPK pk;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "COMENTARIO", nullable = false, length = 200)
    private String comentario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PARA_OC", nullable = false)
    private BigInteger paraOc;
    @JoinColumns({
        @JoinColumn(name = "ID_REQUISICION", referencedColumnName = "ID_REQUISICION", nullable = false, insertable = false, updatable = false)
        ,
        @JoinColumn(name = "ID_LINEA", referencedColumnName = "ID_PARTIDA", nullable = false, insertable = false, updatable = false)})
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private DetalleDeRequisicion detalleDeRequisicion;

    public ComentarioRequisicion() {
    }

    public ComentarioRequisicion(ComentarioRequisicionPK comentarioRequisicionPK) {
        this.pk = comentarioRequisicionPK;
    }

    public ComentarioRequisicion(ComentarioRequisicionPK comentarioRequisicionPK, String comentario, BigInteger paraOc) {
        this.pk = comentarioRequisicionPK;
        this.comentario = comentario;
        this.paraOc = paraOc;
    }

    public ComentarioRequisicion(BigInteger idRequisicion, BigInteger idLinea) {
        this.pk = new ComentarioRequisicionPK(idRequisicion, idLinea);
    }

    public ComentarioRequisicionPK getPK() {
        return pk;
    }

    public void setPK(ComentarioRequisicionPK pk) {
        this.pk = pk;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public BigInteger getParaOc() {
        return paraOc;
    }

    public void setParaOc(BigInteger paraOc) {
        this.paraOc = paraOc;
    }

    public DetalleDeRequisicion getDetalleDeRequisicion() {
        return detalleDeRequisicion;
    }

    public void setDetalleDeRequisicion(DetalleDeRequisicion detalleDeRequisicion) {
        this.detalleDeRequisicion = detalleDeRequisicion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pk != null ? pk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComentarioRequisicion)) {
            return false;
        }
        ComentarioRequisicion other = (ComentarioRequisicion) object;
        if ((this.pk == null && other.pk != null) || (this.pk != null && !this.pk.equals(other.pk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.ComentarioRequisicion[ comentarioRequisicionPK=" + pk + " ]";
    }

}
