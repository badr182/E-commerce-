package org.sid.eco.web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.sid.eco.dao.ProductRepository;
import org.sid.eco.entities.Product;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@RestController
public class CatalogueRestController {
	
	private ProductRepository productRepository;

	public CatalogueRestController(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	// PathVariable
	// produces = MediaType.IMAGE_JPEG_VALUE
	@GetMapping(path = "/photoProduct/{id}",produces = MediaType.IMAGE_JPEG_VALUE) 
	public byte[] getPhoto(@PathVariable("id") Long id ) throws Exception {
		Product p = productRepository.findById(id).get();
		// /home/galaxy/Documents/
		//  Paths.get( "kk" ) 
		return Files.readAllBytes( Paths.get("/home/galaxy/Documents/ecommerce_resource/products/"+p.getPhotoName()) );
	}
	
	@PostMapping(path = "/uploadPhoto/{id}")
	public void uploadPhoto(MultipartFile file,@PathVariable Long id) throws IOException {
		Product p = productRepository.findById(id).get();
		// p.setPhotoName(id+".jpg");
		p.setPhotoName(file.getOriginalFilename());
		Files.write(Paths.get("/home/galaxy/Documents/ecommerce_resource/products/"+p.getPhotoName()),file.getBytes());
		
		productRepository.save(p);
	}
	
	
}
