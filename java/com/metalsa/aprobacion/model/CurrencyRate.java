package com.metalsa.aprobacion.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * modelo para cambios de moneda
 */
@Data
@NoArgsConstructor
@Entity(name = "nvc_vm_gl_daily_rates")
@IdClass(CurrencyRate.Pk.class)
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "CurrencyRate.getAllRatesFromCurrency",
                resultClass = CurrencyRate.class,
                query = "select cr.* " +
                        "from nvc_vm_gl_daily_rates cr " +
                        "  join (select from_currency, to_currency, max(conversion_date) conversion_date " +
                        "        from nvc_vm_gl_daily_rates " +
                        "        where conversion_type = 'Corporate' " +
                        "        group by from_currency, to_currency) m " +
                        "    on m.from_currency = cr.from_currency " +
                        "      and m.to_currency = cr.to_currency " +
                        "      and m.conversion_date = cr.conversion_date " +
                        "where cr.conversion_type = 'Corporate' " +
                        "  and cr.from_currency = ? " +
                        "order by cr.to_currency"
        ),
        @NamedNativeQuery(
                name = "CurrencyRate.getRate",
                resultClass = CurrencyRate.class,
                query = "select cr.* " +
                        "from nvc_vm_gl_daily_rates cr " +
                        "where cr.conversion_type = 'Corporate' " +
                        "  and cr.from_currency = ?1 " +
                        "  and cr.to_currency = ?2 " +
                        "  and rownum = 1 " +
                        "order by cr.conversion_date desc"
        )
})
public class CurrencyRate implements Serializable {

    @Id
    private String toCurrency;

    @Id
    private String fromCurrency;

    @Id
    private Date conversionDate;

    @Id
    private String conversionType;
    private Double conversionRate;
    private String statusCode;

    public CurrencyRate(String fromCurrency, String toCurrency, Date conversionDate, String conversionType, Double conversionRate, String statusCode) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.conversionDate = new Date(conversionDate.getTime());
        this.conversionType = conversionType;
        this.conversionRate = conversionRate;
        this.statusCode = statusCode;
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

    /**
     * Primary key
     */
    @Data
    public static class Pk implements Serializable {
        private String fromCurrency;
        private String toCurrency;
        private Date conversionDate;
        private String conversionType;

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
}
