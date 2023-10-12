package com.metalsa.aprobacion.repository;

import com.metalsa.aprobacion.model.AdjuntoAprobacion;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
public interface AdjuntosRepository extends PagingAndSortingRepository<AdjuntoAprobacion, AdjuntoAprobacion.Pk> {

    List<AdjuntoAprobacion> getAllByRequisicionOrderByLineaAsc(Long id);

    Long countByRequisicion(Long idRequisicion);

        @Query(value = "   SELECT \n"
            + "         d.id_requisicion,\n"
            + "          d.id_partida,\n"
            + "          ROWNUM id_adjunto,\n"
            + "          cart.id_carro_compra,\n"
            + "          d.descripcion_producto,\n"
            + "          d.id_unidad_de_medida,\n"
            + "          NVL (\n"
            + "             (SELECT descripcion\n"
            + "                FROM NVC_TBL_DOCUMENTOS_RFQ\n"
            + "               WHERE     llave_id in \n"
            + "                            (SELECT llave_id\n"
            + "                               FROM NVC_TBL_RFQ_LINEA\n"
            + "                              WHERE     id_requisicion = d.id_requisicion\n"
            + "                                    AND id_linea = d.id_partida)\n"
            + "                     AND UPPER (nombre_formato) =\n"
            + "                            UPPER (\n"
            + "                                  files.nombre_archivo\n"
            + "                               || files.extension_archivo)\n"
            + "                     AND ROWNUM = 1),\n"
            + "             (files.nombre_archivo || files.extension_archivo))\n"
            + "             desc_rfq,\n"
            + "          files.nombre_archivo || files.extension_archivo nombre_archivo,\n"
            + "          files.tamanio_archivo,\n"
            + "          files.fecha_creacion,\n"
            + "          files.nombre_archivo_ftp || files.extension_archivo archivo_ftp,\n"
            + "          REGEXP_REPLACE (\n"
            + "             REGEXP_REPLACE (files.ubicacion_ftp, '^/?[a-z]+/', ''),\n"
            + "             '^/',\n"
            + "             '')\n"
            + "             ubicacion,\n"
            + "             CASE WHEN INSTR (ubicacion_ftp, '/fad/') <> 0 THEN 1 ELSE 0 END "
            + "             is_Adjunto_fad,"
            + "             id_fad "
            + "     FROM detalle_de_requisicion d\n"
            + "          JOIN nvc_tbl_carro_compra cart\n"
            + "             ON     cart.id_requisicion = d.id_requisicion\n"
            + "                AND cart.id_partida = d.id_partida\n"
            + "          JOIN nvc_tbl_documento files\n"
            + "             ON (files.id_carro_compra = cart.id_carro_compra OR files.id_formato_asignacion_directa = cart.id_fad) "
            + " where d.id_requisicion=:idRequisicion order by is_Adjunto_fad asc",
            nativeQuery = true
    )
    List<AdjuntoAprobacion> getAllByRequisicionOrderByLineaAscQuery(@Param("idRequisicion") Long idRequisicion);
}
