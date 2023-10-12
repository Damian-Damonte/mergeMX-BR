/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.builder;

import com.metalsa.recibos.model.AlmacenRecibos;
import com.metalsa.recibos.model.DetalleRecibo;
import com.metalsa.recibos.model.ReciboConsulta;
import com.metalsa.recibos.pojo.Linea;
import com.metalsa.recibos.pojo.ReciboParcial;
import com.metalsa.recibos.pojo.Requisicion;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author yair.nunez
 */
public class RecibosBuilder {
        
    //METODOS PARA LA CONSULTA DE REQUISICIONES RECIBOS 
    public static Linea lineaBuilder(ReciboConsulta reqRecibo){
        Linea linea = new Linea();
        linea.setNumeroLinea(reqRecibo.getID_PARTIDA());
        linea.setIdRequisicion(reqRecibo.getID_REQUISICION());
        linea.setUrgente(reqRecibo.getURGENTE());
        linea.setIdOrdenCompra(reqRecibo.getID_ORDEN_DE_COMPRA());
        linea.setDescripcion(reqRecibo.getDESCRIPCION_PRODUCTO());
        linea.setFechaNecesidad(reqRecibo.getFECHA_REQUERIDA());
        linea.setFechaVencimiento(reqRecibo.getPROMISED_DATE());
        linea.setCantidad(reqRecibo.getCANTIDAD_REQUERIDA());
        linea.setCantidadPorRecibir(reqRecibo.getCANTIDAD_PENDIENTE());
        linea.setCantidadRecibida(reqRecibo.getCANTIDAD_ENTREGADA());
        linea.setUdm(reqRecibo.getID_UNIDAD_DE_MEDIDA());
        linea.setEstatus(reqRecibo.getREQ_ESTADO());
        linea.setNombreProveedor(reqRecibo.getNOMBRE_PROVEEDOR());
        linea.setIdPartidaOc(reqRecibo.getID_PARTIDA_OC());
        linea.setIdLocalizacion(reqRecibo.getID_LOCALIZACION());
        linea.setIdUen(reqRecibo.getID_UEN());
        linea.setIdProveedor(reqRecibo.getID_PROVEEDOR());
        linea.setIdCc(reqRecibo.getID_CC());
        linea.setComprador(reqRecibo.getCOMPRADOR());
        linea.setFechaRequisicion(reqRecibo.getFECHA_REQUISICION());
        linea.setRequisitor(reqRecibo.getREQUISITOR());
        linea.setNotificaProveedor(reqRecibo.getNOTIFICA_PROVEEDOR());
        linea.setProvTieneContacto(reqRecibo.getPROV_TIENE_CONTACTO());
        return linea;
    }
    
    public static Requisicion requisicionBuilder(ReciboConsulta reqRecibo){
        Requisicion reqNueva = new Requisicion();
        reqNueva.setIdRequisicion(reqRecibo.getID_REQUISICION());
        reqNueva.setComprador(reqRecibo.getCOMPRADOR());
        reqNueva.setFechaCreacion(reqRecibo.getFECHA_REQUISICION());
        reqNueva.setRequisitor(reqRecibo.getREQUISITOR());
        reqNueva.setFuente(reqRecibo.getFUENTE());
        reqNueva.setIdUen(reqRecibo.getID_UEN());
        reqNueva.setIdCc(reqRecibo.getID_CC());
        reqNueva.setNombreCc(reqRecibo.getNOMBRE_CC());
        reqNueva.setCodigoCc(reqRecibo.getCODIGO_CC());
        reqNueva.setTipoRequisicion(obtenerTipoRequisicion(reqRecibo.getFUENTE()));
        reqNueva.setNotificaProveedor(reqRecibo.getNOTIFICA_PROVEEDOR());
        reqNueva.setPedidoEspecial(reqRecibo.getPedidoEspecial());
        
        return reqNueva;
    }
    
    public static void pushLinea(List<Requisicion> listaRequi,ReciboConsulta requisicion){
        listaRequi.stream().filter(req -> Objects.equals(requisicion.getID_REQUISICION(), req.getIdRequisicion())).forEachOrdered((req) -> {
            req.getLineas().add(lineaBuilder(requisicion));
            if("VENCIDA".equals(requisicion.getREQ_ESTADO())){
                req.setLineasVencidas(req.getLineasVencidas()+1);
            }
        });
    }
    
    public static void pushRequisicion(List<Requisicion> listaRequi,ReciboConsulta requisicion){
        if(!requisicionExistente(listaRequi,requisicion.getID_REQUISICION())){
            listaRequi.add(requisicionBuilder(requisicion));
        }      
    }
    
    //METODOS PARA LA CONSULTA DE ALMACEN RECIBOS 
    
