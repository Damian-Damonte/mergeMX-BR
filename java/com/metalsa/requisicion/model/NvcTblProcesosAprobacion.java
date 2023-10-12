package com.metalsa.requisicion.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author APOMR10051
 */
@Entity
@Table(name = "NVC_TBL_PROCESOS_APROBACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NvcTblProcesosAprobacion.findAll", query = "SELECT n FROM NvcTblProcesosAprobacion n")
    ,
    @NamedQuery(name = "NvcTblProcesosAprobacion.findByIdProcesoAprobacion", query = "SELECT n FROM NvcTblProcesosAprobacion n WHERE n.idProcesoAprobacion = :idProcesoAprobacion")
    ,
    @NamedQuery(name = "NvcTblProcesosAprobacion.findByDescripcion", query = "SELECT n FROM NvcTblProcesosAprobacion n WHERE n.descripcion = :descripcion")})
public class NvcTblProcesosAprobacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PROCESO_APROBACION")
    private Integer idProcesoAprobacion;
    @Size(max = 250)
    @Column(name = "DESCRIPCION")
    private String descripcion;

    public NvcTblProcesosAprobacion() {
    }

    public NvcTblProcesosAprobacion(Integer idProcesoAprobacion) {
        this.idProcesoAprobacion = idProcesoAprobacion;
    }

    public Integer getIdProcesoAprobacion() {
        return idProcesoAprobacion;
    }

    public void setIdProcesoAprobacion(Integer idProcesoAprobacion) {
        this.idProcesoAprobacion = idProcesoAprobacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
