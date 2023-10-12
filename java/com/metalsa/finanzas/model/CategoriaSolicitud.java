package com.metalsa.finanzas.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import lombok.Data;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.ParamDef;

/**
 *
 * @author JL
 */
@Data
@Entity(name = "NVC_TBL_CATEGORIA_PRESUPUESTO")
@FilterDef(name="estatusPartidaFilter",
        parameters={
		@ParamDef( name="idEstatus", type="integer" )
    
})
public class CategoriaSolicitud implements Serializable {

    private static final long serialVersionUID = 1L;    
    
    @Id
    private Long id;

    @Column(name = "ID_SOLICITUD_PRESUPUESTO")
    private Long idSolicitudPresupuesto;
    
    @Column(name="ID_PARTIDA")
    private Integer idPartida;
    
    @Column(name = "ID_CATEGORIA_DESTINO")
    private Long idCategoriaDestino;
    
    @Column(name = "ID_CATEGORIA_ORIGEN")
    private Long idCategoriaOrigen;
    
    private Double monto;
    
    private String moneda;
    
    @Column(name="ID_ESTATUS")
    private Integer idEstatus;
    
    @Column(name="USUARIO_CC")
    private String aprobadorCC;
    
    @Formula("GET_NOMBRE_USUARIO(USUARIO_CC)")
    private String nombreAprobadorCC;
    
    @Temporal (javax.persistence.TemporalType.DATE)
    private Date fechaCreacion;
    
    @Transient
    private String sFechaCreacion;
    
    @Column(name="visto")
    private Integer visto;
    
    @Column(name="COMENTARIOS")
    private String comentarios;
    
    @OneToMany
    @JoinColumn(name="ACC_CAT_ID", referencedColumnName = "ID_CATEGORIA_DESTINO")
    @Filter(name="idiomaUsuarioFilter", condition="IDIOMA=:idIdioma")
    private Set<CategoriaIdioma> nombre;

    
    @OneToMany
    @JoinColumn(name="ACC_CAT_ID", referencedColumnName = "ID_CATEGORIA_ORIGEN")
    @Filter(name="idiomaUsuarioFilter", condition="IDIOMA=:idIdioma")
    private Set<CategoriaIdioma> nombreOrigen;
    
    
    public Date getFechaCreacion() {
        return fechaCreacion == null ? null : (Date) fechaCreacion.clone();
    }

    public void setFechaCreacion(Date fechaCreacion) {
        if (fechaCreacion == null)
            this.fechaCreacion = null;
        else
            this.fechaCreacion = (Date) fechaCreacion.clone();
    }
    


    /**
     * @return the sFechaCreacion
     */
    public String getsFechaCreacion() {
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        this.setsFechaCreacion(df.format(this.getFechaCreacion()));
        return  sFechaCreacion;
    }
    
    /**
     * @param sFechaCreacion the sFechaCreacion to set
     */
    public void setsFechaCreacion(String sFechaCreacion) {
        this.sFechaCreacion = sFechaCreacion;
    }
}
