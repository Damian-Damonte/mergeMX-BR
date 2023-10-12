package com.metalsa.aprobacion.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.type.BooleanType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Modelo para usuarios por centro de costo
 */
@Entity(name = "cc_por_usuario")
@IdClass(CcPorUsuario.Pk.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "CcPorUsuario.extendido",
                classes = {
                        @ConstructorResult(
                                targetClass = CcPorUsuario.class,
                                columns = {
                                        @ColumnResult(name = "id_usuario", type = StringType.class),
                                        @ColumnResult(name = "id_uen", type = LongType.class),
                                        @ColumnResult(name = "id_cc", type = LongType.class),
                                        @ColumnResult(name = "tipo_de_relacion", type = StringType.class),
                                        @ColumnResult(name = "cc_default", type = BooleanType.class),
                                }
                        )
                }
        ),
        @SqlResultSetMapping(
                    name = "CcPorUsuario.responsable",
                    classes = {
                            @ConstructorResult(
                                    targetClass = CcPorUsuario.class,
                                    columns = {
                                            @ColumnResult(name = "id_usuario", type = StringType.class),
                                            @ColumnResult(name = "id_uen", type = LongType.class),
                                            @ColumnResult(name = "id_cc", type = LongType.class),
                                            @ColumnResult(name = "codigo_cc", type = StringType.class),
                                            @ColumnResult(name = "nombre_cc", type = StringType.class),
                                            @ColumnResult(name = "tipo_de_relacion", type = StringType.class),
                                    }
                            )
                    }
            )
})
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "CcPorUsuario.getUsuarioExtByCcAndUenAndUsuario",
                resultSetMapping = "CcPorUsuario.extendido",
                query = "select cu.id_usuario, cu.id_uen, cu.id_cc, cu.tipo_de_relacion, \n" +
                        "    decode(cu.cc_default, 'S', 1, 0) as cc_default \n" + //<R21206>
                        "from cc_por_usuario cu \n" +
                        "  join nvc_tbl_oa_cc_h cc on cc.id_uen = cu.id_uen and cc.id_cc = cu.id_cc \n" +
                        "where cu.id_uen = :uen \n" +
                        "  and cu.id_cc = :cc \n" +
                        "  and cu.id_usuario = :usuario \n" +
                        "order by cu.tipo_de_relacion desc, cu.id_usuario"
        ),
    @NamedNativeQuery(
                name = "CcPorUsuario.getnombreCcByUsuarioAndUenAndUsuario",
                resultSetMapping = "CcPorUsuario.responsable",
                query = "select DISTINCT cu.id_usuario, cu.id_uen, cu.id_cc, cc.codigo_cc, cc.nombre_cc, cu.tipo_de_relacion  " +
                        "from cc_por_usuario cu  " +
                        "  join NVC_VM_OA_CC cc on cc.id_uen = cu.id_uen and cc.id_cc = cu.id_cc  " +
                        "where cu.id_uen = :uen  " +
                        "  and cu.id_usuario = :usuario  " +
                        "  and cc.lenguaje = :lang  " +
                        "  and cu.tipo_de_relacion in (:tipoRelacion)  " +  //R40102 
                        "order by cc.codigo_cc"
        ),
	//<MDA_CONTRALOR>
    @NamedNativeQuery(
            name = "CcPorUsuario.getCCSPorTipoRelacion",
            resultSetMapping = "CcPorUsuario.responsable",
                query = "select DISTINCT cu.id_usuario, cu.id_uen, cu.id_cc, cc.codigo_cc, cc.nombre_cc, cu.tipo_de_relacion  " +
                        "from cc_por_usuario cu  " +
                        "  join NVC_VM_OA_CC cc on cc.id_uen = cu.id_uen and cc.id_cc = cu.id_cc  " + //mda_reportes
                        "where cu.id_uen = :uen  " +
                        "  and cu.id_usuario = :usuario  " +
                        "  and cc.lenguaje = :lang  " +
                        "  and cu.tipo_de_relacion = :relacion " +
                        //"  and cc.active= 1  " +
                        "order by cc.codigo_cc"
        ),
		//<MDA_CONTRALOR>
    //<MDA_INC_DEC>
    @NamedNativeQuery(
            name = "CcPorUsuario.getnombreCcByUenAndLangAndUsuario",
            resultSetMapping = "CcPorUsuario.responsable",
                query = "select cu.id_usuario, cu.id_uen, cu.id_cc, cc.codigo_cc, cc.nombre_cc, cu.tipo_de_relacion  " +
                        "from cc_por_usuario cu  " +
                        "  join nvc_VM_oa_cc cc on cc.id_uen = cu.id_uen and cc.id_cc = cu.id_cc  " +
                        "where cu.id_uen = :uen  " +
                        "  and cu.id_usuario = :usuario  " +
                        "  and cc.lenguaje = :lang  " +
                        "  and cu.tipo_de_relacion in ('Del','Resp')" + 
                        "  order by cu.CC_DEFAULT desc, cc.codigo_cc"
    )
    ,
    @NamedNativeQuery(
            name = "CcPorUsuario.getCCSDefault",
            resultSetMapping = "CcPorUsuario.responsable",
            query = "select "
            + "    cu.id_usuario, "
            + "    cu.id_uen, "
            + "    cu.id_cc, "
            + "    cc.codigo_cc, "
            + "    cc.nombre_cc, "
            + "    cu.tipo_de_relacion "
            + "from cc_por_usuario cu "
            + "join nvc_VM_oa_cc cc "
            + "    on cc.id_uen = cu.id_uen "
            + "    and cc.id_cc = cu.id_cc "
            + "where cu.id_usuario = :usuario "
            + "    and cc.lenguaje = :lang "
            + "    and cu.cc_default = 'S'"
    )
		//<MDA_CONTRALOR>
    ,
    @NamedNativeQuery(
            name = "CcPorUsuario.getUsuariosPorTipoRelacion",
            resultClass = UsuarioUen.class,
            query = "select\n"
            + "    id_usuario,\n"
            + "    id_uen,\n"
            + "    get_nombre_usuario(id_usuario) nombre_usuario\n"
            + "from cc_por_usuario\n"
            + "where tipo_de_relacion = :tipo\n"
            + "and id_uen = :uen\n"
            + "group by id_usuario,id_uen"
    )
    ,
        @NamedNativeQuery(  //R40102 
            name = "CcPorUsuario.get_RespccOrDel",
            resultSetMapping = "CcPorUsuario.extendido",
            query = "SELECT  cc.id_usuario,cc.id_uen,cc.id_cc,cc.tipo_de_relacion,decode(cc.cc_default, 'S', 1, 0) cc_default\n"
            + "FROM CC_POR_USUARIO cc\n"
            + "WHERE cc.id_uen = :idUen\n"
            + "AND cc.id_cc = :idCentroCosto\n"
            + "AND cc.id_usuario = :idUsuario\n"
            + "AND UPPER(cc.tipo_de_relacion) = upper(:tipoRelacion)"
            + "AND ROWNUM =1"
    )
		//<MDA_CONTRALOR>
