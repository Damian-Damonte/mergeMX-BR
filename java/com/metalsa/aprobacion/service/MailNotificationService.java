package com.metalsa.aprobacion.service;

import com.metalsa.aprobacion.model.*;
import com.metalsa.aprobacion.repository.CentroCostoRepository;
import com.metalsa.aprobacion.repository.MembresiaCentroCostoRepository;
import com.metalsa.aprobacion.repository.PlantillaCorreoRepository;
import com.metalsa.aprobacion.repository.ProyectoRepository;
import com.metalsa.aprobacion.repository.ReqPorAprobarRepository;
import com.metalsa.aprobacion.repository.UsuarioRepository;
import com.metalsa.core.model.NvcTblEmailToSend;
import com.metalsa.core.utils.Utilerias;
import com.metalsa.correos.pojo.NotificacionRequest;
import com.metalsa.mail.client.MailClient;
import com.metalsa.generales.model.PerAllPeopleF;
import com.metalsa.perfil.pojo.RolRequest;
import com.metalsa.perfil.pojo.UenRequest;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.tools.generic.DateTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@Service
@PropertySource ("file:${user.dir}/conf_metalsa/spx_conf.properties")
@Log4j
public class MailNotificationService {

    private static final String RETURNED = "APROBACION: REGRESADAS";
    private static final String REJECTED = "APROBACION: RECHAZADAS";
    public static final String APPROVED = "APROBACION: APROBADAS";
    private static final String PRE_APPROVED = "APROBACION: PRE-APROBADAS";
    //<RELEASE ARG>
    private static final String FOR_APPROVED = "SPX:NOTIFICACION_POR_APROBAR";
    private static final String PREAPROBADOR_PROYECTO = "SPX:NOTIFICACION_PREAPROBADORES_PROYECTO";

    private enum diccionario {
        CENTRO_COSTO("Centro de costo"),
        PROYECTO("Proyecto");

        diccionario(String traduccion) {
            this.traduccion = traduccion;
        }
        private final String traduccion;

        public String getTraduccion() {
            return traduccion;
        }

    }
    //</RELEASE ARG>
    private final UsuarioRepository usuarios;

    private final PlantillaCorreoRepository plantillas;
	
    //<RELEASE ARG>
    @Autowired
    private ReqPorAprobarRepository requisicionRepository;

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private CentroCostoRepository ccRepository;

    @Autowired
    private MembresiaCentroCostoRepository membresiaCentroCostoRepository;
    //</RELEASE ARG>
    @Getter
    @Setter
    @Value("${mc.send}")
    private String service;

    @Getter
    @Setter
    @Value("${spx.support}")
    private String emailSupport;

    @Getter
    @Setter
    @Value("${spx.supportcc}")
    private String emailSupportCc;

    @Autowired
    public MailNotificationService(UsuarioRepository usuarios, PlantillaCorreoRepository plantillas) {
        this.usuarios = usuarios;
        this.plantillas = plantillas;
    }

    public boolean sendSpxMail(NvcTblEmailToSend email) {
        if (email != null) {
            String[] to = email.getReceipt() != null ? email.getReceipt().split(",") : new String[]{""};;
            String[] cc = email.getReceiptsCc() != null ? email.getReceiptsCc().split(",") : new String[]{""};
            MailData notificacion = MailData.builder()
                    .receipts(asList(to))
                    .cc(asList(cc))
                    .subject(email.getSubject())
                    .body(email.getEmailBody())
                    .build();
            return this.sendMail(notificacion);
        }
        return false;
    }

    public boolean sendTestMail(NotificacionRequest req) {
        try {
            Usuario user = usuarios.findOne(req.getIdUsuario());
            PlantillaCorreo template = req.getCorreo();
            if (template != null) {
                String subject = "[TEST] ".concat(template.getAsunto());
                String body = template.getCuerpo();
                String rcp = user.getEmail();
                MailData notificacion = MailData.builder()
                        .receipts(asList(rcp))
                        .cc(asList("miguel.rodriguez.guillen@metalsa.com"))
                        .subject(subject)
                        .body(body)
                        .build();
                return this.sendMail(notificacion);
            }
        } catch (Exception e) {
            log.debug("error al enviar la prueba de correo " + e);
            return false;
        }
        return false;
    }

