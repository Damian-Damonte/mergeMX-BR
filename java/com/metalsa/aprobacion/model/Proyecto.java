package com.metalsa.aprobacion.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author APOOD9272
 */
@Entity
@Table (name = "nvc_tbl_proyectos_h")
@Data
@NamedNativeQueries ({
    @NamedNativeQuery (
            name = "Proyecto.findProyectosUsuario",
            resultClass = Proyecto.class,
            query = "select\n"
            + "    pro.id_proyecto,\n"
            + "    pro.id_uen,\n"
            + "    pro.active,\n"
            + "    pro.cod_proyecto,\n"
            + "    pro.nombre_proyecto\n"
            + "from nvc_tbl_proyectos_h pro\n"
            + "inner join proyectos_por_aprobador_uen pre\n"
            + "on pro.id_proyecto = pre.id_proyecto\n"
            + "where pro.active = 1\n"
            + "and pre.id_usuario = :id_usuario"
    )

})

public class Proyecto implements Serializable {

    @Id
    Long idProyecto;

    Long idUen;

    Short active;

    String nombreProyecto;

    String codProyecto;

    @OneToMany (fetch = FetchType.LAZY)
    @JoinTable (
            name = "proyectos_por_aprobador_uen",
            joinColumns = @JoinColumn (name = "id_proyecto"),
            inverseJoinColumns = @JoinColumn (name = "id_usuario")
    )
    List<Preaprobador> preaprobadores = new ArrayList<>();
    

    @Override
    public String toString() {
        return "proyectoPreaprobador{" + "idProyecto=" + idProyecto + ", nombreProyecto=" + nombreProyecto + ", codProyecto=" + codProyecto + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.idProyecto);
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
        final Proyecto other = (Proyecto) obj;
        return Objects.equals(this.idProyecto, other.idProyecto);
    }

}
