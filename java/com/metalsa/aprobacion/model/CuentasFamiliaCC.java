package com.metalsa.aprobacion.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author APOOD9272
 */
@Entity(name = "ctas_fam_cc")
@NamedNativeQueries({
    @NamedNativeQuery(
            name = "CuentasFamiliaCC.getCuentasFamiliaPorUenCC",
            query = "SELECT \n"
            + "    *\n"
            + "FROM ctas_fam_cc\n"
            + "WHERE id_uen =  ?1\n"
            + "AND segmento_2 = ?2\n"
            + "AND id_familia = ?3\n"
            + "AND  language  = ?4",
            resultClass = CuentasFamiliaCC.class
    )
    ,
    @NamedNativeQuery(
            name = "CuentasFamiliaCC.getByCuenta",
            query = "SELECT \n"
            + "    *\n"
            + "FROM ctas_fam_cc\n"
            + "WHERE id_uen =  ?1\n"
            + "AND segmento_2 = ?2\n"
            + "AND id_familia = ?3\n"
            + "AND  id_cuenta  = ?4\n"
            + "AND  language  = ?5",
            resultClass = CuentasFamiliaCC.class
    ),})
public class CuentasFamiliaCC implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_cuenta")
    @JsonProperty("id_cuenta")
    private Long idCuenta;

    @JsonProperty("segmento_1")
    @Column(name = "segmento_1")
    private String segmento1;

    @JsonProperty("segmento_2")
    @Column(name = "segmento_2")
    private String segmento2;

    @JsonProperty("segmento_3")
    @Column(name = "segmento_3")
    private String segmento3;

    @JsonProperty("segmento_4")
    @Column(name = "segmento_4")
    private String segmento4;
    
    @JsonProperty("segmento_5")
    @Column(name = "segmento_5")
    private String segmento5;

    @JsonProperty("segmento_6")
    @Column(name = "segmento_6")
    private String segmento6;

    @JsonProperty("segmento_7")
    @Column(name = "segmento_7")
    private String segmento7;

    @JsonProperty("segmento_8")
    @Column(name = "segmento_8")
    private String segmento8;
    
    @JsonProperty("combinacion")
    @Column(name = "combinacion")
    private String combinacion;

    @JsonProperty("start_date_active")
    @Column(name = "start_date_active")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDateActive;

    @JsonProperty("end_date_active")
    @Column(name = "end_date_active")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endDateActive;

    @JsonProperty("cuenta_natural")
    @Column(name = "cuenta_natural")
    private String cuentaNatural;
    
    @JsonProperty("id_uen")
    @Column(name = "id_uen")//2
    private Long idUen;

    @JsonProperty("id_familia")
    @Column(name = "id_familia")//3
    private Long idFamilia;

    @JsonProperty("id_cc")
    @Column(name = "id_cc")
    private Long idCC;

    @JsonProperty("language")
    @Column(name = "language")
    private String lenguaje;

    @JsonProperty("id_requisicion")
    @Transient
    private Integer IdRequisicion;

    @JsonProperty("partida")
    @Transient
    private Integer Partida;

    public Long getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Long idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getSegmento1() {
        return segmento1;
    }

    public void setSegmento1(String segmento1) {
        this.segmento1 = segmento1;
    }

    public String getSegmento2() {
        return segmento2;
    }

    public void setSegmento2(String segmento2) {
        this.segmento2 = segmento2;
    }

    public String getSegmento3() {
        return segmento3;
    }

    public void setSegmento3(String segmento3) {
        this.segmento3 = segmento3;
    }

    public String getSegmento4() {
        return segmento4;
    }

    public void setSegmento4(String segmento4) {
        this.segmento4 = segmento4;
    }

    public String getSegmento5() {
        return segmento5;
    }

    public void setSegmento5(String segmento5) {
        this.segmento5 = segmento5;
    }

    public String getSegmento6() {
        return segmento6;
    }

    public void setSegmento6(String segmento6) {
        this.segmento6 = segmento6;
    }

    public String getSegmento7() {
        return segmento7;
    }

    public void setSegmento7(String segmento7) {
        this.segmento7 = segmento7;
    }

    public String getSegmento8() {
        return segmento8;
    }

    public void setSegmento8(String segmento8) {
        this.segmento8 = segmento8;
    }

    public String getCombinacion() {
        return combinacion;
    }

    public void setCombinacion(String combinacion) {
        this.combinacion = combinacion;
    }

    public Date getStartDateActive() {
        return startDateActive == null ? null : (Date) startDateActive.clone();
    }

    public void setStartDateActive(Date startDateActive) {
         if (startDateActive == null)
            this.startDateActive = null;
        else
            this.startDateActive = (Date) startDateActive.clone();
    }

    public Date getEndDateActive() {
        return endDateActive == null ? null : (Date) endDateActive.clone();
    }

    public void setEndDateActive(Date endDateActive) {
        if (endDateActive == null)
            this.endDateActive = null;
        else
            this.endDateActive = (Date) endDateActive.clone();
    }

    public String getCuentaNatural() {
        return cuentaNatural;
    }

    public void setCuentaNatural(String cuentaNatural) {
        this.cuentaNatural = cuentaNatural;
    }

    public Long getIdUen() {
        return idUen;
    }

    public void setIdUen(Long idUen) {
        this.idUen = idUen;
    }

    public Long getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(Long idFamilia) {
        this.idFamilia = idFamilia;
    }

    public Long getIdCC() {
        return idCC;
    }

    public void setIdCC(Long idCC) {
        this.idCC = idCC;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public Integer getIdRequisicion() {
        return IdRequisicion;
    }

    public void setIdRequisicion(Integer IdRequisicion) {
        this.IdRequisicion = IdRequisicion;
    }

    public Integer getPartida() {
        return Partida;
    }

    public void setPartida(Integer Partida) {
        this.Partida = Partida;
    }

    @Override
    public String toString() {
        return "CuentasFamiliaCC{" + "idCuenta=" + idCuenta + ", segmento1=" + segmento1 + ", segmento2=" + segmento2 + ", segmento3=" + segmento3 + ", segmento4=" + segmento4 + ", segmento5=" + segmento5 + ", segmento6=" + segmento6 + ", segmento7=" + segmento7 + ", segmento8=" + segmento8 + ", combinacion=" + combinacion + ", startDateActive=" + startDateActive + ", endDateActive=" + endDateActive + ", cuentaNatural=" + cuentaNatural + ", idUen=" + idUen + ", idFamilia=" + idFamilia + ", idCC=" + idCC + ", lenguaje=" + lenguaje + ", IdRequisicion=" + IdRequisicion + ", Partida=" + Partida + '}';
    }


}
