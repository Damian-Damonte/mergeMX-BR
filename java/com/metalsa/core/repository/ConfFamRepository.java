/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.repository;

import com.metalsa.core.model.NvcTblConfFam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author apojm20015
 */

@Repository
public interface ConfFamRepository extends JpaRepository<NvcTblConfFam, Integer> {
    
}
