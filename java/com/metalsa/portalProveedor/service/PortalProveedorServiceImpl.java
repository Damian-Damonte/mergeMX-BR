/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.portalProveedor.service;

import com.metalsa.core.model.NvcTblOaProveedoresH;
import com.metalsa.core.repository.ProveedoresRepository;
import com.metalsa.core.utils.UploadFile;
import com.metalsa.finanzas.model.AdjuntoPojo;
import com.metalsa.portalProveedor.model.DcEstatus;
import com.metalsa.portalProveedor.model.UnidadMedida;
import com.metalsa.portalProveedor.model.Moneda;
import com.metalsa.portalProveedor.model.NvcTblCotizaciones;
import com.metalsa.portalProveedor.model.NvcTblDocsCotizacion;
import com.metalsa.portalProveedor.model.NvcTblGastoAdicional;
import com.metalsa.portalProveedor.model.NvcTblReqLineaProv;
import com.metalsa.portalProveedor.pojo.NvcTblDocsCotizacionPojo;
import com.metalsa.portalProveedor.pojo.OaIvaUenPojo;
import com.metalsa.portalProveedor.pojo.PoVendorPojo;
import com.metalsa.portalProveedor.pojo.RfqLineaRequest;
import com.metalsa.portalProveedor.pojo.RfqPojo;
import com.metalsa.portalProveedor.pojo.RfqProveedorRequest;
import com.metalsa.portalProveedor.repository.DcEstatusRepository;
import com.metalsa.portalProveedor.repository.NvcTblDocsCotizacionRepository;
import com.metalsa.portalProveedor.repository.NvcTblGastoAdicionalRepository;
import com.metalsa.portalProveedor.repository.NvcTblReqLineaProvRepository;
import com.metalsa.portalProveedor.repository.PortalProveedorRepository;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mlopez
 */
@Service
@Slf4j
public class PortalProveedorServiceImpl implements PortalProveedorService {
    
    @Value("${ftpPT.host}")
    private String host;
    
    @Value("${ftpPT.port}")
    private Integer port;
    
    @Value("${ftpPT.user}")
    private String user;
    
    @Value("${ftpPT.password}")
    private String pass;
    
    @Autowired
    private ProveedoresRepository proveedoresRepo;
    
    @Autowired
    private PortalProveedorRepository portalProveedorRepository;
    
    @Autowired
    private NvcTblReqLineaProvRepository tblReqLineaProvRepository;
    
    @Autowired
    private NvcTblGastoAdicionalRepository gastoAdicionalRepository;
    
    @Autowired
    private DcEstatusRepository estatusRepository;
    
    @Autowired
    private NvcTblDocsCotizacionRepository docsCotizacionRepository;
    
    @Override
    public List<NvcTblOaProveedoresH> getProveedorByLikeName(String name, BigDecimal idUen) {
        return proveedoresRepo.buscarLikeNombreByUen_("%" + name.toUpperCase() + "%", idUen);
    }
    
    @Override
    public List<RfqPojo> getCotizacionesProveedor(RfqProveedorRequest request) {
        return portalProveedorRepository.getCotizacionesProveedor(request);
    }
    
    @Override
    public List<OaIvaUenPojo> getIvaByUen(Integer idUen) {
        return portalProveedorRepository.getIvaByUen(idUen);
    }
    
    @Override
    public List<Moneda> getMonedasActivas() {
        return portalProveedorRepository.getMonedasActivas();
    }
    
    @Override
    public List<UnidadMedida> getUnidadMedidaByIdioma(String idioma) {
        return portalProveedorRepository.getUnidadMedidaByIdioma(idioma);
    }
    
    @Override
    public List<NvcTblReqLineaProv> getLineaByRfqAndSupplier(RfqLineaRequest request) {
        return tblReqLineaProvRepository.findRquisByRfqAndSupplier(request.getIdRfq(), request.getIdProveedor());
    }
    
    @Override
    public List<NvcTblGastoAdicional> getGastoAdicionalByIdReqLineaProv(Integer idReqLineaProv) {
        return gastoAdicionalRepository.getByIdReqLineaProv(idReqLineaProv);
    }
    
    @Override
    public boolean saveGastoAdicional(List<NvcTblGastoAdicional> gastos) {
        
        try {
            for (NvcTblGastoAdicional gasto : gastos) {
                if (gasto.isEliminar()) {
                    gastoAdicionalRepository.delete(gasto);
                } else {
                    gastoAdicionalRepository.save(gasto);
                }
                
            }
            
            return true;
        } catch (Exception e) {
            System.out.println("------- Error save cotizaciones -------");
            System.out.println(e.getMessage());
            System.out.println(e.getLocalizedMessage());
            System.out.println("----------------------------------------");
        }
        
        return false;
    }
    
