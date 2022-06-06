package com.isaachome.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.isaachome.entity.Product;
import com.isaachome.payload.FileResponse;
import com.isaachome.service.ProductService;

/**
 *
 * @author IsaacHome
 */

@RestController
@RequestMapping("api/products")
public class ProductApi {


	@Value("${project.image}")
	private String path;
	@Autowired
	private ProductService service;
	
	@GetMapping
	public List<Product> getAllProducts() {
		return service.getAllProducts();
	}
	
	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product product){
		return new ResponseEntity<Product>(service.create(product),HttpStatus.CREATED);
	}
	
	@PostMapping("/{id}/image/upload")
	public ResponseEntity<FileResponse> uploadImageForProduct(
			@PathVariable("id")long id,
			@RequestParam("image") MultipartFile image){
		var fileResponse = service.uploadProductImage(id, path, image);
		return new ResponseEntity<>(fileResponse,HttpStatus.CREATED);
	}
	
}
