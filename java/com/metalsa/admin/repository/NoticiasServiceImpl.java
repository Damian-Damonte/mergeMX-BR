package com.metalsa.admin.repository;

import com.metalsa.admin.model.NoticiaRequest;
import com.metalsa.admin.model.NoticiaResponse;
import com.metalsa.admin.pojo.FiltroObj;
import com.metalsa.admin.pojo.FiltrosNoticias;
import com.metalsa.core.model.Rol;
import com.metalsa.finanzas.repository.UploadFileRepository;
import com.metalsa.home.model.NoticiaHome;
import com.metalsa.perfil.pojo.Uen;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Struct;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author miguel.rdz
 * @param <T>
 * @param <ID>
 */
@Log4j
@Service
public class NoticiasServiceImpl<T, ID extends Serializable> implements NoticiasService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UploadFileRepository UploadFileRepository;

    @Getter
    @Setter
    @Value("${ftp.host}")
    private String host;

    @Getter
    @Setter
    @Value("${ftp.port}")
    private Integer port;

    @Getter
    @Setter
    @Value("${ftp.user}")
    private String user;

    @Getter
    @Setter
    @Value("${ftp.password}")
    private String pass;

    @Getter
    @Setter
    @Value("${iis.env}")
    private String env;

    @Override
    public List<NoticiaHome> getActuales() {
        List<NoticiaHome> noticias = new ArrayList<>();
        NoticiaHome noticia;
        try {
            StoredProcedureQuery procedure = em.createStoredProcedureQuery("PKG_NOTICIAS.GET_NOTICIAS_VIGENTES");
            procedure.registerStoredProcedureParameter("cursor_out", void.class, ParameterMode.REF_CURSOR);
            boolean isOK = procedure.execute();
            if (isOK) {
                List<Object[]> cursor = (List<Object[]>) procedure.getResultList();
                for (Object[] row : cursor) {
                    noticia = new NoticiaHome();
                    int pos = -1;
                    noticia.setIdNoticia(row[++pos] == null ? null : ((BigDecimal) row[pos]).intValue());
                    noticia.setClave(row[++pos] == null ? null : ((String) row[pos]));
                    noticia.setOrden(row[++pos] == null ? null : ((BigDecimal) row[pos]).intValue());
                    noticia.setIdTipoNoticia(row[++pos] == null ? null : ((BigDecimal) row[pos]).intValue());
                    noticia.setDescripcion(row[++pos] == null ? null : ((String) row[pos]));
                    noticia.setIdIdioma(row[++pos] == null ? null : ((String) row[pos]));
                    noticia.setDuracion(row[++pos] == null ? null : ((BigDecimal) row[pos]).intValue() / 1000);
                    noticia.setTieneVigencia(row[++pos] == null ? null : ((BigDecimal) row[pos]).intValue());
                    noticia.setFechaInicioVigencia(row[++pos] == null ? null : ((String) row[pos]));
                    noticia.setFechaFinVigencia(row[++pos] == null ? null : ((String) row[pos]));
                    noticia.setUens(row[++pos] == null ? null : ((String) row[pos]));
                    noticia.setRoles(row[++pos] == null ? null : ((String) row[pos]));
                    noticia.setUsuarioCreacion(row[++pos] == null ? null : ((String) row[pos]));
                    noticia.setUsuarioActualizacion(row[++pos] == null ? null : ((String) row[pos]));
                    noticia.setNombre(row[++pos] == null ? null : ((String) row[pos]));
                    noticia.setRuta(row[++pos] == null ? null : ((String) row[pos]));
                    noticias.add(noticia);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return noticias;
    }

    @Override
    public List<NoticiaHome> getHistorico() {
        List<NoticiaHome> noticias = new ArrayList<>();
        NoticiaHome noticia;
        try {
            StoredProcedureQuery procedure = em.createStoredProcedureQuery("PKG_NOTICIAS.GET_NOTICIAS_HISTORICO");
            procedure.registerStoredProcedureParameter("cursor_out", void.class, ParameterMode.REF_CURSOR);
            boolean isOK = procedure.execute();
            if (isOK) {
                List<Object[]> cursor = (List<Object[]>) procedure.getResultList();
                for (Object[] row : cursor) {
                    noticia = new NoticiaHome();
                    int pos = -1;
                    noticia.setIdNoticia(row[++pos] == null ? null : ((BigDecimal) row[pos]).intValue());
                    noticia.setClave(row[++pos] == null ? null : ((String) row[pos]));
                    noticia.setOrden(row[++pos] == null ? null : ((BigDecimal) row[pos]).intValue());
                    noticia.setIdTipoNoticia(row[++pos] == null ? null : ((BigDecimal) row[pos]).intValue());
                    noticia.setDescripcion(row[++pos] == null ? null : ((String) row[pos]));
                    noticia.setIdIdioma(row[++pos] == null ? null : ((String) row[pos]));
                    noticia.setDuracion(row[++pos] == null ? null : ((BigDecimal) row[pos]).intValue() / 1000);
                    noticia.setFechaInicioVigencia(row[++pos] == null ? null : ((String) row[pos]));
                    noticia.setFechaFinVigencia(row[++pos] == null ? null : ((String) row[pos]));
                    noticia.setUens(row[++pos] == null ? null : ((String) row[pos]));
                    noticia.setRoles(row[++pos] == null ? null : ((String) row[pos]));
                    noticia.setUsuarioCreacion(row[++pos] == null ? null : ((String) row[pos]));
                    noticia.setUsuarioActualizacion(row[++pos] == null ? null : ((String) row[pos]));
                    noticia.setNombre(row[++pos] == null ? null : ((String) row[pos]));
                    noticia.setRuta(row[++pos] == null ? null : ((String) row[pos]));
                    noticias.add(noticia);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return noticias;
    }

    @Override
    public FiltrosNoticias getFiltrosHist() {
        FiltrosNoticias filtros = new FiltrosNoticias();
        try {
            filtros.setNoticiaList(getNoticiasFiltro());
            filtros.setUenList(getUensFiltro());
            filtros.setRolList(getRolesFiltro());
            filtros.setUsuarioCreacionList(getUCreacionFiltro());
            filtros.setUsuarioActualizacionList(getUActualizacionFiltro());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return filtros;
    }

    @Override
    public Iterable<Uen> getUens() {
        List<Uen> uens = new ArrayList<>();
        Uen uen;
        try {
            StoredProcedureQuery procedure = em.createStoredProcedureQuery("PKG_NOTICIAS.GET_UENS");
            procedure.registerStoredProcedureParameter("cursor_out", void.class, ParameterMode.REF_CURSOR);
            boolean isOK = procedure.execute();
            if (isOK) {
                List<Object[]> cursor = (List<Object[]>) procedure.getResultList();
                for (Object[] row : cursor) {
                    uen = new Uen();
                    int pos = -1;
                    uen.setId(row[++pos] == null ? null : ((BigDecimal) row[pos]).intValue());
                    uen.setNombre(row[++pos] == null ? null : ((String) row[pos]));
                    uens.add(uen);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return uens;
    }

    private List<FiltroObj> getUensFiltro() {
        List<FiltroObj> uens = new ArrayList<>();
        FiltroObj uen;
        try {
            StoredProcedureQuery procedure = em.createStoredProcedureQuery("PKG_NOTICIAS.GET_UENS");
            procedure.registerStoredProcedureParameter("cursor_out", void.class, ParameterMode.REF_CURSOR);
            boolean isOK = procedure.execute();
            if (isOK) {
                List<Object[]> cursor = (List<Object[]>) procedure.getResultList();
                for (Object[] row : cursor) {
                    uen = new FiltroObj();
                    int pos = -1;
                    uen.setIntKey(row[++pos] == null ? null : ((BigDecimal) row[pos]).intValue());
                    uen.setDescripcion(row[++pos] == null ? null : ((String) row[pos]));
                    uens.add(uen);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return uens;
    }

    @Override
    public Iterable<Uen> getUensById(String idUens) {
        List<Uen> uens = new ArrayList<>();
        Uen uen;
        try {
            StoredProcedureQuery procedure = em.createStoredProcedureQuery("PKG_NOTICIAS.GET_UENS_BY_ID");
            procedure.registerStoredProcedureParameter("p_id_uens", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("cursor_out", void.class, ParameterMode.REF_CURSOR);
            procedure.setParameter("p_id_uens", idUens);
            boolean isOK = procedure.execute();
            if (isOK) {
                List<Object[]> cursor = (List<Object[]>) procedure.getResultList();
                for (Object[] row : cursor) {
                    uen = new Uen();
                    int pos = -1;
                    uen.setId(row[++pos] == null ? null : ((BigDecimal) row[pos]).intValue());
                    uen.setNombre(row[++pos] == null ? null : ((String) row[pos]));
                    uens.add(uen);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return uens;
    }

    @Override
    public boolean updateUens(NoticiaRequest req) {
        try {
            List<String> result = new ArrayList<>();
            String uens = "";
            if (req.getNoticia().getUenList() != null) {
                for (Uen u : req.getNoticia().getUenList()) {
                    result.add(u.getId().toString());
                }
                uens = String.join(",", result);
            }
            StoredProcedureQuery procedure = em.createStoredProcedureQuery("PKG_NOTICIAS.UPDATE_UENS");
            procedure.registerStoredProcedureParameter("p_id_uens", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_id_noticia", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_id_usuario", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("msg_out", String.class, ParameterMode.OUT);
            procedure.setParameter("p_id_uens", uens);
            procedure.setParameter("p_id_noticia", req.getNoticia().getIdNoticia());
            procedure.setParameter("p_id_usuario", req.getIdUsuario());
            procedure.execute();
            return procedure.getOutputParameterValue("msg_out").toString().equals("OK");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public Iterable<Rol> getRoles() {
        List<Rol> roles = new ArrayList<>();
        Rol rol;
        try {
            StoredProcedureQuery procedure = em.createStoredProcedureQuery("PKG_NOTICIAS.GET_ROLES");
            procedure.registerStoredProcedureParameter("cursor_out", void.class, ParameterMode.REF_CURSOR);
            boolean isOK = procedure.execute();
            if (isOK) {
                List<Object[]> cursor = (List<Object[]>) procedure.getResultList();
                for (Object[] row : cursor) {
                    rol = new Rol();
                    int pos = -1;
                    rol.setId(row[++pos] == null ? null : ((BigDecimal) row[pos]).intValue());
                    rol.setNombreRol(row[++pos] == null ? null : ((String) row[pos]));
                    rol.setClave(row[++pos] == null ? null : ((String) row[pos]));
                    roles.add(rol);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return roles;
    }

    private List<FiltroObj> getRolesFiltro() {
        List<FiltroObj> roles = new ArrayList<>();
        FiltroObj rol;
        try {
            StoredProcedureQuery procedure = em.createStoredProcedureQuery("PKG_NOTICIAS.GET_ROLES");
            procedure.registerStoredProcedureParameter("cursor_out", void.class, ParameterMode.REF_CURSOR);
            boolean isOK = procedure.execute();
            if (isOK) {
                List<Object[]> cursor = (List<Object[]>) procedure.getResultList();
                for (Object[] row : cursor) {
                    rol = new FiltroObj();
                    int pos = -1;
                    rol.setIntKey(row[++pos] == null ? null : ((BigDecimal) row[pos]).intValue());
                    rol.setDescripcion(row[++pos] == null ? null : ((String) row[pos]));
                    rol.setStrKey(row[++pos] == null ? null : ((String) row[pos]));
                    roles.add(rol);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return roles;
    }

    @Override
    public boolean updateRoles(NoticiaRequest req) {
        try {
            List<String> result = new ArrayList<>();
            String roles = "";
            if (req.getNoticia().getRolList() != null) {
                for (Rol r : req.getNoticia().getRolList()) {
                    result.add(r.getId().toString());
                }
                roles = String.join(",", result);
            }
            StoredProcedureQuery procedure = em.createStoredProcedureQuery("PKG_NOTICIAS.UPDATE_ROLES");
            procedure.registerStoredProcedureParameter("p_id_roles", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_id_noticia", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_id_usuario", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("msg_out", String.class, ParameterMode.OUT);
            procedure.setParameter("p_id_roles", roles);
            procedure.setParameter("p_id_noticia", req.getNoticia().getIdNoticia());
            procedure.setParameter("p_id_usuario", req.getIdUsuario());
            procedure.execute();
            return procedure.getOutputParameterValue("msg_out").toString().equals("OK");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public Iterable<Rol> getRolesById(String idRoles) {
        List<Rol> roles = new ArrayList<>();
        Rol rol;
        try {
            StoredProcedureQuery procedure = em.createStoredProcedureQuery("PKG_NOTICIAS.GET_ROLES_BY_ID");
            procedure.registerStoredProcedureParameter("p_id_roles", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("cursor_out", void.class, ParameterMode.REF_CURSOR);
            procedure.setParameter("p_id_roles", idRoles);
            boolean isOK = procedure.execute();
            if (isOK) {
                List<Object[]> cursor = (List<Object[]>) procedure.getResultList();
                for (Object[] row : cursor) {
                    rol = new Rol();
                    int pos = -1;
                    rol.setId(row[++pos] == null ? null : ((BigDecimal) row[pos]).intValue());
                    rol.setNombreRol(row[++pos] == null ? null : ((String) row[pos]));
                    rol.setClave(row[++pos] == null ? null : ((String) row[pos]));
                    roles.add(rol);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return roles;
    }

    @Override
    public boolean orderNoticia(NoticiaRequest req) {
        try {
            StoredProcedureQuery procedure = em.createStoredProcedureQuery("PKG_NOTICIAS.ORDER_NOTICIA");
            procedure.registerStoredProcedureParameter("p_old_order", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_new_order", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("msg_out", String.class, ParameterMode.OUT);
            procedure.setParameter("p_old_order", req.getOldOrder());
            procedure.setParameter("p_new_order", req.getNewOrder());
            procedure.execute();
            return procedure.getOutputParameterValue("msg_out").toString().equals("OK");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public NoticiaResponse insertNoticia(NoticiaRequest req) {
        OracleCallableStatement ocs = null;
        oracle.jdbc.OracleConnection oraConnection = null;
        NoticiaResponse res = new NoticiaResponse();
        try {
            if (null != req.getNoticia()) {
                if (req.getNoticia().getNombreFtp() == null) {
                    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("ddMMyyyyHHss");
                    String fileName = req.getNoticia().getNombre().substring(0, req.getNoticia().getNombre().lastIndexOf(".")).trim();
                    String fileExt = req.getNoticia().getNombre().substring(req.getNoticia().getNombre().lastIndexOf(".")).trim();
                    String fileNameFtp = fileName + " - " + sdf.format(new java.util.Date()) + fileExt;
                    req.getNoticia().setNombreFtp(fileNameFtp);
                    if (!UploadFileRepository.upload(new ByteArrayInputStream(req.getNoticia().getFile()), env.concat(req.getNoticia().getRuta()), req.getNoticia().getNombreFtp(), host, port, user, pass)) {
                        res.setOutMessage("FTP-ERROR");
                        return res;
                    }
                }
                oraConnection = jdbcTemplate.getDataSource().getConnection().unwrap(OracleConnection.class);
                Struct noticia = objectToStruct(oraConnection, req.getNoticia());
                ocs = (OracleCallableStatement) oraConnection.prepareCall("{call pkg_noticias.insert_noticia(?,?,?,?)}");
                ocs.setObject("p_noticia", noticia);
                ocs.setObject("p_id_usuario", req.getIdUsuario());
                ocs.registerOutParameter("msg_out", Types.VARCHAR);
                ocs.registerOutParameter("value_out", Types.INTEGER);
                ocs.execute();
                res.setOutMessage(ocs.getString("msg_out"));
                res.setOutValue(ocs.getInt("value_out"));
                log.debug("message:" + res.getOutMessage());

            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            res.setOutMessage("INSERT-ERROR");
        } finally {
            try {
                if (oraConnection != null) {
                    oraConnection.close();
                }
                if (ocs != null) {
                    ocs.close();
                }
            } catch (SQLException e) {
                log.error(e);
            }
        }
        return res;
    }

    @Override
    public NoticiaResponse updateNoticia(NoticiaRequest req) {
        OracleCallableStatement ocs = null;
        oracle.jdbc.OracleConnection oraConnection = null;
        NoticiaResponse res = new NoticiaResponse();
        try {
            if (null != req.getNoticia()) {
                if (req.getNoticia().getFile() != null) {
                    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("ddMMyyyyHHss");
                    String fileName = req.getNoticia().getNombre().substring(0, req.getNoticia().getNombre().lastIndexOf(".")).trim();
                    String fileExt = req.getNoticia().getNombre().substring(req.getNoticia().getNombre().lastIndexOf(".")).trim();
                    String fileNameFtp = fileName + " - " + sdf.format(new java.util.Date()) + fileExt;
                    req.getNoticia().setNombreFtp(fileNameFtp);
                    UploadFileRepository.upload(new ByteArrayInputStream(req.getNoticia().getFile()), env.concat(req.getNoticia().getRuta()), fileNameFtp, host, port, user, pass);
                }
                oraConnection = jdbcTemplate.getDataSource().getConnection().unwrap(OracleConnection.class);
                Struct noticia = objectToStruct(oraConnection, req.getNoticia());
                ocs = (OracleCallableStatement) oraConnection.prepareCall("{call pkg_noticias.update_noticia(?,?,?)}");
                ocs.setObject("p_noticia", noticia);
                ocs.setObject("p_id_usuario", req.getIdUsuario());
                ocs.registerOutParameter("msg_out", Types.VARCHAR);
                ocs.execute();
                res.setOutMessage(ocs.getString("msg_out"));
                log.debug("message:" + res.getOutMessage());
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                if (oraConnection != null) {
                    oraConnection.close();
                }
                if (ocs != null) {
                    ocs.close();
                }
            } catch (SQLException e) {
                log.error(e);
            }
        }
        return res;
    }

    @Override
    public boolean deleteNoticia(String idUsuario, Integer idNoticia) {
        try {
            StoredProcedureQuery procedure = em.createStoredProcedureQuery("PKG_NOTICIAS.DELETE_NOTICIA");
            procedure.registerStoredProcedureParameter("P_ID_USUARIO", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_ID_NOTICIA", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("MSG_OUT", String.class, ParameterMode.OUT);
            procedure.setParameter("P_ID_USUARIO", idUsuario);
            procedure.setParameter("P_ID_NOTICIA", idNoticia);
            procedure.execute();
            return procedure.getOutputParameterValue("MSG_OUT").toString().equals("OK");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public boolean pauseNoticia(Integer idNoticia, Integer estatus) {
        try {
            StoredProcedureQuery procedure = em.createStoredProcedureQuery("PKG_NOTICIAS.PAUSE_NOTICIA");
            procedure.registerStoredProcedureParameter("P_ID_NOTICIA", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_TIENE_VIGENCIA", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("MSG_OUT", String.class, ParameterMode.OUT);
            procedure.setParameter("P_ID_NOTICIA", idNoticia);
            procedure.setParameter("P_TIENE_VIGENCIA", estatus);
            procedure.execute();
            return procedure.getOutputParameterValue("MSG_OUT").toString().equals("OK");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

    private Struct objectToStruct(oracle.jdbc.OracleConnection oraConnection, NoticiaHome noticia) throws SQLException {
        log.debug(" **** objectToStruct *** ini:" + (new java.util.Date(System.currentTimeMillis())));
        Struct structureItem = null;
        if (noticia != null) {
            try {
                DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                Date fechaIniVig = new java.sql.Date(((java.util.Date) df.parse(noticia.getFechaInicioVigencia())).getTime());
                Date fechaFinVig = new java.sql.Date(((java.util.Date) df.parse(noticia.getFechaFinVigencia())).getTime());
                List<Integer> result = new ArrayList<>();
                String roles = "";
                if (noticia.getRolList() != null) {
                    for (Rol r : noticia.getRolList()) {
                        result.add(r.getId());
                    }
                    Collections.sort(result);
                    roles = result.stream().map(String::valueOf).collect(Collectors.joining(","));
                }
                result = new ArrayList<>();
                String uens = "";
                if (noticia.getUenList() != null) {
                    for (Uen u : noticia.getUenList()) {
                        result.add(u.getId());
                    }
                    Collections.sort(result);
                    uens = result.stream().map(String::valueOf).collect(Collectors.joining(","));
                }
                Object[] objItem = new Object[]{
                    noticia.getIdNoticia() == null ? 0 : noticia.getIdNoticia(),
                    noticia.getIdIdioma() == null ? "" : noticia.getIdIdioma(),
                    noticia.getClave() == null ? "" : noticia.getClave().toUpperCase(),
                    noticia.getIdTipoNoticia() == null ? 0 : noticia.getIdTipoNoticia(),
                    uens,
                    roles,
                    noticia.getNombre() == null ? "" : noticia.getNombre(),
                    noticia.getDescripcion() == null ? "" : noticia.getDescripcion(),
                    noticia.getRuta() == null ? "" : noticia.getRuta().concat(noticia.getNombreFtp()),
                    noticia.getDuracion() == null ? 0 : noticia.getDuracion() * 1000,
                    noticia.getContenido() == null ? "" : noticia.getContenido(),
                    noticia.getEnlace() == null ? "" : noticia.getEnlace(),
                    noticia.getOrden() == null ? 0 : noticia.getOrden(),
                    noticia.getTieneVigencia() == null ? 0 : noticia.getTieneVigencia(),
                    fechaIniVig,
                    fechaFinVig,
                    noticia.getUsuarioCreacion() == null ? 0 : noticia.getUsuarioCreacion()
                };
                structureItem = oraConnection.createStruct("NOTICIA", objItem);
            } catch (SQLException e) {
                log.debug(" **** objectToStruct *** " + e);
            } catch (ParseException ex) {
                Logger.getLogger(NoticiasServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return structureItem;
    }

    private List<FiltroObj> getNoticiasFiltro() {
        List<FiltroObj> noticias = new ArrayList<>();
        FiltroObj noticia;
        try {
            StoredProcedureQuery procedure = em.createStoredProcedureQuery("PKG_NOTICIAS.GET_NOTICIAS_HISTORICO");
            procedure.registerStoredProcedureParameter("cursor_out", void.class, ParameterMode.REF_CURSOR);
            boolean isOK = procedure.execute();
            if (isOK) {
                List<Object[]> cursor = (List<Object[]>) procedure.getResultList();
                for (Object[] row : cursor) {
                    noticia = new FiltroObj();
                    noticia.setIntKey(row[0] == null ? null : ((BigDecimal) row[0]).intValue());
                    noticia.setDescripcion(row[4] == null ? null : ((BigDecimal) row[0]).intValue() + " - " + ((String) row[4]));
                    noticias.add(noticia);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return noticias;
    }

    private List<FiltroObj> getUCreacionFiltro() {
        List<FiltroObj> us = new ArrayList<>();
        FiltroObj u;
        try {
            StoredProcedureQuery procedure = em.createStoredProcedureQuery("PKG_NOTICIAS.GET_US_CREACION_HIST");
            procedure.registerStoredProcedureParameter("cursor_out", void.class, ParameterMode.REF_CURSOR);
            boolean isOK = procedure.execute();
            if (isOK) {
                List<Object[]> cursor = (List<Object[]>) procedure.getResultList();
                for (Object[] row : cursor) {
                    u = new FiltroObj();
                    int pos = -1;
                    u.setStrKey(row[++pos] == null ? null : ((String) row[pos]));
                    u.setDescripcion(row[++pos] == null ? null : ((String) row[pos]));
                    us.add(u);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return us;
    }

    private List<FiltroObj> getUActualizacionFiltro() {
        List<FiltroObj> us = new ArrayList<>();
        FiltroObj u;
        try {
            StoredProcedureQuery procedure = em.createStoredProcedureQuery("PKG_NOTICIAS.GET_US_ACT_HIST");
            procedure.registerStoredProcedureParameter("cursor_out", void.class, ParameterMode.REF_CURSOR);
            boolean isOK = procedure.execute();
            if (isOK) {
                List<Object[]> cursor = (List<Object[]>) procedure.getResultList();
                for (Object[] row : cursor) {
                    u = new FiltroObj();
                    int pos = -1;
                    u.setStrKey(row[++pos] == null ? null : ((String) row[pos]));
                    u.setDescripcion(row[++pos] == null ? null : ((String) row[pos]));
                    us.add(u);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return us;
    }
}
