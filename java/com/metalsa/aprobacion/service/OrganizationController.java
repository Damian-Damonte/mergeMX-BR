package com.metalsa.aprobacion.service;
//MDA_REPORTES_FINANZA  
import com.metalsa.aprobacion.model.NvcTblOrganizacionesH;
import com.metalsa.aprobacion.model.UsuarioUen;
import com.metalsa.aprobacion.repository.OrganizacionesRepository;
import com.metalsa.aprobacion.repository.UsuarioUenRepository;
import com.metalsa.utils.Constants;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@RestController
@RequestMapping(Constants.URI_API_UENS)
public class OrganizationController {

    private OrganizacionesRepository uens;

    private UsuarioUenRepository usuariosPorUen;
    
    //MDA_REPORTES_FINANZA 
    public OrganizationController(OrganizacionesRepository uens, UsuarioUenRepository usuariosPorUen) {
        this.uens = uens;
        this.usuariosPorUen = usuariosPorUen;
    }

    @RequestMapping
    @ResponseBody
    public Page<NvcTblOrganizacionesH> getAll(@PageableDefault Pageable page) {
        return uens.findAll(page);
    }

    //<MDA_INC_DEC>
    @RequestMapping("/activas")
    @ResponseBody
    public Iterable<NvcTblOrganizacionesH> getAllUensActivas() {
        return uens.getAllUensActivas();
    }
    //</MDA_INC_DEC>

    @RequestMapping("/{idUen}")
    @ResponseBody
    @Cacheable("uen")
    public NvcTblOrganizacionesH getUen(@PathVariable Long idUen) {
        return uens.findOne(idUen);
    }

    //MDA_REPORTES_FINANZA    
    @GetMapping("/{uen}/users")
    public Iterable<UsuarioUen> getUsuariosPorUen(@PathVariable("uen") Long uen) {
        return usuariosPorUen.findAllByIdUenOrderByIdUsuario(uen);
    }

    @GetMapping("/preaprobacion/{user}")
    public Iterable<NvcTblOrganizacionesH> getUsuariosPorUen(@PathVariable("user") String usuario) {
        return uens.getUensPreaprobacion(usuario);
    }

    @GetMapping("/uensactivasdisp/{user}")
    public Iterable<NvcTblOrganizacionesH> getUensActivasDispByUser(@PathVariable("user") String usuario) {
        return uens.getUensActivasDispByUser(usuario);
    }
}
