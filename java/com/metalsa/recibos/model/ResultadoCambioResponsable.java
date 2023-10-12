/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import static javax.persistence.ParameterMode.IN;
import static javax.persistence.ParameterMode.REF_CURSOR;
import javax.persistence.StoredProcedureParameter;

/**
 *
 * @author yair.nunez
 */
@Entity
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
            name = "actualizarRecibe",
            procedureName = "NVC_RECIBOS_SPX_PKG.actualizarRecibe",
            parameters = {
                @StoredProcedureParameter(mode = IN, name = "p_id_requisicion", type = Integer.class),
                @StoredProcedureParameter(mode = IN, name = "p_id_usuario", type = String.class),
                @StoredProcedureParameter(mode = IN, name = "p_tipo_usuario", type = String.class)
            }
    ),
    @NamedStoredProcedureQuery(
            name = "consultaCambioResponsable",
            resultClasses = ResultadoCambioResponsable.class,
            procedureName = "NVC_RECIBOS_SPX_PKG.consulta_cambio_responsable",
            parameters = {
                @StoredProcedureParameter(mode = REF_CURSOR, name = "V_CURSOR", type = void.class),
                @StoredProcedureParameter(mode = IN, name = "p_id_requisicion", type = Integer.class),
                @StoredProcedureParameter(mode = IN, name = "p_id_usuario", type = String.class)
            }
    )
})
@IdClass(ResultadoCambioResponsablePK.class)
public class ResultadoCambioResponsable implements Serializable{
    @Id
    @Column(name="ID_REQUISICION")
    private Integer idRequisicion;
    
    @Id
    @Column(name = "ID_PARTIDA")
    private Integer idLinea;
    
    @Column(name = "DESCRIPCION_PRODUCTO")
    private String descripcion;
    
    @Column(name = "ID_REQUISITOR")
    private String idRequisitor;
    
    @Column(name = "REQUISITOR")
    private String requisitor;
    
    @Column(name = "ID_APROBADOR")
    private String idAprobador;
    
    @Column(name = "APROBADOR")
    private String aprobador;
    
    @Column(name = "CODIGO_CC")
    private String ccProyecto;
    
    @Column(name = "CODIGO_PROYECTO")
    private String codigoProyecto;
    
    @Column(name = "FUENTE")
    private String fuente;
    
    @Column(name = "ID_COORDINADOR")
    private String idCoordinador;
    
    @Column(name = "COORDINADOR")
    private String coordinador;
    
    @Column(name = "ID_RESPONSABLE_CC")
    private String idResponsableCc;
    
    @Column(name = "RESPONSABLE_CC")
    private String responsableCc;
    
    @Column(name = "ID_RESPONSABLE_PROYECTO")
    private String idResponsableProyecto;
    
    @Column(name = "RESPONSABLE_PROYECTO")
    private String responsableProyecto;
    
    @Column(name = "RECIBE")
    private String recibe;

    public Integer getIdRequisicion() {
        return idRequisicion;
    }

    public void setIdRequisicion(Integer idRequisicion) {
        this.idRequisicion = idRequisicion;
    }

    public Integer getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(Integer idLinea) {
        this.idLinea = idLinea;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIdRequisitor() {
        return idRequisitor;
    }

    public void setIdRequisitor(String idRequisitor) {
        this.idRequisitor = idRequisitor;
    }

    public String getRequisitor() {
        return requisitor;
    }

    public void setRequisitor(String requisitor) {
        this.requisitor = requisitor;
    }

    public String getIdAprobador() {
        return idAprobador;
    }

    public void setIdAprobador(String idAprobador) {
        this.idAprobador = idAprobador;
    }

    public String getAprobador() {
        return aprobador;
    }

    public void setAprobador(String aprobador) {
        this.aprobador = aprobador;
    }

    public String getCcProyecto() {
        return ccProyecto;
    }

    public void setCcProyecto(String ccProyecto) {
        this.ccProyecto = ccProyecto;
    }

    public String getCodigoProyecto() {
        return codigoProyecto;
    }

    public void setCodigoProyecto(String codigoProyecto) {
        this.codigoProyecto = codigoProyecto;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getIdCoordinador() {
        return idCoordinador;
    }

    public void setIdCoordinador(String idCoordinador) {
        this.idCoordinador = idCoordinador;
    }

    public String getCoordinador() {
        return coordinador;
    }

    public void setCoordinador(String coordinador) {
        this.coordinador = coordinador;
    }

    public String getIdResponsableCc() {
        return idResponsableCc;
    }

    public void setIdResponsableCc(String idResponsableCc) {
        this.idResponsableCc = idResponsableCc;
    }

    public String getResponsableCc() {
        return responsableCc;
    }

    public void setResponsableCc(String responsableCc) {
        this.responsableCc = responsableCc;
    }

    public String getIdResponsableProyecto() {
        return idResponsableProyecto;
    }

    public void setIdResponsableProyecto(String idResponsableProyecto) {
        this.idResponsableProyecto = idResponsableProyecto;
    }

    public String getResponsableProyecto() {
        return responsableProyecto;
    }

    public void setResponsableProyecto(String responsableProyecto) {
        this.responsableProyecto = responsableProyecto;
    }

    public String getRecibe() {
        return recibe;
    }

    public void setRecibe(String recibe) {
        this.recibe = recibe;
    }
     
    
}
