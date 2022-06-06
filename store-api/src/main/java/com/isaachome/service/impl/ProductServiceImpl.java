package com.isaachome.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.isaachome.ResourceNotFoundException;
import com.isaachome.entity.Product;
import com.isaachome.payload.FileResponse;
import com.isaachome.repo.ProductRepo;
import com.isaachome.service.FileService;
import com.isaachome.service.ProductService;

/**
 *
 * @author IsaacHome
 */

@Service
public class ProductServiceImpl  implements ProductService{

	private ProductRepo repo;
	private FileService fileService;
	
	public ProductServiceImpl(ProductRepo repo,FileService fileService) {
		super();
		this.repo = repo;
		this.fileService = fileService;
	}

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Product create(Product product) {
		return repo.save(product);
	}
	

	@Override
	public Product getProductById(long id) {
		var p = repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product","ID",id));
		return p;
	}
	
	
	public FileResponse uploadProductImage(long id,String path,MultipartFile image) {
		var p = getProductById(id);
		String filename="";
		try {
			filename = this.fileService.uploadImage(path, image);
			p.setImage(filename);
			p =repo.save(p);
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		return new FileResponse("Product Image was uploaded", filename);
	}

}
