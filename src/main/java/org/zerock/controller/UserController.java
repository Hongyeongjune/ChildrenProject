package org.zerock.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.model.dto.CIRequest;
import org.zerock.model.dto.NHRequest;
import org.zerock.service.FileService;
import org.zerock.service.UserService;

@RestController
public class UserController {
	
	UserService userService;
	FileService fileService;
	
	public UserController(UserService userService, FileService fileService) {
		this.userService = userService;
		this.fileService = fileService;    
	}
	
	@RequestMapping(value ="createNH", method = RequestMethod.POST)
	public void createMusicNH(@RequestBody NHRequest request) {
		
		userService.createUserNH(request);
	}
	
	@RequestMapping(value ="createCI", method = RequestMethod.POST)
	public void userCreateCI(@RequestBody CIRequest request) {
		
		userService.createUserCI(request);
	}
	
	@RequestMapping(value = "/createNHFile/{name}", method = RequestMethod.GET)
	public void createNHFile(@PathVariable("name") String name) {
		
		userService.createNHFile(name);
		
	}
	
	@RequestMapping(value = "/createCIFile/{name}", method = RequestMethod.GET)
	public void createCIFile(@PathVariable("name") String name) {
		System.out.println("안녕");
		userService.createCIFile(name);
		
	}
	
	@GetMapping("downloads/filename/{filename}")
	public ResponseEntity<InputStreamResource> downloadFileByFileName(@PathVariable("filename") String fileName) throws Exception {
		return fileService.downloadFileByName(fileName);
	}


}
