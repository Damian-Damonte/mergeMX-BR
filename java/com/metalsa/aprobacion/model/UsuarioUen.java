package com.metalsa.aprobacion.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@Entity
@Table(name = "nvc_v_usuarios_por_uen")
@IdClass(UsuarioUen.Pk.class)
@Data
public class UsuarioUen implements Serializable{
    @Id
    private Long idUen;
    
    @Id
    private String idUsuario;
    
    private String nombreUsuario;

    /**
     * Pk
     */
    @Data
    public static class Pk implements Serializable {
        @Id
        private Long idUen;
        @Id
        private String idUsuario;
    }
}
