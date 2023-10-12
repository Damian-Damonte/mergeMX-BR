/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import static javax.persistence.ParameterMode.IN;
import static javax.persistence.ParameterMode.REF_CURSOR;
import javax.persistence.StoredProcedureParameter;

/**
 *
 * @author yair.nunez
 */
@Entity
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
            name = "obtenerRequisitores",
            resultClasses = Requisitor.class,
            procedureName = "UTILERIAS_PKG.obtener_lstrequisitores",
            parameters = {
                @StoredProcedureParameter(mode = REF_CURSOR, name = "V_CURSOR", type = void.class),
                @StoredProcedureParameter(mode = IN, name = "p_id_usuario", type = String.class),
                @StoredProcedureParameter(mode = IN, name = "p_id_uen", type = Integer.class)
            }
    )
})
@IdClass(RequisitorPK.class)
public class Requisitor implements Serializable{
    //MDA_CONTRALOR
    public Requisitor() {
    }

    public Requisitor(String idUsuario, String nombreUsuario) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
    }
    //MDA_CONTRALOR
		
    @Id
    @Column(name ="ID_USUARIO")
    private String idUsuario;
    
    @Column(name ="NOMBRE_USUARIO")
    private String nombreUsuario;

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    @Override
    public String toString() {
        return "Requisitor{" + "idUsuario=" + idUsuario + ", nombreUsuario=" + nombreUsuario + '}';
    }
    
    
}