    //<RELEASE ARG>
    public boolean sendComentariosUsuario(String idUsuario, String comentario) {
        Usuario user = usuarios.findOne(idUsuario);
        Map<String, PlantillaCorreo> templates = plantillas.getByDescripcion("ENVIO CORREO COMENTARIOS").stream()
                .collect(Collectors.toMap(PlantillaCorreo::getIdioma, Function.identity(), (o, n) -> o));
        PlantillaCorreo template = templates.get(user.getIdioma());
        if (template != null) {
            String subject = template.getAsunto();
            String body = template.getCuerpo();
            MailData notificacion = MailData.builder()
                    .receipts(asList(emailSupport))
                    .cc(asList(emailSupportCc == null ? "" : emailSupportCc))
                    .subject(subject.replace("[id_usuario]", idUsuario))
                    .body(body.replace("[nombre_usuario]", user.getNombreUsuario()).replace("[comentario]", comentario))
                    .build();
            return this.sendMail(notificacion);
        }
        return false;
    }
	//</RELEASE ARG>

    public boolean sendRolRequest(String idUsuario, PerAllPeopleF empInfo, String uensUsuario, List<RolRequest> solicitudes) {
        Usuario user = usuarios.findOne(idUsuario);
        Map<String, PlantillaCorreo> templates = plantillas.getByDescripcion("SOLICITUD_ROL").stream()
                .collect(Collectors.toMap(PlantillaCorreo::getIdioma, Function.identity(), (o, n) -> o));
        PlantillaCorreo template = templates.get(user.getIdioma());
        if (template != null) {
            String subject = template.getAsunto();
            String body = template.getCuerpo()
                    .replace("[nombre_usuario]", user.getNombreUsuario())
                    .replace("[id_usuario]", idUsuario)
                    .replace("[email]", empInfo.getEmail() != null ? empInfo.getEmail() : "N/D")
                    .replace("[extension]", empInfo.getExtension() != null ? empInfo.getExtension() : "N/D")
                    .replace("[puesto]", empInfo.getPuesto() != null ? empInfo.getPuesto() : "N/D")
                    .replace("[uens_usuario]", uensUsuario != null ? uensUsuario : "N/D")
                    .replace("[localizacion]", empInfo.getLocalizacion() != null ? empInfo.getLocalizacion() : "N/D")
                    .replace("[body_roles]", preparaBodyRoles(user, solicitudes));
            //PERFIL Modificar cuando se pase a produccion
            //.receipts(asList("service-desk@metalsa.com"))
            /*MRG ESTE BLOQUE ES PARA LAS PRUEBAS DE CLAUDIA J*/
            String rcp;
            if (idUsuario.equalsIgnoreCase("OLIP")) {
                rcp = "osccompras@metalsa.com";
            } else {
                rcp = "service-desk@metalsa.com";
            }
            /*MRG ESTE BLOQUE ES PARA LAS PRUEBAS DE CLAUDIA J*/
            MailData notificacion = MailData.builder()
                    .receipts(asList(rcp))
//                    .cc(asList("claudia.jimenez@metalsa.com", "eric.herrera@metalsa.com"))
                    .cc(asList("osccompras@metalsa.com"))
                    .subject(subject)
                    .body(body)
                    .build();
            return this.sendMail(notificacion);
        }
        return false;
    }
    
    public boolean sendUenRequest(String idUsuario, PerAllPeopleF empInfo, String uensUsuario, List<UenRequest> solicitudes) {
        Usuario user = usuarios.findOne(idUsuario);
        Map<String, PlantillaCorreo> templates = plantillas.getByDescripcion("SOLICITUD_UEN").stream()
                .collect(Collectors.toMap(PlantillaCorreo::getIdioma, Function.identity(), (o, n) -> o));
        PlantillaCorreo template = templates.get(user.getIdioma());
        if (template != null) {
            String subject = template.getAsunto();
            String body = template.getCuerpo()
                    .replace("[nombre_usuario]", user.getNombreUsuario())
                    .replace("[id_usuario]", idUsuario)
                    .replace("[email]", empInfo.getEmail() != null ? empInfo.getEmail() : "N/D")
                    .replace("[extension]", empInfo.getExtension() != null ? empInfo.getExtension() : "N/D")
                    .replace("[puesto]", empInfo.getPuesto() != null ? empInfo.getPuesto() : "N/D")
                    .replace("[uens_usuario]", uensUsuario != null ? uensUsuario : "N/D")
                    .replace("[localizacion]", empInfo.getLocalizacion() != null ? empInfo.getLocalizacion() : "N/D")
                    .replace("[body_uens]", preparaBodyUens(user, solicitudes));
            //PERFIL Modificar cuando se pase a produccion
            //.receipts(asList("service-desk@metalsa.com"))
            /*MRG ESTE BLOQUE ES PARA LAS PRUEBAS DE CLAUDIA J*/
            String rcp;
            if (idUsuario.equalsIgnoreCase("OLIP")) {
                rcp = "osccompras@metalsa.com";
            } else {
                rcp = "service-desk@metalsa.com";
            }
            /*MRG ESTE BLOQUE ES PARA LAS PRUEBAS DE CLAUDIA J*/
            MailData notificacion = MailData.builder()
                    .receipts(asList(rcp))
                    .cc(asList("osccompras@metalsa.com"))
//                    .cc(asList("claudia.jimenez@metalsa.com", "eric.herrera@metalsa.com"))
                    .subject(subject)
                    .body(body)
                    .build();
            return this.sendMail(notificacion);
        }
        return false;
    }

