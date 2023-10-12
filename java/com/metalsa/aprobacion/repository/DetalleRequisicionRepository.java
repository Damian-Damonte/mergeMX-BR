package com.metalsa.aprobacion.repository;

import com.metalsa.aprobacion.model.DetalleRequisicion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by ruben.bresler on 07/07/2017.
 */
public interface DetalleRequisicionRepository extends JpaRepository<DetalleRequisicion, DetalleRequisicion.Key> {

    List<DetalleRequisicion> getAllByRequisicionOrderByLineaAsc(Long id);

    Optional<DetalleRequisicion> getByRequisicionAndLinea(Long id, Long linea);

    @Query(value = "select d.* from requisicion r, detalle_de_requisicion d "
            + "where r.id_requisicion = d.id_requisicion "
            + "and r.id_requisicion = :idRequisicion /*#pageable*/",
            countQuery = "select count(1) "
            + "from requisicion r, detalle_de_requisicion d "
            + "where r.id_requisicion = d.id_requisicion "
            + "and r.id_requisicion = :idRequisicion order by d.id_requisicion desc, d.id_partida ",
            nativeQuery = true
    )
    Page<DetalleRequisicion> getRequisicionById(
            @Param("idRequisicion") String idRequisicion,
            Pageable page);

    @Query(value = "SELECT COUNT(*) FROM detalle_de_requisicion u WHERE u.id_requisicion=:idRequisicion and u.id_partida=:idLinea and (u.estatus='APROBADA' or nvl(U.SELECCION,0)=1)", nativeQuery = true)
    Long estaAprobada(@Param("idRequisicion") Long idRequisicion, @Param("idLinea") Long idLinea);
}
