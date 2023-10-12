package com.metalsa.aprobacion.repository;

import com.metalsa.aprobacion.model.Proyecto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author APOOD9272
 */

@Repository
public interface PreaprobadorProyectoRepository extends JpaRepository<Proyecto, Long>{
    
    List<Proyecto> findAllByIdUen(Long idUen);
    
    List<Proyecto> findAllByIdUenAndActive(Long idUen,Short active);
    
    List<Proyecto> findAllByActiveOrderByIdUen(Short Active);

    @Query(name = "Proyecto.findProyectosUsuario")
    List<Proyecto> findProyectosByUser(@Param("id_usuario") String user);
    
    
}
