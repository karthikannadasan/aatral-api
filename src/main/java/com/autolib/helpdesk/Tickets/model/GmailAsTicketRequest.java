package com.autolib.helpdesk.Tickets.model;

import com.autolib.helpdesk.schedulers.model.GoogleMailAsTicket;

public class GmailAsTicketRequest {

	public GmailAsTicketRequest() {
		// TODO Auto-generated constructor stub
	}

	private Ticket ticket;
	private GoogleMailAsTicket gmailAsTicket;

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public GoogleMailAsTicket getGmailAsTicket() {
		return gmailAsTicket;
	}

	public void setGmailAsTicket(GoogleMailAsTicket gmailAsTicket) {
		this.gmailAsTicket = gmailAsTicket;
	}

	@Override
	public String toString() {
		return "GmailAsTicketRequest [ticket=" + ticket + ", gmailAsTicket=" + gmailAsTicket + "]";
	}

}
