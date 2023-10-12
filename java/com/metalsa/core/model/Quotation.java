/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author APOJO9952
 */
/**
 * Contiene informaci&oacute;n del las Cotizaciones.
 */
@Entity
@IdClass(Quotation.class)
@XmlRootElement
@NamedNativeQueries({
    @NamedNativeQuery(name = "Quotation.getQuotationsById",
            //            query = "  SELECT d.id_requisicion,"
            //            + "  d.id_partida,"
            //            + "  d.descripcion_producto,"
            //            + "  d.cantidad_requerida,"
            //            + "  d.id_unidad_de_medida,"
            //            + "  pu.valor ,"
            //            + "  req.prov,"
            //            + "  d.urgente,"
            //            //            + "  d.id_uen,"
            //            // + "  NVL (REQ.ATTRIBUTE3, ' ') ATTRIBUTE3,"
            //            // + "  NVL (REQ.ATTRIBUTE5, ' ') ATTRIBUTE5,"
            //            //+ "  NVL (REQ.ATTRIBUTE8, ' ') ATTRIBUTE8,"
            //            + "  0 ACTIVE,"
            //            + "  '' MOTIVO_REGRESO"
            //            + "  FROM REQUISICION r,"
            //            + "  DETALLE_DE_REQUISICION d,"
            //            + "  PARAMETROS_POR_UEN_CIA pu,"
            //            + "  PARAMETROS_CONFIGURACION p,"
            //            + "  (  SELECT c.id_requisicion,"
            //            + "  c.id_partida,"
            //            + "  COUNT (DISTINCT c.id_proveedor) prov"
            //            + "  ,c.attribute3,"
            //            + "  c.attribute5,"
            //            + "  c.attribute8"
            //            + "  FROM NVC_VM_OA_COTIZACIONES c,"
            //            + "  REQUISICION r,"
            //            + "  detalle_de_requisicion d"
            //            + "  WHERE     c.id_requisicion = r.id_requisicion"
            //            + "  AND d.id_requisicion = r.id_requisicion"
            //            + "  AND r.id_usuario = ?1"
            //            + "  AND d.estatus = 'SEL COTIZACION'"
            //            + "  GROUP BY c.id_requisicion,"
            //            + "  c.id_partida"
            //            + "  ,c.attribute3,"
            //            + "  c.attribute5,"
            //            + "  c.attribute8"
            //            + "  ORDER BY TO_NUMBER (c.id_requisicion) DESC, c.id_partida) req"
            //            + "  WHERE     r.id_requisicion = d.id_requisicion"
            //            + "  AND r.id_uen = pu.id_uen"
            //            + "  AND d.estatus = 'SEL COTIZACION'"
            //            + "  AND p.id_parametro = pu.id_parametro"
            //            + "  AND UPPER (p.nombre_parametro) ="
            //            + "  DECODE (d.urgente,"
            //            + " 'S', 'COTIZACIONES POR PARTIDA URGENTE',"
            //            + " 'COTIZACIONES POR PARTIDA')"
            //            + "  AND d.id_partida = req.id_partida"
            //            + "  AND d.id_requisicion = req.id_requisicion"
            //            + "  UNION"
            query = " SELECT D.ID_REQUISICION,"
            + "  D.ID_PARTIDA,"
            + "  D.DESCRIPCION_PRODUCTO,"
            + "  D.CANTIDAD_REQUERIDA,"
            + "  D.ID_UNIDAD_DE_MEDIDA,"
            + "  PU.VALOR VALOR,"
            + " (SELECT COUNT (*)"
            + "  FROM NVC_TBL_REQ_LINEA_PROV PROVL"
            + "  WHERE PROVL.LLAVE_ID = RFQL.LLAVE_ID AND PROVL.ID_ESTATUS = 48)"
            + "  PROV,"
            + "  D.URGENTE,"
            //            + "  D.ID_UEN,"
            // + "  ' ' ATTRIBUTE3,"
            // + "  ' ' ATTRIBUTE5,"
            //+ "  ' ' ATTRIBUTE8,"
            + "  1 ACTIVE,"
            + " (select PKG_SELECCION_REQ.ultimo_comentario(D.ID_REQUISICION,D.ID_PARTIDA,2) from dual) MOTIVO_REGRESO, " //SPX_COMPRAS_RETURN_REQ
            + " D.FECHA_REQUERIDA,  R.FECHA_REQUISICION, "
            + " RQ.ID_INCOTERM, (select descripcion from NVC_TBL_INCOTERM where id_incoterm = RQ.ID_INCOTERM) INCOTERM, "
            + " RQ.SHIP_TO, "
            + " RQ.BLL_TO BILL_TO, d.comentarios_comprador, d.comentarios_usuario, d.notes_to_buyer,"
            + " RQ.ID_MEDIO, (SELECT cm.descripcion FROM Nvc_Tbl_Medio m, NVC_TBL_CAT_MEDIO cm WHERE m.ID_MEDIO = RQ.ID_MEDIO and cm.ID_CAT_MEDIO = m.ID_CAT_MEDIO and m.ID_UEN = r.id_uen  ) MEDIO_TRANSPORTE, "
            + " (SELECT NVL(PER.VALOR,'N') VALOR \n" +
"             FROM nvc_tbl_notificacion NT, nvc_tbl_perfil PER \n" +
"             WHERE NT.ID_NOTIFICACION = PER.ID_NOTIFICACION \n" +
"             AND NT.CLAVE = 'SHOW_COTIZATION_TO_REQUEST' \n" +
"             AND PER.ID_USUARIO = D.COMPRADOR) DIO_COM_PERM_PREVIEW, "
            + "(CASE D.ID_TIPO_CARGO WHEN 3 THEN '0' ELSE \n"
            +"((SELECT CODIGO_CC FROM NVC_TBL_OA_CC_H WHERE ID_CC = D.ID_CC AND ID_UEN = D.ID_UEN)) END) CODIGO_CC,"
            + " (SELECT COUNT(1) FROM CC_POR_USUARIO cc WHERE cc.id_uen = D.ID_UEN AND cc.id_cc = D.ID_CC and cc.id_usuario = ?1 ) CON_PERMISOS,"
            + " D.MOSTRAR_DOC_QUOT, "
            + " (SELECT DESC_ESP FROM DC_ESTATUS where sc_id = RQ.ESTATUS ) ESTATUS_ESP_RQ, "
            + "(SELECT  DESC_ING FROM DC_ESTATUS where sc_id = RQ.ESTATUS  ) ESTATUS_ENG_RQ "
            + "  FROM REQUISICION R"
            + "  JOIN DETALLE_DE_REQUISICION D"
            + "  ON R.ID_REQUISICION = D.ID_REQUISICION"
            + "  JOIN NVC_TBL_RFQ_LINEA RFQL"
            + "  ON RFQL.ID_REQUISICION = D.ID_REQUISICION"
            + "  AND RFQL.ID_LINEA = D.ID_PARTIDA"
            + "  LEFT JOIN PARAMETROS_POR_UEN_CIA PU"
            + "  ON PU.ID_UEN = D.ID_UEN"
            + "  AND UPPER (PU.ID_PARAMETRO) = DECODE (D.URGENTE, 'S', 15, 14) "
            + "  JOIN nvc_tbl_rfq RQ ON RQ.ID_RFQ = RFQL.ID_RFQ "
            + "  WHERE    R.ID_USUARIO = ?1 "
            + "  AND D.ID_ESTATUS = 27"
            + "  AND 0 < ("
            + "  SELECT COUNT (*)"
            + "  FROM NVC_TBL_REQ_LINEA_PROV PROVL"
            + "  WHERE PROVL.LLAVE_ID = RFQL.LLAVE_ID"
            + "  AND PROVL.ID_ESTATUS = 48)"
            + " order by d.id_requisicion desc, d.urgente desc, d.id_partida asc ",
            resultClass = Quotation.class, resultSetMapping = "QuotationMap"
    )
})
@SqlResultSetMappings({
    @SqlResultSetMapping(name = "QuotationMap",
            classes = {
                @ConstructorResult(targetClass = Quotation.class,
                        columns = {
                            @ColumnResult(name = "ID_REQUISICION", type = Integer.class)
                            ,        
                          @ColumnResult(name = "ID_PARTIDA", type = Integer.class)
                            ,          
                          @ColumnResult(name = "DESCRIPCION_PRODUCTO", type = String.class)
                            ,   
                          @ColumnResult(name = "CANTIDAD_REQUERIDA", type = Double.class)
                            ,    
                          @ColumnResult(name = "ID_UNIDAD_DE_MEDIDA", type = String.class)
                            ,    
                          @ColumnResult(name = "VALOR", type = String.class)
                            ,          
                          @ColumnResult(name = "PROV", type = Integer.class)
                            ,          
                          @ColumnResult(name = "URGENTE", type = String.class)
                            ,          
                          @ColumnResult(name = "ACTIVE", type = Integer.class)
                            ,          
                          @ColumnResult(name = "MOTIVO_REGRESO", type = String.class)
                            ,
                          @ColumnResult(name = "FECHA_REQUISICION", type = String.class)
                            ,
                          @ColumnResult(name = "FECHA_REQUERIDA", type = String.class),
                          
                          @ColumnResult(name = "ID_INCOTERM", type = Integer.class),
                          @ColumnResult(name = "ID_MEDIO", type = Integer.class),
                          @ColumnResult(name = "INCOTERM", type = String.class),
                          @ColumnResult(name = "MEDIO_TRANSPORTE", type = String.class),
                          @ColumnResult(name = "SHIP_TO", type = String.class),
                          @ColumnResult(name = "BILL_TO", type = String.class),
                          @ColumnResult(name = "DIO_COM_PERM_PREVIEW", type = String.class),
                          @ColumnResult(name = "comentarios_comprador", type = String.class),
                          @ColumnResult(name = "comentarios_usuario", type = String.class),
                          @ColumnResult(name = "notes_to_buyer", type = String.class),
                          @ColumnResult(name = "CODIGO_CC", type = String.class),
                          @ColumnResult(name = "CON_PERMISOS", type = Integer.class),
                          @ColumnResult(name = "MOSTRAR_DOC_QUOT", type = Integer.class),
                          @ColumnResult(name = "ESTATUS_ESP_RQ", type = String.class),
                          @ColumnResult(name = "ESTATUS_ENG_RQ", type = String.class)
                          
                        })}
    )})

