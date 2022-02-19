package com.autolib.helpdesk.Returnable.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autolib.helpdesk.Returnable.entity.ReturnableRequest;
import com.autolib.helpdesk.Returnable.entity.ReturnableSearchRequest;
import com.autolib.helpdesk.Returnable.service.ReturnableService;
import com.autolib.helpdesk.jwt.JwtTokenUtil;

@RestController
@RequestMapping("returnables")
@CrossOrigin
public class ReturnableController {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	ReturnableService returnableService;

	@Autowired
	JwtTokenUtil jwtUtil;

	@PostMapping("save-returnable")
	public ResponseEntity<?> saveReturnable(@RequestHeader(value = "Authorization") String token,
			@RequestBody ReturnableRequest request) throws Exception {
		logger.info("getHomePageDetails starts:::" + request);
		jwtUtil.isValidToken(token);
		Map<String, Object> resp = new HashMap<String, Object>();
		try {
			resp = returnableService.saveReturnable(request);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		logger.info("getHomePageDetails ends:::");
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	@GetMapping("get-returnable/{returnableId}")
	public ResponseEntity<?> getReturnable(@RequestHeader(value = "Authorization") String token,
			@PathVariable("returnableId") int returnableId) throws Exception {
		logger.info("getReturnable starts:::" + returnableId);
		jwtUtil.isValidToken(token);
		Map<String, Object> resp = new HashMap<String, Object>();
		try {
			resp = returnableService.getReturnable(returnableId);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		logger.info("getAllAgentTickets ends:::");
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	@PostMapping("delete-returnable")
	public ResponseEntity<?> deleteReturnable(@RequestHeader(value = "Authorization") String token,
			@RequestBody ReturnableRequest request) throws Exception {
		logger.info("deleteReturnable starts:::" + request);
		jwtUtil.isValidToken(token);
		Map<String, Object> resp = new HashMap<String, Object>();
		try {
			resp = returnableService.deleteReturnable(request);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		logger.info("deleteReturnable ends:::");
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	@PostMapping("search-returnables")
	public ResponseEntity<?> searchReturnable(@RequestHeader(value = "Authorization") String token,
			@RequestBody ReturnableSearchRequest request) throws Exception {
		logger.info("searchReturnable starts:::" + request);
		jwtUtil.isValidToken(token);
		Map<String, Object> resp = new HashMap<String, Object>();
		try {
			resp = returnableService.searchReturnable(request);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		logger.info("searchReturnable ends:::");
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

}
