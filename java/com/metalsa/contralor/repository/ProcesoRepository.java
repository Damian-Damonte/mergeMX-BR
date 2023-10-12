package com.metalsa.contralor.repository;

import com.metalsa.contralor.model.CentroCostoProcesoDetalle;
import com.metalsa.contralor.model.Proceso;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Oscar del Angel
 */
@Repository
public interface ProcesoRepository extends JpaRepository<Proceso, Integer>, ProcesosProcedure {

    @Query(name = "CentroCostoProcesoDetalle.findCCSUsers")
    List<CentroCostoProcesoDetalle> findCCSUsers(Integer uen, Long cc);
}
