package com.metalsa.aprobacion.repository;

import com.metalsa.aprobacion.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    Optional<Usuario> getById(String usuario);
}
