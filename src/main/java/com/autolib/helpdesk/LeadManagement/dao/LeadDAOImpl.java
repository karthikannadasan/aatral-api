package com.autolib.helpdesk.LeadManagement.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.autolib.helpdesk.Agents.entity.Agent;
import com.autolib.helpdesk.LeadManagement.model.Lead;
import com.autolib.helpdesk.LeadManagement.model.LeadMeeting;
import com.autolib.helpdesk.LeadManagement.model.LeadRequest;
import com.autolib.helpdesk.LeadManagement.model.LeadSearchRequest;
import com.autolib.helpdesk.LeadManagement.model.LeadTask;
import com.autolib.helpdesk.LeadManagement.repository.LeadMeetingRepository;
import com.autolib.helpdesk.LeadManagement.repository.LeadRepository;
import com.autolib.helpdesk.LeadManagement.repository.LeadTaskRepository;
import com.autolib.helpdesk.common.DirectoryUtil;
import com.autolib.helpdesk.common.Util;

@Repository
public class LeadDAOImpl implements LeadDAO {

	@Value("${al.ticket.content-path}")
	private String contentPath;

	@Autowired
	LeadRepository leadRepo;

	@Autowired
	LeadTaskRepository leadTaskRepo;

	@Autowired
	LeadMeetingRepository leadMeetingRepo;

	@Autowired
	EntityManager em;

