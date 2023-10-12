package com.metalsa.requisicion.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author APOMS7376
 */
@Entity
@Table(name = "dc_comprador_familia")
@NamedQueries({
    @NamedQuery(name = "DcCompradorFamilia.findAll", query = "SELECT o FROM DcCompradorFamilia o"),
    @NamedQuery(name = "DcCompradorFamilia.findById", query = "SELECT o FROM DcCompradorFamilia o Where o.idCompradorFamilia = :idCompradorFamilia"),
    @NamedQuery(name = "DcCompradorFamilia.findByIdUsuario", query = "SELECT o FROM DcCompradorFamilia o Where o.idUsuario = :idUsuario"),
    @NamedQuery(name = "DcCompradorFamilia.findByIdUen", query = "SELECT o FROM DcCompradorFamilia o Where o.idUen = :idUen"),
    @NamedQuery(name = "DcCompradorFamilia.findByIdFamilia", query = "SELECT o FROM DcCompradorFamilia o Where o.idFamilia = :idFamilia"),
    @NamedQuery(name = "DcCompradorFamilia.findByUenFamilia", 
            query = "SELECT o FROM DcCompradorFamilia o Where o.idUen = :idUen and o.idFamilia = :idFamilia")
})
public class DcCompradorFamilia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID_COMPRADOR_FAMILIA")
    private Integer idCompradorFamilia;

    @Column(name = "ID_UEN")
    private Integer idUen;
    
    @Column(name = "ID_FAMILIA")
    private Integer idFamilia;

    @Column(name = "ID_USUARIO")
    private String idUsuario;

    @Column(name = "NOMBRE_FAMILIA")
    private String nombreFamilia;

    @Column(name = "NUMERO_FAMILIA")
    private String numeroFamilia;

    public Integer getIdCompradorFamilia() {
        return idCompradorFamilia;
    }

    public void setIdCompradorFamilia(Integer idCompradorFamilia) {
        this.idCompradorFamilia = idCompradorFamilia;
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

    public String getNombreFamilia() {
        return nombreFamilia;
    }

    public void setNombreFamilia(String nombreFamilia) {
        this.nombreFamilia = nombreFamilia;
    }

    public String getNumeroFamilia() {
        return numeroFamilia;
    }

    public void setNumeroFamilia(String numeroFamilia) {
        this.numeroFamilia = numeroFamilia;
    }

    public Integer getIdUen() {
        return idUen;
    }

    public void setIdUen(Integer idUen) {
        this.idUen = idUen;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCompradorFamilia != null ? idCompradorFamilia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DcCompradorFamilia)) {
            return false;
        }
        DcCompradorFamilia other = (DcCompradorFamilia) object;
        if ((this.idCompradorFamilia == null && other.idCompradorFamilia != null)
                || (this.idCompradorFamilia != null && !this.idCompradorFamilia.equals(other.idCompradorFamilia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.DcCompradorFamilia[ idCompradorFamilia=" + idCompradorFamilia + " ]";
    }

}
