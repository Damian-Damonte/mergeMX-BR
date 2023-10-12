/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author APOJO9952
 */
@Entity
@IdClass(SupplierSelSPX.class)
@XmlRootElement
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(name = "insertaComentarios",
            procedureName = "PKG_SELECCION_REQ.insertaComentarios", //SPX_COMPRAS_RETURN_REQ
            parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "requisiciones", type = String.class)
                ,
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "comentario", type = String.class)
                ,
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "id_usuario", type = String.class)
                ,
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "modulo", type = String.class)
                ,
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "estatus", type = String.class)
            })
    ,
     @NamedStoredProcedureQuery(name = "procesaAprobacionSeleccion",
            procedureName = "PKG_SELECCION_REQ.PRE_PROCESA",
            parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "PARAMETROS", type = String.class)
                ,
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ID_USUARIO", type = String.class)
                ,
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "requisiciones_a_procesar", type = String.class),
                
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "MSJ_USUARIO", type = String.class)
                
            })
    ,
     @NamedStoredProcedureQuery(name = "envioEmails",
            procedureName = "PKG_SELECCION_REQ.ENVIA_EMAIL_SELCOTIZACION",
            parameters = {                
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "ID_REQUISICION", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "ID_LINEA", type = Integer.class)
            }),
     @NamedStoredProcedureQuery(name = "prcRespDelRequisicion",
            procedureName = "SP_RESP_DEL_REQ",
            parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "PARAM_ID_UEN", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "PARAM_LENGUAJE", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "PARAM_CODIGO_CC", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "PARAM_IDREQUISICION", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "PARAM_RESULTADO", type = String.class)
                
            })
     
})
@NamedNativeQueries({
    @NamedNativeQuery(name = "SupplierSelSPX.findAllSupSpx",
            query
            = "SELECT DISTINCT\n" +
"       RFQL.ID_RFQ,\n" +
"       NULL RAZON_SELECCION_COTIZACION,\n" +
"       NVL (PROV.ID_PROV_INTERNO, PROV.ID_PROV_EXTERNO) ID_PROVEEDOR,\n" +
"       PROVL.DESC_ALTERNATIVA DESCRIPCION_PRODUCTO,\n" +
"       PROV.ID_COTIZACION NUM_COTIZACION,\n" +
"       PROV.ID_SITE ID_SUCURSAL_PROVEEDOR,\n" +
"       NVL (SITE.VENDOR_NAME, PROVT.NOMBRE) NOMBRE_PROVEEDOR,\n" +
"       PROVL.PRECIO_UNITARIO PRECIO_COTIZADO,\n" +
"       CC.VF_PRECIO_UNITARIO PRECIO_EXPRESS,\n" +
"       PROVL.TIEMPO_ENTREGA LEAD_TIME,\n" +
"       (PROVL.PRECIO_UNITARIO) MONTO,\n" +
"       PROVL.MONEDA,\n" +
"       TIPO_CAMBIO_F (PROVL.MONEDA, D.ID_UEN) TIPO_CAMBIO,\n" +
"       PROVL.COMENTARIO,\n" +
"       D.MEJOR_OPCION RECOMENDADA,\n" +
"       NULL GASTOS_IMPORTACION,\n" +
"       '' ATTRIBUTE7,\n" +
"       D.ID_UEN,\n" +
"       NVL (PROV.ID_PROV_INTERNO, 0) EXT,      \n" +
"       RC.NOMBRE_US STRRZ_SEL_EN,\n" +
"       RC.NOMBRE_ESA STRRZ_SEL_ES,\n" +
"       RC.ID_RAZON,\n" +
"       PROVL.MOSTRAR_DOC_QUOT,\n" +
"       CASE\n" +
"          WHEN     PROVL.ID_RAZON_SEL_COTIZACION IS NULL\n" +
"               AND PROVL.MOTIVO_SUGERIDA IS NULL\n" +
"          THEN\n" +
"             0\n" +
"          ELSE\n" +
"             1\n" +
"       END\n" +
"       MEJOR_OPCION,\n" +
"       (SELECT SUM (GT.PRECIO_UNITARIO * GT.CANTIDAD) \n" +
"        FROM NVC_TBL_GASTO_ADICIONAL GT WHERE GT.ID_REQ_LINEA_PROV = PROVL.ID_REQ_LINEA_PROV) GASTO,\n" +
"       (SELECT PKG_SELECCION_REQ.REGRESA_INFO_GASTOAD (PROVL.ID_REQ_LINEA_PROV, D.ID_UEN,D.ID_REQUISICION) FROM DUAL) JSONGASTOAD,\n" +
"       PROVL.ID_REQ_LINEA_PROV LINEA_PROV,\n" +
"       D.COMPRADOR,\n" +
"       (SELECT NOMBRE_USUARIO FROM USUARIO WHERE ID_USUARIO = D.COMPRADOR) NOMBRECOMPRADOR,\n" +
"       (SELECT COUNT (*) FROM NVC_TBL_DOCS_COTIZACION D WHERE D.ID_COTIZACION = PROV.ID_COTIZACION) TIENEDOCUMENTOS,\n" +
"       (SELECT COUNT (*) FROM PUNCHOUT_BACKORDER PB WHERE PB.ID_REQUISICION = D.ID_REQUISICION AND PB.ID_PARTIDA = D.ID_PARTIDA) TIENEBACKORDERS\n" +
"    FROM NVC_TBL_RFQ_PROV PROV\n" +
"    JOIN NVC_TBL_REQ_LINEA_PROV PROVL ON PROV.LLAVE_ID = PROVL.ID_RFQ_PROV AND PROVL.ID_ESTATUS = 48\n" +
"    JOIN NVC_TBL_RFQ_LINEA RFQL ON PROVL.LLAVE_ID = RFQL.LLAVE_ID AND RFQL.ID_REQUISICION = ?1 AND RFQL.ID_LINEA = ?2\n" +
"    JOIN DETALLE_DE_REQUISICION D ON D.ID_REQUISICION = RFQL.ID_REQUISICION AND D.ID_PARTIDA = RFQL.ID_LINEA\n" +
"    LEFT JOIN RAZON_SEL_COTIZACION RC ON RC.ID_RAZON = PROVL.ID_RAZON_SEL_COTIZACION\n" +
"    LEFT JOIN NVC_TBL_PROV_SITES_H SITE ON SITE.ID_PROVEEDOR = PROV.ID_PROV_INTERNO AND SITE.ID_SUCURSAL_PROVEEDOR = PROV.ID_SITE\n" +
"    LEFT JOIN NVC_TBL_PROV_TEMPORAL PROVT ON PROVT.ID_PROVEEDOR = PROV.ID_PROV_EXTERNO\n" +
"    LEFT JOIN RAZON_SEL_COTIZACION RC ON RC.ID_RAZON = PROVL.ID_RAZON_SEL_COTIZACION\n" +
"    LEFT JOIN NVC_TBL_PROV_SITES_H SITE ON SITE.ID_PROVEEDOR = PROV.ID_PROV_INTERNO AND SITE.ID_SUCURSAL_PROVEEDOR = PROV.ID_SITE\n" +
"    LEFT JOIN NVC_TBL_PROV_TEMPORAL PROVT ON PROVT.ID_PROVEEDOR = PROV.ID_PROV_EXTERNO\n" + 
"    LEFT JOIN NVC_TBL_CARRO_COMPRA CC ON CC.ID_REQUISICION = D.ID_REQUISICION AND CC.ID_PARTIDA = D.ID_PARTIDA AND CC.PUNCHOUT = 1",
            resultClass = SupplierSelSPX.class, resultSetMapping = "SupplierSelMapping2"
    ),
    @NamedNativeQuery(name = "SupplierSelSPX.updatePartidasDetalle",
            query = "UPDATE DETALLE_DE_REQUISICION SET ID_ESTATUS = 27 "
            + "         WHERE ID_REQUISICION = ?1 "
            + "         AND ID_PARTIDA = ?2"
    )
    ,
     @NamedNativeQuery(name = "SupplierSelSPX.updatePartidasLinea",
            query = "  UPDATE NVC_TBL_REQ_LINEA_PROV SET ID_ESTATUS = 28 "
            + "         WHERE LLAVE_ID IN ( "
            + "         SELECT ID_RFQ from NVC_TBL_RFQ_LINEA "
            + "         WHERE ID_REQUISICION = ?1  "
            + "         AND ID_LINEA = ?2 "
            + "         )"
    ),
     @NamedNativeQuery(name = "SupplierSelSPX.regresaAprobadoresProyecto", 
             query="SELECT u.nombre_usuario NOMBRE_APROBADOR\n" +
"           FROM usuario u, nvc_vm_oa_proyecto_aprobador ap\n" +
"          WHERE     ap.id_proyecto = (SELECT DISTINCT id_proyecto\n" +
"                                        FROM detalle_de_requisicion\n" +
"                                       WHERE id_requisicion = ?1)\n" +
"                AND ap.id_empleado = u.id_empleado")
})
@SqlResultSetMappings({
    @SqlResultSetMapping(name = "SupplierSelMapping2", classes = {
        @ConstructorResult( 
            targetClass = SupplierSelSPX.class,
            columns = {
                @ColumnResult(name = "ID_RFQ", type = Integer.class)
                ,
                            @ColumnResult(name = "ID_PROVEEDOR", type = Integer.class)
                ,
                          @ColumnResult(name = "DESCRIPCION_PRODUCTO", type = String.class)
                ,
                          @ColumnResult(name = "NUM_COTIZACION", type = long.class)
                ,
                          @ColumnResult(name = "ID_SUCURSAL_PROVEEDOR", type = Integer.class)
                ,
                          @ColumnResult(name = "NOMBRE_PROVEEDOR", type = String.class)
                ,
                          @ColumnResult(name = "PRECIO_COTIZADO", type = Double.class)
                ,
                          @ColumnResult(name = "PRECIO_EXPRESS", type = Double.class)
                ,
                          @ColumnResult(name = "LEAD_TIME", type = String.class)
                ,
                          @ColumnResult(name = "MONTO", type = Double.class)
                ,
                          @ColumnResult(name = "MONEDA", type = String.class)
                ,
                          @ColumnResult(name = "COMENTARIO", type = String.class)
                ,
                          @ColumnResult(name = "RECOMENDADA", type = String.class)
                ,
                          @ColumnResult(name = "GASTOS_IMPORTACION", type = Double.class)
                ,
                          @ColumnResult(name = "ATTRIBUTE7", type = Double.class)
                ,
                          @ColumnResult(name = "ID_UEN", type = Integer.class)
                ,
                          @ColumnResult(name = "EXT", type = Integer.class)
                ,   
                          @ColumnResult(name = "MEJOR_OPCION", type = Integer.class)
                ,
                          @ColumnResult(name = "GASTO", type = Double.class)
                ,
                          @ColumnResult(name = "LINEA_PROV", type = Integer.class)
                ,
                          @ColumnResult(name = "RAZON_SELECCION_COTIZACION", type = Integer.class),
                          
                          @ColumnResult(name="STRRZ_SEL_EN", type = String.class),
                          
                          @ColumnResult(name="COMPRADOR", type = String.class),
                          
                          @ColumnResult(name="STRRZ_SEL_ES", type = String.class),
                          
                          @ColumnResult(name="JSONGASTOAD", type = String.class),
                          
                          @ColumnResult(name="ID_RAZON", type = Integer.class),
                          
                          @ColumnResult(name="TIENEDOCUMENTOS", type = Integer.class),
                          
                          @ColumnResult(name="TIENEBACKORDERS", type = Integer.class),
                          
                          @ColumnResult(name="NOMBRECOMPRADOR", type = String.class),
                    
                          @ColumnResult(name="TIPO_CAMBIO", type = Double.class),
                          
                          @ColumnResult(name = "MOSTRAR_DOC_QUOT", type = Integer.class)
            })})})
