package com.metalsa.aprobacion.service;

import com.metalsa.aprobacion.model.CurrencyRate;
import com.metalsa.aprobacion.model.CurrencyRates;
import com.metalsa.aprobacion.repository.CurrencyRateRepository;
import com.metalsa.error.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@Service
public class CurrencyService {
    @Autowired
    private CurrencyRateRepository rates;

    @Qualifier("application")
    @Autowired
    private MessageSource messages;

    public List<CurrencyRates> getTodayRates() {
        return rates.getCurrencies().stream()
                .map(curr -> new CurrencyRates(curr, rates.getAllRatesFromCurrency(curr)))
                .collect(Collectors.toList());
    }

    public CurrencyRates getRatesFromCurrency(String from) {
        return new CurrencyRates(from, rates.getAllRatesFromCurrency(from));
    }

    CurrencyRate getRate(String moneda, String uen) {//<T489111>
        CurrencyRate cr=new CurrencyRate();
        cr.setConversionRate(rates.getRateFromDual(moneda, uen));
        return cr;
    }//</T489111>
    
    Double getRateByPartida(Long requi, Long partida) {
        return rates.getMontoByRateAndPartida(requi, partida);
    }
    
    //<MDA_MULTICC>
    Double getRateByDistribucion(Long requi, Long partida, Long cuenta) {
        return rates.getMontoByRateAndDistribucion(requi, partida, cuenta);
    }
    //</MDA_MULTICC>
}
