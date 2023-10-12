package com.metalsa.reportes.controller;

import com.metalsa.reportes.model.Balanza;
import com.metalsa.reportes.model.BalanzaLinea;
import com.metalsa.reportes.model.EstadoCuenta;
import com.metalsa.reportes.model.EstadoCuentaProject;
import com.metalsa.reportes.model.EstadoCuentaTravel;
import com.metalsa.reportes.model.ExportModel;
import com.metalsa.reportes.model.GastosProject;
import com.metalsa.reportes.model.GastosTravel;
import com.metalsa.reportes.model.Incrementos;
import com.metalsa.reportes.model.Parametros;
import com.metalsa.reportes.model.PresupuestoAnual;
import com.metalsa.reportes.model.PresupuestoAnualLinea;
import com.metalsa.reportes.model.PresupuestoUen;
import com.metalsa.reportes.model.PresupuestoUenLinea;
import com.metalsa.reportes.model.Transferencias;
import com.metalsa.reportes.model.Usuarios;
import com.metalsa.reportes.model.Variacion;
import com.metalsa.reportes.model.VariacionLinea;
import com.metalsa.reportes.repository.ExportReportService;
import com.metalsa.reportes.repository.ReportesRepository;
import com.metalsa.utils.Constants;
import java.io.ByteArrayInputStream;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Oscar del Angel
 */
@RestController
@RequestMapping(Constants.URI_API_REPORTES)
@CrossOrigin
@Log4j
public class ReporteController {

    @Autowired
    private ReportesRepository reportesRepository;

    @Autowired
    private ExportReportService fileService;

    @PostMapping(path = "/IncrementosDecrementos")
    @ResponseBody
    public Iterable<Incrementos> getReporteIncrementoDecrementos(@RequestBody Parametros filtros) {
        if (filtros.getTipoVista() == 1) { //incremento de estadoCuenta
            return this.reportesRepository.getReporteIncrementoEdo(filtros);
        }
        return this.reportesRepository.getReporteIncremento(filtros); // incrementos por solicitud
    }

    @PostMapping(path = "/Transferencias")
    @ResponseBody
    public Iterable<Transferencias> getReporteTransferencias(@RequestBody Parametros filtros) {
        if (filtros.getTipoVista() == 1) { //incremento de estadoCuenta
            return this.reportesRepository.getReporteTransferenciasEdo(filtros);
        }
        return this.reportesRepository.getReporteTransferencias(filtros); // incrementos por solicitud
    }

    @PostMapping(path = "/getReporteBalanza")
    @ResponseBody
    public Iterable<BalanzaLinea> getReporteBalanza(@RequestBody Parametros filtros) {
        return this.reportesRepository.getReporteBalanza(filtros);
    }

    @PostMapping(path = "/getReporteBalanzaAgrupado")
    @ResponseBody
    public Iterable<Balanza> getReporteBalanzaAgrupado(@RequestBody Parametros filtros) {
        return this.reportesRepository.getReporteBalanzaAgrupado(filtros);
    }

    @PostMapping(path = "/getReportePresupuestoAnual")
    @ResponseBody
    public Iterable<PresupuestoAnualLinea> getReportePresupuestoAnual(@RequestBody Parametros filtros) {
        return this.reportesRepository.getReportePresupuestoAnual(filtros);
    }

    @PostMapping(path = "/getReportePresupuestoAnualAgrupado")
    @ResponseBody
    public Iterable<PresupuestoAnual> getReportePresupuestoAnualAgrupado(@RequestBody Parametros filtros) {
        return this.reportesRepository.getReportePresupuestoAnualAgrupado(filtros);
    }

    @PostMapping(path = "/getReporteVariacion")
    @ResponseBody
    public Iterable<VariacionLinea> getReporteVariacion(@RequestBody Parametros filtros) {
        return this.reportesRepository.getReporteVariacion(filtros);
    }

