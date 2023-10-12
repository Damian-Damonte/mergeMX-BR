/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.asignacionFamilias.service;

import com.metalsa.compradorCC.pojo.CategoriaPojo;
import com.metalsa.compradorCC.pojo.CompradorCcPojo;
import com.metalsa.compradorCC.pojo.ListDc;
import com.metalsa.compradorCC.pojo.RequestFiltros;
import java.util.List;

/**
 *
 * @author jose.jimenez07
 */
public interface AsignacionFamiliasService {

    List<CategoriaPojo> getDatosByFiltrosFam(RequestFiltros requestFiltros); // DATOS QUE SE MUESTRAN EN LA TABLA

    List<CompradorCcPojo> getCompradorFam(Long idUen,String tipoAprobacion);   // COMBO COMPRADORES 

    List<CompradorCcPojo> getPrevCompradorFam(Long idUen,String tipoAprobacion);    //COMBO  COMPRADORES PREVIOS

    List<CompradorCcPojo> getAdministradorFam(Long idUen,String tipoAprobacion); // COMBO ADMINISTRADOR

    void saveDatosFamilias(List<ListDc> datosTabla,String tipoAprobacion);

    List<CompradorCcPojo> getAllComprador(Long idUen,String tipoAprobacion);    //COMBO  CACHE COMPRADORES
    
    CompradorCcPojo getAdministradorPorUen(Long idUen,String tipoAprobacion); // El administrador por uen de la pantalla asignacion de compradores

}
