package com.autolib.helpdesk.schedulers.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autolib.helpdesk.LeadManagement.model.Lead;
import com.autolib.helpdesk.LeadManagement.model.LeadMailTemplate;
import com.autolib.helpdesk.LeadManagement.repository.LeadMailTemplateRepository;
import com.autolib.helpdesk.LeadManagement.repository.LeadRepository;
import com.autolib.helpdesk.common.EmailModel;
import com.autolib.helpdesk.common.EmailSender;
import com.autolib.helpdesk.common.Util;
import com.autolib.helpdesk.schedulers.model.LeadMailSentStatus;
import com.autolib.helpdesk.schedulers.repository.LeadMailSentStatusRepository;

@RestController
public class SendLeadMailsScheduler {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	LeadMailTemplateRepository mailTempRepo;

	@Autowired
	LeadRepository leadRepo;

	@Autowired
	LeadMailSentStatusRepository mailStatusRepo;

	@Autowired
	private EmailSender emailSender;

	@GetMapping("SendLeadMailsScheduler")
	@Scheduled(cron = "0 3 3 * * *")
	void execute() {

		logger.info("SendLeadMailsSchedulerDaily starts:::");
		Map<String, Object> resp = new HashMap<>();
		List<LeadMailTemplate> templates = new ArrayList<>();

		List<LeadMailSentStatus> mailsSentStatus = new ArrayList<>();

		try {

			Calendar calendar = Calendar.getInstance();

			int day = calendar.get(Calendar.DAY_OF_WEEK);
			int month = calendar.get(Calendar.MONTH) + 1;

			System.out.println(day);
			System.out.println(month + "::" + String.valueOf(month % 2 == 0));

			System.out.println(LocalDate.now().getDayOfWeek().name());
			System.out.println(calendar.getFirstDayOfWeek());

			List<String> frequencies = new ArrayList<>();

			frequencies.add("Daily");

			if (LocalDate.now().getDayOfWeek().name().equalsIgnoreCase("MONDAY")) {
				frequencies.add("Weekly");
			}
			if (calendar.get(Calendar.DAY_OF_MONTH) == 1 || calendar.get(Calendar.DAY_OF_MONTH) == 15) {
				frequencies.add("Bi-Weekly");
			}
			if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
				frequencies.add("Monthly");
			}
			if (month % 2 == 0 && calendar.get(Calendar.DAY_OF_MONTH) == 1) {
				frequencies.add("Bi-Monthly");
			}

			System.out.println(frequencies);

			templates = mailTempRepo.findByFrequencyInAndEnabled(frequencies, true);

			templates.forEach(template -> System.out.println(template));

			templates.stream().forEach(_template -> {

				List<Lead> leads = leadRepo.findByStatus(_template.getStatus());

				leads.forEach(lead -> System.out.println(lead));

				if (leads.size() > 0) {

					leads.stream().filter(_lead -> {
						return Util.validateEmailID(_lead.getEmail())
								|| Util.validateEmailID(_lead.getAlternateEmail());
					}).filter(_lead -> _lead.isSendEmailUpdates()).forEach(_lead -> {

						mailsSentStatus.add(sendMail(_lead, _template));

					});
				}

			});

			mailStatusRepo.saveAll(mailsSentStatus);

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("SendLeadMailsScheduler ends:::" + resp);
	}

	LeadMailSentStatus sendMail(Lead _lead, LeadMailTemplate template) {
		LeadMailSentStatus mailSent = new LeadMailSentStatus();
		try {

			EmailModel emailModel = new EmailModel("Leads");

			if (Util.validateEmailID(_lead.getEmail())) {
				emailModel.setMailTo(_lead.getEmail());
				emailModel.setMailList(new String[] { _lead.getAlternateEmail() });
			} else if (Util.validateEmailID(_lead.getAlternateEmail())) {
				emailModel.setMailTo(_lead.getAlternateEmail());
			}

			emailModel.setMailSub(template.getSubject());

			emailModel.setMailText(template.getMessage());

			int i = emailSender.sendmail(emailModel);

			if (i == 1) {
				mailSent.setLeadId(_lead.getId());
				mailSent.setTemplateId(template.getId());
				mailSent.setMailTo(emailModel.getMailTo());
				mailSent.setMailCC(Arrays.stream(emailModel.getMailList()).collect(Collectors.joining(";")));
				mailSent.setSubject(emailModel.getMailSub());
				mailSent.setMessage(emailModel.getMailText());
				mailSent.setStatus("Sent");
			} else if (i == 2) {
				mailSent.setLeadId(_lead.getId());
				mailSent.setTemplateId(template.getId());
				mailSent.setMailTo(emailModel.getMailTo());
				mailSent.setMailCC(Arrays.stream(emailModel.getMailList()).collect(Collectors.joining(";")));
				mailSent.setSubject(emailModel.getMailSub());
				mailSent.setMessage(emailModel.getMailText());
				mailSent.setStatus("JavaMailSender/MailProperties not Found");
			} else if (i == 0) {
				mailSent.setLeadId(_lead.getId());
				mailSent.setTemplateId(template.getId());
				mailSent.setMailTo(emailModel.getMailTo());
				mailSent.setMailCC(Arrays.stream(emailModel.getMailList()).collect(Collectors.joining(";")));
				mailSent.setSubject(emailModel.getMailSub());
				mailSent.setMessage(emailModel.getMailText());
				mailSent.setStatus("Exception Occured");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mailSent;
	}

}
