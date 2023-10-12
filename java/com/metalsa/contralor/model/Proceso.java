package com.metalsa.contralor.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
	//<R39943>
/**
 *
 * @author Oscar del Angel
 */
@NamedNativeQueries({
})

@Entity(name = "Proceso")
@Getter
@Setter
@EqualsAndHashCode(of = "idProceso")
public class Proceso implements Serializable {

    @Id
    @Column(name = "id_proceso")
    private Integer idProceso;

    @Column(name = "nombre_proceso")
    private String nombre;

    @Column(name = "clave")
    private String clave;

}
