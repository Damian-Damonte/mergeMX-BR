package com.metalsa.catalogo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author APOMS7376
 */
@Entity
@Table(name = "NVC_TBL_CATALOGO_LOCALIZACION")
@NamedQuery(
        name = "NvcTblCatalogoLocalizacion.findByCatUenLocalizacion",
        query = "select c from NvcTblCatalogoLocalizacion c where c.idCatalogoUen.id = :idCatalogoUen " +
                "and c.idLocalizacion = :idLocalizacion"
)
public class NvcTblCatalogoLocalizacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_catalogo_localizacion")
    private Integer idCatalogoLocalizacion;
    @JoinColumn(name = "ID_CATALOGO_UEN", referencedColumnName = "ID_CATALOGO_UEN")
    @ManyToOne(optional = false)
    private NvcTblCatalogoUen idCatalogoUen;
    @Column(name = "id_localizacion")
    private Integer idLocalizacion;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_CREACION")
    private Date fechaCreacion;
    @Column(name = "FECHA_ACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @Column(name = "USUARIO_CREACION")
    private String usuarioCreacion;
    @Column(name = "USUARIO_ACTUALIZACION")
    private String usuarioActualizacion;
    
    @JoinColumn(
            name = "ID_CATALOGO_LOCALIZACION",
            referencedColumnName = "ID_CATALOGO_LOCALIZACION"
    )
    @OneToMany()
    private List<NvcTblCatalogoUenSite> sites;

    public Integer getIdCatalogoLocalizacion() {
        return idCatalogoLocalizacion;
    }

    public void setIdCatalogoLocalizacion(Integer idCatalogoLocalizacion) {
        this.idCatalogoLocalizacion = idCatalogoLocalizacion;
    }

    public NvcTblCatalogoUen getIdCatalogoUen() {
        return idCatalogoUen;
    }

    public void setIdCatalogoUen(NvcTblCatalogoUen idCatalogoUen) {
        this.idCatalogoUen = idCatalogoUen;
    }

    public Integer getIdLocalizacion() {
        return idLocalizacion;
    }

    public void setIdLocalizacion(Integer idLocalizacion) {
        this.idLocalizacion = idLocalizacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public String getUsuarioActualizacion() {
        return usuarioActualizacion;
    }

    public void setUsuarioActualizacion(String usuarioActualizacion) {
        this.usuarioActualizacion = usuarioActualizacion;
    }

    public List<NvcTblCatalogoUenSite> getSites() {
        return this.sites;
    }

    @Override
    public String toString() {
        return "NvcTblCatalogoLocalizacion{" +
                "idCatalogoLocalizacion=" + idCatalogoLocalizacion +
                ", idCatalogoUen=" + idCatalogoUen +
                ", idLocalizacion=" + idLocalizacion +
                ", fechaCreacion=" + fechaCreacion +
                ", fechaActualizacion=" + fechaActualizacion +
                ", usuarioCreacion='" + usuarioCreacion + '\'' +
                ", usuarioActualizacion='" + usuarioActualizacion + '\'' +
                ", sites=" + sites +
                '}';
    }
}
