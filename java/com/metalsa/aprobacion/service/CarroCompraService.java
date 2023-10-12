package com.metalsa.aprobacion.service;

import com.metalsa.aprobacion.model.CarroCompra;
import com.metalsa.aprobacion.model.NvcTblFad;
import com.metalsa.aprobacion.model.NvcTblOrganizacionesH;
import com.metalsa.aprobacion.repository.CarroCompraRepository;
import com.metalsa.aprobacion.repository.NvcTblFadRepository;
import com.metalsa.aprobacion.repository.OrganizacionesRepository;
import com.metalsa.aprobacion.repository.RolesPorUsuarioRepository;
import com.metalsa.core.api.service.TokenApiController;
import com.metalsa.core.pojo.Token;
import com.metalsa.error.NoOwnerException;
import com.metalsa.error.NotFoundException;
import com.metalsa.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Created by ruben.bresler on 12/07/2017.
 */
@CrossOrigin
@RestController
@RequestMapping(Constants.URI_API_CARROCOMPRAS)
@Slf4j
public class CarroCompraService {

    @Qualifier("application")
    @Autowired
    private MessageSource messages;

    @Autowired
    private CarroCompraRepository shoppingCart;

    @Autowired
    private OrganizacionesRepository uens;

    @Autowired
    private RolesPorUsuarioRepository roles;

    @Autowired
    private NvcTblFadRepository fad;

    @RequestMapping(method = RequestMethod.GET)
    public Page<CarroCompra> getCarroCompras(@PageableDefault Pageable page) {
        return shoppingCart.findAllByOrderByIdDesc(page);
    }

    @RequestMapping(value = "/preview/{user}", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<CarroCompra> getPreviewCarroCompras(
            @PathVariable(value = "user") String usuario,
            @PageableDefault Pageable page) {

        //Se filtra por las uens activas registradas en la nueva tabla
        List<Long> uensByUser = uens.getUensActivasByUser(usuario).stream()
                .map(NvcTblOrganizacionesH::getId)
                .collect(Collectors.toList());

//        log.debug("uens: " + uens);
        if (roles.countByRolAndUsuario(Constants.ROL_INTER_UEN, usuario) > 0) {
            log.debug("uensByUser: " + uensByUser);
            log.debug("usuario con permiso INTER_UEN");
            return shoppingCart.getPreviewCarroCompraInterUen(usuario, uensByUser);
        }
        
        return shoppingCart.getPreviewCarroCompra(usuario,uensByUser);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteItemCarroCompra(
            @PathVariable("id") Long id,
            @RequestParam("token") String token,
            @RequestParam(value = "lang", required = false, defaultValue = "es") Locale locale) {

        CarroCompra item = shoppingCart.findOne(id);

        if (item == null) {
            throw new NotFoundException(messages.getMessage(
                    "words.cart.not.found",
                    new Object[]{id},
                    locale)
            );
        }

        Token t = TokenApiController.getToken(token);

        if (!(StringUtils.isNotBlank(t.getIdUsuario()) && t.getIdUsuario().equalsIgnoreCase(item.getUsuario()))) {
            throw new NoOwnerException(messages.getMessage(
                    "words.cart.invalid.owner",
                    new Object[]{t.getIdUsuario()},
                    locale)
            );
        }
        shoppingCart.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/fadByIdFad/{idFad}", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<NvcTblFad> getFadByIdFad(
            @PathVariable(value = "idFad") Integer idFad) {
        return fad.getByIdFad(idFad);
    }

    @RequestMapping(value = "/fadByIdReq/{idRequisicion}", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<NvcTblFad> getFadByIdRequisicion(
            @PathVariable(value = "idRequisicion") Integer idRequisicion) {
        return fad.getFadByIdReq(idRequisicion);
    }
}
