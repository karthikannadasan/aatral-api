package com.autolib.helpdesk.Tickets.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="call_reports")
public class CallReport {
	
	public CallReport() {
		super();
	}


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;
	
	@Column(name="institute_id")
	private String instituteId;
	
	@Column(name="in_time")
	private Date reportingInTime;
	
	@Column(name="out_time")
	private Date reportingOutTime;
	
	@Column(name="problem_reported")
	private String problemsReported;
	
	@Column(name="action_taken")
	private String actionTaken;
	
	@Column(name="customer_remarks")
	private String customerRemarks;
	
	@Column(name="follow_up_action")
	private String followUpAction;
	
	@Column(name="description_name_supplied")
	private String descriptionOfNameSupplied;
	
	@Column(name="customer_name")
	private String customerName;
	
	@Column(name ="file_name")
	private String fileName;
	
	@Column(name ="file_size")
	private long fileSize;
	
	@Column(name="file_type" ,length=128)
	private String fileType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInstituteId() {
		return instituteId;
	}

	public void setInstituteId(String instituteId) {
		this.instituteId = instituteId;
	}

	public Date getReportingInTime() {
		return reportingInTime;
	}

	public void setReportingInTime(Date reportingInTime) {
		this.reportingInTime = reportingInTime;
	}

	public Date getReportingOutTime() {
		return reportingOutTime;
	}

	public void setReportingOutTime(Date reportingOutTime) {
		this.reportingOutTime = reportingOutTime;
	}

	public String getProblemsReported() {
		return problemsReported;
	}

	public void setProblemsReported(String problemsReported) {
		this.problemsReported = problemsReported;
	}

	public String getActionTaken() {
		return actionTaken;
	}

	public void setActionTaken(String actionTaken) {
		this.actionTaken = actionTaken;
	}

	public String getCustomerRemarks() {
		return customerRemarks;
	}

	public void setCustomerRemarks(String customerRemarks) {
		this.customerRemarks = customerRemarks;
	}

	public String getFollowUpAction() {
		return followUpAction;
	}

	public void setFollowUpAction(String followUpAction) {
		this.followUpAction = followUpAction;
	}

	public String getDescriptionOfNameSupplied() {
		return descriptionOfNameSupplied;
	}

	public void setDescriptionOfNameSupplied(String descriptionOfNameSupplied) {
		this.descriptionOfNameSupplied = descriptionOfNameSupplied;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	@Override
	public String toString() {
		return "CallReport [id=" + id + ", instituteId=" + instituteId + ", reportingInTime=" + reportingInTime
				+ ", reportingOutTime=" + reportingOutTime + ", problemsReported=" + problemsReported + ", actionTaken="
				+ actionTaken + ", customerRemarks=" + customerRemarks + ", followUpAction=" + followUpAction
				+ ", descriptionOfNameSupplied=" + descriptionOfNameSupplied + ", customerName=" + customerName
				+ ", fileName=" + fileName + ", fileSize=" + fileSize + ", fileType=" + fileType + "]";
	}
	
	
	
}
