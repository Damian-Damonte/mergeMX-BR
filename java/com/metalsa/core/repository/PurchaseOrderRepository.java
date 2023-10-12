/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.repository;

import com.metalsa.core.model.PurchaseOrderDetail;
import com.metalsa.core.model.PurchaseOrderStatus;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author juan.vazquez02
 */
@Repository
public interface PurchaseOrderRepository extends PagingAndSortingRepository<PurchaseOrderDetail, Integer> {
    
    List<PurchaseOrderDetail> getByRequisitionId(int requisition, int org_id);
    PurchaseOrderStatus getByPurchaseOrderId(String ponum, int org_id);
}
