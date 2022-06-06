package com.isaachome.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.isaachome.entity.Product;
import com.isaachome.payload.FileResponse;

/**
 *
 * @author IsaacHome
 */

public interface ProductService {
	
	List<Product> getAllProducts();
	Product create(Product product);
	Product getProductById(long id);
	FileResponse uploadProductImage(long id,String path,MultipartFile image);

}
