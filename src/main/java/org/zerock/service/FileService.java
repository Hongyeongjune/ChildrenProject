package org.zerock.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

public interface FileService {
	
	ResponseEntity<InputStreamResource> downloadFileByName(String fileName) throws Exception;

}