public class SupplierSelSPX implements Serializable {

    @Id
    private Integer ID_RFQ;
    @Id
    private Integer ID_PROVEEDOR;
    private String DESCRIPCION_PRODUCTO;
    @Id
    private long NUM_COTIZACION;
    private Integer ID_SUCURSAL_PROVEEDOR;
    private String NOMBRE_PROVEEDOR;
    private Double PRECIO_COTIZADO;
    private Double PRECIO_EXPRESS;
    private String LEAD_TIME;
    private Double MONTO;
    private String MONEDA;
    private String COMENTARIO;
    private String RECOMENDADA;
    private Double GASTOS_IMPORTACION;
    private Double ATTRIBUTE7;
    private Integer ID_UEN;
    private Integer EXT;
    private Integer MEJOR_OPCION;
    private Double GASTO;
    private Integer LINEA_PROV;
    private Integer RAZON_SELECCION_COTIZACION;
    private String STRRZ_SEL_EN;
    private String STRRZ_SEL_ES;
    private String COMPRADOR;
    private String JSONGASTOAD;
    private Integer ID_RAZON;
    private Integer TIENEDOCUMENTOS;
    private String NOMBRECOMPRADOR;
    private Double TIPO_CAMBIO;
    private Integer MOSTRAR_DOC_QUOT;
    private boolean hasBackorders;
    @Transient
    private List<Backorder> backorders;

