package com.metalsa.admin.controller;

import com.metalsa.admin.model.NoticiaRequest;
import com.metalsa.admin.model.NoticiaResponse;
import com.metalsa.admin.pojo.FiltrosNoticias;
import com.metalsa.admin.repository.NoticiaHomeRepository;
import com.metalsa.home.model.NoticiaHome;
import com.metalsa.admin.repository.NoticiasService;
import com.metalsa.core.model.Rol;
import com.metalsa.perfil.pojo.Uen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j;
import com.metalsa.utils.Constants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author miguel.rdz
 *
 */
@RestController
@Api(value = "Noticias", tags = {"Noticias Service API"})
@RequestMapping(Constants.URI_API_NOTICIAS)
@Log4j
public class NoticiasController {

    @Getter
    @Setter
    @Value("${iis.root}")
    private String iisRoot;

    @Getter
    @Setter
    @Value("${iis.env}")
    private String env;

    @Autowired
    private NoticiasService noticiasService;

    @Autowired
    private NoticiaHomeRepository noticiaHomeRepository;

    @GetMapping("/getActuales")
    public Iterable<NoticiaHome> getActuales() {
        Iterable<NoticiaHome> noticias = noticiasService.getActuales();
        noticias.forEach(n -> {
            n.setUrlMedia(iisRoot.concat(env).concat(n.getRuta()));
            n.setNombreFtp(n.getRuta().substring(n.getRuta().lastIndexOf('/') + 1));
            n.setFtpPath(env.concat(n.getRuta().replace(n.getNombreFtp(), "")));
            n.setUenList(noticiasService.getUensById(n.getUens()));
            n.setRolList(noticiasService.getRolesById(n.getRoles()));
        });
        return noticias;
    }

    @GetMapping("/getHistorico")
    Page<NoticiaHome> getHistorico(@PageableDefault Pageable page) {
        Page<NoticiaHome> noticias = noticiaHomeRepository.getHistorico(page);
        noticias.forEach(n -> {
            n.setUrlMedia(iisRoot.concat(env).concat(n.getRuta()));
            n.setNombreFtp(n.getRuta().substring(n.getRuta().lastIndexOf('/') + 1));
            n.setFtpPath(env.concat(n.getRuta().replace(n.getNombreFtp(), "")));
            n.setUenList(noticiasService.getUensById(n.getUens()));
            n.setRolList(noticiasService.getRolesById(n.getRoles()));
        });
        return noticias;
    }

    @RequestMapping(value = "/getFiltrosHistorico/", method = RequestMethod.GET)
    @ResponseBody
    public FiltrosNoticias getFiltrosHistorico() {
        FiltrosNoticias filtros = noticiasService.getFiltrosHist();
        return filtros;
    }

