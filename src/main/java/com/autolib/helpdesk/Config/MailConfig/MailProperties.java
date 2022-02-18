package com.autolib.helpdesk.Config.MailConfig;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mail_properties")
public class MailProperties {

	public MailProperties() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column
	private String username;

	@Column
	private String password;

	@Column
	private String host;

	@Column
	private int port;

	@Column
	private String protocol;

	@Column
	private String configFor;

	@Column(nullable = false)
	private boolean active;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getConfigFor() {
		return configFor;
	}

	public void setConfigFor(String configFor) {
		this.configFor = configFor;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "MailProperties [id=" + id + ", username=" + username + ", password=" + password + ", host=" + host
				+ ", port=" + port + ", protocol=" + protocol + ", configFor=" + configFor + ", active=" + active + "]";
	}

}
