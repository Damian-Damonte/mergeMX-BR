package com.metalsa.aprobacion.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class CategoryCentroCosto extends Presupuesto {

    private Long cuenta;
    private Long uen;
    private Long centroCosto;
    private String codigoCC;
    private String segmento3;
    private Long categoria;
    private String nombreCategoria;
    private boolean permitido;  //contralor R40102 

    public CategoryCentroCosto(
            Double disponible,
            Double inicial,
            Double actual,
            Double comprometido,
            Long cuenta,
            Long uen,
            Long centroCosto,
            String codigoCC,
            String segmento3,
            Long categoria,
            String nombreCategoria) {
        super(disponible, inicial, actual, comprometido);
        this.cuenta = cuenta;
        this.uen = uen;
        this.centroCosto = centroCosto;
        this.codigoCC = codigoCC;
        this.segmento3 = segmento3;
        this.categoria = categoria;
        this.nombreCategoria = nombreCategoria;
    }
}
