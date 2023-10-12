/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.almacen.service;

import com.metalsa.almacen.pojo.Almacen;
import com.metalsa.aprobacion.model.Usuario;
import com.metalsa.inventarioFisico.AlmacenInvFisRequest;
import com.metalsa.inventarioFisico.FuenteInvFisRequest;
import com.metalsa.inventarioFisico.InventarioFisicoRequest;
import com.metalsa.inventarioFisico.UsuarioInvFisRequest;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

/**
 *
 * @author hector.gutierrez02
 */
@Log4j
@Service
public class InventarioFisicoServiceImpl<T, ID extends Serializable> implements InventarioFisicoService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Iterable<Almacen> getAlmacenesPorUen(int uen) {
        List<Almacen> listAlmacen = new ArrayList();
        try {
            listAlmacen = em.createNativeQuery("select  distinct a.id_uen, b.id_almacen\n"
                    + "from    LOCALIZACIONES_POR_UEN_CIA a,\n"
                    + "        NVC_VM_OA_ALMACEN b\n"
                    + "where   a.id_uen = ?1 \n"
                    + "and     a.id_uen = b.id_uen\n"
                    + "and     a.estatus = 1 order by b.id_almacen").
                    setParameter(1, uen).getResultList();
        } catch (Exception e) {
            log.error("error en : despachar :" + e.getMessage());
        }
        return listAlmacen;
    }

    @Override
    public Iterable<AlmacenInvFisRequest> getAlmacenes() {
        List<AlmacenInvFisRequest> listAlmacen = new ArrayList();
        try {
            listAlmacen = em.createNativeQuery("SELECT DISTINCT ID_UEN, ID_LOCALIZACION, ID_ALMACEN\n"
                    + "FROM ICOM_LOC_WAREHOUSES\n"
                    + "ORDER BY ID_UEN, ID_LOCALIZACION, ID_ALMACEN").getResultList();
        } catch (Exception e) {
            log.error("error en : despachar :" + e.getMessage());
        }
        return listAlmacen;
    }

    @Override
    public Iterable<Usuario> geUsuariosActivos() {
        List<Usuario> listUsuario = new ArrayList();
        try {
            listUsuario = em.createNativeQuery("SELECT u.id_usuario,u.NOMBRE_USUARIO\n"
                    + "           FROM nvc_vm_per_all_people_f h, usuario u\n"
                    + "          WHERE h.person_id = u.id_empleado\n"
                    + "                AND NVL (h.effective_start_date, SYSDATE) <= SYSDATE\n"
                    + "                AND NVL (h.effective_end_date, SYSDATE) >= SYSDATE\n"
                    + "                AND h.current_employee_flag IS NOT NULL\n"
                    + "                AND h.current_employee_flag = 'Y'\n"
                    + "          order by u.NOMBRE_USUARIO").getResultList();
        } catch (Exception e) {
            log.debug(e);
            e.printStackTrace();
        }
        return listUsuario;
    }

    @Override
    public List<InventarioFisicoRequest> getAllBlockInventarioFisico() {
        List<Object[]> listInvFis = new ArrayList();
        List<InventarioFisicoRequest> invFis = new ArrayList<>();
        InventarioFisicoRequest ObjRequest = null;
        try {
            listInvFis = em.createNativeQuery("SELECT U.ACTIVO, U.ID_UEN, F.FUENTE, US.ID_USUARIO, AL.ID_ALMACEN \n"
                    + "FROM TBL_UEN_INV_FISICO U\n"
                    + "LEFT OUTER JOIN TBL_FUENTE_INV_FISICO F ON U.ID_UEN=F.ID_UEN\n"
                    + "LEFT OUTER JOIN TBL_USUARIO_INV_FISICO US ON US.ID_UEN=U.ID_UEN\n"
                    + "LEFT OUTER JOIN TBL_ALMACEN_INV_FISICO AL ON AL.ID_UEN=U.ID_UEN\n"
                    + "ORDER BY U.ID_UEN").getResultList();
        } catch (Exception e) {
            log.debug(e);
            e.printStackTrace();
        }

        int id_uen_aux = 0;
        for (Object[] obj : listInvFis) {
            int activo = ((BigDecimal) obj[0]).intValue();
            int id_uen = ((BigDecimal) obj[1]).intValue();
            String sfuente = String.valueOf(obj[2]);
            String sUsuario = String.valueOf(obj[3]);
            String sAlmacen = String.valueOf(obj[4]);

            if (id_uen_aux != id_uen) {
                ObjRequest = new InventarioFisicoRequest();
                Map<String, String> uen = new HashMap<>();
                uen.put("id_uen", String.valueOf(id_uen));
                uen.put("uen_desc", "");
                List<FuenteInvFisRequest> fuentes = new ArrayList<>();
                FuenteInvFisRequest fuente = new FuenteInvFisRequest();
                fuente.setTipo(sfuente);
                fuente.setDesc("");
                fuentes.add(fuente);
                List<UsuarioInvFisRequest> usuarios = new ArrayList<>();
                UsuarioInvFisRequest user = new UsuarioInvFisRequest();
                user.setId_usuario(sUsuario);
                user.setNombre_usuario("");
                usuarios.add(user);
                List<AlmacenInvFisRequest> AlmacenList = new ArrayList<>();
                if (sAlmacen != null && !sAlmacen.equals("null")) {
                    AlmacenInvFisRequest Almacen = new AlmacenInvFisRequest();
                    Almacen.setId_uen(id_uen);
                    Almacen.setId_almacen(Integer.parseInt(sAlmacen));
                    AlmacenList.add(Almacen);
                }
                ObjRequest.setUen(uen);
                ObjRequest.setActivo(activo);
                ObjRequest.setFuentes(fuentes);
                ObjRequest.setUsuarios(usuarios);
                ObjRequest.setAlmacen(AlmacenList);
                invFis.add(ObjRequest);
                id_uen_aux = id_uen;
            } else {
                FuenteInvFisRequest fuente = new FuenteInvFisRequest();
                fuente.setTipo(sfuente);
                fuente.setDesc("");
                UsuarioInvFisRequest user = new UsuarioInvFisRequest();
                user.setId_usuario(sUsuario);
                user.setNombre_usuario("");
                if (ObjRequest.getFuentes().indexOf(fuente) == -1) {
                    ObjRequest.getFuentes().add(fuente);
                }
                if (ObjRequest.getUsuarios().indexOf(user) == -1) {
                    ObjRequest.getUsuarios().add(user);
                }
                if (sAlmacen != null && !sAlmacen.equals("null")) {
                    AlmacenInvFisRequest Almacen = new AlmacenInvFisRequest();
                    Almacen.setId_uen(id_uen);
                    Almacen.setId_almacen(Integer.parseInt(sAlmacen));
                    if (ObjRequest.getAlmacen().indexOf(Almacen) == -1) {
                        ObjRequest.getAlmacen().add(Almacen);
                    }
                }
            }
        }
        return invFis;
    }

    @Override
    public String insertBloqueoInventario(InventarioFisicoRequest request) {
        String result = "";
        try {
            int iId_uen;
            String sFuentes = "";
            String sUsuario = "";
            String sAlmacen = "";

            iId_uen = Integer.parseInt(request.getUen().get("id_uen"));

            for (FuenteInvFisRequest obj : request.getFuentes()) {
                sFuentes = sFuentes + obj.getTipo() + ",";
            }
            for (UsuarioInvFisRequest obj : request.getUsuarios()) {
                sUsuario = sUsuario + obj.getId_usuario() + ",";
            }
            for (AlmacenInvFisRequest obj : request.getAlmacen()) {
                sAlmacen = sAlmacen + obj.getId_almacen() + ",";
            }
            if (!sFuentes.equals("")) {
                sFuentes = sFuentes.substring(0, sFuentes.length() - 1);
            }
            if (!sUsuario.equals("")) {
                sUsuario = sUsuario.substring(0, sUsuario.length() - 1);
            }
            if (!sAlmacen.equals("")) {
                sAlmacen = sAlmacen.substring(0, sAlmacen.length() - 1);
            }
            StoredProcedureQuery procedure = em.createStoredProcedureQuery("PKG_INVENTARIO_FISICO.CREA_BLOCK_INVFIS");
            procedure.registerStoredProcedureParameter("P_ACTIVO", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_ID_UEN", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_FUENTES", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_USUARIOS", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_ALMACEN", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_OPERACION", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_RESULT", String.class, ParameterMode.OUT);
            procedure.setParameter("P_ACTIVO", request.getActivo());
            procedure.setParameter("P_ID_UEN", iId_uen);
            procedure.setParameter("P_FUENTES", sFuentes);
            procedure.setParameter("P_USUARIOS", sUsuario);
            procedure.setParameter("P_ALMACEN", sAlmacen);
            procedure.setParameter("P_OPERACION", "I");
            procedure.execute();
            result = procedure.getOutputParameterValue("P_RESULT").toString();
        } catch (Exception e) {
            log.debug(e);
            result = e.getMessage();
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String EliminaBloqueoInventario(InventarioFisicoRequest request) {
        String result = "";
        try {
            int iId_uen;
            String sFuentes = "";
            String sUsuario = "";
            String sAlmacen = "";

            iId_uen = Integer.parseInt(request.getUen().get("id_uen"));

            StoredProcedureQuery procedure = em.createStoredProcedureQuery("PKG_INVENTARIO_FISICO.CREA_BLOCK_INVFIS");
            procedure.registerStoredProcedureParameter("P_ACTIVO", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_ID_UEN", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_FUENTES", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_USUARIOS", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_ALMACEN", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_OPERACION", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_RESULT", String.class, ParameterMode.OUT);
            procedure.setParameter("P_ACTIVO", request.getActivo());
            procedure.setParameter("P_ID_UEN", iId_uen);
            procedure.setParameter("P_FUENTES", sFuentes);
            procedure.setParameter("P_USUARIOS", sUsuario);
            procedure.setParameter("P_ALMACEN", sAlmacen);
            procedure.setParameter("P_OPERACION", "E");
            procedure.execute();
            result = procedure.getOutputParameterValue("P_RESULT").toString();
        } catch (Exception e) {
            log.debug(e);
            result = e.getMessage();
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String insertConfigBanner(String valor) {
        String result = "";
        try {
            StoredProcedureQuery procedure = em.createStoredProcedureQuery("PKG_INVENTARIO_FISICO.GUARDA_CONF_BANNER");
            procedure.registerStoredProcedureParameter("P_VALOR", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("P_RESULT", String.class, ParameterMode.OUT);
            procedure.setParameter("P_VALOR", valor);
            procedure.execute();
            result = procedure.getOutputParameterValue("P_RESULT").toString();
        } catch (Exception e) {
            log.debug(e);
            result = e.getMessage();
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String getConfigBanner() {
        String result = "";
        List<String> listBanner = new ArrayList();
        try {
            listBanner = em.createNativeQuery("SELECT CONFIG_PARAM \n"
                    + "FROM NVC_TBL_CONFIG_APP\n"
                    + "WHERE CONFIG_NAME = 'BANNER_DICIEMBRE'").getResultList();
        } catch (Exception e) {
            log.debug(e);
            e.printStackTrace();
        }
        if (!listBanner.isEmpty()) {
            result = listBanner.get(0);
        }
        return result;
    }
}
