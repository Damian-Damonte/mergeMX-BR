package com.metalsa.requisicion.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author APOMS7376
 */
@Entity
@Table(name = "nvc_tbl_razon_fad")
@NamedQueries({
    @NamedQuery(name = "NvcTblRazonFad.findByIdRazonFad", query = "Select n from NvcTblRazonFad n where n.idRazonFad = :idRazonFad "),
    @NamedQuery(name = "NvcTblRazonFad.findByIdioma", query = "Select n from NvcTblRazonFad n where n.idIdioma = :idIdioma "),
    @NamedQuery(name = "NvcTblRazonFad.findByIdRazonIdioma", query = "Select n from NvcTblRazonFad n where n.idRazonFad = :idRazonFad and n.idIdioma = :idIdioma ")
})
public class NvcTblRazonFad implements Serializable {

    @Id
    @Column(name = "id_razon_fad")
    private Integer idRazonFad;

    public Integer getIdRazonFad() {
        return idRazonFad;
    }

    public void setIdRazonFad(Integer idRazonFad) {
        this.idRazonFad = idRazonFad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(String idIdioma) {
        this.idIdioma = idIdioma;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "id_idioma")
    private String idIdioma;
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

}
