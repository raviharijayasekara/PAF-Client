package com.healthcare.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.healthcare.model.Patient;
import com.healthcare.util.Constants;
import com.healthcare.util.DBConnection;
import com.healthcare.util.CommonUtils;
//import com.google.gson.Gson;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonParser;
//import com.google.gson.reflect.TypeToken;
//import com.sun.jersey.api.client.Client;
//import com.sun.jersey.api.client.ClientResponse;
//import com.sun.jersey.api.client.WebResource;


public class PatientController implements IPatientController {

	public static final Logger log = Logger.getLogger(PatientController.class.getName());

	private static Connection con;

	private static PreparedStatement preparedStmt;

	private static Statement st;

	// to insert patient details to the db
	@Override
	public String registerPatient(Patient patient) {

		System.out.println("register");
		String output = "";

		// Here we call the generatePatientIDs method to auto generate a patientId
		/*
		 * String pid = CommonUtils.generatePatientIDs(getPatientIDs());
		 * System.out.println("PatientId : " +pid );
		 */

		try {

			con = DBConnection.getDBConnection();
			String query = "INSERT INTO patient(fName, lName, nic, dob, phone, email, gender, bloodGroup, allergies) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?)";
			
			preparedStmt = con.prepareStatement(query);

//				preparedStmt.(Constants.COLUMN_INDEX_ONE, patient.getPid());
				System.out.println("PatientId :" + patient.getPid());
				preparedStmt.setString(Constants.COLUMN_INDEX_ONE, patient.getfName());
				System.out.println("PatientName :" + patient.getfName());
				preparedStmt.setString(Constants.COLUMN_INDEX_TWO, patient.getlName());
				preparedStmt.setString(Constants.COLUMN_INDEX_THREE, patient.getNic());
				System.out.println("Nic :" + patient.getNic());
				preparedStmt.setString(Constants.COLUMN_INDEX_FOUR, patient.getDob());
				preparedStmt.setString(Constants.COLUMN_INDEX_FIVE, patient.getPhone());
				preparedStmt.setString(Constants.COLUMN_INDEX_SIX, patient.getEmail());
				preparedStmt.setString(Constants.COLUMN_INDEX_SEVEN, patient.getGender());
				preparedStmt.setString(Constants.COLUMN_INDEX_EIGHT, patient.getBloodGroup());
				preparedStmt.setString(Constants.COLUMN_INDEX_NINE, patient.getAllergies());
				preparedStmt.executeUpdate();
			
		
				String newPatient = getAllPatients();

				//output = "{\"status\" : \"success\", \"data\" : \"Successfully registered\"}";
				output = "{\"status\":\"success\", \"data\": \"" + newPatient + "\"}";
			

		} catch (Exception e) {
			
			output = "{\"status\" : \"error\", \"data\" : \"Error while registering to the system..!\"}";
			log.log(Level.SEVERE, e.getMessage());

		} finally {

			try {
				if (preparedStmt != null) {
					preparedStmt.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}

		return output;
	}
	
	// to get all the registerd patients to a arraylist
		@Override
		public ArrayList<String> getPatientIDs() {

			ArrayList<String> patientList = new ArrayList<String>();

			ResultSet rs = null;

			try {
				con = DBConnection.getDBConnection();

				String query = "SELECT pid FROM patient";

				preparedStmt = con.prepareStatement(query);
				rs = preparedStmt.executeQuery();

				while (rs.next()) {

					patientList.add(rs.getString(1));

				}

			} catch (Exception e) {

				log.log(Level.SEVERE, e.getMessage());

			} finally {
				try {
					if (preparedStmt != null) {
						preparedStmt.close();
					}
					if (con != null) {
						con.close();
					}
				} catch (SQLException e) {
					log.log(Level.SEVERE, e.getMessage());
				}
			}
			return patientList;
		}

	// to get details of all the registered patients
	@Override
	public String getAllPatients() {

		String output = "";
		ResultSet rs = null;

		try {
			con = DBConnection.getDBConnection();

			String query = "SELECT * FROM patient";

			st = con.createStatement();
			rs = st.executeQuery(query);

			output = "<table class = \"table table-striped table-responsive\" style=\"width:120%; margin-left: -40px\">" +
					 "<tr style=\"background-color:#000099; color:#ffffff;\"><th>Patient Id</th>" + "<th>First Name</th>" + "<th>Last Name</th>" +
					 "<th>Gender</th>" + "<th>NIC</th>" + "<th>DOB</th>" + "<th>Blood Group</th>" +
					 "<th>Email</th>" + "<th>Phone</th>" + "<th>Allergies</th>" + "<th>Update</th>" + "<th>Remove</th></tr>";

			while (rs.next()) {

				int pid = rs.getInt("pid");
				String fName = rs.getString("fName");
				String lName = rs.getString("lName");
				String nic = rs.getString("nic");
				String dob = rs.getString("dob");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				String gender = rs.getString("gender");				
				String bloodGroup = rs.getString("bloodGroup");
				String allergies = rs.getString("allergies");
		
				System.out.println("GetAllAPtient : pid : " + pid);

				output += "<tr><td><input id = \"hidPatientIdSave\" name = \"hidPatientIdSave\" type=\"hidden\" value = '" + pid + "'>" + pid + "</td>";
				output += "<td>" + fName + "</td>";
				output += "<td>" + lName + "</td>";
				output += "<td>" + gender + "</td>";
				output += "<td>" + nic + "</td>";
				output += "<td>" + dob + "</td>";
				output += "<td>" + bloodGroup + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + phone + "</td>";
				output += "<td>" + allergies + "</td>";
				output += "<td><input name = \"btnUpdate\" type = \"button\" value = \"Update\" class = \"btnUpdate btn btn-success btn-sm\"></td>"
						+ "<td><input name = 'btnRemove' type = 'button' value = 'Remove' class = 'btnRemove btn btn-danger btn-sm' data-patientid = '"+ pid +"'>" 
						+ "</td></tr>";
				System.out.println("Data retrived from the database");

			}
			// Complete the html table
			output += "</table>";

		} catch (Exception e) {

			output = "Error while reading the patient details...!";
			log.log(Level.SEVERE, e.getMessage());

		} finally {

			try {
				if (st != null) {
					st.close();
				}

				if (con != null) {
					con.close();
				}

				if (rs != null) {
					rs.close();
				}

			} catch (Exception e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return output;

	}

	// to update patient details
	@Override
	public String updatePatientDetails(int pid, String fName, String lName, String gender, String nic, String dob, String email,
			String phone, String bloodGroup, String allergies) {

		String output = "";

		try {

			con = DBConnection.getDBConnection();

			String query = "UPDATE patient SET fName=?, lName=?,  nic=?, dob=?,  phone=?, email=?, gender=?, bloodGroup=?, allergies=?"
					+ " WHERE pid=? ";
			

			preparedStmt = con.prepareStatement(query);
			
			preparedStmt.setString(Constants.COLUMN_INDEX_ONE, fName);
			preparedStmt.setString(Constants.COLUMN_INDEX_TWO, lName);
			preparedStmt.setString(Constants.COLUMN_INDEX_THREE, nic);
			preparedStmt.setString(Constants.COLUMN_INDEX_FOUR, dob);
			preparedStmt.setString(Constants.COLUMN_INDEX_FIVE, phone);
			preparedStmt.setString(Constants.COLUMN_INDEX_SIX, email);
			preparedStmt.setString(Constants.COLUMN_INDEX_SEVEN, gender);
			preparedStmt.setString(Constants.COLUMN_INDEX_EIGHT, bloodGroup);
			preparedStmt.setString(Constants.COLUMN_INDEX_NINE, allergies);
			preparedStmt.setInt(Constants.COLUMN_INDEX_TEN, pid);	
			preparedStmt.execute();
			
			System.out.println("Update patintId : " + pid);
			
			String newPatient = getAllPatients();
			 output = "{\"status\":\"success\", \"data\": \"" + newPatient + "\"}"; 
			
		} catch (Exception e) {

			output = "{\"status\":\"error\", \"data\":\"Error while updating the patient details..!\"}"; 
			log.log(Level.SEVERE, e.getMessage());

		} finally {

			try {
				if (preparedStmt != null) {
					preparedStmt.close();
				}

				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}

		return output;
	}
	
	// to delete a patient from the system
	@Override
	public String deletePatient(int pid) {
		
		String output = "";
		System.out.println("delete");

		try {

			con = DBConnection.getDBConnection();

			String query = "DELETE FROM patient WHERE pid = ?";

			preparedStmt = con.prepareStatement(query);

			preparedStmt.setInt(1,pid);

			preparedStmt.execute();
			output = "Vaule has been deleted successfully..!\";\r\n";
			String newPatient = getAllPatients();
			output = "{\"status\":\"success\", \"data\": \"" + newPatient + "\"}"; 

		} catch (Exception e) {

			output = "{\"status\":\"error\", \"data\":\"Error while deleting the patient account..!\"}"; 
			log.log(Level.SEVERE, e.getMessage());

		} finally {

			try {
				if (preparedStmt != null) {
					preparedStmt.close();
				}

				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}

		return output;
	}

	

}