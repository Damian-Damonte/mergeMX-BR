package com.metalsa.contralor.controller;

import com.google.gson.JsonSyntaxException;
import com.metalsa.aprobacion.model.NvcTblOrganizacionesH;
import com.metalsa.aprobacion.model.UenWithDefault;
import com.metalsa.aprobacion.repository.OrganizacionesRepository;
import com.metalsa.contralor.model.CentroCostoProceso;
import com.metalsa.contralor.model.CentroCostoProcesoUpdate;
import com.metalsa.contralor.model.Configuracion;
import com.metalsa.contralor.model.ContralorResponse;
import com.metalsa.contralor.model.Mes;
import com.metalsa.contralor.model.Procesos;
import com.metalsa.contralor.model.ProcesosUpdate;
import com.metalsa.contralor.model.ProcessUenCC;
import com.metalsa.contralor.model.RequestBodyConfiguration;
import com.metalsa.contralor.model.RequestBodyContralor;
import com.metalsa.contralor.model.ResponsableProceso;
import com.metalsa.contralor.model.Valores;
import com.metalsa.contralor.repository.ConfiguracionRepository;
import com.metalsa.contralor.repository.ProcesoRepository;
import com.metalsa.contralor.service.ConfiguracionContralorService;
import com.metalsa.finanzas.model.AdjuntoPojo;
import com.metalsa.finanzas.model.RespuestaPojo;
import com.metalsa.utils.Constants;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Oscar del Angel
 */
@RestController
@RequestMapping(Constants.URI_API_CONTRALOR)
@CrossOrigin
@Log4j
public class ConfiguracionContralorController {

    @Autowired
    private ConfiguracionRepository configuracionRepository;

    @Autowired
    private ProcesoRepository procesoRepository;

    @Autowired
    private OrganizacionesRepository organizacionesRepository;

    @Autowired
    private ConfiguracionContralorService configuracionContralorService;

    @GetMapping(path = "/configuraciones", params = "uen")
    public Iterable<Configuracion> configuracionesUen(Integer uen) {
        return this.configuracionRepository.findAllByUen(uen);
    }

    @GetMapping(path = "/configuracion", params = {"uen", "name"})
    public Configuracion configuracionUen(Integer uen, String name) {
        return this.configuracionRepository.findByUenName(uen, name);
    }

    @PostMapping(path = "/configuraciones/nombres")
    @ResponseBody
    public Iterable<Configuracion> configuraciones(@RequestParam(name = "uen") Integer uen, @RequestBody List<String> keys) {
        List<Configuracion> response = new ArrayList<>();
        for (String key : keys) {
            Configuracion configuration = this.configuracionRepository.findByUenName(uen, key);
            if (configuration != null) {
                response.add(configuration);
            }
        }
        return response;
    }

    @GetMapping(path = "/months", params = "idioma")
    public Iterable<Mes> configuracionUen(String idioma) {
        return this.configuracionRepository.findMonthsByLanguage(idioma);
    }

    @GetMapping(path = "/value", params = {"uen", "name"})
    public Valores singlevalue(Integer uen, String name) {
        return this.configuracionRepository.findSingleValue(uen, name);
    }

    @GetMapping(path = "/authorizedUens", params = {"name", "idUsuario"})
    public Iterable<NvcTblOrganizacionesH> getAuthorizedUensByName(String name, String idUsuario) {
        return this.configuracionRepository.getAuthorizedUensByName(name, idUsuario);
    }

    @PostMapping(path = "/update")
    @ResponseBody
    public RespuestaPojo updateconfiguracionUen(@RequestBody RequestBodyConfiguration request) {
        RespuestaPojo response = this.configuracionRepository.updateConfiguration(request);
        response.setListResult(this.configuracionRepository.findAllByUen(request.getUen()));
        return response;
    }

    //<R39943>
    @GetMapping(path = "/procesos")
    public Iterable<Procesos> getProcesos() {
        return this.procesoRepository.findAllProcess();
    }
    //<R39943>

    @GetMapping(path = "/procesosuen", params = {"uen", "parent"})
    public Iterable<Procesos> getProcesosUen(Integer uen, Integer parent) {
        return this.procesoRepository.findAllProcessUen(uen, parent);
    }

    @GetMapping(path = "/procesosuencc", params = {"uen", "idioma"})
    public Iterable<ProcessUenCC> getProcesosUenCC(Integer uen, String idioma) {
        return this.procesoRepository.findAllProcessUenCC(uen, idioma);
    }

    @GetMapping(path = "/procesosuenlevel", params = {"uen", "level"})
    public Iterable<Procesos> getProcesosUenIdioma(Integer uen, Integer level) {
        return this.procesoRepository.findAllProcessUenLang(uen, level);
    }

    @PostMapping(path = "/procesosccs")
    @ResponseBody
    public Iterable<CentroCostoProceso> getCentroCostoProcesos(@RequestBody RequestBodyContralor rb, @RequestHeader(value = "Authorization") String token) {
        log.debug("tengo hambre karen" + token);
        log.debug("ConfiguracionContralorController.getCentroCostoProcesos token:" + token);
        Iterable<CentroCostoProceso> listCC = this.procesoRepository.findCostsCenterProcess(rb);

        for (CentroCostoProceso cc : listCC) {
            cc.setLineas(this.procesoRepository.findDetailCC(rb.getUen(), cc.getIdCC()));
        }
        return listCC;
    }

