package com.metalsa.catalogo.controller;

import com.metalsa.catalogo.model.NvcTblCatalogo;
import com.metalsa.catalogo.model.NvcTblCatalogoItem;
import com.metalsa.catalogo.model.NvcTblCatalogoUen;
import com.metalsa.catalogo.pojo.CatalogoFiltro;
import com.metalsa.catalogo.pojo.CatalogoRequest;
import com.metalsa.catalogo.pojo.CatalogoResponse;
import com.metalsa.catalogo.pojo.Filtros;
import com.metalsa.catalogo.repository.CatalogoItemRepository;
import com.metalsa.catalogo.repository.CatalogoRepository;
import com.metalsa.catalogo.repository.CatalogoUenRepository;
import com.metalsa.catalogo.repository.IvaRepository;
import com.metalsa.catalogo.repository.LocalizacionesRepository;
import com.metalsa.catalogo.service.CatalogoService;
import com.metalsa.core.model.NvcTblUenUsuAprobacion;
import com.metalsa.core.repository.UenUsuAprobacionRepository;
import com.metalsa.portalProveedor.model.DcEstatus;
import com.metalsa.portalProveedor.repository.DcEstatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j;
import com.metalsa.utils.Constants;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author miguel.rdz
 *
 */
@RestController
@Api(value = "Catalogo", tags = {"Catalogo Service API"})
@RequestMapping(Constants.URI_API_CATALOGOS)
@Log4j
public class CatalogoController {

    @Autowired
    private CatalogoRepository catalogosRep;
    @Autowired
    private CatalogoUenRepository catalogoUenRep;
    @Autowired
    private CatalogoItemRepository catalogoItemRep;
    @Autowired
    private IvaRepository ivaRep;
    @Autowired
    private LocalizacionesRepository locRep;
    @Autowired
    private DcEstatusRepository estatusRepository;
    @Autowired
    private UenUsuAprobacionRepository uenUsuAprobRepository;
    @Autowired
    private CatalogoService catalogoService;

    @Qualifier("application")
    @Autowired
    private MessageSource messages;

