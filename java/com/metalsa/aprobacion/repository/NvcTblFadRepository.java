/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.aprobacion.repository;

import com.metalsa.aprobacion.model.NvcTblFad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author APOMR10051
 */
public interface NvcTblFadRepository extends JpaRepository<NvcTblFad, Integer>, NvcTblFadProcedures {

    Iterable<NvcTblFad> getByIdFad(@Param("idFad") Integer idFad);

    Iterable<NvcTblFad> getByIdRequisicion(@Param("idRequisicion") Integer idRequisicion);
}
