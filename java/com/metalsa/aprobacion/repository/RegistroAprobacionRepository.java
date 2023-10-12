package com.metalsa.aprobacion.repository;

import com.metalsa.aprobacion.model.LogRequisicion;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author APOOD9272
 */
public interface RegistroAprobacionRepository extends CrudRepository<LogRequisicion, Long>,RegistroAprobacionProcedure{
    
    

    
}
