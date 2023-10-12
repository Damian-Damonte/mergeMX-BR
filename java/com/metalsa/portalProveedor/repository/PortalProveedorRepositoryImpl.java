/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.portalProveedor.repository;

import com.metalsa.portalProveedor.model.UnidadMedida;
import com.metalsa.portalProveedor.model.Moneda;
import com.metalsa.portalProveedor.pojo.OaIvaUenPojo;
import com.metalsa.portalProveedor.pojo.PoVendorPojo;
import com.metalsa.portalProveedor.pojo.RfqLineaPojo;
import com.metalsa.portalProveedor.pojo.RfqPojo;
import com.metalsa.portalProveedor.pojo.RfqProveedorRequest;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Struct;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.extern.log4j.Log4j;
import org.hibernate.internal.SessionImpl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mlopez
 */
@Log4j
@Repository
public class PortalProveedorRepositoryImpl implements PortalProveedorRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<RfqPojo> getCotizacionesProveedor(RfqProveedorRequest params) {
        List<RfqPojo> rfqListResult = new ArrayList<>();
        CallableStatement cal = null;
        SessionImpl sessionImpl = null;
        try {
            String dateStarStr = null;
            String dateEndStr = null;
            sessionImpl = em.unwrap(SessionImpl.class);

            if ((params.getStartDate() != null && !params.getStartDate().equals("")) && (params.getEndDate() != null && !params.getEndDate().equals(""))) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                SimpleDateFormat sdfNew = new SimpleDateFormat("dd/MM/yyyy");
                Date dateStart = sdf.parse(params.getStartDate());
                Date dateEnd = sdf.parse(params.getEndDate());

                dateStarStr = sdfNew.format(dateStart);
                dateEndStr = sdfNew.format(dateEnd);

                System.out.println("START_DATE: " + dateStarStr);
                System.out.println("END_DATE: " + dateEndStr);
            }
            cal = sessionImpl.connection().prepareCall("{call get_rfq_captura_cotizacion_pro(?,?,?,?,?)}");
            cal.setObject(1, params.getIdProveedor());
            cal.setString(2, params.getIdioma());
            cal.setString(3, dateStarStr);
            cal.setString(4, dateEndStr);
            cal.registerOutParameter(5, Types.ARRAY, "RFQ_OBJECT_ARRAY");
            cal.execute();

            Array arrayUen = cal.getArray(5);

            Object[] rfqList = (Object[]) arrayUen.getArray();

            for (int ii = 0; ii < rfqList.length; ii++) {

                RfqPojo rfqObj = new RfqPojo();

                Struct rfqsStructure = (Struct) rfqList[ii];

                if (rfqsStructure != null) {

                    Object[] rfqsObjects = rfqsStructure.getAttributes();

                    if (rfqsObjects != null) {

                        rfqObj.setIdRfq((BigDecimal) rfqsObjects[0]);
                        rfqObj.setFechaCreacion((String) rfqsObjects[1]);
                        rfqObj.setInicioVigencia((String) rfqsObjects[2]);
                        rfqObj.setFinVigencia((String) rfqsObjects[3]);
                        rfqObj.setVencido((String) rfqsObjects[4]);
                        rfqObj.setShipTo((String) rfqsObjects[5]);
                        rfqObj.setBillTo((String) rfqsObjects[6]);
                        rfqObj.setIdIncoterm((BigDecimal) rfqsObjects[7]);
                        rfqObj.setIncotermDesc((String) rfqsObjects[8]);
                        rfqObj.setIdUen((BigDecimal) rfqsObjects[9]);
                        rfqObj.setNombreUen((String) rfqsObjects[10]);

                        Array requisiciones = (Array) rfqsObjects[11];
                        rfqObj.setIdCotizacionProveedor((String) rfqsObjects[12]);
                        Object[] requisicionesList = (Object[]) requisiciones.getArray();

                        List<RfqLineaPojo> requisList = new ArrayList<>();

                        for (int r = 0; r < requisicionesList.length; r++) {

                            Struct requiStructure = (Struct) requisicionesList[r];
                            Object[] requisObjects = requiStructure.getAttributes();
                            RfqLineaPojo requiObj = new RfqLineaPojo();
                            requiObj.setIdRequisicion((BigDecimal) requisObjects[0]);
                            requiObj.setIdPartida((BigDecimal) requisObjects[1]);
                            requiObj.setDescripcion((String) requisObjects[2]);
                            requiObj.setCantidad((BigDecimal) requisObjects[3]);
                            requiObj.setUdm((String) requisObjects[4]);
                            requiObj.setNumAttachments((BigDecimal) requisObjects[5]);
                            requiObj.setNombreComprador((String) requisObjects[6]);
                            requiObj.setCountMessages((BigDecimal) requisObjects[7]);
                            requiObj.setComentarios((String) requisObjects[8]);
                            requiObj.setFechaRequerida((String) requisObjects[9]);
                            requisList.add(requiObj);
                        }

                        rfqObj.setRequisiciones(requisList);
                        rfqListResult.add(rfqObj);
                    }

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in getCotizacionesProveedor" + e.getMessage());
        } finally {
            try {
                if (cal != null) {
                    cal.close();
                }
                if (sessionImpl != null) {
                    sessionImpl.close();
                }
            } catch (Exception e) {

                e.printStackTrace();
                System.out.println("Error in getCotizacionesProveedor" + e.getMessage());
            }
        }

        return rfqListResult;

    }

