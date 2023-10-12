/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.reportes.repository;

import com.metalsa.core.model.Periodos;
import com.metalsa.core.repository.PeriodosRepository;
import com.metalsa.reportes.model.Balanza;
import com.metalsa.reportes.model.BalanzaLinea;
import com.metalsa.reportes.model.EstadoCuenta;
import com.metalsa.reportes.model.EstadoCuentaProject;
import com.metalsa.reportes.model.EstadoCuentaTravel;
import com.metalsa.reportes.model.GastosProject;
import com.metalsa.reportes.model.GastosTravel;
import com.metalsa.reportes.model.Incrementos;
import com.metalsa.reportes.model.Meses;
import com.metalsa.reportes.model.Parametros;
import com.metalsa.reportes.model.Presupuesto;
import com.metalsa.reportes.model.PresupuestoAnual;
import com.metalsa.reportes.model.PresupuestoAnualLinea;
import com.metalsa.reportes.model.PresupuestoUen;
import com.metalsa.reportes.model.PresupuestoUenLinea;
import com.metalsa.reportes.model.Transferencias;
import com.metalsa.reportes.model.Usuarios;
import com.metalsa.reportes.model.Variacion;
import com.metalsa.reportes.model.VariacionLinea;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;

@Log4j
public class ReportesRepositoryImpl implements ReportesProcedure {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private PeriodosRepository repository;

    @Override
    public List<BalanzaLinea> getReporteBalanza(Parametros parametros) {
        log.debug("----------------------------- getReporteBalanza -------------------------------");
//        log.debug(parametros.filtrosBalanza());

        List<BalanzaLinea> result;
        try {

            StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("getReporteBalanza");
            proc.setParameter("p_uen", parametros.getIdUen());
            proc.setParameter("p_periodo_inicial", parametros.getPeriodoInicial());
            proc.setParameter("p_periodo_final", parametros.getPeriodoFinal());
            proc.setParameter("p_cc_inicial", parametros.getCcInicial());
            proc.setParameter("p_cc_final", parametros.getCcFinal());
            proc.setParameter("p_id_categorias", parametros.getIdcategorias());
            proc.setParameter("p_id_ccs", parametros.getIdccs());
            proc.setParameter("p_idioma", parametros.getIdioma());

            proc.execute();
            result = proc.getResultList();
        } catch (Exception e) {
            log.debug(e.getLocalizedMessage(), e);
            result = new ArrayList<>();
        }

        return result;
    }

    @Override
    public List<Balanza> getReporteBalanzaAgrupado(Parametros filtros) {

        List<Balanza> result = new ArrayList<>();

//        log.debug("iniciando busqueda");
        Map<String, List<BalanzaLinea>> groupByCC
                = getReporteBalanza(filtros)
                        .stream()
                        .collect(Collectors.groupingBy(BalanzaLinea::getCodigoCC));
//        log.debug("iniciando agrupacion");
        for (Map.Entry<String, List<BalanzaLinea>> entry : groupByCC.entrySet()) {

            BalanzaLinea header = entry.getValue().get(0);
            Balanza balanza = new Balanza();
            balanza.setCodigoCC(header.getCodigoCC());
            balanza.setNombreCC(header.getNombreCC());
            balanza.setResponsable(header.getResponsable());
            balanza.setLineas(entry.getValue());
            Meses totales = new Meses();
            for (BalanzaLinea linea : entry.getValue()) {
                totales.setEnero(totales.getEnero() + linea.getEnero());
                totales.setFebrero(totales.getFebrero() + linea.getFebrero());
                totales.setMarzo(totales.getMarzo() + linea.getMarzo());
                totales.setAbril(totales.getAbril() + linea.getAbril());
                totales.setMayo(totales.getMayo() + linea.getMayo());
                totales.setJunio(totales.getJunio() + linea.getJunio());
                totales.setJulio(totales.getJulio() + linea.getJulio());
                totales.setAgosto(totales.getAgosto() + linea.getAgosto());
                totales.setSeptiembre(totales.getSeptiembre() + linea.getSeptiembre());
                totales.setOctubre(totales.getOctubre() + linea.getOctubre());
                totales.setNoviembre(totales.getNoviembre() + linea.getNoviembre());
                totales.setDiciembre(totales.getDiciembre() + linea.getDiciembre());
            }
            balanza.setTotales(totales);
            result.add(balanza);
        }
//        log.debug("proceso finalizado");

        return result
                .stream()
                .sorted(Comparator.comparing(Balanza::getCodigoCC))
                .collect(Collectors.toList());
    }