    private String preparaBodyRoles(Usuario user, List<RolRequest> solicitudes) {
        Map<String, PlantillaCorreo> templates = plantillas.getByDescripcion("SOLICITUD_ROL_BODY").stream()
                .collect(Collectors.toMap(PlantillaCorreo::getIdioma, Function.identity(), (o, n) -> o));
        PlantillaCorreo template = templates.get(user.getIdioma());
        StringBuilder bodyRoles = new StringBuilder();
        if (template != null) {
            String body = template.getCuerpo();
            solicitudes.forEach((solicitud) -> {
                bodyRoles.append(body.replace("[nombre_rol]", solicitud.getRolSolicitado().get(0).getNombre()).replace("[opciones_menu] ", "menus").replace("[motivo]", solicitud.getRazon()));
            });
        }
        return bodyRoles.toString();
    }
    
    private String preparaBodyUens(Usuario user, List<UenRequest> solicitudes) {
        Map<String, PlantillaCorreo> templates = plantillas.getByDescripcion("SOLICITUD_UEN_BODY").stream()
                .collect(Collectors.toMap(PlantillaCorreo::getIdioma, Function.identity(), (o, n) -> o));
        PlantillaCorreo template = templates.get(user.getIdioma());
        StringBuilder bodyUens = new StringBuilder();
        if (template != null) {
            String body = template.getCuerpo();
            solicitudes.forEach((solicitud) -> {
                bodyUens.append(body.replace("[nombre_uen]", solicitud.getUenSolicitada().get(0).getNombre()).replace("[motivo]", solicitud.getRazon()));
            });
        }
        return bodyUens.toString();
    }

    public boolean notificarRegresadas(RegresoRequisicionVO regreso) {
        return sendNotificarionRegresadasAndRechazadas(regreso, RETURNED);
    }

    public boolean notificarRechazadas(RegresoRequisicionVO regreso) {
        return sendNotificarionRegresadasAndRechazadas(regreso, REJECTED);
    }
	
	//<RELEASE ARG>
    public boolean notificarPorAprobar(List<RequisicionVO> requisiciones) {
        return this.sendNotificacionPorAprobar(requisiciones);
    }

    public boolean notificarAprobadas(ApprovalRequest data) {
        return sendNotificacionAprobadas(data, APPROVED);
    }

    public boolean notificarPreaprobadoresProyecto(Map<String, List<Proyecto>> preaprobadores) {
        return this.sendNotificacionPreaprobadores(preaprobadores);
    }

    public boolean notificarPreAprobadas(ApprovalRequest data) {
        return sendNotificacionAprobadas(data, PRE_APPROVED);
    }
	//</RELEASE ARG>
	
