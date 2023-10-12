package com.metalsa.finanzas.model;

import lombok.Data;

/**
 *
 * @author JL
 */
@Data
public class CategoriaPojo {
  
    private Long id;
    private Long idCategoriaDestino;
    private Long idCategoriaOrigen;
    private Double monto;
    private String moneda;
}