    @PostMapping(path = "/countprocesosccs")
    @ResponseBody
    public Long findCostsCenterProcessCount(@RequestBody RequestBodyContralor rb) {
        return this.procesoRepository.findCostsCenterProcessCount(rb);
    }

    @PostMapping(path = "/updateusuariosccs")
    @ResponseBody
    public ContralorResponse updateUsuariosCCS(@RequestBody CentroCostoProcesoUpdate ccs, @RequestHeader(value = "Authorization") String token) {

        ContralorResponse result = this.procesoRepository.updateUsersCCS(ccs);
        return result;
    }

    @PostMapping(path = "/updateprocess")
    @ResponseBody
    public ContralorResponse updateProcess(@RequestBody ProcesosUpdate ccs) {
        return this.procesoRepository.UpdateProcess(ccs);
    }

    @PostMapping(path = "/updateprocessuencc")
    @ResponseBody
    public ContralorResponse updateProcessUenCc(@RequestBody ProcesosUpdate request) {
        return this.procesoRepository.addUpdateProcessUenCC(request);
    }

    @GetMapping(path = "/uenscontralor", params = {"user", "process"})
    public Iterable<UenContralor> getUensByApprovalProcess(String user, String process) {
        Iterable<UenContralor> result = null;
        if ("SPX_ADM_CONTROLER_UEN_READ_ONLY".equals(process)) {
            List<UenWithDefault> uenList = organizacionesRepository.getUensActivasByUser(user);
            result = this.convierteUens(uenList);
        } else {
            result = this.configuracionRepository.getUensByApprovalProcess(user, process);
        }
        return result;
    }

    private Iterable<UenContralor> convierteUens(List<UenWithDefault> uenList) {
        List<UenContralor> newList = new ArrayList<>();
        for (UenWithDefault u : uenList) {
            UenContralor newUen = new UenContralor();
            newUen.setIdUen(u.getId().intValue());
            newUen.setNombreUen(u.getNombre());
            newUen.setCompania(u.getDescripcion());
            newUen.setNombreCompania(u.getDescripcion());
            newUen.setMoneda(u.getCurrency());
            newList.add(newUen);
        }
        return newList;
    }

    @GetMapping(path = "/uensbyparamvalue", params = {"param", "value"})
    public Iterable<UenContralor> getUensByParamValue(String param, String value) {
        return this.configuracionRepository.getUensByParamValue(param, value);
    }

    @GetMapping(path = "proceso/responsiblebylevel", params = {"uen", "level"})
    public Iterable<ResponsableProceso> getResponsiblesByLevel(Integer uen, Integer level) {
        return this.procesoRepository.getResponsiblesByLevel(uen, level);
    }

    @PostMapping(path = "/excelconfiguracionresponsabledelegado")
    @ResponseBody
    public ResponseEntity<InputStreamResource> getExcelConfiguracionResponsableDelegado(@RequestBody RequestBodyContralor rb) {
        ByteArrayInputStream in = null;
        HttpHeaders headers = null;
        try {
            in = configuracionContralorService.getExcelConfiguracionResponsableDelegado(rb);
            headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=excelconfiguracionresponsabledelegado.xlsx");
            headers.add("Content-Type", "application/xlsx");

        } catch (JsonSyntaxException e) {
            log.error("Error al obtener los datos" + e.getMessage());
        }
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }

    @PostMapping(path = "/downloadPlantilla")
    @ResponseBody
    public ResponseEntity<InputStreamResource> downloadPlantilla(@RequestBody RequestBodyContralor rb) throws IOException {
        ByteArrayInputStream in = null;
        HttpHeaders headers = null;
        try {
            if (rb.getTipo() != null && rb.getTipo() == 2) { // 2 completo 
                rb.setCcs(null);
                rb.setDelegates(null);
                rb.setProcess(null);
                rb.setResponsiblescc(null);
                rb.setResponsiblesgroup(null);
                rb.setGroups(null);
                rb.setFistUpdate(null);
            }
            rb.setPagestart(1);
            rb.setPageend(99999);//Todos los registros
            in = configuracionContralorService.creaPlantillaCc(rb); // donde null es listCategoria, aqui no se usa por lo tanto es null
            headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=plantilla.xlsx");
            headers.add("Content-Type", "application/xlsx");
        } catch (JsonSyntaxException e) {
            log.error("Error al obtener los datos" + e.getMessage());
        }
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }

    @PostMapping(path = "/uploadPlantilla")
    @ResponseBody
    public Iterable<CentroCostoProceso> uploadPlantilla(@RequestBody AdjuntoPojo data, @RequestHeader(value = "Authorization") String token) throws IOException {
        List<CentroCostoProceso> datosTabla = null;
        try {
            log.debug("ConfiguracionContralorController.uploadPlantilla token:" + token);
            datosTabla = configuracionContralorService.uploadPlantilla(data);
            log.debug("datos tabla token:" + datosTabla.size());
        } catch (Throwable t) {
            log.error(t);
        }
        return datosTabla;
    }
}