    public static ReciboParcial reciboBuilder(DetalleRecibo reqRecibo,Integer idReq,Integer idPartida){
        ReciboParcial recibo = new ReciboParcial();
        recibo.setIdRequisicion(idReq);
        recibo.setNumeroLinea(idPartida);
        recibo.setFechaRecibo(reqRecibo.getFechaRecibo());
        recibo.setNumeroRecibo(reqRecibo.getNumeroRecibo());
        recibo.setCantidadRecibida(reqRecibo.getCantidadRecibida());
        recibo.setRecibidoEn(reqRecibo.getRecibidoEn());
        return recibo;
    }
    
    public static Linea lineaBuilder(AlmacenRecibos reqRecibo){
        Linea linea = new Linea();
        linea.setIdRequisicion(reqRecibo.getIdRequisicion());
        linea.setNumeroLinea(reqRecibo.getIdPartida());
        linea.setUrgente(reqRecibo.getUrgente());
        linea.setIdOrdenCompra(reqRecibo.getIdOrdenCompra());
        linea.setDescripcion(reqRecibo.getDescripcionProducto());
        linea.setCantidad(reqRecibo.getCantidadRequerida());
        linea.setUdm(reqRecibo.getIdUnidadMedida());
        linea.setNombreProveedor(reqRecibo.getNombreProveedor());
        linea.setIdProveedor(reqRecibo.getIdProveedor());
        linea.setReceptor(reqRecibo.getReceptor());
        linea.setIdPartidaOc(reqRecibo.getIdPartidaOc());
        linea.setCantidadRecibida(reqRecibo.getCantidadEntregada());
        linea.setCantidadPorRecibir(reqRecibo.getCantidadPendiente());
        return linea;
    }
    
    public static Requisicion requisicionBuilder(AlmacenRecibos reqRecibo){
        Requisicion reqNueva = new Requisicion();
        reqNueva.setIdRequisicion(reqRecibo.getIdRequisicion());
        reqNueva.setComprador(reqRecibo.getComprador());
        reqNueva.setFechaCreacion(reqRecibo.getFechaRequerida());
        reqNueva.setRequisitor(reqRecibo.getRequisitor());
        reqNueva.setFuente(reqRecibo.getFuente());
        reqNueva.setIdUen(reqRecibo.getIdUen());
        reqNueva.setTipoRequisicion(obtenerTipoRequisicion(reqRecibo.getFuente()));
        reqNueva.setIdCc(reqRecibo.getIdCc());
        reqNueva.setNombreCc(reqRecibo.getNombreCc());
        reqNueva.setCodigoCc(reqRecibo.getCodigoCc());
        return reqNueva;
    }
    
    
    public static void pushLinea(List<Requisicion> listaRequi,AlmacenRecibos requisicion){
        listaRequi.stream().filter(req -> Objects.equals(requisicion.getIdRequisicion(), req.getIdRequisicion())).forEachOrdered((req) -> {
            if(!lineaExistente(req.getLineas(),requisicion.getIdPartida())){
                req.getLineas().add(lineaBuilder(requisicion));
            }
        });
    }
    
    public static void pushRequisicion(List<Requisicion> listaRequi,AlmacenRecibos requisicion){
        if(!requisicionExistente(listaRequi,requisicion.getIdRequisicion())){
            listaRequi.add(requisicionBuilder(requisicion));
        }      
    }
    
    
    public static boolean lineaExistente(List<Linea> listaLineas, Integer numeroLinea){
        if(listaLineas.isEmpty()){
            return false;
        }else{
            if (listaLineas.stream().anyMatch(linea -> Objects.equals(numeroLinea, linea.getNumeroLinea()))) {
                return true;
            }
        }
        return false;
    }
    
    
    
    //METODO PARA LAS 2 CONSULTAS
    public static boolean requisicionExistente(List<Requisicion> listaRequi,Integer requisicion){
        if(listaRequi.isEmpty()){
            return false;
        }else{
            if (listaRequi.stream().anyMatch(req -> Objects.equals(requisicion, req.getIdRequisicion()))) {
                return true;
            }
        }
        return false;
    }
    
    public static String obtenerTipoRequisicion(String fuente){
        String tipoRequisicion;
        switch(fuente){
            case "A":
                tipoRequisicion="Almacén";
                break;
            case "K":
                tipoRequisicion="Consignación";
                break;
            case "L":
                tipoRequisicion="Lento Movimiento";
                break;
            case "C":
                tipoRequisicion="Spot";
                break;
            case "MCCE":
                tipoRequisicion="MultiCC";
                break;
            case "MCCV":
                tipoRequisicion="MultiCC";
                break;
            case "E":
                tipoRequisicion="Catálogo externo";
                break;
            case "P":
                tipoRequisicion="Catálogo interno";
                break;
            case "V":
                tipoRequisicion="Vales fondo";
                break;
            default:
                tipoRequisicion="Desconocida";;
                break;
            }
        return tipoRequisicion;
    }
}
