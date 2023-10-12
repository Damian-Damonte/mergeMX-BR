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
    @NamedNativeQuery(name = "PurchaseOrderDetail.getByRequisitionId",
        query = " SELECT * FROM (  "
            + "     SELECT "
            + "         poha.segment1 po_num, "
            + "         poha.currency_code currency_code, "
            + "         poha.org_id org_id, "
            + "         poha.revision_num revision_num, "
            + "         poha.authorization_status status, "
            + "         poha.vendor_id, "
            + "         porl.item_description description, "
            + "         poda.quantity_ordered order_qty, "
            + "         porl.line_num po_linenum, "
            + "         'METITEM1' itemsetid, "
            + "         porl.line_type_id line_type, "
            + "         porl.unit_meas_lookup_code order_unit, "
            + "         porl.unit_price unit_cost, "
            + "         1 conversion, "  
            + "         poda.code_combination_id gldebitacct, "
            + "         porl.line_num mt_prline, "                
            + "         poha.ship_to_location_id location "                                
            + "     FROM po_requisition_headers_all porh "
            + "         INNER JOIN po_requisition_lines_all porl ON porl.requisition_header_id = porh.requisition_header_id "
            + "         INNER JOIN po_req_distributions_all podi ON podi.requisition_line_id = porl.requisition_line_id "
            + "         INNER JOIN po_distributions_all poda ON poda.req_distribution_id = podi.distribution_id "
            + "         INNER JOIN po_lines_all pola ON pola.po_line_id = poda.po_line_id "
            + "         INNER JOIN po_headers_all poha ON poha.po_header_id = pola.po_header_id "
            + "     WHERE porh.segment1 = concat('CEP', ?1) and porh.org_id = ?2 "
            + "     UNION ALL "
            + "     SELECT "
            + "         poha.segment1 po_num, "
            + "         poha.currency_code currency_code, "
            + "         poha.org_id org_id, "
            + "         poha.revision_num revision_num, "
            + "         poha.authorization_status status, "
            + "         poha.vendor_id, "
            + "         pola.item_description description, "
            + "         poda.quantity_ordered order_qty, "
            + "         pola.line_num po_linenum, "
            + "         'METITEM1' itemsetid, "
            + "         pola.line_type_id line_type, "
            + "         pola.unit_meas_lookup_code order_unit, "
            + "         pola.unit_price unit_cost, "
            + "         1 conversion, "
            + "         poda.code_combination_id gldebitacct, "
            + "         pola.line_num mt_prline, "
            + "         poha.ship_to_location_id location "                                                
            + "     FROM po_distributions_all poda "
            + "         INNER JOIN po_lines_all pola ON pola.po_line_id = poda.po_line_id "
            + "         INNER JOIN po_headers_all poha ON poha.po_header_id = poda.po_header_id "
            + "     WHERE poda.req_header_reference_num = concat('EP', ?1) and poha.org_id = ?2 "
            + " ) ORDER BY po_linenum " ,
            resultClass = PurchaseOrderDetail.class, resultSetMapping = "PurchaseOrderDetailMap"
    )
})
@SqlResultSetMappings({
    @SqlResultSetMapping(name = "PurchaseOrderDetailMap",
            classes = {
                @ConstructorResult(targetClass = PurchaseOrderDetail.class,
                    columns = {
                      @ColumnResult(name = "po_num", type = String.class),
                      @ColumnResult(name = "currency_code", type = String.class),
                      @ColumnResult(name = "org_id", type = Integer.class),
                      @ColumnResult(name = "revision_num", type = Integer.class),
                      @ColumnResult(name = "status", type = String.class),
                      @ColumnResult(name = "vendor_id", type = Integer.class),
                      @ColumnResult(name = "description", type = String.class),
                      @ColumnResult(name = "order_qty", type = Integer.class),
                      @ColumnResult(name = "po_linenum", type = Integer.class),
                      @ColumnResult(name = "itemsetid", type = String.class),
                      @ColumnResult(name = "line_type", type = Integer.class),
                      @ColumnResult(name = "order_unit", type = String.class),
                      @ColumnResult(name = "unit_cost", type = Double.class),
                      @ColumnResult(name = "conversion", type = Double.class),
                      @ColumnResult(name = "gldebitacct", type = String.class),
                      @ColumnResult(name = "mt_prline", type = Integer.class),
                      @ColumnResult(name = "location", type = String.class)
                    })
            }
    )})
public class PurchaseOrderDetail implements Serializable {
    
    @Id
    @Column(name = "po_num")
    private String ponum;
    
    @Column(name = "currency_code")
    private String currencycode;

    @Column(name = "org_id")
    private Integer orgid;
    
    @Column(name = "revision_num")
    private Integer revisionnum;

    @Column(name = "status")
    private String status;

    @Column(name = "vendor_id")
    private Integer vendorid;

    @Column(name = "description")
    private String description;

    @Column(name = "order_qty")
    private Integer orderqty;

    @Column(name = "po_linenum")
    private Integer polinenum;
    
