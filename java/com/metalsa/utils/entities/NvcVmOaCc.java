/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.utils.entities;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jose.rivera02
 */
@Entity
@Table(name = "NVC_VM_OA_CC")
@NamedQueries({
    @NamedQuery(name = "NvcVmOaCc.findAll", query = "SELECT n FROM NvcVmOaCc n")
    ,    
    @NamedQuery(name = "NvcVmOaCc.findByIdUenLangAcc",
            query = "SELECT n "
            + "FROM NvcVmOaCc n "
            + "WHERE n.accesoRestringido = 'N' "
            + "AND n.lenguaje = :lenguaje "
            + "AND n.nvcVmOaCcKey.idUen = :idUen "
            + "ORDER BY n.codigoCc asc ")
    ,    
    @NamedQuery(name = "NvcVmOaCc.findByIdUen", query = "SELECT n FROM NvcVmOaCc n WHERE n.nvcVmOaCcKey.idUen = :idUen ORDER BY n.codigoCc")
    ,
    
    
})
public class NvcVmOaCc implements Serializable {    

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    private NvcVmOaCcKey nvcVmOaCcKey;        

    @Size(max = 450)
    @Column(name = "CODIGO_CC", length = 450)
    private String codigoCc;
    @Size(max = 720)
    @Column(name = "NOMBRE_CC", length = 720)
    private String nombreCc;
    @Column(name = "ACCESO_RESTRINGIDO")
    private Character accesoRestringido;
    @Size(max = 12)
    @Column(name = "LENGUAJE", length = 12)
    private String lenguaje;

    @Transient
    private String nombreIdioma;

    public NvcVmOaCc() {
    }
   
        

    public String getCodigoCc() {
        return codigoCc;
    }

    public void setCodigoCc(String codigoCc) {
        this.codigoCc = codigoCc;
    }

    public String getNombreCc() {
        return nombreCc;
    }

    public void setNombreCc(String nombreCc) {
        this.nombreCc = nombreCc;
    }

    public Character getAccesoRestringido() {
        return accesoRestringido;
    }

    public void setAccesoRestringido(Character accesoRestringido) {
        this.accesoRestringido = accesoRestringido;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public void setNombreIdioma(String nombreIdioma) {
        this.nombreIdioma = nombreIdioma;
    }
       
    public NvcVmOaCcKey getNvcVmOaCcKey(){
        return nvcVmOaCcKey;
    }

    public BigInteger getIdUen(){
        return nvcVmOaCcKey.getIdUen();
    }

    public BigInteger getIdCc(){
        return nvcVmOaCcKey.getIdCc();
    }
}
