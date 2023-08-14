<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<link href="css/style.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Batch Register</title>
<script>
	$(document).ready(function() {
		$("span.errorMessage").hide();
		$("startdate").datepicker({
			dateFormat : 'yyyy-mm-dd',
			maxDate : "-1d",
			changeYear : true,
			changeMonth : true
		});
		$("enddate").datepicker({
			dateFormat : 'yyyy-mm-dd',
			maxDate : "-1d",
			changeYear : true,
			changeMonth : true
		});
	});
</script>
</head>
<body>
	<%@ include file="/common/header.jsp"%>
	<form name="batchregisterForm" method="post" action="batchregisterForm">

		<fieldset class="mt1per">
			<div style="width: 100%; display: block;">
				<div style="width: 2%; display: inline-block;">
					<img class="imgheader" alt="User" src="image/batch.png" width="42">
				</div>
				<div style="width: 94%; display: inline-block;">
					<h1 class="h1header">Batch . Register</h1>
				</div>
			</div>
		</fieldset>
		<fieldset class="mt1per">
			<div>

				<div class="registerlbl">
					<label for="Batch Name">Batch Name</label><span
						class="clrredmandatory"><s:label>*</s:label></span>
				</div>
				<div style="display: inline-block; width: 60%; margin-top: 1%;">
					<s:textfield id="batchname" name="batchname"
						class="registertextbox"></s:textfield>
					<span><s:fielderror cssClass="inb customErrorMessage">
							<s:param>batchname</s:param>
						</s:fielderror> </span>
				</div>
			</div>

			<div>
				<div class="registerlbl">
					<s:label for="date">Duration</s:label>
					<span class="clrredmandatory"><s:label>*</s:label></span>
				</div>
				<div style="display: inline-block; width: 60%; margin-top: 1%;">
			
						<s:textfield type="date" id="startdate" class="registerdatebox"
							name="startdate"></s:textfield>
						

					<label style="padding-left: 12px;"><b>~</b></label>
						<s:textfield type="date" id="enddate" class="registerdatebox"
							name="enddate"></s:textfield>
							
						
					</div>
					</div>
					<div style="padding-left: 350px; width: 600px">
					<div style="padding-left: 95px;">
					<s:fielderror cssClass="inb customErrorMessage">
						<s:param>startdate</s:param>
						</s:fielderror>
						<s:fielderror cssClass="inb customErrorMessage">
							<s:param>enddate</s:param>
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
					<a href="batchCancel" class="btn btn-red">x Cancel</a>
				</div>
			</div>
		</fieldset>
	</form>
</body>
</html>