public class Quotation implements Serializable {
    private String comentarios_comprador;
    private String comentarios_usuario;
    private String notes_to_buyer;
    @Id
    private Integer ID_REQUISICION;
    @Id
    private Integer ID_PARTIDA;
    private String DESCRIPCION_PRODUCTO;
    private Double CANTIDAD_REQUERIDA;
    private String ID_UNIDAD_DE_MEDIDA;
    private String VALOR;
    private Integer PROV;
    private String URGENTE;
    private Integer ACTIVE;
    private String MOTIVO_REGRESO;
    private String FECHA_REQUISICION;
    private String FECHA_REQUERIDA;
    private Integer CON_PERMISOS;
    private Integer ID_INCOTERM;
    private Integer ID_MEDIO;
    private String INCOTERM;
    private String MEDIO_TRANSPORTE;
    private String SHIP_TO;
    private String BILL_TO;
    private String CODIGO_CC;
    private String DIO_COM_PERM_PREVIEW;
    private Integer MOSTRAR_DOC_QUOT;
    private String ESTATUS_ESP_RQ;
    private String ESTATUS_ENG_RQ;
    
    public Quotation() {
    }

    
    
    public Quotation(Integer ID_REQUISICION, Integer ID_PARTIDA,
            String DESCRIPCION_PRODUCTO, Double CANTIDAD_REQUERIDA,
            String ID_UNIDAD_DE_MEDIDA, String VALOR, Integer PROV,
            String URGENTE, Integer ACTIVE, String MOTIVO_REGRESO,
            String FECHA_REQUERIDA, String FECHA_REQUISICION,
            Integer ID_INCOTERM,
            Integer ID_MEDIO,
            String INCOTERM,
            String MEDIO_TRANSPORTE,
            String SHIP_TO,
            String BILL_TO,
            String DIO_COM_PERM_PREVIEW,
            String comentarios_comprador,
            String comentarios_usuario,
            String notes_to_buyer,
            String CODIGO_CC,
            Integer CON_PERMISOS,
            Integer MOSTRAR_DOC_QUOT,
            String ESTATUS_ESP_RQ, String ESTATUS_ENG_RQ) {
        this.ID_REQUISICION = ID_REQUISICION;
        this.ID_PARTIDA = ID_PARTIDA;
        this.DESCRIPCION_PRODUCTO = DESCRIPCION_PRODUCTO;
        this.CANTIDAD_REQUERIDA = CANTIDAD_REQUERIDA;
        this.ID_UNIDAD_DE_MEDIDA = ID_UNIDAD_DE_MEDIDA;
        this.VALOR = VALOR;
        this.PROV = PROV;
        this.URGENTE = URGENTE;
        this.ACTIVE = ACTIVE;
        this.MOTIVO_REGRESO = MOTIVO_REGRESO;
        this.FECHA_REQUERIDA = FECHA_REQUERIDA;
        this.FECHA_REQUISICION = FECHA_REQUISICION;
        this.ID_INCOTERM = ID_INCOTERM;
        this.ID_MEDIO = ID_MEDIO;
        this.INCOTERM = INCOTERM;
        this.MEDIO_TRANSPORTE = MEDIO_TRANSPORTE;
        this.SHIP_TO = SHIP_TO;
        this.BILL_TO = BILL_TO;
        this.DIO_COM_PERM_PREVIEW = DIO_COM_PERM_PREVIEW;
        this.comentarios_comprador = comentarios_comprador;
        this.comentarios_usuario = comentarios_usuario;
        this.notes_to_buyer = notes_to_buyer;
        this.CODIGO_CC = CODIGO_CC;
        this.CON_PERMISOS = CON_PERMISOS;
        this.MOSTRAR_DOC_QUOT = MOSTRAR_DOC_QUOT;
        this.ESTATUS_ENG_RQ = ESTATUS_ENG_RQ;
        this.ESTATUS_ESP_RQ = ESTATUS_ESP_RQ;
    }

