/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author edgar.leal
 */
@Entity
@Table(name = "CHAT_COMPRADOR_VIEW")
@IdClass(ChatcompradorId.class)
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chatcomprador.findAll", query = "SELECT c FROM Chatcomprador c")
    , @NamedQuery(name = "Chatcomprador.findByFechaCreacion", query = "SELECT c FROM Chatcomprador c WHERE c.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Chatcomprador.findByFechaCreacionsa", query = "SELECT c FROM Chatcomprador c WHERE c.fechaCreacionsa = :fechaCreacionsa")
    , @NamedQuery(name = "Chatcomprador.findByTotal", query = "SELECT c FROM Chatcomprador c WHERE c.total = :total")
    , @NamedQuery(name = "Chatcomprador.findByIdRol", query = "SELECT c FROM Chatcomprador c WHERE c.idRol = :idRol")
    , @NamedQuery(name = "Chatcomprador.findByIntera", query = "SELECT c FROM Chatcomprador c WHERE c.intera = :intera")
    , @NamedQuery(name = "Chatcomprador.findByMensaje", query = "SELECT c FROM Chatcomprador c WHERE c.mensaje = :mensaje")
    , @NamedQuery(name = "Chatcomprador.findByNombreUsuario", query = "SELECT c FROM Chatcomprador c WHERE c.nombreUsuario = :nombreUsuario")
    , @NamedQuery(name = "Chatcomprador.findByLeido", query = "SELECT c FROM Chatcomprador c WHERE c.leido = :leido")
    , @NamedQuery(name = "Chatcomprador.findByIdRequisicion", query = "SELECT c FROM Chatcomprador c WHERE c.idRequisicion = ?1")})
public class Chatcomprador implements Serializable {

    private static final long serialVersionUID = 1L;
    
    
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Size(max = 10)
    @Column(name = "FECHA_CREACIONSA")
    private String fechaCreacionsa;
    @Size(max = 17)
    @Column(name = "TOTAL")
    private String total;
    @Column(name = "ID_ROL")
    private Long idRol;
    @Id
    @Size(max = 11)
    @Column(name = "INTERA")
    private String intera;
    @Id 
    @Size(max = 3000)
    @Column(name = "MENSAJE")
    private String mensaje;
    @Size(max = 80)
    @Column(name = "NOMBRE_USUARIO")
    private String nombreUsuario;
    @Column(name = "LEIDO")
    private Character leido;
    @Id
    @Column(name = "ID_REQUISICION")
    private Integer idRequisicion;
    
    

    public Chatcomprador() {
      
    }
    
    //https://www.securecoding.cert.org/confluence/display/java/OBJ04-J.+Provide+mutable+classes+with+copy+functionality+to+safely+allow+passing+instances+to+untrusted+code
    //AAB
    
    public Chatcomprador(Chatcomprador mc, String fechaCreacionsa, String total, 
            Long idRol, String intera, String mensaje, String nombreUsuario,
            Character leido, Integer idRequisicion){
        this.fechaCreacion = new Date(mc.fechaCreacion.getTime());
        this.fechaCreacionsa = fechaCreacionsa;
        this.total = total;
        this.idRol = idRol;
        this.intera = intera;
        this.mensaje = mensaje;
        this.nombreUsuario = nombreUsuario;
        this.leido = leido;
        this.idRequisicion = idRequisicion;
    }

    public Chatcomprador(Date d, String fechaCreacionsa, String total, 
            Long idRol, String intera, String mensaje, String nombreUsuario,
            Character leido, Integer idRequisicion){
        this.fechaCreacion = new Date(d.getTime()); // Make defensive copy
        this.fechaCreacionsa = fechaCreacionsa;
        this.total = total;
        this.idRol = idRol;
        this.intera = intera;
        this.mensaje = mensaje;
        this.nombreUsuario = nombreUsuario;
        this.leido = leido;
        this.idRequisicion = idRequisicion;
    }
    
    public Date getFechaCreacion() {
        return (Date) fechaCreacion.clone(); //Copy and return
    }

//    public void setFechaCreacion(Date fechaCreacion) {
//        this.fechaCreacion = fechaCreacion;
//    }

    public String getFechaCreacionsa() {
        return fechaCreacionsa;
    }

    public void setFechaCreacionsa(String fechaCreacionsa) {
        this.fechaCreacionsa = fechaCreacionsa;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    public String getIntera() {
        return intera;
    }

    public void setIntera(String intera) {
        this.intera = intera;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Character getLeido() {
        return leido;
    }

    public void setLeido(Character leido) {
        this.leido = leido;
    }

    public Integer getIdRequisicion() {
        return idRequisicion;
    }

    public void setIdRequisicion(Integer idRequisicion) {
        this.idRequisicion = idRequisicion;
    }
    
}