package com.metalsa.aprobacion.service;

import com.metalsa.aprobacion.model.*;
import com.metalsa.aprobacion.repository.AdjuntosRepository;
import com.metalsa.aprobacion.repository.DetalleRequisicionRepository;
import com.metalsa.aprobacion.repository.MensajeRequisicionRepository;
import com.metalsa.aprobacion.repository.RequisicionRepository;
import com.metalsa.error.NotFoundException;
import com.metalsa.utils.Constants;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ruben.bresler on 07/07/2017.
 */
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(Constants.URI_API_REQUISIONES)
@Log4j
public class RequisicionController {

    @Qualifier("application")
    @Autowired
    private MessageSource messages;

    @Autowired
    private RequisicionRepository requisiciones;

    @Autowired
    private DetalleRequisicionRepository details;

    @Autowired
    private AdjuntosRepository archivos;

    @Autowired
    private MensajeRequisicionRepository mensajes;

    @Autowired
    private Environment env;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Page<Requisicion> getRequisiciones(@RequestParam(value = "user", required = false) String usuario,
                                              @PageableDefault Pageable page) {
        Page<Requisicion> list = new PageImpl<Requisicion>(new ArrayList<>(), page, 0);

        if (StringUtils.isNotBlank(usuario))
            list = requisiciones.findAllByUsuario(usuario, page);

        return list;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Requisicion getRequisicion(@PathVariable Long id) {
        return requisiciones.getByIdRequisicion(id)
                .orElseThrow(() -> new NotFoundException(
                        messages.getMessage("req.not-found", new Object[]{id}, LocaleContextHolder.getLocale())));
    }

    @RequestMapping(value = "/{id}/details", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<DetalleRequisicion> getDetails(@PathVariable Long id) {
        return details.getAllByRequisicionOrderByLineaAsc(id);
    }

    @RequestMapping(value = "/{id}/details/{line}", method = RequestMethod.GET)
    @ResponseBody
    public DetalleRequisicion getDetails(@PathVariable Long id, @PathVariable Long line) {
        return details.getByRequisicionAndLinea(id, line)
                .orElseThrow(() -> new NotFoundException(
                        messages.getMessage("detail.not-found", new Object[]{id, line}, LocaleContextHolder
                                .getLocale())
                ));
    }

    @RequestMapping(value = "/{id}/countfiles", method = RequestMethod.GET)
    @ResponseBody
    public Long getTotalAdjPorRequisicion(@PathVariable("id") Long idRequisicion) {
        return this.archivos.countByRequisicion(idRequisicion);
    }
    
    @RequestMapping(value = "/{id}/files", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<AdjuntoAprobacion> getAdjuntosPorRequisicion(@PathVariable("id") Long id) {
        long cont=0;
        List<AdjuntoAprobacion> list = archivos.getAllByRequisicionOrderByLineaAscQuery(id);
        final String iisEnv = env.getProperty("iis.env").concat("/").replaceAll("//", "/");
        if (StringUtils.isNotBlank(iisEnv)) {
            for(AdjuntoAprobacion f :list){
            f.setIisUbicacion(iisEnv.concat(f.getUbicacion()));
            f.setAdjunto(cont++);
            }
        }
        return list;
    }

    @RequestMapping(value = "/{id}/messages", method = RequestMethod.GET)
    @ResponseBody
    public ConversacionRequisicion getConversacionRequi(@PathVariable("id") Long id,
                                                        @RequestParam(name = "sender", required = false) Long sender,
                                                        @RequestParam(name = "receipt", required = false) Long receipt) {
        List<MensajeRequisicion> list = mensajes.mensajesPorRequisicion(id);
        if (sender != null && receipt != null) {
            list = list.stream()
                    .filter(m -> sender.equals(m.getRolRemitente()) && receipt.equals(m.getRolDestinatario()))
                    .collect(Collectors.toList());
        }
        return new ConversacionRequisicion(id, list);
    }

    @Autowired
    private ReportService reports;

    @GetMapping("/{id}/messages/pdf")
    public void getPdfConversacion(@PathVariable("id") Long id, HttpServletResponse response) {
        ConversacionRequisicion chat = getConversacionRequi(id, null, null);

        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        try {
            reports.printMensajesRequisicion(chat, response.getOutputStream());
            response.flushBuffer();
            java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MMM/yyyy HH:mm"));

        } catch (IOException ex) {
            log.error("Error writing pdf file to output stream.", ex);
            throw new RuntimeException("IOError writing file to output stream");
        }
    }
}
