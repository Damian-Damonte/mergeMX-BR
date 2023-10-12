package com.metalsa.finanzas.model;

import java.util.List;
import lombok.Data;

/**
 *
 * @author JL
 */
@Data
public class TransaccionPojo {
    
    private Long idTransaccion;
    private Long idUen;
    private List<CatalogoPojo> centrosCosto;
    private List<CatalogoPojo> categorias;
    private String periodo;
    private String tipoTransaccion;
    private Double porcentaje;
    private Double monto;
    private String moneda;
    private String solicitante;
    private String aprobador;
    private String razon;
    private String usuario;
    private List<AdjuntoPojo> adjuntos;
    
}
