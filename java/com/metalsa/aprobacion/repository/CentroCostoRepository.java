	//MDA_REPORTES_FINANZAS: reestructruracion de la clase, favor de replazar todo
package com.metalsa.aprobacion.repository;

import com.metalsa.aprobacion.model.CentroCosto;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */


public interface CentroCostoRepository extends PagingAndSortingRepository<CentroCosto, Long>,CentroCostoInterface {

    CentroCosto getByIdAndIdUenAndIdioma(Long id, Long uen, String idioma);
    

}
