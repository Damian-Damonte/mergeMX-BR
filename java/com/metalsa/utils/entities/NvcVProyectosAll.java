/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.utils.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author APODR7218
 */
@Entity
@Table(name = "NVC_V_PROYECTOS_ALL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NvcVProyectosAll.findAll", query = "SELECT n FROM NvcVProyectosAll n")
    ,
    @NamedQuery(name = "NvcVProyectosAll.findByUserUen", query = "SELECT distinct NEW com.metalsa.utils.entities.NvcVProyectosAll(n.idProyecto, n.codProyecto, n.proyecto, n.idUen) FROM NvcVProyectosAll n WHERE (:idUsuario = '-' OR n.idUsuario = :idUsuario) AND n.idUen = :idUen order by n.codProyecto")
    ,
})
public class NvcVProyectosAll implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PROYECTO")
    private Integer idProyecto;
    @Column(name = "COD_PROYECTO")
    private String codProyecto;
    @Column(name = "PROYECTO")
    private String proyecto;
    @Column(name = "ID_USUARIO")
    private String idUsuario;
    @Column(name = "ID_UEN")
    private Integer idUen;
    
    public NvcVProyectosAll(){
        
    }
    public NvcVProyectosAll(Integer idProyecto, String codProyecto, String proyecto, Integer idUen) {
        this.idProyecto = idProyecto;
        this.codProyecto = codProyecto;
        this.proyecto = proyecto;
        this.idUen = idUen;
    }

    /**
     * @return the idProyecto
     */
    public Integer getIdProyecto() {
        return idProyecto;
    }

    /**
     * @param idProyecto the idProyecto to set
     */
    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    /**
     * @return the codProyecto
     */
    public String getCodProyecto() {
        return codProyecto;
    }

    /**
     * @param codProyecto the codProyecto to set
     */
    public void setCodProyecto(String codProyecto) {
        this.codProyecto = codProyecto;
    }

    /**
     * @return the proyecto
     */
    public String getProyecto() {
        return proyecto;
    }

    /**
     * @param proyecto the proyecto to set
     */
    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    /**
     * @return the idUsuario
     */
    public String getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the idUen
     */
    public Integer getIdUen() {
        return idUen;
    }

    /**
     * @param idUen the idUen to set
     */
    public void setIdUen(Integer idUen) {
        this.idUen = idUen;
    }
}
