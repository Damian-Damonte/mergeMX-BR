package com.metalsa.requisicion.pojo;

import com.metalsa.aprobacion.model.Usuario;
import com.metalsa.core.model.NvcTblOaProveedoresH;
import java.util.List;

/**
 *
 * @author APOMR10051
 */
public class RequisicionFadRequest {

    private String idIdioma;
    private List<UenWithDefault> uen;
    private String fechaIni;
    private String fechaFin;
    private String foliosFad;
    /*filtros avanzados*/
    private NvcTblOaProveedoresH proveedor;
    private Integer idRequisicion;
    private Integer idOc;
    private List<Usuario> requisitores;
    private List<Usuario> compradores;
    private List<Usuario> autores;

    public String getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(String idIdioma) {
        this.idIdioma = idIdioma;
    }

    public List<UenWithDefault> getUen() {
        return uen;
    }

    public void setUen(List<UenWithDefault> uen) {
        this.uen = uen;
    }

    public String getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(String fechaIni) {
        this.fechaIni = fechaIni;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getFoliosFad() {
        return foliosFad;
    }

    public void setFoliosFad(String foliosFad) {
        this.foliosFad = foliosFad;
    }

    public NvcTblOaProveedoresH getProveedor() {
        return proveedor;
    }

    public void setProveedor(NvcTblOaProveedoresH proveedor) {
        this.proveedor = proveedor;
    }

    public Integer getIdRequisicion() {
        return idRequisicion;
    }

    public void setIdRequisicion(Integer idRequisicion) {
        this.idRequisicion = idRequisicion;
    }

    public Integer getIdOc() {
        return idOc;
    }

    public void setIdOc(Integer idOc) {
        this.idOc = idOc;
    }

    public List<Usuario> getRequisitores() {
        return requisitores;
    }

    public void setRequisitores(List<Usuario> requisitores) {
        this.requisitores = requisitores;
    }

    public List<Usuario> getCompradores() {
        return compradores;
    }

    public void setCompradores(List<Usuario> compradores) {
        this.compradores = compradores;
    }

    public List<Usuario> getAutores() {
        return autores;
    }

    public void setAutores(List<Usuario> autores) {
        this.autores = autores;
    }

}
