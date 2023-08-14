<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String adminId = (String) session.getAttribute("sessionadminId");
%>
<html>
<head>
<link href="css/style.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Register</title>
<script>
	$(document).ready(function() {
		$("span.errorMessage").hide();
		$("dob").datepicker({
			dateFormat : 'yyyy-mm-dd',
			maxDate : "-21y",
			changeYear : true,
			changeMonth : true
		});
	});
</script>
</head>
<body>
	<%@ include file="/common/header.jsp"%>
	<form name="registerForm" method="post" action="registerForm">

		<fieldset class="mt1per">
			<div style="width: 100%; display: block;">
				<div style="width: 2%; display: inline-block;">
					<img class="imgheader" alt="User" src="image/profile.png"
						width="42">
				</div>
				<div style="width: 94%; display: inline-block;">
					<h1 class="h1header">Admin . Register</h1>
				</div>
			</div>
		</fieldset>
		<fieldset class="mt1per">
			<div>
				<div>
					<div class="registerlbl">
						<label for="User Name">User Name</label><span
							class="clrredmandatory"><s:label>*</s:label></span>
					</div>
					<div style="display: inline-block; width: 60%; margin-top: 1%;">
						<s:textfield id="userName" name="userName" class="registertextbox"></s:textfield>
						<span><s:fielderror cssClass="inb customErrorMessage">
								<s:param>userName</s:param>
							</s:fielderror> </span>
					</div>
				</div>
				<div>
					<div class="registerlbl">
						<label for="Gender">Gender</label><span class="clrredmandatory"><s:label>*</s:label></span>
					</div>
					<div
						style="display: inline-block; width: 60%; margin-top: 1%; margin-left: 1%;">
						<s:radio id="gender" name="gender"
							list="#{'1':'Male','2':'Female'}"></s:radio>
						<span><s:fielderror cssClass="inb customErrorMessage">
								<s:param>gender</s:param>
							</s:fielderror> </span>
					</div>
				</div>
				<div>
					<div class="registerlbl">
						<s:label for="Email">Email</s:label>
						<span class="clrredmandatory"><s:label>*</s:label></span>
					</div>
					<div style="display: inline-block; width: 60%; margin-top: 1%;">
						<s:textfield type="email" id="mailId" name="mailId"
							class="registertextbox"></s:textfield>
						<s:fielderror cssClass="inb customErrorMessage">
							<s:param>mailId</s:param>
						</s:fielderror>
					</div>
				</div>
				<div>
					<div class="registerlbl">
						<s:label for="contectNo">Contect No</s:label>
						<span class="clrredmandatory"><s:label>*</s:label></span>
					</div>
					<div style="display: inline-block; width: 60%; margin-top: 1%;">
						<s:textfield type="text" id="contactNo" class="registertextbox"
							name="contactNo"></s:textfield>
						<s:fielderror cssClass="inb customErrorMessage">
							<s:param>contactNo</s:param>
						</s:fielderror>
					</div>
				</div>
				<div>
					<div class="registerlbl">
						<s:label for="contectNo">DOB</s:label>
						<span class="clrredmandatory"><s:label>*</s:label></span>
					</div>
					<div style="display: inline-block; width: 60%; margin-top: 1%;">
						<s:textfield type="date" id="dob" class="registerdatebox"
							name="dob"></s:textfield>
						<s:fielderror cssClass="inb customErrorMessage">
							<s:param>dob</s:param>
						</s:fielderror>
					</div>
				</div>
				<div>
					<div class="registerlbl">
						<s:label for="contectNo">Password</s:label>
						<span class="clrredmandatory"><s:label>*</s:label></span>
					</div>
					<div style="display: inline-block; width: 60%; margin-top: 1%;">
						<s:textfield type="password" id="password" class="registertextbox"
							name="password"></s:textfield>
						<s:fielderror cssClass="inb customErrorMessage">
							<s:param>password</s:param>
						</s:fielderror>
					</div>
				</div>
				<div>
					<div class="registerlbl">
						<s:label for="contectNo">Confirm Password</s:label>
						<span class="clrredmandatory"><s:label>*</s:label></span>
					</div>
					<div style="display: inline-block; width: 60%; margin-top: 1%;">
						<s:textfield type="password" id="confirmPassword"
							class="registertextbox" name="confirmPassword"></s:textfield>
						<s:fielderror cssClass="inb customErrorMessage">
							<s:param>confirmPassword</s:param>
						</s:fielderror>
					</div>
				</div>
			</div>
		</fieldset>
		<fieldset class="mt1per">
			<div style="width: 100%; display: block;">
				<div style="width: 48%; display: inline-block;">
					<s:submit class="btn btn-green" value="+ Register"></s:submit>
				</div>
				<div style="width: 10%; display: inline-block;">
					<a href="AdminCancel" class="btn btn-red">Cancel</a>
				</div>
			</div>
		</fieldset>
	</form>
</body>
</html>