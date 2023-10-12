package com.metalsa.aprobacion.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@Data
public class Periodo {
    private String nombre;
    private Date inicio;
    private Date fin;

    public Periodo() {
    }

    public Periodo(String nombre, Date inicio, Date fin) {
        this.nombre = nombre;
        if (inicio != null)
            this.inicio = new Date(inicio.getTime());
        if (fin != null)
            this.fin = new Date(fin.getTime());
    }

    public Date getInicio() {
        return inicio == null ? null : (Date) inicio.clone();
    }

    public void setInicio(Date inicio) {
        if (inicio == null)
            this.inicio = null;
        else
            this.inicio = (Date) inicio.clone();
    }

    public Date getFin() {
        return fin == null ? null : (Date) fin.clone();
    }

    public void setFin(Date fin) {
        if (fin == null)
            this.fin = null;
        else
            this.fin = (Date) fin.clone();
    }
}
