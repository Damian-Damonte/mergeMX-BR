/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.model;

/**
 *
 * @author edgar.leal
 */

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import static javax.persistence.ParameterMode.REF_CURSOR;
import javax.persistence.StoredProcedureParameter;

/**
 *
 * @author edgar.leal
 */
@Entity
@NamedStoredProcedureQueries({
    //<R17231>
    @NamedStoredProcedureQuery(name = "PRC_SPX_REQS_POR_RECIBIR",
            procedureName = "NVC_RECIBOS_SPX_PKG.PRC_SPX_REQS_POR_RECIBIR", //SPX_COMPRAS_RETURN_REQ
            resultClasses = ReciboConsulta.class,
            parameters = {
                @StoredProcedureParameter(mode = REF_CURSOR, name = "V_CURSOR", type = void.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ID_REQUISICION", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ID_ORDEN_DE_COMPRA", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_UEN", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_FECHA_INICIAL", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_FECHA_FINAL", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_URGENTE", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ID_PROVEEDOR", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ID_CC", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ID_REQUISITOR", type = String.class),                
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ID_USUARIO", type = String.class)
            })
    ,
    //</R17231>
    
    @NamedStoredProcedureQuery(name = "recibeEscritorio",
            procedureName = "NVC_RECIBOS_SPX_PKG.RECIBE_ESCRITORIO",
            parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_VALORES", type = String.class) //noc si cambiar esto a string y en store a varchar
                ,
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "P_RETURN", type = String.class)
                
    }),
    
    @NamedStoredProcedureQuery(
            name = "valida_lineas_recibo",
            procedureName = "NVC_RECIBOS_SPX_PKG.valida_lineas_recibo",
            parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_valores", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "identificador", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_result", type = Integer.class)
            }
    ), 
    
    @NamedStoredProcedureQuery(
            name = "prc_detalle_validaciones",
            procedureName = "NVC_RECIBOS_SPX_PKG.prc_detalle_validaciones",
            parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_requi", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_idioma", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_identificador", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "mensaje", type = String.class)
                
            }
    )
    
        
        
})

