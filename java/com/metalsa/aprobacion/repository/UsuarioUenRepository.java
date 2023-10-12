package com.metalsa.aprobacion.repository;

import com.metalsa.aprobacion.model.UsuarioUen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
public interface UsuarioUenRepository extends JpaRepository<UsuarioUen, UsuarioUen.Pk> {
    List<UsuarioUen> findAllByIdUenOrderByIdUsuario(Long uen);
}
