package com.nayank.norder.order;

import java.util.ArrayList;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	private OrderRepos orderRepos;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	
	@RequestMapping(value="/newOrder/")
	public @ResponseBody Orders  newOrder()
	{

		ArrayList<Item> items1 = new ArrayList<Item>();
		items1.add(new Item((long)1,(long)1));
		items1.add(new Item((long)2,(long)2));
		items1.add(new Item((long)3,(long)3));

		Orders orders =orderRepos.save(new Orders((long)1, "PENDING", items1));
		kafkaTemplate.send("create-order", orders.getId().toString());
		return orders;
	}

	@RequestMapping(value="/fulfil/{id}")
	public void  fulfil(@PathVariable("id") Long oid)
	{
		Optional<Orders> orders= orderRepos.findById(oid);
		orders.ifPresent(o -> 
		{
			o.setStatus("FULFILLED");
			orderRepos.save(o);
		}
				);
	}
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

		orderRepos.save(new Orders((long)1, "PENDING", items1));

		ArrayList<Item> items2 = new ArrayList<Item>();
		items2.add(new Item((long)1,(long)3));
		items2.add(new Item((long)2,(long)2));
		items2.add(new Item((long)3,(long)1));
		orderRepos.save(new Orders((long)2,"PENDING",  items2));

		ArrayList<Item> items3 = new ArrayList<Item>();
		items3.add(new Item((long)1,(long)4));
		items3.add(new Item((long)2,(long)5));
		items3.add(new Item((long)3,(long)6));
		orderRepos.save(new Orders((long)3,"PENDING",  items3));
	}
}

@RepositoryRestResource
interface OrderRepos extends JpaRepository< Orders,Long> 
{

}

