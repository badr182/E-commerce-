package org.sid.eco.dao;

import java.util.List;

import org.sid.eco.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin("*")
@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	// RestResource
	
	@RestResource( path = "/selectedProducts" )
	public List<Product> findBySelectedIsTrue(); 
	
	@RestResource( path = "/productsByKeyword" )
	public List<Product> findByNameContains(@Param("mc") String mc);
	// @Query("select p from Product p where p.name like :x")
	// public List<Product> chercher(@Param("x") String mc);
	
	@RestResource( path = "/promoProducts" )
	public List<Product> findByPromoionIsTrue();
	
	@RestResource( path = "/dispoProducts" )
	public List<Product> findByAvailableIsTrue();
}
