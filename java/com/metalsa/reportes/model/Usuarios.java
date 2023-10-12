package com.metalsa.reportes.model;

import java.io.Serializable;
import javax.persistence.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Oscar del Angel
 */

@NamedStoredProcedureQuery(
	name = "Usuarios.getUsuariosTransaccion", 
	procedureName = "NVC_PKG_REPORTES_SPX.GET_USUARIOS_TRANSACCION", 
	resultClasses = Usuarios.class, 
	parameters = {
		@StoredProcedureParameter(name = "p_tipo_reporte",mode = ParameterMode.IN, type = String.class),
		@StoredProcedureParameter(name = "p_tipo_usuario",mode = ParameterMode.IN, type = String.class),
		@StoredProcedureParameter(name = "p_ref_cursor",mode = ParameterMode.REF_CURSOR, type = Void.class)
	}
)
@Entity
@Getter
@Setter
public class Usuarios implements Serializable {

    @Id
    @Column(name = "id_usuario")
    private String idUsuario;

    @Column(name = "nombre_usuario")
    private String nombreUsuario;

}
