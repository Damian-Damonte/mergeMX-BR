/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.admin.repository;

import com.metalsa.admin.model.NvcTblDocumento;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author jose.jimenez07
 */
public interface NvcTblDocumentoRepository extends CrudRepository<NvcTblDocumento, Long>{
    @Query("SELECT n FROM NvcTblDocumento n where n.provDirId=:provDirId and activo=1 and n.idUen=:idUen")
    List<NvcTblDocumento> findByProvDir(@Param("provDirId") Long provDirId,@Param("idUen")Integer idUen);
    
}
