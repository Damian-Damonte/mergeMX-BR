package com.metalsa.finanzas.model;

import java.io.Serializable;
import javax.persistence.Column;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author jose.rivera02
 */
@Data
@EqualsAndHashCode
public class FamiliasIdiomaPk implements Serializable{
    
    @Column(name = "ID_FAMILIA")
    private Long idCategoria;
    
    @Column(name = "ID_IDIOMA")
    private String idIdioma;
}
