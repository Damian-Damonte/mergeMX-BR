//package com.metalsa.correos.model;
//
//import java.io.Serializable;
//import javax.persistence.Basic;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Lob;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
//import javax.persistence.Table;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//
///**
// *
// * @author APOMR10051
// */
//@Entity
//@Table(name = "NVC_TBL_CORREO", catalog = "")
//@NamedQueries({
//    @NamedQuery(name = "NvcTblCorreo.findAll", query = "SELECT n FROM NvcTblCorreo n")
//    ,
//    @NamedQuery(name = "NvcTblCorreo.findByIdTipoCorreo", query = "SELECT n FROM NvcTblCorreo n WHERE n.idTipoCorreo = :idTipoCorreo")
//})
//public class NvcTblCorreo implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//    @Id
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "ID_CORREO")
//    private Integer idCorreo;
//    @Size(max = 100)
//    @Column(name = "ASUNTO")
//    private String asunto;
//    @Lob
//    @Column(name = "CUERPO")
//    private String cuerpo;
//    @Size(max = 5)
//    @Column(name = "IDIOMA")
//    private String idioma;
//    @NotNull
//    @Column(name = "ID_TIPO_CORREO")
//    private Integer idTipoCorreo;
//
//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (getIdCorreo() != null ? getIdCorreo().hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        if (!(object instanceof NvcTblCorreo)) {
//            return false;
//        }
//        NvcTblCorreo other = (NvcTblCorreo) object;
//        if ((this.getIdCorreo() == null && other.getIdCorreo() != null) || (this.getIdCorreo() != null && !this.idCorreo.equals(other.idCorreo))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "com.metalsa.correos.model.NvcTblCorreo[ idCorreo=" + getIdCorreo() + " ]";
//    }
//
//    public Integer getIdCorreo() {
//        return idCorreo;
//    }
//
//    public void setIdCorreo(Integer idCorreo) {
//        this.idCorreo = idCorreo;
//    }
//
//    public String getAsunto() {
//        return asunto;
//    }
//
//    public void setAsunto(String asunto) {
//        this.asunto = asunto;
//    }
//
//    public String getCuerpo() {
//        return cuerpo;
//    }
//
//    public void setCuerpo(String cuerpo) {
//        this.cuerpo = cuerpo;
//    }
//
//    public String getIdioma() {
//        return idioma;
//    }
//
//    public void setIdioma(String idioma) {
//        this.idioma = idioma;
//    }
//
//    public Integer getIdTipoCorreo() {
//        return idTipoCorreo;
//    }
//
//    public void setIdTipoCorreo(Integer idTipoCorreo) {
//        this.idTipoCorreo = idTipoCorreo;
//    }
//}
