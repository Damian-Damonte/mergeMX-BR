package com.metalsa.contralor.model;

import com.metalsa.finanzas.model.*;
import lombok.Data;


/**
 *
 * @author JL
 */
@Data
public class AdjuntoPojoP {

    private String nombreArchivo;
    private Long peso;
    private String mime;
    private byte[] file;
    private int uen;


    public byte[] getFile() {
        return file == null ? null : (byte[]) file.clone();
    }
    
    public void setFile(byte[] file) {
        if(null == file){
            this.file = null;
        }else{
            this.file = (byte[]) file.clone();
        }
    }
    
}
