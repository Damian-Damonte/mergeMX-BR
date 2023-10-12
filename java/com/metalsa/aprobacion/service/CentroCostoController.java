package com.metalsa.aprobacion.service;

import com.metalsa.aprobacion.model.CcPorUsuario;
import com.metalsa.aprobacion.model.CentroCosto;
import com.metalsa.aprobacion.model.UsuarioUen;	//<MDA_CONTRALOR>
import com.metalsa.aprobacion.repository.CentroCostoRepository;
import com.metalsa.aprobacion.repository.MembresiaCentroCostoRepository;
import com.metalsa.core.model.CentroCostoUsuario;
import com.metalsa.error.NotFoundException;
import com.metalsa.utils.Constants;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@RestController
@RequestMapping(Constants.URI_API_CC)
public class CentroCostoController {

    @Autowired
    private CentroCostoRepository centros;

    @Autowired
    private MembresiaCentroCostoRepository miembros;

    @Qualifier("application")
    @Autowired
    private MessageSource messages;

    //MDA_REPORTES_FINANZAS
    @GetMapping(path = "/byuenidioma", params = {"uen", "idioma"})
    public Iterable<CentroCosto> getAllByUen(Long uen, String idioma) {
        if (uen != null) {
            return centros.getAllByIdUenAndIdioma(uen, idioma);
        }
        return centros.getAllDistinctCCS(idioma);
    }
    @RequestMapping(value = "/porrespdel", params = {"uen", "usuario", "idioma"})
    public Iterable<CentroCosto> getPorRespDel(Long uen, String usuario, String idioma) {
        
            return centros.getAllByRespAndDel(uen,usuario, idioma);
    }

    @RequestMapping("/{id}/{uen}/{lang}")
    @ResponseBody
    public CentroCosto getCC(@PathVariable Long id, @PathVariable Long uen, @PathVariable String lang) {
        //<MDA_INC_DEC>
        return centros.getByIdAndIdUenAndIdioma(id, uen, Constants.getIdioma(lang));
        //</MDA_INC_DEC>

    }

    @RequestMapping("/{id}/members")
    @ResponseBody
    public Iterable<CcPorUsuario> getMembersByCc(@PathVariable Long id) {
        return miembros.getAllByCcOrderByUenAscRelacionDescUsuarioDesc(id);
    }

    @RequestMapping("/{id}/members/{uen}")
    @ResponseBody
    public Iterable<CcPorUsuario> getMembersByCcAndUen(
            @PathVariable Long id,
            @PathVariable Long uen) {
        return miembros.getAllByCcAndUenOrderByRelacionDescUsuarioDesc(id, uen);
    }

    @RequestMapping("/{id}/members/{uen}/{usuario}")
    @ResponseBody
    public CcPorUsuario getMemberByCcAndUenAndUser(
            @PathVariable Long id,
            @PathVariable Long uen,
            @PathVariable String usuario) {
        return miembros.getUsuarioExtByCcAndUenAndUsuario(id, uen, usuario)
                .orElseThrow(() -> new NotFoundException(messages.getMessage("words.cc.member.not-found", new Object[]{usuario},
                LocaleContextHolder.getLocale())));
    }

    //MDA_REPORTES_FINANZAS
    @RequestMapping("/resp/{uen}/{usuario}/{lang}/{tipoRelacion}")
    @ResponseBody
    public Iterable<CcPorUsuario> getCCResponsable(@PathVariable Long uen, @PathVariable String usuario, @PathVariable String lang, @PathVariable List<String> tipoRelacion) {
        return miembros.getnombreCcByUsuarioAndUenAndUsuario(uen, usuario, lang, tipoRelacion);
    }

    //<MDA_INC_DEC>
    @RequestMapping("/relacion/{uen}/{usuario}/{lang}")
    @ResponseBody
    public Iterable<CcPorUsuario> getnombreCcByUenAndLangAndUsuario(@PathVariable Long uen, @PathVariable String usuario, @PathVariable String lang) {
        return miembros.getnombreCcByUenAndLangAndUsuario(uen, usuario, lang);
    }

    @RequestMapping("/ccsdefault/{usuario}/{lang}")
    @ResponseBody
    public Iterable<CcPorUsuario> getCCSDefault(@PathVariable String usuario, @PathVariable String lang) {
        return miembros.getCCSDefault(usuario, Constants.getIdioma(lang));
    }
    //</MDA_INC_DEC>

    @RequestMapping(value = "/portiporelacion", params = {"uen", "usuario", "relacion", "idioma"})
    public Iterable<CentroCostoUsuario> getPorTipoRelacion(Long uen, String usuario, String relacion, String idioma) {
        return this.miembros.getByUserUenRelations(idioma, usuario, relacion, uen.intValue());
    }

    @RequestMapping(value = "/relaciones", params = {"idioma", "usuario", "uen", "cc"})
    public Iterable<CentroCostoUsuario> getRelaciones(String idioma, String usuario, Integer uen, Long cc) {
        return this.miembros.getByUserUenCC(idioma, usuario, uen, cc);
    }

    @RequestMapping(value = "/usuario/portiporelacion", params = {"uen", "relacion"})
    public Iterable<UsuarioUen> getUsuariosPorTipoRelacion(Long uen, String relacion) {
        return this.miembros.getUsuariosPorTipoRelacion(uen, relacion);
    }

    //MDA_REPORTES_FINANZAS
    @RequestMapping("/getRespccOrDel/{idUen}/{idCc}/{usuario}")
    @ResponseBody
    public CcPorUsuario getRespccOrDel(@PathVariable Long idUen, @PathVariable Long idCc, @PathVariable String usuario) {
        return miembros.getRespccOrDel(idUen, idCc, usuario, "Resp");
    }

    //</MDA_CONTRALOR>
}
