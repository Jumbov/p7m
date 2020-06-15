package it.validafiles.app.services;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.util.encoders.Base64;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Files;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.validafiles.app.daos.EstensioneFileDao;
import it.validafiles.app.entities.MimeEstensioneFile;
import it.validafiles.app.pojos.FileChecked;

@Service
public class CheckFilesService implements ICheckFilesService {
	
	 private static final Logger log = LoggerFactory.getLogger(CheckFilesService.class);
			 
	@Autowired
	EstensioneFileDao estensioneFileDao;
	
	@Override
	public List<FileChecked> getFileChecked(String path) {
		
		List<MimeEstensioneFile> listMimes = estensioneFileDao.findAll();
		
		List<FileChecked> listFileChecked = new ArrayList<FileChecked>();
		
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		
		//Cicla tutti i file della cartella
		for (File file : listOfFiles) { 
		    if (file.isFile()) {
		        String fileName = file.getName();
		        String estensione = fileName.substring(fileName.lastIndexOf(".") + 1);
		        
		        String fileMimeType="";
		        
		        if("p7m".equalsIgnoreCase(estensione)) {
		        	
		        	try {
						byte[] content = FileUtils.readFileToByteArray(file);
						byte[] contentUnsigned = decryptP7mFile(file.getName(), content);
						 try {
							 fileMimeType = getMimeTypeFromByteArray(contentUnsigned);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
		        }else {	//se il file non è firmato p7m
		        	try {
		        		fileMimeType = getMimeTypeOfUnencryptedFile(file);
					} catch (IOException e) {
						e.printStackTrace();
					}
		        }
		        
		        FileChecked fc = new FileChecked();
		        fc.setFileName(fileName);
	        	fc.setValid(false);
	        	//Di ciascun file viene considerato il mime type: match con l'elenco dei mime type indicati a DB.
		        for(MimeEstensioneFile mime : listMimes)	
		        {
		            if(mime.getMimeType().equalsIgnoreCase(fileMimeType)) {
		            	
		            	fc.setValid(true);
		            	break;
		            }
		        }
		        listFileChecked.add(fc);
		    }
		}
		return listFileChecked;
	}
	
	
	private byte[] decryptP7mFile(final String fileName, byte[] p7bytes) {
	    try {

	       try { 				
	        	p7bytes = Base64.decode(p7bytes); 			
	        } catch (Exception e) {
	        	log.error("File P7m not in base64" + e.getMessage()); 			
	        }
	        
	        CMSSignedData cms = new CMSSignedData(p7bytes); 

	        if (cms.getSignedContent() == null) {
	        	log.error("Unable to find signed Content during decoding from P7M for file: " + fileName);               
	            return null;
	        }

	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        cms.getSignedContent().write(out);

	        return out.toByteArray();
	    } catch (CMSException e) {
	    	log.error("CMSException from P7M for file: " , fileName , e);
	    } catch (IOException e) {
	    	log.error("IOException from P7M for file: " , fileName , e);
	    }
	    return null;
	}
	
	
	private String getMimeTypeFromByteArray(byte data[]) throws Exception {		
		InputStream is = new BufferedInputStream(new ByteArrayInputStream(data));
		String mimeType = URLConnection.guessContentTypeFromStream(is);
		return mimeType;
	}
	
	private String getMimeTypeOfUnencryptedFile(File f) throws IOException {
		
		return Files.probeContentType(f.toPath());
	}

}
