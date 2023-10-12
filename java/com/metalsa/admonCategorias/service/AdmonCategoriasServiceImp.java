/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.admonCategorias.service;

import com.metalsa.admonCategorias.pojo.ConfFamPojo;
import com.metalsa.aprobacion.model.NvcTblOrganizacionesH;
import com.metalsa.compradorCC.pojo.CategoriaPojo;
import com.metalsa.compradorCC.pojo.CompradorCcPojo;
import com.metalsa.compradorCC.pojo.RequestFiltros;
import com.metalsa.recibos.pojo.TipoRequisicion;
import com.metalsa.utils.Constants;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

/**
 *
 * @author jose.jimenez07
 */
@Log4j
@Service
public class AdmonCategoriasServiceImp implements AdmonCategoriasService {

    @PersistenceContext
    private EntityManager em;
    private final DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    public List<CategoriaPojo> getDatosByFiltros(RequestFiltros requestFiltros) {
        List<Object[]> results;
        results = tipoQuery(requestFiltros, Constants.TIPO_PESTANA_FAM);
        List<CategoriaPojo> listCategorias = new ArrayList();
        CompradorCcPojo comprador;
        try {

            for (Object[] result : results) {
                CategoriaPojo categoria = new CategoriaPojo();
                categoria.setId_familia(Integer.parseInt(result[0].toString()));
                categoria.setNombre_familia(result[1].toString());
                categoria.setId_uen(requestFiltros.getModelListaUensCc().get(0).getId()); // esto porque la uen no viene de manera explicita, ya que las uen se obtienen mediante rangos
                categoria.setTipoRequisicion(new TipoRequisicion());
                if (!listCategorias.stream().anyMatch(x -> x.getId_familia().equals(Integer.parseInt(result[0].toString())))) {
                    //bloque familias 
                    for (Object[] resultFamilias : results) {
                        String[] temIds = resultFamilias[2].toString().split("-");  //donde posicion 0 es padre y 1 es idfamilia
                        CategoriaPojo familia = new CategoriaPojo();
                        familia.setId_familia(Integer.parseInt(temIds[1]));
                        familia.setNombre_familia(resultFamilias[3].toString());
                        familia.setId_padre(Integer.parseInt(temIds[0]));
                        familia.setId_uen(requestFiltros.getModelListaUensCc().get(0).getId()); // esto porque la uen no viene de manera explicita, ya que las uen se obtienen mediante rangos
                        familia.setTipoRequisicion(new TipoRequisicion());
                        if (familia.getId_padre().equals(categoria.getId_familia()) && !categoria.getNodo().stream().anyMatch(x -> x.getId_familia().equals(familia.getId_familia()))) {
                            //subFamilias
                            for (Object[] resultSubFamilias : results) {
                                //Aui no debe evaluar
                                String[] temIdsSub = resultSubFamilias[4].toString().split("-");  //donde posicion 0 es padre y 1 es idfamilia
                                CategoriaPojo nodo = new CategoriaPojo();
                                nodo.setId_padre(temIdsSub[0] == null ? 0 : Integer.parseInt(temIdsSub[0]));
                                nodo.setId_familia(temIdsSub[1] == null ? 0 : Integer.parseInt(temIdsSub[1]));
                                nodo.setNombre_familia(resultSubFamilias[5] == null ? "-" : resultSubFamilias[5].toString());
                                nodo.setId_uen(requestFiltros.getModelListaUensCc().get(0).getId());  // esto porque la uen no viene de manera explicita, ya que las uen se obtienen mediante rangos
                                nodo.setCreation_date(resultSubFamilias[7] == null ? "-" : df.format(resultSubFamilias[7]));
                                /*Aqui no*/
                                //Aui tampoco
                                /*fecha exacta*/
                                nodo.setDate(resultSubFamilias[7] == null ? null : (Date) resultSubFamilias[7]);
                                /*fecha end*/
                                comprador = new CompradorCcPojo();     /// para administrador
                                comprador.setId(resultSubFamilias[8] == null ? "-" : resultSubFamilias[8].toString());
                                comprador.setNombre_comprador(resultSubFamilias[9] == null ? "-" : resultSubFamilias[9].toString());
                                nodo.setAdministrador(comprador);
                                nodo.setAdministradorFilter(resultSubFamilias[9] == null ? "-" : resultSubFamilias[9].toString());
                                /*Fuentes*/
                                nodo.setTipoRequisicion(new TipoRequisicion()); 
                                nodo.getTipoRequisicion().setSpot(resultSubFamilias[10] == null ? false : "1".equals(resultSubFamilias[10]));
                                nodo.getTipoRequisicion().setAlmacen(resultSubFamilias[11] == null ? false : "1".equals(resultSubFamilias[11]));
                                nodo.getTipoRequisicion().setProveedor(resultSubFamilias[12] == null ? false : "1".equals(resultSubFamilias[12]));
                                nodo.getTipoRequisicion().setValesFondo(resultSubFamilias[13] == null ? false : "1".equals(resultSubFamilias[13]));
                                nodo.getTipoRequisicion().setInterCompania(resultSubFamilias[14] == null ? false : "1".equals(resultSubFamilias[14]));
                                nodo.setComentarioUs(resultSubFamilias[15] == null ? "" : resultSubFamilias[15].toString());
                                nodo.setComentarioEsa(resultSubFamilias[16] == null ? "" : resultSubFamilias[16].toString());
                                nodo.setComentarioPtb(resultSubFamilias[17] == null ? "" : resultSubFamilias[17].toString());
                                if (nodo.getId_padre().equals(familia.getId_familia()) && !familia.getNodo().stream().anyMatch(x -> x.getId_familia().equals(nodo.getId_familia()))) {
                                    if (familia.getDate() == null) {
                                        /*Bloque para subir la fecha mas actual a nivel familia*/
                                        familia.setDate(nodo.getDate());
                                        familia.setCreation_date(nodo.getCreation_date()); //fecha para mostrar en la tabla 
                                        familia.setAdministrador(nodo.getAdministrador());
                                        familia.setAdministradorFilter(nodo.getAdministradorFilter());
                                    } else {
                                        if (nodo.getDate() != null && familia.getDate().before(nodo.getDate())) {
                                            familia.setDate(nodo.getDate());  //subimos la fecha
                                            familia.setCreation_date(nodo.getCreation_date()); //fecha para mostrar en la tabla 
                                            familia.setAdministrador(nodo.getAdministrador());
                                            familia.setAdministradorFilter(nodo.getAdministradorFilter());
                                        }
                                    }
                                    familia.getNodo().add(nodo); //agregando subFamilia
                                }
                            }
                            //
                            if (categoria.getDate() == null) {
                                categoria.setDate(familia.getDate());
                                categoria.setCreation_date(familia.getCreation_date()); //fecha para mostrar en la tabla 
                                categoria.setAdministrador(familia.getAdministrador());
                                categoria.setAdministradorFilter(familia.getAdministradorFilter());
                            } else {
                                if (familia.getDate() != null && categoria.getDate().before(familia.getDate())) {
                                    categoria.setDate(familia.getDate());  //subimos la fecha
                                    categoria.setCreation_date(familia.getCreation_date()); //fecha para mostrar en la tabla 
                                    categoria.setAdministrador(familia.getAdministrador());
                                    categoria.setAdministradorFilter(familia.getAdministradorFilter());
                                }
                            }
                            categoria.getNodo().add(familia); // familias 

                        }
                    }
                    //
                    listCategorias.add(categoria);
                }
            }
        } catch (NumberFormatException e) {
            log.error("Error al traer los datos" + e.getMessage());
        }
        return listCategorias;
    }

