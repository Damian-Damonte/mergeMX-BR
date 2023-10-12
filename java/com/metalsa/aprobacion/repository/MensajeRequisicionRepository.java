package com.metalsa.aprobacion.repository;


import com.metalsa.aprobacion.model.MensajeRequisicion;
import com.metalsa.aprobacion.model.NotificacionHeader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by ruben.bresler on 06/07/2017.
 */
public interface MensajeRequisicionRepository extends PagingAndSortingRepository<MensajeRequisicion, Long>,
        MensajesProcedures {
    Page<MensajeRequisicion> findAllByRemitenteAndRolDestinatarioOrderByFechaDesc(String remitente, Long rol,
                                                                                  Pageable page);

    List<MensajeRequisicion> findAllByRequisicionOrderByFechaDesc(Long requisicion);

    List<MensajeRequisicion> mensajesPorRequisicion(Long requisicion);

    List<NotificacionHeader> getProcNuevoHeader(String usuario, String idioma);
}
