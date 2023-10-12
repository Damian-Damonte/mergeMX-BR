/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.recibos.repository;

import com.metalsa.recibos.model.Udm;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author yair.nunez
 */
public interface UdmRepository extends PagingAndSortingRepository<Udm, Long> {
    Iterable<Udm> findUdmByLanguage(String language);
}
