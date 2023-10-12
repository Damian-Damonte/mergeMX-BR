/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.perfil.service;

import com.metalsa.aprobacion.repository.UsuarioRepository;
import com.metalsa.generales.model.NvcTblRolPerfil;
import com.metalsa.generales.model.Usuario;
import com.metalsa.perfil.pojo.DcIdioma;
import com.metalsa.perfil.pojo.Moneda;
import com.metalsa.perfil.pojo.CentroCosto;
import com.metalsa.perfil.pojo.OpcionPerfil;
import com.metalsa.perfil.pojo.PerfilRequest;
import com.metalsa.perfil.pojo.RolRequest;
import com.metalsa.perfil.pojo.UenWithCCDefault;
import com.metalsa.perfil.pojo.UnidadTiempo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;

import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author miguel.rdz
 * @param <T>
 * @param <ID>
 */
@Log4j
@Service
public class PerfilServiceImpl<T, ID extends Serializable> implements PerfilService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public List<UenWithCCDefault> getUensPorUsuario(String idUsuario, String idIdioma) {
        List<UenWithCCDefault> listUens = new ArrayList();
        try {
            List<Object[]> results = em.createNativeQuery("select o.id_uen, o.nombre_uen, o.currency_code, cu.id_cc, "
                    + "cc.codigo_cc || ' - ' || cc.nombre_cc as nombre_cc from uen_por_usuario u "
                    + "join nvc_tbl_organizaciones_h o on o.id_uen = u.id_uen and u.id_usuario = :idUsuario "
                    + "left join cc_por_usuario cu on cu.id_uen = u.id_uen and cu.id_usuario = u.id_usuario and cu.cc_default = 'S' "
                    + "left join nvc_vm_oa_cc cc on cc.id_uen = u.id_uen and cc.id_cc = cu.id_cc and cc.lenguaje = :idIdioma ")
                    .setParameter("idUsuario", idUsuario)
                    .setParameter("idIdioma", idIdioma)
                    .getResultList();
            results.stream().map((result) -> {
                UenWithCCDefault uen = new UenWithCCDefault();
                uen.setIdUen(Long.parseLong(result[0].toString()));
                uen.setNombre(result[1].toString());
                uen.setMoneda(result[2].toString());
                List<CentroCosto> ccSelected = new ArrayList();
                CentroCosto cc = new CentroCosto();
                if (result[3] != null && result[4] != null) {
                    cc.setIdCc(Long.parseLong(result[3].toString()));
                    cc.setNombreCc(result[4].toString());
                }
                ccSelected.add(cc);
                uen.setUenCc(ccSelected);
                uen.setCcDisponibles(getListCCDisponibles(uen.getIdUen(), idIdioma));
                return uen;
            }).forEachOrdered((uen) -> {
                listUens.add(uen);
            });
        } catch (Exception e) {
            log.error("error en : getUensPorUsuario :" + e.getMessage());
        }
        return listUens;
    }

    private List<CentroCosto> getListCCDisponibles(Long idUen, String idIdioma) {
        List<CentroCosto> listCc = new ArrayList();
        try {
            List<Object[]> results = em.createNativeQuery("SELECT CC.ID_CC, CC.CODIGO_CC || ' - ' || CC.NOMBRE_CC as NOMBRE_CC "
                    + "FROM NVC_VM_OA_CC CC WHERE CC.ID_UEN = :idUen AND LENGUAJE = :idIdioma ORDER BY CC.CODIGO_CC ")
                    .setParameter("idUen", idUen)
                    .setParameter("idIdioma", idIdioma)
                    .getResultList();
            results.stream().map((result) -> {
                CentroCosto cc = new CentroCosto();
                cc.setIdCc(Long.parseLong(result[0].toString()));
                cc.setNombreCc(result[1].toString());
                return cc;
            }).forEachOrdered((cc) -> {
                listCc.add(cc);
            });
        } catch (Exception e) {
            log.error("error en : getListCCDisponibles :" + e.getMessage());
        }
        return listCc;
    }

    @Override
    public List<UenWithCCDefault> getDefaultUenPorUsuario(String idUsuario) {
        List<UenWithCCDefault> uenDefault = new ArrayList();
        try {
            List<Object[]> results = em.createNativeQuery("select o.id_uen, o.nombre_uen, o.currency_code "
                    + "from uen_por_usuario u, nvc_tbl_organizaciones_h o "
                    + "where u.id_uen = o.id_uen and u.id_usuario = :idUsuario and u.uen_default = 'S'")
                    .setParameter("idUsuario", idUsuario)
                    .getResultList();
            results.stream().map((result) -> {
                UenWithCCDefault uen = new UenWithCCDefault();
                uen.setIdUen(Long.parseLong(result[0].toString()));
                uen.setNombre(result[1].toString());
                uen.setMoneda(result[2].toString());
                return uen;
            }).forEachOrdered((uen) -> {
                uenDefault.add(uen);
            });
        } catch (Exception e) {
            log.error("error en : getDefaultUenPorUsuario :" + e.getMessage());
        }
        return uenDefault;
    }

    @Override
    public List<DcIdioma> getIdiomas() {
        List<DcIdioma> listIdiomas = new ArrayList();
        try {
            List<Object[]> results = em.createNativeQuery("select * from dc_idioma").getResultList();
            results.stream().map((result) -> {
                DcIdioma lang = new DcIdioma();
                lang.setIdIdioma(result[0].toString());
                lang.setDescripcion(result[1].toString());
                lang.setCodigo(result[2].toString());
                return lang;
            }).forEachOrdered((lang) -> {
                listIdiomas.add(lang);
            });
        } catch (Exception e) {
            log.error("error en : getIdiomas :" + e.getMessage());
        }
        return listIdiomas;
    }

    @Override
    public List<Moneda> getMonedas() {
        List<Moneda> listMonedas = new ArrayList();
        try {
            List<Object[]> results = em.createNativeQuery("select * from nvc_tbl_monedas_h "
                    + "where active = 1 and id_moneda not in('MXN') order by "
                    + "case when id_moneda = 'USD' then 1 else "
                    + "case when id_moneda = 'MXP' then 2 else "
                    + "case when id_moneda = 'EUR' then 3 "
                    + "end end end, id_moneda").getResultList();
            results.stream().map((result) -> {
                Moneda mon = new Moneda();
                mon.setIdMoneda(result[0].toString());
                mon.setMoneda(result[1].toString());
                return mon;
            }).forEachOrdered((mon) -> {
                listMonedas.add(mon);
            });
        } catch (Exception e) {
            log.error("error en : getMonedas :" + e.getMessage());
        }
        return listMonedas;
    }

    @Override
    public List<Moneda> getMonedaDefault(String idUsuario) {
        List<Moneda> monedaDefault = new ArrayList();
        try {
            List<Object[]> results = em.createNativeQuery("select m.id_moneda, m.moneda "
                    + "from nvc_tbl_monedas_h m, usuario u where m.id_moneda = u.moneda_default "
                    + "and u.id_usuario = :idUsuario")
                    .setParameter("idUsuario", idUsuario)
                    .getResultList();
            results.stream().map((result) -> {
                Moneda mon = new Moneda();
                mon.setIdMoneda(result[0].toString());
                mon.setMoneda(result[1].toString());
                return mon;
            }).forEachOrdered((mon) -> {
                monedaDefault.add(mon);
            });
        } catch (Exception e) {
            log.error("error en : getMonedaDefault :" + e.getMessage());
        }
        return monedaDefault;
    }

    @Override
    public List<UnidadTiempo> getUnidadesTiempo(String idIdioma) {
        List<UnidadTiempo> listUnidades = new ArrayList();
        try {
            List<Object[]> results = em.createNativeQuery("select ut.bloque, ut.id_unidad_tiempo, ut.descripcion "
                    + "from nvc_tbl_cat_unidades_tiempo ut where ut.id_idioma = :idIdioma order by id_unidad_tiempo asc ")
                    .setParameter("idIdioma", idIdioma)
                    .getResultList();
            results.stream().map((result) -> {
                UnidadTiempo unidad = new UnidadTiempo();
                unidad.setBloque(Long.parseLong(result[0].toString()));
                unidad.setIdUnidadTiempo(Long.parseLong(result[1].toString()));
                unidad.setDescripcion(result[2].toString());
                return unidad;
            }).forEachOrdered((unidad) -> {
                listUnidades.add(unidad);
            });
        } catch (Exception e) {
            log.error("error en : getUnidadesTiempo :" + e.getMessage());
        }
        return listUnidades;
    }

    private List<UnidadTiempo> getUnidadTiempoById(String idIdioma, Long idUnidadTiempo) {
        List<UnidadTiempo> unidadTiempo = new ArrayList();
        try {
            List<Object[]> results = em.createNativeQuery("select ut.id_unidad_tiempo, ut.descripcion "
                    + "from nvc_tbl_cat_unidades_tiempo ut where ut.bloque = "
                    + "(select ut2.bloque from nvc_tbl_cat_unidades_tiempo ut2 where ut2.id_unidad_tiempo = :idUnidadTiempo) "
                    + "and ut.id_idioma = :idIdioma")
                    .setParameter("idIdioma", idIdioma)
                    .setParameter("idUnidadTiempo", idUnidadTiempo)
                    .getResultList();
            results.stream().map((result) -> {
                UnidadTiempo unidad = new UnidadTiempo();
                unidad.setIdUnidadTiempo(Long.parseLong(result[0].toString()));
                unidad.setDescripcion(result[1].toString());
                return unidad;
            }).forEachOrdered((unidad) -> {
                unidadTiempo.add(unidad);
            });
        } catch (Exception e) {
            log.error("error en : getUnidadTiempoById :" + e.getMessage());
        }
        return unidadTiempo;
    }

    @Override
    public List<OpcionPerfil> getOpcionesPerfil(String idUsuario, String idIdioma) {
        List<OpcionPerfil> listOpciones = new ArrayList();
        try {
            List<Object[]> results = em.createNativeQuery("select p.id_notificacion, p.valor, p.id_unidad_tiempo, p.activo "
                    + "from nvc_tbl_perfil p where p.id_usuario = :idUsuario order by p.id_notificacion asc ")
                    .setParameter("idUsuario", idUsuario)
                    .getResultList();
            results.stream().map((result) -> {
                OpcionPerfil opcion = new OpcionPerfil();
                opcion.setIdNotificacion(Long.parseLong(result[0].toString()));
                opcion.setValor(result[1] != null ? result[1].toString() : null);
                opcion.setUnidadTiempo(result[2] != null ? getUnidadTiempoById(idIdioma, Long.parseLong(result[2].toString())) : new ArrayList()); //<R43490>
                opcion.setActivo(result[3] != null ? Integer.parseInt(result[3].toString()) : null);
                return opcion;
            }).forEachOrdered((opcion) -> {
                listOpciones.add(opcion);
            });
        } catch (Exception e) {
            log.error("error en : getOpcionesPerfil :" + e.getMessage());
        }
        return listOpciones;
    }

    @Override
    public List<CentroCosto> getUserCcByRelacion(String idUsuario, String idIdioma, String tipoRelacion) {
        List<CentroCosto> listCc = new ArrayList();
        try {
            List<Object[]> results = em.createNativeQuery("select uen.organization_name, cu.id_cc, cc.codigo_cc || ' - ' || cc.nombre_cc as nombre_cc "
                    + "from cc_por_usuario cu left join nvc_vm_oa_cc cc "
                    + "on cu.id_cc = cc.id_cc and cu.id_uen = cc.id_uen "
                    + "join oa_uens uen on cu.id_uen = uen.organization_id "
                    + "where cu.id_usuario = :idUsuario "
                    + "and cu.tipo_de_relacion = :tipoRelacion and cc.lenguaje = :idIdioma order by uen.organization_name ")
                    .setParameter("idUsuario", idUsuario)
                    .setParameter("tipoRelacion", tipoRelacion)
                    .setParameter("idIdioma", idIdioma)
                    .getResultList();
            results.stream().map((result) -> {
                CentroCosto cc = new CentroCosto();
                cc.setNombreUen(result[0].toString());
                cc.setIdCc(Long.parseLong(result[1].toString()));
                cc.setNombreCc(result[2].toString());
                return cc;
            }).forEachOrdered((cc) -> {
                listCc.add(cc);
            });
        } catch (Exception e) {
            log.error("error en : getUserCcByRelacion :" + e.getMessage());
        }
        return listCc;
    }

    @Override
    public boolean saveConfiguracion(PerfilRequest req) {
        this.updateConfiguracion(req);
        if (req.getPerfilActivo().equals("SPX_REQUISITOR")) {
            this.updateDefaultCcValues(req);
        } else if (req.getPerfilActivo().equals("COMPRADOR")) {
            System.out.println("GUARDANDO COMPRADOR");
        }
        return false;
    }

    @Override
    public boolean saveNotificaciones(PerfilRequest req) {
        String out = "OK";
        try {
            for (OpcionPerfil opcion : req.getOpciones()) {
                StoredProcedureQuery procedure = em.createStoredProcedureQuery("NVC_PKG_PERFIL_SPX.UPDATE_NOTIFICACION");
                procedure.registerStoredProcedureParameter("P_ID_USUARIO", String.class, ParameterMode.IN);
                procedure.registerStoredProcedureParameter("P_VALOR", String.class, ParameterMode.IN);
                procedure.registerStoredProcedureParameter("P_ID_UNIDAD_TIEMPO", Long.class, ParameterMode.IN);
                procedure.registerStoredProcedureParameter("P_ID_NOTIFICACION", Long.class, ParameterMode.IN);
                procedure.registerStoredProcedureParameter("P_ACTIVO", Integer.class, ParameterMode.IN);
                procedure.registerStoredProcedureParameter("P_OUT", String.class, ParameterMode.OUT);
                procedure.setParameter("P_ID_USUARIO", req.getIdUsuario());
                procedure.setParameter("P_VALOR", opcion.getValor());
                procedure.setParameter("P_ID_UNIDAD_TIEMPO", opcion.getUnidadTiempo() != null && !opcion.getUnidadTiempo().isEmpty() ? opcion.getUnidadTiempo().get(0).getIdUnidadTiempo() : null);
                procedure.setParameter("P_ID_NOTIFICACION", opcion.getIdNotificacion());
                procedure.setParameter("P_ACTIVO", opcion.getValorActivo());
                procedure.execute();
                out = procedure.getOutputParameterValue("P_OUT").toString();
                log.debug("P_OUT->" + out);
            }
            return "OK".equals(out);
        } catch (Exception e) {
            log.debug(e.getLocalizedMessage(), e);
            log.error("error en : saveNotificaciones :" + e.getMessage());
            return false;
        }
    }

    private boolean updateConfiguracion(PerfilRequest req) {
        try {
            List<Moneda> monedaDefault = req.getDatos().getMonedaDefault();
            if (!req.getDatos().isMonedaDefaultActiva()) {
                monedaDefault = null;
            }
            StoredProcedureQuery procedure = em.createStoredProcedureQuery("NVC_PKG_PERFIL_SPX.UPDATE_CONFIGURACION");
            procedure.registerStoredProcedureParameter("P_ID_USUARIO", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_ID_IDIOMA", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_PERFIL", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_ID_UEN", Long.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_ID_MONEDA", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_OUT", String.class, ParameterMode.OUT);
            procedure.setParameter("P_ID_USUARIO", req.getIdUsuario());
            procedure.setParameter("P_ID_IDIOMA", req.getDatos().getIdiomaSel());
            procedure.setParameter("P_PERFIL", req.getPerfilActivo());
            procedure.setParameter("P_ID_UEN", req.getDatos().getUenDefault() != null ? req.getDatos().getUenDefault().get(0).getIdUen() : null);
            procedure.setParameter("P_ID_MONEDA", monedaDefault != null && !monedaDefault.isEmpty() ? monedaDefault.get(0).getIdMoneda() : null);
            procedure.execute();
            log.debug("P_OUT->" + procedure.getOutputParameterValue("P_OUT"));
            return procedure.getOutputParameterValue("P_OUT") == "OK";
        } catch (Exception e) {
            log.debug(e.getLocalizedMessage(), e);
            log.error("error en : updateConfiguracion :" + e.getMessage());
            return false;
        }
    }

    private boolean updateDefaultCcValues(PerfilRequest req) {
        String out = "OK";
        try {
            for (UenWithCCDefault newCcValue : req.getDatos().getUenCcEditados()) {
                StoredProcedureQuery procedure = em.createStoredProcedureQuery("NVC_PKG_PERFIL_SPX.UPDATE_CC_UEN_DEFAULT");
                procedure.registerStoredProcedureParameter("P_ID_USUARIO", String.class, ParameterMode.IN);
                procedure.registerStoredProcedureParameter("P_ID_UEN", Long.class, ParameterMode.IN);
                procedure.registerStoredProcedureParameter("P_ID_CC", Long.class, ParameterMode.IN);
                procedure.registerStoredProcedureParameter("P_OUT", String.class, ParameterMode.OUT);
                procedure.setParameter("P_ID_USUARIO", req.getIdUsuario());
                procedure.setParameter("P_ID_UEN", newCcValue.getIdUen());
                procedure.setParameter("P_ID_CC", newCcValue.getUenCc().get(0).getIdCc());
                procedure.execute();
                out = procedure.getOutputParameterValue("P_OUT").toString();
                log.debug("P_OUT->" + out);
            }
            return "OK".equals(out);
        } catch (Exception e) {
            log.debug(e.getLocalizedMessage(), e);
            log.error("error en : updateDefaultCcValues :" + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean insertRolPerfil(String idUsuario, RolRequest rolReq) {
        String out = "NOT OK";
        try {
            List<NvcTblRolPerfil> rolSol = rolReq.getRolSolicitado();
            if (rolSol != null) {
                StoredProcedureQuery procedure = em.createStoredProcedureQuery("NVC_PKG_PERFIL_SPX.INSERT_ROL_PERFIL");
                procedure.registerStoredProcedureParameter("P_ID_ROL", Integer.class, ParameterMode.IN);
                procedure.registerStoredProcedureParameter("P_ID_USUARIO", String.class, ParameterMode.IN);
                procedure.registerStoredProcedureParameter("P_OUT", String.class, ParameterMode.OUT);
                procedure.setParameter("P_ID_ROL", rolSol.get(0).getIdRol());
                procedure.setParameter("P_ID_USUARIO", idUsuario);
                procedure.execute();
                out = procedure.getOutputParameterValue("P_OUT").toString();
                log.debug("P_OUT->" + out);
                return "OK".equals(out);
            }
            return "OK".equals(out);
        } catch (Exception e) {
            log.debug(e.getLocalizedMessage(), e);
            log.error("error en : insertRolPerfil :" + e.getMessage());
            return "OK".equals(out);
        }
    }

    @Override
    public boolean updateGuiaPerfil(PerfilRequest req) {
        String out = "OK";
        try {
            StoredProcedureQuery procedure = em.createStoredProcedureQuery("NVC_PKG_PERFIL_SPX.UPDATE_ROL_PERFIL");
            procedure.registerStoredProcedureParameter("P_ID_USUARIO", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_OUT", String.class, ParameterMode.OUT);
            procedure.setParameter("P_ID_USUARIO", req.getIdUsuario());
            procedure.execute();
            out = procedure.getOutputParameterValue("P_OUT").toString();
            log.debug("P_OUT->" + out);
            return "OK".equals(out);
        } catch (Exception e) {
            log.debug(e.getLocalizedMessage(), e);
            log.error("error en : updateGuiaPerfil :" + e.getMessage());
            return false;
        }
    }

    @Override
    public Usuario getUsuario(String idUsuario) {
        Usuario user = new Usuario();
        try {
            user = (Usuario) em.createNamedQuery("Usuario.findById")
                    .setParameter("user", idUsuario).getSingleResult();
        } catch (Exception e) {
            log.error("error en : getUsuario :" + e.getMessage());
        }
        return user;
    }
}
