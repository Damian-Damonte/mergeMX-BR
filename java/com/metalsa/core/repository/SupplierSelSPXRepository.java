/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.repository;

import com.metalsa.core.model.SupplierSelSPX;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author APOJO9952
 */
@Repository
public interface SupplierSelSPXRepository  extends PagingAndSortingRepository<SupplierSelSPX, Long> {
        Iterable<SupplierSelSPX> findAllSupSpx(int idRequisicion, int idPartida);
}
