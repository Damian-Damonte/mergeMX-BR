package com.metalsa.requisicion.pojo;

/**
 *
 * @author jose.jimenez07
 */
public class NvcTblCatalogoDescuentoPojo {
    private Long idDescuento;
    private Double descuento;
    private Integer uomDescuento;
    private Double valorInicial;
    private Double valorFinal;
    private String condicion1;
    private String condicion2;
    private Integer orden;
    private String uom;

    public NvcTblCatalogoDescuentoPojo() {
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Integer getUomDescuento() {
        return uomDescuento;
    }

    public void setUomDescuento(Integer uomDescuento) {
        this.uomDescuento = uomDescuento;
    }

    public Double getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(Double valorInicial) {
        this.valorInicial = valorInicial;
    }

    public Double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(Double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public String getCondicion1() {
        return condicion1;
    }

    public void setCondicion1(String condicion1) {
        this.condicion1 = condicion1;
    }

    public String getCondicion2() {
        return condicion2;
    }

    public void setCondicion2(String condicion2) {
        this.condicion2 = condicion2;
    }

    public Long getIdDescuento() {
        return idDescuento;
    }

    public void setIdDescuento(Long idDescuento) {
        this.idDescuento = idDescuento;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

}
