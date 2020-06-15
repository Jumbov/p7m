package it.validafiles.app.pojos;

public class FileChecked {

	private String fileName;
	private boolean valid;
	
	public FileChecked() {
		
	}

	public FileChecked(String fileName, boolean valid) {
		this.fileName = fileName;
		this.valid = valid;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	
	
	
}
