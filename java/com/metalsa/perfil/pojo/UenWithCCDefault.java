package com.metalsa.perfil.pojo;

import java.util.List;

/**
 *
 * @author APOMR10051
 */
public class UenWithCCDefault {

    private Long idUen;
    private String nombre;
    private String moneda;
    private List<CentroCosto> uenCc;
    private List<CentroCosto> ccDisponibles;

    public Long getIdUen() {
        return idUen;
    }

    public void setIdUen(Long idUen) {
        this.idUen = idUen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public List<CentroCosto> getUenCc() {
        return uenCc;
    }

    public void setUenCc(List<CentroCosto> uenCc) {
        this.uenCc = uenCc;
    }

    public List<CentroCosto> getCcDisponibles() {
        return ccDisponibles;
    }

    public void setCcDisponibles(List<CentroCosto> ccDisponibles) {
        this.ccDisponibles = ccDisponibles;
    }
}
