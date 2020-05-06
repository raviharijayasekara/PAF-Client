package com.healthcare.controller;

import java.util.List;

import com.healthcare.model.Patient;

public interface IPatientController {

	public String registerPatient(Patient patient);
	
	public List<String> getPatientIDs();

//	public String getPatientDetailById(int patientId);
	
	public String getAllPatients();
	
	//public String updatePatientDetails(Patient patient);
	public String updatePatientDetails(String pid, String fName, String lName, String gender, String nic, String dob, String email,
			String phone, String bloodGroup, String allergies);


	public String deletePatient(String pid);
//	


	//String updatePatientEmail(Patient patient);
	
}