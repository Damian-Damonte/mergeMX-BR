package com.metalsa.catalogo.model;

/**
 *
 * @author APOMR10051
 */
<<<<<<< HEAD
public class ItemEstatus {

    private Integer idEstatus;
    private String estatus;

    public Integer getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Integer idEstatus) {
        this.idEstatus = idEstatus;
=======
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
>>>>>>> mexico
    }

    public String getEstatus() {
        return estatus;
    }
<<<<<<< HEAD

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
    
=======
    
    public boolean equals(String estatus) {
        return this.estatus != null && estatus != null && this.estatus.equals(estatus);
    }
    
    public boolean equals(Integer estatus) {
        return estatus != null && estatus == this.idEstatus;
    }
>>>>>>> mexico
}
