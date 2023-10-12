package com.metalsa.utils;

import org.springframework.context.i18n.LocaleContextHolder;

/**
 * Created by ruben.bresler on 05/07/2017.
 */
public class Constants {

    public static final String BOOLEAN_YES = "S";
    public static final String BOOLEAN_NO = "N";

    public static final Long ROL_COMPRADOR = 3L;
    public static final Long ROL_REQUISITOR = 1L;
    public static final Long ROL_INTER_UEN = 18L;

    public static final String URI_API_CATALOGOS = "/api/v1/catalogo";
    public static final String URI_API_USUARIOS = "/api/v1/user";
    public static final String URI_API_MOTOR = "/api/v1/motor";
    public static final String URI_API_MENU = "/api/v1/menu";
    public static final String URI_API_APROBACIONES = "/api/v1/aprobacion";
    public static final String URI_API_REQUISIONES = "/api/v1/req";
    public static final String URI_API_REQUISIONES_B = "/api/v1/requisiciones";
    public static final String URI_API_CARROCOMPRAS = "/api/v1/cart";
    public static final String URI_API_UENS = "/api/v1/uens";
    public static final String URI_API_CC = "/api/v1/cc";
    public static final String URI_API_CURRENCY = "/api/v1/currency";
    public static final String URI_API_PROYECTO = "/api/v1/proyecto";
    public static final String URI_API_UX = "/api/v1/ux";
    public static final String URI_API_CUENTA_FAM_CC = "/api/v1/cuentaFCC";
    public static final String URI_API_PARAMETROS = "/api/v1/config";
    public static final String URI_API_BITACORA = "/api/v1/bitacora";
    public static final String URI_API_HOME = "/api/v1/home";
    public static final String URI_API_PROYECTO_PREAPROBACION = "/api/v1/proyecto/preaprobacion";//<RELEASE ARG/>
    public static final String URI_API_FAMILIA = "/api/v1/familia";//<RELEASE_BRASIL>
	public static final String URI_API_FINAZAS = "/api/v1/finanzas"; //<jl>
    public static final String URI_API_REPORTES = "/api/v1/reportes";//<R16788>
    public static final String URI_API_PERFIL = "/api/v1/perfil";
    public static final String URI_API_CONTRALOR = "/api/v1/contralor";//<MDA_CONTRALOR>
    public static final String URI_API_CORREOS = "/api/v1/correos";
    public static final String URI_API_PROVEEDOR_PORTAL = "/api/v1/proveedor";
    public static final String URI_API_PROVEEDOR_DIRIGIDO = "/api/v1/provDirigidos";
    public static final String URI_API_NOTICIAS = "/api/v1/noticias";
    public static final String URI_API_CONTRALOR_P = "/api/v1/contralorCC";
    public static final String URI_API_ALMACEN = "/api/v1/almacen";
    public static final String URI_API_MATRIZ_PAGOS = "/api/v1/matrizPagos";
    public static final String URI_API_UTILERIAS = "/api/v1/utilerias";
    public static final String URI_API_INVENTARIO_FISICO = "/api/v1/inventarioFisico";
    public static final String TIPO_DETALLE_REQUISICION_ALMACEN = "A";
    public static final String TIPO_DETALLE_REQUISICION_SPOT = "C";
    public static final String TIPO_DETALLE_REQUISICION_PROVEEDOR = "P";

    public static final String LANG_ES = "ESA";
    public static final String LANG_US = "US";
    public static final String LANG_PT = "PTB";

    public static final String REPORT_CHAT = "/reports/aprobacion/MensajesRequisitor.jasper";
    //<RELEASE ARG>
    public static final String ADMIN_PREAPROBACION = "PRE_APPROVAL_ADMIN";
    public static final String PREAPROBADOR_DEFAULT = "PRE_APPROVAL_DEFAULT";
    //</RELEASE ARG>

    //<R16788>
    public final static String AUTHORITY = "https://login.windows.net/common/oauth2/authorize";
    public final static String CLIENT_ID = "7859f948-02a3-4cc7-85be-eb9560231eed";
    public final static String RESOURSE = "https://analysis.windows.net/powerbi/api";
    public final static String METALSA = "grupometalsa.onmicrosoft.com";
    public final static String GROUPS = "groups";
    public final static String REPORTS = "reports";
    public final static String DASHBOARDS = "dashboards";
    public final static String PATH_URL = "https://api.powerbi.com/v1.0/myorg";
    public final static String URL_REPORT = PATH_URL + "/groups/_grupid_/reports/_reportid_";
    public final static String URL_REPORTS = PATH_URL + "/groups/_grupid_/reports/";
    public final static String URL_DASHBOARD = PATH_URL + "/groups/_grupid_/dashboards/_dashboardid_";
    public final static String URL_DASHBOARDS = PATH_URL + "/groups/_grupid_/dashboards";
    public final static String URL_GENERATE_TOKEN = URL_REPORT + "/GenerateToken";
    ///<R16788>
    /*MDA CC Y FAMILIAS*/
    public static final String URI_API_COMPRADORCC = "/api/v1/compradorCc";

    public static final String PLANTILLA = "plantilla";
    public static final String REPORTE = "reporte";
    public static final String PLANTILLATOBB = "plantillaToBB";
    public static final String TIPO_PESTANA_CC = "comprador";
    public static final String TIPO_PESTANA_FAM = "familia";
    public static final String PLANTILLAFAM = "plantillaFam";
    public static final String REPORTEFAM = "reporteFam";
    /**
     * ************** END ****************
     */
    /**
     * **Asignacion aprobadores familias***
     */
    public static final String URI_API_ASIGNACION_FAMILIAS = "/api/v1/asignacionFamilias";
    /**
     * ************** END ****************
     */

