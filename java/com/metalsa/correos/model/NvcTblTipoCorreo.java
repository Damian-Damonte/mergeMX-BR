package com.metalsa.correos.model;

import com.metalsa.aprobacion.model.PlantillaCorreo;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author APOMR10051
 */
@Entity
@Table(name = "NVC_TBL_TIPO_CORREO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NvcTblTipoCorreo.findAll", query = "SELECT n FROM NvcTblTipoCorreo n")
})
public class NvcTblTipoCorreo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TIPO_CORREO")
    private Integer idTipoCorreo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "DESCRIPCION")
    private String descripcion;
//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "proyectos_por_aprobador_uen",
//            joinColumns = @JoinColumn(name = "id_proyecto"),
//            inverseJoinColumns = @JoinColumn(name = "id_usuario")
//    )
//    List<Preaprobador> preaprobadores = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TIPO_CORREO", referencedColumnName = "ID_TIPO_CORREO", insertable = false, updatable = false)
    private List<PlantillaCorreo> correo;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getIdTipoCorreo() != null ? getIdTipoCorreo().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcTblTipoCorreo)) {
            return false;
        }
        NvcTblTipoCorreo other = (NvcTblTipoCorreo) object;
        if ((this.getIdTipoCorreo() == null && other.getIdTipoCorreo() != null) || (this.getIdTipoCorreo() != null && !this.idTipoCorreo.equals(other.idTipoCorreo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.correos.model.NvcTblTipoCorreo[ idTipoCorreo=" + getIdTipoCorreo() + " ]";
    }

    public Integer getIdTipoCorreo() {
        return idTipoCorreo;
    }

    public void setIdTipoCorreo(Integer idTipoCorreo) {
        this.idTipoCorreo = idTipoCorreo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<PlantillaCorreo> getCorreo() {
        return correo;
    }

    public void setCorreo(List<PlantillaCorreo> correo) {
        this.correo = correo;
    }
}
