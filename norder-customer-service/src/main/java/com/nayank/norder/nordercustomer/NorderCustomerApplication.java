package com.nayank.norder.nordercustomer;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EnableDiscoveryClient
@SpringBootApplication
public class NorderCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NorderCustomerApplication.class, args);
	}

}



@RestController
@RefreshScope
class CustomerController {
	private static final Logger LOG = Logger.getLogger(CustomerController.class.getName());	

	@Autowired
	private CustomerRepos customerRepos;
	
	
	@RequestMapping(value="/message")
	public List<Customer>  login(String name, String email)
	{
		return customerRepos.findByNameAndEmail(name,email);
	}
}


@Component
class SampleCLR implements  CommandLineRunner
{
	@Autowired
	private CustomerRepos customerRepos;

	private static final Logger LOG = Logger.getLogger(SampleCLR.class.getName());
	
	@Override
	public void run(String... args) throws Exception {

		LOG.info("sunning sampler");

		customerRepos.save(new Customer("Nayan","n@g.com","Pune"));
		customerRepos.save(new Customer("Nayan1","n1@g.com","Pune1"));
		customerRepos.save(new Customer("Nayan2","n2@g.com","Pune2"));
		customerRepos.save(new Customer("Nayan3","n3@g.com","Pune3"));
	}
	
}

@RepositoryRestResource
interface CustomerRepos extends JpaRepository< Customer,Long> 
{
	List<Customer> findByNameAndEmail(String name,String email);	
}
 

@Entity
class Customer 
{
	@Id
	@GeneratedValue
	Long id ;
	String name ;
	String email;
	String address;

	public Customer()
	{
	}
	
	public Customer (String name , String email, String address)
	{
		this.name=name;
		this.email= email;
		this.address=address;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", email=" + email + ", address=" + address + "]";
	}
}