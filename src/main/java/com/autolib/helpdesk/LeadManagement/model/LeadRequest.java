package com.autolib.helpdesk.LeadManagement.model;

public class LeadRequest {

	public LeadRequest() {
		// TODO Auto-generated constructor stub
	}

	private Lead lead;
	private LeadTask leadTask;
	private LeadMeeting leadMeeting;

	private String directoryName;

	public LeadTask getLeadTask() {
		return leadTask;
	}

	public void setLeadTask(LeadTask leadTask) {
		this.leadTask = leadTask;
	}

	public LeadMeeting getLeadMeeting() {
		return leadMeeting;
	}

	public void setLeadMeeting(LeadMeeting leadMeeting) {
		this.leadMeeting = leadMeeting;
	}

	public Lead getLead() {
		return lead;
	}

	public void setLead(Lead lead) {
		this.lead = lead;
	}

	public String getDirectoryName() {
		return directoryName;
	}

	public void setDirectoryName(String directoryName) {
		this.directoryName = directoryName;
	}

}
