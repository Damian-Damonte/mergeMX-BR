package com.metalsa.finanzas.service;

import com.metalsa.finanzas.model.AdjuntoPojo;
import com.metalsa.finanzas.model.AdjuntoTransaccion;
import com.metalsa.finanzas.model.CatalogoPojo;
import com.metalsa.finanzas.model.RespuestaPojo;
import com.metalsa.finanzas.model.TransaccionPojo;
import com.metalsa.finanzas.repository.AdjuntoTransaccionRepository;
import com.metalsa.finanzas.repository.TransaccionFinanzasRepository;
import com.metalsa.finanzas.repository.UploadFileRepository;
import com.metalsa.utils.Constants;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
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
public class TransaccionFinanzasService {

    @Autowired
    private TransaccionFinanzasRepository transaccionFinanzasRepository;

    @Autowired
    private AdjuntoTransaccionRepository adjuntoTransaccionRepository;

    @Autowired
    private UploadFileRepository uploadFileRepository;

    @Getter
    @Setter
    @Value("${iis.env}")
    private String iisEnv;

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

    @RequestMapping(value = "/transaccion/modificar", method = RequestMethod.POST)
    public RespuestaPojo guardarSolicitud(@RequestBody List<TransaccionPojo> data,
            @RequestParam("lang") String idioma) {

        RespuestaPojo respuesta = new RespuestaPojo();
        for (TransaccionPojo data1 : data) {
            
            StringBuffer categorias = new StringBuffer();
            StringBuffer centrosCosto = new StringBuffer();
            for (CatalogoPojo categoria : data1.getCategorias()) {
                categorias = categorias.append(categoria.getId()).append(',');
            }

            for (CatalogoPojo cc : data1.getCentrosCosto()) {
                centrosCosto = centrosCosto.append(cc.getDescripcion()).append(',');
            }

            log.debug("*******************************Inicio incremento/decremento*****************************************");
            log.debug("data1.getIdUen(): " + data1.getIdUen());
            log.debug("centrosCosto: " + centrosCosto.toString());
            log.debug("categorias: " + categorias.toString());
            log.debug("data1.getPeriodo(): " + data1.getPeriodo());
            log.debug("data1.getTipoTransaccion(): " + data1.getTipoTransaccion());
            log.debug("data1.getPorcentaje(): " + data1.getPorcentaje());
            log.debug("data1.getMonto(): " + data1.getMonto());
            log.debug("data1.getMoneda(): " + data1.getMoneda());
            log.debug("data1.getMoneda(): " + data1.getMoneda());
            log.debug("data1.getSolicitante(): " + data1.getSolicitante());
            log.debug("data1.getAprobador(): " + data1.getAprobador());
            log.debug("data1.getRazon(): " + data1.getRazon());
            log.debug("data1.getUsuario(): " + data1.getUsuario());
            log.debug("data1.getIdTransaccion(): " + data1.getIdTransaccion());
            log.debug("*********************************Fin incremento/decremento******************************************");

            respuesta = transaccionFinanzasRepository.modificarPresupuesto(data1.getIdUen(), centrosCosto.toString(),
                    categorias.toString(), data1.getPeriodo(), data1.getTipoTransaccion(),
                    data1.getPorcentaje(), data1.getMonto(), data1.getMoneda(), data1.getSolicitante(),
                    data1.getAprobador(), data1.getRazon(), data1.getUsuario(), data1.getIdTransaccion());

	
            log.debug("respuesta->" + respuesta);
			//MDA_REPORTES_FINANZAS
            if ("".equals(respuesta.getMensaje()) || "OK".equals(respuesta.getMensaje())) {
                AdjuntoTransaccion adjuntoTransaccion;

                String filename;
                String pathFTP;

                for (AdjuntoPojo adjunto : data1.getAdjuntos()) {

                    filename = adjunto.getNombreArchivo();
                    pathFTP = iisEnv + data1.getIdUen().toString() + "/bdg/incrementos/" + respuesta.getValor() + '/';

                    adjuntoTransaccion = new AdjuntoTransaccion();
                    adjuntoTransaccion.setIdTransaccionGroup(respuesta.getValor().longValue());
                    adjuntoTransaccion.setNombreArchivo(filename);
                    adjuntoTransaccion.setMime(adjunto.getMime());
                    adjuntoTransaccion.setPeso(adjunto.getPeso());
                    adjuntoTransaccion.setRuta(pathFTP);
                    try {
                        adjuntoTransaccionRepository.save(adjuntoTransaccion);
                    } catch (Exception e) {
                        log.debug(e.getMessage());
                        respuesta.setMensaje("Error al guardar archivos adjuntos");
                    }

                    InputStream fis = new ByteArrayInputStream(adjunto.getFile());
                    uploadFileRepository.upload(fis, pathFTP, filename, host, port, user, pass);
                }
            }

        }

        //respuesta.setMensaje("OK");
        return respuesta;
    }
}
