<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String student = (String) session.getAttribute("sessionuser");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/style.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<link
	href="https:code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.us"
	rel="stylesheet">
<script type="text/javascript">
	$(document).ready(function() {
		$("span.errorMessage").hide();
		$("#dob").datepicker({
			dateFormat : 'dd/mm/yyyy',
			maxDate : "-18y",
			changeYear : true,
			changeMonth : true
		});
	});
	$(document).ready(function() {
		if ($('#student').val() == "Student1") {
			$('#menudiv').hide();
			$('#AdminHover').hide();
		} else {
		}
	});
	function fnCancel(studentId) {
		$('#userId').val(studentId);
		$('#StudentCancelForm').submit();
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit</title>
</head>
<body>
	<%@ include file="/common/header.jsp"%>
	<fieldset class="mt2per br4">
		<div style="width: 100%; display: block;">
			<div style="width: 2%; display: inline-block;">
				<img class="imgheader" alt="User" src="image/student.png" width="42">
			</div>
			<div style="width: 94%; display: inline-block;">
				<h1 class="h1header">Student . Edit</h1>
			</div>
		</div>
	</fieldset>
	<s:iterator value="list">
		<s:textfield type="hidden" id="student" name="student"
			value="%{#session.sessionuser}"></s:textfield>
		<form name="StudentCancelForm" id="StudentCancelForm"
			action="StudentCancelForm" method="post">
			<s:textfield type="hidden" name="userId" id="userId"></s:textfield>
		</form>
		<form name="studentEditForm" id="studentEditForm"
			action="studentEditForm" method="post" enctype="multipart/form-data">

			<fieldset class="mt1per br4">
				<s:textfield type="hidden" name="studentId" id="studentId"
					value="%{studentId}"></s:textfield>
				<s:textfield type="hidden" name="studentMailId" id="studentMailId"
					value="%{MailId}"></s:textfield>
				<div>
					<div>
						<div class="registerlbl">
							<label for="student Id">student ID</label><span><s:label> :</s:label></span>
						</div>
						<div
							style="display: inline-block; width: 60%; margin-top: 1%; margin-left: 1%;">
							<s:label class="fwb brown" name="studentId" id="studentId"
								value="%{studentId}"></s:label>
						</div>
					</div>

					<div>
						<div class="registerlbl">
							<label for="student Name">Student Name</label><span
								class="clrredmandatory"><s:label>*</s:label></span>
						</div>
						<div style="display: inline-block; width: 60%; margin-top: 1%;">
							<s:textfield id="studentName" name="studentName"
								value="%{studentName}" class="registertextbox">
							</s:textfield>
							<span><s:fielderror cssClass="inb customErrorMessage">
									<s:param>studentName</s:param>
								</s:fielderror> </span>
						</div>
					</div>
					<div>
						<div class="registerlbl">
							<label for="Gender">Gender</label><span class="clrredmandatory"><s:label>*</s:label></span>
						</div>
						<div style="display: inline-block; width: 60%; margin-top: 1%;"
							class="ml2per">
							<s:radio id="gender" name="gender"
								list="#{'1':'Male','2':'Female'}"></s:radio>
							<span><s:fielderror cssClass="inb customErrorMessage">
									<s:param>gender</s:param>
								</s:fielderror> </span>
						</div>
					</div>
					<div>
						<div>
							<div class="registerlbl">
								<s:label for="DOB">DOB</s:label>
								<span class="clrredmandatory"><s:label>*</s:label></span>
							</div>
							<div style="display: inline-block; width: 60%; margin-top: 1%;">
								<s:textfield type="date" id="dob" class="registertextbox"
									name="dob"></s:textfield>
								<s:fielderror cssClass="inb customErrorMessage">
									<s:param>dob</s:param>
								</s:fielderror>
							</div>
						</div>
						<div class="registerlbl">
							<s:label for="mailId">Email</s:label>
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
							<s:label for="Address">Address</s:label>
							<span class="clrredmandatory"><s:label>*</s:label></span>
						</div>
						<div style="display: inline-block; width: 60%; margin-top: 1%;">
							<s:textarea cssStyle="height:50px;" id="address"
								class="registertextbox" name="address"></s:textarea>
							<s:fielderror cssClass="inb customErrorMessage">
								<s:param>address</s:param>
							</s:fielderror>
						</div>
					</div>
					<div>
						<div class="registerlbl">
							<s:label for="pincode">Pincode</s:label>
							<span class="clrredmandatory"><s:label>*</s:label></span>
						</div>
						<div style="display: inline-block; width: 60%; margin-top: 1%;">
							<s:textfield id="pincode" class="registertextbox" name="pincode"></s:textfield>
							<s:fielderror cssClass="inb customErrorMessage">
								<s:param>pincode</s:param>
							</s:fielderror>
						</div>
					</div>
					<div>
						<div class="registerlbl">
							<s:label for="image">Image</s:label>
							<span class="clrredmandatory"><s:label>*</s:label></span>
						</div>
						<div style="display: inline-block; width: 60%; margin-top: 1%;">
							<s:file cssStyle="margin-left:2%;" id="image" name="image"
								accept="image/png,image/gif,image/jpeg" />
							<s:fielderror cssClass="inb customErrorMessage">
								<s:param>image</s:param>
							</s:fielderror>
							<img alt="User" class="lable1"
								src="uploads/StudentImage/<s:property value="image"/>"
								width="30" height="30">

						</div>
					</div>
				</div>
			</fieldset>

			<fieldset class="mt1per br4">
				<div style="width: 100%; display: block;">
					<div style="width: 49%; display: inline-block; padding-left: 3%">
						<s:submit name="button" class="btn btn-orange" value="Update"
							id="update"></s:submit>
					</div>
					<div style="display: inline-block; width: 2%; margin-left: 1%;">
						<a href="javascript:;" class="btn btn-red"
							onclick="fnCancel('${studentId}')">Cancel</a>
					</div>
				</div>
			</fieldset>
		</form>
	</s:iterator>
</body>
</html>