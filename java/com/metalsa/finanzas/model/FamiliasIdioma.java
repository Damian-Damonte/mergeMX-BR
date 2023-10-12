package com.metalsa.finanzas.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
/**
 *
 * @author jose.rivera02
 */
@Data
@Entity(name = "NVC_TBL_FAMILIAS_IDIOMA")
public class FamiliasIdioma implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "ID_FAMILIA")
    private Long idCategoria;
    
    @Id
    @Column(name = "ID_IDIOMA")
    private String idIdioma;
    
    @Column(name = "NOMBRE_FAMILIA_IDIOMA")
    private String nombreFamiliaIdioma;
   
    
}
