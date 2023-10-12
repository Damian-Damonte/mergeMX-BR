package com.metalsa.core.repository;

import com.metalsa.core.model.NvcTblUenUsuAprobacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author apomr
 */
@Repository
public interface UenUsuAprobacionRepository extends JpaRepository<NvcTblUenUsuAprobacion, Integer> {

    List<NvcTblUenUsuAprobacion> getByUenProceso(@Param("idUen") Integer idUen, @Param("idProceso") Integer idProceso, @Param("isDelegado") Integer isDelegado);

}
