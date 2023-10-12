package com.metalsa.finanzas.service;

import com.metalsa.finanzas.model.ActualizarPojo;
import com.metalsa.finanzas.model.AdjuntoPojo;
import com.metalsa.finanzas.model.AdjuntoSolicitud;
import com.metalsa.finanzas.model.CategoriaPojo;
import com.metalsa.finanzas.model.RespuestaPojo;
import com.metalsa.finanzas.model.SeguimientoSolicitud;
import com.metalsa.finanzas.model.SolicitudIncremento;
import com.metalsa.finanzas.model.SolicitudPojo;
import com.metalsa.finanzas.repository.AdjuntoSolicitudRepository;
import com.metalsa.finanzas.repository.NotificacionFinanzasRepository;
import com.metalsa.finanzas.repository.SeguimientoSolicitudRepository;
import com.metalsa.finanzas.repository.SolicitudIncrementoRepository;
import com.metalsa.finanzas.repository.UploadFileRepository;
import com.metalsa.utils.Constants;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author JL
 */
@RestController
@RequestMapping(Constants.URI_API_FINAZAS)
@Log4j
public class SolicitudIncrementoService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private SolicitudIncrementoRepository solicitudIncrementoRepository;

    @Autowired
    private AdjuntoSolicitudRepository adjuntoSolicitudRepository;

    @Autowired
    private UploadFileRepository uploadFileRepository;

    @Autowired
    private SeguimientoSolicitudRepository seguimientoSolicitudRepository;

    @Autowired
    private NotificacionFinanzasRepository notificacionFinanzasRepository;

    @Qualifier("application")
    @Autowired
    private MessageSource messages;

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

    @RequestMapping(value = "/solicitudes/{user}/{idioma}", method = RequestMethod.GET)
    public Iterable<SolicitudIncremento> getSolicitudes(@PathVariable(value = "user") String idUsuario,
            @PathVariable(value = "idioma") String idioma) {

        idioma = Constants.getIdioma(idioma);

        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("idiomaUsuarioFilter");
        filter.setParameter("idIdioma", idioma);

        Filter filter2 = session.enableFilter("estatusPartidaFilter");
        filter2.setParameter("idEstatus", 19);//POR APROBAR

        return solicitudIncrementoRepository.getSolicitudes(idioma, idUsuario);
    }

    @RequestMapping(value = "/solicitudes/finanzas/{user}/{idioma}", method = RequestMethod.GET)
    public Iterable<SolicitudIncremento> getSolicitudesFinanzas(@PathVariable(value = "user") String idUsuario,
            @PathVariable(value = "idioma") String idioma) {

        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("idiomaUsuarioFilter");
        filter.setParameter("idIdioma", idioma);
        Filter filter2 = session.enableFilter("estatusPartidaFilter");
        filter2.setParameter("idEstatus", 50);//POR APROBAR
        return solicitudIncrementoRepository.getSolicitudesFinanzas(idioma, idUsuario);

    }

    @RequestMapping(value = "/solicitudes", params = {"idUsuario"}, method = RequestMethod.GET)
    public @ResponseBody
    Iterable<SeguimientoSolicitud> getSolicitudesUsuario(@RequestParam("idUsuario") String idUsuario) {
        return seguimientoSolicitudRepository.getSolicitudesUsuario(idUsuario);
    }

    @RequestMapping(value = "/reporte", params = {"idUsuario"}, method = RequestMethod.GET)
    public @ResponseBody
    Iterable<SeguimientoSolicitud> getReporteUsuario(@RequestParam("idUsuario") String idUsuario,
            @RequestParam("fechaInicial") String fechaInicial,
            @RequestParam("fechaFin") String fechaFin) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaI = null;
        Date fechaO = null;

        if (null != fechaInicial && !"null".equals(fechaInicial)) {
            try {
                fechaI = dateFormat.parse(fechaInicial);
            } catch (ParseException ex) {
                log.debug(ex.getMessage());
            }
        }

        if (null != fechaFin && !"null".equals(fechaFin)) {
            try {
                fechaO = dateFormat.parse(fechaFin);
            } catch (ParseException ex) {
                log.debug(ex.getMessage());
            }
        }
        return seguimientoSolicitudRepository.getReporteUsuario(idUsuario, fechaI, fechaO);
    }

    @RequestMapping(value = "/download/reporte", params = {"idUsuario"}, method = RequestMethod.GET)
    public ModelAndView downloadReporteUsuario(@RequestParam("idUsuario") String idUsuario) {
        Map<String, Object> model = new HashMap<>();
        model.put("messages", messages);
        model.put("records", seguimientoSolicitudRepository.getReporteUsuario(idUsuario, null, null));
        return new ModelAndView("excelView", "solicitudIncremento", model);
    }

    @RequestMapping(value = "/solicitudes/historico", params = {"idUsuario", "idioma"}, method = RequestMethod.GET)
    public @ResponseBody
    Iterable<SolicitudIncremento> getHistoricoByUser(@RequestParam("idUsuario") String idUsuario,
            @RequestParam("idioma") String idioma) {
        idioma = Constants.getIdioma(idioma);

        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("idiomaUsuarioFilter");
        filter.setParameter("idIdioma", idioma);
        return solicitudIncrementoRepository.getHistoricoByUser(idioma, idUsuario);
    }

    @RequestMapping(value = "/solicitudes/totalporaprobar/cc", params = {"idUsuario"}, method = RequestMethod.GET)
    public @ResponseBody
    Integer getSolicitudesPendientesCC(@RequestParam("idUsuario") String idUsuario) {
        return solicitudIncrementoRepository.getCountAprobacionCC(idUsuario);
    }

    @RequestMapping(value = "/solicitudes/totalporaprobar/finanzas", params = {"idUsuario"}, method = RequestMethod.GET)
    public @ResponseBody
    Integer getSolicitudesPendientesFinanzas(@RequestParam("idUsuario") String idUsuario) {
        return solicitudIncrementoRepository.getCountAprobacionFinanzas(idUsuario);
    }

    @RequestMapping(value = "/solicitudes/agregar", method = RequestMethod.POST)
    public RespuestaPojo guardarSolicitud(@RequestBody SolicitudPojo data,
            @RequestParam("lang") String idioma) {

        log.debug(" .......................... razon: " + data.getRazon());
        log.debug(" .......................... categoria destino: " + data.getCategorias().get(0).getIdCategoriaDestino());
        log.debug(" .......................... fechaNecesidad: " + data.getFechaNecesidad());

        StringBuffer categorias = new StringBuffer();
        for (CategoriaPojo cat : data.getCategorias()) {
            categorias.append(cat.getIdCategoriaDestino()).append('|').append(cat.getIdCategoriaOrigen()).append('|')
                    .append(cat.getMonto()).append('|').append(cat.getMoneda()).append(',');
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = (Date) format.parse(data.getFechaNecesidad());
            log.debug(" .......................... fechaNecesidad: " + date);
        } catch (ParseException ex) {
            log.debug(ex.getMessage());
        }

        RespuestaPojo resp = solicitudIncrementoRepository.crearSolicitud(data.getIdUen(), data.getIdCcDestino(), data.getIdCcOrigen(),
                data.getPeriodoDestino(), data.getPeriodoOrigen(), date, data.getRazon(),
                data.getUsuarioCreacion(), categorias.toString(), data.getTipo());

        log.debug("respuesta----------: " + resp.getMensaje() + ", valor:" + resp.getValor());
        if (resp.getValor() > 0) {
            AdjuntoSolicitud adjuntoSolicitud;

            String filename;
            String pathFTP;

            for (AdjuntoPojo adjunto : data.getAdjuntos()) {
                filename = adjunto.getNombreArchivo();
                pathFTP = iisEnv + data.getIdUen().toString() + "/bdg/solicitudes/" + resp.getValor() + '/';

                adjuntoSolicitud = new AdjuntoSolicitud();
                adjuntoSolicitud.setIdSolicitudPresupuesto(resp.getValor().longValue());
                adjuntoSolicitud.setNombreArchivo(filename);
                adjuntoSolicitud.setMime(adjunto.getMime());
                adjuntoSolicitud.setPeso(adjunto.getPeso());
                adjuntoSolicitud.setRuta(pathFTP);
                try {
                    adjuntoSolicitudRepository.save(adjuntoSolicitud);
                } catch (Exception e) {
                    log.debug(e.getMessage());
                    resp.setMensaje("Error al guardar archivos adjuntos");
                }

                InputStream fis = new ByteArrayInputStream(adjunto.getFile());
                uploadFileRepository.upload(fis, pathFTP, filename, host, port, user, pass);

            }

            try {
                String tipoCorreo;
                String etapa;
                String idiomaEbs = Constants.getIdioma(idioma);

                ////////////////////////////////////////////////////////////////////////////////////////////////////////
                //author: juan.munoz
                //fecha: 10/Jun/2021
                //task redmine 63757
                //
                //Aqui buscamos los siguientes aprobadores, si el usuario creacion se encuentra entre los 
                //siguientes aprobadores, entonces procedemos a hacer la llamada del metodo de aprobacion
                //
                ////////////////////////////////////////////////////////////////////////////////////////////////////////
                String siguienteAprobador = solicitudIncrementoRepository.siguienteAprobador(resp.getValor(), data.getIdUen(), data.getIdCcOrigen(), data.getTipo());
                if(siguienteAprobador != null && siguienteAprobador.contains(data.getUsuarioCreacion())){
                    SolicitudIncremento solicitudIncremento = solicitudIncrementoRepository.findByIdSolicitudPresupuesto(Long.parseLong(resp.getValor().toString()));
                    log.debug("categorias: " + solicitudIncremento.getCategorias().size());
                    List<CategoriaPojo> listCategorias = solicitudIncremento.getCategorias().stream().map(cat ->{
                        CategoriaPojo p = new CategoriaPojo();
                        p.setId(cat.getId());
                        return p;
                    }).collect(Collectors.toList());
                    ActualizarPojo actualizarPojo = new ActualizarPojo();
                    actualizarPojo.setAccion("AUTO_APROBACION_CC");
                    actualizarPojo.setUsuario(data.getUsuarioCreacion());
                    actualizarPojo.setRazon("");
                    actualizarPojo.setCategorias(listCategorias);
                    this.actualizarSolicitud(actualizarPojo,idiomaEbs);
                }else{
                    log.debug("idioma: " + idioma);
                    if (data.getTipo().equals("INCREASE")) {
                        tipoCorreo = "APROB FINANZAS: CREADA_INCREMENTO";
                        etapa = "4";
                    } else {
                        tipoCorreo = "APROB FINANZAS: CREADA_TRANSFERENCIA";
                        etapa = "5";
                    }
                    log.debug("datos: " + resp.getValor().toString() + "-" + idiomaEbs + "-" + tipoCorreo + "-" + etapa);
                    RespuestaPojo envioresponse = notificacionFinanzasRepository.enviarNotificacion(resp.getValor().toString(), idiomaEbs, tipoCorreo, etapa);
                    log.debug(envioresponse.toString());
                }
                ////////////////////////////////////////////////////////////////////////////////////////////////////////
                //FIN siguientes aprobadores
                ////////////////////////////////////////////////////////////////////////////////////////////////////////
            } catch (Exception e) {
                log.error("error al enviar notificacion de creacion de solicitud a presupuesto " + e.getMessage(), e);
            }

        }

        return resp;
    }

    @RequestMapping(value = "/solicitudes/actualizar", method = RequestMethod.POST)
    public RespuestaPojo actualizarSolicitud(@RequestBody ActualizarPojo data,
            @RequestParam("lang") String idioma) {

        String tipoCorreo = null;
        String etapa = null;
        log.debug("categorias1........." + data.getCategorias());
        StringBuilder categorias = new StringBuilder();
        for (CategoriaPojo cat : data.getCategorias()) {
            categorias.append(cat.getId().toString()).append(',');
        }
        log.debug("categorias.........." + categorias.toString());
        RespuestaPojo resp = solicitudIncrementoRepository.actualizarSolicitud(categorias.toString(),
                data.getUsuario(), data.getRazon(), data.getAccion());

        if (null != data.getAccion()) {
            switch (data.getAccion()) {
                case "APROBACION_CC":
                    tipoCorreo = "APROB FINANZAS: APROBADAS";
                    etapa = "1";
                    break;
                case "APROBACION_FINANZAS":
                    tipoCorreo = "APROB FINANZAS: APROBADAS";
                    etapa = "2";
                    break;
                case "RECHAZO":
                    tipoCorreo = "APROB FINANZAS: RECHAZADAS";
                    etapa = "3";
                    break;
                case "AUTO_APROBACION_CC":
                    tipoCorreo = "APROB FINANZAS: APROBADAS";
                    etapa = "1";
                    break;
                default:
                    break;
            }
        }

        idioma = Constants.getIdioma(idioma);
//        log.debug("categorias:" + categorias.toString());
//        log.debug("idioma:" + idioma);
//        log.debug("tipoCorreo:" + tipoCorreo);
//        log.debug("etapa:" + etapa);
        if (null != etapa) {
            RespuestaPojo response = notificacionFinanzasRepository.enviarNotificacion(categorias.toString(), idioma, tipoCorreo, etapa);
            log.debug(response.getValor() + " - " + response.getClass());
        }

        //regresar las solicitudes con errores
        if (null != resp.getMensaje()) {

            String[] resultado = resp.getMensaje().split("\\|");
            StringBuilder sfondo = new StringBuilder();
            StringBuilder error = new StringBuilder();

            List<String> sinFondo = new ArrayList();
            List<String> errorDesconocido = new ArrayList();
            for (String resultado2 : resultado) {
                if (resultado2.startsWith("100,")) {
                    sinFondo.add(resultado2.substring(resultado2.indexOf(",")));
                } else {
                    errorDesconocido.add(resultado2.substring(resultado2.indexOf(",")));
                }
            }
            for (String sf : sinFondo) {
                sfondo.append(sf);
            }
            sfondo.append(",");

            for (String ed : errorDesconocido) {
                error.append(ed);
            }
            error.append(",");

            resp.setMensaje("{\"sin_fondo\":\"" + sfondo.toString() + "\",\"error\":\"" + error.toString() + "\"}");
        }

        return resp;

    }

    @RequestMapping(value = "/solicitudes/marcar_en_visto", method = RequestMethod.POST)
    public RespuestaPojo setSolicitudEnVisto(@RequestParam("id") Long id) {
        log.debug("record categoria id........." + id);
        RespuestaPojo resp = solicitudIncrementoRepository.setSolicitudEnVisto(id);
        return resp;

    }

    @RequestMapping(value = "/solicitudes/esPreaprobador", params = {"idUsuario"}, method = RequestMethod.GET)
    public @ResponseBody
    Integer isPreAprobador(@RequestParam("idUsuario") String idUsuario) {
        return solicitudIncrementoRepository.getEsPreaprobador(idUsuario);
    }
    
    @RequestMapping(value = "/solicitudes/cancelar_solicitud", method = RequestMethod.POST)
    public RespuestaPojo cancelarSolicitud(@RequestParam("id") Long id, @RequestParam("usuario") String usuario) {
        log.debug("record categoria id........." + id);
        RespuestaPojo resp = solicitudIncrementoRepository.cancelarSolicitud(id, usuario);
        return resp;
    }

}
