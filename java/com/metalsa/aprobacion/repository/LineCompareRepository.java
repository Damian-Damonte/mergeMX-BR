package com.metalsa.aprobacion.repository;

import com.metalsa.aprobacion.model.LineCompare;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
public interface LineCompareRepository extends JpaRepository<LineCompare, Long> {
    //<ERM#17475>
    List<LineCompare> getAllByRequisicion(Long requisicion, String idioma,Integer idstatus);
    //</ERM#17475>
}
