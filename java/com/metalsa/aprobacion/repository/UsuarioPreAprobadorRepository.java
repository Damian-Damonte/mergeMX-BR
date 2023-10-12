package com.metalsa.aprobacion.repository;

import com.metalsa.aprobacion.model.UsuarioPreAprobador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
public interface UsuarioPreAprobadorRepository extends JpaRepository<UsuarioPreAprobador, UsuarioPreAprobador.Pk> {
    
    List<UsuarioPreAprobador> findAllByIdUenOrderByIdProyecto(Long uen);
    
    List<UsuarioPreAprobador> findAllByIdUenAndIdProyectoIn(Long uen, Collection<Long> proyectos);

    List<UsuarioPreAprobador> findAllByIdUsuario(String idUsuario);
    
    @Procedure(name = "addRolPreaprobador")
    Integer asignarUsuario(@Param("p_uen") Long uen, @Param("p_proyecto") Long proyecto, @Param("p_usuario") String usuario);
}
