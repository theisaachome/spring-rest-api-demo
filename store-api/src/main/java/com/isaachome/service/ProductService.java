package com.isaachome.service;

import java.util.List;

import com.isaachome.entity.Product;

/**
 *
 * @author IsaacHome
 */

public interface ProductService {
	
	List<Product> getAllProducts();
	Product create(Product product);
	Product getProductById(long id);

}
