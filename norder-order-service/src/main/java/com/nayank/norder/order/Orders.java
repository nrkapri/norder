package com.nayank.norder.order;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Embeddable
class Item{
	Long productId;
	Long quantity;
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "Item [productId=" + productId + ", quantity=" + quantity + "]";
	}
	public Item(Long productId, Long quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
	}

	public Item()
	{

	}
}

@Entity
class Orders 
{
	@Id
	@GeneratedValue
	Long id ;
	Long customerId;

	//@OneToMany(targetEntity=Item.class,mappedBy="id", fetch=FetchType.EAGER)
	@ElementCollection 
	List<Item> items= new ArrayList<Item>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = (ArrayList<Item>) items;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", customerId=" + customerId + ", items=" + items + "]";
	}

	public Orders( Long customerId, List<Item> items) {
		this.customerId = customerId;
		this.items = (ArrayList<Item>) items;
	}

	public Orders()
	{
		
	}



}