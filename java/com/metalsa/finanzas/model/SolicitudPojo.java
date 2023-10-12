package com.metalsa.finanzas.model;

import java.util.List;
import lombok.Data;

/**
 *
 * @author JL
 */
@Data
public class SolicitudPojo{
    
    private Long idUen;
    private Long idCcDestino;
    private Long idCcOrigen;
    private String periodoDestino;
    private String periodoOrigen;
    //@JsonFormat(pattern="yyyy-MM-dd")
    private String fechaNecesidad;
    private String razon;
    private Integer idEstatus;
    private String usuarioCreacion;
    private String tipo;
    private List<CategoriaPojo> categorias;
    private List<AdjuntoPojo> adjuntos;
    
    /*public Date getFechaNecesidad() {
        return fechaNecesidad == null ? null : (Date) fechaNecesidad.clone();
    }

    public void setFechaNecesidad(Date fechaNecesidad) {
        if (fechaNecesidad == null)
            this.fechaNecesidad = null;
        else
            this.fechaNecesidad = (Date) fechaNecesidad.clone();
    }*/
    
   }
