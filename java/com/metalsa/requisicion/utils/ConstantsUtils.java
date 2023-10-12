/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.requisicion.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juliocisneros
 */
public class ConstantsUtils {

    public static final String DEFAULT_SI = "S";
    public static final String DEFAULT_NO = "N";
    public static final String ACTIVO = "A";
    public static final int ROL_COORD_PROCESO = 22;

    public static final String TIPO_REQUI_INTERNA = "INTERNA";
    public static final String TIPO_REQUI_EXTERNA = "EXTERNA";
    public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_PATTERN_DD_MM_YY = "dd/MM/yy";
    public static final String DATE_PATTERN_DD_MM_YYYY = "dd/MM/yyyy";
    public static final String DATE_PATTERN_DD_MMM_YYYY = "dd/MMM/yyyy";
    public static final String DD_MMM_YYYY_HH_MM_SS = "dd/MMM/yyyy HH:mm:ss"; //<ISSUE 3>

    public static final String STATUS_PROCESO_ACTIVO = "E";
    public static final String STATUS_PROCESO_INACTIVO = "D";

    public static final String DATOS_AUDITORIA_ADMON = "-Administración";
    public static final int SC_ACTIVO = 1;
    public static final String ESTATUS_RECHAZADA = "RECHAZADA";
    public static final String ESTATUS_REJECTED = "REJECTED";
    public static final String ESTATUS_RETURNED = "RETURNED";
    public static final String SRC_STOCK = "A";
    public static final String SRC_CONSIGMENT = "K";
    public static final String SRC_SUPPLIER = "P";
    public static final String SRC_EXTERNAL = "E";
    public static final String SRC_SPOT = "C";
    public static final String CERRADA_X_REINGRESO = "CERRADA_X_REINGRESO";
    public static final BigInteger ESTATUS_NUEVO = BigInteger.ONE;

    public static final String TIPO_INCOTERM_FOB = "FOB";
    public static final String TIPO_TERMINO_TRANSPORTE = "FREIGHT TERMS";
    public static final String CODIGO_INCOTERM_DDP = "DDP";

    public static final BigInteger STATUS_MONEDA_ACTIVE = new BigInteger("1");
    public static final String REGION_ALL = "ALL";
    public static final BigDecimal ESTATUS_SEL_COTIZACION = new BigDecimal(27);
    public static final BigDecimal ESTATUS_EN_PROCESO = new BigDecimal(49);
    public static final String ACCION_ACTIVACION = "action_cot_active";
    public static final String ACCION_DESACTIVACION = "action_cot_deactivate";

    // <CAT_VAR>
    public static final int PRECIO_FIJO = 1;
    public static final int PRECIO_ABIERTO = 2;
    public static final int PRECIO_FACTOR = 3;
    public static final int MATRIZ_RANGOS = 4;
    public static final int MATRIZ_MES = 5;

    public static final String TIPOMES = "TIPO_MES";
    public static final String TIPOANIO = "TIPO_ANIO";
    public static final String TIPOCANTIDAD = "TIPO_CANTIDAD";
    public static final String TIPOFORMULA = "TIPO_FORMULA";
    public static final String TIPOTOTAL = "TIPO_TOTAl";
    public static final String TIPONORMAL = "TIPO_NORMAL";
    // <CAT_VAR>
    public static final Integer CUENTA_PROYECTO = 1;
    public static final Integer CUENTA_CENTRO_COSTO = 2;
    public static final Integer CUENTA_MULTI_CENTRO_COSTO = 3;

    public static final String TYPE_INTRAUEN_1 = "I1";

    public enum ESTATUS {