    public String getJSONGASTOAD() {
        return JSONGASTOAD;
    }
    public void setJSONGASTOAD(String JSONGASTOAD) {
        this.JSONGASTOAD = JSONGASTOAD;
    }
    
    
    public Integer getRAZON_SELECCION_COTIZACION() {
        return RAZON_SELECCION_COTIZACION;
    }

    public void setMOTIVO(Integer RAZON_SELECCION_COTIZACION) {
        this.RAZON_SELECCION_COTIZACION = RAZON_SELECCION_COTIZACION;
    }

    public SupplierSelSPX() {
    }
//More than 10 parameters (found 29).
    public SupplierSelSPX(Integer ID_RFQ, Integer ID_PROVEEDOR, String DESCRIPCION_PRODUCTO, 
            long NUM_COTIZACION, Integer ID_SUCURSAL_PROVEEDOR, String NOMBRE_PROVEEDOR, 
            Double PRECIO_COTIZADO, Double PRECIO_EXPRESS, String LEAD_TIME, Double MONTO, String MONEDA, 
            String COMENTARIO, String RECOMENDADA, Double GASTOS_IMPORTACION, 
            Double ATTRIBUTE7, Integer ID_UEN, Integer EXT, Integer MEJOR_OPCION, 
            Double GASTO, Integer LINEA_PROV, Integer RAZON_SELECCION_COTIZACION,
            String STRRZ_SEL_EN, String COMPRADOR, String STRRZ_SEL_ES
            , String JSONGASTOAD, Integer ID_RAZON, Integer TIENEDOCUMENTOS, Integer TIENEBACKORDERS,
            String NOMBRECOMPRADOR, Double TIPO_CAMBIO, Integer MOSTRAR_DOC_QUOT
    ) {
        this.ID_RFQ = ID_RFQ;
        this.ID_PROVEEDOR = ID_PROVEEDOR;
        this.DESCRIPCION_PRODUCTO = DESCRIPCION_PRODUCTO;
        this.NUM_COTIZACION = NUM_COTIZACION;
        this.ID_SUCURSAL_PROVEEDOR = ID_SUCURSAL_PROVEEDOR;
        this.NOMBRE_PROVEEDOR = NOMBRE_PROVEEDOR;
        this.PRECIO_COTIZADO = PRECIO_COTIZADO;
        this.PRECIO_EXPRESS = PRECIO_EXPRESS;
        this.LEAD_TIME = LEAD_TIME;
        this.MONTO = MONTO;
        this.MONEDA = MONEDA;
        this.COMENTARIO = COMENTARIO;
        this.RECOMENDADA = RECOMENDADA;
        this.GASTOS_IMPORTACION = GASTOS_IMPORTACION;
        this.ATTRIBUTE7 = ATTRIBUTE7;
        this.ID_UEN = ID_UEN;
        this.EXT = EXT;
        this.MEJOR_OPCION = MEJOR_OPCION;
        this.GASTO = GASTO;
        this.LINEA_PROV = LINEA_PROV;
        this.RAZON_SELECCION_COTIZACION = RAZON_SELECCION_COTIZACION;
        this.STRRZ_SEL_EN = STRRZ_SEL_EN;
        this.COMPRADOR = COMPRADOR;
        this.STRRZ_SEL_ES = STRRZ_SEL_ES;
        this.JSONGASTOAD = JSONGASTOAD;
        this.ID_RAZON = ID_RAZON;
        this.TIENEDOCUMENTOS = TIENEDOCUMENTOS;
        this.hasBackorders = TIENEBACKORDERS > 0;
        this.NOMBRECOMPRADOR= NOMBRECOMPRADOR;
        this.TIPO_CAMBIO = TIPO_CAMBIO;
        this.MOSTRAR_DOC_QUOT = MOSTRAR_DOC_QUOT;
    }

