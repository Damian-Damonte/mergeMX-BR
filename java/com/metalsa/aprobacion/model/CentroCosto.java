package com.metalsa.aprobacion.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
//<R16788>
import java.util.Objects;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.persistence.Transient;
//</R16788>

/**
 * Modelo de Centro de Costo
 *
 * @author ruben.bresler
 */
//<R16788>
@NamedNativeQueries({
    @NamedNativeQuery(
            name = "CentroCosto.getAllByIdUenAndIdioma", //MDA_REPORTES_FINANZAS
            query = "select\n"
            + "    id_uen,\n"
            + "    id_cc,\n"
            + "    codigo_cc,\n"
            + "    nombre_cc,\n"
            + "    acceso_restringido,\n"
            + "    lenguaje\n"
            + "from nvc_vm_oa_cc\n"
            + "where id_uen = ?1\n"
            + "and lenguaje = ?2\n"
            + "order by codigo_cc",
            resultClass = CentroCosto.class
    )
    ,
    @NamedNativeQuery(
            name = "CentroCosto.getAllByRespAndDel", //MDA_REPORTES_FINANZAS
            query = "select\n"
            + "    cc.id_uen,\n"
            + "    cc.id_cc,\n"
            + "    cc.codigo_cc,\n"
            + "    cc.nombre_cc,\n"
            + "    cc.acceso_restringido,\n"
            + "    cc.lenguaje\n"
            + "from nvc_vm_oa_cc cc\n"
            + "inner join cc_por_usuario ccu\n"
            + "on ccu.id_cc = cc.id_cc\n"
            + "and ccu.id_uen = cc.id_uen\n"
            + "and cc.id_uen = ?1\n"
            + "and ccu.id_usuario = ?2\n"
            + "and cc.lenguaje = ?3\n"
            + "and ccu.tipo_de_relacion in('Resp', 'Del')\n"
            + "order by cc.codigo_cc",
            resultClass = CentroCosto.class
    )
    ,
    @NamedNativeQuery(
            name = "CentroCosto.getAllDistinctCCS",
            query = "select  \n"
            + "    id_cc,  \n"
            + "    codigo_cc,  \n"
            + "    nombre_cc  \n"
            + "from NVC_VM_OA_CC  \n"
            + "where lenguaje = ?1  \n"
            + "group by id_cc,codigo_cc,nombre_cc ",
            resultSetMapping = "CentroCosto.mainvaluesContructor"
    )
	//MDA_REPORTES_FINANZAS
})

@SqlResultSetMappings({
    @SqlResultSetMapping(
        name = "CentroCosto.mainvaluesContructor",
        classes = @ConstructorResult(
            targetClass = CentroCosto.class,
            columns = {
                @ColumnResult(name = "id_cc", type = Long.class),
                @ColumnResult(name = "codigo_cc", type = String.class),
                @ColumnResult(name = "nombre_cc", type = String.class)
            }
        )
    )
})
@Table(name="nvc_vm_oa_cc") //MDA_REPORTES_FINANZAS
@Entity
@IdClass(CentroCosto.CentroCostoPk.class)
public class CentroCosto implements Serializable {
//</R16788>
    
    @Id
    @Column(name = "id_cc")
    private Long id;

    @Id
    @Column(name = "codigo_cc")
    private String codigo;

    @Column(name = "nombre_cc")
    private String nombre;

    @Column(name = "id_uen")
    private Long idUen;

    @Column(name = "acceso_restringido")
    private boolean restringido;

    @Column(name = "lenguaje")
    private String idioma;

    @Transient
    private String fullName;


    public CentroCosto() {
    }

    public CentroCosto(Long id, String codigo, String nombre) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    
    public String getFullName() {
        this.fullName = this.codigo + "-" + this.nombre;
        return this.fullName;
    }
    //<R16788>
    
    public String getNombre() {
        return nombre;//
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getIdUen() {
        return idUen;
    }

    public void setIdUen(Long idUen) {
        this.idUen = idUen;
    }

    public boolean isRestringido() {
        return restringido;
    }

    public void setRestringido(boolean restringido) {
        this.restringido = restringido;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    /**
     * Primary key
     */
    @Data
    public static class CentroCostoPk implements Serializable {

        @Id
        @Column(name = "id_cc")
        private Long id;

        @Id
        @Column(name = "codigo_cc")
        private String codigo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.codigo);
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
        final CentroCosto other = (CentroCosto) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }
    //</R16788>    

}
