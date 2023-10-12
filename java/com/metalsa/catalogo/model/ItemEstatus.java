package com.metalsa.catalogo.model;

/**
 *
 * @author APOMR10051
 */
public enum ItemEstatus {
    APPROVED(1, "APPROVED"),
    ACTIVE(45, "ACTIVE");
    
    int idEstatus;
    String estatus;

    private ItemEstatus(int id, String estatus) {
        this.idEstatus = id;
        this.estatus = estatus;
    }

    public int getIdEstatus() {
        return idEstatus;
    }

    public String getEstatus() {
        return estatus;
    }
    
    public boolean equals(String estatus) {
        return this.estatus != null && estatus != null && this.estatus.equals(estatus);
    }
    
    public boolean equals(Integer estatus) {
        return estatus != null && estatus == this.idEstatus;
    }
}
