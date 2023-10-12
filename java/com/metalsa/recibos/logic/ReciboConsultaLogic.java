/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.logic;

import com.metalsa.recibos.builder.RecibosBuilder;
import com.metalsa.recibos.model.AlmacenRecibos;
import com.metalsa.recibos.model.DetalleRecibo;
import com.metalsa.recibos.model.ImprimeRecibo;
import com.metalsa.recibos.model.ReciboConsulta;
import com.metalsa.recibos.model.ReporteEstatus;
import com.metalsa.recibos.model.ReporteRecibos;
import com.metalsa.recibos.model.Requisitor;
import com.metalsa.recibos.model.ResultadoCambioResponsable;
import com.metalsa.recibos.model.Uom;
import com.metalsa.recibos.pojo.FolioCorreo;
import com.metalsa.recibos.pojo.ReporteParam;
import com.metalsa.recibos.pojo.Requisicion;
import com.metalsa.recibos.pojo.TipoRequisicion;
import com.metalsa.recibos.repository.AlmacenReciboRepository;
import com.metalsa.recibos.repository.CambioResponsableRepository;
import com.metalsa.recibos.repository.DetalleReciboRepository;
import com.metalsa.recibos.repository.ManipulacionReciboRepository;
import com.metalsa.recibos.repository.ReciboConsultaRepository;
import com.metalsa.recibos.repository.ReporteRecibosRepository;
import com.metalsa.recibos.repository.ReporteResumenRepository;
import com.metalsa.recibos.repository.RequisitorRepository;
import com.metalsa.recibos.repository.UomReciboRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.log4j.Log4j;//<R17231>
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//REVISAR RESPALDO PARA ISSUE  17486   OMAR ESTRELLA 
/**
 *
 * @author yair.nunez
 */
@Log4j//<R17231>
@Service
public class ReciboConsultaLogic {

    @Autowired
    private ReciboConsultaRepository reciboConsultaRepository;

    @Autowired
    private ManipulacionReciboRepository manipulacionReciboRepository;

    @Autowired
    private AlmacenReciboRepository almacenReciboRepository;

    @Autowired
    private ReporteRecibosRepository reporteRecibosRepository;

    @Autowired
    private ReporteResumenRepository reporteResumenRepository;

    @Autowired
    private CambioResponsableRepository cambioResponsableRepository;

    @Autowired
    private RequisitorRepository requisitorRepository;

    @Autowired
    private UomReciboRepository uomReciboRepository;

    @Autowired
    private DetalleReciboRepository detalleReciboRepository;

    public String recibeRecibo(String json) {
        return reciboConsultaRepository.recibeEscritorio(json);
    }

    public List<ImprimeRecibo> detalleRecibo(String P_REQUICISION, String P_FOLIO, String P_FORMAT_DATE) {
        return manipulacionReciboRepository.detalleRecibo(P_REQUICISION, P_FOLIO, P_FORMAT_DATE);
    }

    //<R17486>
    public List<ImprimeRecibo> imprimeRecibo(String P_USUARIO, String P_FORMAT_DATE, 
            String P_REQUICISION, String P_ORDEN_COMPRA, String P_FINI, String P_FFIN, 
            String P_FOLIO,String P_ID_UENS, String P_MOSTRAR_RECIBOS, Integer P_PAGINA, Integer P_PAGSIZE ) {
        System.out.println(P_USUARIO);
        System.out.println(P_FORMAT_DATE);
        System.out.println(P_REQUICISION);
        System.out.println(P_ORDEN_COMPRA);
        System.out.println(P_FINI);
        System.out.println(P_FFIN);
        System.out.println(P_FOLIO);
        System.out.println(P_ID_UENS);
        System.out.println(P_MOSTRAR_RECIBOS);
        return manipulacionReciboRepository
                .imprimeRecibo(P_USUARIO, P_FORMAT_DATE, P_REQUICISION, P_ORDEN_COMPRA, 
                        P_FINI, P_FFIN, P_FOLIO,P_ID_UENS,P_MOSTRAR_RECIBOS,
                        P_PAGINA, P_PAGSIZE);
    }
    
    
    public Integer cuentaRecibo(String P_USUARIO, String P_FORMAT_DATE, 
            String P_REQUICISION, String P_ORDEN_COMPRA, String P_FINI, String P_FFIN, 
            String P_FOLIO,String P_ID_UENS, String P_MOSTRAR_RECIBOS) {

        return manipulacionReciboRepository
                .cuentaRecibo(P_USUARIO, P_FORMAT_DATE, P_REQUICISION, P_ORDEN_COMPRA, 
                        P_FINI, P_FFIN, P_FOLIO,P_ID_UENS,P_MOSTRAR_RECIBOS);
    }
    // </R17486>
	
