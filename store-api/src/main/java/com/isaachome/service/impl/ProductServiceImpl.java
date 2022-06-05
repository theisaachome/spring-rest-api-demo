package com.isaachome.service.impl;

import java.util.List;

import com.isaachome.entity.Product;
import com.isaachome.repo.ProductRepo;
import com.isaachome.service.ProductService;

/**
 *
 * @author IsaacHome
 */

public class ProductServiceImpl  implements ProductService{

	private ProductRepo repo;
	
	public ProductServiceImpl(ProductRepo repo) {
		super();
		this.repo = repo;
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
//		var p = repo.findById(id).orElseThrow(()-> new Exception());
		return null;
	}

}
