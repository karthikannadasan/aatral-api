package com.autolib.helpdesk.LeadManagement.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autolib.helpdesk.LeadManagement.model.LeadMailSentSearchRequest;
import com.autolib.helpdesk.LeadManagement.model.LeadMailTemplate;
import com.autolib.helpdesk.LeadManagement.model.LeadMailTemplateSearchRequest;
import com.autolib.helpdesk.LeadManagement.service.LeadMailService;
import com.autolib.helpdesk.common.Util;
import com.autolib.helpdesk.jwt.JwtTokenUtil;

@Controller
@RestController
@CrossOrigin("*")
@RequestMapping("lead-mail")
public class LeadMailController {
	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	JwtTokenUtil jwtUtil;

	@Autowired
	LeadMailService leadMailService;

	@PostMapping("save-lead-mail-template")
	public ResponseEntity<?> saveLeadMailTemplate(@RequestHeader(value = "Authorization") String token,
			@RequestBody LeadMailTemplate template) {

		logger.info("saveLeadMailTemplate starts:::" + template);
		jwtUtil.isValidToken(token);
		Map<String, Object> resp = new HashMap<>();

		try {

			resp = leadMailService.saveLeadMailTemplate(template);

		} catch (Exception e) {
			resp.putAll(Util.FailedResponse());
			e.printStackTrace();
		}

		logger.info("saveLeadMailTemplate ends:::");
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	@GetMapping("get-lead-mail-template/{templateId}")
	public ResponseEntity<?> getLeadMailTemplate(@RequestHeader(value = "Authorization") String token,
			@PathVariable("templateId") int templateId) {

		logger.info("getLeadMailTemplate starts:::" + templateId);
		jwtUtil.isValidToken(token);
		Map<String, Object> resp = new HashMap<>();

		try {

			resp = leadMailService.getLeadMailTemplate(templateId);

		} catch (Exception e) {
			resp.putAll(Util.FailedResponse());
			e.printStackTrace();
		}

		logger.info("getLeadMailTemplate ends:::");
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	@PostMapping("delete-lead-mail-template")
	public ResponseEntity<?> deleteLeadMailTemplate(@RequestHeader(value = "Authorization") String token,
			@RequestBody LeadMailTemplate template) {

		logger.info("deleteLeadMailTemplate starts:::" + template);
		jwtUtil.isValidToken(token);
		Map<String, Object> resp = new HashMap<>();

		try {

			resp = leadMailService.deleteLeadMailTemplate(template);

		} catch (Exception e) {
			resp.putAll(Util.FailedResponse());
			e.printStackTrace();
		}

		logger.info("deleteLeadMailTemplate ends:::");
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	@PostMapping("search-lead-mail-templates")
	public ResponseEntity<?> searchLeadMailTemplates(@RequestHeader(value = "Authorization") String token,
			@RequestBody LeadMailTemplateSearchRequest request) {

		logger.info("searchLeads starts:::" + request);
		jwtUtil.isValidToken(token);
		Map<String, Object> resp = new HashMap<>();

		try {

			resp = leadMailService.searchLeadMailTemplates(request);

		} catch (Exception e) {
			resp.putAll(Util.FailedResponse());
			e.printStackTrace();
		}

		logger.info("searchLeads ends:::");
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	@PostMapping("search-lead-mail-sent-status")
	public ResponseEntity<?> searchLeadMailSentStatus(@RequestHeader(value = "Authorization") String token,
			@RequestBody LeadMailSentSearchRequest request) {

		logger.info("searchLeadMailSentStatus starts:::" + request);
		jwtUtil.isValidToken(token);
		Map<String, Object> resp = new HashMap<>();

		try {

			resp = leadMailService.searchLeadMailSentStatus(request);

		} catch (Exception e) {
			resp.putAll(Util.FailedResponse());
			e.printStackTrace();
		}

		logger.info("searchLeadMailSentStatus ends:::");
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

}