    private boolean sendNotificarionRegresadasAndRechazadas(RegresoRequisicionVO regreso, String operacion) {
        List<MailData> notificaciones = new ArrayList<>();

        Map<String, PlantillaCorreo> templates = plantillas.getByDescripcion(operacion).stream()
                .collect(Collectors.toMap(PlantillaCorreo::getIdioma, Function.identity(), (o, n) -> o));

        Usuario aprobador = usuarios.findOne(regreso.getIdAprobador());

        Map<String, List<RequisicionVO>> requisidores = regreso.getRequisiciones().stream()
                .collect(Collectors.groupingBy(RequisicionVO::getIdRequisitor));

        for (Map.Entry<String, List<RequisicionVO>> e : requisidores.entrySet()) {
            Usuario usuario = usuarios.findOne(e.getKey());
            List<NotificacionRegresada> lineas = e.getValue().stream()
                    .flatMap(requi
                            -> requi.getLineas().stream()
                            .map(l -> new NotificacionRegresada(requi.getIdRequisicion(),
                            requi.getTipoRequisicion(), l.getIdPartida(), l.getDescripcionItem())))
                    .collect(Collectors.toList());
            VelocityContext context = new VelocityContext();
            context.put("aprobador", aprobador.getNombreUsuario());
            context.put("motivo", regreso.getRazonRegreso());
            context.put("regresadas", lineas);
            String message = getMessage(templates.get(usuario.getIdioma()), context);

            //<RELEASE ARG>
            notificaciones.add(MailData.builder()
                    .receipts(asList(usuario.getEmail()))
                    .cc(asList(""))
                    .subject(templates.get(usuario.getIdioma()).getAsunto())
                    .body(message)
                    .build());
            //</RELEASE ARG>
        }

        Map<String, List<NotificacionRegresada>> compradores = regreso.getRequisiciones().stream()
                .flatMap(requi
                        -> requi.getLineas().stream()
                        .filter(l -> StringUtils.isNotBlank(l.getIdComprador()))
                        .map(l -> new AbstractMap.SimpleEntry<>(l.getIdComprador(),
                        new NotificacionRegresada(requi.getIdRequisicion(),
                                requi.getTipoRequisicion(), l.getIdPartida(), l.getDescripcionItem()))))
                .collect(Collectors.groupingBy(e -> e.getKey(),
                        Collectors.mapping(o -> o.getValue(), Collectors.toList())));

        for (Map.Entry<String, List<NotificacionRegresada>> e : compradores.entrySet()) {
            Usuario usuario = usuarios.findOne(e.getKey());

            VelocityContext context = new VelocityContext();
            context.put("aprobador", aprobador.getNombreUsuario());
            context.put("motivo", regreso.getRazonRegreso());
            context.put("regresadas", e.getValue());
            String message = getMessage(templates.get(usuario.getIdioma()), context);

            //<RELEASE ARG>
            notificaciones.add(MailData.builder()
                    .receipts(asList(usuario.getEmail()))
                    .cc(asList(""))
                    .subject(templates.get(usuario.getIdioma()).getAsunto())
                    .body(message)
                    .build());
            //</RELEASE ARG>
        }

        return notificaciones.stream()
                .allMatch(this::sendMail);
    }

