<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String studentId = (String) session
			.getAttribute("sessionstudentId");
	String student = (String) session.getAttribute("sessionuser");
	String userName1 = (String) session.getAttribute("sessionuserName");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Single View Screen</title>
<link href='css/style.css' rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.10.2.js">	
</script>
<meta http-equiv="content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width,initial-scale=1">
<script>
	$(document).ready(function() {
		if ($('#student').val() == "Student1") {
			$('#StuBack').hide();
			$('#menudiv').hide();
			$('#AdminHover').hide();
		} else {
			$('#fFeesPay').hide();
		}
	});
	function fnEdit(SingleId) {
		$('#userId').val(SingleId);
		$('#seditForm').submit();
	}
	function fnFeesPay(SingleId) {
		$('#studentId').val(SingleId);
		$('#stuFeesPayForm').submit();
	}
</script>
</head>
<body>
	<%@ include file="/common/header.jsp"%>
	<fieldset class="mt2per br4">
		<div style="width: 100%; display: block;">
			<div style="width: 2%; display: inline-block;">
				<img class="imgheader" alt="User" src="image/student.png" width="42">
			</div>
			<div style="width: 94%; display: inline-block;">
				<h1 class="h1header">Student View</h1>
			</div>
		</div>
	</fieldset>
	<form name="stuFeesPayForm" id="stuFeesPayForm" action="Feesviewform"
		method="post">
		<input type='hidden' name='studentId' id='studentId'>
	</form>
	<s:form name="seditForm" id="seditForm" action="seditForm"
		method="post">
		<input type='hidden' name='userId' id='userId'>

		<s:textfield type="hidden" id="student" name="student"
			value="%{#session.sessionuser}"></s:textfield>
		<s:iterator value="list">
			<h2>
				<div class="maindiv1">
					<div class="sdiv5" style="text-align: left">
						<a href="Back2" class="btnblack btn-cyan" id="StuBack"
							name="StuBack">Back</a>
					</div>
					<div class="sdiv4" style="text-align: right">
						<a href="javascript:;" class="btn btn-green"
							onclick="fnFeesPay('${studentId}')" id="fFeesPay" name="fFeesPay">Pay
							Fees</a> <a href="javascript:;" class="btn btn-orange"
							onclick="fnEdit('${studentId}')">Edit</a>
					</div>
				</div>
			</h2>
		</s:iterator>
	</s:form>
	<fieldset class="br4">
		<s:form name="sviewForm" id="sviewForm" class="model-contant"
			action="sviewForm" method="post">
			<s:iterator value="list">
				<div style="width: 100%; display: block;">
					<div style="width: 80%; display: inline-block; float: left">
						<div class="sdiv1">
							<label class="label fwb">Student ID</label>
						</div>
						<div class="sdiv2">
							<label class="fwb pad brown"><s:property
									value="studentId" /></label>
						</div>
						<br>
						<div style="margin-top: 1%;" class="sdiv1">
							<label class="label fwb">Student Name</label>
						</div>
						<div class="sdiv2">
							<label class="pad"><s:property value="studentName" /></label>
						</div>
						<br>
						<div style="margin-top: 1%;" class="sdiv1">
							<label class="label fwb">Gender</label>
						</div>
						<div class="sdiv2">
							<label class="pad"> <s:set name="checkGender"
									value="gender" /> <s:if test="%{#checkGender ==1}">Male</s:if>
								<s:else>Female</s:else>
							</label>
						</div>
						<br>
						<div style="margin-top: 1%;" class="sdiv1">
							<label class="label fwb">DOB</label>
						</div>
						<div class="sdiv2">
							<label class="pad" id="dob"> <s:property value="dob" />
							</label>
						</div>
						<br>
						<div style="margin-top: 1%;" class="sdiv1">
							<label class="label fwb">Email</label>
						</div>
						<div class="sdiv2">
							<label class="pad" id="mailId"> <s:property
									value="mailId" />
							</label>
						</div>
						<br>
						<div style="margin-top: 1%;" class="sdiv1">
							<label class="label fwb">Contact No</label>
						</div>
						<div class="sdiv2">
							<label class="pad" id="contactNo"> <s:property
									value="contactNo" />
							</label>
						</div>
						<br>
						<div style="margin-top: 1%;" class="sdiv1">
							<label class="label fwb">Address</label>
						</div>
						<div class="sdiv2">
							<label class="pad" id="address"> <s:property
									value="address" />
							</label>
						</div>
						<br>
						<div style="margin-top: 1%;" class="sdiv1">
							<label class="label fwb">Pincode</label>
						</div>
						<div class="sdiv2">
							<label class="pad" id="pincode"> <s:property
									value="pincode" />
							</label>
						</div>
					</div>
					<div style="width: 19%; display: inline-block;">
						<div style="margin-top: 15%;">
							<label class="label fwb">Student Image</label>
						</div>
						<img alt="User" class="label1"
							src="uploads/StudentImage/<s:property value="image"/>"
							width="100" height="120" style="margin-top: 3%">
					</div>
				</div>
			</s:iterator>
		</s:form>
	</fieldset>
</body>
</html>