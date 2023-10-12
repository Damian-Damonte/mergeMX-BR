package com.metalsa.finanzas.repository;

import com.metalsa.finanzas.model.AdjuntoPojo;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import lombok.extern.log4j.Log4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author JL
 */
@Log4j
public class UploadFileRepositoryImpl {
    public static final String FTP_PATH_DEFAULT = "/dev/2303/bdg_increase/";
    
    
    
    public boolean connect(FTPClient client, String host,Integer port,String user,String pass){
        boolean success = false;
        log.debug("host: " + host);
        try {
            client.connect(host, port);
            log.debug("conectando........");
            success = client.login(user, pass);
        } catch (IOException ex) {
            log.debug(ex.getMessage());
        }
        return success; 
    }
    
    public boolean upload(InputStream file, String path, String fileName,String host,Integer port,String user,String pass){
        log.debug("inicianado........");
        FTPClient client = new FTPClient();
        boolean respuesta = false;

        try {
            boolean success = connect(client, host, port, user, pass);

            if(success){
                log.debug("logueado........");
            }
            client.setFileType(FTPClient.BINARY_FILE_TYPE);
            client.enterLocalPassiveMode(); 
            
            String remoto = path + fileName;

            //
            // Store file to server
            //
            log.debug("remoto:" + remoto);
            log.debug("fileName:" + fileName);
            log.debug("file:" + file);
            
            if (!client.changeWorkingDirectory(path)) {
                            log.info("No existe directorio, creando " + path);
                            createDirectoryTreeFTP(client, path);
                        }
            
            respuesta = client.storeFile(fileName, file);
             if(respuesta){
                log.debug("guardado........" + path);
             }
            client.logout();
            log.debug("desloguenado........");
        } catch (IOException e) {
            log.debug(e);
        } finally {
            try {
                if (file != null) {
                    file.close();
                }
                client.disconnect();
                log.debug("desconectando........");
            } catch (IOException e) {
                log.debug(e);
            }
        }
        log.debug("respuesta: " + respuesta);
        return respuesta;
    }
    
   
    /**
     * Crea un arbol de direcciones en servidor ftp remoto
     *
     * @param client
     * @param dirTree Directorio en folders delimitados por '/'
     * @throws java.io.IOException
     */
    public void createDirectoryTreeFTP(FTPClient client, String dirTree) throws IOException {
        log.debug("*** createDirectoryTreeFTP ***");
        boolean dirExists = true;
        String[] directories = dirTree.split("/");
        for (String dir : directories) {
            if (!dir.isEmpty()) {
                if (dirExists) {
                    dirExists = client.changeWorkingDirectory(dir);
                }
                if (!dirExists) {
                    if (!client.makeDirectory(dir)) {
                        throw new IOException("Unable to create remote directory '" + dir + "'.  error=" + client.getReplyString() + "'");
                    }
                    if (!client.changeWorkingDirectory(dir)) {
                        throw new IOException("Unable to change into newly created remote directory '" + dir + "'.  error='" + client.getReplyString() + "'");
                    }
                }
            }
        }
    }
    
