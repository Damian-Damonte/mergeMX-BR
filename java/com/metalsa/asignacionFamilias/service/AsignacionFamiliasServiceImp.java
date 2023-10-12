/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.asignacionFamilias.service;

import com.metalsa.compradorCC.pojo.CategoriaPojo;
import com.metalsa.compradorCC.pojo.CompradorCcPojo;
import com.metalsa.compradorCC.pojo.ListDc;
import com.metalsa.compradorCC.pojo.RequestFiltros;
import com.metalsa.utils.Constants;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

/**
 *
 * @author jose.jimenez07
 */
@Log4j
@Service
public class AsignacionFamiliasServiceImp implements AsignacionFamiliasService {

    @PersistenceContext
    private EntityManager em;
    private final DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    public List<CompradorCcPojo> getCompradorFam(Long idUen, String tipoAprobacion) {
        List<CompradorCcPojo> listBuyerCc = new ArrayList<>();
        CompradorCcPojo compradorCc;
        try {
            List<Object[]> results = em.
                    createNativeQuery("select DISTINCT u.ID_USUARIO,u.NOMBRE_USUARIO \n"
                            + "                                                        from FAMILIAS_POR_APROBADOR ucc,usuario u \n"
                            + "                                                        where ucc.ID_USUARIO= u.ID_USUARIO and ucc.ID_UEN=:idUen\n"
                            + "                                                        and ucc.TIPO_APROBACION=:tipoAprobacion")
                    .setParameter("idUen", idUen)
                    .setParameter("tipoAprobacion", tipoAprobacion)
                    .getResultList();
            for (Object[] result : results) {
                compradorCc = new CompradorCcPojo();
                compradorCc.setId(result[0].toString());
                compradorCc.setNombre_comprador(result[1].toString());
                listBuyerCc.add(compradorCc);
            }
        } catch (Exception e) {
            log.error("error en : getBuyerCc :" + e.getMessage());
        }
        return listBuyerCc;
    }

