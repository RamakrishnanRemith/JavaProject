<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String adminId = (String) session.getAttribute("sessionadminId");
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
	function fnCancel(adminId) {
		alert(adminId);
		$('#userId').val(adminId);
		$('#AdminCancelForm').submit();
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
				<img class="imgheader" alt="User" src="image/profile.png" width="42">
			</div>
			<div style="width: 94%; display: inline-block;">
				<h1 class="h1header">Profile . Edit</h1>
			</div>
		</div>
	</fieldset>
	<form name="AdminCancelForm" id="AdminCancelForm"
		action="AdminCancelForm" method="post">
		<s:textfield type="hidden" name="userId" id="userId"></s:textfield>
	</form>
	<form name="editForm" id="editForm" action="editForm" method="post">
		<s:iterator value="List">
			<fieldset class="mt1per br4">
				<s:textfield type="hidden" name="adminId" id="adminId"
					value="%{adminId}"></s:textfield>
				<s:textfield type="hidden" name="hdnmailId" id="hdnmailId"
					value="%{mailId}"></s:textfield>
				<div>
					<div>
						<div class="registerlbl">
							<label for="admin Id">Admin ID</label><span><s:label> :</s:label></span>
						</div>
						<div
							style="display: inline-block; width: 60%; margin-top: 1%; margin-left: 1%;">
							<s:label class="fwb brown" name="adminId" id="adminId"
								value="%{adminId}"></s:label>
						</div>
					</div>

					<div>
						<div class="registerlbl">
							<label for="Admin Name">Admin Name</label><span
								class="clrredmandatory"><s:label>*</s:label></span>
						</div>
						<div style="display: inline-block; width: 60%; margin-top: 1%;">
							<s:textfield id="userName" name="userName" value="%{userName}"
								class="registertextbox">
							</s:textfield>
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
				</div>
			</fieldset>
			<fieldset class="mt1per br4">
				<div style="width: 100%; display: block;">
					<div style="width: 49%; display: inline-block; padding-left: 3%">
						<s:submit name="button" class="btn btn-orange" id="update"
							value="Update"></s:submit>
					</div>
					<div style="display: inline-block; width: 2%; margin-left: 1%;">
						<a href="javascript:;" class="btn btn-red"
							onclick="fnCancel('${adminId}')">Cancel</a>
					</div>
				</div>
			</fieldset>
		</s:iterator>
	</form>
</body>
</html>