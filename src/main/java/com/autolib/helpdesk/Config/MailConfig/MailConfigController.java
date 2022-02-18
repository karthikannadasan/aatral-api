package com.autolib.helpdesk.Config.MailConfig;

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

import com.autolib.helpdesk.common.Util;
import com.autolib.helpdesk.jwt.JwtTokenUtil;

@Controller
@RestController
@CrossOrigin("*")
@RequestMapping("mail")
public class MailConfigController {
	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	JwtTokenUtil jwtUtil;

	@Autowired
	private MailSenderConfigurations mailServ;

	@GetMapping("get-mail-properties/{configFor}")
	public ResponseEntity<?> getMailProperties(@RequestHeader(value = "Authorization") String token,
			@PathVariable("configFor") String configFor) throws Exception {
		logger.info("getMailProperties Starts::::::::::" + configFor);
		jwtUtil.isValidToken(token);
		Map<String, Object> resp = new HashMap<String, Object>();
		try {
			if (configFor != null && !configFor.isEmpty()) {
				resp.put("MailProperties", mailServ.getProperties(configFor));
				resp.putAll(Util.SuccessResponse());
			} else {
				resp.put("MailProperties", null);
				resp.putAll(Util.invalidMessage("'configFor' can't be empty"));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		logger.info("getMailProperties Ends::::::::::");
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	@PostMapping("save-mail-properties")
	public ResponseEntity<?> saveMailProperties(@RequestHeader(value = "Authorization") String token,
			@RequestBody MailProperties property) throws Exception {
		logger.info("saveMailProperties Starts::::::::::" + property);
		jwtUtil.isValidToken(token);
		Map<String, Object> resp = new HashMap<String, Object>();
		try {
			resp = mailServ.saveMailProperties(property);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		logger.info("saveMailProperties Ends::::::::::");
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	@GetMapping("sendMail/{configFor}")
	public ResponseEntity<?> sendMail(@PathVariable("configFor") String configFor) throws Exception {
		logger.info("sendMail Starts::::::::::" + configFor);
		Map<String, Object> resp = new HashMap<String, Object>();
		try {
			mailServ.sendMail(configFor);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		logger.info("sendMail Ends::::::::::");
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

}
