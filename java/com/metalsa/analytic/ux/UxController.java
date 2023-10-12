package com.metalsa.analytic.ux;

import com.metalsa.analytic.ux.aprobaciones.ApprovalUxCapture;
import com.metalsa.analytic.ux.aprobaciones.ApprovalUxCaptureRepository;
import com.metalsa.analytic.ux.recibos.RecibosUxCapture;
import com.metalsa.analytic.ux.recibos.RecibosUxCaptureRepository;
import com.metalsa.analytic.ux.seleccion.SeleccionUxCapture;
import com.metalsa.analytic.ux.seleccion.SeleccionUxCaptureRepository;
import com.metalsa.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@RestController
@RequestMapping(Constants.URI_API_UX)
@CrossOrigin
public class UxController {

    private ApprovalUxCaptureRepository aprobaciones;
    
    private SeleccionUxCaptureRepository seleccion;
    
    private RecibosUxCaptureRepository recibos;
    
    @Autowired
    public UxController(ApprovalUxCaptureRepository aprobaciones,SeleccionUxCaptureRepository seleccion,RecibosUxCaptureRepository recibos) {
        this.aprobaciones = aprobaciones;
        this.seleccion = seleccion;
        this.recibos = recibos;
    }
    
    @PostMapping("/aprobaciones")
    public ResponseEntity captureFiltrosAprobaciones(@RequestBody ApprovalUxCapture capture) {
        aprobaciones.save(capture);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @PostMapping("/seleccion")
    public ResponseEntity captureFiltrosSeleccion(@RequestBody SeleccionUxCapture capture) {
        seleccion.save(capture);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @PostMapping("/recibos")
    public ResponseEntity captureFiltrosRecibos(@RequestBody RecibosUxCapture capture) {
        recibos.save(capture);
        return new ResponseEntity(HttpStatus.OK);
    }
}
