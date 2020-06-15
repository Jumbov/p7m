package it.validafiles.app.entities;

import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity 
@Table(name = "extentions_mime")  
public class MimeEstensioneFile {
	
	@Id
	@Column
	private String extention;
	
	@Column(name="mime_type")
	private String mimeType;

	
	public MimeEstensioneFile() {
		
	}
	
	public MimeEstensioneFile(String estensione) {
		this.extention = estensione;
	}

	
	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getExtention() {
		return extention;
	}

	public void setExtention(String extention) {
		this.extention = extention;
	}
	
	

}
