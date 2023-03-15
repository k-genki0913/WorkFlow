package model;

import java.io.Serializable;

public class Account implements Serializable{
	private String id;
	private String password;
	private String name;
	private int department;
	private int position;
	
	public Account() {}
	public Account(String id, String password, String name, int department, int position) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.department = department;
		this.position = position;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getDepartment() {
		return this.department;
	}
	
	public void setDepartment(int department) {
		this.department = department;
	}
	
	public int getPosition() {
		return this.position;
	}
	
	public void setPosition(int position) {
		this.position = position;
	}
	
}
