package org.zerock.service.impl;

import java.io.File;
import java.io.FileInputStream;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.zerock.service.FileService;

@Service
public class FileServiceImpl implements FileService {
	
	private final String WINDOW_PATH = "C:\\Users\\User\\Desktop\\java\\";
	private final String LINUX_PATH = "/home/ubuntu/boot13-1/";
	
	@Override
	public ResponseEntity<InputStreamResource> downloadFileByName(String fileName) throws Exception {
//        String filePath = WINDOW_PATH + fileName;
		String filePath = LINUX_PATH + fileName;
        File file = new File(filePath);
        
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName())
                .contentType(MediaType.APPLICATION_OCTET_STREAM).contentLength(file.length())
                .body(resource);
    }

}
