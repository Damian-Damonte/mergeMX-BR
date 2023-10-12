package com.metalsa.requisicion.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author APOPH5586
 */
@Entity
@Table(name = "NVC_TBL_CAT_UNIDADES_TIEMPO", catalog = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NvcTblCatUnidadesTiempo.findAll", query = "SELECT n FROM NvcTblCatUnidadesTiempo n")
    ,
    @NamedQuery(name = "NvcTblCatUnidadesTiempo.findByIdUnidadTiempo", query = "SELECT n FROM NvcTblCatUnidadesTiempo n WHERE n.idUnidadTiempo = :idUnidadTiempo")
    ,
    @NamedQuery(name = "NvcTblCatUnidadesTiempo.findByIdIdioma", query = "SELECT n FROM NvcTblCatUnidadesTiempo n WHERE n.idIdioma.scId = ?1")
    ,
    @NamedQuery(name = "NvcTblCatUnidadesTiempo.findByDescripcion", query = "SELECT n FROM NvcTblCatUnidadesTiempo n WHERE n.descripcion = ?1")
    ,
    @NamedQuery(name = "NvcTblCatUnidadesTiempo.findByBloque", query = "SELECT n FROM NvcTblCatUnidadesTiempo n WHERE n.bloque = ?1")})
public class NvcTblCatUnidadesTiempo implements Serializable/*, ComprasObject*/ {

    @JoinColumn(name = "ID_IDIOMA", referencedColumnName = "SC_ID")
    @ManyToOne(optional = false)
    private DcIdioma idIdioma;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_UNIDAD_TIEMPO", nullable = false)
    private Integer idUnidadTiempo;
    @Size(max = 100)
    @Column(name = "DESCRIPCION", length = 100)
    private String descripcion;
    @Column(name = "BLOQUE")
    private Integer bloque;

    public NvcTblCatUnidadesTiempo() {
    }

    public NvcTblCatUnidadesTiempo(Integer idUnidadTiempo) {
        this.idUnidadTiempo = idUnidadTiempo;
    }

    public NvcTblCatUnidadesTiempo(Integer idUnidadTiempo, DcIdioma idIdioma) {
        this.idUnidadTiempo = idUnidadTiempo;
        this.idIdioma = idIdioma;
    }

    public Integer getIdUnidadTiempo() {
        return idUnidadTiempo;
    }

    public void setIdUnidadTiempo(Integer idUnidadTiempo) {
        this.idUnidadTiempo = idUnidadTiempo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getBloque() {
        return bloque;
    }

    public void setBloque(Integer bloque) {
        this.bloque = bloque;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUnidadTiempo != null ? idUnidadTiempo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcTblCatUnidadesTiempo)) {
            return false;
        }
        NvcTblCatUnidadesTiempo other = (NvcTblCatUnidadesTiempo) object;
        if ((this.idUnidadTiempo == null && other.idUnidadTiempo != null) || (this.idUnidadTiempo != null && !this.idUnidadTiempo.equals(other.idUnidadTiempo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.admin.NvcTblCatUnidadesTiempo[ idUnidadTiempo=" + idUnidadTiempo + " ]";
    }

    public DcIdioma getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(DcIdioma idIdioma) {
        this.idIdioma = idIdioma;
    }

    // @Override
    public String objectToString() {
        StringBuilder toString = new StringBuilder();
        toString.append(this.toString());
        toString.append("\n");
        toString.append("idUnidadTiempo: ");
        toString.append(idUnidadTiempo);
        toString.append("\n");
        toString.append("idIdioma: ");
        toString.append((null != idIdioma) ? idIdioma.getScId() : "NULO");
        toString.append("\n");
        toString.append("descripcion: ");
        toString.append(descripcion);
        toString.append("\n");
        toString.append("bloque: ");
        toString.append(bloque);
        return toString.toString();
    }

}
