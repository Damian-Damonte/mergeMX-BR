package com.metalsa.core.repository;

import com.metalsa.core.model.Periodos;
import java.util.List;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Oscar del Angel
 */
//R41223
@Repository
public interface PeriodosRepository extends JpaRepository<Periodos, Integer>, PeriodosRepositoryProcedure {

    Periodos findOneByAnioAndNumMes(Integer anio, Integer numMes);

    List<Periodos> findByIdiomaOrderByAnioDesc(String idioma);

    List<Periodos> findByIdiomaAndAnioOrderByNumMesDesc(String idioma, Integer anio);

    List<Periodos> findByIdiomaAndAnioBetween(String idioma, Integer inicio, Integer fin);

    @Cacheable(cacheNames = "rangoPeriodos")
    List<Periodos> findByIdiomaAndIdBetween(String idioma, Integer inicio, Integer fin);

    /**
     *
     * @param idioma idioma
     * @param anioInicial anio inicial para obtener los periodos
     * @param currentYear anio actual
     * @param mes por default es el mes actual.
     * @return
     */
    List<Periodos> getPeriodoDesde(@Param("idioma") String idioma, @Param("anioInicial") Integer anioInicial, @Param("currentYear") Integer currentYear, @Param("mes") Integer mes);
    
    List<Periodos> getAllByAnioActualDisponible(@Param("idioma") String idioma);

}