@IdClass(ReciboConsultaPK.class)
public class ReciboConsulta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    //@Column(name = "ID_REQUISICION")
    private Integer ID_REQUISICION;
    @Id
    //@Column(name = "ID_PARTIDA")
    private Integer ID_PARTIDA;
    //@Column(name = "NOMBRE_PROVEEDOR")
    private String NOMBRE_PROVEEDOR;
   
    private String FECHA_REQUISICION; //date
    
    private Integer ID_UEN;

    private Integer ID_ORDEN_DE_COMPRA;

    private Integer ID_PROVEEDOR;
    
    private String CLOSED_CODE;

    private String AUTHORIZATION_STATUS;

    private String CANCEL_FLAG;
    
    private Integer ID_LOCALIZACION;

    private Integer SHIP_TO_LOCATION_ID;
    
    private Integer LOCALIZACION_IGUAL;
    
    private String REQ_ESTADO; // este se cambia
    
    private Integer ID_CC;
    
    private String NOMBRE_CC;
    
    private String CODIGO_CC;
    
    private String ID_REQUISITOR;

    private String URGENTE;
    
    private String COMPRADOR;
    
    private String REQUISITOR;

    private Double CANTIDAD_REQUERIDA;
    
    private Double CANTIDAD_ENTREGADA;

    private Double CANTIDAD_PENDIENTE;
    
    private String ID_UNIDAD_DE_MEDIDA;

    private String DESCRIPCION_PRODUCTO;
    
    private String FECHA_REQUERIDA; // date

    private String FUENTE;
    
    private Integer ID_PARTIDA_OC;
    
    private String PROMISED_DATE;
    
    private String NOTIFICA_PROVEEDOR;
    
    private Integer PROV_TIENE_CONTACTO;
    
    private Integer pedidoEspecial;

   
    public ReciboConsulta() {
    }

    public ReciboConsulta(Integer ID_REQUISICION, Integer ID_PARTIDA, String NOMBRE_PROVEEDOR, String FECHA_REQUISICION, Integer ID_UEN, Integer ID_ORDEN_DE_COMPRA, Integer ID_PROVEEDOR, String CLOSED_CODE, String AUTHORIZATION_STATUS, String CANCEL_FLAG, Integer ID_LOCALIZACION, Integer SHIP_TO_LOCATION_ID, Integer LOCALIZACION_IGUAL, String REQ_ESTADO, Integer ID_CC, String NOMBRE_CC, String CODIGO_CC, String ID_REQUISITOR, String URGENTE, String COMPRADOR, String REQUISITOR, Double CANTIDAD_REQUERIDA, Double CANTIDAD_ENTREGADA, Double CANTIDAD_PENDIENTE, String ID_UNIDAD_DE_MEDIDA, String DESCRIPCION_PRODUCTO, String FECHA_REQUERIDA, String FUENTE, Integer ID_PARTIDA_OC, String PROMISED_DATE, String NOTIFICA_PROVEEDOR, Integer PROV_TIENE_CONTACTO) {
        this.ID_REQUISICION = ID_REQUISICION;
        this.ID_PARTIDA = ID_PARTIDA;
        this.NOMBRE_PROVEEDOR = NOMBRE_PROVEEDOR;
        this.FECHA_REQUISICION = FECHA_REQUISICION;
        this.ID_UEN = ID_UEN;
        this.ID_ORDEN_DE_COMPRA = ID_ORDEN_DE_COMPRA;
        this.ID_PROVEEDOR = ID_PROVEEDOR;
        this.CLOSED_CODE = CLOSED_CODE;
        this.AUTHORIZATION_STATUS = AUTHORIZATION_STATUS;
        this.CANCEL_FLAG = CANCEL_FLAG;
        this.ID_LOCALIZACION = ID_LOCALIZACION;
        this.SHIP_TO_LOCATION_ID = SHIP_TO_LOCATION_ID;
        this.LOCALIZACION_IGUAL = LOCALIZACION_IGUAL;
        this.REQ_ESTADO = REQ_ESTADO;
        this.ID_CC = ID_CC;
        this.CODIGO_CC=CODIGO_CC;
        this.NOMBRE_CC = NOMBRE_CC;
        this.ID_REQUISITOR = ID_REQUISITOR;
        this.URGENTE = URGENTE;
        this.COMPRADOR = COMPRADOR;
        this.REQUISITOR = REQUISITOR;
        this.CANTIDAD_REQUERIDA = CANTIDAD_REQUERIDA;
        this.CANTIDAD_ENTREGADA = CANTIDAD_ENTREGADA;
        this.CANTIDAD_PENDIENTE = CANTIDAD_PENDIENTE;
        this.ID_UNIDAD_DE_MEDIDA = ID_UNIDAD_DE_MEDIDA;
        this.DESCRIPCION_PRODUCTO = DESCRIPCION_PRODUCTO;
        this.FECHA_REQUERIDA = FECHA_REQUERIDA;
        this.FUENTE = FUENTE;
        this.ID_PARTIDA_OC = ID_PARTIDA_OC;
        this.PROMISED_DATE = PROMISED_DATE;
        this.NOTIFICA_PROVEEDOR = NOTIFICA_PROVEEDOR;
        this.PROV_TIENE_CONTACTO = PROV_TIENE_CONTACTO;
    }

    public Integer getPROV_TIENE_CONTACTO() {
        return PROV_TIENE_CONTACTO;
    }

    public void setPROV_TIENE_CONTACTO(Integer PROV_TIENE_CONTACTO) {
        this.PROV_TIENE_CONTACTO = PROV_TIENE_CONTACTO;
    }
    
    

    public String getNOTIFICA_PROVEEDOR() {
        return NOTIFICA_PROVEEDOR;
    }

    public void setNOTIFICA_PROVEEDOR(String NOTIFICA_PROVEEDOR) {
        this.NOTIFICA_PROVEEDOR = NOTIFICA_PROVEEDOR;
    }
    
    

    public String getPROMISED_DATE() {
        return PROMISED_DATE;
    }

    public void setPROMISED_DATE(String PROMISED_DATE) {
        this.PROMISED_DATE = PROMISED_DATE;
    }
    
    

    public Integer getID_PARTIDA_OC() {
        return ID_PARTIDA_OC;
    }

    public void setID_PARTIDA_OC(Integer ID_PARTIDA_OC) {
        this.ID_PARTIDA_OC = ID_PARTIDA_OC;
    }
    

    public Integer getID_REQUISICION() {
        return ID_REQUISICION;
    }

    public void setID_REQUISICION(Integer ID_REQUISICION) {
        this.ID_REQUISICION = ID_REQUISICION;
    }

    public Integer getID_PARTIDA() {
        return ID_PARTIDA;
    }

    public void setID_PARTIDA(Integer ID_PARTIDA) {
        this.ID_PARTIDA = ID_PARTIDA;
    }

    public String getNOMBRE_PROVEEDOR() {
        return NOMBRE_PROVEEDOR;
    }

    public void setNOMBRE_PROVEEDOR(String NOMBRE_PROVEEDOR) {
        this.NOMBRE_PROVEEDOR = NOMBRE_PROVEEDOR;
    }

    public String getFECHA_REQUISICION() {
        return FECHA_REQUISICION;
    }

    public void setFECHA_REQUISICION(String FECHA_REQUISICION) {
        this.FECHA_REQUISICION = FECHA_REQUISICION;
    }

    public Integer getID_UEN() {
        return ID_UEN;
    }

    public String getCODIGO_CC() {
        return CODIGO_CC;
    }

    public void setCODIGO_CC(String CODIGO_CC) {
        this.CODIGO_CC = CODIGO_CC;
    }
    
    

    public void setID_UEN(Integer ID_UEN) {
        this.ID_UEN = ID_UEN;
    }

    public Integer getID_ORDEN_DE_COMPRA() {
        return ID_ORDEN_DE_COMPRA;
    }

    public void setID_ORDEN_DE_COMPRA(Integer ID_ORDEN_DE_COMPRA) {
        this.ID_ORDEN_DE_COMPRA = ID_ORDEN_DE_COMPRA;
    }

    public Integer getID_PROVEEDOR() {
        return ID_PROVEEDOR;
    }

    public void setID_PROVEEDOR(Integer ID_PROVEEDOR) {
        this.ID_PROVEEDOR = ID_PROVEEDOR;
    }

    public String getCLOSED_CODE() {
        return CLOSED_CODE;
    }

    public void setCLOSED_CODE(String CLOSED_CODE) {
        this.CLOSED_CODE = CLOSED_CODE;
    }

    public String getAUTHORIZATION_STATUS() {
        return AUTHORIZATION_STATUS;
    }

    public void setAUTHORIZATION_STATUS(String AUTHORIZATION_STATUS) {
        this.AUTHORIZATION_STATUS = AUTHORIZATION_STATUS;
    }

    public String getNOMBRE_CC() {
        return NOMBRE_CC;
    }

    public void setNOMBRE_CC(String NOMBRE_CC) {
        this.NOMBRE_CC = NOMBRE_CC;
    }
    
    public String getCANCEL_FLAG() {
        return CANCEL_FLAG;
    }

    public void setCANCEL_FLAG(String CANCEL_FLAG) {
        this.CANCEL_FLAG = CANCEL_FLAG;
    }

    public Integer getID_LOCALIZACION() {
        return ID_LOCALIZACION;
    }

    public void setID_LOCALIZACION(Integer ID_LOCALIZACION) {
        this.ID_LOCALIZACION = ID_LOCALIZACION;
    }

    public Integer getSHIP_TO_LOCATION_ID() {
        return SHIP_TO_LOCATION_ID;
    }

    public void setSHIP_TO_LOCATION_ID(Integer SHIP_TO_LOCATION_ID) {
        this.SHIP_TO_LOCATION_ID = SHIP_TO_LOCATION_ID;
    }

    public Integer getLOCALIZACION_IGUAL() {
        return LOCALIZACION_IGUAL;
    }

    public void setLOCALIZACION_IGUAL(Integer LOCALIZACION_IGUAL) {
        this.LOCALIZACION_IGUAL = LOCALIZACION_IGUAL;
    }

    public String getREQ_ESTADO() {
        return REQ_ESTADO;
    }

    public void setREQ_ESTADO(String REQ_ESTADO) {
        this.REQ_ESTADO = REQ_ESTADO;
    }

    public Integer getID_CC() {
        return ID_CC;
    }

    public void setID_CC(Integer ID_CC) {
        this.ID_CC = ID_CC;
    }

    public String getID_REQUISITOR() {
        return ID_REQUISITOR;
    }

    public void setID_REQUISITOR(String ID_REQUISITOR) {
        this.ID_REQUISITOR = ID_REQUISITOR;
    }

    public String getURGENTE() {
        return URGENTE;
    }

    public void setURGENTE(String URGENTE) {
        this.URGENTE = URGENTE;
    }

    public String getCOMPRADOR() {
        return COMPRADOR;
    }

    public void setCOMPRADOR(String COMPRADOR) {
        this.COMPRADOR = COMPRADOR;
    }

    public String getREQUISITOR() {
        return REQUISITOR;
    }

    public void setREQUISITOR(String REQUISITOR) {
        this.REQUISITOR = REQUISITOR;
    }

    public Double getCANTIDAD_REQUERIDA() {
        return CANTIDAD_REQUERIDA;
    }

    public void setCANTIDAD_REQUERIDA(Double CANTIDAD_REQUERIDA) {
        this.CANTIDAD_REQUERIDA = CANTIDAD_REQUERIDA;
    }

    public Double getCANTIDAD_ENTREGADA() {
        return CANTIDAD_ENTREGADA;
    }

    public void setCANTIDAD_ENTREGADA(Double CANTIDAD_ENTREGADA) {
        this.CANTIDAD_ENTREGADA = CANTIDAD_ENTREGADA;
    }

    public Double getCANTIDAD_PENDIENTE() {
        return CANTIDAD_PENDIENTE;
    }

    public void setCANTIDAD_PENDIENTE(Double CANTIDAD_PENDIENTE) {
        this.CANTIDAD_PENDIENTE = CANTIDAD_PENDIENTE;
    }

    

    public String getID_UNIDAD_DE_MEDIDA() {
        return ID_UNIDAD_DE_MEDIDA;
    }

    public void setID_UNIDAD_DE_MEDIDA(String ID_UNIDAD_DE_MEDIDA) {
        this.ID_UNIDAD_DE_MEDIDA = ID_UNIDAD_DE_MEDIDA;
    }

    public String getDESCRIPCION_PRODUCTO() {
        return DESCRIPCION_PRODUCTO;
    }

    public void setDESCRIPCION_PRODUCTO(String DESCRIPCION_PRODUCTO) {
        this.DESCRIPCION_PRODUCTO = DESCRIPCION_PRODUCTO;
    }

    public String getFECHA_REQUERIDA() {
        return FECHA_REQUERIDA;
    }

    public void setFECHA_REQUERIDA(String FECHA_REQUERIDA) {
        this.FECHA_REQUERIDA = FECHA_REQUERIDA;
    }

    public String getFUENTE() {
        return FUENTE;
    }

    public void setFUENTE(String FUENTE) {
        this.FUENTE = FUENTE;
    }

    public Integer getPedidoEspecial() {
        return pedidoEspecial;
    }

    public void setPedidoEspecial(Integer pedidoEspecial) {
        this.pedidoEspecial = pedidoEspecial;
    }
    


    
    
    
           
}
