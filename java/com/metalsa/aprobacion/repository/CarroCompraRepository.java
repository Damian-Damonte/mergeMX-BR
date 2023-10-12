package com.metalsa.aprobacion.repository;

import com.metalsa.aprobacion.model.CarroCompra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by ruben.bresler on 12/07/2017.
 */
public interface CarroCompraRepository extends PagingAndSortingRepository<CarroCompra, Long> {

    Page<CarroCompra> findAllByOrderByIdDesc(Pageable page);

    Page<CarroCompra> findAllByUsuarioOrderByIdDesc(String usuario, Pageable page);

    @Query(name = "getPreviewCarroCompraInterUen")
    Iterable<CarroCompra> getPreviewCarroCompraInterUen(String usuario, List<Long> uens);

    @Query(name = "getPreviewCarroCompra")
    Iterable<CarroCompra> getPreviewCarroCompra(String usuario, List<Long> uens);
}
