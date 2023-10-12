package com.metalsa.admin.repository;

import com.metalsa.home.model.NoticiaHome;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author miguel.rdz
 */
public interface NoticiaHomeRepository extends JpaRepository<NoticiaHome, Integer> {

    Page<NoticiaHome> getHistorico(Pageable page);
    
    Page<NoticiaHome> getHistoricoFiltros(List<String> ids, String titulo, List<Integer> uens, List<Integer> roles, List<String> uCreacion, List<String> uActualizacion, Pageable page);
    
    Page<NoticiaHome> getHistoricoFiltros(List<String> ids, String titulo, List<String> uens, List<String> roles, List<String> uCreacion, List<String> uActualizacion, String fechaIni, String fechaFin, Pageable page);

}
