package com.healthcare.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.healthcare.model.Patient;
import com.healthcare.controller.PatientController;

/**
 * Servlet implementation class PatientAPI
 */
@WebServlet("/PatientAPI")
public class PatientAPI extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	PatientController PatientController = new PatientController();
	

    /**
     * Default constructor. 
     */
    public PatientAPI() {
       
    }
    
    private static Map getParasMap(HttpServletRequest request) {
		
    	System.out.println("getParasMap");
		Map<String, String> map = new HashMap<String, String>();
		
		try {
			
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			
			scanner.close();
			
			String[] params = queryString.split("&");
			
			for(String param : params) {
				
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}					 
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return map;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
System.out.println("Patient Api post method");
		
		Patient patient = new Patient();
		
//		patient.setPid(Integer.parseInt(request.getParameter("pid").toString()));
		System.out.println("PID in api : " + request.getParameter("fName"));
		patient.setfName(request.getParameter("fName"));
		System.out.println("First Name in api : " + request.getParameter("fName"));
		patient.setlName(request.getParameter("lName"));
		patient.setNic(request.getParameter("nic"));
		patient.setDob(request.getParameter("dob"));
		patient.setPhone(request.getParameter("phone"));
		patient.setEmail(request.getParameter("email"));
		patient.setGender(request.getParameter("gender"));
		patient.setBloodGroup(request.getParameter("bloodGroup"));
		patient.setAllergies(request.getParameter("allergies"));
		
		String output = PatientController.registerPatient(patient);
		System.out.println(output);
		response.getWriter().write(output);
		
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Map paras = getParasMap(request);
		
		System.out.println("Patient API put method");
		System.out.println("patient id: " + paras.get("hidPatientIdSave").toString());
		System.out.println("patient name: " + paras.get("fName").toString());
		
		String output = PatientController.updatePatientDetails(
				Integer.parseInt(paras.get("hidPatientIdSave").toString()),
				paras.get("fName").toString(),
				paras.get("lName").toString(),
				paras.get("gender").toString(),
				paras.get("nic").toString(),
				paras.get("dob").toString().replace("%2F", "/"),
				paras.get("email").toString().replace("%40", "@"),
				paras.get("phone").toString(),				
				paras.get("bloodGroup").toString(),
				paras.get("allergies").toString());

		response.getWriter().write(output);
		
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request);
		
		String output = PatientController.deletePatient(Integer.parseInt(paras.get("pid").toString()));
		
		response.getWriter().write(output);
	}
	
	

}