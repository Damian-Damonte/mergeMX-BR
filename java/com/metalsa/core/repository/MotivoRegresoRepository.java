/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.repository;
import com.metalsa.core.model.MotivoRegresoView;
import java.math.BigInteger;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
/**
 *
 * @author edgar.leal
 */
@Repository
public interface MotivoRegresoRepository extends PagingAndSortingRepository<MotivoRegresoView, Long>{
    MotivoRegresoView findByIdRequisicionAndIdPartida(BigInteger requision, BigInteger partida);
}
