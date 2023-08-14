<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<link href="css/style.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Course Register</title>
<script>
$(document).ready(function() {
	$("span.errorMessage").hide();
});
</script>
</head>
<body>
	<%@ include file="/common/header.jsp"%>
	<form name="courseregisterForm" method="post" action="courseregisterForm">

		<fieldset class="mt1per">
			<div style="width: 100%; display: block;">
				<div style="width: 2%; display: inline-block;"> 
					<img class="imgheader" alt="User" src="image/course.png" width="42">
				</div>
				<div style="width: 94%; display: inline-block;">
					<h1 class="h1header">Course . Register</h1>
				</div>
			</div>
		</fieldset>
		<fieldset class="mt1per">
			<div>

				<div class="registerlbl">
					<label for="Course Name">Course Name</label><span
						class="clrredmandatory"><s:label>*</s:label></span>
				</div>
				<div style="display: inline-block; width: 60%; margin-top: 1%;">
					<s:textfield id="coursename" name="coursename"
						class="registertextbox"></s:textfield>
					<span><s:fielderror cssClass="inb customErrorMessage">
							<s:param>coursename</s:param>
						</s:fielderror> </span>
				</div>
			</div>

			<div>
				<div class="registerlbl">
					<s:label for="month">Duration</s:label>
					<span class="clrredmandatory"><s:label>*</s:label></span>
				</div>
				<div style="display: inline-block; width: 60%; margin-top: 1%;">
						<s:select cssStyle="height:30px; width:140px;"  class="registertextbox" list="#{'0':'Select Months','1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10','11':'11','12':'12'}" name="duration" id="duration">
						
						</s:select><b>Months</b>
						<s:fielderror cssClass="inb customErrorMessage">
							<s:param>duration</s:param>
						</s:fielderror>
					</div>
				</div>
				<div>
				<div class="registerlbl">
					<s:label for="fees">Fees</s:label>
					<span class="clrredmandatory"><s:label>*</s:label></span>
				</div>
				<div style="display: inline-block; width: 60%; margin-top: 1%;">
						<s:textfield type="fees" id="fees" class="registerdatebox" name="fees"></s:textfield>
						<s:fielderror cssClass="inb customErrorMessage">
							<s:param>fees</s:param>
						</s:fielderror>
					</div>
				</div>
		</fieldset>
		<fieldset class="mt1per">
			<div style="width: 100%; display: block;">
				<div style="width: 48%; display: inline-block;">
					<s:submit class="btn btn-green" value="+ Register"></s:submit>
				</div>
				<div style="width: 10%; display: inline-block;">
					<a href="courseCancel" class="btn btn-red">x Cancel</a>
				</div>
			</div>
		</fieldset>
	</form>
</body>
</html>