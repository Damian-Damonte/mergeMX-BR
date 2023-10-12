/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;

/**
 *
 * @author juan.vazquez02
 */
@Entity
@NamedNativeQueries({
    @NamedNativeQuery(name = "PurchaseOrderDetail.getByPurchaseOrderId",
        query = "   SELECT "
            + "         poha.segment1 po_num, "
            + "         NVL(SUM(poda.quantity_delivered), 0) quantity_delivered, "
            + "         NVL(SUM(poda.quantity_ordered), 0) quantity_ordered, "
            + "         poha.authorization_status status "
            + "     FROM po_headers_all poha "
            + "         INNER JOIN po_distributions_all poda ON poda.po_header_id = poha.po_header_id "
            + "     WHERE poha.segment1 = ?1 and poda.org_id = ?2 "
            + "     GROUP BY poha.segment1, poha.authorization_status ",
            resultClass = PurchaseOrderStatus.class, resultSetMapping = "PurchaseOrderStatusMap"
    )
})
@SqlResultSetMappings({
    @SqlResultSetMapping(name = "PurchaseOrderStatusMap",
            classes = {
                @ConstructorResult(targetClass = PurchaseOrderStatus.class,
                    columns = {
                      @ColumnResult(name = "po_num", type = String.class),
                      @ColumnResult(name = "quantity_delivered", type = Integer.class),
                      @ColumnResult(name = "quantity_ordered", type = Integer.class),
                      @ColumnResult(name = "status", type = String.class)
                    })
            }
    )})
public class PurchaseOrderStatus implements Serializable {
    
    @Id
    @Column(name = "po_num")
    private String ponum;
    
    @Column(name = "quantity_delivered")
    private Integer quantitydelivered;

    @Column(name = "quantity_ordered")
    private Integer quantityordered;
    
    @Column(name = "status")
    private String status;

    public PurchaseOrderStatus() {
        
    }
    
    public PurchaseOrderStatus(String ponum, Integer quantitydelivered, Integer quantityordered, 
            String status) {
        this.ponum = ponum;
        this.quantitydelivered = quantitydelivered;
        this.quantityordered = quantityordered;
        this.status = status;
    }

    public String getPonum() {
        return ponum;
    }

    public void setPonum(String ponum) {
        this.ponum = ponum;
    }

    public Integer getQuantitydelivered() {
        return quantitydelivered;
    }

    public void setQuantitydelivered(Integer quantitydelivered) {
        this.quantitydelivered = quantitydelivered;
    }

    public Integer getQuantityordered() {
        return quantityordered;
    }

    public void setQuantityordered(Integer quantityordered) {
        this.quantityordered = quantityordered;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PurchaseOrderStatus{" + "ponum=" + ponum + ", quantitydelivered=" + quantitydelivered + ", quantityordered=" + quantityordered + ", status=" + status + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.ponum);
        hash = 67 * hash + Objects.hashCode(this.quantitydelivered);
        hash = 67 * hash + Objects.hashCode(this.quantityordered);
        hash = 67 * hash + Objects.hashCode(this.status);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PurchaseOrderStatus other = (PurchaseOrderStatus) obj;
        if (!Objects.equals(this.ponum, other.ponum)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.quantitydelivered, other.quantitydelivered)) {
            return false;
        }
        return Objects.equals(this.quantityordered, other.quantityordered);
    }

}
