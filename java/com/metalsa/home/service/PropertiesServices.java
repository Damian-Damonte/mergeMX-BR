package com.metalsa.home.service;

import com.metalsa.utils.Constants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author APOMR10051
 */
@RestController
@RequestMapping(Constants.URI_API_HOME)
public class PropertiesServices {

    @Getter
    @Setter
    @Value("${urlTutorial}")
    private String help;
    
    @Getter
    @Setter
    @Value("${iis.root}")
    private String iisRoot;
    
    @Getter
    @Setter
    @Value("${iis.env}")
    private String iisEnv;
    
    @Getter
    @Setter
    @Value("${ftpcat.appimg}")
    private String appImg;

    @RequestMapping(value = "/getHelpUrl", method = RequestMethod.GET)
    public String getHelpUrl() {
        return "{\"url\": \"" + help + "\"}";
    }
    
    //<RDM14265> APOMR10051 Widget no carga imagenes
    @RequestMapping(value = "/getImgServer", method = RequestMethod.GET)
    public String getImgServer() {
        return "{\"url\": \"" + iisRoot.concat(iisEnv) + "\"}";
    }
    //<RDM14265>
    
    @RequestMapping(value = "/getAppImagesUrl", method = RequestMethod.GET)
    public String getAppImagesUrl() {
        return "{\"url\": \"" + iisRoot.concat(iisEnv).concat(appImg) + "\"}";
    }
    
    //<MDA_INC_DEC>
    @RequestMapping(value = "/getRootUrl", method = RequestMethod.GET)
    public String getRootUrl() {
        return "{\"url\": \"" + iisRoot+ "\"}";
    }
    //</MDA_INC_DEC>
}
