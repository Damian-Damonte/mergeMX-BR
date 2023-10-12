package com.metalsa.analytic.ux;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 * @param <T> Tipo de entidad
 */
@NoRepositoryBean
public interface UxCaptureRepository<T extends UxCapture> extends JpaRepository<T, Long> {
}
