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
<title>Change Password</title>
<script>
	$(document).ready(function() {
		$("span.errorMessage").hide();
	});
</script>
</head>
<body>
	<%@ include file="/common/header.jsp"%>
	<form name="changepasswordForm" method="post"
		action="changePasswordForm">

		<fieldset class="mt2per br4">
			<div style="width: 100%; display: block;">
				<div style="width: 2%; display: inline-block;">
					<img alt="User" src="image/passwordchange.png" width="20"
						height="20">
				</div>
				<div style="width: 97%; display: inline-block;">
					<h2>Change Password</h2>
				</div>
			</div>
		</fieldset>
		<fieldset class="mt2per mb2per br4">
			<div style="display: none;">
				<s:textfield id="adminId" name="adminId"
					value="%{#session.sessionadminId}" class="textbox1" size="20">
				</s:textfield>
			</div>
			<div>
				<div class="changepasswordlbl mt2per">
					<div class="passlbl">
						<label for="password">Current Password</label><span
							class="clrredmandatory"><s:label>*</s:label></span>
					</div>
					<div style="display: inline-block; width: 60%;">
						<s:textfield type="password" id="password" name="password"
							class="textbox1" size="30"></s:textfield>
						<span><s:fielderror cssClass="inb customErrorMessage">
								<s:param>password</s:param>
							</s:fielderror> </span>
					</div>
				</div>
				<div class="changepasswordlbl mt2per">
					<div class="passlbl">
						<s:label for="newpassword">New Password</s:label>
						<span class="clrredmandatory"><s:label>*</s:label></span>
					</div>
					<div style="display: inline-block; width: 60%;">
						<s:textfield type="password" id="newpassword" name="newPassword"
							class="textbox1" size="20"></s:textfield>
						<s:fielderror cssClass="inb customErrorMessage">
							<s:param>newPassword</s:param>
						</s:fielderror>
					</div>
				</div>
				<div class="changepasswordlbl mt2per">
					<div class="passlbl">
						<s:label for="confirmpassword">Confirm Password</s:label>
						<span class="clrredmandatory"><s:label>*</s:label></span>
					</div>
					<div style="display: inline-block; width: 60%;">
						<s:textfield type="password" id="confirmpassword" class="textbox1"
							name="confirmPassword" size="40"></s:textfield>
						<s:fielderror cssClass="inb customErrorMessage">
							<s:param>confirmPassword</s:param>
						</s:fielderror>
					</div>
				</div>
			</div>
		</fieldset>
		<fieldset class="mt2per br4">
			<div style="margin-right: 45%;">
				<s:submit class="btn btn-orange" value="Change Password"></s:submit>
			</div>
		</fieldset>
	</form>
</body>
</html>