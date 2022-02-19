package com.autolib.helpdesk.Returnable.entity;

import java.util.Date;
import java.util.List;

public class ReturnableSearchRequest {

	private String subject;
	private String description;
	private String instName;
	private String instId;
	private String agentEmailId;
	private String agentName;
	private List<ReturnableProduct> returnableProducts;
	private Date createdDateFrom = null;
	private Date createdDateTo = null;
	private Date lastupdatedatetimeFrom = null;
	private Date lastupdatedatetimeTo = null;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInstName() {
		return instName;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}

	public String getInstId() {
		return instId;
	}

	public void setInstId(String instId) {
		this.instId = instId;
	}

	public String getAgentEmailId() {
		return agentEmailId;
	}

	public void setAgentEmailId(String agentEmailId) {
		this.agentEmailId = agentEmailId;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public List<ReturnableProduct> getReturnableProducts() {
		return returnableProducts;
	}

	public void setReturnableProducts(List<ReturnableProduct> returnableProducts) {
		this.returnableProducts = returnableProducts;
	}

	public Date getCreatedDateFrom() {
		return createdDateFrom;
	}

	public void setCreatedDateFrom(Date createdDateFrom) {
		this.createdDateFrom = createdDateFrom;
	}

	public Date getCreatedDateTo() {
		return createdDateTo;
	}

	public void setCreatedDateTo(Date createdDateTo) {
		this.createdDateTo = createdDateTo;
	}

	public Date getLastupdatedatetimeFrom() {
		return lastupdatedatetimeFrom;
	}

	public void setLastupdatedatetimeFrom(Date lastupdatedatetimeFrom) {
		this.lastupdatedatetimeFrom = lastupdatedatetimeFrom;
	}

	public Date getLastupdatedatetimeTo() {
		return lastupdatedatetimeTo;
	}

	public void setLastupdatedatetimeTo(Date lastupdatedatetimeTo) {
		this.lastupdatedatetimeTo = lastupdatedatetimeTo;
	}

}
