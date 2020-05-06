<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.healthcare.controller.PatientController"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="ISO-8859-1">

<title>Patient Service</title>

<!-- CSS -->
<link rel="stylesheet" href="Views/bootstrap.min.css">
<link rel="stylesheet" href="Views/Patient.css">
<link rel="stylesheet" href="Views/bootstrap-datepicker3.css" />


<!-- JS -->
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Patient.js" type="text/javascript"></script>
<script src="Components/bootstrap-datepicker.min.js"
	type="text/javascript"></script>
<script src="Components/popper.min.js" type="text/javascript"></script>

</head>

<body>

<div class="container">


		<form name="patientSignUp" id="patientSignUp" method="POST"
			action="Patient.jsp">

			<div class="form-title">Create Your Account</div>
			<br>
			<br>
			<h5>Profile Information</h5>

			<div class="row">
				<div class="col">
					<label>First Name<label_1> *</label_1></label><br /> <input
						type="text" placeholder="Enter First Name" name="fName" id="fName"
						class="form-control form-control-sm"><br />
				</div>

				<div class="col">
					<label>Last Name<label_1> *</label_1></label><br /> <input
						type="text" placeholder="Enter Last Name" name="lName" id="lName"
						class="form-control form-control-sm"><br />
				</div>
			</div>

			<div class="row">
				<div class="col">
					<label>Gender<label_1> *</label_1></label><br /> <select
						name="gender" id="gender" class="form-control form-control-sm">
						<option value="0">Select Gender</option>
						<option value="Male">Male</option>
						<option value="Female">Female</option>
					</select><br />
				</div>

				<div class="col">
					<label>NIC<label_1> *</label_1></label><br /> <input type="text"
						placeholder="Enter NIC Number" name="NIC" id="nic"
						class="form-control form-control-sm"><br />
				</div>
			</div>

			<div class="row">
				<div class="col">
					<label>Date Of Birth<label_1> *</label_1></label><br /> <input
						type="text" placeholder="Enter Date Of Birth" name="dob" id="dob"
						class="form-control form-control-sm"><br />
				</div>

				<div class="col">
					<label>Blood Group<label_1> *</label_1></label><br /> <select
						name="bloodGroup" id="bloodGroup"
						class="form-control form-control-sm">
						<option value="0">Select Blood Group</option>
						<option value="A">A</option>
						<option value="B">B</option>
						<option value="AB">AB</option>
						<option value="O">O</option>
					</select><br />
				</div>
			</div>

			<h5>Contact Information</h5>

			<div class="row">
				<div class="col">
					<label>Email<label_1> *</label_1></label><br /> <input type="text"
						placeholder="Enter Email" name="email" id="email"
						class="form-control form-control-sm"><br />
				</div>

				<div class="col">
					<label>Telephone Number<label_1> *</label_1></label><br /> <input
						type="text" placeholder="Enter Telephone Number" name="phone"
						id="phone" class="form-control form-control-sm"><br />
				</div>
			</div>

		<div class="row">
				<div class="col">
					<label>Allergies<label_1> *</label_1></label><br /> <input type="text"
						placeholder="Enter Allergies" name="allergies" id="allergies"
						class="form-control form-control-sm"><br />
				</div>

				<div class="col">
					<label>pid<label_1> *</label_1></label><br /> <input type="text"
						placeholder="Enter PID" name="pid" id="pid"
						class="form-control form-control-sm"><br />
				</div>
			</div>
			<hr>

			<p>
				By creating an account you agree to our <a href="#"><b>Terms
						& Privacy Policy</b></a>
			</p>


			<input id="btnSave" name="btnSave" type="button" value="Add Profile"
				class="btn btn-primary"> <input type="hidden"
				id="hidPatientIdSave" name="hidPatientIdSave" value=""> <br>

		</form>

		<div id="alertSuccess" class="alert alert-success"></div>
		<div id="alertError" class="alert alert-danger"></div>

		<div id="divPatientsGrid">
			<%
			PatientController patientObj = new PatientController();
				out.print(patientObj.getAllPatients());
			%>
		</div>

	</div>



</body>
</html>