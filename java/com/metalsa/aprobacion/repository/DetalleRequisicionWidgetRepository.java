package com.metalsa.aprobacion.repository;

import com.metalsa.aprobacion.model.DetalleRequisicionWidget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by miguel.rdz on 09/08/2018.
 */
//<R17226>
public interface DetalleRequisicionWidgetRepository extends JpaRepository<DetalleRequisicionWidget, DetalleRequisicionWidget.Key> {

    @Query(value = "select d.id_requisicion, d.id_partida, d.descripcion_producto, ei.desc_estatus, d.id_orden_de_compra, d.id_partida_oc, NVC_PKG_HOME_SPX.FN_TRANSLATE_STATUS(d.estatus_oc, :idIdioma) estatus_oc, d.fuente"
            + ",\n"
            + "(SELECT PEDIDO_ESPECIAL\n"
            + "FROM NVC_TBL_CARRO_COMPRA\n"
            + "WHERE ID_REQUISICION = d.id_requisicion\n"
            + "AND ID_PARTIDA = d.id_partida)\n"
            + "PEDIDO_ESPECIAL "
            + "from requisicion r, detalle_de_requisicion d, dc_estatus e, dc_estatus_idioma ei "
            + "where r.id_requisicion = d.id_requisicion "
            + "and d.id_estatus = e.sc_id "
            + "and e.sc_id = ei.id_estatus "
            + "and ei.id_idioma = :idIdioma "
            + "and r.id_usuario = :user and d.estatus not in (:estatus) order by d.id_requisicion asc, d.id_partida asc",
            nativeQuery = true
    )
    List<DetalleRequisicionWidget> getRequisByUsuarioAndEstatus(
            @Param("idIdioma") String idIdioma,
            @Param("user") String usuario,
            @Param("estatus") List<String> estatus);
}
//</R17226>