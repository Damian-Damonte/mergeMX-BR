package com.metalsa.home.repository;

import com.metalsa.home.model.Widget;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author APOMR10051
 */
public interface WidgetsByRolRepository extends JpaRepository<Widget, Long> {

    @Query(value = "select distinct w.id_widget, w.widget, w.width, w.height, w.max_width, w.max_height, w.min_width, w.min_height, w.predeterminado "
            + "from nvc_tbl_widget w, nvc_tbl_widget_por_rol wr where w.id_widget = wr.id_widget "
            + "and wr.id_rol in (:rolesUsuario) and w.activo = 1 order by w.id_widget ", nativeQuery = true)
    List<Widget> getWidgetsByRol(@Param("rolesUsuario") List<Integer> rolesUsuario);

}