    @Override
    public List<PresupuestoAnualLinea> getReportePresupuestoAnual(Parametros parametros) {
        log.debug("----------------------------- getReportePresupuestoAnual -------------------------------");
//        log.debug(parametros.filtrosBalanza());
        List<PresupuestoAnualLinea> result;
        try {

            StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("getReportePresupuestoAnual");
            proc.setParameter("p_uen", parametros.getIdUen());
            proc.setParameter("p_periodo_inicial", parametros.getPeriodoInicial());
            proc.setParameter("p_periodo_final", parametros.getPeriodoFinal());
            proc.setParameter("p_cc_inicial", parametros.getCcInicial());
            proc.setParameter("p_cc_final", parametros.getCcFinal());
            proc.setParameter("p_id_ccs", parametros.getIdccs());
            proc.setParameter("p_idioma", parametros.getIdioma());

            proc.execute();
            result = proc.getResultList();
        } catch (Exception e) {
            log.debug(e.getLocalizedMessage(), e);
            result = new ArrayList<>();
        }
//        log.debug("elementos encontrados ->" + result.size());
        return result;

    }

    @Override
    public List<PresupuestoAnual> getReportePresupuestoAnualAgrupado(Parametros filtros) {

        List<PresupuestoAnual> result = new ArrayList<>();

//        log.debug("iniciando busqueda");
        Map<String, List<PresupuestoAnualLinea>> groupByCC
                = getReportePresupuestoAnual(filtros)
                        .stream()
                        .collect(Collectors.groupingBy(PresupuestoAnualLinea::getCodigoCC));

//        log.debug("iniciando agrupacion->" + groupByCC.size());
        for (Map.Entry<String, List<PresupuestoAnualLinea>> entry : groupByCC.entrySet()) {
            PresupuestoAnualLinea first = entry.getValue().get(0);
            PresupuestoAnual header = new PresupuestoAnual();
            header.setCodigoCC(first.getCodigoCC());
            header.setNombreCC(first.getNombreCC());
            header.setResponsable(first.getResponsable());
            header.setLineas(entry.getValue());
            result.add(header);
        }
//        log.debug("proceso finalizado");

        return result
                .stream()
                .sorted(Comparator.comparing(PresupuestoAnual::getCodigoCC))
                .collect(Collectors.toList());
    }

    @Override
    public List<VariacionLinea> getReporteVariacion(Parametros filtros) {
        log.debug("----------------------------- getReporteVariacion -------------------------------");
//        log.debug(filtros.filtrosVariacion());
        List<VariacionLinea> result;
        try {

            StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("getReporteVariacion");
            proc.setParameter("p_uen", filtros.getIdUen());
            proc.setParameter("p_periodo_inicial", filtros.getPeriodoInicial());
            proc.setParameter("p_periodo_final", filtros.getPeriodoFinal());
            proc.setParameter("p_id_ccs", filtros.getIdccs());
            proc.setParameter("p_cc_inicial", filtros.getCcInicial());
            proc.setParameter("p_cc_final", filtros.getCcFinal());
            proc.setParameter("p_id_categorias", filtros.getIdcategorias());
            proc.setParameter("p_idioma", filtros.getIdioma());

            proc.execute();
            result = proc.getResultList();
        } catch (Exception e) {
            log.debug(e.getLocalizedMessage(), e);
            result = new ArrayList<>();
        }
//        log.debug("elementos encontrados ->" + result.size());
        return result;
    }
    
