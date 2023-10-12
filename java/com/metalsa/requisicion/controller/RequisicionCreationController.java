package com.metalsa.requisicion.controller;

import com.metalsa.aprobacion.model.Usuario;
import com.metalsa.aprobacion.repository.OrganizacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.RequestBody;
import com.metalsa.aprobacion.model.NvcTblOrganizacionesH;
import com.metalsa.utils.Constants;

import org.springframework.web.bind.annotation.PostMapping;

// POJO's
import com.metalsa.requisicion.pojo.*;
import com.metalsa.requisicion.service.RequisicionService;
import com.metalsa.cart.service.CartService;
import com.metalsa.requisicion.repository.UserRepository;
import com.metalsa.requisicion.utils.ConstantsUtils;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 *
 * @author Gamaliel Espinoza M.
 *
 */
@RestController
@Api(value = "Creacion", tags = {"Creacion Service API"})
@RequestMapping(Constants.URI_API_REQUISIONES_B)
@Log4j
public class RequisicionCreationController {    
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    private RequisicionService requiService;
    
    @Autowired
    private UserRepository usersRepo; 
    
    @Autowired
    private OrganizacionesRepository uensRepo;
    
    @PostMapping("/create")
    @ResponseBody
    public Object createRequisition(
            @RequestHeader("X-SPX-UserId") String userId,
            @RequestBody List<NvcTblCarroCompraPojo> request
    ) {
        Usuario user = usersRepo.getOneById(userId);
        UsuarioPojo userPojo = new UsuarioPojo();
        userPojo.setIdUsuario(user.getId());
        userPojo.setIdioma(user.getIdioma());
        
        // TODO: asegurarse de que el uens requisitora venga en el request
        // Por ahora voy a consultar el default para poder continuar con el proceso.
        List<NvcTblOrganizacionesH> defaultUens = uensRepo.getUensActivasByUser2(userId);
        NvcTblOrganizacionesH defaultUen = defaultUens.get(0);
        request.forEach((entity) -> {
            System.out.println("idCarroCompra: " + entity.getIdCarroCompra());
            System.out.println("idLocalizacion: " + entity.getIdlocalizacion());
            if (entity.getIdUenRequisitora() == null)
                entity.setIdUenRequisitora(defaultUen.getId().intValue());
            if (entity.getFechaNecesidad() == null)
                entity.setFechaNecesidad(
                        ConstantsUtils.formattDate_DD_MM_YYYY(DateTime.now().toDate())
                );
        });
        
        RequisicionRequest req = new RequisicionRequest();
        req.setUser(userPojo);
        req.setEntityList(request);
        req.setAppOrigen("SPX");
        return requiService.createRequisicion(req, userId);
    }
}
