package com.healthcare.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Patient {

	
	private String pid;
	private String fName;
	private String lName;
	private String gender;
	private String nic;
	private String dob;
	private String email;
	private String phone;
	private String bloodGroup;
	private String allergies;
	
	
	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Patient(String pid, String fName, String lName, String gender, String nic, String dob, String email,
			String phone, String bloodGroup, String allergies) {
		super();
		this.pid = pid;
		this.fName = fName;
		this.lName = lName;
		this.gender = gender;
		this.nic = nic;
		this.dob = dob;
		this.email = email;
		this.phone = phone;
		this.bloodGroup = bloodGroup;
		this.allergies = allergies;
	}

	
	public String getPid() {
		return pid;
	}
	
	public void setPid(String pid) {
		this.pid = pid;
	}
	
	public String getfName() {
		return fName;
	}
	
	public void setfName(String fName) {
		this.fName = fName;
	}
	
	public String getlName() {
		return lName;
	}
	
	public void setlName(String lName) {
		this.lName = lName;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getNic() {
		return nic;
	}
	
	public void setNic(String nic) {
		this.nic = nic;
	}
	
	public String getDob() {
		return dob;
	}
	
	public void setDob(String dob) {
		this.dob = dob;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getBloodGroup() {
		return bloodGroup;
	}
	
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	
	public String getAllergies() {
		return allergies;
	}
	
	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

}