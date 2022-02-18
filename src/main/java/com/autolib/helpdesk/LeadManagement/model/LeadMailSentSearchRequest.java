package com.autolib.helpdesk.LeadManagement.model;

import java.util.Date;

public class LeadMailSentSearchRequest {

	private String leadId;
	private String templateId;
	private String mailId;
	private String mailCC;
	private String leadCompany;
	private String leadTitle;
	private Date sendDateFrom;
	private Date sendDateTo;

	public String getLeadId() {
		return leadId;
	}

	public void setLeadId(String leadId) {
		this.leadId = leadId;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getMailCC() {
		return mailCC;
	}

	public void setMailCC(String mailCC) {
		this.mailCC = mailCC;
	}

	public String getLeadCompany() {
		return leadCompany;
	}

	public void setLeadCompany(String leadCompany) {
		this.leadCompany = leadCompany;
	}

	public String getLeadTitle() {
		return leadTitle;
	}

	public void setLeadTitle(String leadTitle) {
		this.leadTitle = leadTitle;
	}

	public Date getSendDateFrom() {
		return sendDateFrom;
	}

	public void setSendDateFrom(Date sendDateFrom) {
		this.sendDateFrom = sendDateFrom;
	}

	public Date getSendDateTo() {
		return sendDateTo;
	}

	public void setSendDateTo(Date sendDateTo) {
		this.sendDateTo = sendDateTo;
	}

}
