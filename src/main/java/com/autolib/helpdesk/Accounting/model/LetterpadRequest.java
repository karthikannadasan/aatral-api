package com.autolib.helpdesk.Accounting.model;

import com.autolib.helpdesk.Tickets.model.CallReport;

public class LetterpadRequest {
	
	private int id;
	
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LetterPad getLetterpad() {
		return letterpad;
	}

	public void setLetterpad(LetterPad letterpad) {
		this.letterpad = letterpad;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public boolean isAddRoundSeal() {
		return addRoundSeal;
	}

	public void setAddRoundSeal(boolean addRoundSeal) {
		this.addRoundSeal = addRoundSeal;
	}

	public boolean isAddFullSeal() {
		return addFullSeal;
	}

	public void setAddFullSeal(boolean addFullSeal) {
		this.addFullSeal = addFullSeal;
	}

	public boolean isAddSign() {
		return addSign;
	}

	public void setAddSign(boolean addSign) {
		this.addSign = addSign;
	}

	public String getSignatureBy() {
		return signatureBy;
	}

	public void setSignatureBy(String signatureBy) {
		this.signatureBy = signatureBy;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getFileAsBase64() {
		return fileAsBase64;
	}

	public void setFileAsBase64(String fileAsBase64) {
		this.fileAsBase64 = fileAsBase64;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getReceiptContent() {
		return receiptContent;
	}

	public void setReceiptContent(String receiptContent) {
		this.receiptContent = receiptContent;
	}

	private LetterPad letterpad;
	
	private String templateName = "";
	
	
	private boolean addRoundSeal = false;
	 
	private boolean addFullSeal = false;
	 
	private boolean addSign = false;
	
	private String signatureBy = "";
	
	private String designation = "";
	
	private String fileAsBase64 = "";
	
	private String filename = "";
	
	private String receiptContent = "";

	private boolean headerLabel = false;
	
	public boolean isHeaderLabel() {
		return headerLabel;
	}

	public void setHeaderLabel(boolean headerLabel) {
		this.headerLabel = headerLabel;
	}

	public boolean isCmpLogo() {
		return cmpLogo;
	}

	public void setCmpLogo(boolean cmpLogo) {
		this.cmpLogo = cmpLogo;
	}

	private boolean cmpLogo = false;

	@Override
	public String toString() {
		return "LetterpadRequest [id=" + id + ", letterpad=" + letterpad + ", templateName=" + templateName
				+ ", addRoundSeal=" + addRoundSeal + ", addFullSeal=" + addFullSeal + ", addSign=" + addSign
				+ ", signatureBy=" + signatureBy + ", designation=" + designation + ", fileAsBase64=" + fileAsBase64
				+ ", filename=" + filename + ", receiptContent=" + receiptContent + ", headerLabel=" + headerLabel
				+ ", cmpLogo=" + cmpLogo + "]";
	}
	
	

}
