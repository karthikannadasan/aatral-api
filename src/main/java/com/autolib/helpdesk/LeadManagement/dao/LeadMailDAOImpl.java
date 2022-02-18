package com.autolib.helpdesk.LeadManagement.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.autolib.helpdesk.LeadManagement.model.LeadMailSentSearchRequest;
import com.autolib.helpdesk.LeadManagement.model.LeadMailSentStatusResponse;
import com.autolib.helpdesk.LeadManagement.model.LeadMailTemplate;
import com.autolib.helpdesk.LeadManagement.model.LeadMailTemplateSearchRequest;
import com.autolib.helpdesk.LeadManagement.repository.LeadMailTemplateRepository;
import com.autolib.helpdesk.Sales.model.DealResponse;
import com.autolib.helpdesk.common.Util;
import com.autolib.helpdesk.schedulers.model.LeadMailSentStatus;

@Repository
public class LeadMailDAOImpl implements LeadMailDAO {

	@Autowired
	LeadMailTemplateRepository mailTemplateRepo;

	@Autowired
	EntityManager em;

	@Override
	public Map<String, Object> saveLeadMailTemplate(LeadMailTemplate template) {
		Map<String, Object> resp = new HashMap<>();
		try {

			template = mailTemplateRepo.save(template);

			resp.putAll(Util.SuccessResponse());
		} catch (Exception ex) {
			resp.putAll(Util.FailedResponse(ex.getMessage()));
			ex.printStackTrace();
		}
		resp.put("LeadMailTemplate", template);
		return resp;
	}

	@Override
	public Map<String, Object> getLeadMailTemplate(int templateId) {
		Map<String, Object> resp = new HashMap<>();
		LeadMailTemplate template = null;
		try {

			template = mailTemplateRepo.findById(templateId);

			resp.putAll(Util.SuccessResponse());
		} catch (Exception ex) {
			resp.putAll(Util.FailedResponse(ex.getMessage()));
			ex.printStackTrace();
		}
		resp.put("LeadMailTemplate", template);
		return resp;
	}

	@Override
	public Map<String, Object> deleteLeadMailTemplate(LeadMailTemplate template) {
		Map<String, Object> resp = new HashMap<>();
		try {

			mailTemplateRepo.delete(template);

			resp.putAll(Util.SuccessResponse());
		} catch (Exception ex) {
			resp.putAll(Util.FailedResponse(ex.getMessage()));
			ex.printStackTrace();
		}
		resp.put("LeadMailTemplate", template);
		return resp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> searchLeadMailTemplates(LeadMailTemplateSearchRequest request) {
		Map<String, Object> resp = new HashMap<>();
		List<LeadMailTemplate> templates = new ArrayList<>();
		try {

			String filterQuery = "";

			if (request.getTitle() != null && !request.getTitle().isEmpty())
				filterQuery = filterQuery + " and t.title like '%" + request.getTitle() + "%' ";
			if (request.getSubject() != null && !request.getSubject().isEmpty())
				filterQuery = filterQuery + " and t.subject like '%" + request.getSubject() + "%' ";
			if (request.getMessage() != null && !request.getMessage().isEmpty())
				filterQuery = filterQuery + " and t.message like '%" + request.getMessage() + "%' ";

			if (request.getStatus() != null && request.getStatus().size() > 0) {
				String _status = "'0'";
				for (String status : request.getStatus()) {
					_status = _status + ",'" + status + "'";
				}
				filterQuery = filterQuery + " and t.status in (" + _status + ") ";
			}

			if (request.getSendingAt() != null && request.getSendingAt().size() > 0) {
				for (String str : request.getSendingAt()) {
					if (str.equalsIgnoreCase("Daily"))
						filterQuery = filterQuery + " and t.daily = true";
					else if (str.equalsIgnoreCase("Weekly"))
						filterQuery = filterQuery + " and t.weekly = true";
					else if (str.equalsIgnoreCase("Bi-Weekly"))
						filterQuery = filterQuery + " and t.biweekly = true";
					else if (str.equalsIgnoreCase("Monthly"))
						filterQuery = filterQuery + " and t.monthly = true";
					else if (str.equalsIgnoreCase("Bi-Monthly"))
						filterQuery = filterQuery + " and t.bimonthly = true";
					else if (str.equalsIgnoreCase("Quarterly"))
						filterQuery = filterQuery + " and t.quarterly = true";
				}
			}

			Query query = em.createQuery("select t from LeadMailTemplate t where 2>1 " + filterQuery,
					LeadMailTemplate.class);

			templates = query.getResultList();

			resp.putAll(Util.SuccessResponse());
		} catch (Exception ex) {
			resp.putAll(Util.FailedResponse(ex.getMessage()));
			ex.printStackTrace();
		}
		resp.put("LeadMailTemplates", templates);
		return resp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> searchLeadMailSentStatus(LeadMailSentSearchRequest request) {
		Map<String, Object> resp = new HashMap<>();
		List<LeadMailSentStatus> status = new ArrayList<>();
		try {

			String filterQuery = "";

			if (request.getLeadId() != null && !request.getLeadId().isEmpty())
				filterQuery = filterQuery + " and ss.leadId = '" + request.getLeadId() + "'";
			if (request.getTemplateId() != null && !request.getTemplateId().isEmpty())
				filterQuery = filterQuery + " and ss.templateId = '" + request.getTemplateId() + "'";
			if (request.getMailId() != null && !request.getMailId().isEmpty())
				filterQuery = filterQuery + " and ss.mailId = '" + request.getMailId() + "'";
			if (request.getMailCC() != null && !request.getMailCC().isEmpty())
				filterQuery = filterQuery + " and ss.mailCC = '" + request.getMailCC() + "'";
			if (request.getLeadCompany() != null && !request.getLeadCompany().isEmpty())
				filterQuery = filterQuery + " and l.company = '" + request.getLeadCompany() + "'";
			if (request.getLeadTitle() != null && !request.getLeadTitle().isEmpty())
				filterQuery = filterQuery + " and l.title = '" + request.getLeadTitle() + "'";

			if (request.getSendDateFrom() != null && request.getSendDateTo() != null) {
				filterQuery = filterQuery + " and l.createddatetime between '"
						+ Util.sdfFormatter(request.getSendDateFrom()) + "' and '"
						+ Util.sdfFormatter(request.getSendDateTo()) + " 23:59:59'";
			}

			Query query = em.createQuery(
					"select new com.autolib.helpdesk.LeadManagement.model.LeadMailSentStatusResponse(l,ss) "
							+ "from LeadMailSentStatus ss left join fetch Lead l on (ss.leadId = l.id) where 2>1 "
							+ filterQuery,
					LeadMailSentStatusResponse.class);

			status = query.getResultList();

			resp.putAll(Util.SuccessResponse());
		} catch (Exception ex) {
			resp.putAll(Util.FailedResponse(ex.getMessage()));
			ex.printStackTrace();
		}
		resp.put("LeadMailSentStatus", status);
		return resp;
	}

}
