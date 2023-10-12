package com.metalsa.requisicion.pojo;

/**
 *
 * @author jose.jimenez07
 */
public class NvcTblCatalogoComboPojo {

    private Long idCombo;
    private Long idVariable;
    private String llave;
    private String valor;

    public NvcTblCatalogoComboPojo() {
    }

    public Long getIdCombo() {
        return idCombo;
    }

    public void setIdCombo(Long idCombo) {
        this.idCombo = idCombo;
    }

    public Long getIdVariable() {
        return idVariable;
    }

    public void setIdVariable(Long idVariable) {
        this.idVariable = idVariable;
    }

    public String getLlave() {
        return llave;
    }

    public void setLlave(String llave) {
        this.llave = llave;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}
