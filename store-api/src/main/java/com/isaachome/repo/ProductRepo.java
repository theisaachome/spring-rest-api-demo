package com.isaachome.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isaachome.entity.Product;

/**
 *
 * @author IsaacHome
 */

public interface ProductRepo  extends JpaRepository<Product, Long>{

}
