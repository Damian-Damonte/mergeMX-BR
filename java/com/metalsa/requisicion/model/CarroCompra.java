/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.requisicion.model;

// codigo comentado por Damian

//import java.io.Serializable;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Table;
//import javax.persistence.Id;
//import javax.persistence.Basic;
//import javax.persistence.GeneratedValue;
//import javax.persistence.SequenceGenerator;
//import javax.validation.constraints.NotNull;
//
///**
// *
// * @author Gamaliel Espinoza M.
// */
//@Entity
//@Table(name = "NVC_TBL_CARRO_COMPRA", catalog = "")
//public class CarroCompra implements Serializable {
//    @Id
//    @SequenceGenerator(
//            name = "SEQ_NVC_TBL_CARRO_COMPRA_GEN",
//            sequenceName = "SEQ_NVC_TBL_CARRO_COMPRA",
//            allocationSize = 1
//    )
//    @GeneratedValue(generator = "SEQ_NVC_TBL_CARRO_COMPRA_GEN")
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "ID_CARRO_COMPRA")
//    private Integer id;
//
//    @Column(name = "RF_NUMBER")
//    private Long rfNumber;
//
//    @Column(name = "ID_RF")
//    private Long idRf;
//
//    @Column(name = "COMMODITY")
//    private String commodity;
//
//    @Column(name = "ID_PART_NUMBER")
//    private Integer idPartNumber;
//
//    @Column(name = "TIPO_RECIBO")
//    private Integer tipoRecibo;
//
//    @Column(name = "ITEM_SPOT_MAXIMO")
//    private String itemSpotMaximo;
//
//    @Column(name = "PUNCHOUT")
//    private Integer punchout;
//
//    public Integer getId() {
//        return this.id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public Long getRfNumber() {
//        return this.rfNumber;
//    }
//
//    public void setRfNumber(Long number) {
//        this.rfNumber = number;
//    }
//
//    public Long getIdRf() {
//        return this.idRf;
//    }
//
//    public void setIdRf(Long id) {
//        this.idRf = id;
//    }
//
//    public String getCommodity() {
//        return this.commodity;
//    }
//
//    public void setCommodity(String commodity) {
//        this.commodity = commodity;
//    }
//
//    public Integer getIdPartNumber() {
//        return this.idPartNumber;
//    }
//
//    public void setIdPartNumber(Integer number) {
//        this.idPartNumber = number;
//    }
//
//    public Integer getTipoRecibo() {
//        return tipoRecibo;
//    }
//
//    public void setTipoRecibo(Integer tipoRecibo) {
//        this.tipoRecibo = tipoRecibo;
//    }
//
//    public String getItemSpotMaximo() {
//        return itemSpotMaximo;
//    }
//
//    public void setItemSpotMaximo(String itemSpotMaximo) {
//        this.itemSpotMaximo = itemSpotMaximo;
//    }
//
//    public Integer getPunchout() {
//        return punchout != null ? punchout : 0;
//    }
//
//    public void setPunchout(Integer punchout) {
//        this.punchout = punchout;
//    }
//}
