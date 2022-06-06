package com.isaachome.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.isaachome.entity.Product;
import com.isaachome.payload.FileResponse;
import com.isaachome.service.FileService;
import com.isaachome.service.ProductService;

/**
 *
 * @author IsaacHome
 */

@RestController
@RequestMapping("/file")
public class FIleController {

	@Autowired
	private FileService fileService;
	@Value("${project.image}")
	private String path;
	
	@Autowired
	private ProductService service;
	
	@PostMapping("/upload")
	public ResponseEntity<FileResponse>  uploadFile(
			@RequestBody Product product,
			@RequestParam("image") MultipartFile image){
		
		
		String filename="";
		try {
			filename = this.fileService.uploadImage(path, image);
			product.setImage(filename);
			service.create(product);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>(new FileResponse(null, "Image can  not be  uploaded on server"),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(new FileResponse(filename, "Image is successfully uploaded"),HttpStatus.OK);
	}
}