    private List<Object[]> tipoQuery(RequestFiltros requestFiltros, String tipoPestana) {
        StringBuilder condiciones = new StringBuilder("");
        String coma;
        Long idUen = requestFiltros.getModelListaUensCc() == null ? null : !requestFiltros.getModelListaUensCc().isEmpty() ? requestFiltros.getModelListaUensCc().get(0).getId() : null;
        String fechaInicio;
        String fechaFin;
        String idioma;
        List<Object[]> results = null;
        String mensaje = "";
        if (requestFiltros != null) {
            if (requestFiltros.getModelListaUensCc() != null && !requestFiltros.getModelListaUensCc().isEmpty()) {

                if (tipoPestana.equals(Constants.TIPO_PESTANA_FAM)) {
                    idioma = requestFiltros.getModelListaUensCc().get(0).getIdioma();
                    condiciones.append(" AND arbol.id_idioma = ").append("\'").append(idioma).append("\'");
                }
            }
            ///pestaÒa dos 
            if (requestFiltros.getListIdCategoria() != null && !requestFiltros.getListIdCategoria().isEmpty()) {
                condiciones.append(" AND arbol.idcategoria IN  ( ");
                coma = "";
                for (Integer cat : requestFiltros.getListIdCategoria()) {
                    condiciones.append(coma).append(cat);
                    coma = ",";
                }
                condiciones.append(")");
            }
            if (requestFiltros.getListIdFamilia() != null && !requestFiltros.getListIdFamilia().isEmpty()) {
                condiciones.append(" AND arbol.idfamilia IN  ( ");
                coma = "";
                for (Integer fam : requestFiltros.getListIdFamilia()) {
                    condiciones.append(coma).append(fam);
                    coma = ",";
                }
                condiciones.append(")");
            }

            if (requestFiltros.getListIdSubFamilia() != null && !requestFiltros.getListIdSubFamilia().isEmpty()) {
                condiciones.append(" AND arbol.idsubfam IN  ( ");
                coma = "";
                for (Integer sfam : requestFiltros.getListIdSubFamilia()) {
                    condiciones.append(coma).append(sfam);
                    coma = ",";
                }
                condiciones.append(")");
            }
            if (requestFiltros.getFechaInicioFam() != null && !requestFiltros.getFechaInicioFam().equals("")) {
                fechaInicio = requestFiltros.getFechaInicioFam();
                condiciones.append(" AND TRUNC(dc.FECHA_CREACION) >=TO_DATE(\'").append(fechaInicio).append("\', \'dd-MM-yyyy\')");
            }

            if (requestFiltros.getFechaFinFam() != null && !requestFiltros.getFechaFinFam().equals("")) {
                fechaFin = requestFiltros.getFechaFinFam();
                condiciones.append(" AND TRUNC(dc.FECHA_CREACION) <=TO_DATE(\'").append(fechaFin).append("\', \'dd-MM-yyyy\')");
            }
            if (requestFiltros.getModelAdministradorFam()!= null && !requestFiltros.getModelAdministradorFam().isEmpty()) {
                condiciones.append(" AND dc.usuario_creacion IN  ( ");
                coma = "";
                for (CompradorCcPojo admon : requestFiltros.getModelAdministradorFam()) {
                    condiciones.append(coma).append("'").append(admon.getId()).append("'");
                    coma = ",";
                }
                condiciones.append(")");
            }
            if (!requestFiltros.isSinRestricciones() && requestFiltros.getModelTipoRequisicion() != null && !requestFiltros.getModelTipoRequisicion().isEmpty()) {
                condiciones.append(" AND  ( ");
                coma = "";
                for (TipoRequisicion cat : requestFiltros.getModelTipoRequisicion()) {
                    switch (cat.getClaveRequisicion()) {
                        case "A":
                            condiciones.append(coma).append(" dc.almacen = 1 ");
                            break;
                        case "C":
                            condiciones.append(coma).append(" dc.spot = 1 ");
                            break;
                        case "P":
                            condiciones.append(coma).append(" dc.proveedor = 1 ");
                            break;
                        case "V":
                            condiciones.append(coma).append(" dc.vales_fondo = 1 ");
                            break;
                        case "I":
                            condiciones.append(coma).append(" dc.inter_compa = 1 ");
                            break;
                    }
                    coma = " and ";
                }
                condiciones.append(")");
            }
            if (requestFiltros.isSinRestricciones()) {
                condiciones.append(" AND  dc.almacen is null  AND dc.spot is null AND dc.proveedor is null AND dc.vales_fondo is null AND dc.inter_compa is null ");
            }
            if (tipoPestana.equals(Constants.TIPO_PESTANA_FAM)) {
                condiciones.append(" ORDER BY arbol.categoria, arbol.familia, arbol.subfamilia");
            }

            String segmento = "";
            if (tipoPestana.equals(Constants.TIPO_PESTANA_FAM)) {
                segmento = (String) em.createNativeQuery("select SEGMENTO1 from RANGO_UEN where ID_UEN =:idUen")
                        .setParameter("idUen", idUen)
                        .getSingleResult();
                segmento = segmento.toUpperCase();
                segmento = segmento.replace("SEGMENTO_1", "cat.SEGMENTO_1");
                segmento = segmento.replace("CATEGORY_SET_NAME", "upper(cat.CATEGORY_SET_NAME)");
                segmento = segmento.replace("ID_FAMILIA", "cat.ID_FAMILIA");
            }

            StoredProcedureQuery procedure = em.createStoredProcedureQuery("NVC_PKG_ASIGNACION_COMPRADOR.getArbolCategorias");
            procedure.registerStoredProcedureParameter("p_idUen", Long.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("whereClause", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("segmentClause", String.class, ParameterMode.IN);
            procedure.registerStoredProcedureParameter("message_out", String.class, ParameterMode.OUT);
            procedure.registerStoredProcedureParameter("c_out", void.class, ParameterMode.REF_CURSOR);
            procedure.setParameter("p_idUen", idUen);
            procedure.setParameter("whereClause", condiciones.toString());
            procedure.setParameter("segmentClause", segmento);
            if (procedure.execute()) {
                results = (List<Object[]>) procedure.getResultList();
                mensaje = (String) procedure.getOutputParameterValue("message_out");
                log.debug(mensaje);
            }

        }
        return results;
    }

    @Override
    public void saveDatosFamilia(List<ConfFamPojo> listDc, String idUsuario) {    //todo esto se debe meter en un paquete para que sea más rapido
        Integer idfam;
        Integer idUen;
        String tipoRequisicion;
        String eliminar;
        String matriz = "";
        String message = "";
        Integer tamCaracteres = 28000;
        List<String> listMatriz = new ArrayList();
        try {
            for (int i = 0; i < listDc.size(); i++) {
                idfam = listDc.get(i).getIdFamilia();
                idUen = listDc.get(i).getIdUen();
                tipoRequisicion = listDc.get(i).getTipoRequisicion();
                eliminar = listDc.get(i).isEliminar() ? "1" : "0";
                matriz += idfam + "," + idUen + "," + tipoRequisicion + "," + eliminar;
                if (i < listDc.size() - 1) {
                    if (matriz.length() > tamCaracteres) {
                        listMatriz.add(matriz);
                        matriz = "";
                    }
                    if (matriz.length() > 0) {
                        matriz += "|";
                    }
                }
            }
            if (matriz.length() > 0) {
                listMatriz.add(matriz);
            }
            for (String matriz_ : listMatriz) {
                StoredProcedureQuery procedure = em.createStoredProcedureQuery("NVC_PKG_ASIGNACION_COMPRADOR.insertUpdateConfFamilia");
                procedure.registerStoredProcedureParameter("p_id_usuario", String.class, ParameterMode.IN);
                procedure.registerStoredProcedureParameter("matriz", String.class, ParameterMode.IN);
                procedure.registerStoredProcedureParameter("message_out", String.class, ParameterMode.OUT);
                procedure.setParameter("p_id_usuario", idUsuario);
                procedure.setParameter("matriz", matriz_);
                procedure.execute();
                message = (String) procedure.getOutputParameterValue("message_out");
                if (!message.equals("OK")) {
                    throw new Error(message);
                }
            }

        } catch (Exception e) {
            log.error("error en : save :" + message + "->" + e.getMessage());
            throw e;

        }
    }

    @Override
    public Iterable<NvcTblOrganizacionesH> getUensCc() {
        Iterable<NvcTblOrganizacionesH> listUenCc = null;
        try {
            listUenCc = em.
                    createNamedQuery("NvcTblOrganizacionesH.getUenCcAdmon")
                    .getResultList();

        } catch (Exception e) {
            log.error("error en : NvcTblOrganizacionesH.getUenCc :" + e.getMessage());
        }
        return listUenCc;
    }

    @Override
    public void saveDatosComentarios(List<ConfFamPojo> datosGuardar) {    //todo esto se debe meter en un paquete para que sea más rapido
        Integer idfam;
        String idioma;
        String comentario;
        String matriz = "";
        String message = "";
        Integer tamCaracteres = 28000;
        List<String> listMatriz = new ArrayList();
        try {
            for (int i = 0; i < datosGuardar.size(); i++) {
                idfam = datosGuardar.get(i).getIdFamilia();
                idioma = datosGuardar.get(i).getIdioma();
                comentario = datosGuardar.get(i).getComentario();
                matriz += idfam + "'" + idioma + "'" + comentario;
                if (i < datosGuardar.size() - 1) {
                    if (matriz.length() > tamCaracteres) {
                        listMatriz.add(matriz);
                        matriz = "";
                    }
                    if (matriz.length() > 0) {
                        matriz += "|";
                    }
                }
            }
            if (matriz.length() > 0) {
                listMatriz.add(matriz);
            }
            for (String matriz_ : listMatriz) {
                StoredProcedureQuery procedure = em.createStoredProcedureQuery("NVC_PKG_ASIGNACION_COMPRADOR.updateFamComentarios");
                procedure.registerStoredProcedureParameter("matriz", String.class, ParameterMode.IN);
                procedure.registerStoredProcedureParameter("message_out", String.class, ParameterMode.OUT);
                procedure.setParameter("matriz", matriz_);
                procedure.execute();
                message = (String) procedure.getOutputParameterValue("message_out");
                if (!message.equals("OK")) {
                    throw new Error(message);
                }
            }

        } catch (Exception e) {
            log.error("error en : save :" + message + "->" + e.getMessage());
            throw e;

        }
    }

    @Override
    public List<CompradorCcPojo> getAdministradorFam(Long idUen) {
        List<CompradorCcPojo> listAdministrador = new ArrayList<>();
        CompradorCcPojo administradorCc;
        try {
            List<Object[]> results = em.
                    createNativeQuery("SELECT DISTINCT logc.USUARIO_CREACION, u.NOMBRE_USUARIO AS ADMINISTRADOR\n"
                            + "  FROM (SELECT ID_VERSION,\n"
                            + "               ID_UEN,\n"
                            + "               ID_FAMILIA,\n"
                            + "               USUARIO_CREACION,\n"
                            + "               FECHA_CREACION\n"
                            + "          FROM ( -- start bloque para sacar los registros mas actuales del log\n"
                            + "                SELECT ID_VERSION,\n"
                            + "                       ID_UEN,\n"
                            + "                       ID_FAMILIA,\n"
                            + "                       USUARIO_CREACION,\n"
                            + "                       FECHA_CREACION,\n"
                            + "                       MAX (ID_VERSION)\n"
                            + "                          OVER (PARTITION BY ID_UEN, ID_FAMILIA)\n"
                            + "                          max_id_version\n"
                            + "                  FROM LOG_CONF_FAM lofam)\n"
                            + "         WHERE ID_VERSION = max_id_version) logc\n"
                            + "       INNER JOIN usuario u ON (logc.USUARIO_CREACION = u.ID_USUARIO)\n"
                            + " WHERE logc.ID_UEN =:idUen")
                    .setParameter("idUen", idUen)
                    .getResultList();
            for (Object[] result : results) {
                administradorCc = new CompradorCcPojo();
                administradorCc.setId(result[0].toString());
                administradorCc.setNombre_comprador(result[1].toString());
                listAdministrador.add(administradorCc);
            }
        } catch (Exception e) {
            log.error("error en : getAdministrador :" + e.getMessage());
        }
        return listAdministrador;
    }

}
