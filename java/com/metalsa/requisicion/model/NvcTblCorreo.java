/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.requisicion.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NamedStoredProcedureQuery;
import static javax.persistence.ParameterMode.IN;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author APOJA5585
 */
@Entity
@Table(name = "NVC_TBL_CORREO", catalog = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NvcTblCorreo.findAll", query = "SELECT n FROM NvcTblCorreo n")
    ,
    @NamedQuery(name = "NvcTblCorreo.findByIdCorreo", query = "SELECT n FROM NvcTblCorreo n WHERE n.idCorreo = :idCorreo")
    ,
    @NamedQuery(name = "NvcTblCorreo.findByAsunto", query = "SELECT n FROM NvcTblCorreo n WHERE n.asunto = :asunto")
    ,
    @NamedQuery(name = "NvcTblCorreo.findByIdTipoCorreo", query = "SELECT n FROM NvcTblCorreo n WHERE n.idTipoCorreo = :idTipoCorreo")
    ,
    @NamedQuery(name = "NvcTblCorreo.findByIdTipoByIdioma", query = "SELECT n FROM NvcTblCorreo n INNER JOIN n.idTipoCorreo t "
            + "INNER JOIN n.idioma i WHERE t.idTipoCorreo = ?1 AND i.scId = ?2")
})

@NamedStoredProcedureQuery(name = "NvcTblCorreo.enviarCorreo",
        resultClasses = NvcTblRfq.class,
        procedureName = "NVC_NOTIFICACIONES.ENVIAR_CORREO",
        parameters = {
            @StoredProcedureParameter(mode = IN, name = "p_correo", type = String.class)
            ,
        @StoredProcedureParameter(mode = IN, name = "p_asunto", type = String.class)
            ,
        @StoredProcedureParameter(mode = IN, name = "p_destinatario", type = String.class)
        }
)
public class NvcTblCorreo implements Serializable {

    @JoinColumn(name = "ID_TIPO_CORREO", referencedColumnName = "ID_TIPO_CORREO")
    @ManyToOne(optional = false)
    private NvcTblTipoCorreo idTipoCorreo;
    private static final long serialVersionUID = 1L;
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
    @JoinColumn(name = "IDIOMA", referencedColumnName = "SC_ID")
    @ManyToOne(optional = false)
    private DcIdioma idioma;

    public NvcTblCorreo() {
    }

    public NvcTblCorreo(Integer idCorreo) {
        this.idCorreo = idCorreo;
    }

    public NvcTblCorreo(Integer idCorreo, NvcTblTipoCorreo idTipoCorreo) {
        this.idCorreo = idCorreo;
        this.idTipoCorreo = idTipoCorreo;
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

    public NvcTblTipoCorreo getIdTipoCorreo() {
        return idTipoCorreo;
    }

    public void setIdTipoCorreo(NvcTblTipoCorreo idTipoCorreo) {
        this.idTipoCorreo = idTipoCorreo;
    }

    public DcIdioma getIdioma() {
        return idioma;
    }

    public void setIdioma(DcIdioma idioma) {
        this.idioma = idioma;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCorreo != null ? idCorreo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcTblCorreo)) {
            return false;
        }
        NvcTblCorreo other = (NvcTblCorreo) object;
        if ((this.idCorreo == null && other.idCorreo != null) || (this.idCorreo != null && !this.idCorreo.equals(other.idCorreo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.cotizaciones.NvcTblCorreo[ idCorreo=" + idCorreo + " ]";
    }
}