    /*MDA ADMINISTRACION CATEGORIAS*/
    public static final String URL_CATEGORIAS = "/api/v1/conf_fam";

    /**/
    public static enum RESPONSE {
        SUCCESS("SUCCESS", 1),
        ERROR("ERROR", -1),
        NOT_EXECUTED("NOT_EXECUTED", 0);

        private final int CODE;
        private final String MSG;

        RESPONSE(String MSG, int CODE) {
            this.CODE = CODE;
            this.MSG = MSG;
        }

        public int getCODE() {
            return CODE;
        }

        public String getMSG() {
            return MSG;
        }

    }

    public static enum ACTIONS {
        INSERT, UPDATE, DELETE
    };

    public static String getIdiomaFromRequest() {
        if ("esa".equalsIgnoreCase(LocaleContextHolder.getLocale().getLanguage()) || "es".equalsIgnoreCase(LocaleContextHolder.getLocale().getLanguage())) {
            return LANG_ES;
        } else if ("en".equalsIgnoreCase(LocaleContextHolder.getLocale().getLanguage())
                || "us".equalsIgnoreCase(LocaleContextHolder.getLocale().getLanguage())) {
            return LANG_US;
        } else if ("pt".equalsIgnoreCase(LocaleContextHolder.getLocale().getLanguage())) {
            return LANG_PT;
        }
        return "";
    }

    //<RELEASE ARG>
    public static String getIdioma(String idioma) {
        switch (idioma.toLowerCase()) {
            case "es":
                return "ESA";
            case "en":
                return "US";
            case "pt":
            case "ptb":
                return "PTB";
            default:
                return idioma;
        }
    }

    public static String genericConfController = "{\n"
            + "      \"confResp\":{ \n"
            + "         \"idParameter\":75,\n"
            + "         \"idParameterUen\":351,\n"
            + "         \"parameterName\":\"PERMITIR_TRANSFERENCIAS_RESPONSABLE\",\n"
            + "         \"parameterDescription\":\"(Y,N) permitir al responsable de ccs realizar transferencias(establecer monto)\",\n"
            + "         \"condition\":\"Y\",\n"
            + "         \"type\":\"JSON\",\n"
            + "         \"values\":[ \n"
            + "            { \n"
            + "               \"id\":867,\n"
            + "               \"idParameterUen\":351,\n"
            + "               \"value\":\"9999999\",\n"
            + "               \"property\":\"MONTO_MAXIMO\",\n"
            + "               \"condition\":null\n"
            + "            }\n"
            + "         ],\n"
            + "         \"remplazable\":null\n"
            + "      },\n"
            + "      \"confDel\":{ \n"
            + "         \"idParameter\":78,\n"
            + "         \"idParameterUen\":354,\n"
            + "         \"parameterName\":\"PERMITIR_TRANSFERENCIAS_DELEGADO\",\n"
            + "         \"parameterDescription\":\"(Y,N) permitir realizar transferencias a los delegados de ccs\",\n"
            + "         \"condition\":\"N\",\n"
            + "         \"type\":\"JSON\",\n"
            + "         \"values\":[ \n"
            + "            { \n"
            + "               \"id\":871,\n"
            + "               \"idParameterUen\":354,\n"
            + "               \"value\":\"0\",\n"
            + "               \"property\":\"MONTO_MAXIMO\",\n"
            + "               \"condition\":null\n"
            + "            }\n"
            + "         ],\n"
            + "         \"remplazable\":null\n"
            + "      },\n"
            + "      \"confTransferCc\":{ \n"
            + "         \"id\":868,\n"
            + "         \"idParameterUen\":352,\n"
            + "         \"value\":\"Y\",\n"
            + "         \"property\":null,\n"
            + "         \"condition\":null\n"
            + "      },\n"
            + "      \"confTransferOtrosCc\":{ \n"
            + "         \"idParameter\":82,\n"
            + "         \"idParameterUen\":548,\n"
            + "         \"parameterName\":\"PERMITIR_TRANSFERENCIAS_OTROS_CCS\",\n"
            + "         \"parameterDescription\":\"(Y/N) permitir transferencias a ccs de los cuales no son responsables\",\n"
            + "         \"condition\":\"N\",\n"
            + "         \"type\":\"JSON\",\n"
            + "         \"values\":[ \n"
            + "            { \n"
            + "               \"id\":1407,\n"
            + "               \"idParameterUen\":548,\n"
            + "               \"value\":\"0\",\n"
            + "               \"property\":\"MONTO_MAXIMO\",\n"
            + "               \"condition\":null\n"
            + "            }\n"
            + "         ],\n"
            + "         \"remplazable\":null\n"
            + "      },\n"
            + "      \"ccsOrigen\":[ \n"
            + "\n"
            + "      ],\n"
            + "      \"ccsDestino\":[ \n"
            + "\n"
            + "      ]\n"
            + "}";

    //</RELEASE ARG>

    public static final int PRECIO_FIJO = 1;

    //<MDA_MULTICC>
    public static enum TIPO_CARGO {
        PROYECTO(1), CENTRO_COSTO(2), MULTI_CC(3);
        private final Integer idTipoCargo;

        TIPO_CARGO(Integer idTipoCargo) {
            this.idTipoCargo = idTipoCargo;
        }

        public Integer getIdTipoCargo() {
            return idTipoCargo;
        }
    }
    //<MDA_MULTICC>
    public static final String DATE_PATTERN_DD_MM_YYYY = "dd/MM/yyyy";
    
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
}
