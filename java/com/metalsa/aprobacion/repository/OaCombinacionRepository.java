package com.metalsa.aprobacion.repository;

import com.metalsa.aprobacion.model.CategoryCentroCosto;
import com.metalsa.aprobacion.model.OaCombinacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
public interface OaCombinacionRepository extends JpaRepository<OaCombinacion, OaCombinacion.OaCombinacionPk> {

//    @Cacheable("budget")
    CategoryCentroCosto findPresupuestoCcByCuentaAndIdioma(
            @Param("cuenta") long cuenta,
            @Param("idioma") String idioma);

    List<CategoryCentroCosto> findPresupuestoByCuentaInAndIdioma(
            @Param("cuentas") List<Long> cuentas,
            @Param("idioma") String idioma);

    List<CategoryCentroCosto> budgetByUenAndCentroCostoAndIdioma(
            @Param("uen") Long uen,
            @Param("cc") Long centro,
            @Param("idioma") String idioma);
}
