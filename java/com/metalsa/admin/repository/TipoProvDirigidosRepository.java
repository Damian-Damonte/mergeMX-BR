package com.metalsa.admin.repository;

import com.metalsa.admin.model.ProveedorDirigidoPorTipo;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author APOMR10051
 */
@Repository
public interface TipoProvDirigidosRepository extends PagingAndSortingRepository<ProveedorDirigidoPorTipo, Integer> {

    List<ProveedorDirigidoPorTipo> getByIdProvDirigido(@Param("idProvDirigido") Integer idProvDirigido, @Param("idIdioma") String idIdioma);

    List<ProveedorDirigidoPorTipo> getAllTipos(@Param("idIdioma") String idIdioma);
}
