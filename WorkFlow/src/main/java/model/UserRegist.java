package model;

import java.io.Serializable;

public class UserRegist implements Serializable{
	private int formID;
	private String document;
	private String documentTable;
	private String applicantName;
	private int departmentID;
	private int situation;
	private String mApprover;
	private String gmApprover;
	private String registName;
	private int registDepartment;
	private int registPosition;
	
	public UserRegist() {}
	public UserRegist(int formID, String document, String documentTable, String applicantName, int departmentID, int situation, String mApprover, String gmApprover, String registName, int registDepartment, int registPosition) {
		this.formID = formID;
		this.document = document;
		this.documentTable = documentTable;
		this.applicantName = applicantName;
		this.departmentID = departmentID;
		this.situation = situation;
		this.mApprover = mApprover;
		this.gmApprover = gmApprover;
		this.registName = registName;
		this.registDepartment = registDepartment;
		this.registPosition = registPosition;
	}
	
	public int getFormID() {
		return this.formID;
	}
	
	public String getDocument() {
		return this.document;
	}
	
	public String getDocumentTable() {
		return this.documentTable;
	}
	
	public String getApplicantName() {
		return this.applicantName;
	}
	
	public int getDepartmentID() {
		return this.departmentID;
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
	
	public String getRegistName() {
		return this.registName;
	}
	
	public int getRegistDepartment() {
		return this.registDepartment;
	}
	
	public int getRegistPosition() {
		return this.registPosition;
	}
}
