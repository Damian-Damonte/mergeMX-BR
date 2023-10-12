package com.metalsa.catalogo.repository;

import com.metalsa.catalogo.model.NvcVmOaIvaH;
import com.metalsa.catalogo.model.NvcVmOaIvaHPK;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.query.Param;

/**
 *
 * @author APOMR10051
 */
public interface IvaRepository extends JpaRepository<NvcVmOaIvaH, NvcVmOaIvaHPK> {

    List<NvcVmOaIvaH> getIvasByIdUen(@Param("idUen") Integer idUen);
}