	public List<ResultadoCambioResponsable> consultaCambioResponsable(String idUsuario, Integer idRequisicion) {
        return cambioResponsableRepository.consultaCambioResponsable(idRequisicion, idUsuario);
    }
    
	
    public boolean actualizarRecibe(Integer idRequisicion, String idUsuario, String tipoUsuario){
        return cambioResponsableRepository.actualizarRecibe(idRequisicion, idUsuario, tipoUsuario);
    }

    //<R17231>
    public List<TipoRequisicion> obtenerRecibos(String uen, String requisitor, String idRequisicion, String idOrdenCompra,String usuario) { //<R19835>
        
        uen = getNullValue(uen);
        idRequisicion = getNullValue(idRequisicion);
        idOrdenCompra = getNullValue(idOrdenCompra);
      
        List<TipoRequisicion> listaFinal = new ArrayList<>();
        List<Requisicion> listaRequi = new ArrayList<>();
        List<ReciboConsulta> recibos = reciboConsultaRepository.PRC_SPX_REQS_POR_RECIBIR
        (idRequisicion, idOrdenCompra, uen, "", "", "", "", "", requisitor,usuario); //<R17231>

        try {
            
            for (ReciboConsulta req : recibos) {
                RecibosBuilder.pushRequisicion(listaRequi, req);
            }
            
            for (ReciboConsulta req : recibos) {
                RecibosBuilder.pushLinea(listaRequi, req);
            }
            
            // <M26022018>
            java.util.Map<String, List<Uom>> mapUdm = new java.util.HashMap<>();

            listaRequi.forEach((req) -> {
                req.getLineas().forEach((linea) -> {
                    linea.getListaUom().add(new Uom("%", "%"));
                    if (!mapUdm.containsKey(linea.getUdm())) {
                        mapUdm.put(linea.getUdm(), obtenerListaUom(linea.getUdm()));
                    }
                    List<Uom> listaObtenida = mapUdm.get(linea.getUdm()); //obtenerListaUom(linea.getUdm());
                    for (Uom u : listaObtenida) {
                        if (u.getUomCode().equals(linea.getUdm())) {
                            linea.getListaUom().add(u);
                        }
                    }
                });
            });
            // <M26022018 />
            pushRequisiciones(listaFinal, listaRequi);
        } catch (Exception e) {
        }
        return listaFinal;
    }
    //</R17231>
    
    public List<ReporteEstatus> obtenerResumenReportes(String idUsuario){
        return reporteResumenRepository.obtenerResumenReportes(idUsuario);
    }
    
    public List<ReporteRecibos> obtenerReporte(Integer idReporte){
        return reporteRecibosRepository.obtenerReporte(idReporte);
    }
    
    public boolean eliminarReporte(Integer idReporte){
        return reporteRecibosRepository.eliminarReporte(idReporte);
    }
    
    public List<Requisitor> obtenerRequisitores(String idUsuario){
        return requisitorRepository.obtenerRequisitores(idUsuario);
    }
    
    public List<Uom> obtenerListaUom(String unidad){
        return uomReciboRepository.obtenerListaUom(unidad);
    }
    
    public boolean enviarCorreoProveedor(List<FolioCorreo> foliosCorreo){
        String timestamp = reporteRecibosRepository.obtenerTimestamp();//obtener timestamp
        for(FolioCorreo f : foliosCorreo){
            String valor = " id_requisicion = "+f.getIdRequisicion()+" and id_partida = "+f.getIdPartida()+" and rc.folio = "+f.getIdFolio();
            reporteRecibosRepository.insertaClave("QUERY", valor, timestamp,"1","NOTIFICAR_PROVEEDOR_CLOSE",0);
        }
        String ejecutado = reporteRecibosRepository.enviarCorreoProveedor(timestamp);

        return "Ejecutado".equals(ejecutado);
    }
    
