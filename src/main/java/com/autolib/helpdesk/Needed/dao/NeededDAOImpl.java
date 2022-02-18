package com.autolib.helpdesk.Needed.dao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.autolib.helpdesk.Agents.repository.AgentRepository;
import com.autolib.helpdesk.Agents.repository.ProductsRepository;
import com.autolib.helpdesk.Institutes.repository.InstituteRepository;
import com.autolib.helpdesk.LeadManagement.repository.LeadRepository;
import com.autolib.helpdesk.Teams.repository.Tasks.TaskRepository;
import com.autolib.helpdesk.common.Util;

@Repository
public class NeededDAOImpl implements NeededDAO {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private AgentRepository agentRepo;
	@Autowired
	private ProductsRepository prodRepo;
	@Autowired
	private TaskRepository taskRepo;
	@Autowired
	private InstituteRepository instRepo;
	@Autowired
	private LeadRepository leadRepo;

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getNeeded(Map<String, Object> req) {
		logger.info("getNeeded DAO Starts:::");
		Map<String, Object> resp = new HashMap<>();
		try {

			List<String> needed = (List<String>) req.get("needed");

			if (needed.contains("agents_min")) {
				resp.put("agents_min", agentRepo.findAllMinDetails());
			}

			if (needed.contains("products_min")) {
				resp.put("products_min", prodRepo.findAllMinDetails());
			}

			if (needed.contains("products_category")) {
				resp.put("products_category", prodRepo.findDistinctCategory());
			}

			if (needed.contains("tasks_labels_distinct")) {
				resp.put("tasks_labels_distinct", taskRepo.findDistinctLabel());
			}

			if (needed.contains("institutes_min")) {
				resp.put("institutes_min", instRepo.getInstituteMimDetails());
			}

			if (needed.contains("lead_companies")) {
				resp.put("lead_companies", leadRepo.findDistinctCompanies());
			}

			if (needed.contains("lead_products")) {
				Set<String> prods = new HashSet<>();

				leadRepo.findDistinctProducts().forEach(prod -> {
					prods.addAll(Arrays.asList(prod.split(";")));
				});

				resp.put("lead_products", prods);
			}

			if (needed.contains("lead_industry_type")) {
				resp.put("lead_industry_type", leadRepo.findDistinctIndustryType());
			}

			if (needed.contains("lead_sources")) {
				resp.put("lead_sources", leadRepo.findDistinctLeadSources());
			}

			if (needed.contains("lead_status")) {
				resp.put("lead_status", leadRepo.findDistinctLeadStatus());
			}

			resp.putAll(Util.SuccessResponse());
		} catch (Exception e) {
			resp.putAll(Util.FailedResponse(e.getMessage()));
			e.printStackTrace();
		}
		logger.info("getNeeded DAO Ends:::");
		return resp;
	}

}