        ESTATUS_POR_COTIZAR("POR COTIZAR"),
        ESTATUS_SEL_COTIZACION("SEL COTIZACION"),
        ESTATUS_SELECCIONADA("SELECCIONADA"),
        ESTATUS_SELECCIONADO("SELECCIONADO"),
        ESTATUS_SOL_INFO_REQUISITOR_COMPRADOR("SOL INFO REQ-COM"),
        ESTATUS_REGRESADA_A_COMPRADOR("REGRESADA A COMPRADOR"),
        ESTATUS_SOL_INFO_COMPRADOR_REQUISITOR("SOL INFO COM-REQ"),
        ESTATUS_DECLINADA("DECLINADA"),
        ESTATUS_ACTIVA("ACTIVA"),
        ESTATUS_INACTIVA("INACTIVA"),
        ESTATUS_SIN_SELECCIONAR("SIN SELECCIONAR"),
        ESTATUS_EN_PROCESO("EN PROCESO"),
        ESTATUS_PENDIENTE("PENDIENTE"),
        ESTATUS_COTIZADA("COTIZADA"),
        ESTATUS_CANCELADA("CANCELADA"),
        ESTATUS_POR_APROBAR("POR APROBAR"),
        ESTATUS_APROBACION_PROYECTO("APROBACION PROYECTO"),
        ESTATUS_SOL_INFO_COMPRADOR_PROVEEDOR("SOL INFO COMPRADOR-PROVEEDOR"),
        ESTATUS_SOL_INFO_PROVEEDOR_COMPRADOR("SOL INFO PROVEEDOR-COMPRADOR"),
        ESTATUS_NO_SELECCIONADA("NO SELECCIONADA"),
        ESTATUS_APROBADA("APROBADA"),
        ESTATUS_REGRESADA_POR_COMPRADOR("REGRESADA60"),
        ESTATUS_REGRESADA_POR_COMPRADOR_RFQ("REGRESADA61"),
        ESTATUS_CANTIDAD_MODIFICADA("CANTIDAD MODIFICADA"),
        ESTATUS_CANTIDAD_MODIFICADA_ENG("QUANTITY ALTERED"),//nestor.lopez ticket 271199 02-10-17
        ESTATUS_COTIZADA_POR_PROVEEDOR("COTIZADA POR PROVEEDOR"),
        ESTATUS_PUBLICADO("PUBLICADO"),
        ESTATUS_NO_PUBLICADO("NO PUBLICADO"),
        ESTATUS_EN_EDICION("EN EDICION"),
        ESTATUS_REGRESADA("REGRESADA"),
        ESTATUS_CERRADA("CERRADA");

        private final String descEstatus;

        ESTATUS(String descEstatus) {
            this.descEstatus = descEstatus;
        }

        public String getDescEstatus() {
            return descEstatus;
        }
    }

    public enum ACTIONS {

        BILL_TO_FLAG(BigInteger.valueOf(0)),
        SHIP_TO_FLAG(BigInteger.valueOf(1)),
        CREATE_REQUISITION(BigInteger.valueOf(1)),
        BACK_TO_BUYER(BigInteger.valueOf(2)),
        BACK_REQUISITOR(BigInteger.valueOf(3)),
        CREATE_RFQ(BigInteger.valueOf(4)),
        SEND_RFQ(BigInteger.valueOf(5)),
        ACTIVATE_QUOTATION(BigInteger.valueOf(6)),
        DISABLE_QUOTATION(BigInteger.valueOf(7)),
        APPROVE(BigInteger.valueOf(8)),
        SELECT_QUOTATION(BigInteger.valueOf(9)),
        CANCEL(BigInteger.valueOf(10)),
        REJECT(BigInteger.valueOf(11)),
        CREATE_OC(BigInteger.valueOf(12)),
        DELETE_RFQ(BigInteger.valueOf(32)); //<RM17613>

        private BigInteger id;

        ACTIONS(BigInteger id) {
            this.id = id;
        }

        public BigInteger getId() {
            return id;
        }

        public void setId(BigInteger id) {
            this.id = id;
        }

    }

    public static final BigDecimal ESTATUS_PENDIENTE = new BigDecimal(50);
    public static final BigDecimal ESTATUS_COTIZADA = new BigDecimal(51);

    public static final BigInteger ID_NOTIF_TEL = BigInteger.ONE;
    public static final BigInteger ID_NOTIF_ENVIO_OC = new BigInteger("13");
    public static final BigInteger ID_NOTIF_COT_URGENTES = new BigInteger("9");
    public static final BigInteger ID_NOTIF_COT_NORMAL = new BigInteger("10");

    public static final String EXTERNA_ESPECIAL = "MCCE";
    public static final String PROVEEDOR_ESPECIAL = "MCCP";
    public static final String TYPE_SPECIAL_1 = "E1";
    public static final String SRC_CONSIGMENT_USD = "D";
    public static final String SRC_OOC = "C";
    public static final String SRC_VALES = "V";
    public static final String SUPLIER_SEARCH = "P";

    public static final int BIN_TRUE = 1;
    public static final int BIN_FALSE = 0;

    public enum TIPO_RESPONSABILIDAD {

        RESPONSABLE(0),
        DELEGADO(1);

        int tipo;

        private TIPO_RESPONSABILIDAD(int tipo) {
            this.tipo = tipo;
        }

        public int getTipo() {
            return tipo;
        }
    }

    public enum TIPO_PROCESO_APROBACION {
        CATALOGO("Catálogo", 1);

        private final String tipoProcesoAprobacion;
        private final Integer tipoProcesoAprobacionId;

        /**
         * @param text
         */
        private TIPO_PROCESO_APROBACION(String tipoProcesoAprobacion, Integer tipoProcesoAprobacionId) {
            this.tipoProcesoAprobacion = tipoProcesoAprobacion;
            this.tipoProcesoAprobacionId = tipoProcesoAprobacionId;
        }

