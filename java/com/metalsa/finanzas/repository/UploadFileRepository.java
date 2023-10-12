package com.metalsa.finanzas.repository;

import com.metalsa.finanzas.model.AdjuntoPojo;
import com.metalsa.finanzas.model.AdjuntoSolicitud;
import java.io.InputStream;
import java.util.List;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author JL
 */
@Repository
public interface UploadFileRepository  extends JpaRepository<AdjuntoSolicitud, Long>{
    
    
    
    boolean connect(FTPClient client, String host,Integer port,String user,String pass);
    
    boolean upload(InputStream file, String path, String fileName,String host,Integer port,String user,String pass);
    
   
    /**
     * Crea un arbol de direcciones en servidor ftp remoto
     *
     * @param client
     * @param dirTree Directorio en folders delimitados por '/'
     * @throws java.io.IOException
     */
    void createDirectoryTreeFTP(FTPClient client, String dirTree);
    
    /**
     * Obtiene lista de archivos ftp comprimidos en zip.
     *
     * @param list
     * @param fileName
     * @return files
     */
    AdjuntoPojo zipFiles(List<AdjuntoPojo> list, String fileName);
    
    
    AdjuntoPojo zipFiles(String ftpName, String fileName, String remotePath, boolean notFoundImage, List<String> names,String host,Integer port,String user,String pass);
    
    
    List<AdjuntoPojo> getFtpFile(FTPClient ftp, String remotePath);
   
    
}
