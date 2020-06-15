package it.validafiles.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.validafiles.app.pojos.JsonResponseBody;
import it.validafiles.app.services.ICheckFilesService;

import javax.servlet.http.HttpServletRequest;



@RestController
@RequestMapping("/checkfiles")
public class WSController {
	
	@Autowired
	ICheckFilesService checkFilesService;

	@RequestMapping("/filepath/{path}")
	public ResponseEntity<JsonResponseBody> getCheckedFileList(HttpServletRequest request, @PathVariable(name = "path") String filepath){
		
		//ToDo: gestione risposta nel caso di errore
		
	    return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), checkFilesService.getFileChecked(filepath) ) );
	}
	
	


}
