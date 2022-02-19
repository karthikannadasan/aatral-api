package com.autolib.helpdesk.Accounting.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="letterpad")
public class LetterPad {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;
	
	
	@Lob
	@Column(name = "to_address")
	private String toAddress ="";
	
	
	@Lob
	@Column(name = "content")
	private String content = "";
	
	@Column(name = "subject")
	private String subject="";
	
	
	@Lob
	@Column(name = "regard_text")
	private String regardText="";
	

	@Column(name = "date")
	@Temporal(TemporalType.DATE)
	private Date letterPadDate;
	
	
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

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getRegardText() {
		return regardText;
	}

	public void setRegardText(String regardText) {
		this.regardText = regardText;
	}

	public Date getLetterPadDate() {
		return letterPadDate;
	}

	public void setLetterPadDate(Date letterPadDate) {
		this.letterPadDate = letterPadDate;
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
		return "LetterPad [id=" + id + ", toAddress=" + toAddress + ", content=" + content + ", subject=" + subject
				+ ", regardText=" + regardText + ", letterPadDate=" + letterPadDate + ", fileName=" + fileName
				+ ", fileSize=" + fileSize + ", fileType=" + fileType + "]";
	}


		
}
