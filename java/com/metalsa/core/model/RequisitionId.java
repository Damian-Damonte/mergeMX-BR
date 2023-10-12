/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author APOJO9952
 */
@Entity
@IdClass(RequisitionId.class)
@XmlRootElement
@NamedNativeQuery(name = "RequisitionId.getRequisitionByIdRequisitor", 
        query = "select DISTINCT r.ID_REQUISICION,   "
        + " (select count(*) TIENECHAT from CHAT_COMPRADOR_VIEW where id_requisicion = r.ID_REQUISICION)  TIENECHAT,"
        + " (select count(*) from DETALLE_DE_REQUISICION where id_requisicion=r.ID_REQUISICION) CANTIDAD_REQUISICIONES,"
        + "  r.id_uen, u.organization_name "                
        + "from detalle_de_requisicion d inner join  requisicion r on d.ID_REQUISICION = r.ID_REQUISICION "
        + " INNER JOIN oa_uens u ON u.organization_id = r.id_uen "
        + "WHERE r.ID_USUARIO = ?1 and d.id_estatus = 27 "//d.estatus = 'SEL COTIZACION'"
        + " and r.id_uen in (select id_uen from uen_por_usuario where id_usuario = ?1)", resultClass = RequisitionId.class, resultSetMapping = "idReqMap")

@SqlResultSetMapping(name = "idReqMap",
        classes = {
            @ConstructorResult(targetClass = RequisitionId.class,
                    columns = {
                        @ColumnResult(name = "ID_REQUISICION", type = Integer.class),
                        @ColumnResult(name="TIENECHAT", type = Integer.class),
                        @ColumnResult(name="CANTIDAD_REQUISICIONES", type = Integer.class),
                        @ColumnResult(name="id_uen", type = Integer.class),
                        @ColumnResult(name="organization_name", type = String.class)
                    }
            )
        }
)
public class RequisitionId implements Serializable {

    @Id
    private Integer requisitionid;
    private Integer TIENECHAT;
    private Integer id_uen;
    private String organization_name;
    private Integer CANTIDAD_REQUISICIONES;
    public Integer getTIENECHAT() {
        return TIENECHAT;
    }

    public void setTIENECHAT(Integer TIENECHAT) {
        this.TIENECHAT = TIENECHAT;
    }
    public RequisitionId(Integer requisitionid) {
        this.requisitionid = requisitionid;
    }

    public Integer getRequisitionid() {
        return requisitionid;
    }

    public void setRequisitionid(Integer requisitionid) {
        this.requisitionid = requisitionid;
    }

    public RequisitionId() {
    }

    public RequisitionId(Integer requisitionid, Integer TIENECHAT,Integer CANTIDAD_REQUISICIONES,
            Integer id_uen, String organization_name) {
        this.requisitionid = requisitionid;
        this.TIENECHAT = TIENECHAT;
        this.CANTIDAD_REQUISICIONES = CANTIDAD_REQUISICIONES;
        this.id_uen = id_uen;
        this.organization_name = organization_name;
    }   

    public Integer getCANTIDAD_REQUISICIONES() {
        return CANTIDAD_REQUISICIONES;
    }

    public void setCANTIDAD_REQUISICIONES(Integer CANTIDAD_REQUISICIONES) {
        this.CANTIDAD_REQUISICIONES = CANTIDAD_REQUISICIONES;
    }
    
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.requisitionid);
        hash = 97 * hash + Objects.hashCode(this.TIENECHAT);
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
        final RequisitionId other = (RequisitionId) obj;
        return Objects.equals(this.requisitionid, other.requisitionid);
    }

    public Integer getId_uen() {
        return id_uen;
    }

    public void setId_uen(Integer id_uen) {
        this.id_uen = id_uen;
    }

    public String getOrganization_name() {
        return organization_name;
    }

    public void setOrganization_name(String organization_name) {
        this.organization_name = organization_name;
    }

}
