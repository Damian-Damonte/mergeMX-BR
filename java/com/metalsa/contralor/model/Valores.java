package com.metalsa.contralor.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Oscar del Angel
 */
@NamedNativeQueries({
    @NamedNativeQuery(
            name = "findByParameterUen",
            resultClass = Valores.class,
            query = "select * from nvc_tbl_parameter_value where id_parameter_uen = :id_parameter_uen"
    )
    ,
    @NamedNativeQuery(
            name = "Valores.findSingleValue",
            resultClass = Valores.class,
            query = "SELECT\n"
            + "    pv.id_parameter_value,\n"
            + "    pv.id_parameter_uen,\n"
            + "    pv.parameter_value,\n"
            + "    pv.parameter_property,\n"
            + "    pv.parameter_condition, \n"
            + "    pv.parameter_value_cc \n"
            + "FROM nvc_tbl_parameter_value pv\n"
            + "WHERE id_parameter_uen = (\n"
            + "    SELECT id_parameter_uen \n"
            + "    FROM nvc_tbl_parameter_uen \n"
            + "    WHERE  id_uen = ?1\n"
            + "	AND id_parameter = (\n"
            + "        select id_parameter \n"
            + "        from nvc_tbl_parameter \n"
            + "        where parameter_name   = ?2\n"
            + "    )\n"
            + ")"
    )

})

@Getter
@Setter
@Entity(name = "nvc_tbl_parameter_value")
public class Valores implements Serializable {

    @Id
    @Column(name = "id_parameter_value")
    private Long id;

    @Column(name = "id_parameter_uen")
    private Long idParameterUen;

    @Column(name = "parameter_value")
    private String value;

    @Column(name = "parameter_property")
    private String property;

    @Column(name = "parameter_condition")
    private String condition;
    
    @Column(name = "parameter_value_cc")
    private String valueCc;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final Valores other = (Valores) obj;
        return Objects.equals(this.id, other.id);
    }

}
