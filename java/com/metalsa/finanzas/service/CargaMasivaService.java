package com.metalsa.finanzas.service;

import com.metalsa.aprobacion.model.CentroCosto;
import com.metalsa.aprobacion.model.UenWithDefault;
import com.metalsa.aprobacion.repository.CentroCostoRepository;
import com.metalsa.aprobacion.repository.OrganizacionesRepository;
import com.metalsa.core.model.Periodos;
import com.metalsa.core.repository.PeriodosRepository;
import com.metalsa.finanzas.model.AdjuntoPojo;
import com.metalsa.finanzas.model.CategoriaIdioma;
import com.metalsa.finanzas.model.SeguimientoSolicitud;
import com.metalsa.finanzas.model.SeguimientoTransferencia;
import com.metalsa.finanzas.repository.CargaMasivaRepository;
import com.metalsa.finanzas.repository.CategoriaIdiomaRepository;
import com.metalsa.utils.Constants;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author JL
 */
@RestController
@RequestMapping(Constants.URI_API_FINAZAS)
@Log4j
public class CargaMasivaService {

    @Autowired
    private CategoriaIdiomaRepository categoriaIdiomaRepository;
    @Autowired
    private PeriodosRepository periodosRepository;
    @Autowired
    private OrganizacionesRepository orgs;
    @Autowired
    private CentroCostoRepository centros;

    @RequestMapping(value = "/transaccion/leerExcel", method = RequestMethod.POST)
    @ResponseBody
    public Iterable<SeguimientoSolicitud> leerExcel(@RequestBody AdjuntoPojo data) {
        log.debug(" **** leerExcel *** ");
        CargaMasivaRepository c = new CargaMasivaRepository();
        return c.procesarExcelTransaccion(data);
    }

    @RequestMapping(value = "/transaccion/leerExcelTransaferencia", method = RequestMethod.POST)
    @ResponseBody
    public Iterable<SeguimientoTransferencia> leerExcelTransaferencia(@RequestBody AdjuntoPojo data) {
        CargaMasivaRepository c = new CargaMasivaRepository();
        return c.procesarExcelTransferencia(data);
    }

    @RequestMapping(value = "/transaccion/downloadPlantilla", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<InputStreamResource> downloadPlantilla(@RequestBody Map<String, Object> data) {
        CargaMasivaRepository c = new CargaMasivaRepository();
        ByteArrayInputStream in = null;
        HttpHeaders headers = null;
        List<String> uen_cc = new ArrayList<>();
        List<CentroCosto> cc = new ArrayList<>();
        try {
            String lang = data.get("lang").toString();
            String user = data.get("user").toString();
            int process = Integer.valueOf(data.get("process").toString());
            Iterable<CategoriaIdioma> listaCaregoria = categoriaIdiomaRepository.findAllByIdioma(Constants.getIdioma(lang));
            List<Periodos> listaPeriodo = periodosRepository.getAllByAnioActualDisponible(Constants.getIdioma(lang));
            List<UenWithDefault> listaUen = orgs.getUensActivasByUserRol(user, process);;
            List<String> listaTransaccion = (List<String>) data.get("transaccion");
            List<String> listaColumnas = (List<String>) data.get("columnasExcel");
            for (int i = 0; i < listaUen.size(); i++) {
                cc = centros.getAllByIdUenAndIdioma(listaUen.get(i).getId(), lang);
                for (int x = 0; x < cc.size(); x++) {
                    uen_cc.add(listaUen.get(i).getNombre() + "-" + cc.get(x).getCodigo());
                }
            }
            in = c.creaPlantillaTransaccion(listaCaregoria, listaPeriodo, lang, listaTransaccion, listaColumnas, uen_cc, "Incremento");
            headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=plantilla.xlsx");
            headers.add("Content-Type", "application/xlsx");

        } catch (Exception e) {
            log.error("Error al obtener los datos" + e.getMessage());
        }
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }

    @RequestMapping(value = "/transaccion/downloadPlantillaTransferencia", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<InputStreamResource> downloadPlantillaTransferencia(@RequestBody Map<String, Object> data) {
        CargaMasivaRepository c = new CargaMasivaRepository();
        ByteArrayInputStream in = null;
        HttpHeaders headers = null;
        List<String> uen_cc = new ArrayList();
        List<CentroCosto> cc;
        try {
            String lang = data.get("lang").toString();
            String user = data.get("user").toString();
            int process = Integer.valueOf(data.get("process").toString());
            List<Periodos> listaPeriodo = periodosRepository.getAllByAnioActualDisponible(Constants.getIdioma(lang));
            List<UenWithDefault> listaUen = orgs.getUensActivasByUserRol(user, process);;
            List<String> listaColumnas1 = (List<String>) data.get("columnasExcel1");
            List<String> listaColumnaS2 = (List<String>) data.get("columnasExcel2");
            for (int i = 0; i < listaUen.size(); i++) {
//                cc=centros.getAllByRespAndDel(listaUen.get(i).getId(), user, lang);
                cc = centros.getAllByIdUenAndIdioma(listaUen.get(i).getId(), lang);
                for (int x = 0; x < cc.size(); x++) {
                    uen_cc.add(listaUen.get(i).getNombre() + "-" + cc.get(x).getCodigo());
                }
            }

            Iterable<CategoriaIdioma> listaCaregoria = categoriaIdiomaRepository.findAllByIdioma(Constants.getIdioma(lang));
            in = c.creaPlantillaTransaccion(listaCaregoria, listaPeriodo, lang, listaColumnas1, listaColumnaS2, uen_cc, "Transferencia");
            headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=plantilla.xlsx");
            headers.add("Content-Type", "application/xlsx");

        } catch (Exception e) {
            log.error("Error al obtener los datos" + e.getMessage());
        }
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }

}
