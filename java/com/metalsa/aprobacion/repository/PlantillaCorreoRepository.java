package com.metalsa.aprobacion.repository;

import com.metalsa.aprobacion.model.PlantillaCorreo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
public interface PlantillaCorreoRepository extends JpaRepository<PlantillaCorreo, Long> {

    Optional<PlantillaCorreo> getByDescripcionAndIdioma(@Param("d") String descripcion, @Param("i") String idioma);

    List<PlantillaCorreo> getByDescripcion(@Param("d") String descripcion);
    
    List<PlantillaCorreo> getByIdTipoCorreo(@Param("idTipoCorreo") Integer idTipoCorreo);
}