    public Integer getMOSTRAR_DOC_QUOT() {
        return MOSTRAR_DOC_QUOT;
    }

    public void setMOSTRAR_DOC_QUOT(Integer MOSTRAR_DOC_QUOT) {
        this.MOSTRAR_DOC_QUOT = MOSTRAR_DOC_QUOT;
    }

    public Integer getID_RAZON() {
        return ID_RAZON;
    }

    public void setID_RAZON(Integer ID_RAZON) {
        this.ID_RAZON = ID_RAZON;
    }
    
    public Integer getID_RFQ() {
        return ID_RFQ;
    }

    public void setID_RFQ(Integer ID_RFQ) {
        this.ID_RFQ = ID_RFQ;
    }

    public Integer getID_PROVEEDOR() {
        return ID_PROVEEDOR;
    }

    public void setID_PROVEEDOR(Integer ID_PROVEEDOR) {
        this.ID_PROVEEDOR = ID_PROVEEDOR;
    }

    public String getDESCRIPCION_PRODUCTO() {
        return DESCRIPCION_PRODUCTO;
    }

    public void setDESCRIPCION_PRODUCTO(String DESCRIPCION_PRODUCTO) {
        this.DESCRIPCION_PRODUCTO = DESCRIPCION_PRODUCTO;
    }

