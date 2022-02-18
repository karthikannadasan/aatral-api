package com.autolib.helpdesk.LeadManagement.service;

import java.util.Map;

import com.autolib.helpdesk.LeadManagement.model.LeadRequest;
import com.autolib.helpdesk.LeadManagement.model.LeadSearchRequest;

public interface LeadService {

	Map<String, Object> createLead(LeadRequest leadReq);

	Map<String, Object> updateLead(LeadRequest leadReq);

	Map<String, Object> getLead(int leadId);

	Map<String, Object> deleteLead(LeadRequest leadReq);

	Map<String, Object> searchLeads(LeadSearchRequest request);

	Map<String, Object> saveLeadTask(LeadRequest leadReq);

	Map<String, Object> getLeadTasks(int leadId);

	Map<String, Object> deleteLeadTask(LeadRequest leadReq);

	Map<String, Object> saveLeadMeeting(LeadRequest leadReq);

	Map<String, Object> getLeadMeetings(int leadId);

	Map<String, Object> deleteLeadMeeting(LeadRequest leadReq);

}
