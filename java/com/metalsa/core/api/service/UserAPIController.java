package com.metalsa.core.api.service;

import com.google.common.base.Objects;
import com.metalsa.core.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.annotations.Api;
import com.metalsa.core.repository.UserRepository;
import java.util.List;
import org.springframework.util.StringUtils;
import com.metalsa.generales.model.NvcTblRolPerfil;
import com.metalsa.generales.service.HeaderService;
import com.metalsa.generales.model.PerAllPeopleF;
import com.metalsa.generales.model.Usuario;
import com.metalsa.generales.repository.UsuarioSpxRepository;

/**
 *
 * @author Edgar Leal
 *
 *
 *
 */
@RestController
@Api(value = "User", tags = {"User Service API"})
@RequestMapping("/api/v1/user")
public class UserAPIController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UsuarioSpxRepository usuarioSpxRepository;

    @Autowired
    private HeaderService headerService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<User> getAll() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody User getById(@PathVariable("id") Long id) {
        return userRepository.findOne(id);
    }

    @RequestMapping(value = "/findByNombre", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Usuario> findByNombre(@RequestParam("query") String query) {
        return usuarioSpxRepository.findByNombre(query.toUpperCase());
    }

    @RequestMapping(value = "/getEmpInfo/{personId}", method = RequestMethod.GET)
    public @ResponseBody PerAllPeopleF getEmpInfo(@PathVariable("personId") Long personId) {
        return headerService.getEmpInfo(personId);
    }

    @RequestMapping(value = "/getRolesPerfil/{idUsuario}", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<NvcTblRolPerfil> getRolesPerfil(@PathVariable("idUsuario") String idUsuario) {
        return headerService.getRolesPerfil(idUsuario);
    }

    @RequestMapping(value = "/getRolesDisponibles/{idUsuario}", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<NvcTblRolPerfil> getRolesDisponibles(@PathVariable("idUsuario") String idUsuario) {
        return headerService.getRolesDisponibles(idUsuario);
    }

    @RequestMapping(value = "/test/{id}", method = RequestMethod.GET)
    public @ResponseBody
    User getUserForNombre(@PathVariable("id") int id) {
        return userRepository.findByidUsuarioAndNombreFamilia("OLIP", "TEST");
    }

    @RequestMapping(value = "/test/", method = RequestMethod.GET)
    public @ResponseBody
    User getEmailQuery() {
        return userRepository.findByEmailAddress("1.2.3");
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    User addUser(@RequestBody User user) {
        User result;
        try {
            result = userRepository.save(user);
        } catch (Exception e) {
            result = null;
        }
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public @ResponseBody
    User editUser(@PathVariable("id") Long id, @RequestBody User user) {
        User result;
        result = userRepository.findOne(id);
        if (result == null) {
            return null;
        }
        try {
            result = userRepository.save(user);
        } catch (Exception e) {
            result = null;
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    Page<User> getAllPaged(@RequestParam(value = "page", required = true, defaultValue = "0") int page,
            @RequestParam(value = "size", required = true, defaultValue = "10") int size,
            @RequestParam(value = "sort", required = false, defaultValue = "") String[] sorting) {
        if (sorting != null && sorting.length > 0) {
            return userRepository.findAll(new PageRequest(page, size, new Sort(sorting)));
        }
        return userRepository.findAll(new PageRequest(page, size));
    }

    @RequestMapping(path = "/addRoles", method = RequestMethod.POST)
    @ResponseBody
    public Integer insertarRolesUsuarios(
            @RequestBody List<String> users,
            @RequestParam String rolName
    ) {
        String usersStr = StringUtils.collectionToCommaDelimitedString(users);
        return this.userRepository.addRolUsers(usersStr, rolName);
    }

    @RequestMapping(path = "/addRol/preaprobadorpro", method = RequestMethod.POST)
    @ResponseBody
    public Integer insertarRolesPreaprobadorPro(
            @RequestBody List<String> users
    ) {
        String usersStr = StringUtils.collectionToCommaDelimitedString(users);
        Integer status = this.userRepository.addRolUsers(usersStr, "SPX - Admin pre Aprobacion Proyecto");
        if (Objects.equal(status, 1)) {
            this.userRepository.removeRolPreaprobador();
        }
        return status;
    }

    //<rdm59310>
    @RequestMapping(value = "/getRolByNombreRolUsuario/{idUsuario}", method = RequestMethod.GET)
    public @ResponseBody
    Integer getRolByNombreRolUsuario(@PathVariable("idUsuario") String idUsuario) {
        System.out.println("idUsuario " + idUsuario);
        Integer tieneRol = userRepository.getRolByNombreRolUsuario(idUsuario);
        System.out.println("tieneRol " + tieneRol);
        return tieneRol;
    }
    //<rdm59310>
}