    private boolean sendNotificacionAprobadas(ApprovalRequest data, String operacion) {

        Map<String, PlantillaCorreo> templates = plantillas.getByDescripcion(operacion).stream()
                .collect(Collectors.toMap(PlantillaCorreo::getIdioma, Function.identity(), (o, n) -> o));

        List<MailData> notificaciones = new ArrayList<>();

        Usuario aprobador = usuarios.findOne(data.getAprobador());

        if (APPROVED.equals(operacion)) {

            Map<String, List<RequisicionVO>> requisidores = data.getRequisiciones().stream()
                    .filter(r -> !data.getAprobador().equalsIgnoreCase(r.getIdRequisitor()))
                    .collect(Collectors.groupingBy(RequisicionVO::getIdRequisitor));

            for (Map.Entry<String, List<RequisicionVO>> e : requisidores.entrySet()) {
                Usuario usuario = usuarios.findOne(e.getKey());
                List<NotificacionAprobada> lineas = e.getValue().stream()
                        .flatMap(requi
                                -> requi.getLineas().stream()
                                .map(l -> NotificacionAprobada.builder()
                                .idRequisicion(requi.getIdRequisicion())
                                .idLinea(l.getIdPartida())
                                .tipo(requi.getTipoRequisicion())
                                .uen(requi.getNombreUen())
                                .fecha(new Date())
                                .descripcion(l.getDescripcionItem())
                                .proximoAprobador(StringUtils.isNotBlank(l.getSiguienteAprobador())
                                        ? usuarios.findOne(l.getSiguienteAprobador()).getNombreUsuario()
                                        : null)
                                .proveedor(StringUtils.isBlank(l.getNombreProveedor()) ? "" : l.getNombreProveedor())
                                .build()))
                        .collect(Collectors.toList());

                VelocityContext context = new VelocityContext();
                context.put("date", new DateTool());
                context.put("aprobador", aprobador.getNombreUsuario());
                context.put("aprobadas", lineas);
                String message = getMessage(templates.get(usuario.getIdioma()), context);
                //<RELEASE ARG>
                notificaciones.add(MailData.builder()
                        .receipts(asList(usuario.getEmail()))
                        .cc(asList(""))
                        .subject(templates.get(usuario.getIdioma()).getAsunto())
                        .body(message)
                        .build());
                //</RELEASE ARG>
            }

            Map<String, List<NotificacionAprobada>> compradores = data.getRequisiciones().stream()
                    .flatMap(requi
                            -> requi.getLineas().stream()
                            .filter(l -> StringUtils.isNotBlank(l.getIdComprador()))
                            .map(l -> new AbstractMap.SimpleEntry<>(l.getIdComprador(),
                            NotificacionAprobada.builder()
                                    .idRequisicion(requi.getIdRequisicion())
                                    .idLinea(l.getIdPartida())
                                    .tipo(requi.getTipoRequisicion())
                                    .uen(requi.getNombreUen())
                                    .fecha(new Date())
                                    .descripcion(l.getDescripcionItem())
                                    .proximoAprobador(StringUtils.isNotBlank(l.getSiguienteAprobador())
                                            ? usuarios.findOne(l.getSiguienteAprobador()).getNombreUsuario()
                                            : null)
                                    .proveedor(StringUtils.isBlank(l.getNombreProveedor()) ? "" : l.getNombreProveedor())
                                    .build())))
                    .filter(e -> data.getAprobador().equalsIgnoreCase(e.getValue().getProximoAprobador()))
                    .collect(Collectors.groupingBy(e -> e.getKey(),
                            Collectors.mapping(o -> o.getValue(), Collectors.toList())));

            notificaciones.addAll(createApprovalNotification(templates, aprobador, compradores));

        } else {

            // para pre-aprobacion
            Map<String, List<NotificacionAprobada>> proximosAprobadores = data.getRequisiciones().stream()
                    .flatMap(requi
                            -> requi.getLineas().stream()
                            .filter(l -> StringUtils.isNotBlank(l.getSiguienteAprobador()))
                            .map(l -> new AbstractMap.SimpleEntry<>(l.getSiguienteAprobador(),
                            NotificacionAprobada.builder()
                                    .idRequisicion(requi.getIdRequisicion())
                                    .idLinea(l.getIdPartida())
                                    .tipo(requi.getTipoRequisicion())
                                    .uen(requi.getNombreUen())
                                    .fecha(new Date())
                                    .descripcion(l.getDescripcionItem())
                                    .proximoAprobador(StringUtils.isNotBlank(l.getSiguienteAprobador())
                                            ? usuarios.findOne(l.getSiguienteAprobador()).getNombreUsuario()
                                            : null)
                                    .proveedor(StringUtils.isBlank(l.getNombreProveedor()) ? "" : l.getNombreProveedor())
                                    .build())))
                    .filter(e -> data.getAprobador().equalsIgnoreCase(e.getValue().getProximoAprobador()))
                    .collect(Collectors.groupingBy(e -> e.getKey(),
                            Collectors.mapping(o -> o.getValue(), Collectors.toList())));
            notificaciones.addAll(createApprovalNotification(templates, aprobador, proximosAprobadores));

        }

        return notificaciones.stream()
                .allMatch(this::sendMail);
    }
	
