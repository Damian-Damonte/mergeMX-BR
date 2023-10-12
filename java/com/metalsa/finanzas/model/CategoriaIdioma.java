package com.metalsa.finanzas.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

/**
 *
 * @author jose.rivera02
 */
@Data
@Entity(name = "NVC_V_ACC_CATEGORIA")
@FilterDef(name="idiomaUsuarioFilter",
        parameters={
		@ParamDef( name="idIdioma", type="string" )
    
}, defaultCondition = "idIdioma='ESA'")
public class CategoriaIdioma implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "ACC_CAT_ID")
    private Integer accCatId;
    
    @Column(name = "NOMBRE_CATEGORIA")
    private String nombreCategoria;
   
    @Column(name = "IDIOMA")
    private String idioma;
}

