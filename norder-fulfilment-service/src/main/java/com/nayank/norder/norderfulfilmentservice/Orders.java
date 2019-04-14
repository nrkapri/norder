package com.nayank.norder.norderfulfilmentservice;

import java.util.ArrayList;
import java.util.List;


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

class Orders 
{
	Long id ;
	Long customerId;
	String status;

	List<Item> items= new ArrayList<Item>();


	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}


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
		return "Orders [id=" + id + ", customerId=" + customerId + ", status=" + status + ", items=" + items + "]";
	}

	public Orders( Long customerId, String status,List<Item> items) {
		this.customerId = customerId;
		this.status=status;
		this.items = (ArrayList<Item>) items;
	}

	public Orders()
	{

	}



}