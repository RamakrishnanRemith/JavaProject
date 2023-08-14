<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String courseId = (String) session.getAttribute("sessioncourseId");
%>
<html>
<head>
<link href="css/style.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Course Edit</title>
<script>
	$(document).ready(function() {
		$("span.errorMessage").hide();
	});
</script>
<script>
	function fnCancel(courseId) {
		$('#userId').val(courseId);
		$('#courseeditCancel').submit();
	}
</script>
</head>
<body>
	<%@ include file="/common/header.jsp"%>
	<form name="courseeditForm" method="post" action="courseeditForm">

		<s:iterator value="list">

			<fieldset class="mt1per">
				<div style="width: 100%; display: block;">
					<div style="width: 2%; display: inline-block;">
						<img class="imgheader" alt="User" src="image/course.png"
							width="42">
					</div>
					<div style="width: 94%; display: inline-block;">
						<h1 class="h1header">Course . Edit</h1>
					</div>
				</div>
			</fieldset>
			<fieldset class="mt1per" style="margin-right: 1%;">
				<s:textfield type="hidden" name="courseId" id="courseId"
					value="%{courseId}"></s:textfield>

				<s:textfield type="hidden" name="fees" id="fees" value="%{fees}"></s:textfield>



				<div>
					<div class="registerlbl">
						<label class="registerlbl">Course ID</label><span
							><s:label>:</s:label></span>
					</div>
					<div
						style="display: inline-block; width: 60%; margin-top: 1%; margin-left: 1%;">
						<s:label class="fwb brown" name="courseId" id="courseId"
							value="%{courseId}"></s:label>
					</div>

					<div class="registerlbl">
						<label for="Course Name">Course Name</label><span
							class="clrredmandatory"><s:label>*</s:label></span>
					</div>
					<div style="display: inline-block; width: 60%; margin-top: 1%;">
						<s:textfield id="courseName" name="courseName"
							class="registertextbox"></s:textfield>
						<span><s:fielderror cssClass="inb customErrorMessage">
								<s:param>courseName</s:param>
							</s:fielderror> </span>
					</div>
				</div>

				<div>
					<div class="registerlbl">
						<s:label for="month">Duration</s:label>
						<span class="clrredmandatory"><s:label>*</s:label></span>
					</div>
					<div style="display: inline-block; width: 60%; margin-top: 1%;">
						<s:select class="registertextbox"
							cssStyle="height:30px;width:140px;"
							list="#{'0':'','1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10','11':'11','12':'12'}"
							name="duration">

						</s:select>
						<b>Months</b>
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
						<%-- <s:textfield type="fees" id="fees" class="registerdatebox" name="fees"></s:textfield> --%>
						<label style="padding-left: 13px;"><s:property
								value="fees" /></label>

					</div>
				</div>
			</fieldset>
			<fieldset class="mt1per">

				<div style="width: 100%; display: block;">
					<div style="width: 48%; display: inline-block;">
						<s:submit class="btn btn-orange" value="Update"></s:submit>
					</div>
					<div style="width: 10%; display: inline-block;">
						<a href="courseeditCancel" class="btn btn-red"
							onclick="fnCancel('${courseId}')">x Cancel</a>
					</div>
				</div>

			</fieldset>
		</s:iterator>
	</form>
</body>
</html>