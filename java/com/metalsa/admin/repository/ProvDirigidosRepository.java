package com.metalsa.admin.repository;

import com.metalsa.admin.model.ProveedorDirigido;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author APOMR10051
 */
@Repository
public interface ProvDirigidosRepository extends PagingAndSortingRepository<ProveedorDirigido, Integer> {
    List<ProveedorDirigido> getProvDirigidosUen(@Param("idUen") Integer idUen);
}
