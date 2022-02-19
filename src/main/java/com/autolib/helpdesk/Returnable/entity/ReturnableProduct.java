package com.autolib.helpdesk.Returnable.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "returnable_products", indexes = { @Index(name = "returnable_id_idx", columnList = "returnable_id") })
public class ReturnableProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "product_id")
	private int productId;

	@Column(name = "returnable_id")
	private int returnableId;

	private String name;
	private String description;
	private String uom;
	private int quantity;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getReturnableId() {
		return returnableId;
	}

	public void setReturnableId(int returnableId) {
		this.returnableId = returnableId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ReturnableProduct [returnableId=" + returnableId + ", id=" + id + ", productId=" + productId + ", name="
				+ name + ", description=" + description + ", uom=" + uom + ", quantity=" + quantity + "]";
	}

}