        public String getValue() {
            return tipoProcesoAprobacion;
        }

        public Integer getId() {
            return tipoProcesoAprobacionId;
        }

    }

    public enum TIPO_RECIBO {
        ALMACEN("Almacén", 1),
        ESCRITORIO("Escritorio", 2);

        private final String tipoRecibo;
        private final Integer tipoReciboId;

        /**
         * @param text
         */
        private TIPO_RECIBO(String tipoRecibo, Integer tipoReciboId) {
            this.tipoRecibo = tipoRecibo;
            this.tipoReciboId = tipoReciboId;
        }

        public String getTipoRecibo() {
            return tipoRecibo;
        }

        public Integer getTipoReciboId() {
            return tipoReciboId;
        }

    }

    public enum TIPO_DOCUMENTO {

        IMAGES(1),
        DOCUMENT(2);

        private int type;

        private TIPO_DOCUMENTO(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }
    }

    public enum TIPO_ITEM {

        CATALOGO(1),
        ALMACEN(2),
        SPOT(3),
        TODOS(3);

        private Integer type;

        private TIPO_ITEM(Integer type) {
            this.type = type;
        }

        public Integer getType() {
            return type;
        }
    }

    public enum SPX_TIPO_ELEMENTOS {

        ITEM("ITEM"),
        CATALOGO("CATALOGO"),
        CATALOGO_UEN("CATALOGO_UEN"), // <R17736/>
        REQUISICION("REQUISICION");

        private final String descipcion;

        /**
         * @param text
         */
        private SPX_TIPO_ELEMENTOS(String descipcion) {
            this.descipcion = descipcion;
        }

        public String getDescipcion() {
            return descipcion;
        }
    }

    public enum EVENTOS {

        EVENTO_NUEVO_CATALOGO("NUEVO_CATALOGO"),
        EVENTO_ACTUALIZA_CATALOGO("ACTUALIZA_CATALOGO"),
        EVENTO_ACTIVA_CATALOGO("ACTIVA_CATALOGO"),
        EVENTO_NUEVO_ITEM("NUEVO_ITEM"),
        EVENTO_ACTUALIZA_ITEM("ACTUALIZA_ITEM"),
        EVENTO_ACTUALIZA_ESTATUS_ITEM("ACTUALIZA_ESTATUS_ITEM"),
        EVENTO_ELIMINA_ITEM("ELIMINA_ITEM"),
        EVENTO_ACTIVA_ITEM("ACTIVA_ITEM"),
        EVENTO_INACTIVA_ITEM("INACTIVA_ITEM"),
        EVENTO_ENVIADO_APROBACION("EVENTO_ENVIADO_APROBACION"),
        EVENTO_APRUEBA_ITEM("APRUEBA_ITEM"),
        EVENTO_REGRESA_ITEM("REGRESA_ITEM"),
        EVENTO_ELIMINA_CATALOGO("ELIMINA_CATALOGO"),
        EVENTO_CARGA_PLANTILLA("CARGA_PLANTILLA"),
        EVENTO_OC_MANUAL_ON_OFF("EVENTO_OC_MANUAL_ON_OFF"), // <R17736/>
        EVENTO_DESCARGA_PLANTILLA("DESCARGA_PLANTILLA");

        private final String descEvento;

        EVENTOS(String descEvento) {
            this.descEvento = descEvento;
        }

        public String getDescEvento() {
            return descEvento;
        }
    }

    public enum SPX_RESPONSE_CODE {

        EXECUTED_OK("OK"),
        EXECUTED_WITH_ERRORS("OK_ERR"),
        EXECUTED_WRONG("99");

        private String responseCode;

        private SPX_RESPONSE_CODE(String responseCode) {
            this.responseCode = responseCode;
        }

        public String getResponseCode() {
            return responseCode;
        }

        public void setResponseCode(String responseCode) {
            this.responseCode = responseCode;
        }
    }

    public enum APP_ORIGEN {
        SPX("SPX"),
        DCOMPRAS("DCOMPRAS");

        private final String origen;

        private APP_ORIGEN(String origen) {
            this.origen = origen;
        }

        public String getOrigen() {
            return origen;
        }
    }

    public enum FUENTE_REQUISICION {

        FUENTE_SPOT("C"),
        FUENTE_ALMACEN("A"),
        FUENTE_CONSIGNACION("K"),
        FUENTE_LENTO_MOVIEMIENTO("L"),
        FUENTE_PROVEEDOR("P"),
        FUENTE_VALES_FONDO("V"),
        FUENTE_INTERUEN("I");

        private final String fuente;

        private FUENTE_REQUISICION(String fuente) {
            this.fuente = fuente;
        }