    public long getNUM_COTIZACION() {
        return NUM_COTIZACION;
    }

    public void setNUM_COTIZACION(long NUM_COTIZACION) {
        this.NUM_COTIZACION = NUM_COTIZACION;
    }

    public Integer getID_SUCURSAL_PROVEEDOR() {
        return ID_SUCURSAL_PROVEEDOR;
    }

    public void setID_SUCURSAL_PROVEEDOR(Integer ID_SUCURSAL_PROVEEDOR) {
        this.ID_SUCURSAL_PROVEEDOR = ID_SUCURSAL_PROVEEDOR;
    }

    public String getNOMBRE_PROVEEDOR() {
        return NOMBRE_PROVEEDOR;
    }

    public void setNOMBRE_PROVEEDOR(String NOMBRE_PROVEEDOR) {
        this.NOMBRE_PROVEEDOR = NOMBRE_PROVEEDOR;
    }

    public Double getPRECIO_COTIZADO() {
        return PRECIO_COTIZADO;
    }

    public void setPRECIO_COTIZADO(Double PRECIO_COTIZADO) {
        this.PRECIO_COTIZADO = PRECIO_COTIZADO;
    }

    public Double getPRECIO_EXPRESS() {
        return PRECIO_EXPRESS;
    }

    public void setPRECIO_EXPRESS(Double PRECIO_EXPRESS) {
        this.PRECIO_EXPRESS = PRECIO_EXPRESS;
    }

    public String getLEAD_TIME() {
        return LEAD_TIME;
    }

    public void setLEAD_TIME(String LEAD_TIME) {
        this.LEAD_TIME = LEAD_TIME;
    }

    public Double getMONTO() {
        return MONTO;
    }

    public void setMONTO(Double MONTO) {
        this.MONTO = MONTO;
    }

    public String getMONEDA() {
        return MONEDA;
    }

    public void setMONEDA(String MONEDA) {
        this.MONEDA = MONEDA;
    }

    public String getCOMENTARIO() {
        return COMENTARIO;
    }

    public void setCOMENTARIO(String COMENTARIO) {
        this.COMENTARIO = COMENTARIO;
    }

    public String getRECOMENDADA() {
        return RECOMENDADA;
    }

    public void setRECOMENDADA(String RECOMENDADA) {
        this.RECOMENDADA = RECOMENDADA;
    }

    public Double getGASTOS_IMPORTACION() {
        return GASTOS_IMPORTACION;
    }

    public void setGASTOS_IMPORTACION(Double GASTOS_IMPORTACION) {
        this.GASTOS_IMPORTACION = GASTOS_IMPORTACION;
    }

    public Double getATTRIBUTE7() {
        return ATTRIBUTE7;
    }

