<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<link href="css/style.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.10.2.js">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</script>
<script>
	$(document).ready(function() {
		$("span.errorMessage").hide();
	});
</script>
<title>Admin Login</title>
</head>
<body>
	<form name="loginForm" action="loginForm" method="post">
		<fieldset class="mt5per mb6per ml20per mr20per">
			<div>
				<div class="ml6per">
					<h3>Login</h3>
				</div>
				<div>
					<div class="mt5per pl11per fwb">
						<label for="adminId">UserId</label>
					</div>
					<div class="pl11per mt2per">
						<s:textfield type="text" id="adminId" name="adminId"
							class="textbox" size="50" placeholder="Email / User Id"></s:textfield>
						<s:fielderror cssClass="inb customErrorMessage">
							<s:param>adminId</s:param>
						</s:fielderror>
					</div>
				</div>
				<div>
					<div class="mt2per pl11per fwb">
						<s:label value="Password">
						</s:label>
					</div>
					<div class="pl11per mt2per">
						<s:textfield type="password" name="password" id="password"
							class="textbox" size="50" placeholder="Password"></s:textfield>
						<s:fielderror cssClass="inb customErrorMessage">
							<s:param>password</s:param>
						</s:fielderror>
					</div>
				</div>
				<div class="pl11per mt2per">
					<a href="Forgotyourpassword">Forgot your password?</a>
				</div>
				<div class="mt2per wid24per mb3per ml4per">
					<s:submit class="loginbtn btn-orange" value="Login"></s:submit>
				</div>
			</div>
		</fieldset>
	</form>
</body>
</html>