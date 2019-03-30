package com.nayank.norder.reservationclient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.hateoas.Resources;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


//@IntegrationComponentScan
@EnableBinding(ReservationChannels.class)
@EnableFeignClients
@EnableZuulProxy
@EnableDiscoveryClient
@EnableCircuitBreaker
@SpringBootApplication
public class ReservationClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationClientApplication.class, args);
	}

}


interface ReservationChannels
{
	@Output
	MessageChannel output();
}


//@MessagingGateway
//interface ReservationWriter
//{
//	@Gateway(requestChannel="output")
//	void write(String rn);
//}


@FeignClient("reservation-service")
interface ReservationReader
{
	@RequestMapping(method=RequestMethod.GET,value="/reservations")
	Resources<Reservation> read();
}




class Reservation
{
	private String reservationName;

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}

	@Override
	public String toString() {
		return "Reservation [reservationName=" + reservationName + "]";
	}

}


@RestController
@RequestMapping("/reservations")
class ReservationAPIGateway
{
	private final ReservationReader reservationReader;
//	private final ReservationWriter reservationWriter;
	private final MessageChannel out;
	
	@Autowired
	public ReservationAPIGateway(ReservationReader reservationReader,
			ReservationChannels rc
			//ReservationWriter reservationWriter
			) {
		this.reservationReader = reservationReader;
//		this.reservationWriter =reservationWriter;
		this.out=rc.output();
	} 

	@RequestMapping(method=RequestMethod.POST)
	public void write(@RequestBody Reservation reservation)
	{
		//reservationWriter.write(reservation.getReservationName());
		out.send(MessageBuilder.withPayload(reservation.getReservationName()).build());
	}
	
	
	List<String> namesFallback()
	{
		return new ArrayList<String>();

	}

	@HystrixCommand(fallbackMethod="namesFallback")
	@RequestMapping("/names")
	public List<String> names()
	{
		Collection<Reservation > c =reservationReader
				.read()
				.getContent();
		System.out.println("Nayan: "+c);
		return		c.stream()
				.map(r->r.getReservationName())
				.collect(Collectors.toList());
	}

}