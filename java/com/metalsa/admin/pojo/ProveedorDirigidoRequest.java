package com.metalsa.admin.pojo;

import com.metalsa.admin.model.ProveedorDirigido;
import com.metalsa.finanzas.model.AdjuntoPojo;
import java.util.List;

public class ProveedorDirigidoRequest {

    private ProveedorDirigido proveedor;
    private String idUsuario;
    private List<AdjuntoPojo> dataFile;

    public ProveedorDirigido getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedorDirigido proveedor) {
        this.proveedor = proveedor;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<AdjuntoPojo> getDataFile() {
        return dataFile;
    }

    public void setDataFile(List<AdjuntoPojo> dataFile) {
        this.dataFile = dataFile;
    }


    
    

}
