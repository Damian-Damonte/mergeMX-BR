package com.metalsa.finanzas.service;

import com.metalsa.aprobacion.model.CategoryBudget;
import com.metalsa.aprobacion.repository.CategoryBudgetRepository;
import com.metalsa.finanzas.model.CategoriaIdioma;
import com.metalsa.finanzas.repository.CategoriaIdiomaRepository;
import com.metalsa.utils.Constants;
import java.util.List;
import java.util.Map;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author JL
 */
@RestController
@RequestMapping(Constants.URI_API_FINAZAS)
@Log4j
public class CategoriaIdiomaService {
    
    @Autowired
    private CategoriaIdiomaRepository categoriaIdiomaRepository;
    
    @Autowired
    private CategoryBudgetRepository categoryBudgetRepository;
    
    @RequestMapping(value = "/categorias/{idioma}",method = RequestMethod.GET)
    public Iterable<CategoriaIdioma> getCategorias(@PathVariable(value = "idioma") String idioma){
        return categoriaIdiomaRepository.findAllByIdioma(Constants.getIdioma(idioma));
    }
    
    @RequestMapping(value = "/categoria",method = RequestMethod.GET)
    public CategoryBudget getCategoriasByUenAndCcAndPeriodoAndId(@RequestParam("uen") Long uen,
            @RequestParam(value = "cc", required = true) Long centroCosto,
            @RequestParam(value = "lang", required = true) String idioma,
            @RequestParam(value = "periodo", required = true) String periodo,
            @RequestParam(value = "categoria", required = true) Integer idCategoria){
        return categoryBudgetRepository.
                getCategoriasByUenAndCcAndPeriodoAndId(
                        uen, centroCosto, periodo, Constants.getIdioma(idioma), idCategoria);
        
    }
    
    @RequestMapping(value = "/categoriaAll",method = RequestMethod.POST)
    public Iterable<CategoryBudget> getCategoriasByUenAndCcAndPeriodoAndIdAll(@RequestBody Map<String,Object> data){
        String idioma= data.get("lang").toString();
        String periodo = data.get("periodo").toString();
        Long uen = Long.valueOf(data.get("uen").toString());
        List<Long> cc = (List)data.get("cc");
        List<Integer> cates = (List)data.get("categorias");
        List<CategoryBudget> result=  categoryBudgetRepository.
                getCategoriasByUenAndCcAndPeriodoAndIdAll(
                        uen, cc, periodo, Constants.getIdioma(idioma), cates);
        return result;
    }
    
}
