package com.metalsa.catalogo.repository;

import com.metalsa.catalogo.model.NvcTblCatalogo;
import com.metalsa.catalogo.model.NvcTblCatalogoUen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author APOMR10051
 */
public interface CatalogoUenRepository extends JpaRepository<NvcTblCatalogoUen, Integer> {

    NvcTblCatalogoUen findByIdCatalogoUen(@Param("idCatalogoUen") Integer idCatalogoUen);
    
    List<NvcTblCatalogoUen> getByIdCatalogo(@Param("idCatalogo") Integer idCatalogo);
    
    NvcTblCatalogoUen getByIdCatalogoUen(@Param("idCatalogoUen") Integer idCatalogoUen);
}
