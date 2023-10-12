/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.utils.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author APOJA5585
 */
@Embeddable
public class NvcVmOaProyectoTareasPK implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TAREA")
    private long idTarea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PROYECTO")
    private long idProyecto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_UEN")
    private long idUen;

    public NvcVmOaProyectoTareasPK() {
    }

    public NvcVmOaProyectoTareasPK(long idTarea, long idProyecto, long idUen) {
        this.idTarea = idTarea;
        this.idProyecto = idProyecto;
        this.idUen = idUen;
    }

    public long getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(long idTarea) {
        this.idTarea = idTarea;
    }

    public long getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(long idProyecto) {
        this.idProyecto = idProyecto;
    }

    public long getIdUen() {
        return idUen;
    }

    public void setIdUen(long idUen) {
        this.idUen = idUen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idTarea;
        hash += (int) idProyecto;
        hash += (int) idUen;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcVmOaProyectoTareasPK)) {
            return false;
        }
        NvcVmOaProyectoTareasPK other = (NvcVmOaProyectoTareasPK) object;
        if (this.idTarea != other.idTarea) {
            return false;
        }
        if (this.idProyecto != other.idProyecto) {
            return false;
        }
        if (this.idUen != other.idUen) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.suite.NvcVmOaProyectoTareasPK[ idTarea=" + idTarea + ", idProyecto=" + idProyecto + ", idUen=" + idUen + " ]";
    }
    
}
