/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.portalProveedor.repository;

import com.metalsa.portalProveedor.model.NvcTblReqLineaProv;
import java.math.BigInteger;
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
public interface NvcTblReqLineaProvRepository extends PagingAndSortingRepository<NvcTblReqLineaProv, Integer> {
    @Query(value = "SELECT rlp FROM NvcTblReqLineaProv rlp "
            + "INNER JOIN rlp.idRfqProv rp "
            + "WHERE rp.idRfq.idRfq = :idRfq "
            + "AND rp.nvcTblProvSitesH.nvcTblProvSitesHPK.idProveedor = :idProveedor "
            + "AND rlp.idEstatus = (SELECT es.scId FROM com.metalsa.portalProveedor.model.DcEstatus es WHERE es.descEstatus = 'PENDIENTE'  ) "
            + "AND rlp.declinada = 'NO'"
           // + "AND rp.nvcTblProvSitesH.nvcTblProvSitesHPK.idSucursalProveedor = :idSucProveedor "
            + " ORDER BY rlp.rfqLinea.detalleRequisicion.requisicion, "
            + "rlp.rfqLinea.detalleRequisicion.linea"
            )
    List<NvcTblReqLineaProv> findRquisByRfqAndSupplier(@Param("idRfq") Integer idRfq, @Param("idProveedor") Long idProveedor);
}