    @Override
    public List<EstadoCuentaTravel> getReporteEdoCuentaTravel(Parametros filtros) {
        log.debug("----------------------------- getReporteEdoCuenta Travel -------------------------------");
//        log.debug(filtros.filtrosVariacion());
        List<EstadoCuentaTravel> result;
        log.debug("P_ID_UEN--->" + filtros.getIdUen());
        log.debug("P_FECHA_I--->" + filtros.getPeriodoInicial());
        log.debug("P_FECHA_F--->" + filtros.getPeriodoFinal());
        log.debug("P_COST_CENTER--->" + filtros.getCcInicial());
        log.debug("P_IDIOMA--->" + filtros.getIdioma());
        try {

            StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("EstadoCuentaTravel.getEstadoCuentaTravel");
            proc.setParameter("P_ID_UEN", filtros.getIdUen());
            proc.setParameter("P_FECHA_I", filtros.getPeriodoInicial());
            proc.setParameter("P_FECHA_F", filtros.getPeriodoFinal());
            proc.setParameter("P_COST_CENTER", filtros.getCcInicial());
            proc.setParameter("P_IDIOMA", filtros.getIdioma());
            proc.execute();
            result = proc.getResultList();
        } catch (Exception e) {
            log.debug(e.getLocalizedMessage(), e);
            result = new ArrayList<>();
        }
        log.debug("elementos encontrados ->" + result.size());
        return result;
    }

    @Override
    public List<Variacion> getReporteVariacionAgrupado(Parametros filtros) {
        log.debug("----------------------------- getReporteVariacionAgrupado -------------------------------");

        List<Variacion> result = new ArrayList<>();
//        log.debug("iniciando busqueda");
        Map<String, List<VariacionLinea>> groupByCC
                = getReporteVariacion(filtros)
                        .stream()
                        .collect(Collectors.groupingBy(VariacionLinea::getCodigoCC));

//        log.debug("iniciando agrupacion->" + groupByCC.size());
        for (Map.Entry<String, List<VariacionLinea>> entry : groupByCC.entrySet()) {

            VariacionLinea first = entry.getValue().get(0);
            Variacion header = new Variacion();
            header.setCodigoCC(first.getCodigoCC());
            header.setNombreCC(first.getNombreCC());
            header.setResponsable(first.getResponsable());
            header.setLineas(entry.getValue());
            header.setTotal(sumarPresuesto(entry.getValue()));
            result.add(header);
        }
//        log.debug("proceso finalizado");
        return result.stream()
                .sorted(Comparator.comparing(Variacion::getCodigoCC))
                .collect(Collectors.toList());

    }

    @Override
    public List<PresupuestoUenLinea> getReportePresupuestoUen(Parametros filtros) {
        log.debug("----------------------------- getReportePresupuestoUen -------------------------------");
//        log.debug(filtros.filtrosPresupuestoUen());
        List<PresupuestoUenLinea> result;
        try {

            StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("getReportePresupuestoUen");
            proc.setParameter("p_periodo_inicial", filtros.getPeriodoInicial());
            proc.setParameter("p_periodo_final", filtros.getPeriodoFinal());
            proc.setParameter("p_anio", filtros.getAnio());
            proc.setParameter("p_tipo_vista", filtros.getTipoVista());
            proc.setParameter("p_id_uens", filtros.getIduens());
            proc.setParameter("p_id_ccs", filtros.getIdccs());
            proc.setParameter("p_idioma", filtros.getIdioma());
            proc.execute();
            result = proc.getResultList();
        } catch (Exception e) {
            log.debug(e.getLocalizedMessage(), e);
            result = new ArrayList<>();
        }
//        log.debug("elementos encontrados ->" + result.size());
        return result;
    }

