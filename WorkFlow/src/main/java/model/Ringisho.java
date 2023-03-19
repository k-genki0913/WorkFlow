package model;

import java.io.Serializable;

public class Ringisho implements Serializable{
	private int formNo;
	private String applicantName;
	private int departmentID;
	private String contents;
	private int manager;
	private int gManager;
	
	public Ringisho() {}
	public Ringisho(int formNo, String applicantName, int departmentID, String contents, int manager, int gManager) {
		this.formNo = formNo;
		this.applicantName = applicantName;
		this.departmentID = departmentID;
		this.contents = contents;
		this.manager = manager;
		this.gManager = gManager;
	}
	
	public int getFormNo() {
		return this.formNo;
	}
	
	public void setFormNo(int no) {
		this.formNo = no;
	}
	
	public String getApplicantName() {
		return this.applicantName;
	}
	
	public void setApplicantName(String name) {
		this.applicantName = name;
	}
	
	public int getDepartment() {
		return this.departmentID;
	}
	
	public void setDepartment(int departmentID) {
		this.departmentID = departmentID;
	}
	
	public String getContents() {
		return this.contents;
	}
	
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public int getManager() {
		return this.manager;
	}
	
	public void setManager(int manager) {
		this.manager = manager;
	}
	
	public int getGManager() {
		return this.gManager;
	}
	
	public void setGManager(int gManager) {
		this.gManager = gManager;
	}

}
