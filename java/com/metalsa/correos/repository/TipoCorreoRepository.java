package com.metalsa.correos.repository;

import com.metalsa.correos.model.NvcTblTipoCorreo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author APOMR10051
 */
@Repository
public interface TipoCorreoRepository extends PagingAndSortingRepository<NvcTblTipoCorreo, Integer> {

    Iterable<NvcTblTipoCorreo> findAllByOrderByIdTipoCorreo();
}
