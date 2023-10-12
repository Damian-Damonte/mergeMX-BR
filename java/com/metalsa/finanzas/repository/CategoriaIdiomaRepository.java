package com.metalsa.finanzas.repository;

import com.metalsa.finanzas.model.CategoriaIdioma;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author JL
 */
public interface CategoriaIdiomaRepository extends JpaRepository<CategoriaIdioma, Integer>{
    
    Iterable<CategoriaIdioma> findAllByIdioma(String idioma);
}
