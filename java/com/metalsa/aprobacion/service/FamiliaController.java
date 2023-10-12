package com.metalsa.aprobacion.service;

import com.metalsa.aprobacion.model.Familia;
import com.metalsa.aprobacion.repository.FamiliaRepository;
import com.metalsa.utils.Constants;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Oscar del Angel
 */
@Log4j
@RestController
@RequestMapping(Constants.URI_API_FAMILIA)
@CrossOrigin
public class FamiliaController {

    @Autowired
    private FamiliaRepository familiaRepository;

    @GetMapping(value = "/findAllFamilia",  params = {"idioma"})
    public Iterable<Familia> findAllFamilia(String idioma) {
        return this.familiaRepository.findAllByIdioma(idioma);
    }

}
