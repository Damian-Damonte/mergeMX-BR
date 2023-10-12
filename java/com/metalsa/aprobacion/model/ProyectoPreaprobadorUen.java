package com.metalsa.aprobacion.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author APOOD9272
 */

@Entity
@Table (name = "proyectos_por_aprobador_uen")
@Data
public class ProyectoPreaprobadorUen implements Serializable {

    @Id
    private Long id;
    
    @Column(name="id_usuario")
    private String idUsuario;
    
    @Column(name="id_proyecto")
    private Long idProyecto;
    
}
