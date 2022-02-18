package com.autolib.helpdesk.Tickets.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autolib.helpdesk.common.Util;
import com.autolib.helpdesk.jwt.JwtTokenUtil;
import com.autolib.helpdesk.schedulers.model.GoogleMailAsTicket;
import com.autolib.helpdesk.schedulers.repository.GoogleMailAsTicketRepository;

@RequestMapping("gmail-ticket")
@RestController
@CrossOrigin("*")
public class GmailTicketController {

	private final Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	GoogleMailAsTicketRepository gmailticketService;

	@Autowired
	JwtTokenUtil jwtUtil;

	@PostMapping("get-all-gmails")
	public ResponseEntity<?> getAllProducts(@RequestHeader(value = "Authorization") String token) throws Exception {
		logger.info("getAllProducts starts:::");
		jwtUtil.isValidToken(token);
		Map<String, Object> resp = new HashMap<String, Object>();
		List<GoogleMailAsTicket> gmails = new ArrayList<>();
		try {
			gmails = gmailticketService.findAll();
			resp.putAll(Util.SuccessResponse());
		} catch (Exception ex) {
			resp.putAll(Util.FailedResponse(ex.getMessage()));
			ex.printStackTrace();
		}
		resp.put("gmails", gmails);
		logger.info("getAllProducts ends:::");
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}
}