    public boolean generarReporte(ReporteParam param){
        StringBuilder whereInterno = new StringBuilder("");
        StringBuilder whereExterno = new StringBuilder("");

        System.out.println(param.toString());
        if(param.getNumeroRecibo()!=null){
            whereInterno.append(" AND FOLIO=");
            whereInterno.append(param.getNumeroRecibo());
        }else if(param.getRequisicion()!=null){
            whereInterno.append(" AND A.ID_REQUISICION=");
            whereInterno.append(param.getRequisicion());
        }else if(param.getOrdenDeCompra()!=null){
            whereInterno.append(" AND B.ID_ORDEN_DE_COMPRA=");
            whereInterno.append(param.getOrdenDeCompra());
        }else{
            String timestamp = reporteRecibosRepository.obtenerTimestamp();//obtener timestamp
            System.out.println("TIMESTAMP="+timestamp);
            //do insert
            
            if(param.getIdUen()!=null){
                reporteRecibosRepository.insertaClave("A.ID_UEN", param.getIdUen().toString(), timestamp, "number",null,null);
            }
            for(String c : param.getCompradores()){
               reporteRecibosRepository.insertaClave("B.COMPRADOR", c, timestamp, "varchar2",null,null); 
            }
            for(Integer p : param.getProveedores()){
                reporteRecibosRepository.insertaClave("C.ID_PROVEEDOR", p.toString(), timestamp, "number",null,null);
            }
            for(String r: param.getRequisitores()){
                reporteRecibosRepository.insertaClave("U2.ID_USUARIO", r, timestamp, "varchar2",null,null);
            }
            for(String receptor : param.getReceptores()){
                reporteRecibosRepository.insertaClave("RC.ID_USUARIO", receptor, timestamp, "varchar2",null,null);
            }
            //generar where
            String whereQuery = reporteRecibosRepository.obtenerWhereQuery(timestamp);
            System.out.println(whereQuery);
            if(whereQuery!=null){
                whereInterno.append(whereQuery);
            }
            
            
            if(!param.getRecibidoen().isEmpty()){
                if(param.getRecibidoen().size()==1){
                    whereExterno.append(" AND RECIBIDO_EN=");
                    whereExterno.append(param.getRecibidoen().get(0));
                }else{
                    whereExterno.append(" AND RECIBIDO_EN IN(");
                    whereExterno.append(param.getRecibidoen().get(0));
                    whereExterno.append(",");
                    whereExterno.append(param.getRecibidoen().get(1));
                    whereExterno.append(")");
                }
            }
            
            
            String rangoFechaRequisicion = " AND TO_DATE(FECHA_REQUISICION, 'DD/MM/RRRR')"
                + " BETWEEN TO_DATE('"+param.getFechaRequisicionInicial()+"', 'mm/dd/yyyy')"
                + " AND TO_DATE('"+param.getFechaRequisicionFinal()+"', 'mm/dd/yyyy')";
        
            String rangoFechaRecibo = " AND TO_DATE(FECHA_DE_RECEPCION,'DD/MM/RRRR')"
                    + " BETWEEN TO_DATE('"+param.getFechaReciboInicial()+"', 'mm/dd/yyyy')"
                    + " AND TO_DATE('"+param.getFechaReciboFinal()+"', 'mm/dd/yyyy')"; 

            if(param.getFechaRequisicionInicial()!=null && param.getFechaRequisicionFinal()!=null){
                whereExterno.append(rangoFechaRequisicion);
            }
            
            if(param.getFechaReciboInicial()!=null && param.getFechaReciboFinal()!=null){
                whereExterno.append(rangoFechaRecibo);
            }
            
        }
 
        System.out.println("WHEREINTERNO="+whereInterno.toString());
        System.out.println("WHEREEXTERNO="+whereExterno.toString());
        //return new ArrayList<>();
        return reporteRecibosRepository.generarReporteRecibos(whereInterno.toString(),whereExterno.toString(),param.getIdUsuario(),param.getNombreReporte());
        //return true;
    }
    
    public List<TipoRequisicion> obtenerAlmacenRecibos(String idUsuario){
        List<TipoRequisicion> listaFinal = new ArrayList<>();
        List<Requisicion> listaRequi = new ArrayList<>();
        List<AlmacenRecibos> recibos = almacenReciboRepository.getAlmacenRecibos(idUsuario);
        System.out.println("Consulta total:"+recibos.size());
        try{
            recibos.forEach((req) -> {
                RecibosBuilder.pushRequisicion(listaRequi,req);
            });
            recibos.forEach((req) -> {
                RecibosBuilder.pushLinea(listaRequi,req);
            });
            listaRequi.forEach((req) -> {
                req.getLineas().forEach((linea)->{
                    List<DetalleRecibo> recibosParciales = detalleReciboRepository.getDetalleRecibo(linea.getIdRequisicion(), linea.getNumeroLinea());
                    recibosParciales.forEach((r) -> {
                        linea.getRecibos().add(RecibosBuilder.reciboBuilder(r, linea.getIdRequisicion(), linea.getNumeroLinea()));
                    });
                });
            });
            pushRequisiciones(listaFinal,listaRequi);
        }catch(Exception e){
            System.out.println(e);
        }
        
        return listaFinal;
    }
    
    
    
    
    
