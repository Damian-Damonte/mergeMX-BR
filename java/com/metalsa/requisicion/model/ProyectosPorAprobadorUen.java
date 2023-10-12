package com.metalsa.requisicion.model;

import java.io.Serializable;
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
@Table(name = "PROYECTOS_POR_APROBADOR_UEN")
@NamedQueries({
    @NamedQuery(name = "ProyectosPorAprobadorUen.ParaAprobacionProyecto",
            query = "SELECT n FROM ProyectosPorAprobadorUen n WHERE n.idUen = :idUen and n.idProyecto = :idProyecto ")})

public class ProyectosPorAprobadorUen implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    private Integer id;
    @Column(name = "ID_UEN")
    private Integer idUen;
    @Column(name = "ID_PROYECTO")
    private Integer idProyecto;
    @Column(name = "ID_USUARIO")
    private String idUsuario;
    @Column(name = "COD_PROYECTO")
    private String codProyecto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUen() {
        return idUen;
    }

    public void setIdUen(Integer idUen) {
        this.idUen = idUen;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCodProyecto() {
        return codProyecto;
    }

    public void setCodProyecto(String codProyecto) {
        this.codProyecto = codProyecto;
    }

}