    @Column(name = "itemsetid")
    private String itemsetid;
    
    @Column(name = "line_type")
    private Integer linetype;
    
    @Column(name = "order_unit")
    private String orderunit;
        
    @Column(name = "unit_cost")
    private Double unitcost;

    @Column(name = "conversion")
    private Double conversion;

    @Column(name = "gldebitacct")
    private String gldebitacct;
    
    @Column(name = "mt_prline")
    private Integer mt_prline;
            
    @Column(name = "location")
    private String location;
    
    public PurchaseOrderDetail() {
        
    }
    
    public PurchaseOrderDetail(String ponum, String currencycode, Integer orgid, Integer revisionnum, 
            String status, Integer vendorid, String description, Integer orderqty, Integer polinenum,
            String itemsetid, Integer linetype, String orderunit, Double unitcost, Double conversion,
            String gldebitacct, Integer mt_prline, String location) {
        this.ponum = ponum;
        this.currencycode = currencycode;
        this.orgid = orgid;
        this.revisionnum = revisionnum;
        this.status = status;
        this.vendorid = vendorid;
        this.description = description;
        this.orderqty = orderqty;
        this.polinenum = polinenum;
        this.itemsetid = itemsetid;
        this.linetype = linetype;
        this.orderunit = orderunit;
        this.unitcost = unitcost;
        this.conversion = conversion;
        this.gldebitacct = gldebitacct;
        this.mt_prline = mt_prline;
        this.location = location;
    }

    public String getPonum() {
        return ponum;
    }

    public void setPonum(String ponum) {
        this.ponum = ponum;
    }

    public String getCurrencycode() {
        return currencycode;
    }

    public void setCurrencycode(String currencycode) {
        this.currencycode = currencycode;
    }

    public Integer getOrgid() {
        return orgid;
    }

    public void setOrgid(Integer orgid) {
        this.orgid = orgid;
    }

    public Integer getRevisionnum() {
        return revisionnum;
    }

    public void setRevisionnum(Integer revisionnum) {
        this.revisionnum = revisionnum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getVendorid() {
        return vendorid;
    }

    public void setVendorid(Integer vendorid) {
        this.vendorid = vendorid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOrderqty() {
        return orderqty;
    }

    public void setOrderqty(Integer orderqty) {
        this.orderqty = orderqty;
    }

    public Integer getPolinenum() {
        return polinenum;
    }

    public void setPolinenum(Integer polinenum) {
        this.polinenum = polinenum;
    }

    public String getItemsetid() {
        return itemsetid;
    }

    public void setItemsetid(String itemsetid) {
        this.itemsetid = itemsetid;
    }

    public Integer getLinetype() {
        return linetype;
    }

    public void setLinetype(Integer linetype) {
        this.linetype = linetype;
    }

    public String getOrderunit() {
        return orderunit;
    }

    public void setOrderunit(String orderunit) {
        this.orderunit = orderunit;
    }

    public Double getUnitcost() {
        return unitcost;
    }

    public void setUnitcost(Double unitcost) {
        this.unitcost = unitcost;
    }

    public Double getConversion() {
        return conversion;
    }

    public void setConversion(Double conversion) {
        this.conversion = conversion;
    }

    public String getGldebitacct() {
        return gldebitacct;
    }

    public void setGldebitacct(String gldebitacct) {
        this.gldebitacct = gldebitacct;
    }

    public Integer getMt_prline() {
        return mt_prline;
    }

    public void setMt_prline(Integer mt_prline) {
        this.mt_prline = mt_prline;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "PurchaseOrderDetail{" + "ponum=" + ponum + ", currencycode=" + currencycode + ", orgid=" + orgid + ", revisionnum=" + revisionnum + ", status=" + status + ", vendorid=" + vendorid + ", description=" + description + ", orderqty=" + orderqty + ", polinenum=" + polinenum + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.ponum);
        hash = 41 * hash + Objects.hashCode(this.currencycode);
        hash = 41 * hash + Objects.hashCode(this.orgid);
        hash = 41 * hash + Objects.hashCode(this.revisionnum);
        hash = 41 * hash + Objects.hashCode(this.status);
        hash = 41 * hash + Objects.hashCode(this.vendorid);
        hash = 41 * hash + Objects.hashCode(this.description);
        hash = 41 * hash + Objects.hashCode(this.orderqty);
        hash = 41 * hash + Objects.hashCode(this.polinenum);
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
        final PurchaseOrderDetail other = (PurchaseOrderDetail) obj;
        if (!Objects.equals(this.currencycode, other.currencycode)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.ponum, other.ponum)) {
            return false;
        }
        if (!Objects.equals(this.orgid, other.orgid)) {
            return false;
        }
        if (!Objects.equals(this.revisionnum, other.revisionnum)) {
            return false;
        }
        if (!Objects.equals(this.vendorid, other.vendorid)) {
            return false;
        }
        if (!Objects.equals(this.orderqty, other.orderqty)) {
            return false;
        }
        return Objects.equals(this.polinenum, other.polinenum);
    }

}