        public String getFuente() {
            return fuente;
        }

//        public FUENTE_REQUISICION getFUENTE_REQUISICION(String fuente) {
//            switch (fuente) {
//            case "C":
//                return FUENTE_SPOT;
//            case "P":
//                return FUENTE_PROVEEDOR;
//            case "A":
//                return FUENTE_ALMACEN;
//            case "K":
//                return FUENTE_CONSIGNACION;
//            }
//            return null;
//        }
    }

    public enum PARAMETROS_CONFIGURACION {

        ASIGNACION_COMPRADOR("Asignacion de comprador"),
        COMPRADOR_POR_DEFAULT("Comprador por defecto"), //<RELEASE ARG/>
        PRE_APPROVAL_DEFAULT("Preaprobador default de la Uen"), //<RELEASE ARG/>
        CONSULTA_PPTO_CARRITO_SPX("SPX_CONSULTA_PPTO_CARRITO_POR_FUENTE"), // <R17639/>
        SPX_PREAPROBACION_FINANZAS("PREAPROBACION_FINANZAS"), // <MDA CONTRALOR />
        SPX_PREAPROBACION_POR_FAMILIAS("PREAPROBACION_POR_FAMILIAS"), // <MDA CONTRALOR />
        APROBACION_FINAL_POR_TIPO("APROBACION_FINAL_POR_TIPO"), // <MDA CONTRALOR />
        PREAPROBACION_PROYECTO("PREAPROBACION_PROYECTO"),
        ADMIN_PREAPROBADORES_PROYECTO("PRE_APPROVAL_ADMIN"),
        PREAPROBADOR_PROYECTO_DEFAULT("PRE_APPROVAL_DEFAULT");   // <MDA CONTRALOR />

        private final String nombreParametro;

        private PARAMETROS_CONFIGURACION(String nombreParametro) {
            this.nombreParametro = nombreParametro;
        }

        public String getNombreParametro() {
            return nombreParametro;
        }
    }

    public enum FUENTES {

        PROVEEDOR(1, "P", "Proveedor", "Internal Catalogo"),
        ALMACEN(2, "A", "Almacen", "Warehouse"),
        SPOT(3, "C", "Spot", "Spot"),
        CONSIGNACION(4, "K", "Consignacion", "Consignment"),
        LENTO_MOVIMIENTO(5, "L", "Lento Movimiento", "Slow Movement"),
        VALES_FONDO(6, "V", "Solicitud de Pago", "Payment request"),
        INTERUEN(7, "I", "Intercompañia", "Intercompany"),
        PUNCHOUT(8, "C", "SPOT Express", "SPOT Express"),
        PEDIDO_ESPECIAL(9, "C", "Pedido Especial", "Special Request");

        private final int codigo;
        private final String fuente;
        private final String descripcion;
        private final String description;

        private FUENTES(int codigo, String fuente, String descripcion, String description) {
            this.codigo = codigo;
            this.fuente = fuente;
            this.descripcion = descripcion;
            this.description = description;
        }

        public int getCodigo() {
            return codigo;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public String getFuente() {
            return fuente;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum TIPO_REQUISICIONES {
        EXTERNA(4, "EXTERNA"),
        INTERNA(5, "INTERNA");

        private final String tipo;
        private final int valor;

        private TIPO_REQUISICIONES(int valor, String tipo) {
            this.valor = valor;
            this.tipo = tipo;
        }

        public String getTipo() {
            return tipo;
        }

        public int getValor() {
            return valor;
        }
    }

    public static final java.text.SimpleDateFormat sdf_dd_mm_yyyy = new java.text.SimpleDateFormat(ConstantsUtils.DATE_PATTERN_DD_MM_YYYY);
    public static final java.text.SimpleDateFormat sdf_ddd_mm_yyyy = new java.text.SimpleDateFormat(ConstantsUtils.DATE_PATTERN_DD_MMM_YYYY); // <FAD />

    public static String formattDate_DD_MM_YYYY(Date date) {
        if (date != null) {
            return sdf_dd_mm_yyyy.format(date);
        }
        return null;
    }

    // <FAD>
    public static String formattDate_DDD_MM_YYYY(Date date) {
        if (date != null) {
            return sdf_ddd_mm_yyyy.format(date);
        }
        return null;
    }
    // </FAD>

    public static Date parseDate_DD_MM_YYYY(String date) {
        if (date != null) {
            try {
                return sdf_dd_mm_yyyy.parse(date);
            } catch (ParseException ex) {
                Logger.getLogger(ConstantsUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    public static Date parseDate_DD_MMM_YYYY(String date) {//<T455581>
        if (date != null) {
            try {
                return sdf_ddd_mm_yyyy.parse(date);
            } catch (ParseException ex) {
                Logger.getLogger(ConstantsUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

}
