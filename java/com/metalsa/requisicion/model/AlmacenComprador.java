package com.metalsa.requisicion.model;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author APOJA5585
 */
@Entity
@Table(name = "ALMACEN_COMPRADOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AlmacenComprador.findAll", query = "SELECT a FROM AlmacenComprador a")
    ,
    @NamedQuery(name = "AlmacenComprador.findByIdAlmacen", query = "SELECT a FROM AlmacenComprador a WHERE a.idAlmacen = :idAlmacen ")
    })

public class AlmacenComprador implements Serializable {

    private static final long serialVersionUID = 1L;
    
    
    @Id
    @Column(name = "id_almacen")
    private Integer idAlmacen;
    
    @Column(name = "id_uen")
    private Integer idUen;

    @Column(name = "id_usuario")
    private String idUsuario;

    

    public AlmacenComprador() {
    }
    
    

    public Integer getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(Integer idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public Integer getIdUen() {
        return idUen;
    }

    public void setIdUen(Integer idUen) {
        this.idUen = idUen;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }


    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAlmacen != null ? idAlmacen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AlmacenComprador)) {
            return false;
        }
        AlmacenComprador other = (AlmacenComprador) object;
        if ((this.idAlmacen == null && other.idAlmacen != null) || (this.idAlmacen != null && !this.idAlmacen.equals(other.idAlmacen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.AlmacenComprador[ idAlmacen=" + idAlmacen + ", idUsuario=" + idUsuario + " ]";
    }



}
