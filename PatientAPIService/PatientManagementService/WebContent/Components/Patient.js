//Page refresh moment
$(document).ready(function(){
	if($("#alertSuccess").text().trim() == ""){
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});

//Save
$(document).on("click", "#btnSave", function(event){
	
	//Clear alerts
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	//Form validation
	var status = validateForm();
	if(status != true){
		
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
	//If valid
//	var type = ($("#hidPatientIdSave").val() == "") ? "POST" : "PUT";
//	
//	$.ajax({
//		
//		url : "PatientAPI",
//		type : type,
//		data : $("#patientSignUp").serialize(),
//		dataType : "text",
//		complete : function(response, status){
//			
//			onPatientRegisterComplete(response.responseText, status);
//		}
//	});
//	
//});
//If valid
var type = ($("#hidPatientIdSave").val() == "") ? "POST" : "PUT";

var hidPatientIdSave = $('#hidPatientIdSave').val();
var fName = $('#fName').val();
var lName = $('#lName').val();
var gender = $('#gender').val();
var nic = $('#nic').val();
var dob = $('#dob').val();          
var email = $('#email').val();
var phone = $('#phone').val();
var bloodGroup = $('#bloodGroup').val();
var allergies = $('#allergies').val();
	  
$.ajax({
	
	url : "PatientAPI",
	type : type,
	data : "hidPatientIdSave=" + hidPatientIdSave + "&fName=" + fName + "&lName=" + lName + "&gender=" + gender + "&nic=" + nic + "&dob=" + dob +
	"&email=" + email + "&phone=" + phone + "&bloodGroup=" + bloodGroup + "&allergies=" + allergies,
	complete : function(response, status){
		
		onPatientRegisterComplete(response.responseText, status);
	}
});

});


function onPatientRegisterComplete(response, status){
	
	if(status == "success"){
		window.location.reload();
		var resultSet = JSON.parse(response);
		
		if(resultSet.status.trim() == "success"){
			
			$("#alertSuccess").text("Successfully saved..!");
			$("#alertSuccess").show();
			
			$("#divPatientsGrid").html(resultSet.data);	
			
		}else if(resultSet.status.trim() == "error"){
			
			$("#alertError").text(resultSet.data);
			$("#alertError").show(); 
		}
		
	}else if(status == "error"){
		$("#alertError").text("Error while saving.");
		$("#alertError").show(); 
	
	}else{
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show(); 
		$("#divPatientsGrid").html(resultSet.data);
	}
	
	$("#hidPatientIdSave").val("");
	$("#patientSignUp")[0].reset(); 
	
}	

//Update
$(document).on("click", ".btnUpdate", function(event)
{

	$("#hidPatientIdSave").val($(this).closest("tr").find('td:eq(0)').text());
	$("#fName").val($(this).closest("tr").find('td:eq(1)').text());
	$("#lName").val($(this).closest("tr").find('td:eq(2)').text());
	$("#gender").val($(this).closest("tr").find('td:eq(3)').text());
	$("#nic").val($(this).closest("tr").find('td:eq(4)').text());
	$("#dob").val($(this).closest("tr").find('td:eq(5)').text());
	$("#bloodGroup").val($(this).closest("tr").find('td:eq(6)').text());
	$("#email").val($(this).closest("tr").find('td:eq(7)').text());
	$("#phone").val($(this).closest("tr").find('td:eq(8)').text());
	$("#allergies").val($(this).closest("tr").find('td:eq(9)').text());
	
	
});

//Delete
$(document).on("click", ".btnRemove", function(event){
		
	$.ajax({
		url : "PatientAPI",
		type : "DELETE",
		data : "pid=" + $(this).data("patientid"),
		dataType : "text",
		complete : function(response, status){
			 
			 onPatientDeleteComplete(response.responseText, status);
		 }
	});
}); 

function onPatientDeleteComplete(response, status){
	
	if (status == "success")
	 {
		window.location.reload();
		setTimeout(function(){ alert("Hello"); }, 3000);
//		 $("#alertSuccess").text("Successfully deleted..!");
		 $("#alertSuccess").show();
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success"){
			
			
			 
			 $("#divPatientsGrid").html(resultSet.data);
			 
		} else if (resultSet.status.trim() == "error"){
			 $("#alertError").text(resultSet.data);
			 $("#alertError").show();
		}
		
	 } else if (status == "error")
	 {
		 $("#alertError").text("Error while deleting..!");
		 $("#alertError").show();
	 } else
	 {
		 $("#alertError").text("Unknown error while deleting..!");
		 $("#alertError").show();
	 } 
}

//Client-Model
function validateForm(){
	
	if($("#fName").val().trim() == ""){
		return "Insert First Name...!";
	}
	
	var letterReg1 = /^[A-Za-z]+$/;
	var tmpfName =  $("#fName").val().trim();
	if(!tmpfName.match(letterReg1)){
		return "First Name must have alphabet charaters only...!";
	}
	
	if($("#lName").val().trim() == ""){
		return "Insert Last Name...!";
	}
	
	
	if($("#gender").val() == "0"){
		return "Select Gender...!";
	}
	
	if($("#nic").val().trim() == ""){
		return "Insert NIC...!";
	}
	
	var nicReg = /^[0-9]{9}[vVxX]$/;
	var tmpNIC =  $("#nic").val().trim();
	if(!tmpNIC.match(nicReg)){
		return "Insert a valid NIC number...!";
	}
	
	if($("#dob").val().trim() == ""){
		return "Insert Date Of Birth...!";
	}
	
	if($("#bloodGroup").val() == "0"){
		return "Select Blood Group...!";
	}
	
	if($("#email").val().trim() == ""){
		return "Insert Email...!";
	}
	
	var emailReg = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	var tmpEmail =  $("#email").val().trim();
	if(!tmpEmail.match(emailReg)){
		return "Insert a valid Email...!";
	}
	
	if($("#phone").val().trim() == ""){
		return "Insert Phone Number...!";
	}
	
	var contactReg = /^\d{10}$/;
	var tmpPhone =  $("#phone").val().trim();
	if(!tmpPhone.match(contactReg)){
		return "Insert a valid Phone Number...!";
	}
	

	
	return true;
}