    @PostMapping(path = "/getReporteVariacionAgrupado")
    @ResponseBody
    public Iterable<Variacion> getReporteVariacionAgrupado(@RequestBody Parametros filtros) {
        return this.reportesRepository.getReporteVariacionAgrupado(filtros);
    }
    
    @PostMapping(path = "/getReporteEdoCuentaTravel")
    @ResponseBody
    public Iterable<EstadoCuentaTravel> getReporteEdoCuentaTravel(@RequestBody Parametros filtros) {
        return this.reportesRepository.getReporteEdoCuentaTravel(filtros);
    }
    @PostMapping(path = "/getReporteEdoCuentaProject")
    @ResponseBody
    public Iterable<EstadoCuentaProject> getReporteEdoCuentaProject(@RequestBody Parametros filtros) {
        return this.reportesRepository.getReporteEdoCuentaProject(filtros);
    }
    

    @PostMapping(path = "/PresupuestoUen")
    @ResponseBody
    public Iterable<PresupuestoUenLinea> getReportePresupuestoUen(@RequestBody Parametros filtros) {
        return this.reportesRepository.getReportePresupuestoUen(filtros);
    }

    @PostMapping(path = "/PresupuestoUenAgrupado")
    public PresupuestoUen getReportePresupuestoUenAgrupado(@RequestBody Parametros filtros) {
        return this.reportesRepository.getReportePresupuestoUenAgrupado(filtros);
    }

    @PostMapping(path = "/EstadoCuenta")
    public Iterable<EstadoCuenta> getReporteEstadoCuenta(@RequestBody Parametros filtros) {
        return this.reportesRepository.getReporteEstadoCuenta(filtros);
    }
    @PostMapping(path = "/GastosTravel")
    public Iterable<GastosTravel> getReporteGastosTravel(@RequestBody Parametros filtros) {
        return this.reportesRepository.getReporteGastosTravel(filtros);
    }
    
    @PostMapping(path = "/GsstosProject")
    public Iterable<GastosProject> getReporteGastosProject(@RequestBody Parametros filtros) {
        return this.reportesRepository.getReporteGastosProject(filtros);
    }

    @GetMapping(path = "/GetUsuarios", params = {"tipo", "usuario"})
    public Iterable<Usuarios> getUsuarios(String tipo, String usuario) {
        return this.reportesRepository.getUsuarios(tipo, usuario);
    }

    @PostMapping(path = "/descargar")
    @ResponseBody
    public ResponseEntity<InputStreamResource> descargarReporte(@RequestBody ExportModel data) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=report.xlsx");
        headers.add("Content-Type", "application/xlsx");

        ByteArrayInputStream in;
        switch (data.getReporte()) {
            case "VARIACION":
                in = this.fileService.getReporteVariacion(data);
                break;
            case "BALANZA":
                in = this.fileService.getReporteBalanza(data);
                break;
            case "PRESUPUESTO_UEN":
                in = this.fileService.getReporteEdoCuentaUen(data);
                break;
            case "PRESUPUESTO_CC":
                in = this.fileService.getReporteEstadoCuentaCC(data);
                break;
            case "PRESUPUESTO_ANUAL":
                in = this.fileService.getReportePresupuestoAnual(data);
                break;
            case "INCREMENTOS_DECREMENTOS":
                in = this.fileService.getReporteMovimientos(data);
                break;
            case "TRANSFERENCIAS":
                in = this.fileService.getReporteTransferencias(data);
                break;
            case "PROJECT_REPORT":
                in = this.fileService.getReporteProject(data);
                break;
            default:
                in = new ByteArrayInputStream(new byte[]{});
                break;
        }

        ResponseEntity response;
        try {
            response = new ResponseEntity(new InputStreamResource(in), HttpStatus.OK);
        } catch (Exception e) {
            log.debug(e.getLocalizedMessage(), e);
            response = new ResponseEntity(e.getLocalizedMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

}