    public Integer getMOSTRAR_DOC_QUOT() {
        return MOSTRAR_DOC_QUOT;
    }

    public void setMOSTRAR_DOC_QUOT(Integer MOSTRAR_DOC_QUOT) {
        this.MOSTRAR_DOC_QUOT = MOSTRAR_DOC_QUOT;
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

    public String getDESCRIPCION_PRODUCTO() {
        return DESCRIPCION_PRODUCTO;
    }

    public void setDESCRIPCION_PRODUCTO(String DESCRIPCION_PRODUCTO) {
        this.DESCRIPCION_PRODUCTO = DESCRIPCION_PRODUCTO;
    }

    public Double getCANTIDAD_REQUERIDA() {
        return CANTIDAD_REQUERIDA;
    }

    public void setCANTIDAD_REQUERIDA(Double CANTIDAD_REQUERIDA) {
        this.CANTIDAD_REQUERIDA = CANTIDAD_REQUERIDA;
    }

    public String getID_UNIDAD_DE_MEDIDA() {
        return ID_UNIDAD_DE_MEDIDA;
    }

    public void setID_UNIDAD_DE_MEDIDA(String ID_UNIDAD_DE_MEDIDA) {
        this.ID_UNIDAD_DE_MEDIDA = ID_UNIDAD_DE_MEDIDA;
    }

    public String getVALOR() {
        return VALOR;
    }

    public void setVALOR(String VALOR) {
        this.VALOR = VALOR;
    }

    public Integer getPROV() {
        return PROV;
    }

    public void setPROV(Integer PROV) {
        this.PROV = PROV;
    }

    public String getURGENTE() {
        return URGENTE;
    }

    public void setURGENTE(String URGENTE) {
        this.URGENTE = URGENTE;
    }

    public Integer getACTIVE() {
        return ACTIVE;
    }

    public void setACTIVE(Integer ACTIVE) {
        this.ACTIVE = ACTIVE;
    }

    public String getMOTIVO_REGRESO() {
        return MOTIVO_REGRESO;
    }

    public void setMOTIVO_REGRESO(String MOTIVO_REGRESO) {
        this.MOTIVO_REGRESO = MOTIVO_REGRESO;
    }

    public Integer getCON_PERMISOS() {
        return CON_PERMISOS;
    }

    public void setCON_PERMISOS(Integer CON_PERMISOS) {
        this.CON_PERMISOS = CON_PERMISOS;
    }
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.ID_REQUISICION);
        hash = 23 * hash + Objects.hashCode(this.ID_PARTIDA);
        hash = 23 * hash + Objects.hashCode(this.DESCRIPCION_PRODUCTO);
        hash = 23 * hash + Objects.hashCode(this.CANTIDAD_REQUERIDA);
        hash = 23 * hash + Objects.hashCode(this.ID_UNIDAD_DE_MEDIDA);
        hash = 23 * hash + Objects.hashCode(this.VALOR);
        hash = 23 * hash + Objects.hashCode(this.PROV);
        hash = 23 * hash + Objects.hashCode(this.URGENTE);
        hash = 23 * hash + Objects.hashCode(this.ACTIVE);
        hash = 23 * hash + Objects.hashCode(this.MOTIVO_REGRESO);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Quotation other = (Quotation) obj;
        if (!Objects.equals(this.ID_REQUISICION, other.ID_REQUISICION)) {
            return false;
        }
        return Objects.equals(this.ID_PARTIDA, other.ID_PARTIDA);
    }

    public String getFECHA_REQUISICION() {
        return FECHA_REQUISICION;
    }

    public void setFECHA_REQUISICION(String FECHA_REQUISICION) {
        this.FECHA_REQUISICION = FECHA_REQUISICION;
    }

    public String getFECHA_REQUERIDA() {
        return FECHA_REQUERIDA;
    }

    public void setFECHA_REQUERIDA(String FECHA_REQUERIDA) {
        this.FECHA_REQUERIDA = FECHA_REQUERIDA;
    }

    public Integer getID_INCOTERM() {
        return ID_INCOTERM;
    }

    public void setID_INCOTERM(Integer ID_INCOTERM) {
        this.ID_INCOTERM = ID_INCOTERM;
    }

    public Integer getID_MEDIO() {
        return ID_MEDIO;
    }

    public void setID_MEDIO(Integer ID_MEDIO) {
        this.ID_MEDIO = ID_MEDIO;
    }

    public String getINCOTERM() {
        return INCOTERM;
    }

    public void setINCOTERM(String INCOTERM) {
        this.INCOTERM = INCOTERM;
    }

    public String getMEDIO_TRANSPORTE() {
        return MEDIO_TRANSPORTE;
    }

    public void setMEDIO_TRANSPORTE(String MEDIO_TRANSPORTE) {
        this.MEDIO_TRANSPORTE = MEDIO_TRANSPORTE;
    }

    public String getSHIP_TO() {
        return SHIP_TO;
    }

    public void setSHIP_TO(String SHIP_TO) {
        this.SHIP_TO = SHIP_TO;
    }

    public String getBILL_TO() {
        return BILL_TO;
    }

    public void setBILL_TO(String BILL_TO) {
        this.BILL_TO = BILL_TO;
    }

    public String getDIO_COM_PERM_PREVIEW() {
        return DIO_COM_PERM_PREVIEW;
    }

    public void setDIO_COM_PERM_PREVIEW(String DIO_COM_PERM_PREVIEW) {
        this.DIO_COM_PERM_PREVIEW = DIO_COM_PERM_PREVIEW;
    }

    public String getComentarios_comprador() {
        return comentarios_comprador;
    }

    public void setComentarios_comprador(String comentarios_comprador) {
        this.comentarios_comprador = comentarios_comprador;
    }

    public String getComentarios_usuario() {
        return comentarios_usuario;
    }

    public void setComentarios_usuario(String comentarios_usuario) {
        this.comentarios_usuario = comentarios_usuario;
    }

    public String getNotes_to_buyer() {
        return notes_to_buyer;
    }

    public void setNotes_to_buyer(String notes_to_buyer) {
        this.notes_to_buyer = notes_to_buyer;
    }

    public String getCODIGO_CC() {
        return CODIGO_CC;
    }

    public void setCODIGO_CC(String CODIGO_CC) {
        this.CODIGO_CC = CODIGO_CC;
    }
    
    public String getESTATUS_ESP_RQ() {
        return ESTATUS_ESP_RQ;
    }

    public void setESTATUS_ESP_RQ(String ESTATUS_ESP_RQ) {
        this.ESTATUS_ESP_RQ = ESTATUS_ESP_RQ;
    }

    public String getESTATUS_ENG_RQ() {
        return ESTATUS_ENG_RQ;
    }

    public void setESTATUS_ENG_RQ(String ESTATUS_ENG_RQ) {
        this.ESTATUS_ENG_RQ = ESTATUS_ENG_RQ;
    }

}
