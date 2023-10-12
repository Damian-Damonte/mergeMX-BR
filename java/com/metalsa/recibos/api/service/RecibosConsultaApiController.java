/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.api.service;

import com.google.gson.Gson;
import com.metalsa.aprobacion.repository.CentroCostoRepository;
import com.metalsa.recibos.logic.ReciboConsultaLogic;
import com.metalsa.recibos.model.SpxTipoDeRequisicion;
import com.metalsa.recibos.pojo.TipoRequisicion;
import com.metalsa.recibos.repository.TipoRequisRecibosRepository;
import com.metalsa.recibos.repository.ReporteRecibosRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.metalsa.core.model.RespuestaSalida;
import com.metalsa.core.repository.LogRepository;
import com.metalsa.recibos.model.ImprimeRecibo;
import com.metalsa.recibos.model.ResultadoCambioResponsable;
import com.metalsa.recibos.model.PreguntasRecibos;
import com.metalsa.recibos.model.ReporteEstatus;
import com.metalsa.recibos.model.ReporteRecibos;
import com.metalsa.recibos.model.Requisitor;
import com.metalsa.recibos.model.Uom;
import com.metalsa.recibos.pojo.LineaPayload;
import com.metalsa.recibos.pojo.PayloadRecibos;
import com.metalsa.recibos.pojo.PojoAux;
import com.metalsa.recibos.pojo.RecibeRecibos;
import com.metalsa.recibos.pojo.RecibosParam;
import com.metalsa.recibos.pojo.RequiFolio;
import com.metalsa.recibos.pojo.ReporteParam;
import java.util.ArrayList;
import com.metalsa.recibos.repository.PreguntasRecibosRepository;
import com.metalsa.recibos.repository.ReciboConsultaRepository;
import java.util.List;
import lombok.extern.log4j.Log4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestParam;//<R17231>

/**
 *
 * @author yair.nunez
 */
@RestController
@Api(value = "Recibo consulta", tags = {"Recibo consulta Service API"})
@RequestMapping("/api/v1/recibo/consulta")
@Log4j
public class RecibosConsultaApiController {
    //MDA_REPORTES_FINANZAS: remover todo lo relacionado con "CentroCostoRepository"
    
    @Autowired
    private ReciboConsultaLogic reciboConsultaLogic;
    
    @Autowired
    private ReciboConsultaRepository reciboConsultaRepository;
    
    @Autowired
    private ReporteRecibosRepository reporteRecibosRepository;
    
    @Autowired
    private CentroCostoRepository centroCostoRepository;
    
    @Autowired
    private TipoRequisRecibosRepository tipoRequisRecibosRepository;
    
    @Autowired
    private PreguntasRecibosRepository preguntasRecibosRepository;
    
    @Autowired
    private LogRepository logRepository;
    