    @Override
    public PresupuestoUen getReportePresupuestoUenAgrupado(Parametros filtros) {
        log.debug("----------------------------- getReportePresupuestoUenAgrupado -------------------------------");
        PresupuestoUen response = new PresupuestoUen();

        List<PresupuestoUen> result = new ArrayList<>();
        List<PresupuestoUenLinea> lineas = getReportePresupuestoUen(filtros);

//        log.debug("iniciando busqueda");
        Map<String, List<PresupuestoUenLinea>> groupByUen
                = lineas.stream().collect(Collectors.groupingBy(PresupuestoUenLinea::getCompany));

//        log.debug("iniciando agrupacion->" + groupByUen.size());
        for (Map.Entry<String, List<PresupuestoUenLinea>> entry : groupByUen.entrySet()) {
            PresupuestoUenLinea first = entry.getValue().get(0);
            PresupuestoUen header = new PresupuestoUen();
            header.setNombreUen(first.getNombreUen());
            header.setLineas(entry.getValue());
            header.setTotales(sumarPresuesto(entry.getValue()));
            result.add(header);
        }

        response.setPresupuestos(result);

        //
        if (filtros.getIduens() == null || filtros.getIduens().contains(",")) {
//            log.debug("pasar si son todas las uen o son almenos dos uens");

            Map<Object, List<PresupuestoUenLinea>> all;
            if (filtros.getTipoVista() == 1) {
                all = lineas.stream().collect(Collectors.groupingBy(PresupuestoUenLinea::getIdCategoria));
            } else {
                all = lineas.stream().collect(Collectors.groupingBy(PresupuestoUenLinea::getCodigoCC));
            }

            List<PresupuestoUenLinea> totalesAll = new ArrayList<>();
            Presupuesto totalAll = new Presupuesto();
            for (Map.Entry<Object, List<PresupuestoUenLinea>> entry : all.entrySet()) {
                PresupuestoUenLinea tipo = entry.getValue().get(0);
                Presupuesto suma = sumarPresuesto(entry.getValue());
                tipo.setDisponible(suma.getDisponible());
                tipo.setComprometido(suma.getComprometido());
                tipo.setErogado(suma.getErogado());
                tipo.setOperativo(suma.getOperativo());
                tipo.setOriginal(suma.getOriginal());
                tipo.setTransferencias(suma.getTransferencias());
                tipo.setIncrementosDecrementos(suma.getIncrementosDecrementos());
                totalesAll.add(tipo);
                suma(totalAll, tipo);
            }
            response.setLineas(totalesAll);
            response.setNombreUen("ALL");
            response.setTotales(totalAll);
        }

//        log.debug("proceso finalizado");
        return response;
    }

    private Presupuesto sumarPresuesto(List<? extends Presupuesto> lineas) {
        Presupuesto totales = new Presupuesto();
        for (Presupuesto linea : lineas) {
            suma(totales, linea);
        }
        return totales;
    }

    private <T extends Presupuesto> void suma(T a, T b) {
        a.setDisponible(a.getDisponible() + b.getDisponible());
        a.setComprometido(a.getComprometido() + b.getComprometido());
        a.setErogado(a.getErogado() + b.getErogado());
        a.setOperativo(a.getOperativo() + b.getOperativo());
        a.setOriginal(a.getOriginal() + b.getOriginal());
        a.setTransferencias(a.getTransferencias() + b.getTransferencias());
        a.setIncrementosDecrementos(a.getIncrementosDecrementos() + b.getIncrementosDecrementos());
    }

    @Override
    public List<EstadoCuenta> getReporteEstadoCuenta(Parametros filtros) {
        log.debug("----------------------------- getReporteEstadoCuenta -------------------------------");
        return this.getMovimientos(filtros, "getReporteEstadoCuenta");
    }
    
    @Override
    public List<GastosTravel> getReporteGastosTravel(Parametros filtros) {
        log.debug("----------------------------- getReporteE gastos Travel -------------------------------");
        log.debug("p_uen" + filtros.getIdUen());
        log.debug("p_periodo_inicial" + filtros.getPeriodoInicial());
        log.debug("p_periodo_final" + filtros.getPeriodoFinal());
        log.debug("p_id_cc" + filtros.getCcInicial());
        log.debug("p_idioma" + filtros.getIdioma());
        List<GastosTravel> result;
        try {
            StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("getGastosTravel");
            proc.setParameter("p_uen", filtros.getIdUen());
            proc.setParameter("p_periodo_inicial", filtros.getPeriodoInicial());
            proc.setParameter("p_periodo_final", filtros.getPeriodoFinal());
            proc.setParameter("p_id_cc", filtros.getCcInicial());
            proc.setParameter("p_idioma", filtros.getIdioma());
            proc.execute();
            result = proc.getResultList();
        } catch (Exception e) {
            log.debug(e.getLocalizedMessage(), e);
            result = new ArrayList<>();
        }
        log.debug("elementos encontrados gastos->" + result.size());
        return result;
    }
    
