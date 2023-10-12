package com.metalsa.aprobacion.repository;

import com.metalsa.aprobacion.model.BudgetHistory;
import com.metalsa.aprobacion.model.BudgetStatusUser;
import com.metalsa.aprobacion.model.CategoryBudget;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
public interface CategoryBudgetRepository extends PagingAndSortingRepository<CategoryBudget, Long>,
        TransferenciaPresupuesto {
	//R41223
    CategoryBudget getByIdUenAndCentroCostoAndCategoriaAndPeriodo(
            Long uen,
            String cc,
            Long categoria,
            String periodo
    );

    //<MDA_INC_DEC>
    CategoryBudget getCategoriasByUenAndCcAndPeriodoAndId(
            @Param("uen") Long uen,
            @Param("cc") Long cc,
            @Param("period") String periodo,
            @Param("lang") String idioma,
            @Param("categoria") Integer idCategoria);
    //</MDA_INC_DEC>

	//R41223
    List<BudgetHistory> getLastBudgetHistory(
            @Param("uen") Long uen,
            @Param("cc") Long cc,
            @Param("months") Long months
    );

    Optional<BudgetStatusUser> getBudgetStatusByUser(
            @Param("username") String user,
            @Param("uen") Long uen,
            @Param("cc") Long cc,
            @Param("period") String period
    );
    
    List<CategoryBudget> getCategoriasByUenAndCcAndPeriodoAndIdAll(
            @Param("uen") Long uen,
            @Param("cc") List<Long> cc,
            @Param("period") String periodo,
            @Param("lang") String idioma,
            @Param("categoria") List<Integer> idCategoria);
}
