<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
<head>
<script>
	$(document).ready(function() {
		$("span.errorMessage").hide();
	});
</script>
<link href="css/style.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forgot Password</title>
</head>
<body>
	<form name="forgotPasswordForm" method="post"
		action="forgotPasswordForm">
		<fieldset class="mt5per mb6per ml20per mr20per">
			<div>
				<h1 style="text-align: center;">Forgot Password?</h1>
			</div>
			<div style="width: 100%; margin-top: 1%; margin-bottom: 3%;">
				<s:textfield type="email" name="mailId" id="mailId"
					class="forgetpass" placeholder="Email Address"></s:textfield>
				<span><s:fielderror cssClass="center inb customErrorMessage">
						<s:param>mailId</s:param>
					</s:fielderror> </span>
			</div>

			<div class="forgetpasswordmaindiv">
				<div class="forgetpasswordindiv1">
					<s:submit class="loginbtn btn-orange" value="Reset"></s:submit>
				</div>
				<div class="forgetpasswordindiv1">
					<a href="ForgotyourpasswordCancel" class="loginbtn btn-red"
						id="Cancel">Cancel</a>
				</div>
			</div>
		</fieldset>
	</form>
</body>
</html>