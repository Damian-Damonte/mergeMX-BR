package com.metalsa.requisicion.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author juliocisneros
 */
@Embeddable
public class NvcVmOaExistenciasPK implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_UEN", nullable = false)
    @JsonIgnore
    private Integer idUen;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ALMACEN", nullable = false)
    @JsonIgnore
    private Integer idAlmacen;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PRODUCTO", nullable = false)
    @JsonIgnore
    private Integer idProducto;

    public NvcVmOaExistenciasPK() {
    }

    public NvcVmOaExistenciasPK(Integer idUen, Integer idAlmacen, Integer idProducto) {
        this.idUen = idUen;
        this.idAlmacen = idAlmacen;
        this.idProducto = idProducto;
    }

    @JsonIgnore
    public Integer getIdUen() {
        return idUen;
    }

    public void setIdUen(Integer idUen) {
        this.idUen = idUen;
    }

    @JsonIgnore
    public Integer getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(Integer idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    @JsonIgnore
    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUen != null ? idUen.hashCode() : 0);
        hash += (int) idAlmacen;
        hash += (idProducto != null ? idProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcVmOaExistenciasPK)) {
            return false;
        }
        NvcVmOaExistenciasPK other = (NvcVmOaExistenciasPK) object;
        if ((this.idUen == null && other.idUen != null) || (this.idUen != null && !this.idUen.equals(other.idUen))) {
            return false;
        }
        if (this.idAlmacen != other.idAlmacen) {
            return false;
        }
        if ((this.idProducto == null && other.idProducto != null) || (this.idProducto != null && !this.idProducto.equals(other.idProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.NvcVmOaExistenciasPK[ idUen=" + idUen + ", idAlmacen=" + idAlmacen + ", idProducto=" + idProducto + " ]";
    }

}
