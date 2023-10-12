package com.metalsa.aprobacion.repository;

import com.metalsa.aprobacion.model.OaProyectos;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author APOOD9272
 */
@Repository
public interface ProyectoRepository extends PagingAndSortingRepository<OaProyectos, Long>,
        ProyectoProcedures {

    String getAprobadorProyecto(Long idProyecto);

    List<OaProyectos> findByIdUen(@Param("idUen") Integer uen);
    
}
