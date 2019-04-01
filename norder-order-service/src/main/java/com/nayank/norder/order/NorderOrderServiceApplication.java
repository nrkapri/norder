package com.nayank.norder.order;

import java.util.ArrayList;
import java.util.List;

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


@EnableDiscoveryClient
@SpringBootApplication
public class NorderOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NorderOrderServiceApplication.class, args);
	}

}


@RestController
@RefreshScope
class OrderController {
	private static final Logger LOG = Logger.getLogger(OrderController.class.getName());	

	@Autowired
	private OrderRepos customerRepos;
	
	
}


@Component
class SampleCLR implements  CommandLineRunner
{
	@Autowired
	private OrderRepos orderRepos;

	private static final Logger LOG = Logger.getLogger(SampleCLR.class.getName());
	
	@Override
	public void run(String... args) throws Exception {

		LOG.info("sunning sampler");

		ArrayList<Item> items1 = new ArrayList<Item>();
		items1.add(new Item((long)1,(long)1));
		items1.add(new Item((long)2,(long)2));
		items1.add(new Item((long)3,(long)3));
		
		orderRepos.save(new Order((long)1, items1));
		
		ArrayList<Item> items2 = new ArrayList<Item>();
		items2.add(new Item((long)1,(long)3));
		items2.add(new Item((long)2,(long)2));
		items2.add(new Item((long)3,(long)1));
		orderRepos.save(new Order((long)2, items2));
		
	
		ArrayList<Item> items3 = new ArrayList<Item>();
		items3.add(new Item((long)1,(long)4));
		items3.add(new Item((long)2,(long)5));
		items3.add(new Item((long)3,(long)6));
		orderRepos.save(new Order((long)3, items3));
		
	}
	
}

@RepositoryRestResource
interface OrderRepos extends JpaRepository< Order,Long> 
{
}
 
