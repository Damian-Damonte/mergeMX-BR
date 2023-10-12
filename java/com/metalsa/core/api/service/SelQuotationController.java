/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.api.service;

import com.google.gson.Gson;
import com.metalsa.core.model.Backorder;
import com.metalsa.core.model.Quotation;
import com.metalsa.core.model.QuotationDetailed;
import com.metalsa.core.model.QuotationDetailedByRequisition;
import com.metalsa.core.model.QuotationDoc;
import com.metalsa.core.model.RazonSel;
import com.metalsa.core.model.RequisitionId;
import com.metalsa.core.model.RespDelRequisicion;
import com.metalsa.core.model.RespuestaSalida;
//import com.metalsa.core.model.SupplierSel;
import com.metalsa.core.model.SupplierSelSPX;
import com.metalsa.core.pojo.ClassOne;
import com.metalsa.core.pojo.EnvioCorreo;
import com.metalsa.core.pojo.QuotationRequest;
import com.metalsa.core.pojo.ResponseAprobador;
import com.metalsa.core.repository.BackorderRepository;
import com.metalsa.core.repository.LogRepository;
import com.metalsa.core.repository.QuotationDocRepository;
import com.metalsa.core.repository.QuotationRepository;
import com.metalsa.core.repository.RazonesSelRepository;
import com.metalsa.core.repository.ReqIdRepository;
import com.metalsa.core.repository.SupplierSelRepository;
import com.metalsa.core.repository.SupplierSelSPXRepository;
import io.swagger.annotations.Api;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author APOJO9952
 */
@RestController
@Api(value = "SelQuotation", tags = {"SelQuotation Service API"})
@RequestMapping("/api/v1/selquotation")
public class SelQuotationController {

    @Autowired
    private SupplierSelRepository supplierSelRepository;
    @Autowired
    private SupplierSelSPXRepository supplierSelSPXRepository;
    @Autowired
    private RazonesSelRepository razonesSelRepository;
    @Autowired
    private QuotationRepository quotationRepository;
    @Autowired
    private ReqIdRepository reqIdRepository;
    @Autowired
    private QuotationDocRepository quotationDocRepository;
    @Autowired
    private LogRepository logRepository;
    @Autowired
    private BackorderRepository backorderRepository;

//    @RequestMapping(value = "/suppliersCompras/{idRequisicion}/{idPartida}", method = RequestMethod.GET)
//    public @ResponseBody
//    Iterable<SupplierSelSPX> getSupCompras(@PathVariable("idRequisicion") String idRequisicion, @PathVariable("idPartida") String idPartida) {
//        return supplierSelRepository.findAllSupCompras(Integer.parseInt(idRequisicion), Integer.parseInt(idPartida));
//    }

    @RequestMapping(value = "/suppliersSpx/{idRequisicion}/{idPartida}", method = RequestMethod.GET)//3727351/2
    public @ResponseBody
    Iterable<SupplierSelSPX> getSupSpx(@PathVariable("idRequisicion") String idRequisicion, @PathVariable("idPartida") String idPartida) {
        return supplierSelSPXRepository.findAllSupSpx(Integer.parseInt(idRequisicion), Integer.parseInt(idPartida));
    }

    @RequestMapping(value = "/getQuotations/{elrequisitor}", method = RequestMethod.GET)//90302
    public @ResponseBody
    Iterable<QuotationDetailedByRequisition> getQuotations(@PathVariable("elrequisitor") String elrequisitor) {
        Iterable<RequisitionId> idList = reqIdRepository.getRequisitionByIdRequisitor(elrequisitor);
        Iterable<Quotation> iter = quotationRepository.getQuotationsById(elrequisitor);
        List<QuotationDetailedByRequisition> listqd = new ArrayList<>();
        for (RequisitionId requisicion : idList) {
            List<QuotationDetailed> list = new ArrayList<>();
            for (Quotation quo : iter) {
                Quotation q = (Quotation) quo;
                if (requisicion.getRequisitionid().equals(quo.getID_REQUISICION()) && q.getACTIVE() == 1) {
//                    if (q.getACTIVE() == 0) {
//                        Iterable<SupplierSel> listCompras = supplierSelRepository.findAllSupCompras(q.getID_REQUISICION(), q.getID_PARTIDA());
//                        list.add(new QuotationDetailed(quo, listCompras, null));
//                    } else {
                    
                    Iterable<SupplierSelSPX> listSPX = supplierSelSPXRepository.findAllSupSpx(q.getID_REQUISICION(), q.getID_PARTIDA());
                    for (SupplierSelSPX s : listSPX) {
                        if (s.hasBackorders()) {
                            s.setBackorders(backorderRepository.getBackorders(q.getID_REQUISICION(), q.getID_PARTIDA()));
                            for(Backorder b : s.getBackorders()){
                                b.setUdm(q.getID_UNIDAD_DE_MEDIDA());
                            }
                        }
                    }
                    list.add(new QuotationDetailed(quo, listSPX));
                    
                }
            }
            if (!list.isEmpty()) {
                listqd.add(new QuotationDetailedByRequisition(
                        requisicion.getRequisitionid(),requisicion.getTIENECHAT(), 
                        requisicion.getId_uen(), requisicion.getOrganization_name(),requisicion.getCANTIDAD_REQUISICIONES(),
                        list));
            }
        }
        return listqd;
    }