    /**
     * Obtiene lista de archivos ftp comprimidos en zip.
     *
     * @param list
     * @param fileName
     * @return files
     */
    public AdjuntoPojo zipFiles(List<AdjuntoPojo> list, String fileName) {
        AdjuntoPojo zipFile = new AdjuntoPojo();
        byte[] buffer = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ZipOutputStream zos = new ZipOutputStream(baos);
            ZipEntry ze;
            for (AdjuntoPojo doc : list) {
                ze = new ZipEntry(doc.getNombreArchivo());
                zos.putNextEntry(ze);
                InputStream in = new ByteArrayInputStream(doc.getFile());
                int len;
                while ((len = in.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                zos.closeEntry();
                in.close();
            }
            zos.flush();
            zos.close();
            baos.flush();
            baos.close();
            zipFile.setNombreArchivo(fileName + ".zip");
            zipFile.setMime("application/zip");
            zipFile.setFile(baos.toByteArray());
            zipFile.setPeso(Long.valueOf(baos.size()));
        } catch (IOException ex) {
            log.debug("No se ha podido generar el Zip " + ex);
        }
        return zipFile;
    }
    
    
    public AdjuntoPojo zipFiles(String ftpName, String fileName, String remotePath, boolean notFoundImage, List<String> names,String host,Integer port,String user,String pass){
    
        
        AdjuntoPojo file = null;
        try {
            
            String[] paths = remotePath.split("/");

            if (paths.length > 0) {

                FTPClient ftp = new FTPClient();
                boolean success = connect(ftp, host, port, user, pass);

                if(success){
                    log.debug("logueado........");
                }
                ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftp.enterLocalPassiveMode();
                String rootDir = ftp.printWorkingDirectory();
                log.debug("rootDir:" + rootDir);
                log.debug("ftpName=" + ftpName);
                //Descarga archivo especifico
                if (!ftpName.equals("")) {
                    log.debug("1");
                    try {
                        if (ftp.changeWorkingDirectory(rootDir + remotePath)) {
                            log.debug("2");
                            ByteArrayOutputStream os = new ByteArrayOutputStream();
                            if (ftp.retrieveFile(ftpName, os)) {
                                byte[] fileContent = os.toByteArray();
                                FileNameMap fileNameMap = URLConnection.getFileNameMap();
                                String contentType = fileNameMap.getContentTypeFor(ftp.printWorkingDirectory() + "/" + ftpName);
                                file = new AdjuntoPojo();
                                file.setNombreArchivo(fileName);
                                file.setPeso(Long.valueOf(os.size()));
                                file.setFile(fileContent);
                                file.setMime(contentType);
                            } /*else if (notFoundImage) {
                                log.error("Archivo " + ftpName + " no encontrado.");
                                ftp.changeWorkingDirectory(rootDir + rProp.getProperty("iis.env") + rProp.getProperty("ftpcat.appimg"));
                                if (ftp.retrieveFile("notfound-big.png", os)) {
                                    byte[] fileContent = os.toByteArray();
                                    file = new AdjuntoPojo(ftpName, os.size(), fileContent);
                                }
                            }*/
                        } else {
                            log.info("No existe directorio " + ftp.printWorkingDirectory() + remotePath);
                        }
                    } catch (Exception ex) {
                        log.error("ocurrió un error al cargar los archivos: ", ex);
                    }
                } else { //Descarga conjunto de archivos .zip
                    log.debug("3");
                    List<AdjuntoPojo> files = new ArrayList<>();
                    
                    if (names != null && !names.isEmpty()) {
                        log.debug("4");
                        for (String f : names) {
                            log.debug("5");

                                files.addAll(getFtpFile(ftp, rootDir + remotePath + "/" + f));

                        }
                    } else {
                            log.debug("6");
                            files.addAll(getFtpFile(ftp, rootDir + remotePath));

                    }
                    file = zipFiles(files, fileName);
                }
                

                
            }
        } catch (IOException e) {
            log.error("No se pudo recuperar la imagen");
        }
        
        return file;
}
    
    
    public List<AdjuntoPojo> getFtpFile(FTPClient ftp, String remotePath) {
        log.debug("remotePath:" + remotePath);
        List<AdjuntoPojo> files = new ArrayList<>();
        remotePath = remotePath.replaceAll("/+", "/");
        try {
            if (ftp.changeWorkingDirectory(remotePath)) {
                log.debug("7");
                log.debug("directorio de trabajo:" + ftp.printWorkingDirectory());
                FTPFile[] ftpFiles = ftp.listFiles(ftp.printWorkingDirectory());
                if (ftpFiles.length > 0) {
                    log.debug("8");
                    
                    for (FTPFile f : ftpFiles) {
                        log.debug("9");
                        log.debug("archivo " + f.getName());
                        String ext = f.getName().substring(f.getName().lastIndexOf(".") + 1);
                        String prefix = f.getName().split("\\.")[0];
                        log.debug("prefix:" + prefix);
                        if (!f.isFile() || ext.equals("tmp")) {
                            log.debug("continue");
                            continue;
                        }
                        
                        if (prefix.equals("tmp") || prefix.equals("thumb") || prefix.equals("large")) {
                            log.debug("continue");
                            continue;
                        }
                        log.debug("10");
                        ByteArrayOutputStream os = new ByteArrayOutputStream();
                        if (ftp.retrieveFile(ftp.printWorkingDirectory() + "/" + f.getName(), os)) {
                            log.debug("11");
                            log.debug("size:" + f.getSize());
                            byte[] fileContent = os.toByteArray();
                            log.debug("12");
                            AdjuntoPojo aP = new AdjuntoPojo();
                            log.debug("13");
                            aP.setNombreArchivo(f.getName());
                            log.debug("14");
                            aP.setPeso(f.getSize());
                            log.debug("15");
                            aP.setFile(fileContent);
                            log.debug("16");
                            files.add(aP);
                            log.debug("17");
                            //files.add(new AdjuntoPojo(f.getName(), f.getSize(), tipoDocumento, fileContent));
                        }
                    }
                }
            } else {
                log.error("No existe directorio " + ftp.printWorkingDirectory() + remotePath);
            }
        } catch (Exception ex) {
            log.error("ocurrió un error al descargar archivos zip: " + ex);
        }
        return files;
    }
    /*public static void main(String [] args){
        UploadFileRepository ufr = new UploadFileRepository();
        File firstLocalFile = new File("C:/Touch2.txt");
        String filename = "/Touch2.txt";
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(firstLocalFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UploadFileRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        ufr.upload(fis, "2303/bdg_increase/" , filename);
    }*/
    
}