    @RequestMapping(value = "getHistoricoFiltros", method = RequestMethod.POST)
    @ResponseBody
    public Page<NoticiaHome> getHistoricoFiltros(@PageableDefault Pageable page, @RequestBody FiltrosNoticias req) {
        Page<NoticiaHome> noticias = null;
        try {
            if (req != null) {
                noticias = noticiaHomeRepository.getHistorico(page);
            } else {
                noticias = noticiaHomeRepository.getHistorico(page);
            }
            if (noticias.getSize() > 0) {
                noticias.forEach(n -> {
                    n.setUrlMedia(iisRoot.concat(env).concat(n.getRuta()));
                    n.setNombreFtp(n.getRuta().substring(n.getRuta().lastIndexOf('/') + 1));
                    n.setFtpPath(env.concat(n.getRuta().replace(n.getNombreFtp(), "")));
                    n.setUenList(noticiasService.getUensById(n.getUens()));
                    n.setRolList(noticiasService.getRolesById(n.getRoles()));
                });
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return noticias;
    }

    @GetMapping("/getUens")
    public Iterable<Uen> getUens() {
        return noticiasService.getUens();
    }

    @RequestMapping(value = "updateUens", method = RequestMethod.POST)
    @ResponseBody
    public NoticiaResponse updateUens(@RequestBody NoticiaRequest req) {
        NoticiaResponse res = new NoticiaResponse();
        try {
            if (req != null) {
                if (noticiasService.updateUens(req)) {
                    res.setOutMessage("OK");
                } else {
                    res.setOutMessage("ERROR");
                }
                res.setNoticias(getActuales());
            }
        } catch (Exception e) {
            log.error("Error en updateUens:" + e.getMessage());
            res.setOutMessage(e.getMessage());
        }
        return res;
    }

    @GetMapping("/getRoles")
    public Iterable<Rol> getRoles() {
        return noticiasService.getRoles();
    }

    @RequestMapping(value = "updateRoles", method = RequestMethod.POST)
    @ResponseBody
    public NoticiaResponse updateRoles(@RequestBody NoticiaRequest req) {
        NoticiaResponse res = new NoticiaResponse();
        try {
            if (req != null) {
                if (noticiasService.updateRoles(req)) {
                    res.setOutMessage("OK");
                } else {
                    res.setOutMessage("ERROR");
                }
                res.setNoticias(getActuales());
            }
        } catch (Exception e) {
            log.error("Error en updateRoles:" + e.getMessage());
            res.setOutMessage(e.getMessage());
        }
        return res;
    }

    @RequestMapping(value = "order", method = RequestMethod.POST)
    @ResponseBody
    public NoticiaResponse orderNoticia(@RequestBody NoticiaRequest req) {
        NoticiaResponse res = new NoticiaResponse();
        try {
            if (req != null) {
                if (noticiasService.orderNoticia(req)) {
                    res.setOutMessage("OK");
                } else {
                    res.setOutMessage("ERROR");
                }
                res.setNoticias(getActuales());
            }
        } catch (Exception e) {
            log.error("Error en orderNoticia:" + e.getMessage());
            res.setOutMessage(e.getMessage());
        }
        return res;
    }

    @RequestMapping(value = "insert", method = RequestMethod.POST)
    @ResponseBody
    public NoticiaResponse insertNoticia(@RequestBody NoticiaRequest req) {
        NoticiaResponse res = new NoticiaResponse();
        try {
            if (req != null) {
                res = noticiasService.insertNoticia(req);
            }
        } catch (Exception e) {
            log.error("Error en insertNoticia:" + e.getMessage());
            res.setOutMessage(e.getMessage());
        }
        return res;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public NoticiaResponse updateNoticia(@RequestBody NoticiaRequest req) {
        NoticiaResponse res = new NoticiaResponse();
        try {
            if (req != null) {
                res = noticiasService.updateNoticia(req);
            }
        } catch (Exception e) {
            log.error("Error en updateNoticia:" + e.getMessage());
            res.setOutMessage(e.getMessage());
        }
        return res;
    }

    @GetMapping("/delete/{idUsuario}/{idNoticia}")
    public NoticiaResponse deleteNoticia(@PathVariable("idUsuario") String idUsuario, @PathVariable("idNoticia") Integer idNoticia) {
        NoticiaResponse response = new NoticiaResponse();
        try {
            if (noticiasService.deleteNoticia(idUsuario, idNoticia)) {
                response.setOutMessage("OK");
                response.setNoticias(this.getActuales());
            } else {
                response.setOutMessage("ERROR");
            }
        } catch (Exception e) {
            log.error("Error al deleteNoticia:" + e.getMessage());
        }
        return response;
    }

    @GetMapping("/pause/{idNoticia}")
    public NoticiaResponse pauseNoticia(@PathVariable("idNoticia") Integer idNoticia, @RequestParam("status") Integer status) {
        NoticiaResponse response = new NoticiaResponse();
        try {
            if (noticiasService.pauseNoticia(idNoticia, status)) {
                response.setOutMessage("OK");
            } else {
                response.setOutMessage("ERROR");
            }
        } catch (Exception e) {
            log.error("Error al pauseNoticia:" + e.getMessage());
        }
        return response;
    }

}
