package com.metalsa.home.service;

import com.metalsa.aprobacion.model.DetalleRequisicion;
import com.metalsa.aprobacion.model.DetalleRequisicionWidget;
import com.metalsa.home.model.Widget;
import com.metalsa.aprobacion.repository.DetalleRequisicionRepository;
import com.metalsa.aprobacion.repository.DetalleRequisicionWidgetRepository;
import com.metalsa.home.model.NoticiaHome;
import com.metalsa.home.model.ItemsSugeridos;
import com.metalsa.home.model.WidgetPorUsuario;
import com.metalsa.home.repository.HomeService;
import com.metalsa.home.repository.ItemsSugeridosRepository;
import com.metalsa.home.repository.WidgetsByRolRepository;
import com.metalsa.home.repository.WidgetsByUserRepository;
import com.metalsa.utils.Constants;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author APOMR10051
 */
@RestController
@RequestMapping(Constants.URI_API_HOME)
public class WidgetServices {

    private DetalleRequisicionRepository detalleRequi;
    private WidgetsByRolRepository widgetRol;
    private WidgetsByUserRepository widgetUser;
    private ItemsSugeridosRepository itemsSugeridos;
    private DetalleRequisicionWidgetRepository detalleRequiWidget; //<R17226>

    @Autowired
    private HomeService homeServices;

    @Getter
    @Setter
    @Value("${iis.root}")
    private String iisRoot;

    @Getter
    @Setter
    @Value("${iis.env}")
    private String env;

    //<R17226>
    public WidgetServices(DetalleRequisicionRepository detalleRequi, WidgetsByRolRepository widgetRol, WidgetsByUserRepository widgetUser, ItemsSugeridosRepository itemsSugeridos, DetalleRequisicionWidgetRepository detalleRequiWidget) {
        this.detalleRequi = detalleRequi;
        this.widgetRol = widgetRol;
        this.widgetUser = widgetUser;
        this.itemsSugeridos = itemsSugeridos;
        this.detalleRequiWidget = detalleRequiWidget;
    }
    //</R17226>

    @GetMapping("/requiById")
    Page<DetalleRequisicion> getRequisicionById(@RequestParam("idRequisicion") String idRequisicion,
            @PageableDefault Pageable page) {
        return detalleRequi.getRequisicionById(idRequisicion, page);
    }

    //<R17226>
    @GetMapping("/estatusRequis")
    Iterable<DetalleRequisicionWidget> getRequisicionesByUser(@RequestParam("idIdioma") String idIdioma,
            @RequestParam("idUsuario") String usuario,
            @RequestParam("estatus") List<String> status) {
        List<DetalleRequisicionWidget> requis = detalleRequiWidget.getRequisByUsuarioAndEstatus(idIdioma, usuario, status);
        return requis;
    }
    //</R17226>

    @GetMapping("/getWidgetsByRol")
    Iterable<Widget> getWidgetsByRol(@RequestParam("rolesUsuario") List<Integer> rolesUsuario) {
        return widgetRol.getWidgetsByRol(rolesUsuario);
    }

    @GetMapping("/getWidgetsByUser")
    Iterable<WidgetPorUsuario> getWidgetsByUser(@RequestParam("rolesUsuario") List<Integer> rolesUsuario,
            @RequestParam("idUsuario") String idUsuario) {

        List widgetsUsuario = widgetUser.getWidgetsByUser(rolesUsuario, idUsuario);
        if (null == widgetsUsuario || widgetsUsuario.isEmpty()) {
            widgetUser.creaWidgetsDefault(idUsuario);
            widgetsUsuario = widgetUser.getWidgetsByUser(rolesUsuario, idUsuario);
        }
        
        return widgetsUsuario;
    }

    @GetMapping("/getItemsSugeridos")
    Iterable<ItemsSugeridos> getItemsSugeridos(@RequestParam("idUsuario") String idUsuario) {
        List<ItemsSugeridos> items = itemsSugeridos.GET_ITEMS_SUGERIDOS_BY_USER(idUsuario);
        items.forEach(i -> {
            i.setIdUsuario(idUsuario);
            if (StringUtils.isNotBlank(i.getUrlFtp())) {
                if (i.getUrlFtp().contains("http")) {
                    i.setUrlIis(i.getUrlFtp());
                } else {
                    String iis = iisRoot.concat(env).concat(i.getUrlFtp().replaceAll(" ", "%20"));
                    try {
                        URL url = new URL(iis);
                        HttpURLConnection huc = (HttpURLConnection) url.openConnection();
                        huc.setRequestMethod("GET");
                        huc.connect();
                        if (huc.getResponseCode() == HttpURLConnection.HTTP_OK) {
                            i.setNoImg(false);
                            i.setUrlIis(iis);
                        } else {
                            i.setNoImg(true);
                            i.setUrlIis(iisRoot.concat(env).concat("resources/imgs/no_image.jpg"));
                        }
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(WidgetServices.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(WidgetServices.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                i.setNoImg(true);
                i.setUrlIis(iisRoot.concat(env).concat("resources/imgs/no_image.jpg"));
            }
        });
        return items;
    }

    @GetMapping("/getNoticias")
    Iterable<NoticiaHome> getNoticias(@RequestParam("idIdioma") String idIdioma) {
        Iterable<NoticiaHome> noticias = homeServices.getNoticias(idIdioma);
        noticias.forEach(n -> {
            String fullPath = iisRoot.concat(env).concat(n.getRuta());
            if (validateUrl(fullPath)) {
                n.setUrlMedia(fullPath);
            } else {
                n.setIdTipoNoticia(4);
                n.setDuracion(5000);
                n.setUrlMedia(iisRoot.concat(env).concat("resources/noticias/404_" + idIdioma + ".html"));
            }

        });
        return noticias;
    }

    private boolean validateUrl(String path) {
        try {
            path = path.replaceAll(" ", "%20");
            URL url = new URL(path);
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            huc.setRequestMethod("GET");
            huc.connect();
            return huc.getResponseCode() == HttpURLConnection.HTTP_OK;
        } catch (MalformedURLException ex) {
            Logger.getLogger(WidgetServices.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WidgetServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
