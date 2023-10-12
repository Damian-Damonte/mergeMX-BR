package com.metalsa.finanzas.repository;

import com.metalsa.aprobacion.model.CategoryBudget;
import com.metalsa.finanzas.model.RespuestaPojo;
import com.metalsa.finanzas.model.TransaccionFinanzas;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author JL
 */
public interface TransaccionFinanzasRepository extends JpaRepository<TransaccionFinanzas, Long> {

    RespuestaPojo modificarPresupuesto(Long idUen, String idCcs, String idCategorias,
            String periodo, String tipoTransaccion, Double porcentaje,
            Double monto, String moneda, String solicitante, String aprobador,
            String razon, String usuario, Long idTransaccion);

	//MDA_REPORTES_FINANZAS
    int transferenciaPresupuestoFinanzas(CategoryBudget origen, CategoryBudget destino, Double ammount, String usuario,
            String modulo, String aprobador, String solicitante, String razon);
}
