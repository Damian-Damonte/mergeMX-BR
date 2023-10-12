/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author edgar.leal
 */
@Entity
@Table(name = "PREGUNTAS_RECIBOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PreguntasRecibos.findAll", query = "SELECT p FROM PreguntasRecibos p")
    , @NamedQuery(name = "PreguntasRecibos.findByPregunta", query = "SELECT p FROM PreguntasRecibos p WHERE p.pregunta = :pregunta")
    , @NamedQuery(name = "PreguntasRecibos.findByIdioma", query = "SELECT p FROM PreguntasRecibos p WHERE p.idioma = ?1")
    , @NamedQuery(name = "PreguntasRecibos.findById", query = "SELECT p FROM PreguntasRecibos p WHERE p.id = :id")})
@IdClass(PreguntasRecibos.class)
public class PreguntasRecibos implements Serializable {

    private static final long serialVersionUID = 1L;
    //@EmbeddedId
    //protected PreguntasRecibosPK preguntasRecibosPK;
    
    @Column(name = "IDIOMA")
    @Id
    private String idioma;
    @Column(name = "ID")
    @Id
    private Integer id;
    
    @Column(name = "PREGUNTA")
    private String pregunta;
    
    @Column(name = "ACTIVA")
    private Integer activa;

    public PreguntasRecibos() {
    }

    public Integer getActiva() {
        return activa;
    }

    public void setActiva(Integer activa) {
        this.activa = activa;
    }
    
    

    public PreguntasRecibos(String idioma, Integer id, String pregunta, Integer activa) {
        this.idioma = idioma;
        this.id = id;
        this.pregunta = pregunta;
        this.activa = activa;
    }
    

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.idioma);
        hash = 19 * hash + Objects.hashCode(this.id);
        hash = 19 * hash + Objects.hashCode(this.pregunta);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PreguntasRecibos other = (PreguntasRecibos) obj;
        if (!Objects.equals(this.idioma, other.idioma)) {
            return false;
        }
        if (!Objects.equals(this.pregunta, other.pregunta)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "PreguntasRecibos{" + "idioma=" + idioma + ", id=" + id + ", pregunta=" + pregunta + ", activa=" + activa + '}';
    }

    /*public PreguntasRecibos(PreguntasRecibosPK preguntasRecibosPK) {
        this.preguntasRecibosPK = preguntasRecibosPK;
    }

    public PreguntasRecibos(PreguntasRecibosPK preguntasRecibosPK, String pregunta) {
        this.preguntasRecibosPK = preguntasRecibosPK;
        this.pregunta = pregunta;
    }

    public PreguntasRecibos(String idioma, BigInteger id) {
        this.preguntasRecibosPK = new PreguntasRecibosPK(idioma, id);
    }

    public PreguntasRecibosPK getPreguntasRecibosPK() {
        return preguntasRecibosPK;
    }

    public void setPreguntasRecibosPK(PreguntasRecibosPK preguntasRecibosPK) {
        this.preguntasRecibosPK = preguntasRecibosPK;
    }*/

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    /*@Override
    public int hashCode() {
        int hash = 0;
        hash += (preguntasRecibosPK != null ? preguntasRecibosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PreguntasRecibos)) {
            return false;
        }
        PreguntasRecibos other = (PreguntasRecibos) object;
        if ((this.preguntasRecibosPK == null && other.preguntasRecibosPK != null) || (this.preguntasRecibosPK != null && !this.preguntasRecibosPK.equals(other.preguntasRecibosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metalsa.recibos.model.PreguntasRecibos[ preguntasRecibosPK=" + preguntasRecibosPK + " ]";
    }*/
    
}
