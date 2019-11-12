package org.sid.eco;

import java.util.Random;

import org.sid.eco.dao.CategoryRepository;
import org.sid.eco.dao.ProductRepository;
import org.sid.eco.entities.Category;
import org.sid.eco.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import net.bytebuddy.utility.RandomString;

@SpringBootApplication
public class ECommerceAngApplication implements CommandLineRunner{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;
	
	public static void main(String[] args) {
		SpringApplication.run(ECommerceAngApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		// Qd tu envoie un produit en format json tu expose Id  
		repositoryRestConfiguration.exposeIdsFor(Product.class, Category.class);
		
		categoryRepository.save( new Category(null,"Computers",null,null,null) );
		categoryRepository.save( new Category(null,"Printers",null,null,null) );
		categoryRepository.save( new Category(null,"Smart Phone",null,null,null) );
		
		Random rnd = new Random();
		categoryRepository.findAll().forEach(c->{
			for( int i = 0 ; i <10 ;i++) {
				Product p = new Product();
				p.setName(RandomString.make(18));
				p.setCurrentprice(100 + rnd.nextInt(1000));
				p.setAvailable(rnd.nextBoolean());
				p.setPromoion(rnd.nextBoolean());
				p.setSelected(rnd.nextBoolean());
				p.setCategory(c);
				p.setPhotoName("unknown.png");
				productRepository.save(p);
			}
		});
	}

}