	//<RELEASE ARG>
    private boolean sendNotificacionPorAprobar(List<RequisicionVO> requisiciones) {

        Map<String, PlantillaCorreo> templates = plantillas.getByDescripcion(FOR_APPROVED).stream()
                .collect(Collectors.toMap(PlantillaCorreo::getIdioma, Function.identity(), (o, n) -> o));

        if (templates.isEmpty() || requisiciones.isEmpty()) {
            log.debug("No se ha encontrado la plantilla para la notificacion");
            return false;
        }

        //obtener los datos de las requisiciones
        log.debug("- - - - - - - - - - - - - OBTENIENDO INFORMACION DE CORREOS - - - - - - - - - - - - - - - - - - - ");
        for (RequisicionVO requisicion : requisiciones) {

            ReqPorAprobar req = this.requisicionRepository.getRequisicion(requisicion.getIdRequisicion().intValue());
            req.setNombreUsuario(this.usuarios.findOne(req.getIdUsuario()).getNombreUsuario());
            List<Usuario> aprobadores;

            //MDA_REPORTES_FINANZAS
            if (req.getIdProyecto() == null) {
                CentroCosto cc = this.ccRepository.getByIdAndIdUenAndIdioma(req.getIdCC().longValue(),req.getIdUen(),"ESA");
                req.setCodigoCC(cc.getCodigo());
                req.setNombreCc(cc.getNombre());
                aprobadores = this.membresiaCentroCostoRepository.getAprobadoresPorRango(req.getIdUen().toString(), "ESA", cc.getCodigo(), req.getIdRequisicion().toString());
            } else {

                OaProyectos pro = this.proyectoRepository.findOne(req.getIdProyecto().longValue());
                req.setCodigoProyecto(pro.getCodProyecto());
                req.setProyecto(pro.getNombreProyecto());
                String aprobador = proyectoRepository.getAprobadorProyecto(req.getIdProyecto().longValue());
                aprobadores = asList(this.usuarios.findOne(aprobador));

            }

            if (aprobadores.isEmpty()) {
                log.debug("No se encontraron aprobadores para la requisicion-->");
                log.debug("id_Requisicion -> " + req.getIdRequisicion() + (req.getIdProyecto() == null ? " , id_cc->" + req.getIdCC() : " , id_proyecto->" + req.getIdProyecto()));
                continue;
            }

            log.debug("- - - - - - - - - - - - - CONSTRUYENDO CORREO  - - - - - - - - - - - - - - - - - - - ");
            Map<String, PlantillaCorreo> multiIdioma = new HashMap<>();
            for (Usuario aprobador : aprobadores) {

                if (false == templates.containsKey(aprobador.getIdioma())) {
                    log.debug("No existe una plantilla para el idioma->" + aprobador.getIdioma());
                    continue;
                }

                if (false == multiIdioma.containsKey(aprobador.getIdioma())) {
                    multiIdioma.put(aprobador.getIdioma(), this.getCorreoPorAprobar(templates.get(aprobador.getIdioma()), req));
                }

                synchronized (this) {

                    log.debug("- - - - ENVIANDO CORREO a - - - - - - -> " + aprobador.getNombreUsuario() + " , " + aprobador.getEmail() + "  , " + aprobador.getIdioma());
                    PlantillaCorreo correo = multiIdioma.get(aprobador.getIdioma());
                    MailClient.setMessage(correo.getAsunto(), "HTML", correo.getCuerpo(), new String[]{aprobador.getEmail()}, new String[]{}, service);
                    MailClient.exec();

                }
            }
        }

        return true;
    }

    private boolean sendNotificacionPreaprobadores(Map<String, List<Proyecto>> preaprobadores) {

        Map<String, PlantillaCorreo> templates = this.getPlantillasIdioma(PREAPROBADOR_PROYECTO);

        if (templates.isEmpty()) {
            log.debug("no existen una  plantilla para enviar la notificacion");
            return false;
        }

        List<MailData> notificaciones = new ArrayList<>();

        for (Map.Entry<String, List<Proyecto>> entry : preaprobadores.entrySet()) {

            VelocityContext context = new VelocityContext();
            context.put("proyectos", entry.getValue());
            Usuario usuario = this.usuarios.findOne(entry.getKey());

            PlantillaCorreo template = templates.get(usuario.getIdioma());

            if(template==null){
                log.debug("no existe plantilla para el idioma->"+usuario.getIdioma());
                continue;
            }
            
            notificaciones.add(MailData.builder()
                    .receipts(asList(usuario.getEmail()))
                    .cc(asList(""))
                    .subject(template.getAsunto())
                    .body(getMessage(template, context))
                    .build());

        }

        return notificaciones
                .stream()
                .allMatch(this::sendMail);
    }

    private List<MailData> createApprovalNotification(
            Map<String, PlantillaCorreo> templates,
            Usuario aprobador,
            Map<String, List<NotificacionAprobada>> data
    ) {

        List<MailData> notificaciones = new ArrayList<>();
        for (Map.Entry<String, List<NotificacionAprobada>> e : data.entrySet()) {
            Usuario usuario = usuarios.findOne(e.getKey());

            VelocityContext context = new VelocityContext();
            context.put("date", new DateTool());
            context.put("aprobador", aprobador.getNombreUsuario());
            context.put("aprobadas", e.getValue());
            String message = getMessage(templates.get(usuario.getIdioma()), context);

            notificaciones.add(MailData.builder()
                    .receipts(asList(usuario.getEmail()))
                    .cc(asList(""))
                    .subject(templates.get(usuario.getIdioma()).getAsunto())
                    .body(message)
                    .build());
        }

        return notificaciones;
    }
	
	//</RELEASE ARG>