    @RequestMapping(value = "/getCatalogo/{idCatalogo}/{idCatalogoUen}", method = RequestMethod.GET)
    @ResponseBody
    public CatalogoResponse getCatalogo(@PathVariable("idCatalogo") Integer idCatalogo, @PathVariable("idCatalogoUen") Integer idCatalogoUen) {
        CatalogoResponse response = new CatalogoResponse();
        try {
            if (idCatalogo != null) {
                response.setCatalogo(catalogosRep.getByIdCatalogo(idCatalogo));
                List<NvcTblCatalogoUen> catalogosUen = catalogoUenRep.getByIdCatalogo(idCatalogo);
                response.setCatalogosUen(catalogosUen);
                NvcTblCatalogoUen catalogoUen;
                if (idCatalogoUen != 0) {
                    catalogoUen = catalogoUenRep.getByIdCatalogoUen(idCatalogoUen);

                } else {
                    catalogoUen = catalogosUen.stream().findFirst().get();
                }
                catalogoUen.setIvaList(ivaRep.getIvasByIdUen(catalogoUen.getIdUen()));
                catalogoUen.setBillToList(locRep.getBillToByIdUen(catalogoUen.getIdUen()));
                try {
                    List<NvcTblUenUsuAprobacion> usuAp = uenUsuAprobRepository.getByUenProceso(catalogoUen.getIdUen(), 3, 0);
                    if (!usuAp.isEmpty()) {
                        catalogoUen.setAprobadorCatalogo(usuAp.stream().findFirst().get().getNombreUsuario());
                    }
                } catch (Exception e) {
                    catalogoUen.setAprobadorCatalogo("N/D");
                }
                response.setCatalogoUen(catalogoUen);
                return response;
            }
        } catch (Exception e) {
            log.error("Error al obtener getCatalogo:" + e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/getCatalogoUen/{idCatalogoUen}", method = RequestMethod.GET)
    @ResponseBody
    public CatalogoResponse getCatalogoUen(@PathVariable("idCatalogoUen") Integer idCatalogoUen) {
        CatalogoResponse response = new CatalogoResponse();
        try {
            if (idCatalogoUen != null) {
                NvcTblCatalogoUen catalogoUen = catalogoUenRep.getByIdCatalogoUen(idCatalogoUen);
                catalogoUen.setIvaList(ivaRep.getIvasByIdUen(catalogoUen.getIdUen()));
                catalogoUen.setBillToList(locRep.getBillToByIdUen(catalogoUen.getIdUen()));
                try {
                    List<NvcTblUenUsuAprobacion> usuAp = uenUsuAprobRepository.getByUenProceso(catalogoUen.getIdUen(), 3, 0);
                    if (!usuAp.isEmpty()) {
                        catalogoUen.setAprobadorCatalogo(usuAp.stream().findFirst().get().getNombreUsuario());
                    }
                } catch (Exception e) {
                    catalogoUen.setAprobadorCatalogo("N/D");
                }
                response.setCatalogoUen(catalogoUen);
                return response;
            }
        } catch (Exception e) {
            log.error("Error al obtener getCatalogoUen:" + e.getMessage());
        }
        return response;
    }

    @GetMapping("/getItemsByCatUen/{idCatalogoUen}")
    Page<NvcTblCatalogoItem> getItemsByCatUen(@PathVariable("idCatalogoUen") String idCatalogoUen,
            @RequestParam("idioma") String idioma,
            @PageableDefault Pageable page) {
        return catalogoItemRep.getByIdCatalogoUen(idCatalogoUen, idioma, page);
    }

    @RequestMapping(value = "getItemsByCatUenFiltroItem/{idCatalogoUen}", method = RequestMethod.POST)
    @ResponseBody
    public Page<NvcTblCatalogoItem> getItemsByCatUenFiltroItem(@PathVariable("idCatalogoUen") String idCatalogoUen,
            @RequestParam("idioma") String idioma,
            @PageableDefault Pageable page, @RequestBody Filtros req) {
        try {
            if (req != null) {
                List<String> items = new ArrayList();
                List<String> estatus = new ArrayList();
                List<String> publicado = new ArrayList();
                req.getItems().forEach((ci) -> {
                    items.add(ci.getIntKey().toString());
                });
                req.getEstatus().forEach((ci) -> {
                    if (ci.getIntKey() == 1) {
                        estatus.add(ci.getStrKey());
                        publicado.add(ci.getStrKey());
                    } else {
                        estatus.add(ci.getIntKey().toString());
                    }
                });
                if (items.isEmpty() && estatus.isEmpty() && publicado.isEmpty()) {
                    return catalogoItemRep.getByIdCatalogoUen(idCatalogoUen, idioma, page);
                } else {
                    if (items.isEmpty()) {
                        items.add("0");
                    }
                    if (estatus.isEmpty()) {
                        estatus.add("0");
                    }
                    if (publicado.isEmpty()) {
                        publicado.add("0");
                    }
                    return catalogoItemRep.getByIdCatalogoUenFiltroItem(idCatalogoUen, idioma, items, estatus, publicado, page);
                }
            } else {
                return catalogoItemRep.getByIdCatalogoUen(idCatalogoUen, idioma, page);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    private List<NvcTblCatalogoItem> getItemsByCatUenFiltro(String idCatalogoUen, String idioma, Filtros req) {
        try {
            if (req != null) {
                List<String> items = new ArrayList();
                List<String> estatus = new ArrayList();
                List<String> publicado = new ArrayList();
                req.getItems().forEach((ci) -> {
                    items.add(ci.getIntKey().toString());
                });
                req.getEstatus().forEach((ci) -> {
                    if (ci.getIntKey() == 1) {
                        publicado.add(ci.getStrKey());
                    }
                    estatus.add(ci.getIntKey().toString());
                });
                if (items.isEmpty()) {
                    items.add("0");
                }
                if (estatus.isEmpty()) {
                    estatus.add("0");
                }
                if (publicado.isEmpty()) {
                    publicado.add("0");
                }
                return catalogoItemRep.getAllByIdCatalogoUenFiltro(idCatalogoUen, idioma, items, estatus, publicado);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @RequestMapping(value = "/getItemEstatus/{idIdioma}", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<CatalogoFiltro> getItemEstatus(@PathVariable("idIdioma") String idIdioma) {
        return catalogoService.getItemEstatus(idIdioma);
    }

    @RequestMapping(value = "saveCatalogo", method = RequestMethod.POST)
    @ResponseBody
    public CatalogoResponse saveCatalogo(@RequestBody CatalogoRequest req) {
        CatalogoResponse res = new CatalogoResponse();
        try {
            if (req != null) {
                //Actualizando Catalogo
                NvcTblCatalogo catalogoToSave = catalogosRep.findByIdCatalogo(req.getCatalogo().getIdCatalogo());
                catalogoToSave.setFechaInicioVigencia(req.getCatalogo().getFechaInicioVigencia());
                catalogoToSave.setFechaFinVigencia(req.getCatalogo().getFechaFinVigencia());
                catalogoToSave.setTipoAvisoTerminacion(req.getCatalogo().getTipoAvisoTerminacion());
                catalogoToSave.setFechaActualizacion(new Date());
                catalogoToSave.setUsuarioActualizacion(req.getIdUsuario());
                catalogosRep.save(catalogoToSave);
                //Actualizando Catalogo uen
                NvcTblCatalogoUen catalogoUenToSave = catalogoUenRep.findByIdCatalogoUen(req.getCatalogoUen().getIdCatalogoUen());
                catalogoUenToSave.setIdIva(req.getCatalogoUen().getIdIva());
                catalogoUenToSave.setIdFacturacion(req.getCatalogoUen().getIdFacturacion());
                catalogoUenToSave.setTipoRecibo(req.getCatalogoUen().getTipoRecibo());
                catalogoUenToSave.setFechaActualizacion(new Date());
                catalogoUenToSave.setUsuarioActualizacion(req.getIdUsuario());
                catalogoUenRep.save(catalogoUenToSave);
                res.setOutMessage("OK");
            }
        } catch (Exception e) {
            log.error("Error en saveCatalogo:" + e.getMessage());
            res.setOutMessage(e.getMessage());
        }
        return res;
    }

    @RequestMapping(value = "activateItems", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public CatalogoResponse activateItems(@RequestBody CatalogoRequest req) {
        CatalogoResponse res = new CatalogoResponse();
        try {
            if (req != null) {
                if (isCatalogoVigente(req.getCatalogo())) {
                    DcEstatus nuevoEstatus;
                    DcEstatus viejoEestatus;
                    if (req.getActiveVal() == 1) {
                        nuevoEstatus = estatusRepository.findByDescEstatus("ACTIVA");
                        viejoEestatus = estatusRepository.findByDescEstatus("INACTIVA");
                    } else {
                        nuevoEstatus = estatusRepository.findByDescEstatus("INACTIVA");
                        viejoEestatus = estatusRepository.findByDescEstatus("ACTIVA");
                    }
                    DcEstatus aprobada = estatusRepository.findByDescEstatus("APROBADA");
                    DcEstatus enEdicion = estatusRepository.findByDescEstatus("EN EDICION");
                    DcEstatus porAprobar = estatusRepository.findByDescEstatus("POR APROBAR");
                    if (req.getItems().size() > 0) {
                        //Actualiza solo los items marcados
                        req.getItems().forEach((i) -> {
                            NvcTblCatalogoItem ent = catalogoItemRep.findByIdItem(i.getIdItem());
                            if (ent.getIdEstatus() == aprobada.getScId().intValue()) {
                                ent.setItemPublicado(nuevoEstatus.getScId().intValue());
                            } else if (ent.getIdEstatus() == enEdicion.getScId().intValue()) {
                                ent.setIdEstatus(porAprobar.getScId().intValue());
                                ent.setNumAprobaciones(ent.getNumAprobaciones() == null ? 1 : ent.getNumAprobaciones() + 1);
                            }
                            ent.setFechaActualizacion(new Date());
                            ent.setUsuarioActualizacion(req.getIdUsuario());
                            catalogoItemRep.save(ent);
                        });
                        res.setOutMessage("OK");
                    } else {
                        if (!req.getFiltros().getItems().isEmpty() || !req.getFiltros().getEstatus().isEmpty()) {
                            List<NvcTblCatalogoItem> items = getItemsByCatUenFiltro(req.getIdCatalogoUen().toString(), req.getFiltros().getIdIdioma(), req.getFiltros());
                            items.forEach((i) -> {
                                NvcTblCatalogoItem ent = catalogoItemRep.findByIdItem(i.getIdItem());
                                if (ent.getIdEstatus() == aprobada.getScId().intValue()) {
                                    ent.setItemPublicado(nuevoEstatus.getScId().intValue());
                                } else if (ent.getIdEstatus() == enEdicion.getScId().intValue()) {
                                    ent.setIdEstatus(porAprobar.getScId().intValue());
                                    ent.setNumAprobaciones(ent.getNumAprobaciones() == null ? 1 : ent.getNumAprobaciones() + 1);
                                }
                                ent.setFechaActualizacion(new Date());
                                ent.setUsuarioActualizacion(req.getIdUsuario());
                                catalogoItemRep.save(ent);
                            });
                        } else {
                            //Actualiza todos los items del catalogo
                            catalogoItemRep.activaItemsAprobados(nuevoEstatus.getScId().intValue(), req.getIdUsuario(), req.getIdCatalogoUen(), viejoEestatus.getScId().intValue());
                            catalogoItemRep.activaItemsEnEdicion(porAprobar.getScId().intValue(), req.getIdUsuario(), req.getIdCatalogoUen());
                        }
                        res.setOutMessage("OK");
                    }
                } else {
                    res.setOutMessage(messages.getMessage("cat.sin_vigencia", null, new Locale(req.getIdioma())));
                }
            }
        } catch (NoSuchMessageException e) {
            res.setOutMessage(e.getMessage());
        }
        return res;
    }

    private boolean isCatalogoVigente(NvcTblCatalogo catalogo) {
        try {
            Date fechaFinDb = catalogo.getFechaFinVigencia() == null ? new Date() : catalogo.getFechaFinVigencia();
            Calendar calFechaFinDb = GregorianCalendar.getInstance();
            calFechaFinDb.setTime(fechaFinDb);
            calFechaFinDb.add(Calendar.DATE, 1);
            calFechaFinDb.set(Calendar.HOUR_OF_DAY, 0);
            calFechaFinDb.set(Calendar.MINUTE, 0);
            calFechaFinDb.set(Calendar.SECOND, 0);
            calFechaFinDb.set(Calendar.MILLISECOND, 0);
            Calendar calFechaActual = GregorianCalendar.getInstance();
            calFechaActual.setTime(new Date());
            calFechaActual.set(Calendar.HOUR_OF_DAY, 0);
            calFechaActual.set(Calendar.MINUTE, 0);
            calFechaActual.set(Calendar.SECOND, 0);
            calFechaActual.set(Calendar.MILLISECOND, 0);
            return calFechaFinDb.getTime().after(calFechaActual.getTime()) || calFechaFinDb.getTime().equals(calFechaActual.getTime());
        } catch (Exception e) {
            log.error(e);
        }
        return false;
    }

    @GetMapping("/getFiltroDescripcion/{idCatalogoUen}")
    public Iterable<CatalogoFiltro> getFiltroDescripcion(@PathVariable("idCatalogoUen") Integer idCatalogoUen, @RequestParam("query") String query) {
        List<CatalogoFiltro> res = new ArrayList<>();
        try {
            if (idCatalogoUen != null) {
                if (query != null) {
                    catalogoItemRep.findByIdCatUenQuery(idCatalogoUen, "%" + query.toUpperCase() + "%").stream().map((catItem) -> {
                        CatalogoFiltro filtro = new CatalogoFiltro();
                        filtro.setIntKey(catItem.getIdItem());
                        filtro.setStrKey(catItem.getCodigoItem());
                        filtro.setDescripcion(catItem.getCodigoItem() + " - " + catItem.getDescripcion());
                        return filtro;
                    }).forEachOrdered((filtro) -> {
                        res.add(filtro);
                    });
                } else {
                    catalogoItemRep.findByIdCatUen(idCatalogoUen).stream().map((catItem) -> {
                        CatalogoFiltro filtro = new CatalogoFiltro();
                        filtro.setIntKey(catItem.getIdItem());
                        filtro.setStrKey(catItem.getCodigoItem());
                        filtro.setDescripcion(catItem.getDescripcion());
                        return filtro;
                    }).forEachOrdered((filtro) -> {
                        res.add(filtro);
                    });
                }
            }
        } catch (Exception e) {
            log.error("Error al obtener getFiltroDescripcion:" + e.getMessage());
        }
        return res;
    }
}
