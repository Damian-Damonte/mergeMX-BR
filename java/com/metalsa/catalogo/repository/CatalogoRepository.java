package com.metalsa.catalogo.repository;

import com.metalsa.catalogo.model.NvcTblCatalogo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author APOMR10051
 */
public interface CatalogoRepository extends JpaRepository<NvcTblCatalogo, Integer> {

    NvcTblCatalogo findByIdCatalogo(@Param("idCatalogo") Integer idCatalogo);
    
    NvcTblCatalogo getByIdCatalogo(@Param("idCatalogo") Integer idCatalogo);
}
