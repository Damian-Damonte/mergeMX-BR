package com.metalsa.requisicion.model;

import com.metalsa.portalProveedor.model.NvcTblRfqProv;
import com.metalsa.requisicion.pojo.ComprasObject;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author APOJA5585
 */
@Entity
@Table(name = "NVC_TBL_PROV_TEMPORAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NvcTblProvTemporal.findAll", query = "SELECT n FROM NvcTblProvTemporal n")
    ,
    @NamedQuery(name = "NvcTblProvTemporal.findByIdProveedor", query = "SELECT n FROM NvcTblProvTemporal n WHERE n.idProveedor = ?1")
    ,
    @NamedQuery(name = "NvcTblProvTemporal.findByNombre", query = "SELECT n FROM NvcTblProvTemporal n WHERE n.nombre = :nombre")
    ,
    @NamedQuery(name = "NvcTblProvTemporal.findByContacto", query = "SELECT n FROM NvcTblProvTemporal n WHERE n.contacto = :contacto")
    ,
    @NamedQuery(name = "NvcTblProvTemporal.findByEmail", query = "SELECT n FROM NvcTblProvTemporal n WHERE n.email = :email")
    ,
    @NamedQuery(name = "NvcTblProvTemporal.findByIdByNombre", query = "SELECT n FROM NvcTblProvTemporal n WHERE n.idProveedor = ?1 AND n.nombre = ?2")
})
public class NvcTblProvTemporal implements Serializable, ComprasObject {

    @OneToMany(mappedBy = "idProvExterno", fetch = FetchType.LAZY)
    private List<NvcTblRfqProv> nvcTblRfqProvList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "proveedorTempSeq")
    @SequenceGenerator(name = "proveedorTempSeq", sequenceName = "SEQ_PROVEEDOR_TEMP", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PROVEEDOR")
    private Integer idProveedor;
    @Size(max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 100)
    @Column(name = "CONTACTO")
    private String contacto;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 450)
    @Column(name = "EMAIL_ADICIONAL")
    private String emailAdicional;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProvExt")
    private Collection<NvcTblMensajeProvExt> nvcTblMensajeProvExtCollection;

    public NvcTblProvTemporal() {
    }

    public NvcTblProvTemporal(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public NvcTblProvTemporal(Integer idProveedor, String email) {
        this.idProveedor = idProveedor;
        this.email = email;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public Collection<NvcTblMensajeProvExt> getNvcTblMensajeProvExtCollection() {
        return nvcTblMensajeProvExtCollection;
    }

    public void setNvcTblMensajeProvExtCollection(Collection<NvcTblMensajeProvExt> nvcTblMensajeProvExtCollection) {
        this.nvcTblMensajeProvExtCollection = nvcTblMensajeProvExtCollection;
    }

    public String getEmailAdicional() {
        return emailAdicional;
    }

    public void setEmailAdicional(String emailAdicional) {
        this.emailAdicional = emailAdicional;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProveedor != null ? idProveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NvcTblProvTemporal)) {
            return false;
        }
        //<M26022018>
        NvcTblProvTemporal other = (NvcTblProvTemporal) object;
        return java.util.Objects.equals(other.idProveedor, this.idProveedor);
        //</M26022018>
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.NvcTblProvTemporal[ idProveedor=" + idProveedor + " ]";
    }

    @Override
    public String objectToString() {
        StringBuilder toString = new StringBuilder();
        toString.append(this.toString());
        toString.append("\n");
        toString.append("idProveedor: ");
        toString.append(idProveedor);
        toString.append("\n");
        toString.append("nombre: ");
        toString.append(nombre);
        toString.append("\n");
        toString.append("contacto: ");
        toString.append(contacto);
        toString.append("\n");
        toString.append("email: ");
        toString.append(email);
        return toString.toString();
    }

    @XmlTransient
    public List<NvcTblRfqProv> getNvcTblRfqProvList() {
        return nvcTblRfqProvList;
    }

    public void setNvcTblRfqProvList(List<NvcTblRfqProv> nvcTblRfqProvList) {
        this.nvcTblRfqProvList = nvcTblRfqProvList;
    }

}