    @RequestMapping(value = "/razon/{language}", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<RazonSel> getRazon(@PathVariable("language") String language) {
        String idioma;
        switch(language){
            case "es": idioma = "ESA"; break;
            default: idioma = "US"; break;
        }
        return razonesSelRepository.findRazonesByLanguage(idioma);
    }

    @RequestMapping(value = "/respDelRequisicion/{id_uen}/{idioma}/{codigo_cc}/{id_requisicion}",
            method = RequestMethod.GET)
    public @ResponseBody
    Iterable<ResponseAprobador> getRespDelRequisicion(
            @PathVariable("id_uen") String id_uen,
            @PathVariable("idioma") String idioma,
            @PathVariable("codigo_cc") String codigo_cc,
            @PathVariable("id_requisicion") String id_requisicion
    ) {
        boolean isMultiCc = supplierSelRepository.isMultiCc(id_requisicion);
        List<String> allDist = new ArrayList();
        if (isMultiCc) {
            allDist.addAll(supplierSelRepository.getAllDist(id_requisicion));
        } else {
            allDist.add(codigo_cc);
        }
        RespDelRequisicion pojo;

        List<ResponseAprobador> listAprobadores = new ArrayList<>();
        String fix_language = idioma.toUpperCase().equals("ES") ? "ESA" : "US";
        for (String codigo_cc_ : allDist) {
            List<RespDelRequisicion> lista = new ArrayList<>();
            String resultado = supplierSelRepository.prcRespDelRequisicion(id_uen, fix_language, codigo_cc_, id_requisicion);
            StringTokenizer cadena = new StringTokenizer(resultado, "$");
//        int idx = 0;
            while (cadena.hasMoreTokens()) {
                StringTokenizer elemento = new StringTokenizer(cadena.nextToken(), "|");
                pojo = new RespDelRequisicion();
                while (elemento.hasMoreTokens()) {
                    pojo.setCodigoCC(elemento.nextToken());
                    pojo.setNombreCC(elemento.nextToken());
                    pojo.setAprobador(elemento.nextToken());
                    pojo.setResponsabilidad(elemento.nextToken());
                    pojo.setNivelAprobacionRi(elemento.nextToken());
                    pojo.setNivelAprobacionRe(elemento.nextToken());
                }
                lista.add(pojo);
            }
            listAprobadores.add(new ResponseAprobador(codigo_cc_, lista));
        }
        return listAprobadores;
    }

    @RequestMapping(value = "/regresaAprobadoresProyecto/{id_requisicion}",
            method = RequestMethod.GET)
    public @ResponseBody Iterable<SupplierSelSPX> getAprobProyRequisicion(    
    @PathVariable("id_requisicion") Integer id_requisicion
    ){
        return supplierSelRepository.regresaAprobadoresProyecto(id_requisicion);  
    }
    
    @RequestMapping(value = "/procesaAprobacionSeleccion", method = RequestMethod.POST) //consumes = "application/json"
    public @ResponseBody
    RespuestaSalida procesaAprobacionSeleccion(@RequestBody String json) throws IOException {
        RespuestaSalida test = new RespuestaSalida();
        StringBuilder idRequis = new StringBuilder();
//        String mensajeRespuesta = "";
        String idUsuario;
        StringBuilder entradaStore = new StringBuilder();
        //ObjectMapper mapper = new ObjectMapper();
        test.servicio = "SelQuotationController";
        
        
        Gson gson = new Gson();
        ClassOne recibePeticion = gson.fromJson(json, ClassOne.class);
        test.mensaje = " Soy usuario " + recibePeticion.getId_usuario();
        idUsuario = recibePeticion.getId_usuario();
        List<EnvioCorreo> listEnvioCorreo  = new ArrayList();
        
        for (QuotationRequest quotationRequest : recibePeticion.getRequi()) {
            EnvioCorreo envioCorreo = new EnvioCorreo();
            idRequis.append(quotationRequest.getQuotation().getId_requisicion()).append(",");
            
            logRepository.AGREGAR_LOG_REQUISICION(quotationRequest.getQuotation().getId_requisicion(), quotationRequest.getQuotation().getId_partida(), "SPX-SELECCION", idUsuario, "SELECCIONADA", "", "");
            
            entradaStore.append(quotationRequest.getQuotation().getId_requisicion())
                    .append(",").append(quotationRequest.getQuotation().getId_partida())
                    .append(",").append(quotationRequest.getList_compras());
            envioCorreo.setIdPartida(quotationRequest.getQuotation().getId_partida());
            envioCorreo.setIdRequisicion(""+ quotationRequest.getQuotation().getId_requisicion());
            
            listEnvioCorreo.add(envioCorreo);
            for (com.metalsa.core.pojo.SupplierSelSPX supplierSelSPX : quotationRequest.getList_spx()) {
                if(null != supplierSelSPX.getRazon_seleccion_cotizacion() && supplierSelSPX.getRazon_seleccion_cotizacion() )
                {
                    entradaStore.append(",").append(supplierSelSPX.getLinea_prov()); //.append("|")
                    //entradaStore.append(",").append(supplierSelSPX.getLinea_prov()).append("|"); //.append("|")
                }
            }
            if(quotationRequest.getMotivo_seleccion() != null )
            {
                entradaStore.append(",").append(quotationRequest.getMotivo_seleccion().replace("|", "")).append("|");
            }else
            {
               entradaStore.append(",").append(quotationRequest.getMotivo_seleccion()).append("|"); 
            }
        }
        test.mensaje = entradaStore.toString();
        String salidaMensaje = supplierSelRepository.procesaAprobacionSeleccion(entradaStore.toString(), idUsuario, idRequis.toString()); //mensajeRespuesta
        for (EnvioCorreo envioCorreo : listEnvioCorreo) {
            supplierSelRepository.envioEmails(envioCorreo.getIdRequisicion(),envioCorreo.getIdPartida());
        }
        
        test.mensajeRespuesta = salidaMensaje;
        test.tipo = json + " Guardar";
        
        
        return test;
    }

    /*@RequestMapping(value = "/returnReqBuyerCancel/{requisiciones}/{comentario}/{id_usuario}/{modulo}/{status}", method = RequestMethod.PUT)
    public @ResponseBody
    void returnReqBuyer(@PathVariable("requisiciones") String requisiciones, @PathVariable("comentario") String comentario, @PathVariable("id_usuario") String id_usuario, @PathVariable("modulo") String modulo, @PathVariable("status") String status) {
        supplierSelRepository.returnReqBuyer(requisiciones, comentario, id_usuario, modulo, status);
    }*/
    
    @RequestMapping(value = "/returnReqBuyerCancel/", method = RequestMethod.POST)
    public @ResponseBody
    RespuestaSalida returnReqBuyer(@RequestBody String json) {
        String modulo = "selcotizacion", idUsuario, status;
        //String idRequis = "";
        String comentario;
        StringBuilder entradaStore = new StringBuilder();
        RespuestaSalida test = new RespuestaSalida();
        
        //test.tipo = "cancelar o regresar";
        test.servicio = "returnReqBuyer";
        test.mensajeRespuesta = "haciendo pruebas";
        test.mensaje = json;
        Gson gson = new Gson();
        ClassOne recibePeticion = gson.fromJson(json, ClassOne.class);
        idUsuario = recibePeticion.getId_usuario();
        status = recibePeticion.getStatus();
        test.tipo =  status;
        comentario = recibePeticion.getComentario();
        
        if(status.equals("Regresar") || status.equals("Return"))
        {
            status = "regresar";
            recibePeticion.getRequi().forEach((quotationRequest) -> {
                logRepository.AGREGAR_LOG_REQUISICION(quotationRequest.getQuotation().getId_requisicion(), quotationRequest.getQuotation().getId_partida(), "SPX-SELECCION", idUsuario, "REGRESADA", "", "");
                entradaStore.append(quotationRequest.getQuotation().getId_requisicion()).append("-").append(quotationRequest.getQuotation().getId_partida()).append(",");
            });
            
        }else
        {
            status = "cancelar";
            recibePeticion.getRequi().forEach((quotationRequest) -> {
                logRepository.AGREGAR_LOG_REQUISICION(quotationRequest.getQuotation().getId_requisicion(), quotationRequest.getQuotation().getId_partida(), "SPX-SELECCION", idUsuario, "CANCELADA", "", "");
                entradaStore.append(quotationRequest.getQuotation().getId_requisicion()).append("-").append(quotationRequest.getQuotation().getId_partida()).append(",");
            });
        }
        
        
        
        test.tipo += entradaStore.toString();
        
        
        supplierSelRepository.returnReqBuyer(entradaStore.toString(), comentario, idUsuario, modulo, status);
        
        return test;
    }

    @RequestMapping(value = "/getDocsCotizacion/{idCotizacion}", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<QuotationDoc> getDocsCotizacion(@PathVariable("idCotizacion") String idCotizacion) {
        
        //return new QuotationDoc();
        return quotationDocRepository.findByIdCotizacion(Integer.parseInt(idCotizacion));
    }
    
}
