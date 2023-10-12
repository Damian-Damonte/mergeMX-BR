package com.metalsa.core.repository;

import com.metalsa.core.model.NvcTblEmailToSend;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author apomr
 */
@Repository
public interface EmailToSendRepository extends JpaRepository<NvcTblEmailToSend, Integer> {

    List<NvcTblEmailToSend> findByStatus(@Param("status") String Status);

}