    @Override
    public List<GastosProject> getReporteGastosProject(Parametros filtros) {
        log.debug("----------------------------- getReporteE gastos Travel -------------------------------");
        List<GastosProject> result;
        try {
            StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("getGastosProyecto");
            proc.setParameter("p_uen", filtros.getIdUen());
            proc.setParameter("p_periodo_inicial", filtros.getPeriodoInicial());
            proc.setParameter("p_periodo_final", filtros.getPeriodoFinal());
            proc.setParameter("p_id_proyecto", Integer.parseInt(filtros.getCcInicial()));
            proc.setParameter("p_idioma", filtros.getIdioma());
            proc.setParameter("p_tareas", filtros.getIdcategoriasOrigen());
            proc.setParameter("p_tipos_gasto", filtros.getIdcategoriasDestino());
            proc.execute();
            result = proc.getResultList();
        } catch (Exception e) {
            log.debug(e.getLocalizedMessage(), e);
            result = new ArrayList<>();
        }
        log.debug("elementos encontrados gastos->" + result.size());
        return result;
    }
    
    @Override
    public List<EstadoCuentaProject> getReporteEdoCuentaProject(Parametros filtros) {
        log.debug("----------------------------- getReporteEdoCuenta Project -------------------------------");
//        log.debug(filtros.filtrosVariacion());
        List<EstadoCuentaProject> result;
       
        try {

            StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("getEstadoCuentaProyecto");
            //proc.setParameter("P_ID_USUARIO", null);
            //proc.setParameter("P_ID_UEN", null);
            proc.setParameter("P_ID_PROYECTO", Integer.parseInt(filtros.getCcInicial()));
            proc.setParameter("P_ID_TAREA", filtros.getIdcategoriasOrigen());
            proc.setParameter("P_TIPO_GASTO", filtros.getIdcategoriasDestino());
            proc.execute();
            result = proc.getResultList();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
            result = new ArrayList<>();
        }
        log.debug("elementos encontrados de estado de cuenta ->" + result.size());
        return result;
    }
    
    @Override
    public List<Incrementos> getReporteIncremento(Parametros filtros) {
        log.debug("----------------------------- getReporteIncremento -------------------------------");
//        log.debug(filtros.toString());

        List<Incrementos> result;
        try {

            StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("Incrementos.getReporteIncremento");
            proc.setParameter("p_num_solicitud", filtros.getNumeroSolicitud());
            proc.setParameter("p_idioma", filtros.getIdioma());
            proc.setParameter("p_id_uens", filtros.getIduens());
            proc.setParameter("p_id_ccs", filtros.getIdccs());
            proc.setParameter("p_requisitores", filtros.getIdrequisitores());
            proc.setParameter("p_usuarios", filtros.getIdaprobadores());
            proc.setParameter("p_show_all", "" + filtros.isShowAll());
            proc.setParameter("p_categorias", filtros.getIdcategorias());
            proc.setParameter("p_periodo_inicial", filtros.getPeriodoInicial());
            proc.setParameter("p_periodo_final", filtros.getPeriodoFinal());
            proc.setParameter("p_fecha_inicial", filtros.getFechaInicial());
            proc.setParameter("p_fecha_final", filtros.getFechaFinal());
            proc.execute();
            result = proc.getResultList();

        } catch (Exception e) {
            log.debug(e.getLocalizedMessage(), e);
            result = new ArrayList<>();
        }

        return result;
    }

    @Override
    public List<Incrementos> getReporteIncrementoEdo(Parametros filtros) {
        log.debug("----------------------------- getReporteIncrementoEdo -------------------------------");
        return this.getMovimientos(filtros, "Incrementos.getReporteIncrementoEdo");
    }

