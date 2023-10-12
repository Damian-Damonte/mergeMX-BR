package com.metalsa.aprobacion.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@Data
public class CurrencyRates implements Serializable {
    public String currency;
    public List<Rates> rates;

    /**
     * rates
     */
    @Data
    public static class Rates implements Serializable{
        private String currency;
        private Date conversionDate;
        private Double rate;

        public Rates(String currency, Date conversionDate, Double rate) {
            this.currency = currency;
            this.conversionDate = new Date(conversionDate.getTime());
            this.rate = rate;
        }

        public Date getConversionDate() {
            return conversionDate == null ? null : new Date(conversionDate.getTime());
        }

        public void setConversionDate(Date conversionDate) {
            if (conversionDate == null)
                this.conversionDate = null;
            else
                this.conversionDate = new Date(conversionDate.getTime());
        }
    }

    public CurrencyRates(String currency, List<CurrencyRate> rates) {
        this.currency = currency;
        this.rates = rates.stream()
                .map(r -> new Rates(r.getToCurrency(), r.getConversionDate(), r.getConversionRate()))
                .collect(Collectors.toList());
    }
}
