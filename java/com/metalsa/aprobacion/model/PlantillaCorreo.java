package com.metalsa.aprobacion.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Plantillas para correos
 */
@Entity(name = "nvc_tbl_correo")
@Data
@NamedNativeQueries({
    @NamedNativeQuery(
            name = "PlantillaCorreo.getByDescripcionAndIdioma",
            query = "select p.* "
            + "from nvc_tbl_correo p "
            + "   join nvc_tbl_tipo_correo t on t.id_tipo_correo = p.id_tipo_correo "
            + "where p.IDIOMA = :i "
            + "   and t.descripcion = :d",
            resultClass = PlantillaCorreo.class
    )
    ,
        @NamedNativeQuery(
            name = "PlantillaCorreo.getByDescripcion",
            query = "select p.* "
            + "from nvc_tbl_correo p "
            + "   join nvc_tbl_tipo_correo t on t.id_tipo_correo = p.id_tipo_correo "
            + "where t.descripcion = :d",
            resultClass = PlantillaCorreo.class
    )
    ,
        @NamedNativeQuery(
            name = "PlantillaCorreo.getByIdTipoCorreo",
            query = "select p.* "
            + "from nvc_tbl_correo p "
            + "where p.id_tipo_correo = :idTipoCorreo order by p.id_correo",
            resultClass = PlantillaCorreo.class
    )
})
public class PlantillaCorreo implements Serializable {

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CORREO")
    private Integer idCorreo;
    @Size(max = 100)
    @Column(name = "ASUNTO")
    private String asunto;
    @Lob
    @Column(name = "CUERPO")
    private String cuerpo;
    @Size(max = 5)
    @Column(name = "IDIOMA")
    private String idioma;
    @NotNull
    @Column(name = "ID_TIPO_CORREO")
    private Integer idTipoCorreo;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getIdCorreo() != null ? getIdCorreo().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PlantillaCorreo)) {
            return false;
        }
        PlantillaCorreo other = (PlantillaCorreo) object;
        if ((this.getIdCorreo() == null && other.getIdCorreo() != null) || (this.getIdCorreo() != null && !this.idCorreo.equals(other.idCorreo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.correos.model.NvcTblCorreo[ idCorreo=" + getIdCorreo() + " ]";
    }

    public Integer getIdCorreo() {
        return idCorreo;
    }

    public void setIdCorreo(Integer idCorreo) {
        this.idCorreo = idCorreo;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getIdTipoCorreo() {
        return idTipoCorreo;
    }

    public void setIdTipoCorreo(Integer idTipoCorreo) {
        this.idTipoCorreo = idTipoCorreo;
    }

}
