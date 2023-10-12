package com.metalsa.generales.repository;

import com.metalsa.generales.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by IntelliJ Idea
 *
 * @author miguel.rdz
 */
public interface UsuarioSpxRepository extends JpaRepository<Usuario, String> {

    Iterable<Usuario> findByNombre(@Param("query") String query);
    
}
