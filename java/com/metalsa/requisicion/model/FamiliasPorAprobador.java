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
@Table(name = "FAMILIAS_POR_APROBADOR")
@NamedQueries({
    @NamedQuery(name = "FamiliasPorAprobador.findByIdFamilia", query = "SELECT a FROM FamiliasPorAprobador a Where "
            + "a.idFamilia = :idFamilia and a.idUen = :idUen and a.tipoAprobacion = :tipoAprobacion ")
})
public class FamiliasPorAprobador implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "ID_UEN")
    private Integer idUen;
    @Id
    @Column(name = "ID_FAMILIA")
    private Integer idFamilia;
    @Column(name = "ID_USUARIO")
    private String idUsuario;
    @Column(name = "TIPO_APROBACION")
    private String tipoAprobacion;

    public Integer getIdUen() {
        return idUen;
    }

    public void setIdUen(Integer idUen) {
        this.idUen = idUen;
    }

    public Integer getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(Integer idFamilia) {
        this.idFamilia = idFamilia;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public String getTipoAprobacion() {
        return tipoAprobacion;
    }

    public void setTipoAprobacion(String tipoAprobacion) {
        this.tipoAprobacion = tipoAprobacion;
    }
}
