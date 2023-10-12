package com.metalsa.requisicion.model;

import com.metalsa.requisicion.utils.DateAdapter;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author APODR7218
 */
@Entity
@Table(name = "NVC_TBL_MOTIVO_REGRESO_ITEM")
@NamedQueries({
    @NamedQuery(name = "NvcTblMotivoRegresoItem.findAll", query = "SELECT n FROM NvcTblMotivoRegresoItem n")
    ,
    @NamedQuery(name = "NvcTblMotivoRegresoItem.findByIdItem", query = "SELECT n FROM NvcTblMotivoRegresoItem n WHERE n.idItem = :idItem ORDER BY n.fechaCreacion ASC")
})
public class NvcTblMotivoRegresoItem implements Serializable {

    private static long serialVersionUID = 1L;

    @Id
    @Column(name = "ID_MOTIVO_REGRESO")
    @SequenceGenerator(name = "NVC_TBL_MOTIVO_REGRESO_SEQ_GEN", sequenceName = "NVC_TBL_MOTIVO_REGRESO_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "NVC_TBL_MOTIVO_REGRESO_SEQ_GEN")
    private Integer idMotivoRegreso;

    @Column(name = "ID_ITEM")
    private Integer idItem;
    @Column(name = "ID_USUARIO")
    private String idUsuario;
    @Column(name = "MOTIVO_REGRESO")
    private String motivoRegreso;
    @XmlJavaTypeAdapter(DateAdapter.class)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_CREACION")
    private Date fechaCreacion;
    @Column(name = "USUARIO_CREACION")
    private String usuarioCreacion;
    @XmlJavaTypeAdapter(DateAdapter.class)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_ACTUALIZACION")
    private Date fechaActualizacion;
    @Column(name = "USUARIO_MODIFICACION")
    private String usuarioModificacion;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getIdMotivoRegreso() != null ? getIdMotivoRegreso().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcTblMotivoRegresoItem)) {
            return false;
        }
        NvcTblMotivoRegresoItem other = (NvcTblMotivoRegresoItem) object;
        if ((this.getIdMotivoRegreso() == null && other.getIdMotivoRegreso() != null) || (this.getIdMotivoRegreso() != null && !this.idMotivoRegreso.equals(other.idMotivoRegreso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.spx.entities.NvcTblMotivoRegresoItem[ idMotivoRegreso=" + getIdMotivoRegreso() + " ]";
    }

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }

    public Integer getIdMotivoRegreso() {
        return idMotivoRegreso;
    }

    public void setIdMotivoRegreso(Integer idMotivoRegreso) {
        this.idMotivoRegreso = idMotivoRegreso;
    }

    /**
     * @return the idItem
     */
    public Integer getIdItem() {
        return idItem;
    }

    /**
     * @param idItem the idItem to set
     */
    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
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
     * @return the motivoRegreso
     */
    public String getMotivoRegreso() {
        return motivoRegreso;
    }

    /**
     * @param motivoRegreso the motivoRegreso to set
     */
    public void setMotivoRegreso(String motivoRegreso) {
        this.motivoRegreso = motivoRegreso;
    }

//    /**
//     * @return the fechaCreacion
//     */
//    public Date getFechaCreacion() {
//        return fechaCreacion;
//    }
    /**
     * @param fechaCreacion the fechaCreacion to set
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * @return the usuarioCreacion
     */
    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    /**
     * @param usuarioCreacion the usuarioCreacion to set
     */
    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

//    /**
//     * @return the fechaActualizacion
//     */
//    public Date getFechaActualizacion() {
//        return fechaActualizacion;
//    }
    /**
     * @param fechaActualizacion the fechaActualizacion to set
     */
    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    /**
     * @return the usuarioModificacion
     */
    public String getUsuarioModificacion() {
        return usuarioModificacion;
    }

    /**
     * @param usuarioModificacion the usuarioModificacion to set
     */
    public void setUsuarioModificacion(String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

}