	@Override
	public Map<String, Object> createLead(LeadRequest leadReq) {
		Map<String, Object> resp = new HashMap<>();
		try {

			if (leadReq.getLead().getOwnerEmailId() == null || leadReq.getLead().getOwnerEmailId().isEmpty()) {
				resp.putAll(Util.invalidMessage("Lead Owner can't be Empty"));
			} else if (leadReq.getLead().getCompany() == null || leadReq.getLead().getCompany().isEmpty()) {
				resp.putAll(Util.invalidMessage("Company can't be Empty"));
			} else {

				leadReq.setLead(leadRepo.save(leadReq.getLead()));

				if (leadReq.getLead().getFiles() != null && leadReq.getLead().getFiles().length() > 0) {

					List<String> filenames = Arrays.asList(leadReq.getLead().getFiles().split(";"));

					filenames.parallelStream().filter(filename -> filename.length() > 0).forEach(filename -> {
						Path source = Paths.get(contentPath + DirectoryUtil.leadRootDirectory
								+ leadReq.getDirectoryName() + "/" + filename);
						Path destination = Paths.get(contentPath + DirectoryUtil.leadRootDirectory
								+ leadReq.getLead().getId() + "/" + filename);

						File directory = destination.toFile();
						System.out.println(directory.getAbsolutePath());
						if (!directory.exists()) {
							directory.mkdirs();
						}
						try {
							Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					});
				}
				resp.putAll(Util.SuccessResponse());
			}
		} catch (Exception ex) {
			resp.putAll(Util.FailedResponse(ex.getMessage()));
			ex.printStackTrace();
		}
		resp.put("Lead", leadReq.getLead());
		return resp;
	}

	@Override
	public Map<String, Object> updateLead(LeadRequest leadReq) {
		Map<String, Object> resp = new HashMap<>();
		try {

			leadReq.setLead(leadRepo.save(leadReq.getLead()));

			resp.putAll(Util.SuccessResponse());

		} catch (Exception ex) {
			resp.putAll(Util.FailedResponse(ex.getMessage()));
			ex.printStackTrace();
		}
		resp.put("Lead", leadReq.getLead());
		return resp;
	}

	@Override
	public Map<String, Object> getLead(int leadId) {
		Map<String, Object> resp = new HashMap<>();
		Lead lead = null;
		try {

			lead = leadRepo.findById(leadId);

			resp.putAll(Util.SuccessResponse());
		} catch (Exception ex) {
			resp.putAll(Util.FailedResponse(ex.getMessage()));
			ex.printStackTrace();
		}
		resp.put("Lead", lead);
		return resp;
	}

	@Override
	public Map<String, Object> deleteLead(LeadRequest leadReq) {
		Map<String, Object> resp = new HashMap<>();
		try {

			leadRepo.delete(leadReq.getLead());

			resp.putAll(Util.SuccessResponse());
		} catch (Exception ex) {
			resp.putAll(Util.FailedResponse(ex.getMessage()));
			ex.printStackTrace();
		}
		return resp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> searchLeads(LeadSearchRequest request) {
		Map<String, Object> resp = new HashMap<>();
		List<Lead> leads = new ArrayList<>();
		try {

			String filterQuery = "";

			if (request.getCompanies() != null && request.getCompanies().size() > 0) {
				String str = "'0'";
				for (String _str : request.getCompanies()) {
					str = str + ",'" + _str + "'";
				}
				filterQuery = filterQuery + " and l.company in (" + str + ") ";
			}

			if (request.getOwners() != null && request.getOwners().size() > 0) {
				String owner = "'0'";
				for (Agent _owner : request.getOwners()) {
					owner = owner + ",'" + _owner.getEmailId() + "'";
				}
				filterQuery = filterQuery + " and l.ownerEmailId in (" + owner + ") ";
			}

			if (request.getProducts() != null && request.getProducts().size() > 0) {
				String subQuery = "";
				for (String _str : request.getProducts()) {

					if (!subQuery.isEmpty())
						subQuery = subQuery + " or ";

					subQuery = subQuery + " l.products like '%" + _str + "%' ";
				}
				filterQuery = filterQuery + " and (" + subQuery + ") ";
			}

			if (request.getIndustryTypes() != null && request.getIndustryTypes().size() > 0) {
				String str = "'0'";
				for (String _str : request.getIndustryTypes()) {
					str = str + ",'" + _str + "'";
				}
				filterQuery = filterQuery + " and l.industryType in (" + str + ") ";
			}

			if (request.getLeadSources() != null && request.getLeadSources().size() > 0) {
				String str = "'0'";
				for (String _str : request.getLeadSources()) {
					str = str + ",'" + _str + "'";
				}
				filterQuery = filterQuery + " and l.leadSource in (" + str + ") ";
			}

			if (request.getStatus() != null && request.getStatus().size() > 0) {
				String str = "'0'";
				for (String _str : request.getStatus()) {
					str = str + ",'" + _str + "'";
				}
				filterQuery = filterQuery + " and l.status in (" + str + ") ";
			}

			if (request.getTitle() != null && !request.getTitle().isEmpty()) {
				filterQuery = filterQuery + " and l.title like '%" + request.getTitle() + "%'";
			}

			if (request.getCity() != null && !request.getCity().isEmpty()) {
				filterQuery = filterQuery + " and l.city like '%" + request.getCity() + "%'";
			}

			if (request.getState() != null && !request.getState().isEmpty()) {
				filterQuery = filterQuery + " and l.state like '%" + request.getState() + "%'";
			}

			if (request.getCountry() != null && !request.getCountry().isEmpty()) {
				filterQuery = filterQuery + " and l.country like '%" + request.getCountry() + "%'";
			}

			if (request.getPincode() != null && !request.getPincode().isEmpty()) {
				filterQuery = filterQuery + " and l.pincode like '%" + request.getPincode() + "%'";
			}

			if (request.getLeadDateFrom() != null && request.getLeadDateTo() != null) {
				filterQuery = filterQuery + " and l.leadDate between '" + Util.sdfFormatter(request.getLeadDateFrom())
						+ "' and '" + Util.sdfFormatter(request.getLeadDateTo()) + " 23:59:59'";
			}

			if (request.getLastupdatedatetimeFrom() != null && request.getLastupdatedatetimeTo() != null) {
				filterQuery = filterQuery + " and l.lastupdatedatetime between '"
						+ Util.sdfFormatter(request.getLastupdatedatetimeFrom()) + "' and '"
						+ Util.sdfFormatter(request.getLastupdatedatetimeTo()) + " 23:59:59'";
			}

			Query query = em.createQuery("select l from Lead l where 2 > 1  " + filterQuery, Lead.class);

			leads = query.getResultList();

			resp.putAll(Util.SuccessResponse());
		} catch (Exception ex) {
			resp.putAll(Util.FailedResponse(ex.getMessage()));
			ex.printStackTrace();
		}
		resp.put("Leads", leads);
		return resp;
	}

	@Override
	public Map<String, Object> saveLeadTask(LeadRequest leadReq) {
		Map<String, Object> resp = new HashMap<>();
		try {

			leadReq.setLeadTask(leadTaskRepo.save(leadReq.getLeadTask()));

			resp.putAll(Util.SuccessResponse());

		} catch (Exception ex) {
			resp.putAll(Util.FailedResponse(ex.getMessage()));
			ex.printStackTrace();
		}
		resp.put("LeadTask", leadReq.getLeadTask());
		return resp;
	}

	@Override
	public Map<String, Object> getLeadTasks(int leadId) {
		Map<String, Object> resp = new HashMap<>();
		List<LeadTask> tasks = new ArrayList<>();
		try {

			tasks = leadTaskRepo.findByLeadId(leadId);

			resp.putAll(Util.SuccessResponse());

		} catch (Exception ex) {
			resp.putAll(Util.FailedResponse(ex.getMessage()));
			ex.printStackTrace();
		}
		resp.put("LeadTasks", tasks);
		return resp;
	}

	@Override
	public Map<String, Object> deleteLeadTask(LeadRequest leadReq) {
		Map<String, Object> resp = new HashMap<>();
		try {

			leadTaskRepo.delete(leadReq.getLeadTask());

			resp.putAll(Util.SuccessResponse());

		} catch (Exception ex) {
			resp.putAll(Util.FailedResponse(ex.getMessage()));
			ex.printStackTrace();
		}
		return resp;
	}

	@Override
	public Map<String, Object> saveLeadMeeting(LeadRequest leadReq) {
		Map<String, Object> resp = new HashMap<>();
		try {

			leadReq.setLeadMeeting(leadMeetingRepo.save(leadReq.getLeadMeeting()));

			resp.putAll(Util.SuccessResponse());

		} catch (Exception ex) {
			resp.putAll(Util.FailedResponse(ex.getMessage()));
			ex.printStackTrace();
		}
		resp.put("LeadTask", leadReq.getLeadTask());
		return resp;
	}

	@Override
	public Map<String, Object> getLeadMeetings(int leadId) {
		Map<String, Object> resp = new HashMap<>();
		List<LeadMeeting> meetings = new ArrayList<>();
		try {

			meetings = leadMeetingRepo.findByLeadId(leadId);

			resp.putAll(Util.SuccessResponse());

		} catch (Exception ex) {
			resp.putAll(Util.FailedResponse(ex.getMessage()));
			ex.printStackTrace();
		}
		resp.put("LeadMeetings", meetings);
		return resp;
	}

	@Override
	public Map<String, Object> deleteLeadMeeting(LeadRequest leadReq) {
		Map<String, Object> resp = new HashMap<>();
		try {

			leadMeetingRepo.delete(leadReq.getLeadMeeting());

			resp.putAll(Util.SuccessResponse());

		} catch (Exception ex) {
			resp.putAll(Util.FailedResponse(ex.getMessage()));
			ex.printStackTrace();
		}
		return resp;
	}

}
