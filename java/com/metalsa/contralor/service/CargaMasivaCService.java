package com.metalsa.contralor.service;

import com.metalsa.aprobacion.model.CentroCosto;
import com.metalsa.aprobacion.model.UsuarioUen;
import com.metalsa.aprobacion.repository.CentroCostoRepository;
import com.metalsa.aprobacion.repository.UsuarioUenRepository;
import com.metalsa.contralor.model.AdjuntoPojoP;
import com.metalsa.contralor.model.Procesos;
import com.metalsa.contralor.model.ProcessUenCC;
import com.metalsa.contralor.model.ReponseProcesos;
import com.metalsa.contralor.repository.CargaMasivaCRepository;
import com.metalsa.contralor.repository.ProcesoRepository;
import com.metalsa.utils.Constants;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
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
 * @author Lorena
 */
@RestController
@RequestMapping(Constants.URI_API_CONTRALOR_P)
@Log4j
public class CargaMasivaCService {

    @Autowired
    private CentroCostoRepository centros;
    
    @Autowired
    private ProcesoRepository procesos;
    
    @Autowired
    private UsuarioUenRepository usuariosPorUen;
    
    @RequestMapping(value = "/leerExcel", method = RequestMethod.POST)
    @ResponseBody
    public  ReponseProcesos leerExcel(@RequestBody AdjuntoPojoP data) {
        CargaMasivaCRepository c = new CargaMasivaCRepository();
        return c.procesarExcelTransaccion(data);
    }

    @RequestMapping(value = "/downloadPlantilla", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<InputStreamResource>  downloadPlantilla(@RequestBody Map<String,Object> data) {
        CargaMasivaCRepository c = new CargaMasivaCRepository();
        ByteArrayInputStream in = null;
        HttpHeaders headers = null;
        List<ProcessUenCC>procesos= new ArrayList<ProcessUenCC>();
        List<CentroCosto>cc= new ArrayList<CentroCosto>();
        List  procesosData = new ArrayList();
        List<Procesos>subprocesosGeneralesUen= new ArrayList<Procesos>();
        List<Procesos>procesosGeneralesUen= new ArrayList<Procesos>();
        try {
            String lang= data.get("lang").toString();
            long uenId= Long.valueOf(data.get("idUen").toString());
            String nombreUen= data.get("nombreUen").toString();
            String infoUen= data.get("columnasExcel").toString();
            List<String> listaColumnas =(List<String>) data.get("columnasExcel2"); 
            cc=centros.getAllByIdUenAndIdioma(uenId, lang);
            List<UsuarioUen>listaUsuario=usuariosPorUen.findAllByIdUenOrderByIdUsuario(uenId);
            in = c.creaPlantillaProcesos(lang,listaColumnas, cc, nombreUen,listaUsuario, infoUen, "Empty", procesos, procesosData, 
                    Integer.valueOf(data.get("idUen").toString()),subprocesosGeneralesUen, procesosGeneralesUen);
           
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
    
    @RequestMapping(value = "/downloadEditarPlantilla", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<InputStreamResource>  downloadEditarPlantilla(@RequestBody Map<String,Object> data) {
        CargaMasivaCRepository c = new CargaMasivaCRepository();
        ByteArrayInputStream in = null;
        HttpHeaders headers = null;
        List<CentroCosto>cc= new ArrayList<CentroCosto>();
        List<ProcessUenCC>proceso= new ArrayList<ProcessUenCC>();
        List<Procesos>subprocesosGeneralesUen= new ArrayList<Procesos>();
        List<Procesos>procesosGeneralesUen= new ArrayList<Procesos>();
        try {
            String lang= data.get("lang").toString();
            int uenId= Integer.valueOf(data.get("idUen").toString());
            String nombreUen= data.get("nombreUen").toString();
            List  procesosData=(List) data.get("procesosData"); 
            List<String> listaColumnas =(List<String>) data.get("columnasExcel2"); 
            procesosGeneralesUen=procesos.findAllProcessUen(uenId,null);
            subprocesosGeneralesUen=procesos.findAllProcessUenLang(uenId,2);
            proceso=procesos.findAllProcessUenCC(uenId, lang);
            cc=centros.getAllByIdUenAndIdioma(Long.valueOf(data.get("idUen").toString()), lang);
            String infoUen= data.get("columnasExcel").toString();
            List<UsuarioUen>listaUsuario=usuariosPorUen.findAllByIdUenOrderByIdUsuario(Long.valueOf(data.get("idUen").toString()));
            in = c.creaPlantillaProcesos(lang, listaColumnas, cc, nombreUen,listaUsuario, infoUen, "Edit", proceso, procesosData,uenId, 
                    subprocesosGeneralesUen, procesosGeneralesUen);
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


