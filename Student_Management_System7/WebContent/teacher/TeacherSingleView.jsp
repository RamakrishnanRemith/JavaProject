<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String teacherId = (String) session
			.getAttribute("sessionteacherId");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Teacher Single View Screen</title>
<link href='css/style.css' rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.10.2.js">
	
</script>
<meta http-equiv="content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width,initial-scale=1">
<script>
	function fnEdit(SingleId) {
		$('#userId').val(SingleId);
		$('#teditForm').submit();
	}
</script>
</head>
<body>
	<%@ include file="/common/header.jsp"%>
	<fieldset class="mt2per br4">
		<div style="width: 100%; display: block;">
			<div style="width: 2%; display: inline-block;">
				<img class="imgheader" alt="User" src="image/teacher.png" width="42">
			</div>
			<div style="width: 94%; display: inline-block;">
				<h1 class="h1header">Teacher View</h1>
			</div>
		</div>
	</fieldset>
	<s:form name="teditForm" id="teditForm" action="teditForm"
		method="post">
		<input type='hidden' name='userId' id='userId'>
		<s:iterator value="list">
			<h2>
				<div class="maindiv1">
					<div class="sdiv5" style="text-align: left">
						<a href="Back1" class="btnblack btn-cyan">Back</a>
					</div>
					<div class="sdiv4" style="text-align: right">
						<a href="javascript:;" class="btn btn-orange"
							onclick="fnEdit('${teacherId}')">Edit</a>
					</div>
				</div>
			</h2>
		</s:iterator>
	</s:form>
	<fieldset class="br4">
		<s:form name="tviewForm" id="tviewForm" class="model-contant"
			action="tviewForm" method="post">
			<s:iterator value="list">
				<div style="width: 100%; display: block;">
					<div style="width: 80%; display: inline-block; float: left">
						<div class="sdiv1">
							<label class="label fwb">Teacher ID</label>
						</div>
						<div class="sdiv2">
							<label class="fwb pad brown"><s:property
									value="teacherId" /></label>
						</div>
						<br>
						<div style="margin-top: 1%;" class="sdiv1">
							<label class="label fwb">Teacher Name</label>
						</div>
						<div class="sdiv2">
							<label class="pad"><s:property value="teacherName" /></label>
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
							<label class="label fwb">Qualification</label>
						</div>
						<div class="sdiv2">
							<label class="pad"> <s:set name="checkQualification"
									value="qualification" /> <s:if
									test="%{#checkQualification ==1}"></s:if> <s:elseif
									test="%{#checkQualification ==2}">BE</s:elseif> <s:elseif
									test="%{#checkQualification ==3}">ME</s:elseif> <s:else>M.sc</s:else>
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
							<label class="label fwb">Teacher Image</label>
						</div>
						<img alt="User" class="label1"
							src="uploads/image/<s:property value="image"/>" width="100"
							height="120" style="margin-top: 3%">
					</div>
				</div>
			</s:iterator>
		</s:form>
	</fieldset>
</body>
</html>