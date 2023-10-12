/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.compradorCC.service;

import com.metalsa.aprobacion.model.NvcTblOrganizacionesH;
import com.metalsa.compradorCC.pojo.CategoriaPojo;
import com.metalsa.compradorCC.pojo.CompradorCcPojo;
import com.metalsa.compradorCC.pojo.DatosTabla;
import com.metalsa.compradorCC.pojo.ListDc;
import com.metalsa.compradorCC.pojo.RequestFiltros;
import java.util.List;

/**
 *
 * @author jose.jimenez07
 */
public interface CompradorCcService {

    Iterable<NvcTblOrganizacionesH> getUensCc(String idUsuario);    //COMBO LIST UEN 

    List<CompradorCcPojo> getBuyerCc(Long idUen);   // COMBO COMPRADORES 

    List<CompradorCcPojo> getAdministrador(Long idUen); // COMBO ADMINISTRADOR

    List<CompradorCcPojo> getPrevComprador(Long idUen);    //COMBO  COMPRADORES PREVIOS

    List<CompradorCcPojo> getAllComprador(Long idUen);    //COMBO  CACHE COMPRADORES

    List<CategoriaPojo> getDatosByFiltrosFam(RequestFiltros requestFiltros); // DATOS QUE SE MUESTRAN EN LA TABLA
    
    List<DatosTabla> getDatosByFiltros(RequestFiltros requestFiltros); // DATOS QUE SE MUESTRAN EN LA TABLA
    
    CompradorCcPojo getAdministradorPorUen(Long idUen); // El administrador por uen de la pantalla asignacion de compradores
    
    void saveDatosCompradorCc(List<DatosTabla> datosTabla);

    List<CompradorCcPojo> getCompradorFam(Long idUen);   // COMBO COMPRADORES 
    
    List<CompradorCcPojo> getPrevCompradorFam(Long idUen);    //COMBO  COMPRADORES PREVIOS
    
    List<CompradorCcPojo> getAdministradorFam(Long idUen); // COMBO ADMINISTRADOR
    
    void saveDatosFamilias(List<ListDc> datosTabla);
    
}
