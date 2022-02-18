package com.autolib.helpdesk.Config.MailConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.mail.Message;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import com.autolib.helpdesk.common.Util;

@Component
public class MailSenderConfigurations {

	private final String COMMON_CONFIG = "Common";
	private final String SALES_CONFIG = "Sales";
	private final String TICKETS_CONFIG = "Tickets";
	private final String LEADS_CONFIG = "Leads";

	private Map<String, JavaMailSenderImpl> senderList = new HashMap<>();

	private List<MailProperties> properties = new ArrayList<>();

	@Autowired
	MailPropertiesRepository mailPropRepo;

	/**
	 * Initialize sender
	 */
	@PostConstruct
	public void buildMailSender() {
		System.out.println("JavaMailSendersInitialization");

		senderList = new HashMap<>();

		properties = mailPropRepo.findAll();

		prepareDeafaultMailProperties(properties);

		properties.stream().filter(prop -> prop.isActive()).forEach(mailProperties -> {

			System.out.println(mailProperties);

			// Email Sender
			JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
			javaMailSender.setHost(mailProperties.getHost());
			javaMailSender.setPort(mailProperties.getPort());
			javaMailSender.setProtocol(mailProperties.getProtocol());
			javaMailSender.setUsername(mailProperties.getUsername());
			javaMailSender.setPassword(mailProperties.getPassword());

			Properties props = javaMailSender.getJavaMailProperties();
			props.put("mail.transport.protocol", mailProperties.getProtocol());
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");

			System.out.println(javaMailSender);

			// Add javaMailSender
			senderList.put(mailProperties.getConfigFor(), javaMailSender);

		});

		System.out.println(senderList);
	}

	void prepareDeafaultMailProperties(List<MailProperties> properties) {
		try {

			MailProperties common_property = properties.stream()
					.filter(prop -> prop.getConfigFor().equalsIgnoreCase(COMMON_CONFIG)).findFirst().orElse(null);

			if (common_property == null) {
				common_property = new MailProperties();
				common_property.setActive(false);
				common_property.setConfigFor(COMMON_CONFIG);
				common_property.setHost("smtp.gmail.com");
				common_property.setPort(587);
				common_property.setProtocol("smtp");

				common_property = mailPropRepo.save(common_property);

				properties.add(common_property);

			}

			MailProperties tickets_property = properties.stream()
					.filter(prop -> prop.getConfigFor().equalsIgnoreCase(TICKETS_CONFIG)).findFirst().orElse(null);

			if (tickets_property == null) {
				tickets_property = new MailProperties();
				tickets_property.setActive(false);
				tickets_property.setConfigFor(TICKETS_CONFIG);
				tickets_property.setHost("smtp.gmail.com");
				tickets_property.setPort(587);
				tickets_property.setProtocol("smtp");

				tickets_property = mailPropRepo.save(tickets_property);

				properties.add(tickets_property);

			}

			MailProperties sales_property = properties.stream()
					.filter(prop -> prop.getConfigFor().equalsIgnoreCase(SALES_CONFIG)).findFirst().orElse(null);

			if (sales_property == null) {
				sales_property = new MailProperties();
				sales_property.setActive(false);
				sales_property.setConfigFor(SALES_CONFIG);
				sales_property.setHost("smtp.gmail.com");
				sales_property.setPort(587);
				sales_property.setProtocol("smtp");

				sales_property = mailPropRepo.save(sales_property);

				properties.add(sales_property);

			}

			MailProperties leads_property = properties.stream()
					.filter(prop -> prop.getConfigFor().equalsIgnoreCase(LEADS_CONFIG)).findFirst().orElse(null);

			if (leads_property == null) {
				leads_property = new MailProperties();
				leads_property.setActive(false);
				leads_property.setConfigFor(LEADS_CONFIG);
				leads_property.setHost("smtp.gmail.com");
				leads_property.setPort(587);
				leads_property.setProtocol("smtp");

				leads_property = mailPropRepo.save(leads_property);

				properties.add(leads_property);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get MailSender
	 * 
	 * @return CustomMailSender
	 */
	public JavaMailSenderImpl getSender(String configFor) {

		JavaMailSenderImpl sender = null;

		if (configFor == null || configFor.isEmpty()) {
			sender = senderList.getOrDefault(COMMON_CONFIG, null);
		} else {
			sender = senderList.getOrDefault(configFor, null);
		}

		return sender;

	}

	/**
	 * Get MailProperties
	 * 
	 * @return CustomMailSender
	 */
	public MailProperties getProperties(String configFor) {

		if (configFor == null || configFor.isEmpty()) {
			return this.properties.stream().filter(prop -> prop.getConfigFor().equalsIgnoreCase(COMMON_CONFIG))
					.findFirst().orElse(null);
		} else {
			return this.properties.stream().filter(prop -> prop.getConfigFor().equalsIgnoreCase(configFor)).findFirst()
					.orElse(null);
		}

	}

	public void sendMail(String configFor) {

		JavaMailSender javaMailSender = senderList.getOrDefault(configFor, null);

		if (javaMailSender != null) {
			MimeMessage msg = javaMailSender.createMimeMessage();

			try {
				msg.setRecipient(Message.RecipientType.TO, new InternetAddress("kannadasantnp@gmail.com"));
				msg.setSubject("Hi");
				msg.setContent("Hi", "text/html; charset=utf-8");
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			javaMailSender.send(msg);
		} else {

		}

	}

	public Map<String, Object> saveMailProperties(MailProperties property) {

		Map<String, Object> resp = new HashMap<>();

		try {

			property = mailPropRepo.save(property);

			buildMailSender();
			resp.putAll(Util.SuccessResponse());

		} catch (Exception e) {
			resp.putAll(Util.FailedResponse());
			e.printStackTrace();
		}

		resp.put("MailProperties", property);
		return resp;
	}

}
