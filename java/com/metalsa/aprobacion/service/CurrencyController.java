package com.metalsa.aprobacion.service;

import com.metalsa.aprobacion.model.CurrencyRate;
import com.metalsa.aprobacion.model.CurrencyRates;
import com.metalsa.utils.Constants;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@RestController
@RequestMapping(Constants.URI_API_CURRENCY)
@Log4j
public class CurrencyController {

    @Autowired
    private CurrencyService service;

    @RequestMapping
    @ResponseBody
    public Iterable<CurrencyRates> getAllRates() {
        return service.getTodayRates();
    }

    @RequestMapping("/{from}")
    @ResponseBody
    public CurrencyRates getRates(@PathVariable("from") String from) {
        return service.getRatesFromCurrency(from);
    }

    @RequestMapping("/{from}/{to}")
    @ResponseBody
    public CurrencyRate getRate(@PathVariable("from") String moneda, @PathVariable("to") String uen) {
        return service.getRate(moneda, uen);//<T489111>
    }

}
