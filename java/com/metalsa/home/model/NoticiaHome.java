package com.metalsa.home.model;

import com.metalsa.core.model.Rol;
import com.metalsa.perfil.pojo.Uen;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import org.hibernate.type.StringType;

/**
 *
 * @author APOMR10051
 */
@Data
@Entity
@Table(name = "NOTICIA_HOME")
@SqlResultSetMappings({
    @SqlResultSetMapping(name = "NoticiaMapping.count", columns = @ColumnResult(name = "cnt")),
    @SqlResultSetMapping(
            name = "NoticiaMapping",
            classes = {
                @ConstructorResult(
                        targetClass = NoticiaHome.class,
                        columns = {
                            @ColumnResult(name = "ID_NOTICIA", type = Integer.class),
                            @ColumnResult(name = "CLAVE", type = StringType.class),
                            @ColumnResult(name = "ORDEN", type = Integer.class),
                            @ColumnResult(name = "ID_TIPO_NOTICIA", type = Integer.class),
                            @ColumnResult(name = "DESCRIPCION", type = StringType.class),
                            @ColumnResult(name = "ID_IDIOMA", type = StringType.class),
                            @ColumnResult(name = "DURACION", type = Integer.class),
                            @ColumnResult(name = "FECHA_INICIO_VIGENCIA", type = StringType.class),
                            @ColumnResult(name = "FECHA_FIN_VIGENCIA", type = StringType.class),
                            @ColumnResult(name = "UENS", type = StringType.class),
                            @ColumnResult(name = "ROLES", type = StringType.class),
                            @ColumnResult(name = "USUARIO_CREACION", type = StringType.class),
                            @ColumnResult(name = "USUARIO_ACTUALIZACION", type = StringType.class),
                            @ColumnResult(name = "NOMBRE", type = StringType.class),
                            @ColumnResult(name = "RUTA", type = StringType.class)
                        }
                )}
    )
})
@NamedNativeQueries({
    @NamedNativeQuery(
            name = "NoticiaHome.getNoticias",
            resultClass = NoticiaHome.class,
            query = "select n.* from ("
            + "select a.id_noticia, a.id_tipo_noticia, a.id_idioma, a.clave, a.uens, a.roles, a.nombre, a.descripcion, a.ruta, a.duracion, \n"
            + "a.contenido, a.enlace, a.orden, a.tiene_vigencia, a.activo, a.fecha_inicio_vigencia, a.fecha_fin_vigencia, a.usuario_creacion, \n"
            + "a.usuario_actualizacion from noticia_home a \n"
            + "where trunc(fecha_inicio_vigencia) <= trunc(sysdate) and trunc(fecha_fin_vigencia) >= trunc(sysdate) and activo != 0 and tiene_vigencia = 1 and a.id_idioma = :idIdioma\n"
            + "union all\n"
            + "select a.id_noticia, a.id_tipo_noticia, a.id_idioma, a.clave, a.uens, a.roles, a.nombre, a.descripcion, a.ruta, a.duracion, \n"
            + "a.contenido, a.enlace, a.orden, a.tiene_vigencia, a.activo, a.fecha_inicio_vigencia, a.fecha_fin_vigencia, a.usuario_creacion, \n"
            + "a.usuario_actualizacion from noticia_home a \n"
            + "where trunc(fecha_inicio_vigencia) <= trunc(sysdate) and trunc(fecha_fin_vigencia) >= trunc(sysdate) and activo != 0 and tiene_vigencia = 1 \n"
            + "and a.clave not in (select clave from noticia_home where trunc(fecha_inicio_vigencia) <= trunc(sysdate) and trunc(fecha_fin_vigencia) >= trunc(sysdate) and activo != 0 and tiene_vigencia = 1 and id_idioma = :idIdioma)\n"
            + ") n order by n.orden"
    ),
    @NamedNativeQuery(
            name = "NoticiaHome.getHistorico",
            resultClass = NoticiaHome.class,
            resultSetMapping = "NoticiaMapping",
            query = "select n.id_noticia, n.clave, n.orden, n.id_tipo_noticia, n.descripcion, n.id_idioma, n.duracion / 1000 duracion, "
            + "to_char(n.fecha_inicio_vigencia, 'DD-Mon-YYYY') as fecha_inicio_vigencia, to_char(n.fecha_fin_vigencia, 'DD-Mon-YYYY') as fecha_fin_vigencia, "
            + "n.uens, n.roles, uc.nombre_usuario as usuario_creacion, ua.nombre_usuario as usuario_actualizacion, n.nombre, n.ruta "
            + "from noticia_home n "
            + "left join usuario uc on n.usuario_creacion = uc.id_usuario "
            + "left join usuario ua on n.usuario_actualizacion = ua.id_usuario "
            + "where (activo = 0 and tiene_vigencia = 0) or trunc(fecha_fin_vigencia) < trunc(sysdate) "
            + "order by id_noticia desc/*#pageable*/"
    ),    
    @NamedNativeQuery(
            name = "NoticiaHome.getHistorico.count",
            resultSetMapping = "NoticiaMapping.count",
            query = "select count(1) as cnt "
            + "from noticia_home n "
            + "where (activo = 0 and tiene_vigencia = 0) or trunc(fecha_fin_vigencia) < trunc(sysdate) "
            + "order by id_noticia desc "
    ),
    @NamedNativeQuery(
            name = "NoticiaHome.getHistoricoFiltros",
            resultClass = NoticiaHome.class,
            resultSetMapping = "NoticiaMapping",
            query = "select n.id_noticia, n.clave, n.orden, n.id_tipo_noticia, n.descripcion, n.id_idioma, n.duracion, "
            + "to_char(n.fecha_inicio_vigencia, 'DD-Mon-YYYY') as fecha_inicio_vigencia, to_char(n.fecha_fin_vigencia, 'DD-Mon-YYYY') as fecha_fin_vigencia, "
            + "n.uens, n.roles, uc.nombre_usuario as usuario_creacion, ua.nombre_usuario as usuario_actualizacion, n.nombre, n.ruta "
            + "from noticia_home n "
            + "left join usuario uc on n.usuario_creacion = uc.id_usuario "
            + "left join usuario ua on n.usuario_actualizacion = ua.id_usuario "
            + "where (activo = 0 and tiene_vigencia = 0) or trunc(fecha_fin_vigencia) < trunc(sysdate) "
            + "and (0 in (?1) or n.id_noticia in (?1)) "
            + "and (0 in (?2) or upper(n.descripcion) like (?2)) "
            + "and (0 in (?3) or trim(n.uens) like (?3)) "
            + "and (0 in (?4) or trim(n.roles) like (?4)) "
            + "and (0 in (?5) or n.usuario_creacion in (?5)) "
            + "and (0 in (?5) or n.usuario_actualizacion in (?5)) "  
            + "order by id_noticia desc/*#pageable*/"
    ),    
    @NamedNativeQuery(
            name = "NoticiaHome.getHistoricoFiltros.count",
            resultSetMapping = "NoticiaMapping.count",
            query = "select count(1) as cnt "
            + "from noticia_home n "
            + "where (activo = 0 and tiene_vigencia = 0) or trunc(fecha_fin_vigencia) < trunc(sysdate) "
            + "order by id_noticia desc "
    )
})
public class NoticiaHome implements Serializable {

