package com.metalsa.almacen.service;

import com.metalsa.almacen.model.Despacho;
import com.metalsa.almacen.model.DetalleDespacho;
import com.metalsa.almacen.pojo.Almacen;
import com.metalsa.almacen.pojo.DespachoRequest;
import com.metalsa.aprobacion.model.NvcTblOrganizacionesH;
import com.metalsa.recibos.model.Requisitor;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

/**
 *
 * @author miguel.rdz
 * @param <T>
 * @param <ID>
 */
@Log4j
@Service
public class DespachoServiceImpl<T, ID extends Serializable> implements DespachoService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Iterable<NvcTblOrganizacionesH> getUensConReserva(String idUsuario) {
        Iterable<NvcTblOrganizacionesH> listUenCc = null;
        try {
            listUenCc = em.createNamedQuery("NvcTblOrganizacionesH.getUensConReserva")
                    .setParameter("idUsuario", idUsuario).getResultList();
        } catch (Exception e) {
            log.error("error en : NvcTblOrganizacionesH.getUensConReserva :" + e.getMessage());
        }
        return listUenCc;
    }

    @Override
    public Iterable<Almacen> getAlmacenesPorUen(String idUsuario, String uens) {
        List<Almacen> listAlmacen = new ArrayList();
        try {
            List<Object[]> results = em.createNativeQuery("select distinct oaa.id_almacen, oaa.almacen, oaa.name "
                    + "from requisicion r "
                    + "inner join almacenes_para_despachador a on r.id_almacen = a.id_almacen and a.id_usuario = :idUsuario "
                    + "inner join nvc_vm_oa_almacen oaa on a.id_almacen = oaa.id_almacen "
                    + "inner join detalle_de_requisicion d on d.id_requisicion = r.id_requisicion "
                    + "inner join oa_uens ou on r.id_uen = ou.organization_id "
                    + "where d.estatus = 'APROBADA' and r.tipo_requisicion = 'INTERNA' and ou.organization_id in (:uens) "
                    + "order by oaa.name")
                    .setParameter("idUsuario", idUsuario)
                    .setParameter("uens", Arrays.asList(uens.split("\\s*,\\s*")))
                    .getResultList();
            results.stream().map((result) -> {
                Almacen a = new Almacen();
                a.setIdAlmacen(Integer.parseInt(result[0].toString()));
                a.setAlmacen(result[1].toString());
                a.setNombreAlmacen(result[2].toString());
                return a;
            }).forEachOrdered((a) -> {
                listAlmacen.add(a);
            });
        } catch (Exception e) {
            log.error("error en : getAlmacenesPorUen :" + e.getMessage());
        }
        return listAlmacen;
    }

    @Override
    public Iterable<Requisitor> getRequisitoresPorUen(String idUsuario, String uens) {
        List<Requisitor> listRequisitores = new ArrayList();
        try {
            List<Object[]> results = em.createNativeQuery("select distinct u.id_usuario, u.nombre_usuario "
                    + "from requisicion r "
                    + "inner join almacenes_para_despachador a on r.id_almacen = a.id_almacen and a.id_usuario = :idUsuario "
                    + "inner join detalle_de_requisicion d on d.id_requisicion = r.id_requisicion "
                    + "inner join usuario u on r.id_usuario = u.id_usuario "
                    + "inner join oa_uens ou on r.id_uen = ou.organization_id "
                    + "where d.estatus = 'APROBADA' and r.tipo_requisicion = 'INTERNA' and ou.organization_id in (:uens) "
                    + "order by u.nombre_usuario")
                    .setParameter("idUsuario", idUsuario)
                    .setParameter("uens", Arrays.asList(uens.split("\\s*,\\s*")))
                    .getResultList();
            results.stream().map((result) -> {
                Requisitor r = new Requisitor();
                r.setIdUsuario(result[0].toString());
                r.setNombreUsuario(result[1].toString());
                return r;
            }).forEachOrdered((r) -> {
                listRequisitores.add(r);
            });
        } catch (Exception e) {
            log.error("error en : getRequisitoresPorUen :" + e.getMessage());
        }
        return listRequisitores;
    }

    @Override
    public Iterable<Despacho> getDespachosPorUsuario(String idUsuario) {
        List<Despacho> listDespachos = new ArrayList();
        List<Object[]> resultset;
        String out = "OK";
        try {
            StoredProcedureQuery procedure = em.createStoredProcedureQuery("PKG_DESPACHOS_SPX.GET_DESPACHOS");
            procedure.registerStoredProcedureParameter("p_id_usuario", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_out", String.class, ParameterMode.OUT);
            procedure.registerStoredProcedureParameter("cursor_out", void.class, ParameterMode.REF_CURSOR);
            procedure.setParameter("p_id_usuario", idUsuario);
            if (procedure.execute()) {
                resultset = (List<Object[]>) procedure.getResultList();
                out = (String) procedure.getOutputParameterValue("p_out");
                resultset.stream().map((row) -> {
                    Despacho d = new Despacho();
                    int index = -1;
                    d.setNombreUen(row[++index] != null ? row[index].toString() : "");
                    d.setCodOrg(row[++index] != null ? row[index].toString() : "");
                    d.setNombreOrg(row[++index] != null ? d.getCodOrg().concat(" - ").concat(row[index].toString()) : "");
                    d.setIdRequisicion(Integer.parseInt(row[++index].toString()));
                    d.setIdItem(Integer.parseInt(row[++index].toString()));
                    d.setNombreRequisitor(row[++index] != null ? row[index].toString() : "");
                    d.setFechaRequerida(row[++index] != null ? row[index].toString() : "");
                    d.setDescripcionItem(row[++index] != null ? row[index].toString() : "");
                    return d;
                }).forEachOrdered((d) -> {
                    listDespachos.add(d);
                });
            }
            log.debug(out);
        } catch (Exception e) {
            log.error("error en : getDespachosPorUsuario :" + e.getMessage());
        }
        return listDespachos;
    }

    @Override
    public Iterable<Despacho> getDespachos(DespachoRequest req) {
        List<Despacho> listDespachos = new ArrayList();
        List<Object[]> resultset;
        String out = "OK";
        try {
            Locale loc;
            if (req.getIdIdioma().equals("US")) {
                loc = new Locale("en", "US");
            } else {
                loc = new Locale("es", "MX");
            }
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy", loc);
//            SimpleDateFormat parser = new SimpleDateFormat("yyyy-mm-dd", loc);
            DateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
            StoredProcedureQuery procedure = em.createStoredProcedureQuery("PKG_DESPACHOS_SPX.GET_DESPACHOS");
            procedure.registerStoredProcedureParameter("p_id_usuario", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_out", String.class, ParameterMode.OUT);
            procedure.registerStoredProcedureParameter("cursor_out", void.class, ParameterMode.REF_CURSOR);
            procedure.setParameter("p_id_usuario", req.getIdUsuario());
            if (procedure.execute()) {
                resultset = (List<Object[]>) procedure.getResultList();
                out = (String) procedure.getOutputParameterValue("p_out");
                resultset.stream().map((row) -> {
                    Despacho d = new Despacho();
                    int index = -1;
                    d.setNombreUen(row[++index] != null ? row[index].toString() : "");
                    d.setCodOrg(row[++index] != null ? row[index].toString() : "");
                    d.setNombreOrg(row[++index] != null ? d.getCodOrg().concat(" - ").concat(row[index].toString()) : "");
                    d.setIdRequisicion(Integer.parseInt(row[++index].toString()));
                    d.setIdItem(Integer.parseInt(row[++index].toString()));
                    d.setNombreRequisitor(row[++index] != null ? row[index].toString() : "");
                    try {
                        Date fechaReq = parser.parse(row[++index] != null ? row[index].toString() : "");
                        Date fechaAprob = parser.parse(row[++index] != null ? row[index].toString() : "");
                        d.setFechaRequerida(formatter.format(fechaReq));
                        d.setFechaAprobacion(formatter.format(fechaAprob));
                    } catch (ParseException e) {
                        System.out.println(e);
                        d.setFechaRequerida("");
                        d.setFechaAprobacion("");
                    }
                    d.setDescripcionItem(row[++index] != null ? row[index].toString() : "");
                    return d;
                }).forEachOrdered((d) -> {
                    listDespachos.add(d);
                });
            }
            log.debug(out);
        } catch (Exception e) {
            log.error("error en : getDespachos :" + e.getMessage());
        }
        return listDespachos;
    }

    @Override
    public Iterable<DetalleDespacho> getDetalleDespachos(DespachoRequest req) {
        List<DetalleDespacho> listDetDespachos = new ArrayList();
        List<Object[]> resultset;
        String out = "OK";
        try {
            Locale loc;
            if (req.getIdIdioma().equals("US")) {
                loc = new Locale("en", "US");
            } else {
                loc = new Locale("es", "MX");
            }
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy", loc);
            DateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
            StoredProcedureQuery procedure = em.createStoredProcedureQuery("PKG_DESPACHOS_SPX.GET_DETALLE_DESPACHOS");
            procedure.registerStoredProcedureParameter("p_requis", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_out", String.class, ParameterMode.OUT);
            procedure.registerStoredProcedureParameter("cursor_out", void.class, ParameterMode.REF_CURSOR);
            procedure.setParameter("p_requis", req.getRequisiciones());
            if (procedure.execute()) {
                resultset = (List<Object[]>) procedure.getResultList();
                out = (String) procedure.getOutputParameterValue("p_out");
                resultset.stream().map((row) -> {
                    DetalleDespacho d = new DetalleDespacho();
                    int index = -1;
                    d.setIdRequisicion(Integer.parseInt(row[++index].toString()));
                    d.setIdPartida(Integer.parseInt(row[++index].toString()));
                    d.setCodigoProducto(row[++index] != null ? row[index].toString() : "");
                    d.setDescripcionProducto(row[++index] != null ? row[index].toString() : "");
                    d.setPrecio(Double.parseDouble(row[++index].toString()));
                    try {
                        Date fechaAprob = parser.parse(row[++index] != null ? row[index].toString() : "");
                        d.setFechaAprobacion(formatter.format(fechaAprob));
                    } catch (ParseException e) {
                        System.out.println(e);
                        d.setFechaAprobacion("");
                    }
                    d.setSegmento1(row[++index] != null ? row[index].toString() : "");
                    d.setSegmento2(row[++index] != null ? row[index].toString() : "");
                    d.setSegmento3(row[++index] != null ? row[index].toString() : "");
                    d.setCantidadEntregada(row[++index] != null ? BigDecimal.valueOf(Double.parseDouble(row[index].toString())) : new BigDecimal(0));
                    d.setCantidadRequerida(row[++index] != null ? BigDecimal.valueOf(Double.parseDouble(row[index].toString())) : new BigDecimal(0));
                    d.setCantidadPendiente(row[++index] != null ? BigDecimal.valueOf(Double.parseDouble(row[index].toString())) : new BigDecimal(0));
                    d.setCantidadPorDespachar(d.getCantidadPendiente());
                    d.setReparable(row[++index] != null ? row[index].toString() : "");
                    d.setGarantia(row[++index] != null ? row[index].toString() : "");
                    d.setSubinventario(row[++index] != null ? row[index].toString() : "");
                    d.setLocator(row[++index] != null ? row[index].toString() : "");
                    d.setNumParteFabricante(row[++index] != null ? row[index].toString() : "");
                    d.setIdUsuario(row[++index] != null ? row[index].toString() : "");
                    return d;
                }).forEachOrdered((d) -> {
                    listDetDespachos.add(d);
                });
            }
            log.debug(out);
        } catch (Exception e) {
            log.error("error en : getDetalleDespachos :" + e.getMessage());
        }
        return listDetDespachos;
    }

    @Override
    public DetalleDespacho despachar(DetalleDespacho req) {
        Integer out = 1;
        try {
            StoredProcedureQuery procedure = em.createStoredProcedureQuery("PKG_DESPACHOS_SPX.DESPACHAR");
            procedure.registerStoredProcedureParameter("p_cantidad", BigDecimal.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_num_req", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_num_part", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_consig_flag", Integer.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_user_id", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_user_rec", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_language", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("p_return", Integer.class, ParameterMode.OUT);
            procedure.registerStoredProcedureParameter("p_folio", Integer.class, ParameterMode.OUT);
            procedure.setParameter("p_cantidad", req.getCantidadPorDespachar());
            procedure.setParameter("p_num_req", req.getIdRequisicion());
            procedure.setParameter("p_num_part", req.getIdPartida());
            procedure.setParameter("p_consig_flag", req.getConsigFlag());
            procedure.setParameter("p_user_id", req.getIdUsuario());
            procedure.setParameter("p_user_rec", req.getIdUsuarioRecibe());
            procedure.setParameter("p_language", req.getIdIdioma());
            procedure.execute();
            out = (Integer) procedure.getOutputParameterValue("p_return");
            if (out != -1) {
                Integer folio = (Integer) procedure.getOutputParameterValue("p_folio");
                if (folio != null) {
                    req.setFolio(folio);
                    req.setCantidadEntregada(req.getCantidadEntregada().add(req.getCantidadPorDespachar()));
                    req.setCantidadPorDespachaTem(req.getCantidadPorDespachar());
                    req.setCantidadPendiente(req.getCantidadPendiente().subtract(req.getCantidadPorDespachar()));
                    req.setCantidadPorDespachar(req.getCantidadPendiente());
                } else {
                    req.setMsgOut("falló al dar despacho");
                }
            } else {
                req.setMsgOut(out.toString());
            }
        } catch (Exception e) {
            log.error("error en : despachar :" + e.getMessage());
            req.setMsgOut("falló al dar despacho");
        }
        return req;
    }

    @Override
    public Iterable<Despacho> getPendDespachoByFilters(DespachoRequest req) {
        List<Despacho> listRequisPend = new ArrayList();
        try {
            Locale loc;
            if (req.getIdIdioma().equals("US")) {
                loc = new Locale("en", "US");
            } else {
                loc = new Locale("es", "MX");
            }
            String uens = req.getUens().stream().map(u -> String.valueOf(u.getId())).collect(Collectors.joining(","));
            String almacenes = req.getAlmacenes().stream().map(a -> String.valueOf(a.getIdAlmacen())).collect(Collectors.joining(","));
            String requisitores = req.getRequisitores().stream().map(r -> String.valueOf(r.getIdUsuario())).collect(Collectors.joining(","));

            List<Object[]> results = em.createNativeQuery("select ou.organization_name, oaa.almacen, d.id_requisicion, d.id_partida, d.id_producto, u.nombre_usuario, d.fecha_requerida "
                    + "from requisicion r "
                    + "inner join almacenes_para_despachador a on r.id_almacen = a.id_almacen and a.id_usuario = :idUsuario "
                    + "inner join nvc_vm_oa_almacen oaa on a.id_almacen = oaa.id_almacen "
                    + "inner join detalle_de_requisicion d on d.id_requisicion = r.id_requisicion "
                    + "inner join usuario u on r.id_usuario = u.id_usuario "
                    + "inner join oa_uens ou on r.id_uen = organization_id "
                    + "where d.estatus = 'APROBADA' and r.tipo_requisicion = 'INTERNA' "
                    //+ "--filtro uen "
                    + "and (0 = nvl2('" + uens + "', 1, 0) or (d.id_uen in (select trim(regexp_substr('" + uens + "', '[^,]+', 1, level)) from dual connect by instr('" + uens + "', ',', 1, level - 1) > 0))) "
                    //+ "--filtro item "
                    + "and (0 = nvl2(:idItem, 1, 0) or (d.id_producto = :idItem)) "
                    //+ "--filtro requisicion "
                    + "and (0 = nvl2(:idRequisicion, 1, 0) or (d.id_requisicion = :idRequisicion)) "
                    //+ "--filtro fecha requerida "
                    + "and (0 = nvl2(:fechaReqIni, 1, 0) or (trunc(d.fecha_requerida) >= to_date(:fechaReqIni, 'dd/mm/yyyy'))) "
                    + "and (0 = nvl2(:fechaReqFin, 1, 0) or (trunc(d.fecha_requerida) <= to_date(:fechaReqFin, 'dd/mm/yyyy'))) "
                    //+ "--filtro organizacion "
                    + "and (0 = nvl2('" + almacenes + "', 1, 0) or (a.id_almacen in (select trim(regexp_substr('" + almacenes + "', '[^,]+', 1, level)) from dual connect by instr('" + almacenes + "', ',', 1, level - 1) > 0))) "
                    //+ "--filtro descripcion "
                    + "and (0 = nvl2(:descripcion, 1, 0) or (d.descripcion_producto = :descripcion)) "
                    //+ "--filtro requisitor "
                    + "and (0 = nvl2('" + requisitores + "', 1, 0) or (r.id_usuario in (select trim(regexp_substr('" + requisitores + "', '[^,]+', 1, level)) from dual connect by instr('" + requisitores + "', ',', 1, level - 1) > 0))) "
                    //+ "--filtro fecha aprobacion "
                    + "and (0 = nvl2(:fechaApIni, 1, 0) or (trunc(d.fecha_de_aprobacion) >= to_date(:fechaApIni, 'dd/mm/yyyy'))) "
                    + "and (0 = nvl2(:fechaApFin, 1, 0) or (trunc(d.fecha_de_aprobacion) <= to_date(:fechaApFin, 'dd/mm/yyyy'))) "
                    + "group by ou.organization_name, oaa.almacen, d.id_requisicion, d.id_partida, d.id_producto, u.nombre_usuario, d.fecha_requerida "
                    + "order by d.id_requisicion, d.id_partida asc ")
                    .setParameter("idUsuario", req.getIdUsuario() != null ? req.getIdUsuario() : "")
                    .setParameter("idItem", req.getIdItem() != null ? req.getIdItem() : "")
                    .setParameter("idRequisicion", req.getIdRequisicion() != null ? req.getIdRequisicion() : "")
                    .setParameter("fechaReqIni", req.getFechaReqIni() != null ? req.getFechaReqIni() : "")
                    .setParameter("fechaReqFin", req.getFechaReqFin() != null ? req.getFechaReqFin() : "")
                    .setParameter("descripcion", req.getDescripcion() != null ? req.getDescripcion() : "")
                    .setParameter("fechaApIni", req.getFechaApIni() != null ? req.getFechaApIni() : "")
                    .setParameter("fechaApFin", req.getFechaApFin() != null ? req.getFechaApFin() : "")
                    .getResultList();
            results.stream().map((result) -> {
                Despacho d = new Despacho();
                d.setNombreUen(result[0].toString());
                d.setCodOrg(result[1].toString());
                d.setIdRequisicion(Integer.parseInt(result[2].toString()));
                d.setIdPartida(Integer.parseInt(result[3].toString()));
                d.setIdItem(Integer.parseInt(result[4].toString()));
                d.setNombreRequisitor(result[5].toString());
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy", loc);
                String strFechaReq = formatter.format((Date) result[6]);
                d.setFechaRequerida(strFechaReq);
                return d;
            }).forEachOrdered((d) -> {
                listRequisPend.add(d);
            });
        } catch (Exception e) {
            log.error("error en : getReqPendDespacho :" + e.getMessage());
        }
        return listRequisPend;
    }

    @Override
    public Integer cancelarDespacho(DetalleDespacho req, String razon) {
        Integer out;
        StoredProcedureQuery procedure = em.createStoredProcedureQuery("PKG_DESPACHOS_SPX.CANCEL_DESPACHO");
        procedure.registerStoredProcedureParameter("P_NUM_REQ", Integer.class, ParameterMode.IN);
        procedure.registerStoredProcedureParameter("P_PARTIDA", Integer.class, ParameterMode.IN);
        procedure.registerStoredProcedureParameter("P_USER_ID", String.class, ParameterMode.IN);
        procedure.registerStoredProcedureParameter("P_REASON", String.class, ParameterMode.IN);
        procedure.registerStoredProcedureParameter("P_RETURN", Integer.class, ParameterMode.OUT);

        procedure.setParameter("P_NUM_REQ", req.getIdRequisicion());
        procedure.setParameter("P_PARTIDA", req.getIdPartida());
        procedure.setParameter("P_USER_ID", req.getIdUsuario());
        procedure.setParameter("P_REASON", razon);
        procedure.execute();
        out = (Integer) procedure.getOutputParameterValue("P_RETURN");
        return out;
    }
}
