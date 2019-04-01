package com.nayank.norder.product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
public class NorderProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NorderProductServiceApplication.class, args);
	}

}


@RestController
@RefreshScope
class ProductController {
	private static final Logger LOG = Logger.getLogger(ProductController.class.getName());	

	@Autowired
	private ProductRepos productRepos;
	
	
	
}


@Component
class SampleCLR implements  CommandLineRunner
{
	@Autowired
	private ProductRepos productRepos;

	private static final Logger LOG = Logger.getLogger(SampleCLR.class.getName());
	
	@Override
	public void run(String... args) throws Exception {

		LOG.info("sunning sampler");

		productRepos.save(new Product("P1",10.0,"Pune"));
		productRepos.save(new Product("P2",15.0,"Pune1"));
		productRepos.save(new Product("P3",20.0,"Pune2"));
		productRepos.save(new Product("P4",30.0,"Pune3"));
	}
	
}

@RepositoryRestResource
interface ProductRepos extends JpaRepository< Product,Long> 
{
}
 

@Entity
class Product  
{
	@Id
	@GeneratedValue
	Long id ;
	String name ;
	Double price;
	String desc;
	
	public Product()
	{
		
	}
	
	public Product( String name, Double price, String desc) {
		this.name = name;
		this.price = price;
		this.desc = desc;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", desc=" + desc + "]";
	}

	
	
	
}