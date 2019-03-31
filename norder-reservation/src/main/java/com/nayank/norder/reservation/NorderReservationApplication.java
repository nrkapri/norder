package com.nayank.norder.reservation;

import java.util.stream.Stream;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.jboss.logging.Logger;
//import org.slf4j.Logger;
//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class NorderReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(NorderReservationApplication.class, args);
	}

}

@RestController
@RefreshScope
class MessageController 
{
	private static final Logger LOG = Logger.getLogger(MessageController.class.getName());
	@Value("${message}")
	String messgae  ;
	
	@RequestMapping(value="/message")
	public String message()
	{
		LOG.info("message callled ");
		return this.messgae;
	}
}


@Component
class SampleCLR implements  CommandLineRunner
{
	private static final Logger LOG = Logger.getLogger(SampleCLR.class.getName());
	private ReservationRepository reservationRepository;
	
	@Autowired
	public void setReservationRepository(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}


	@Override
	public void run(String... args) throws Exception {
		Stream.of("Nayan","Josh","Chris","Linus","Mark","Lary","Sergey").forEach(name->reservationRepository.save(new Reservation(name)));
		reservationRepository.findAll().forEach(r -> System.out.println(r.toString()));
		LOG.info("default callled ");
	}
	
}

@RepositoryRestResource
interface ReservationRepository extends JpaRepository<Reservation , Long>
{
}

@Entity 
class Reservation
{
	@Id
	@GeneratedValue
	Long id;

	String reservationName;



	public Reservation() {
	}

	public Reservation( String reservationName) {
		this.reservationName = reservationName;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", reservationName=" + reservationName + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	} 
}