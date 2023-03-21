package model;

import java.io.Serializable;

public class HomeDocument implements Serializable{
	private int formID;
	private String document;
	private String documentTable;
	private String applicantName;
	
	public HomeDocument() {}
	
	public HomeDocument(int formID,String document, String documentTable, String applicantName) {
		this.formID = formID;
		this.document = document;
		this.documentTable = documentTable;
		this.applicantName = applicantName;
	}
	
	public int getFormID() {
		return this.formID;
	}
	
	public void setFormID(int formID) {
		this.formID = formID;
	}
	
	public String getDocumentTable() {
		return this.documentTable;
	}
	
	public String getDocumentName() {
		return this.document;
	}
	
	public void setDocumentName(String documentName) {
		this.document = documentName;
	}
	
	
	public String getApplicantName() {
		return this.applicantName;
	}
	
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
}
