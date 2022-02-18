package com.autolib.helpdesk.LeadManagement.service;

import java.util.Map;

import com.autolib.helpdesk.LeadManagement.model.LeadMailSentSearchRequest;
import com.autolib.helpdesk.LeadManagement.model.LeadMailTemplate;
import com.autolib.helpdesk.LeadManagement.model.LeadMailTemplateSearchRequest;

public interface LeadMailService {

	Map<String, Object> saveLeadMailTemplate(LeadMailTemplate template);

	Map<String, Object> getLeadMailTemplate(int templateId);

	Map<String, Object> deleteLeadMailTemplate(LeadMailTemplate template);

	Map<String, Object> searchLeadMailTemplates(LeadMailTemplateSearchRequest request);

	Map<String, Object> searchLeadMailSentStatus(LeadMailSentSearchRequest request);

}