    //<R17231>
    @RequestMapping(value = "/reqsrecibir/{uen}/{requisitor}", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<TipoRequisicion> obtenerRecibos(
            @PathVariable("uen") String uen,
            @PathVariable("requisitor") String requisitor,
            @RequestParam(value = "idRequisicion", required = false) String idRequisicion, //<R19835>
            @RequestParam(value = "idOrdenCompra", required = false) String idOrdenCompra,
            @RequestParam(value = "usuario", required = false) String usuario
    ) { //<R19835>

        if (null != uen && "null".equals(uen)) {
            uen = null;
        }

        return reciboConsultaLogic.obtenerRecibos(uen, requisitor, idRequisicion, idOrdenCompra, usuario); //<R19835>
    }
    //</R17231>


    @RequestMapping(value = "/tiposrequis", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<SpxTipoDeRequisicion> getTiposRequis() {

        return tipoRequisRecibosRepository.findAll();

    }

    @RequestMapping(value = "/imprimeRecibo", method = RequestMethod.POST)
    public @ResponseBody
    Iterable<ImprimeRecibo> imprimeRecibo(@RequestBody RecibosParam recibosParam) {
        String P_USUARIO=recibosParam.getUsuario(), P_FORMAT_DATE = recibosParam.getIdioma(), P_ORDEN_COMPRA = recibosParam.getOrdenCompra(), P_FINI = recibosParam.getfIni(), P_FFIN =  recibosParam.getfFin(), P_FOLIO =  recibosParam.getFolio();
        String P_REQUICISION = "";
        //<R17486>
        String P_ID_UENS = recibosParam.getIdUens();
        String P_MOSTRAR_RECIBOS = recibosParam.isMostrarRecibos() ? "1" : "0";

        if (recibosParam.getIdRequi() != null) {
            P_REQUICISION = Integer.toString(recibosParam.getIdRequi());
        }
        List<ImprimeRecibo> lista = reciboConsultaLogic.imprimeRecibo(P_USUARIO, P_FORMAT_DATE, P_REQUICISION, P_ORDEN_COMPRA, P_FINI, P_FFIN, P_FOLIO, P_ID_UENS, P_MOSTRAR_RECIBOS, recibosParam.getPageNumber(), recibosParam.getRowsPerPage());
        //</R17486>
        return lista;

    }
    
    //<R17486>
    @RequestMapping(value = "/cuentaRecibo", method = RequestMethod.POST)
    public @ResponseBody
    Integer cuentaRecibo(@RequestBody RecibosParam recibosParam) {
        String P_USUARIO=recibosParam.getUsuario(), P_FORMAT_DATE = recibosParam.getIdioma(), P_ORDEN_COMPRA = recibosParam.getOrdenCompra(), P_FINI = recibosParam.getfIni(), P_FFIN =  recibosParam.getfFin(), P_FOLIO =  recibosParam.getFolio();
        String P_REQUICISION = "";
        
        String P_ID_UENS = recibosParam.getIdUens();
        String P_MOSTRAR_RECIBOS = recibosParam.isMostrarRecibos() ? "1" : "0";

        if (recibosParam.getIdRequi() != null) {
            P_REQUICISION = Integer.toString(recibosParam.getIdRequi());
        }
        Integer total = reciboConsultaLogic.cuentaRecibo(P_USUARIO, P_FORMAT_DATE, P_REQUICISION, P_ORDEN_COMPRA, P_FINI, P_FFIN, P_FOLIO, P_ID_UENS, P_MOSTRAR_RECIBOS);
        return total;

    }
    //</R17486>

    @RequestMapping(value = "/detalleRecibo", method = RequestMethod.POST)
    public @ResponseBody
    Iterable<ImprimeRecibo> detalleRecibo(@RequestBody RecibosParam recibosParam) {
        return reciboConsultaLogic.detalleRecibo(Integer.toString(recibosParam.getIdRequi()),recibosParam.getFolio(),recibosParam.getIdioma());
    }

    @RequestMapping(value = "/detalleRecibos", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Iterable<List<ImprimeRecibo>> getDetalleRecibos(@RequestBody List<RecibosParam> recibosParam) {
        List<List<ImprimeRecibo>> salida = new ArrayList<>();
        for (RecibosParam recibo : recibosParam) {
            String idRequisicion = String.valueOf(recibo.getIdRequi());
            salida.add(reciboConsultaLogic.detalleRecibo(idRequisicion,recibo.getFolio(),recibo.getIdioma()));
        }
        return salida;
    }

    @RequestMapping(value = "/preguntasRecibos/{idioma}", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<PreguntasRecibos> preguntasRecibos( @PathVariable("idioma") String idioma) {
        //return preguntasRecibosRepository.findAll();
        return preguntasRecibosRepository.findByIdioma(idioma);
    }
    
    //<RM20626> - Se elimina la validacion reciboConsultaRepository.prc_detalle_validaciones
    @RequestMapping(value = "/recibeRecibos", method = RequestMethod.POST)
    public @ResponseBody
    RespuestaSalida setRecibeRecibos(@RequestBody String json) {

        Gson gson = new Gson();
        RespuestaSalida respuestaSalida = new RespuestaSalida();
        
        RecibeRecibos recibeRecibos = gson.fromJson(json, RecibeRecibos.class);
        log.debug("[INICIO] setRecibeRecibos() - Requis:" + recibeRecibos.getDatos().size());
        StringBuilder entrada = null;
        String aux = "";
        Integer entro = 0, tieneMensaje = 0;
        List<RequiFolio> lRequiFolio = new ArrayList();
        String comentario= null;
        //Map requiFolios = new HashMap();
        String identificador, folio="-1", idioma;
        
        String[] parts = null;
        String[] msj;//<R21093>

        List<String> lineas = new ArrayList();

        switch(recibeRecibos.getIdioma()){
            case "es":
                idioma = "ESA";
                break;
            default:
                idioma = "US";
                break;
        }

        for (PayloadRecibos o : recibeRecibos.getDatos()) {
            entrada = new StringBuilder();
            List<PojoAux> listPojoAux = new ArrayList<>();
            log.debug("obtenerTimestamp()");
            identificador = reporteRecibosRepository.obtenerTimestamp();
            log.debug("obtenerTimestamp() - identificador: " + identificador);
            for (LineaPayload linea : o.getLineas()) {
                 
                PojoAux pojoAux = new PojoAux();
                pojoAux.setIdRequisicion(o.getId_requisicion());
                pojoAux.setLinea(linea.getNumero_linea());
                listPojoAux.add(pojoAux);
                
                if("%".equals(linea.getUdm_cambiada())){
                    Double cantidadCalculada = linea.getCantidad_por_recibir() * (linea.getAux_cantidad()/100);
                    linea.setAux_cantidad(cantidadCalculada);
                    linea.setUdm_cambiada(linea.getUdm());
                }
                
                if (linea.getCerrar() == null) {
                    linea.setCerrar(false);
                }
                
                if (!o.getComentario().equals("")) {
                    comentario = o.getComentario().replaceAll("\\\\", "");
                }
                
                if(o.getCurrent() > 3) //linea.getCantidad_por_recibir() --> linea.getAux_cantidad() identificador
                {
                    aux = linea.getId_orden_compra()+"\\"+linea.getId_partida_oc()+"\\"+linea.getAux_cantidad()+"\\"+linea.getUdm()+"\\"+linea.getId_proveedor()+"\\"+linea.getId_localizacion()+"\\"+linea.getId_uen()+"\\X\\X\\X\\X\\X\\X\\null\\null\\null\\null\\"+linea.getCantidad_recibida()+"\\"+linea.getCerrar()+"\\"+recibeRecibos.getId_usuario()+"\\"+o.getId_requisicion()+"\\"+linea.getNumero_linea()+"\\"+o.getCurrent()+"\\"+comentario+"\\"+linea.getC_cerrado()+"\\"+linea.getNotificar()+"\\"+identificador+"\\"+linea.getUdm_cambiada();
                } else {
                    aux = linea.getId_orden_compra()+"\\"+linea.getId_partida_oc()+"\\"+linea.getAux_cantidad()+"\\"+linea.getUdm()+"\\"+linea.getId_proveedor()+"\\"+linea.getId_localizacion()+"\\"+linea.getId_uen()+"\\"+o.getP_0()+"\\"+o.getP_1()+"\\"+o.getP_2()+"\\"+o.getP_3()+"\\"+o.getP_4()+"\\"+o.getP_5()+"\\null\\null\\null\\null\\"+linea.getCantidad_recibida()+"\\"+linea.getCerrar()+"\\"+recibeRecibos.getId_usuario()+"\\"+o.getId_requisicion()+"\\"+linea.getNumero_linea()+"\\"+o.getCurrent()+"\\"+comentario+"\\"+linea.getC_cerrado()+"\\"+linea.getNotificar()+"\\"+identificador+"\\"+linea.getUdm_cambiada();
                }
                lineas.add("" + linea.getId_partida_oc());
                entrada.append(aux);
                entrada.append("|");
                entro++;
            }
            
            if (entro > 0) {
				msj = new String[20]; //<R21093>
                log.debug("valida_lineas_recibo() - entrada: " + entrada.toString() + ", identificador: " + identificador);
                Integer respuesta = reciboConsultaRepository.valida_lineas_recibo(entrada.toString(), identificador);
                log.debug("valida_lineas_recibo() - respuesta: " + respuesta);
                
                String mensaje = reciboConsultaRepository.prc_detalle_validaciones(o.getId_requisicion() + "", idioma, identificador); 
                
                int contMsj = 0;
                
                if (mensaje == null || mensaje.isEmpty()) {
                    log.debug("recibeRecibo() - entrada: " + entrada.toString());
                    folio = reciboConsultaLogic.recibeRecibo(entrada.toString());
                    log.debug("recibeRecibo() - folio: " + folio);
                    log.debug("[INICIO] AGREGAR_LOG_REQUISICION()");
                    listPojoAux.forEach((pojoAux) -> {
                        logRepository.AGREGAR_LOG_REQUISICION(pojoAux.getIdRequisicion(), pojoAux.getLinea(), "SPX-RECIBOS", recibeRecibos.getId_usuario() , "RECIBIDA", "", "");
                    });
                    log.debug("[FIN] AGREGAR_LOG_REQUISICION()");
                } else {
                    tieneMensaje = 1;
                    parts = mensaje.split("\\|");
                    
                    for (int i = 0; i < parts.length; i++) {
                        String [] auxPart, reqLinea;
                        String test;
                        auxPart = parts[i].split("\\*");
                        reqLinea = auxPart[0].split("-");
                        
                        logRepository.AGREGAR_LOG_REQUISICION(Integer.parseInt(reqLinea[0]), Integer.parseInt(reqLinea[1]) , "SPX-RECIBOS", recibeRecibos.getId_usuario() , "ERROR AL RECIBIR", auxPart[1], "");
                        
                        test = parts[i].replace("%", reqLinea[0]).replace("@", reqLinea[1]).replace("*", " ");
                        log.debug(test);
                        //if (lineas.contains(reqLinea[1])) { //<R21093>
                        msj[contMsj] = parts[i].replace("%", reqLinea[0]).replace("@", reqLinea[1]).replace("*", " ");
                        contMsj++;
                        //} //<R21093>
                    }
                }
                RequiFolio requiFolio = new RequiFolio();
                requiFolio.setRequi(o.getId_requisicion().toString());
                requiFolio.setOc(o.getId_orden_compra().toString());
                requiFolio.setMensajes(msj);
                requiFolio.setTieneMensaje(tieneMensaje);
                requiFolio.setFolio(folio);
                lRequiFolio.add(requiFolio);
            }
            entro = 0;
        }

        respuestaSalida.setMensaje("Todo ok");
        respuestaSalida.setRequiFolio(lRequiFolio);
        log.debug("[FIN] setRecibeRecibos(): " + respuestaSalida.mensaje);
        return respuestaSalida;
    }
    //</RM20626>
    
    @RequestMapping(value = "/consultaCambioResponsable", method = RequestMethod.POST)
    public @ResponseBody
    Iterable<ResultadoCambioResponsable> consultaCambioResponsable(@RequestBody RecibosParam param){
        System.out.println(param.toString());
        return reciboConsultaLogic.consultaCambioResponsable(param.getUsuario(),param.getIdRequi());
    }
    
    @RequestMapping(value = "/actualizarRecibe", method = RequestMethod.POST)
    RespuestaSalida actualizarRecibe(@RequestBody RecibosParam param){
        boolean actualizado = reciboConsultaLogic.actualizarRecibe(param.getIdRequi(),param.getUsuario(),param.getTipoUsuario());
        RespuestaSalida respuesta = new RespuestaSalida();
        if(actualizado){
            respuesta.setMensaje("Requisicion Actualizada");
        }else{
            respuesta.setMensaje("Error");
        }
        return respuesta;
    }
    
    @RequestMapping(value = "/recibosAlmacen", method = RequestMethod.POST)
    public @ResponseBody
    Iterable<TipoRequisicion> getRecibosAlmacen(@RequestBody RecibosParam param){
        return reciboConsultaLogic.obtenerAlmacenRecibos(param.getUsuario());
    }
    
    @RequestMapping(value = "/generarReporteRecibos", method = RequestMethod.POST)
    public @ResponseBody
    RespuestaSalida generarReporte(@RequestBody ReporteParam param){
        boolean generado = reciboConsultaLogic.generarReporte(param);
        RespuestaSalida respuesta = new RespuestaSalida();
        if(generado){
            respuesta.setMensaje("Reporte Generado");
        }else{
            respuesta.setMensaje("Error");
        }
        return respuesta;
    }
    
    @RequestMapping(value = "/obtenerResumenReportes", method = RequestMethod.POST)
    public Iterable<ReporteEstatus> obtenerResumenReportes(@RequestBody ReporteParam param){
        System.out.println(param.toString());
        return reciboConsultaLogic.obtenerResumenReportes(param.getIdUsuario());
    }
    
    @RequestMapping(value = "/obtenerReporte", method = RequestMethod.POST)
    public @ResponseBody Iterable<ReporteRecibos> obtenerReporte(@RequestBody ReporteParam param){
        System.out.println(param.toString());
        return reciboConsultaLogic.obtenerReporte(param.getIdReporte());
    }
    
    @RequestMapping(value = "/eliminarReporte", method = RequestMethod.POST)
    public @ResponseBody
    RespuestaSalida eliminarReporte(@RequestBody ReporteParam param){
        System.out.println(param.toString());
        boolean eliminado = reciboConsultaLogic.eliminarReporte(param.getIdReporte());
        RespuestaSalida respuesta = new RespuestaSalida();
        if(eliminado){
            respuesta.setMensaje("Reporte Eliminado");
        }else{
            respuesta.setMensaje("Error");
        }
        return respuesta;
    }
    
    @RequestMapping(value = "/obtenerRequisitores", method = RequestMethod.POST)
    public @ResponseBody Iterable<Requisitor> obtenerRequisitores(@RequestBody ReporteParam param){
        return reciboConsultaLogic.obtenerRequisitores(param.getIdUsuario());
    }
     
    @RequestMapping(value="/obtenerListaUom", method = RequestMethod.POST)
    public @ResponseBody Iterable<Uom> obtenerListaUom(@RequestBody RecibosParam param){
        return reciboConsultaLogic.obtenerListaUom(param.getUnidad());
    }
    
    @RequestMapping(value="/enviarCorreoProveedor", method = RequestMethod.POST)
    public @ResponseBody RespuestaSalida enviarCorreoProveedor(@RequestBody RecibosParam param){
        System.out.println(param.toString());
        boolean eliminado = reciboConsultaLogic.enviarCorreoProveedor(param.getFoliosCorreo());
        RespuestaSalida respuesta = new RespuestaSalida();
        if(eliminado){
            respuesta.setMensaje("Enviado");
        }else{
            respuesta.setMensaje("Error");
        }
        return respuesta;
    }
}