    private String getMessage(PlantillaCorreo plantilla, VelocityContext contexto) {
        StringWriter stWriter = new StringWriter();
        Reader templateReader = new BufferedReader(
                new InputStreamReader(
                        new ByteArrayInputStream(plantilla.getCuerpo().getBytes(Charset.forName("UTF-8"))), Charset.forName("UTF-8")));
        Velocity.setProperty("runtime.references.strict", "true");
        Velocity.evaluate(contexto, stWriter, getClass().getSimpleName(), templateReader);

        return stWriter.toString();
    }

    public boolean sendMail(MailData data) {
        synchronized (this) {
            List<String> rcpt = data.getReceipts().stream()
                    .flatMap(r -> Stream.of(StringUtils.split(r, ",")))
                    .collect(Collectors.toList());

            List<String> cc = data.getCc().stream()
                    .flatMap(r -> Stream.of(StringUtils.split(r, ",")))
                    .collect(Collectors.toList());

            MailClient.setMessage(data.getSubject(), "HTML",
                    data.getBody(), rcpt.toArray(new String[]{}), cc.toArray(new String[]{}),
                    service);
            return MailClient.exec().equalsIgnoreCase("success");
        }
    }

    /**
     * Configuracion para enviar un mensaje
     */
    @Getter
    @Builder
    public static class MailData {

        private List<String> receipts;
        private List<String> cc;
        private String subject;
        private String body;
    }

    /**
     * mapeo de datos para plantilla de regresados
     */
    @Getter
    @AllArgsConstructor
    public static class NotificacionRegresada {

        private Long idRequisicion;
        private String tipo;
        private Long idLinea;
        private String descripcion;
    }

    /**
     * mapeo de datos para plantilla de aprobadas
     */
    @Getter
    @SuppressFBWarnings({"EI_EXPOSE_REP2"})
    public static class NotificacionAprobada {

        private String uen;
        private Long idRequisicion;
        private String tipo;
        private Long idLinea;
        private String descripcion;
        private String proveedor;
        private Date fecha;
        private String proximoAprobador;

        @Builder
        protected NotificacionAprobada(String uen, Long idRequisicion, String tipo, Long idLinea, String descripcion,
                String proveedor, Date fecha, String proximoAprobador) {
            this.uen = uen;
            this.idRequisicion = idRequisicion;
            this.tipo = tipo;
            this.idLinea = idLinea;
            this.descripcion = descripcion;
            this.proveedor = proveedor;
            if (fecha != null) {
                this.fecha = new Date(fecha.getTime());
            }
            this.proximoAprobador = proximoAprobador;
        }

        public Date getFecha() {
            return fecha == null ? null : (Date) fecha.clone();
        }

        public void setFecha(Date fecha) {
            if (fecha == null) {
                this.fecha = null;
            } else {
                this.fecha = (Date) fecha.clone();
            }
        }
    }

