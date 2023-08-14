<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String adminId = (String) session.getAttribute("sessionAdminId");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Single View Screen</title>
<link href='css/style.css' rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.10.2.js">
	
</script>
<meta http-equiv="content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width,initial-scale=1">
<script>
	function fnEdit(SingleId) {
		$('#hdnuserId').val(SingleId);
		$('#sieditForm').submit();
	}
</script>
</head>
<body>
	<%@ include file="/common/header.jsp"%>
	<fieldset class="mt2per br4">
		<div style="width: 100%; display: block;">
			<div style="width: 2%; display: inline-block;">
				<img class="imgheader" alt="User" src="image/profile.png" width="42">
			</div>
			<div style="width: 94%; display: inline-block;">
				<h1 class="h1header">Profile</h1>
			</div>
		</div>
	</fieldset>
	<s:form name="sieditForm" id="sieditForm" action="sieditForm"
		method="post">
		<input type='hidden' name='hdnuserId' id='hdnuserId'>
		<div class="maindiv1">
			<div class="sdiv5" style="text-align: left">
				<a href="AdminBack2" class="btnblack btn-cyan">Back</a>
			</div>
			<s:iterator value="list">
				<div class="sdiv4" style="text-align: right">
					<a href="javascript:;" class="btn btn-orange"
						onclick="fnEdit('${adminId}')">Edit</a>
				</div>
			</s:iterator>
		</div>
	</s:form>
	<fieldset class="br4 mt1per">
		<s:form name="viewForm" id="viewForm" class="model-contant"
			action="viewForm" method="post">
			<s:iterator value="list">
				<table border='0' width='400px' cellpadding='0' cellspacing='6'>
					<tr>
						<td class="label fwb">Admin ID</td>
						<td class="fwb pad brown"><s:property value="adminId" /></td>
					</tr>
					<tr>
						<td class="label fwb">User Name</td>
						<td class="pad"><s:property value="userName" /></td>
					</tr>
					<tr>
						<td class="label fwb">Gender</td>
						<td class="pad"><label> <s:set name="checkGender"
									value="gender" /> <s:if test="%{#checkGender ==1}">Male</s:if>
								<s:else>Female</s:else>
						</label></td>
					</tr>
					<tr>
						<td class="label fwb">DOB</td>
						<td class="pad"><label id="dob"> <s:property
									value="dob" />
						</label></td>
					</tr>
					<tr>
						<td class="label fwb">Email</td>
						<td class="pad"><label id="mailId"> <s:property
									value="mailId" />
						</label></td>
					</tr>
					<tr>
						<td class="label fwb">Contact No</td>
						<td class="pad"><label id="contactNo"> <s:property
									value="contactNo" />
						</label></td>
					</tr>
				</table>
			</s:iterator>
		</s:form>
	</fieldset>
</body>
</html>