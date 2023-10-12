package com.metalsa.aprobacion.repository;

import com.metalsa.aprobacion.model.CategoryBudget;
import java.util.List;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
public interface TransferenciaPresupuesto {

	//MDA_REPORTES_FINANZAS
    int transferenciaPresupuesto(CategoryBudget origen, CategoryBudget destino, Double ammount, String usuario, 
            String modulo, String aprobador, String solicitante, String razon,String periodoDestino);

    //<MDA_INC_DEC>
    int transferenciaPresupuestoFinanzas(CategoryBudget origen, CategoryBudget destino, Double ammount, String usuario,
            String modulo, String aprobador, String solicitante, String razon);
    //</MDA_INC_DEC>
    
    List<CategoryBudget> getCategoriasByUenAndCcAndPeriodo(
            Long uen,
            Long cc,
            String periodo,
            String idioma);
}
