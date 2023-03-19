package model;

import java.io.Serializable;

public class Ringisho implements Serializable{
	private int formID;
	private String document;
	private String applicantName;
	private int departmentID;
	private int situation;
	private String mApprover;
	private String gmApprover;
	private String contents;
	
	public Ringisho() {}
	public Ringisho(int formID, String document, String applicantName, int departmentID, int situation, String mApprover, String gmApprover, String contents) {
		this.formID = formID;
		this.document = document;
		this.applicantName = applicantName;
		this.departmentID = departmentID;
		this.situation = situation;
		this.mApprover = mApprover;
		this.gmApprover = gmApprover;
		this.contents = contents;
	}
	
	public int getFormID() {
		return this.formID;
	}
	
	public void setFormID(int formID) {
		this.formID = formID;
	}
	
	public String getDocument() {
		return this.document;
	}
	
	public void setDocument(String document) {
		this.document = document;
	}
	
	public String getApplicantName() {
		return this.applicantName;
	}
	
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	
	public int getDepartmentID() {
		return this.departmentID;
	}
	
	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
	}
	
	public int getSituation() {
		return this.situation;
	}
	
	public void setSituation(int situation) {
		this.situation = situation;
	}
	
	public String getMApprover() {
		return this.mApprover;
	}
	
	public void setMApprover(String mApprover) {
		this.mApprover = mApprover;
	}
	
	public String getGMApprover() {
		return this.gmApprover;
	}
	
	public void setGMApprover(String gmApprover) {
		this.gmApprover = gmApprover;
	}
	
	public String getContents() {
		return this.contents;
	}
	
	public void setContents(String contents) {
		this.contents = contents;
	}
	

}
