/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.requisicion.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "PARTIDA_DOCUMENTS_MAXIMO")
public class PartidaDocumentsMaximo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "PARTIDA_DOCUMENTS_MAXIMO_SEQ")
    @SequenceGenerator(name = "PARTIDA_DOCUMENTS_MAXIMO_SEQ",
            sequenceName = "SEQ_PARTIDA_DOCUMENTS_MAXIMO",
            allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PARTIDA_DOCUMENTS_MAXIMO")
    private Long idPartidaDocumenstMaximo;

    @Column(name = "ID_REQUISICION")
    private Long idRequisicion;

    @Column(name = "ID_PARTIDA")
    private Long idPartida;

    @Column(name = "TYPE_DOCUMENT")
    private String typeDocument;

    @Column(name = "VALUE_DOCUMENT")
    private String valueDocument;

    @Column(name = "ID_SITE_MAXIMO")
    private String idSiteMaximo;
    
    @Column(name = "ITEM_SPOT_NUM")
    private String itemSpotNum;

    @Column(name = "ATTEMPTS_NUM")
    private int attemptsNum;

    @Column(name = "STATUS")
    private String status;

    public Long getIdPartidaDocumenstMaximo() {
        return idPartidaDocumenstMaximo;
    }

    public void setIdPartidaDocumenstMaximo(Long idPartidaDocumenstMaximo) {
        this.idPartidaDocumenstMaximo = idPartidaDocumenstMaximo;
    }

    public Long getIdRequisicion() {
        return idRequisicion;
    }

    public void setIdRequisicion(Long idRequisicion) {
        this.idRequisicion = idRequisicion;
    }

    public Long getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(Long idPartida) {
        this.idPartida = idPartida;
    }

    public String getTypeDocument() {
        return typeDocument;
    }

    public void setTypeDocument(String typeDocument) {
        this.typeDocument = typeDocument;
    }

    public String getValueDocument() {
        return valueDocument;
    }

    public void setValueDocument(String valueDocument) {
        this.valueDocument = valueDocument;
    }

    public String getIdSiteMaximo() {
        return idSiteMaximo;
    }

    public void setIdSiteMaximo(String idSiteMaximo) {
        this.idSiteMaximo = idSiteMaximo;
    }

    public String getItemSpotNum() {
        return itemSpotNum;
    }

    public void setItemSpotNum(String itemSpotNum) {
        this.itemSpotNum = itemSpotNum;
    }

    public int getAttemptsNum() {
        return attemptsNum;
    }

    public void setAttemptsNum(int attemptsNum) {
        this.attemptsNum = attemptsNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