    public void setATTRIBUTE7(Double ATTRIBUTE7) {
        this.ATTRIBUTE7 = ATTRIBUTE7;
    }

    public Integer getID_UEN() {
        return ID_UEN;
    }

    public void setID_UEN(Integer ID_UEN) {
        this.ID_UEN = ID_UEN;
    }

    public Integer getEXT() {
        return EXT;
    }

    public void setEXT(Integer EXT) {
        this.EXT = EXT;
    }

    public Integer getMEJOR_OPCION() {
        return MEJOR_OPCION;
    }

    public void setMEJOR_OPCION(Integer MEJOR_OPCION) {
        this.MEJOR_OPCION = MEJOR_OPCION;
    }

    public Double getGASTO() {
        return GASTO;
    }

    public void setGASTO(Double GASTO) {
        this.GASTO = GASTO;
    }

    public Integer getLINEA_PROV() {
        return LINEA_PROV;
    }

    public void setLINEA_PROV(Integer LINEA_PROV) {
        this.LINEA_PROV = LINEA_PROV;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.ID_RFQ);

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
        return getClass() == obj.getClass();
    }

    public String getSTRRZ_SEL_EN() {
        return STRRZ_SEL_EN;
    }

    public void setSTRRZ_SEL_EN(String STRRZ_SEL_EN) {
        this.STRRZ_SEL_EN = STRRZ_SEL_EN;
    }
    
    public String getSTRRZ_SEL_ES() {
        return STRRZ_SEL_ES;
    }

    public void setSTRRZ_SEL_ES(String STRRZ_SEL_ES) {
        this.STRRZ_SEL_ES = STRRZ_SEL_ES;
    }

    public String getCOMPRADOR() {
        return COMPRADOR;
    }

    public void setCOMPRADOR(String COMPRADOR) {
        this.COMPRADOR = COMPRADOR;
    }
    
    

    @Override
    public String toString() {
        return "SupplierSelSPX{" + "ID_RFQ=" + ID_RFQ + ", ID_PROVEEDOR=" + ID_PROVEEDOR + 
                ", DESCRIPCION_PRODUCTO=" + DESCRIPCION_PRODUCTO + ", NUM_COTIZACION=" + 
                NUM_COTIZACION + ", ID_SUCURSAL_PROVEEDOR=" + ID_SUCURSAL_PROVEEDOR + 
                ", NOMBRE_PROVEEDOR=" + NOMBRE_PROVEEDOR + ", PRECIO_COTIZADO=" + 
                PRECIO_COTIZADO + ", LEAD_TIME=" + LEAD_TIME + ", MONTO=" + 
                MONTO + ", MONEDA=" + MONEDA + ", COMENTARIO=" + COMENTARIO + 
                ", RECOMENDADA=" + RECOMENDADA + ", GASTOS_IMPORTACION=" + 
                GASTOS_IMPORTACION + ", ATTRIBUTE7=" + ATTRIBUTE7 + ", ID_UEN=" + 
                ID_UEN + ", EXT=" + EXT + ", MEJOR_OPCION=" + MEJOR_OPCION + ", GASTO=" + 
                GASTO + ", LINEA_PROV=" + LINEA_PROV + '}';
    }

    public Integer getTieneDocumentos() {
        return TIENEDOCUMENTOS;
    }

    public void setTieneDocumentos(Integer tieneDocumentos) {
        this.TIENEDOCUMENTOS = tieneDocumentos;
    }

    public String getNombreComprador() {
        return NOMBRECOMPRADOR;
    }

    public void setNombreComprador(String nombreComprador) {
        this.NOMBRECOMPRADOR = nombreComprador;
    }

    public Double getTIPO_CAMBIO() {
        return TIPO_CAMBIO;
    }

    public void setTIPO_CAMBIO(Double TIPO_CAMBIO) {
        this.TIPO_CAMBIO = TIPO_CAMBIO;
    }

    public boolean hasBackorders() {
        return hasBackorders;
    }

    public void setHasBackorders(boolean hasBackorders) {
        this.hasBackorders = hasBackorders;
    }

    public List<Backorder> getBackorders() {
        return backorders;
    }

    public void setBackorders(List<Backorder> backorders) {
        this.backorders = backorders;
    }
}