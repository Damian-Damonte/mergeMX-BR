package com.metalsa.home.repository;

import com.metalsa.home.model.Home;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author edgar.leal
 */
public interface HomeRepository extends PagingAndSortingRepository<Home, Long> {

    @Procedure(name = "CLEAR_USER_WIDGETS")
    String CLEAR_USER_WIDGETS(@Param("P_ID_USUARIO") String P_ID_USUARIO);
    
    @Procedure(name = "INSERT_USER_WIDGET")
    String INSERT_USER_WIDGET(@Param("P_ID_WIDGET") Number P_ID_WIDGET, @Param("P_ID_USUARIO") String P_ID_USUARIO, @Param("P_POS_X") Number P_POS_X, @Param("P_POS_Y") Number P_POS_Y, @Param("P_WIDTH") Number P_WIDTH, @Param("P_HEIGHT") Number P_HEIGHT, @Param("P_FIJO") Number P_FIJO);

}
