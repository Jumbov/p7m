package it.validafiles.app.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.validafiles.app.entities.MimeEstensioneFile;

public interface EstensioneFileDao extends JpaRepository<MimeEstensioneFile, String>{

	List<MimeEstensioneFile> findAll();
}
