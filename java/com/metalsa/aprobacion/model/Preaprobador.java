package com.metalsa.aprobacion.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;

/**
 *
 * @author APOOD9272
 */
@Entity
@Table (name = "Usuario")
@Data
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
            name = "Preaprobador.eliminaRolNoPreaprobador",
            procedureName = "NVC_PKG_SPX_APPROVALS.REMOVER_ROL_PREAPROBADOR_PROY"
    ),
//    @NamedStoredProcedureQuery(
//            name = "Preaprobador.agregarRolPreaprobador",
//            procedureName = "NVC_PKG_SPX_APPROVALS.AGREGAR_ROLES_PREAPROBADORES",
//            parameters ={
//                @StoredProcedureParameter(mode = ParameterMode.OUT,name = "P_OUT_RESULT",type = Integer.class)
//            }
//    ),
    @NamedStoredProcedureQuery(
            name = "Preaprobador.removerPreaprobador",
            procedureName = "NVC_PKG_SPX_APPROVALS.REMOVER_PREAPROBADORES",
            parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN,name = "P_ID_PROYECTO",type = Long.class),
                @StoredProcedureParameter(mode = ParameterMode.IN,name = "P_ID_USUARIOS",type = String.class)
            }
    ),
    @NamedStoredProcedureQuery(
            name = "Preaprobador.asignarPreaprobadores",
            procedureName = "NVC_PKG_SPX_APPROVALS.ASIGNAR_PREPROBADORES_PROYECTO",
            parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN,name = "P_ID_PROYECTO",type = Long.class),
                @StoredProcedureParameter(mode = ParameterMode.IN,name = "P_ID_USUARIOS",type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT,name = "P_OUT_RESULT",type = Integer.class)
            }
    )
})


@NamedNativeQueries (
        {
            @NamedNativeQuery (
                    name = "Preaprobador.findPreAprobadorDefault",
                    query = "select id_usuario,nombre_usuario from preaprobadores_x_defecto_x_uen where id_uen = :id_uen",
                    resultClass = Preaprobador.class
            )
            ,
        @NamedNativeQuery (
                    name = "Preaprobador.findPreaprobadoresUen",
                    query = "select id_usuario,nombre_usuario,id_uen from preaprobadores_x_defecto_x_uen",
                    resultSetMapping = "map.preaprobador"
            )
        }
)
@SqlResultSetMappings ({
    @SqlResultSetMapping (
            name = "map.preaprobador",
            classes = @ConstructorResult (
                    targetClass = Preaprobador.class,
                    columns = {
                        @ColumnResult (name = "id_usuario", type = String.class)
                        ,
                        @ColumnResult (name = "nombre_usuario", type = String.class)
                        ,
                        @ColumnResult (name = "id_uen", type = Long.class)
                    }
            )
    )
})

public class Preaprobador implements Serializable {

    @Id
    @Column (name = "id_usuario")
    private String idUsuario;

    @Column (name = "nombre_usuario")
    private String nombreUsuario;

    @Transient
    private boolean isDefault;

    @Transient
    private Long idUen;

    public Preaprobador() {
    }

    public Preaprobador(String idUsuario, String nombreUsuario, Long idUen) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.idUen = idUen;
    }

    @Override

    public String toString() {
        return "\tPreaprobador{" + "idUsuario=" + idUsuario + ", nombreUsuario=" + nombreUsuario + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.idUsuario);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Preaprobador other = (Preaprobador) obj;
        return Objects.equals(this.idUsuario, other.idUsuario);
    }

}
