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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author edgar.leal
 */
@Entity
@Table(name = "MOTIVO_REGRESO_VIEW")
//@XmlRootElement
@NamedQueries({
      
      @NamedQuery(name = "MotivoRegresoView.findAll", query = "SELECT m FROM MotivoRegresoView m")
    , @NamedQuery(name = "MotivoRegresoView.findById", query = "SELECT m FROM MotivoRegresoView m WHERE m.id = :id")
    , @NamedQuery(name = "MotivoRegresoView.findByComentario", query = "SELECT m FROM MotivoRegresoView m WHERE m.comentario = :comentario")
    , @NamedQuery(name = "MotivoRegresoView.findByIdRequisicion", query = "SELECT m FROM MotivoRegresoView m WHERE m.idRequisicion = :idRequisicion")
    , @NamedQuery(name = "MotivoRegresoView.findByIdPartida", query = "SELECT m FROM MotivoRegresoView m WHERE m.idPartida = :idPartida")})
public class MotivoRegresoView implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private BigInteger id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "COMENTARIO")
    private String comentario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_REQUISICION")
    private BigInteger idRequisicion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PARTIDA")
    private BigInteger idPartida;

    public MotivoRegresoView() {
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public BigInteger getIdRequisicion() {
        return idRequisicion;
    }

    public void setIdRequisicion(BigInteger idRequisicion) {
        this.idRequisicion = idRequisicion;
    }

    public BigInteger getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(BigInteger idPartida) {
        this.idPartida = idPartida;
    }
    
}
