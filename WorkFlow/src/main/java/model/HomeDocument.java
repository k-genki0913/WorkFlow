package model;

import java.io.Serializable;

public class HomeDocument implements Serializable{
	private int formID;
	private String documentName;
	private String applicantName;
	
	public HomeDocument() {}
	
	public HomeDocument(int formID, String documentName, String applicantName) {
		this.formID = formID;
		this.documentName = documentName;
		this.applicantName = applicantName;
	}
	
	public int getFormID() {
		return this.formID;
	}
	
	public void setFormID(int formID) {
		this.formID = formID;
	}
	
	public String getDocumentName() {
		return this.documentName;
	}
	
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	
	public String getApplicantName() {
		return this.applicantName;
	}
	
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
}
