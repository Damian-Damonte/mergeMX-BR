package com.metalsa.aprobacion.repository;

import com.metalsa.aprobacion.model.Requisicion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * Created by ruben.bresler on 07/07/2017.
 */
public interface RequisicionRepository extends JpaRepository<Requisicion, Long> {

    @Query(value = "select distinct r.* " +
                    "from requisicion r, detalle_de_requisicion d " +
                    "where d.id_requisicion = r.id_requisicion " +
                    "  and r.id_usuario = ?1 " +
                    "  and d.fuente = ?2 /*#pageable*/",
            countQuery = "select count(1) " +
                    "from (select distinct r.id_requisicion " +
                    "   from requisicion r, detalle_de_requisicion d " +
                    "   where d.id_requisicion = r.id_requisicion " +
                    "       and r.id_usuario = ?1 " +
                    "       and d.fuente = ?2) ",
            nativeQuery = true
    )
    Page<Requisicion> findAllByUsuarioAndTipaje(String usuario, String tipo, Pageable page);

    Page<Requisicion> findAllByUsuario(String usuario, Pageable page);

    Optional<Requisicion> getByIdRequisicion(Long id);
}
