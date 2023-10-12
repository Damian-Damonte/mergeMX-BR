/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.model;

import java.io.Serializable;
<<<<<<< HEAD
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;

=======
import javax.persistence.*;
>>>>>>> mexico
/**
 *
 * @author juliocisneros
 */
@Entity
@Table(name = "OA_UENS")
//@XmlRootElement //<release_etown_recibos>
<<<<<<< HEAD
=======
@NamedQuery(
        name = "OaUens.findByOrganizationId",
        query = "select o from OaUens o where o.organizationId = :organizationId"
)
>>>>>>> mexico
@NamedNativeQueries({
    @NamedNativeQuery(
            name = "OaUens.findMainValuesById",
            query = "select "
            + "organization_id, organization_name,currency_code "
            + "from oa_uens where organization_id = :id_uen",
            resultSetMapping = "OaUens.mapMailyValues"
    ),
    //    @NamedNativeQuery(name = "UenPorUsuario.uenPorUsuario",
    //            query="select * from uen_por_usuario where id_usuario = ?1",
    //            resultClass = UenPorUsuario.class
    //    ),
    @NamedNativeQuery(name = "OaUens.uensPorRequisicionesUsuario",
            query = "SELECT\n"
            + "    company,\n"
            + "    company_name,\n"
            + "    organization_id,\n"
            + "    organization_name,\n"
            + "    business_group_id,\n"
            + "    currency_code,\n"
            + "    period_set_name,\n"
            + "    location_id,\n"
            + "    location_code,\n"
            + "    location_name\n"
            + "FROM\n"
            + "    oa_uens\n"
            + "WHERE\n"
            + "    organization_id IN (\n"
            + "        SELECT\n"
            + "            u.id_uen\n"
            + "        FROM\n"
            + "            uen_por_usuario u\n"
            + "            JOIN requisicion r ON\n"
            + "                r.id_usuario = u.id_usuario\n"
            + "            AND\n"
            + "                r.id_uen = u.id_uen\n"
            + "            JOIN detalle_de_requisicion d ON\n"
            + "                d.id_estatus = 27\n"
            + "            AND\n"
            + "                d.id_requisicion = r.id_requisicion\n"
            + "        WHERE\n"
            + "            u.id_usuario = ?1\n"
            + "    )",
            resultClass = OaUens.class
    )
    ,
    @NamedNativeQuery(name = "OaUens.uensPorUsuario",
            query = "SELECT\n"
            + "    company,\n"
            + "    company_name,\n"
            + "    organization_id,\n"
            + "    organization_name,\n"
            + "    business_group_id,\n"
            + "    currency_code,\n"
            + "    period_set_name,\n"
            + "    location_id,\n"
            + "    location_code,\n"
            + "    location_name\n"
            + "FROM\n"
            + "    oa_uens\n"
            + "WHERE\n"
            + "    organization_id IN (\n"
            + "        SELECT\n"
            + "            u.id_uen\n"
            + "        FROM\n"
            + "            uen_por_usuario u\n "
            + "         WHERE\n"
            + "            u.id_usuario = ?1\n"
            + "    )",
            resultClass = OaUens.class
    )
    ,
    @NamedNativeQuery(name = "OaUens.getUensActivasPorUsuario",
            query = "SELECT\n"
            + "    company,\n"
            + "    company_name,\n"
            + "    organization_id,\n"
            + "    organization_name,\n"
            + "    business_group_id,\n"
            + "    currency_code,\n"
            + "    period_set_name,\n"
            + "    location_id,\n"
            + "    location_code,\n"
            + "    location_name\n"
            + "FROM NVC_TBL_UENS_ACTIVAS uensactivas\n"
            + "JOIN oa_uens uens\n"
            + "on uens.organization_id = uensactivas.id_uen\n"
            + "INNER JOIN uen_por_usuario uenUsuario\n"
            + "ON uenUsuario.id_uen = uens.organization_id\n"
            + "WHERE 1 = 1\n"
            + "AND uenUsuario.id_usuario = ?1",
            resultClass = OaUens.class
    ), //<R17486>
    //<release_etown_recibos> se comenta este bloque y se cambia la consulta
//      @NamedNativeQuery(name = "OaUens.uensPorUsuarioRecibo",
//            query ="select company, company_name,organization_id,organization_name,\n" +
//"                    business_group_id,currency_code,period_set_name,location_id,location_code,location_name \n" +
//"                    from oa_uens \n" +
//"                    WHERE organization_id in (\n" +
//"                        select id_uen from uen_por_usuario where id_usuario= ?1 \n" +
//"                        and\n" +
//"                            id_uen in (\n" +
//"                                    select id_uen from ( \n" +
//"                                            select dr.id_uen from articulos_recibidos ar  \n" +
//"                                            inner join detalle_de_requisicion dr on ar.id_requisicion = dr.id_requisicion\n" +
//"                                            inner join requisicion r on r.id_requisicion = dr.id_requisicion and r.id_usuario = ?1 \n" +
//"                                            where  dr.estatus in ('APROBADA', 'CERRADA') \n" +
//"                                        UNION ALL \n" +
//"                                            select dr.id_uen from recibos_requi_oracle rro  \n" +
//"                                            inner join detalle_de_requisicion dr on rro.id_requisicion = dr.id_requisicion  \n" +
//"                                            inner join requisicion r on r.id_requisicion = dr.id_requisicion\n" +
//"                                                and r.id_usuario = ?1 \n" +
//"                                            where  rro.id_recibo is not null \n" +
//"                                            and dr.estatus in ('APROBADA', 'CERRADA') \n" +
//"                                            ) v  group by  v.id_uen \n" +
//"                            ) \n" +
//"                    ) " ,
//            resultClass = OaUens.class
//    )   // </R17486>
    
          @NamedNativeQuery(name = "OaUens.uensPorUsuarioRecibo",
            query ="select company, company_name,organization_id,organization_name,\n" +
"                    business_group_id,currency_code,period_set_name,location_id,location_code,location_name \n" +
"                    from oa_uens \n" +
"                    WHERE organization_id in (\n" +
"                        select id_uen from uen_por_usuario where id_usuario= ?1 \n" +
"                        and\n" +
"                            id_uen in (\n" +
"                                    select t1.id_uen from \n" +
"                                            (select r.id_uen, r.id_requisicion from  requisicion r \n" +
"                                                   inner join detalle_de_requisicion dr on r.id_requisicion = dr.id_requisicion \n" +
"                                                   where \n" +
"                                                   r.id_usuario = ?1 \n " +
"                                                   and dr.estatus in ('APROBADA', 'CERRADA') \n" +
"                                                   group by r.id_uen, r.id_requisicion) t1 " +
"                                            where \n" +
"                                            t1.id_requisicion in ( select ar.id_requisicion from articulos_recibidos ar) \n" +
"                                            or t1.id_requisicion in ( select rro.id_requisicion from recibos_requi_oracle rro where rro.id_recibo is not null)  \n" +
"                            ) \n" +
"                    ) " ,
            resultClass = OaUens.class
    )   
//<release_etown_recibos>
// </R17486>
        ,
})

