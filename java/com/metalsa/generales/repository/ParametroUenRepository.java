package com.metalsa.generales.repository;

import com.metalsa.generales.model.ParametroByUen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
public interface ParametroUenRepository extends JpaRepository<ParametroByUen, ParametroByUen.Pk> {

    List<ParametroByUen> findAllByIdUen(Long uen);

    Optional<ParametroByUen> findByIdUenAndNombre(Long uen, String nombre);
}
