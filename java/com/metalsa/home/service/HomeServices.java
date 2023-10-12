package com.metalsa.home.service;

import com.metalsa.aprobacion.service.MailNotificationService;
import com.metalsa.home.model.ItemsSugeridos;
import com.metalsa.home.pojo.Feedback;
import com.metalsa.home.pojo.WidgetDataset;
import com.metalsa.home.pojo.Widget;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.metalsa.home.repository.HomeRepository;
import com.metalsa.home.repository.ItemsSugeridosRepository;
import com.metalsa.utils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author edgar.leal
 */
@RestController
@Api(value = "The home services", tags = {"Home services"})
@RequestMapping(Constants.URI_API_HOME)
public class HomeServices {

    @Autowired
    private HomeRepository homeRepository;
    @Autowired
    private MailNotificationService mailService;
    @Autowired
    private ItemsSugeridosRepository itemsSugeridosRepository;

    @ResponseBody
    @RequestMapping(value = "/saveWidgets", method = RequestMethod.POST)
    ResponseEntity saveWidgets(@RequestBody WidgetDataset param) {
        String retorno = "";
        try {
            retorno = homeRepository.CLEAR_USER_WIDGETS(param.getIdUsuario());
            System.out.println(retorno);
            for (Widget widget : param.getWidgetList()) {
                retorno = homeRepository.INSERT_USER_WIDGET(widget.getIdWidget(), param.getIdUsuario(), widget.getPosX(), widget.getPosY(), widget.getWidth(), widget.getHeight(), widget.getFijo());
            }
        } catch (Exception e) {
            System.out.println("Fallo al guardar widgets: " + e);
        }
        return new ResponseEntity(retorno.equals("OK") ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @ResponseBody
    @RequestMapping(value = "/sendFeedback", method = RequestMethod.POST)
    ResponseEntity sendFeedback(@RequestBody Feedback param) {
        boolean retorno = false;
        try {
            retorno = mailService.sendComentariosUsuario(param.getUsuario(), param.getComentario());
        } catch (Exception e) {
            System.out.println("no se fue posible enviar la notificacion=> " + e.getLocalizedMessage());
            System.out.println(e);
        }
        return new ResponseEntity(retorno ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @ResponseBody
    @RequestMapping(value = "/addCarroCompras", method = RequestMethod.POST)
    ResponseEntity addCarroCompra(@RequestBody ItemsSugeridos param) {
        System.out.println("addCarroCompra ENTRA");
        String retorno = "";
        try {
            retorno = itemsSugeridosRepository.ADD_CARRO_COMPRAS(param.getIdUsuario(), param.getIdProducto(), param.getIdUen(),
                    param.getIdAlmacen(), param.getIdLocalizacion(), param.getDescripcion(), param.getFuente(), param.getUdm(),
                    param.getIdSubfamilia(), param.isNoImg() ? "" : param.getUrlFtp());
            System.out.println("ADD CARRO: " + retorno);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new ResponseEntity(retorno.equals("OK") ? HttpStatus.OK : HttpStatus.CONFLICT);
    }
}
