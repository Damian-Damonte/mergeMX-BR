package com.metalsa.generales.repository;

import com.metalsa.generales.model.Parametro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
public interface ParametroRepository extends JpaRepository<Parametro, Long> {

    Optional<Parametro> findByNombre(String nombre);
}
