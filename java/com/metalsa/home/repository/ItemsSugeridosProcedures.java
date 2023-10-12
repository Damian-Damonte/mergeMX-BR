package com.metalsa.home.repository;

import com.metalsa.home.model.ItemsSugeridos;
import java.util.List;

/**
 *
 * @author APOMR10051
 */
public interface ItemsSugeridosProcedures {

    List<ItemsSugeridos> GET_ITEMS_SUGERIDOS_BY_USER(String P_ID_USUARIO);

}
