package com.metalsa.aprobacion.repository;

import com.metalsa.aprobacion.model.SuplierCompareDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
public interface SuplierCompareDtoRepository extends JpaRepository<SuplierCompareDto, Long> {
    
    //<ERM#17475>
    List<SuplierCompareDto> getAllByRequisicion(Long requisicion,Integer idstatus);
    //</ERM#17475>
}
