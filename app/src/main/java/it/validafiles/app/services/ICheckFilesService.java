package it.validafiles.app.services;

import java.util.List;

import it.validafiles.app.pojos.FileChecked;

public interface ICheckFilesService {
	
	List<FileChecked> getFileChecked(String path);	

}