//</MDA_INC_DEC>
})


public class CcPorUsuario implements Serializable {

    public void CcPorUsuario(){
        
    }
    
    public CcPorUsuario(String usuario,Long uen,Long cc,String relacion,boolean ccDefault){
        this.usuario = usuario;
        this.uen = uen;
        this.cc = cc;
        this.relacion = relacion;
        this.ccDefault = ccDefault;
        
    }
    
    public CcPorUsuario(String usuario,Long uen,Long cc,String codigoCc,String nombreCc,
                   String relacion ){
        this.usuario = usuario;
        this.uen = uen;
        this.cc = cc;
        this.codigoCc = codigoCc;
        this.nombreCc  = nombreCc;
        this.relacion = relacion;        
    }
    
    @Id
    @Column(name = "id_usuario")
    private String usuario;

    @Id
    @Column(name = "id_uen")
    private Long uen;

    @Id
    @Column(name = "id_cc")
    private Long cc;

    @Column(name = "tipo_de_relacion")
    private String relacion;

    @Transient
    @Column(name="cc_default")
    private boolean ccDefault;
    
    @Transient
    @Column(name = "codigo_cc")
    private String codigoCc;
    
    @Transient
    @Column(name = "nombre_cc")
    private String nombreCc;
    
    @Transient
    private String lenguaje;
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.usuario);
        hash = 41 * hash + Objects.hashCode(this.uen);
        hash = 41 * hash + Objects.hashCode(this.cc);
        hash = 41 * hash + Objects.hashCode(this.relacion);
        hash = 41 * hash + Objects.hashCode(this.ccDefault);
        hash = 41 * hash + Objects.hashCode(this.codigoCc);
        hash = 41 * hash + Objects.hashCode(this.nombreCc);
        hash = 41 * hash + Objects.hashCode(this.lenguaje);
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
        final CcPorUsuario other = (CcPorUsuario) obj;
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        if (!Objects.equals(this.uen, other.uen)) {
            return false;
        }
        if (!Objects.equals(this.cc, other.cc)) {
            return false;
        }
        if (!Objects.equals(this.relacion, other.relacion)) {
            return false;
        }
        if (!Objects.equals(this.ccDefault, other.ccDefault)) {
            return false;
        }
        if (!Objects.equals(this.codigoCc, other.codigoCc)) {
            return false;
        }
        if (!Objects.equals(this.nombreCc, other.nombreCc)) {
            return false;
        }
        return Objects.equals(this.lenguaje, other.lenguaje);
    }

    @Override
    public String toString() {
        return "com.metalsa.aprobacion.model.CcPorUsuario[ usuario=" + usuario 
                + ", uen" + uen
                + ", cc" + cc 
                + ", relacion" + relacion
                + ", ccDefault" + ccDefault
                + ", codigoCc" + codigoCc
                + ", nombreCc" + nombreCc
                + ", lenguaje" + lenguaje
                + " ]";
    }

    /**
     * Primary key
     */
    @Data
    public static class Pk implements Serializable {
        @Id
        @Column(name = "id_usuario")
        private String usuario;

        @Id
        @Column(name = "id_uen")
        private Long uen;

        @Id
        @Column(name = "id_cc")
        private Long cc;
    }
}