    @Override
    public List<OaIvaUenPojo> getIvaByUen(Integer idUen) {

        List<OaIvaUenPojo> ivaResult = new ArrayList<>();
        List<String> result;
        String nativeQuery = "SELECT IVA FROM NVC_VM_OA_IVA WHERE ID_UEN = ?1  and (INACTIVE_DATE>sysdate or INACTIVE_DATE is null) ORDER BY IVA";

        result = em.createNativeQuery(nativeQuery)
                .setParameter(1, idUen)
                .getResultList();

        for (String iva : result) {
            OaIvaUenPojo ivaPojo = new OaIvaUenPojo();
            ivaPojo.setId(iva);
            ivaPojo.setIvaName(iva);
            ivaResult.add(ivaPojo);
        }

        return ivaResult;
    }

    @Override
    public List<Moneda> getMonedasActivas() {
        try {
            List<Moneda> resultQuery;

            String nativeQuery = "SELECT id_moneda, moneda FROM NVC_TBL_MONEDAS_H  WHERE active = 1 AND id_moneda not in('MXN') order by id_Moneda asc";

            resultQuery = em.createNativeQuery(nativeQuery, Moneda.class).getResultList();

            return resultQuery;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("------ Error in getMonedasActivas PortalProveedor ------");
        }
        return new ArrayList<>();
    }

    @Override
    public List<UnidadMedida> getUnidadMedidaByIdioma(String idioma) {

        try {
            List<UnidadMedida> resultQuery;

            String nativeQuery = "SELECT id_unidad_de_medida id, unidad_de_medida medida from NVC_VM_OA_UNIDADES_DE_MEDIDA WHERE lenguaje = ?1";

            resultQuery = em.createNativeQuery(nativeQuery, UnidadMedida.class)
                    .setParameter(1, idioma)
                    .getResultList();

            return resultQuery;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("------ Error in getUnidadMedidaByIdioma PortalProveedor ------");
        }
        return new ArrayList<>();
    }

    @Override
    public PoVendorPojo getProveedorByVendorEncoded(String vendorEncoded) {
        PoVendorPojo poVendorPojo = new PoVendorPojo();
        if (vendorEncoded != null) {
            String nativeQuery = "SELECT vendor_id, vendor_name FROM po_vendors_s WHERE vendor_id  = portal_prov_decodemm_number(?1)";

            List<PoVendorPojo> result = em.createNativeQuery(nativeQuery, PoVendorPojo.class)
                    .setParameter(1, vendorEncoded)
                    .getResultList();

            if (result != null && !result.isEmpty()) {
                poVendorPojo = result.get(0);
            }
        }
        return poVendorPojo;
    }

    @Transactional
    @Override
    public boolean guardarIdCotizacion(String idRfq, String idCotizacion) {
        try {
            em.createNativeQuery("UPDATE nvc_tbl_rfq_prov SET id_cotizacion_proveedor =?1 where id_rfq =?2")
                    .setParameter(1, idCotizacion)
                    .setParameter(2, idRfq).executeUpdate();
            return true;
        } catch (Exception e) {
            log.error(e);
            return false;
        }
    }
}