    @Override
    public List<CompradorCcPojo> getAdministradorFam(Long idUen, String tipoAprobacion) {
        List<CompradorCcPojo> listAdministrador = new ArrayList<>();
        CompradorCcPojo administradorCc;
        try {
            List<Object[]> results = em.
                    createNativeQuery("select DISTINCT logc.CREATED_BY, u.NOMBRE_USUARIO as ADMINISTRADOR from (\n"
                            + "select ID_VERSION,ID_UEN,ID_FAMILIA,CREATED_BY,CREATION_DATE,ID_PREV_USUARIO\n"
                            + "from\n"
                            + "(-- start bloque para sacar los registros mas actuales del log \n"
                            + "select  ID_VERSION,\n"
                            + "ID_UEN,\n"
                            + "ID_FAMILIA,\n"
                            + "TIPO_APROBACION,\n"
                            + "CREATED_BY,\n"
                            + "CREATION_DATE,\n"
                            + "ID_PREV_USUARIO,\n"
                            + "max(ID_VERSION) over (partition by ID_UEN,ID_FAMILIA,TIPO_APROBACION) max_id_version\n"
                            + "from   LOG_FAMILIA_FINANZAS lofam  WHERE lofam.TIPO_APROBACION=:tipoAprobacion\n"
                            + ")\n"
                            + "where ID_VERSION = max_id_version\n"
                            + ") logc\n"
                            + "inner join usuario u on (logc.CREATED_BY = u.ID_USUARIO) where logc.ID_UEN=:idUen")
                    .setParameter("idUen", idUen)
                    .setParameter("tipoAprobacion", tipoAprobacion)
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

    @Override
    public List<CompradorCcPojo> getPrevCompradorFam(Long idUen, String tipoAprobacion) {
        CompradorCcPojo compradorCc;
        List<CompradorCcPojo> ListcompradorCc = new ArrayList();
        try {
            List<Object[]> results = em.
                    createNativeQuery("SELECT DISTINCT logc.ID_PREV_USUARIO,\n"
                            + "  u.NOMBRE_USUARIO AS PRECOMPRADOR\n"
                            + "FROM\n"
                            + "  (SELECT ID_VERSION,\n"
                            + "    ID_UEN,\n"
                            + "    ID_FAMILIA,\n"
                            + "    TIPO_APROBACION,\n"
                            + "    CREATED_BY,\n"
                            + "    CREATION_DATE,\n"
                            + "    ID_PREV_USUARIO\n"
                            + "  FROM\n"
                            + "    ( -- start bloque para sacar los registros mas actuales del log\n"
                            + "    SELECT ID_VERSION,\n"
                            + "      ID_UEN,\n"
                            + "      ID_FAMILIA,\n"
                            + "      TIPO_APROBACION,\n"
                            + "      CREATED_BY,\n"
                            + "      CREATION_DATE,\n"
                            + "      ID_PREV_USUARIO,\n"
                            + "      MAX(ID_VERSION) over (partition BY ID_UEN,ID_FAMILIA,TIPO_APROBACION) max_id_version\n"
                            + "    FROM LOG_FAMILIA_FINANZAS lofam\n"
                            + "    WHERE lofam.TIPO_APROBACION=:tipoAprobacion\n"
                            + "    )\n"
                            + "  WHERE ID_VERSION = max_id_version\n"
                            + "  ) logc\n"
                            + "INNER JOIN usuario u\n"
                            + "ON (logc.ID_PREV_USUARIO = u.ID_USUARIO)\n"
                            + "WHERE logc.ID_UEN =:idUen")
                    .setParameter("idUen", idUen)
                    .setParameter("tipoAprobacion", tipoAprobacion)
                    .getResultList();
            for (Object[] result : results) {
                compradorCc = new CompradorCcPojo();
                compradorCc.setId(result[0].toString());
                compradorCc.setNombre_comprador(result[1].toString());
                ListcompradorCc.add(compradorCc);
            }
        } catch (Exception e) {
            log.error("Error al traer los datos" + e.getMessage());
        }
        return ListcompradorCc;
    }

    private List<Object[]> tipoQuery(RequestFiltros requestFiltros, String tipoPestana) {
        String sql = "";
        String condiciones = "";
        Map<String, Object> variables = new HashMap();
        Long idUen = 0L;
        List<String> listComprador = new ArrayList();
        List<String> listAdministrador = new ArrayList();
        List<String> listPrevComprador = new ArrayList();
        String fechaInicio;
        String fechaFin;
        String idioma;
        List<Object[]> results = null;
        try {
            if (!requestFiltros.getModelListaUensCc().isEmpty()) {
                idUen = requestFiltros.getModelListaUensCc().get(0).getId();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        if (tipoPestana.equals(Constants.TIPO_PESTANA_FAM)) {
            sql = "      SELECT arbol.idcategoria,\n"
                    + "        arbol.categoria,\n"
                    + "        arbol.idcategoria\n"
                    + "        ||'-'\n"
                    + "        ||arbol.idfamilia idfamilia,\n"
                    + "        arbol.familia,\n"
                    + "        arbol.idfamilia\n"
                    + "        ||'-'\n"
                    + "        || arbol.idsubfam idsubfam,\n"
                    + "        arbol.subfamilia,\n"
                    + "        null as ID_COMPRADOR_FAMILIA,\n"
                    + "        dc.id_uen,\n"
                    + "        dc.id_usuario,\n"
                    + "        Ua.NOMBRE_USUARIO,\n"
                    + "        dc.created_by,\n"
                    + "        Ub.NOMBRE_USUARIO AS Administrador,\n"
                    + "        dc.creation_Date,\n"
                    + "        dc.id_prev_usuario,\n"
                    + "        Uc.NOMBRE_USUARIO AS prevUsuario,\n"
                    + "        dc.tipo_aprobacion\n"
                    + "      FROM\n"
                    + "        (SELECT\n"
                    + "          CASE\n"
                    + "            WHEN dc.id_uen IS NULL\n"
                    + "            THEN modelFam.id_uen\n"
                    + "            ELSE dc.id_uen\n"
                    + "          END id_uen,-- EN LA RESULT SALEN DATOS EN NULO ESTO PORQUE NO EXISTE UN COLUMNA ID_UEN EN LA NVC_TBL_FAMILIAS Y SI SALE UN VALOR ES POR EL MATCH QUE HACE CON DC Y LOG, LA UEN FALTANTE SE ASINGNA EN JAVA                                                   dc.ID_USUARIO,\n"
                    + "          CASE\n"
                    + "            WHEN dc.id_familia IS NULL\n"
                    + "            THEN modelFam.id_familia\n"
                    + "            ELSE dc.id_familia\n"
                    + "          END id_familia,\n"
                    + "          dc.id_usuario,\n"
                    + "          modelFam.created_by,\n"
                    + "          modelFam.creation_Date,\n"
                    + "          modelFam.id_prev_usuario,\n"
                    + "          CASE\n"
                    + "            WHEN dc.tipo_aprobacion IS NULL\n"
                    + "            THEN modelFam.tipo_aprobacion\n"
                    + "            ELSE dc.tipo_aprobacion\n"
                    + "          END tipo_aprobacion\n"
                    + "        FROM\n"
                    + "          (SELECT ID_VERSION,\n"
                    + "            ID_UEN,\n"
                    + "            ID_FAMILIA,\n"
                    + "            CREATED_BY,\n"
                    + "            CREATION_DATE,\n"
                    + "            ID_PREV_USUARIO,\n"
                    + "            TIPO_APROBACION\n"
                    + "          FROM\n"
                    + "            ( -- start bloque para sacar los registros mas actuales del log\n"
                    + "            SELECT ID_VERSION,\n"
                    + "              ID_UEN,\n"
                    + "              ID_FAMILIA,\n"
                    + "              CREATED_BY,\n"
                    + "              CREATION_DATE,\n"
                    + "              ID_PREV_USUARIO,\n"
                    + "              TIPO_APROBACION,\n"
                    + "              MAX(ID_VERSION) OVER (PARTITION BY ID_UEN,ID_FAMILIA,TIPO_APROBACION) max_id_version\n"
                    + "            FROM LOG_FAMILIA_FINANZAS lofam\n"
                    + "            WHERE lofam.TIPO_APROBACION=:tipoAprobacion\n"
                    + "            )\n"
                    + "          WHERE ID_VERSION = max_id_version\n"
                    + "          ) modelFam\n"
                    + "        FULL OUTER JOIN FAMILIAS_POR_APROBADOR dc\n"
                    + "        ON (modelFam.ID_UEN     = dc.ID_UEN\n"
                    + "        AND modelFam.ID_FAMILIA = dc.ID_FAMILIA)\n"
                    + "        WHERE dc.TIPO_APROBACION=:tipoAprobacion and dc.ID_UEN =:idUen\n"
                    + "        ) dc\n"
                    + "      RIGHT JOIN\n"
                    + "        (SELECT cat.ID_FAMILIA idCategoria,\n"
                    + "          icat.nombre_familia_idioma categoria,\n"
                    + "          fam.id_familia idfamilia,\n"
                    + "          ifam.nombre_familia_idioma familia,\n"
                    + "          sub.ID_FAMILIA idsubfam,\n"
                    + "          isub.nombre_familia_idioma subfamilia,\n"
                    + "          isub.id_idioma\n"
                    + "        FROM\n"
                    + "          --- regresion\n"
                    + "          nvc_tbl_familias cat,\n"
                    + "          nvc_tbl_familias_idioma icat,\n"
                    + "          nvc_tbl_familias fam,\n"
                    + "          nvc_tbl_familias_idioma ifam,\n"
                    + "          nvc_tbl_familias sub,\n"
                    + "          nvc_tbl_familias_idioma isub\n"
                    + "          ---\n"
                    + "        WHERE cat.id_familia     = icat.id_familia\n"
                    + "        AND cat.nivel            = 1\n"
                    + "        AND fam.id_familia_padre = cat.id_familia\n"
                    + "        AND fam.id_familia       = ifam.id_familia\n"
                    + "        AND fam.nivel            = 2\n"
                    + "        AND sub.id_familia_padre = fam.id_familia\n"
                    + "        AND sub.id_familia       = isub.id_familia\n"
                    + "        AND sub.nivel            = 3\n"
                    + "        AND icat.id_idioma       =ifam.id_idioma\n"
                    + "        AND ifam.id_idioma       =isub.id_idioma\n"
                    + "        AND cat.ESTATUS          ='A'\n"
                    + "        AND [segmento]\n"
                    + "        ) arbol\n"
                    + "      ON (dc.id_familia=arbol.idsubfam)\n"
                    + "      LEFT JOIN usuario Ua\n"
                    + "      ON (Ua.ID_USUARIO = dc.ID_USUARIO)\n"
                    + "      LEFT JOIN usuario Ub\n"
                    + "      ON (Ub.ID_USUARIO = dc.created_by)\n"
                    + "      LEFT JOIN usuario Uc\n"
                    + "      ON (Uc.ID_USUARIO  = dc.ID_PREV_USUARIO)\n"
                    + "      WHERE 1=1\n";
        }
        if (requestFiltros != null) {
            if (requestFiltros.getModelListaUensCc() != null && !requestFiltros.getModelListaUensCc().isEmpty() && tipoPestana.equals(Constants.TIPO_PESTANA_FAM)) {
                condiciones += "AND arbol.id_idioma=:idioma\n";
                idioma = requestFiltros.getModelListaUensCc().get(0).getIdioma();
                variables.put("idioma", idioma);
                //condiciones += "AND (dc.ID_UEN = :idUen or dc.id_uen is null) -- null porque no esta en dc_comprador y en log, esta por configurar\n";
                variables.put("idUen", idUen);
                variables.put("tipoAprobacion", requestFiltros.getTipoAprobacion());
            }
            ///pestaña dos 
            if (requestFiltros.getListIdCategoria() != null && !requestFiltros.getListIdCategoria().isEmpty()) {
                condiciones += "AND arbol.idcategoria in (:idCat)\n";
                variables.put("idCat", requestFiltros.getListIdCategoria());
            }
            if (requestFiltros.getListIdFamilia() != null && !requestFiltros.getListIdFamilia().isEmpty()) {
                condiciones += "AND arbol.idfamilia in (:idFam)\n";
                variables.put("idFam", requestFiltros.getListIdFamilia());
            }

            if (requestFiltros.getListIdSubFamilia() != null && !requestFiltros.getListIdSubFamilia().isEmpty()) {
                condiciones += "AND arbol.idsubfam in (:idSub)\n";
                variables.put("idSub", requestFiltros.getListIdSubFamilia());
            }

            if (requestFiltros.getModelCompradorFam() != null && !requestFiltros.getModelCompradorFam().isEmpty()) {
                condiciones += "AND dc.ID_USUARIO in (:listComprador)\n";
                requestFiltros.getModelCompradorFam().forEach((tmp) -> {
                    listComprador.add(tmp.getId()); //idcomprador, 
                });
                variables.put("listComprador", listComprador);
            }
            if (requestFiltros.getModelAdministradorFam() != null && !requestFiltros.getModelAdministradorFam().isEmpty()) {
                condiciones += "AND dc.CREATED_BY in (:listAdministrador)\n";
                requestFiltros.getModelAdministradorFam().forEach((tmp) -> {
                    listAdministrador.add(tmp.getId());
                });
                variables.put("listAdministrador", listAdministrador);
            }
            if (requestFiltros.getModelPrevCompradorFam() != null && !requestFiltros.getModelPrevCompradorFam().isEmpty()) {
                condiciones += "AND dc.ID_PREV_USUARIO in (:listPrevComprador)\n";
                requestFiltros.getModelPrevCompradorFam().forEach((tmp) -> {
                    listPrevComprador.add(tmp.getId()); // idocmprador
                });
                variables.put("listPrevComprador", listPrevComprador);
            }

            if (requestFiltros.getFechaInicioFam() != null && !requestFiltros.getFechaInicioFam().equals("")) {
                condiciones += "AND TRUNC(dc.CREATION_DATE) >=TO_DATE(:fechaInicio, 'dd-MM-yyyy')\n";
                fechaInicio = requestFiltros.getFechaInicioFam();
                variables.put("fechaInicio", fechaInicio);
            }

            if (requestFiltros.getFechaFinFam() != null && !requestFiltros.getFechaFinFam().equals("")) {
                condiciones += "AND TRUNC(dc.CREATION_DATE) <=TO_DATE(:fechaFin, 'dd-MM-yyyy')\n";
                fechaFin = requestFiltros.getFechaFinFam();
                variables.put("fechaFin", fechaFin);
            }
            if (tipoPestana.equals(Constants.TIPO_PESTANA_FAM)) {
                condiciones += " ORDER BY arbol.idsubfam desc";
            }

            if (tipoPestana.equals(Constants.TIPO_PESTANA_FAM)) {
                String segmento = (String) em.createNativeQuery("select SEGMENTO1 from RANGO_UEN where ID_UEN =:idUen")
                        .setParameter("idUen", idUen)
                        .getSingleResult();
                segmento =segmento.toUpperCase();
                segmento = segmento.replace("SEGMENTO_1", "cat.SEGMENTO_1");
                segmento = segmento.replace("CATEGORY_SET_NAME", "upper(cat.CATEGORY_SET_NAME)");
                segmento = segmento.replace("ID_FAMILIA", "cat.ID_FAMILIA");
                sql = sql.replace("[segmento]", segmento);
            }

            sql += condiciones;
            Query q = em.createNativeQuery(sql);
            variables.forEach((k, v) -> q.setParameter(k, v));
            log.debug(variables.size());
            results = q.getResultList();

        }
        return results;
    }

    @Override
    public List<CategoriaPojo> getDatosByFiltrosFam(RequestFiltros requestFiltros) {
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
                if (!listCategorias.stream().anyMatch(x -> x.getId_familia().equals(Integer.parseInt(result[0].toString())))) {
                    //bloque familias 
                    for (Object[] resultFamilias : results) {
                        String[] temIds = resultFamilias[2].toString().split("-");  //donde posicion 0 es padre y 1 es idfamilia
                        CategoriaPojo familia = new CategoriaPojo();
                        familia.setId_familia(Integer.parseInt(temIds[1]));
                        familia.setNombre_familia(resultFamilias[3].toString());
                        familia.setId_padre(Integer.parseInt(temIds[0]));
                        familia.setId_uen(requestFiltros.getModelListaUensCc().get(0).getId()); // esto porque la uen no viene de manera explicita, ya que las uen se obtienen mediante rangos
                        if (familia.getId_padre().equals(categoria.getId_familia()) && !categoria.getNodo().stream().anyMatch(x -> x.getId_familia().equals(familia.getId_familia()))) {
                            //subFamilias
                            for (Object[] resultSubFamilias : results) {
                                String[] temIdsSub = resultSubFamilias[4].toString().split("-");  //donde posicion 0 es padre y 1 es idfamilia
                                CategoriaPojo nodo = new CategoriaPojo();
                                nodo.setId_padre(temIdsSub[0] == null ? 0 : Integer.parseInt(temIdsSub[0]));
                                nodo.setId_familia(temIdsSub[1] == null ? 0 : Integer.parseInt(temIdsSub[1]));
                                nodo.setNombre_familia(resultSubFamilias[5] == null ? "-" : resultSubFamilias[5].toString());
                                nodo.setId_dc_comprador(resultSubFamilias[6] == null ? null : Long.parseLong(resultSubFamilias[6].toString()));
                                nodo.setId_uen(requestFiltros.getModelListaUensCc().get(0).getId());  // esto porque la uen no viene de manera explicita, ya que las uen se obtienen mediante rangos
                                comprador = new CompradorCcPojo();      /////////// para comprador
                                comprador.setId(resultSubFamilias[8] == null ? "-" : resultSubFamilias[8].toString());
                                comprador.setNombre_comprador(resultSubFamilias[9] == null ? "-" : resultSubFamilias[9].toString());
                                nodo.setCompradorFilter(resultSubFamilias[9] == null ? "-" : resultSubFamilias[9].toString());
                                nodo.getComprador().add(comprador);
                                comprador = new CompradorCcPojo();     /// para administrador
                                comprador.setId(resultSubFamilias[10] == null ? "-" : resultSubFamilias[10].toString());
                                comprador.setNombre_comprador(resultSubFamilias[11] == null ? "-" : resultSubFamilias[11].toString());
                                nodo.setAdministrador(comprador);
                                nodo.setAdministradorFilter(resultSubFamilias[11] == null ? "-" : resultSubFamilias[11].toString());
                                nodo.setCreation_date(resultSubFamilias[12] == null ? "-" : df.format(resultSubFamilias[12]));
                                comprador = new CompradorCcPojo();     /// prev user
                                comprador.setId(resultSubFamilias[13] == null ? "-" : resultSubFamilias[13].toString());
                                comprador.setNombre_comprador(resultSubFamilias[14] == null ? "-" : resultSubFamilias[14].toString());
                                nodo.setPrevComprador(comprador);
                                nodo.setPrevCompradorFilter(resultSubFamilias[14] == null ? "-" : resultSubFamilias[14].toString());
                                /*fecha exacta*/
                                nodo.setDate(resultSubFamilias[12] == null ? null : (Date) resultSubFamilias[12]);
                                /*fecha end*/
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

    @Override
    public void saveDatosFamilias(List<ListDc> listDc, String tipoAprobacion) {    //todo esto se debe meter en un paquete para que sea más rapido
        Integer idfam;
        Integer idUen;
        String idUsuario;
        String idAdmon;
        String matriz = "";
        String message = "";
        Integer tamCaracteres = 28000;
        List<String> listMatriz = new ArrayList();
        try {
            for (int i = 0; i < listDc.size(); i++) {
                idfam = listDc.get(i).getIdFamilia();
                idUen = listDc.get(i).getIdUen();
                idUsuario = listDc.get(i).getIdUsuario() == null ? "0" : listDc.get(i).getIdUsuario();
                idAdmon = listDc.get(i).getIdAdmin();
                matriz += idfam + "," + idUen + "," + idUsuario + "," + idAdmon;
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
                StoredProcedureQuery procedure = em.createStoredProcedureQuery("NVC_PKG_ASIGNACION_CC_FAM.insertUpdateFamilias");
                procedure.registerStoredProcedureParameter("matriz", String.class, ParameterMode.IN);
                procedure.registerStoredProcedureParameter("tipoAprobacion", String.class, ParameterMode.IN);
                procedure.registerStoredProcedureParameter("message_out", String.class, ParameterMode.OUT);
                procedure.setParameter("matriz", matriz_);
                procedure.setParameter("tipoAprobacion", tipoAprobacion);
                procedure.execute();
                message = (String) procedure.getOutputParameterValue("message_out");
                if (!message.equals("OK")) {
                    throw new Error();
                }
            }

        } catch (Exception e) {
            log.error("error en : save :" + message + "->" + e.getMessage());
            throw e;

        }
    }

    @Override
    public List<CompradorCcPojo> getAllComprador(Long idUen, String tipoAprobacion) {
        List<CompradorCcPojo> listCacheComprador = new ArrayList<>();
        CompradorCcPojo compradorCc;
        try {
            List<Object[]> results = em.
                    createNativeQuery("select DISTINCT u.ID_USUARIO,u.NOMBRE_USUARIO from usuario u \n"
                            + "                            inner join UEN_POR_USUARIO uenU on (u.ID_USUARIO = uenU.ID_USUARIO)\n"
                            + "                            where uenU.ID_UEN=:idUen\n"
                            + "                            and u.estatus='A'")
                    .setParameter("idUen", idUen)
                    .getResultList();
            for (Object[] result : results) {
                compradorCc = new CompradorCcPojo();
                compradorCc.setId(result[0].toString());
                compradorCc.setNombre_comprador(result[1].toString());
                listCacheComprador.add(compradorCc);
            }
        } catch (Exception e) {
            log.error("error en : getCacheComprador :" + e.getMessage());
        }
        return listCacheComprador;
    }

    @Override
    public CompradorCcPojo getAdministradorPorUen(Long idUen, String tipoAprobacion) {
        String descripcionAprobacion = tipoAprobacion.equalsIgnoreCase("FINANZAS")?"SPX_ADMIN_COMPRADORES":"SPX_ADMIN_COMPRADORES";
        CompradorCcPojo compradorCc = new CompradorCcPojo();
        try {
            List<Object[]> results = em.
                    createNativeQuery("select u.id_usuario, u.nombre_usuario from nvc_tbl_uen_usu_aprobacion ua, \n"
                            + "nvc_tbl_procesos_aprobacion pa, usuario u where ua.id_usuario = u.id_usuario and ua.delegado = 0 \n"
                            + "and ua.id_uen = :idUen and ua.id_proceso_aprobacion = pa.id_proceso_aprobacion \n"
                            + "and pa.descripcion = :descripcionAprobacion")
                    .setParameter("idUen", idUen)
                    .setParameter("descripcionAprobacion", descripcionAprobacion)
                    .getResultList();
            for (Object[] result : results) {
                compradorCc = new CompradorCcPojo();
                compradorCc.setId(result[0].toString());
                compradorCc.setNombre_comprador(result[1].toString());
                log.debug(compradorCc);
            }
        } catch (Exception e) {
            log.error("error en : getCacheComprador :" + e.getMessage());
        }
        return compradorCc;
    }

}