@SqlResultSetMappings({
    @SqlResultSetMapping(
     name = "OaUens.mapMailyValues",
            classes = {
                @ConstructorResult(
                    targetClass = OaUens.class,
                    columns = {
                        @ColumnResult(name = "ORGANIZATION_ID", type = Long.class),
                        @ColumnResult(name = "ORGANIZATION_NAME", type = String.class),
                        @ColumnResult(name = "CURRENCY_CODE", type = String.class)
                    }
                )
            }
    )
})
public class OaUens implements Serializable {


    @Column(name = "COMPANY")
    private String company;
    @Column(name = "COMPANY_NAME")
    private String companyName;
    @Id
    @Column(name = "ORGANIZATION_ID")
    private Long organizationId;
    @Column(name = "ORGANIZATION_NAME")
    private String organizationName;
    @Column(name = "BUSINESS_GROUP_ID")
    private long businessGroupId;

    @Column(name = "CURRENCY_CODE")
    private String currencyCode;
    @Column(name = "PERIOD_SET_NAME")
    private String periodSetName;

    @Column(name = "LOCATION_ID")
    private Long locationId;

    @Column(name = "LOCATION_CODE")
    private String locationCode;

    @Column(name = "LOCATION_NAME")
    private String locationName;
<<<<<<< HEAD
=======
    
    @Column(name = "COMPRADOR_FAMILIA")
    private Integer compradorFamilia;
>>>>>>> mexico

    public OaUens() {
    }

    public OaUens(Long organizationId) {
        this.organizationId = organizationId;
    }

    public OaUens(Long organizationId, String organizationName, String currencyCode) {
        this.organizationId = organizationId;
        this.organizationName = organizationName;
        this.currencyCode = currencyCode;
    }
    
    public OaUens(Long organizationId, String organizationName, long businessGroupId,
            String currencyCode,
            String periodSetName

    ) {
        this.organizationId = organizationId;
        this.organizationName = organizationName;
        this.businessGroupId = businessGroupId;
        this.currencyCode = currencyCode;
        this.periodSetName = periodSetName;

    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public long getBusinessGroupId() {
        return businessGroupId;
    }

    public void setBusinessGroupId(long businessGroupId) {
        this.businessGroupId = businessGroupId;
    }


    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getPeriodSetName() {
        return periodSetName;
    }

    public void setPeriodSetName(String periodSetName) {
        this.periodSetName = periodSetName;
    }


    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (organizationId != null ? organizationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof OaUens)) {
            return false;
        }
        OaUens other = (OaUens) object;
        return this.organizationId.equals(other.organizationId);
    }

    @Override
    public String toString() {
        return "com.metalsa.dcompras.entities.OaUens[ organizationId=" + organizationId + " ]";
    }

<<<<<<< HEAD
=======
    public Integer getCompradorFamilia() {
        return this.compradorFamilia;
    }
    
    public void setCompradorFamilia(Integer comprador) {
        this.compradorFamilia = comprador;
    }
>>>>>>> mexico

}