	//<RELEASE ARG>
    private PlantillaCorreo getCorreoPorAprobar(PlantillaCorreo input, ReqPorAprobar requisicion) {

        int inicio = input.getCuerpo().indexOf("[inicio_partida]");
        int fin = input.getCuerpo().indexOf("[fin_partida]");

        String oheader = input.getCuerpo().substring(0, inicio);
        String oline = input.getCuerpo().substring(inicio + 16, fin);
        String ofooter = input.getCuerpo().substring(fin + 13);

        PlantillaCorreo plantilla = new PlantillaCorreo();

        plantilla.setAsunto(input.getAsunto().replace("[requisicion]", requisicion.getIdRequisicion().toString()));

        List<ReqPorAprobar> lineas = this.requisicionRepository.getDetallePorAprobar(requisicion.getIdRequisicion());
        StringBuilder trs = new StringBuilder();
        Double total = 0.0;

        for (ReqPorAprobar detalle : lineas) {
//            log.debug(detalle.toString());

            String tr = oline;
            tr = tr.replace("[linea]", detalle.getIdPartida().toString())
                    .replace("[descripcion]", detalle.getDescripcionItem())
                    .replace("[fecha_necesidad]", Utilerias.GET_DATE_DD_MM_YYYY(detalle.getFechaRequerida()))
                    .replace("[udm]", detalle.getIdUnidadMedida())
                    .replace("[cantidad]", detalle.getCantidadRequerida().toString())
                    .replace("[precio]", detalle.getPrecio().toString())
                    .replace("[monto]", detalle.getMontoExtendido().toString());
            trs.append(tr);
            total = total + detalle.getMontoExtendido();
        }

        oheader = oheader.replace("[requisitor]", requisicion.getNombreUsuario())
                .replace("[enviada]", Utilerias.GET_SYSDATE(Utilerias.DATE_FORMAT.DD_MM_YYYY))
                .replace("[uen]", requisicion.getUen())
                .replace("[tipo_requisicion]", requisicion.getFuente())
                .replace("[requisicion]", requisicion.getIdRequisicion().toString());

        ofooter = ofooter.replace("[monto_total]", total.toString());

        plantilla.setAsunto(plantilla.getAsunto().replace("[requisicion]", requisicion.getIdRequisicion().toString()));
        plantilla.setCuerpo(oheader + trs.toString() + ofooter);

        if (requisicion.getIdProyecto() == null) {
            plantilla.setCuerpo(plantilla.getCuerpo().replaceAll(Pattern.quote("[cc_proyecto]"), diccionario.CENTRO_COSTO.getTraduccion()));
            plantilla.setCuerpo(plantilla.getCuerpo().replaceAll(Pattern.quote("[cc_proyecto_val]"), requisicion.getCodigoCC() + " - " + requisicion.getNombreCc()));
        } else {
            plantilla.setCuerpo(plantilla.getCuerpo().replaceAll(Pattern.quote("[cc_proyecto_val]"), requisicion.getCodigoProyecto() + " - " + requisicion.getProyecto()));
            plantilla.setCuerpo(plantilla.getCuerpo().replaceAll(Pattern.quote("[cc_proyecto]"), diccionario.PROYECTO.getTraduccion()));
        }

        return plantilla;
    }

    private Map<String, PlantillaCorreo> getPlantillasIdioma(String nombrecorreo) {
        return plantillas
                .getByDescripcion(nombrecorreo)
                .stream()
                .collect(
                        Collectors
                                .toMap(PlantillaCorreo::getIdioma, Function.identity(), (o, n) -> o)
                );
    }
	//</RELEASE ARG>
    
    public List<MailData> getMailApproval(ApprovalRequest data, String operacion) {
		//<T395493>
        Map<String, PlantillaCorreo> templates = plantillas.getByDescripcion(operacion).stream()
                .collect(Collectors.toMap(PlantillaCorreo::getIdioma, Function.identity(), (o, n) -> o));

        List<MailData> notificaciones = new ArrayList<>();

        Usuario aprobador = usuarios.findOne(data.getAprobador());

        if (APPROVED.equals(operacion)) {

            Map<String, List<RequisicionVO>> requisidores = data.getRequisiciones().stream()
                    .filter(r -> !data.getAprobador().equalsIgnoreCase(r.getIdRequisitor()))
                    .collect(Collectors.groupingBy(RequisicionVO::getIdRequisitor));

            for (Map.Entry<String, List<RequisicionVO>> e : requisidores.entrySet()) {
                Usuario usuario = usuarios.findOne(e.getKey());
                List<NotificacionAprobada> lineas = e.getValue().stream()
                        .flatMap(requi
                                -> requi.getLineas().stream()
                                .map(l -> NotificacionAprobada.builder()
                                .idRequisicion(requi.getIdRequisicion())
                                .idLinea(l.getIdPartida())
                                .tipo(requi.getTipoRequisicion())
                                .uen(requi.getNombreUen())
                                .fecha(new Date())
                                .descripcion(l.getDescripcionItem())
                                .proximoAprobador(StringUtils.isNotBlank(l.getSiguienteAprobador())
                                        ? usuarios.findOne(l.getSiguienteAprobador()).getNombreUsuario()
                                        : null)
                                .proveedor(StringUtils.isBlank(l.getNombreProveedor()) ? "" : l.getNombreProveedor())
                                .build()))
                        .collect(Collectors.toList());

                VelocityContext context = new VelocityContext();
                context.put("date", new DateTool());
                context.put("aprobador", aprobador.getNombreUsuario());
                context.put("aprobadas", lineas);
                String message = getMessage(templates.get(usuario.getIdioma()), context);
                //<RELEASE ARG>
                notificaciones.add(MailData.builder()
                        .receipts(asList(usuario.getEmail()))
                        .cc(asList(""))
                        .subject(templates.get(usuario.getIdioma()).getAsunto())
                        .body(message)
                        .build());
                //</RELEASE ARG>
            }
        }
        return notificaciones;
	//</T395493>
    }
}
