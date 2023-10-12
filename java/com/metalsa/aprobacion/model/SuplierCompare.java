package com.metalsa.aprobacion.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@Data
@AllArgsConstructor
public class SuplierCompare implements Serializable {
    private Long idProvedor;
    private String nombreProveedor;
    private String terminosEspecialesPago;
    private List<SuplierCompareDetail> rfq;

    /**
     * Detalle de RFQ por proveedor
     */
    @Data
    @Builder
    public static class SuplierCompareDetail implements Serializable {
        private Long idLinea;
        private Double precioUnitario;
        private Double cantidad;
        private Double gastoAdicional;
        private Double importe;
        private String moneda;
        private Double cambioMoneda;
        private String incoterm;
        private Integer tiempoEntrega;
        private boolean seleccionComprador;
        private boolean seleccionRequisitor;

        public static SuplierCompareDetail fromDto(SuplierCompareDto detail) {
            return SuplierCompareDetail.builder()
                    .idLinea(detail.getIdLinea())
                    .precioUnitario(detail.getPrecioUnitario())
                    .cantidad(detail.getCantidadRequerida())
                    .gastoAdicional(detail.getGastoAdicional())
                    .importe(detail.getImporte())
                    .moneda(detail.getMoneda())
                    .cambioMoneda(detail.getCambioMoneda())
                    .incoterm(detail.getIncoterm())
                    .tiempoEntrega(detail.getTiempoEntrega())
                    .seleccionComprador(detail.isSeleccionComprador())
                    .seleccionRequisitor(detail.isSeleccionRequisitor())
                    .build();
        }
    }
}
