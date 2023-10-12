/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.portalProveedor.repository;

import com.metalsa.portalProveedor.model.NvcTblGastoAdicional;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mlopez
 */

@Repository
public interface NvcTblGastoAdicionalRepository extends PagingAndSortingRepository<NvcTblGastoAdicional, Integer>{
    
    @Query(value = "SELECT n FROM NvcTblGastoAdicional n WHERE n.idReqLineaProv.idReqLineaProv = :idReqLineaProv order by n.idGastoAdicional")
    List<NvcTblGastoAdicional> getByIdReqLineaProv(@Param("idReqLineaProv") Integer idReqLineaProv);
    
}