    @Override
    public List<Transferencias> getReporteTransferencias(Parametros filtros) {
        log.debug("----------------------------- getReporteTransferencias -------------------------------");
//        log.debug(filtros.filtrosTransferencia());

        List<Transferencias> result;
        try {

            StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("Transferencias.getReporteTransferencias");
            proc.setParameter("p_num_solicitud", filtros.getNumeroSolicitud());
            proc.setParameter("p_idioma", filtros.getIdioma());
            proc.setParameter("p_id_uens", filtros.getIduens());
            proc.setParameter("p_id_uens_origen", filtros.getIduensorigen());
            proc.setParameter("p_id_uens_destino", filtros.getIduensdestino());
            proc.setParameter("p_requisitores", filtros.getIdrequisitores());
            proc.setParameter("p_creadores", filtros.getIdcreadores());
            proc.setParameter("p_show_all", "" + filtros.isShowAll());
            proc.setParameter("p_categorias_origen", filtros.getIdcategoriasOrigen());
            proc.setParameter("p_categorias_destino", filtros.getIdcategoriasDestino());
            proc.setParameter("p_id_ccs", filtros.getIdccs());
            proc.setParameter("p_id_ccs_origen", filtros.getIdccsOrigen());
            proc.setParameter("p_id_ccs_destino", filtros.getIdccsDestino());
            proc.setParameter("p_periodos", filtros.getPeriodos());
            proc.setParameter("p_fecha_inicial", filtros.getFechaInicial());
            proc.setParameter("p_fecha_final", filtros.getFechaFinal());
            proc.execute();
            result = proc.getResultList();

        } catch (Exception e) {
            log.debug(e.getLocalizedMessage(), e);
            result = new ArrayList<>();
        }

        return result;
    }

    @Override
    public List<Transferencias> getReporteTransferenciasEdo(Parametros filtros) {
        log.debug("----------------------------- getReporteTransferenciasEdo -------------------------------");
        return this.getMovimientos(filtros, "Transferencias.getReporteTransferenciasEdo");
    }

    public List getMovimientos(Parametros filtros, String procedure) {
//        log.debug(filtros.filtrosEstadoCuenta());
        List result;
        log.debug("p_uen---> " + filtros.getIdUen());
        log.debug("p_periodo_inicial---> " + filtros.getPeriodoInicial());
        log.debug("p_periodo_final---> " + filtros.getPeriodoFinal());
        log.debug("p_id_cc---> " + filtros.getIdccs());
        log.debug("p_idioma---> " + filtros.getIdioma());
        
        try {

            StoredProcedureQuery proc = em.createNamedStoredProcedureQuery(procedure);
            proc.setParameter("p_uen", filtros.getIdUen());
            proc.setParameter("p_periodo_inicial", filtros.getPeriodoInicial());
            proc.setParameter("p_periodo_final", filtros.getPeriodoFinal());
            proc.setParameter("p_id_cc", filtros.getIdccs());
            proc.setParameter("p_idioma", filtros.getIdioma());
            proc.execute();
            result = proc.getResultList();

        } catch (Exception e) {
            log.debug(e.getLocalizedMessage(), e);
            result = new ArrayList<>();
        }

        return result;
    }

    @Override
    public List<Usuarios> getUsuarios(String tipoReporte, String tipoUsuario) {
        List result;
        try {

            StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("Usuarios.getUsuariosTransaccion");
            proc.setParameter("p_tipo_reporte", tipoReporte);
            proc.setParameter("p_tipo_usuario", tipoUsuario);
            proc.execute();
            result = proc.getResultList();
        } catch (Exception e) {
            result = new ArrayList<>();
        }

        return result;
    }

    public String getIdPeriodos(int inicio, int fin) {
        List<Periodos> periodos = repository.findByIdiomaAndIdBetween("ESA", inicio, fin);
        String ids = periodos.stream()
                .map(Periodos::getNombreOriginal)
                .collect(Collectors.joining("','"));

        return ids.isEmpty() ? null : "'" + ids + "'";
    }

}
