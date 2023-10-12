package com.metalsa.aprobacion.repository;

import com.metalsa.aprobacion.model.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, CurrencyRate.Pk> {

    List<CurrencyRate> getAllRatesFromCurrency(String currency);

    @Query(value = "select distinct cr.from_currency " +
            "from nvc_vm_gl_daily_rates cr " +
            "where cr.conversion_type = 'Corporate' " +
            "  and trunc(cr.conversion_date) <= trunc(sysdate) " +
            "  and trunc(cr.conversion_date) > trunc(sysdate-1) " +
            "order by cr.from_currency",
            nativeQuery = true)
    List<String> getCurrencies();

    Optional<CurrencyRate> getRate(String from, String to);

    @Query(value = "select det.monto_extendido * tipo_cambio_f(det.id_moneda, det.id_uen) cr " +
            "from detalle_de_requisicion det " +
            "where det.id_requisicion = ?1 " +
            "  and det.id_partida = ?2", nativeQuery = true)
    Double getMontoByRateAndPartida(Long requi, Long partida);
    //<T489111>
    @Query(value = "SELECT (compras_as.TIPO_CAMBIO_F (?1, ?2)) CONVERSION_RATE FROM DUAL", nativeQuery = true)
    Double getRateFromDual(String moneda, String uen);
    //</T489111>    
    //<MDA_MULTICC>
    @Query(value = "SELECT case det.fuente when 'C' then det.monto_extendido * dist.porcentaje else dist.monto end * tipo_cambio_f (det.id_moneda, det.id_uen) cr" +
            "  FROM nvc_tbl_distribucion_requi dist" +
            "       INNER JOIN detalle_de_requisicion det" +
            "          ON     det.id_requisicion = dist.id_requisicion" +
            "             AND det.id_partida = dist.id_partida" +
            " WHERE     dist.id_requisicion = ?1" +
            "       AND dist.id_partida = ?2" +
            "       AND dist.id_cuenta = ?3", nativeQuery = true)
    Double getMontoByRateAndDistribucion(Long requi, Long partida, Long cuenta);
    //<MDA_MULTICC/>
}
