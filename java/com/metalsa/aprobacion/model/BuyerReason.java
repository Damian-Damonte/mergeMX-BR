package com.metalsa.aprobacion.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@Data
@Entity
@IdClass(BuyerReason.Pk.class)
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "BuyerReason.getBuyerReason",
                query = "select d.id_requisicion requisicion, d.id_partida linea, " +
                        "  get_supplier_by_buyer(d.id_requisicion, d.id_partida) proveedor, " +
                        "  get_req_selection_motive(d.id_requisicion, d.id_partida) razon_comprador, " +
                        "  get_monto_con_gastos_req(d.id_requisicion,d.id_partida) monto_total " +
                        "from detalle_de_requisicion d " +
                        "where d.id_requisicion = :req " +
                        "  and d.id_partida = :line",
                resultClass = BuyerReason.class
        )
})
public class BuyerReason {

    @Id
    private Long requisicion;

    @Id
    private Long linea;

    private String proveedor;

    private String razonComprador;

    private Double montoTotal;

    /**
     * primary key
     */
    @Data
    public static class Pk implements Serializable {
        @Id
        private Long requisicion;

        @Id
        private Long linea;
    }
}