    @Override
    public boolean deleteGastoAdicional(NvcTblGastoAdicional gasto) {
        try {
            gastoAdicionalRepository.delete(gasto);
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("------- Error save saveReqLineaProv -------");
            System.out.println(e.getMessage());
        }
        
        return false;
    }
    
    @Override
    public boolean saveReqLineaProv(List<NvcTblReqLineaProv> lineas) {
        try {
            tblReqLineaProvRepository.save(lineas);
            return true;
        } catch (Exception e) {
            log.error("Error", e);
        }
        
        return false;
    }
    
    @Override
    public boolean sendQuaotation(List<NvcTblReqLineaProv> lineas) {
        
        try {
            lineas.forEach(x -> x.setCapturadoPor("S"));
            DcEstatus estatus = estatusRepository.findByDescEstatus("COTIZADA");
            
            lineas.forEach((lineaProv) -> {
                if (lineaProv.getDeclinada().equals("NO")) {
                    lineaProv.setIdEstatus(estatus.getScId().intValue());
                }
            });
            
            tblReqLineaProvRepository.save(lineas);
            
            return true;
            
        } catch (Exception e) {
            System.out.println("------- Error in sendQuaotation -------");
            System.out.println(e.getMessage());
        }
        
        return false;
    }
    
    @Override
    public boolean saveDocCotizacion(List<NvcTblDocsCotizacionPojo> cotizaciones) {
        
        try {
            cotizaciones.forEach((cotizacion) -> {
                //cotizacion.getAdjuntos().forEach((adjunto) -> {
                for (AdjuntoPojo adjunto : cotizacion.getAdjuntos()) {
                    NvcTblDocsCotizacion modeloCot = new NvcTblDocsCotizacion();
                    NvcTblDocsCotizacion modeloCotTem = new NvcTblDocsCotizacion();
                    modeloCot.setIdCotizacion(new NvcTblCotizaciones(cotizacion.getIdCotizacion()));
                    modeloCot.setRutaRaiz(UploadFile.FTP_PATH_ROOT_DCOM);
                    modeloCot.setRuta(UploadFile.FTP_PATH_COT_DCOM);
                    modeloCot.setEnviado("N".charAt(0));
                    modeloCot.setConsecutivo(0);
                    persistDocumento(modeloCot, adjunto);
                    modeloCotTem = docsCotizacionRepository.getByIdDocumento(modeloCot.getIdDocumento());
                    InputStream fis = new ByteArrayInputStream(adjunto.getFile());
                    modeloCotTem.setRuta(UploadFile.FTP_PATH_COT_DCOM + modeloCotTem.getNombre());
                    docsCotizacionRepository.save(modeloCotTem);
                    UploadFile.upload(fis, UploadFile.FTP_PATH_COT_DCOM, modeloCotTem.getNombre(), host, port, user, pass);
                }
                //listaCot.add(modeloCot);
            });

            //docsCotizacionRepository.save(listaCot);
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("------- Error in saveDocCotizacion -------");
            System.out.println(e.getMessage());
        }
        
        return false;
    }
    
    @Override
    public boolean deleteDocCotizacion(NvcTblDocsCotizacion doc) {
        try {
            
            docsCotizacionRepository.delete(doc);
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("------- Error in deleteDocCotizacion -------");
            System.out.println(e.getMessage());
        }
        
        return false;
    }
    
    @Transactional
    public void persistDocumento(NvcTblDocsCotizacion modeloCot, AdjuntoPojo adjunto) {
        BigInteger size = BigInteger.valueOf(adjunto.getPeso());
        modeloCot.setDescripcion(adjunto.getDescription());
        modeloCot.setMime(adjunto.getMime());
        modeloCot.setNombreFormato(adjunto.getNombreArchivo());
        modeloCot.setFecha(new Date());
        modeloCot.setTamano(size);
        docsCotizacionRepository.save(modeloCot);
    }
    
    @Override
    public PoVendorPojo getProveedorByVendorEncoded(String vendorEncoded) {
        return portalProveedorRepository.getProveedorByVendorEncoded(vendorEncoded);
    }
    
    @Override
    public boolean guardarIdCotizacion(String idRfq, String idCotizacion) {
        return portalProveedorRepository.guardarIdCotizacion(idRfq, idCotizacion);
        
    }
    
}