    public void pushRequisiciones(List<TipoRequisicion> listaFinal,List<Requisicion> requisiciones){
        TipoRequisicion generales = new TipoRequisicion();
        generales.setTipoRequisicion("General");
        generales.setClaveRequisicion("G");
        generales.setRequisiciones(requisiciones);
        listaFinal.add(generales);
        /*
        List<Requisicion> spot = new ArrayList<>();
        List<Requisicion> catalogoExterno = new ArrayList<>();
        List<Requisicion> catalogoInterno = new ArrayList<>();
        List<Requisicion> valesFondo = new ArrayList<>();
        List<Requisicion> almacen = new ArrayList<>();
        List<Requisicion> consignacion = new ArrayList<>();
        List<Requisicion> lentoMovimiento = new ArrayList<>();
        List<Requisicion> multicc = new ArrayList<>();
        
        
        for(Requisicion req: requisiciones){
           switch(req.getFuente()){
            case "A":
                almacen.add(req);
                break;
            case "K":
                consignacion.add(req);
                break;
            case "L":
                lentoMovimiento.add(req);
                break;
            case "C":
                spot.add(req);
                break;
            case "MCCE":
                multicc.add(req);
                break;
            case "MCCV":
                multicc.add(req);
                break;
            case "E":
                catalogoExterno.add(req);
                break;
            case "P":
                catalogoInterno.add(req);
                break;
            case "V":
                valesFondo.add(req);
                break;
            default:
                System.out.println("TIPO DE FUENTE NO VÁLIDO="+req.getFuente());
                break;
            } 
        }

        evaluaListas(listaFinal,multicc,"multicc");
        evaluaListas(listaFinal,consignacion,"consignacion");
        evaluaListas(listaFinal,lentoMovimiento,"lentoMovimiento");
        evaluaListas(listaFinal,almacen,"almacen");
        evaluaListas(listaFinal,valesFondo,"valesFondo");
        evaluaListas(listaFinal, catalogoInterno, "catalogoInterno");
        evaluaListas(listaFinal, catalogoExterno, "catalogoExterno");
        evaluaListas(listaFinal, spot, "spot");*/
    }
    
    private void evaluaListas(
            List<TipoRequisicion> listaFinal, // Lista que ha sido procesada
            List<Requisicion> tipoLista, //Son las requisiciones agrupadas de cierto tipo 
            String tipo){ //Tipo de requisicion.
        if(!tipoLista.isEmpty()){            
            switch(tipo){
                case "multicc":
                    setLstTipoRequisicion(listaFinal, "MultiCC", "MCC",tipoLista);
                    break;
                case "consignacion":
                     setLstTipoRequisicion(listaFinal, "Consignacion", "K",tipoLista);
                    break;
                case "lentoMovimiento":
                    setLstTipoRequisicion(listaFinal, "Lento Movimiento", "L",tipoLista);
                    break;
                case "almacen":
                    setLstTipoRequisicion(listaFinal, "Almacen", "A",tipoLista);
                    break;
                case "valesFondo":
                     setLstTipoRequisicion(listaFinal, "Vales fondo", "V",tipoLista);
                    break;
                case "catalogoInterno":
                    setLstTipoRequisicion(listaFinal, "Catalago interno", "P",tipoLista);
                    break;
                case "catalogoExterno":
                    setLstTipoRequisicion(listaFinal, "Catalago externo", "E",tipoLista);
                    break;
                case "spot":
                default:
                    setLstTipoRequisicion(listaFinal, "Spot", "C",tipoLista);
                    break;
            }
        }
    }
    
    private void setLstTipoRequisicion(List<TipoRequisicion> listaFinal, 
            String strTipoReq, String strClaveReq, List<Requisicion> tipoLista){
        TipoRequisicion lstTipoRequisicion = new TipoRequisicion();
        lstTipoRequisicion.setTipoRequisicion(strTipoReq);
        lstTipoRequisicion.setClaveRequisicion(strClaveReq);
        lstTipoRequisicion.setRequisiciones(tipoLista);
        listaFinal.add(lstTipoRequisicion);
    }
    

    //<R17231>
    private String getNullValue(String val){        
        if("null".equals(val) || "undefined".equals(val)){
            return null;
        }        
        return val;
    }
    //</R17231>
}
