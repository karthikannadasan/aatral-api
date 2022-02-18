package com.autolib.helpdesk.LeadManagement.model;

import java.util.List;

public class LeadMailTemplateSearchRequest {

	private String title;
	private String subject;
	private String message;
	private List<String> status;
	private List<String> sendingAt;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getStatus() {
		return status;
	}

	public void setStatus(List<String> status) {
		this.status = status;
	}

	public List<String> getSendingAt() {
		return sendingAt;
	}

	public void setSendingAt(List<String> sendingAt) {
		this.sendingAt = sendingAt;
	}

	@Override
	public String toString() {
		return "LeadMailTemplateSearchRequest [title=" + title + ", subject=" + subject + ", message=" + message
				+ ", status=" + status + ", sendingAt=" + sendingAt + "]";
	}

}