    @Id
    @Column(name = "id_noticia")
    private Integer idNoticia;

    @Column(name = "id_tipo_noticia")
    private Integer idTipoNoticia;

    @Column(name = "uens")
    private String uens;

    @Transient
    private Iterable<Uen> uenList;

    @Column(name = "id_idioma")
    private String idIdioma;

    @Column(name = "clave")
    private String clave;

    @Column(name = "roles")
    private String roles;

    @Transient
    private Iterable<Rol> rolList;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "ruta")
    private String ruta;

    @Transient
    private String ftpPath;

    @Transient
    private String urlMedia;
    
    @Transient
    private String nombreFtp;

    @Column(name = "duracion")
    private Integer duracion;

    @Column(name = "contenido")
    private String contenido;

    @Column(name = "enlace")
    private String enlace;

    @Column(name = "orden")
    private Integer orden;

    @Column(name = "tiene_vigencia")
    private Integer tieneVigencia;

    @Column(name = "activo")
    private Integer activo;

    @Column(name = "fecha_inicio_vigencia")
    private String fechaInicioVigencia;

    @Column(name = "fecha_fin_vigencia")
    private String fechaFinVigencia;

    @Column(name = "usuario_creacion")
    private String usuarioCreacion;

    @Column(name = "usuario_actualizacion")
    private String usuarioActualizacion;

    @Transient
    private byte[] file;
    
    public NoticiaHome() {
    }
    
    public NoticiaHome(Integer idNoticia, String clave, Integer orden, Integer idTipoNoticia, String descripcion, String idIdioma,
            Integer duracion, String fechaInicioVigencia, String fechaFinVigencia, String uens, String roles, String usuarioCreacion,
            String usuarioActualizacion, String nombre, String ruta) {
        this.idNoticia = idNoticia;
        this.clave = clave;
        this.orden = orden;
        this.idTipoNoticia = idTipoNoticia;
        this.descripcion = descripcion;
        this.idIdioma = idIdioma;
        this.duracion = duracion;
        this.fechaInicioVigencia = fechaInicioVigencia;
        this.fechaFinVigencia = fechaFinVigencia;
        this.uens = uens;
        this.roles = roles;
        this.usuarioCreacion = usuarioCreacion;
        this.usuarioActualizacion = usuarioActualizacion;
        this.nombre = nombre;
        this.ruta = ruta;
    }

}
