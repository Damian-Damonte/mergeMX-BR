package com.metalsa.finanzas.repository;

import com.metalsa.finanzas.model.RespuestaPojo;
import com.metalsa.finanzas.model.SolicitudIncremento;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author JL
 */
public interface SolicitudIncrementoRepository extends PagingAndSortingRepository <SolicitudIncremento, Long>{
    
    
    
    Page<SolicitudIncremento> findAllByIdCcDestino(Long idCC, Pageable p);
    
    List<SolicitudIncremento> getHistoricoByUser(String idioma, String usuario);    
                
    List<SolicitudIncremento> getSolicitudes(String idioma, String usuario);
          
    List<SolicitudIncremento> getSolicitudesFinanzas(String idioma, String usuario);
    
    RespuestaPojo crearSolicitud(Long idUen, Long idCcDestino, Long idCcOrigen, 
            String periodoDestino, String periodoOrigen, Date fechaNecesidad, 
            String razon, String usuario, String categorias, String tipo);
    
    
    RespuestaPojo actualizarSolicitud(String ids, String usuario, String razon, String accion);
    
    RespuestaPojo setSolicitudEnVisto(Long id);
    
    Integer getCountAprobacionCC(String usuario);
    
    Integer getCountAprobacionFinanzas(String usuario);
    
    Integer getEsPreaprobador(String usuario);
    
    RespuestaPojo cancelarSolicitud(Long id, String usuario);
    
}
