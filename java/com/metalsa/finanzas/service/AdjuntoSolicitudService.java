package com.metalsa.finanzas.service;

import com.metalsa.finanzas.model.AdjuntoPojo;
import com.metalsa.finanzas.model.AdjuntoSolicitud;
import com.metalsa.finanzas.repository.AdjuntoSolicitudRepository;
import com.metalsa.finanzas.repository.UploadFileRepository;
import com.metalsa.utils.Constants;
import java.io.IOException;
import java.util.Arrays;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author JL
 */
@RestController
@RequestMapping(Constants.URI_API_FINAZAS)
@Log4j
public class AdjuntoSolicitudService {
   
    @Autowired
    private AdjuntoSolicitudRepository adjuntoSolicitudRepository;
    
    @Autowired
    private UploadFileRepository uploadFileRepository;
    
    @Getter
    @Setter
    @Value("${ftp.host}")
    private String host;
    
    @Getter
    @Setter
    @Value("${ftp.port}")
    private Integer port;
    
   
    @Getter
    @Setter
    @Value("${ftp.user}")
    private String user;
    

    @Getter
    @Setter
    @Value("${ftp.password}")
    private String pass;
    
    
    @RequestMapping(value = "/adjuntos/{id}",method = RequestMethod.GET)
    public Iterable<AdjuntoSolicitud> getAllByIdSolicitudPresupuesto(@PathVariable(value = "id") Long id){
        return adjuntoSolicitudRepository.findAllByIdSolicitudPresupuesto(id);
    }
    
    
    @RequestMapping(path = "/adjuntos/download", method = RequestMethod.GET)
    public ResponseEntity<ByteArrayResource> download(@RequestParam("url") String url,
                                             @RequestParam("files") String[] names,
                                             @RequestParam("ftpName") String ftpName,
                                             @RequestParam("fileName") String fileName,
                                             @RequestParam("notFoundImage") boolean notFoundImage) throws IOException {
        
        AdjuntoPojo file = uploadFileRepository.zipFiles(ftpName, fileName, url, false, Arrays.asList(names),host, port, user, pass);
        if (file != null) {
                    String mimeType = file.getMime();
                    if (mimeType == null) {
                        mimeType = "application/octet-stream";
                    }
                    
                    //Path path = Paths.get(file.getAbsolutePath());
                    //ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
                    ByteArrayResource resource = new ByteArrayResource(file.getFile());

                    HttpHeaders headers = new HttpHeaders();
                    String headerKey = "Content-Disposition";
                    String headerValue = String.format("attachment; filename=\"%s\"", file.getNombreArchivo());
                    headers.add(headerKey, headerValue);
                    
                    log.info("Documento FTP descargado correctamente");
                    
                    return ResponseEntity.ok()
                            .headers(headers)
                            .contentLength(file.getPeso())
                            .contentType(MediaType.parseMediaType(mimeType))
                            .body(resource);
        
                    
                } else {
                    log.info("No existe archivo");
                    return (ResponseEntity<ByteArrayResource>) ResponseEntity.notFound();
                }

        
    }
}
