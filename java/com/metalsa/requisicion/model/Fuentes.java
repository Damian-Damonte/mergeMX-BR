package com.metalsa.requisicion.model;

import com.fasterxml.jackson.annotation.JsonValue; 

/**
 *
 * @author Gamaliel Espinoza M.
 */
public enum Fuentes {
    PROVEEDOR(1, "P"),
    ALMACEN(2, "A"),
    SPOT(3, "C"),
    CONSIGNACION(4, "K"),
    LENTO_MOVIMIENTO(5, "L"),
    VALES_FONDO(6, "V"),
    INTERUEN(7, "I"),
    PUNCHOUT(8, "C"),
    PEDIDO_ESPECIAL(9, "C"),
    NONE(10000, ".");
    
    int codigo;
    String fuente;
    
    private Fuentes(int codigo, String fuente) {
        this.codigo = codigo;
        this.fuente = fuente;
    }
    
    public static Fuentes fromCodigo(int codigo) {
        for (Fuentes f : Fuentes.values()) {
            if (f.equals(codigo)) {
                return f;
            }
        }
        return Fuentes.NONE;
    }
            
    @JsonValue
    public String getFuente() {
        return this.fuente;
    }
    
    public int getCodigo() {
        return this.codigo;
    }
    
    public boolean equals(String fuente) {
        return fuente != null && this.fuente != null && this.fuente.equals(fuente);
    }
    
    public boolean equals(Integer codigo) {
        return codigo != null && codigo == this.codigo;
    }
}
