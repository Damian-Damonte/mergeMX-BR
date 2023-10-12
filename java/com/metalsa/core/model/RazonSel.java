/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author APOJO9952
 */
@Entity
@IdClass(RazonSel.class)
@XmlRootElement
@NamedNativeQueries({
    @NamedNativeQuery(name = "RazonSel.findRazonesByLanguage", query = "SELECT   ID_RAZON, "
            + "         DECODE (?1, 'ESA', NOMBRE_ESA, 'US', NOMBRE_US, NOMBRE_ESA) RAZON,UNIONS "
            + "   FROM RAZON_SEL_COTIZACION "
            + "   WHERE ID_RAZON > 0  "
            + "   AND APLICACION = 'DCompras'  "
            + "  ORDER BY ID_RAZON", resultClass = RazonSel.class, resultSetMapping = "RazonSelMapping")})
@SqlResultSetMappings({
    @SqlResultSetMapping(name = "RazonSelMapping",
            classes = {
                @ConstructorResult(targetClass = RazonSel.class,
                        columns = {
                            @ColumnResult(name = "ID_RAZON", type = String.class)
                            ,
                          @ColumnResult(name = "RAZON", type = String.class),
                            @ColumnResult(name = "UNIONS", type = String.class)})}),})
public class RazonSel implements Serializable {

    @Id
    private String ID_RAZON;
    private String RAZON;
    private String UNIONS;

    public RazonSel(String ID_RAZON, String RAZON,String UNIONS) {
        this.ID_RAZON = ID_RAZON;
        this.RAZON = RAZON;
        this.UNIONS = UNIONS;
    }

    public String getID_RAZON() {
        return ID_RAZON;
    }

    public void setID_RAZON(String ID_RAZON) {
        this.ID_RAZON = ID_RAZON;
    }

    public String getRAZON() {
        return RAZON;
    }

    public void setRAZON(String RAZON) {
        this.RAZON = RAZON;
    }

    public String getUNIONS() {
        return UNIONS;
    }

    public void setUNIONS(String UNIONS) {
        this.UNIONS = UNIONS;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.ID_RAZON);
        hash = 17 * hash + Objects.hashCode(this.RAZON);
        hash = 17 * hash + Objects.hashCode(this.UNIONS);
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
        final RazonSel other = (RazonSel) obj;
        if (!Objects.equals(this.ID_RAZON, other.ID_RAZON)) {
            return false;
        }
        return Objects.equals(this.RAZON, other.RAZON);
    }

}
