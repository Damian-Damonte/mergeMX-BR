package com.metalsa.aprobacion.repository;

import com.metalsa.aprobacion.model.CategoryConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
public interface CategoryConfigurationRepository extends JpaRepository<CategoryConfiguration, CategoryConfiguration.Pk> {
}
