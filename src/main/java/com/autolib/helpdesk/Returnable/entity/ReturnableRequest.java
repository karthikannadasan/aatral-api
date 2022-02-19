package com.autolib.helpdesk.Returnable.entity;

import java.util.List;

public class ReturnableRequest {

	private Returnable returnable;
	
	private List<ReturnableProduct> returnableProducts;

	public Returnable getReturnable() {
		return returnable;
	}

	public void setReturnable(Returnable returnable) {
		this.returnable = returnable;
	}

	public List<ReturnableProduct> getReturnableProducts() {
		return returnableProducts;
	}

	public void setReturnableProducts(List<ReturnableProduct> returnableProducts) {
		this.returnableProducts = returnableProducts;
	}
	
	

}
