package com.metalsa.contralor.controller;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class UenContralor implements Serializable{
    @Id
    @Column(name="organization_id")
    private Integer idUen;
    
    @Column(name="organization_name")
    private String nombreUen;
    
    @Column(name="company_name")
    private String nombreCompania;
    
    @Column(name="currency_code")
    private String moneda;
    
    @Column(name="company")
    private String compania;

    public Integer getIdUen() {
        return idUen;
    }

    public void setIdUen(Integer idUen) {
        this.idUen = idUen;
    }

    public String getNombreUen() {
        return nombreUen;
    }

    public void setNombreUen(String nombreUen) {
        this.nombreUen = nombreUen;
    }

    public String getNombreCompania() {
        return nombreCompania;
    }

    public void setNombreCompania(String nombreCompania) {
        this.nombreCompania = nombreCompania;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.idUen);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UenContralor other = (UenContralor) obj;
        if (!Objects.equals(this.idUen, other.idUen)) {
            return false;
        }
        return true;
    }
    
    
}
