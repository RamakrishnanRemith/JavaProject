<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String sid = (String) session.getAttribute("SD");
	String cid = (String) session.getAttribute("CD");
	String bid = (String) session.getAttribute("BD");
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<link href="css/style.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>fees detail Register</title>
<script type="text/javascript">
	$(document).ready(function() {
		$("span.errorMessage").hide();
	});
	function fnCancel(singleId, cousreid) {
		/* alert(singleId);
		alert(cousreid); */
		$('#sid').val(singleId);
		$('#userId').val(cousreid);
		$('#fdetailregisterviewformcan').submit();
	}
	function fnregister(singleId, cousreid) {
		/* alert(singleId);
		alert(cousreid); */
		$('#sid').val(singleId);
		$('#userId').val(cousreid);

		$('#fdrform').submit();
	}
</script>
</head>
<body>
	<%@ include file="/common/header.jsp"%>
	<s:form name="fdetailregisterviewformcan"
		id="fdetailregisterviewformcan" action="fDviewform" method="post"
		accept-charset="UTF_8">
		<input type='hidden' name="sid" id="sid">
		<input type='hidden' name="userId" id="userId">
	</s:form>
	<form name="fdrform" id="fdrform" method="post" action="fdrform">
		<input type='hidden' name="sid" id="sid"> <input type='hidden'
			name="userId" id="userId">
		<fieldset class="mt1per">
			<div style="width: 100%; display: block;">
				<div style="width: 2%; display: inline-block;">
					<img class="imgheader" alt="User" src="image/fees.png" width="42">
				</div>
				<div style="width: 94%; display: inline-block;">
					<h1 class="h1header">Fees Detail . Register</h1>
				</div>
			</div>
		</fieldset>
		<div style="display: inline-block; width: 100%; margin-top: 1%;">
			<div class="registerlbl">
				<s:label for="fees">Course Name</s:label>
				<span ><s:label>:</s:label></span>
			</div>
			<div style="text-align: left; width: 20%; display: inline-block;">
				<label class="pad"><s:property value="courseName" /></label>
				<s:textfield type="hidden" id="courseName" class="registerdatebox"
					name="courseName"></s:textfield>
			</div>
		</div>

		<div style="display: inline-block; width: 100%; margin-top: 1%;">
			<div class="registerlbl">
				<s:label for="fees">Batch Name</s:label>
				<span ><s:label>:</s:label></span>

			</div>
			<div style="text-align: left; width: 20%; display: inline-block;">
				<label class="pad"><s:property value="batchName" /></label>
			</div>
			<s:textfield type="hidden" id="batchName" class="registerdatebox"
				name="batchName"></s:textfield>
		</div>

		<div style="display: inline-block; width: 100%; margin-top: 1%;">
			<div class="registerlbl">
				<s:label for="fees">Student Name</s:label>
				<span ><s:label>:</s:label></span>
			</div>
			<div style="text-align: left; width: 20%; display: inline-block;">
				<label class="pad"><s:property value="studentName" /></label>
				<s:textfield type="hidden" id="studentName" class="registerdatebox"
					name="studentName"></s:textfield>
			</div>
		</div>

		<div style="display: inline-block; width: 100%; margin-top: 1%;">
			<div class="registerlbl">
				<s:label for="fees">Total Fees</s:label>
				<span ><s:label>:</s:label></span>
			</div>
			<div style="text-align: left; width: 20%; display: inline-block;">
				<label class="pad"><s:property value="totalFees" /></label>
				<s:textfield type="hidden" id="totalFees" class="registerdatebox"
					name="totalFees"></s:textfield>
			</div>
		</div>
		<br>
		<div>
			<div class="registerlbl">
				<s:label for="fees">Pay Fees</s:label>
				<span class="clrredmandatory"><s:label>*</s:label></span>
			</div>
			<div style="display: inline-block; width: 60%; margin-top: 1%;">
				<s:textfield type="fees" id="paidfees" class="registerdatebox"
					name="paidfees"></s:textfield>
				<s:fielderror cssClass="inb customErrorMessage">
					<s:param>paidfees</s:param>
				</s:fielderror>
			</div>
		</div>
		<div>
			<div class="registerlbl">
				<s:label for="fees">Remain Fees</s:label>
				<span><s:label>:</s:label></span>
			</div>
			<div style="display: inline-block; width: 60%; margin-top: 1%;">
				<!-- <input id="remainFees" name="remainFees"  value=""  class="registerdatebox" readonly="readonly"/> -->
				<div class="sdiv2">
					<label class="pad"><s:property value="remainFees" /></label>
					<s:textfield type="hidden" id="remainFees" class="registerdatebox"
						name="remainFees"></s:textfield>
				</div>
			</div>
		</div>
		<div>
			<div class="registerlbl">
				<s:label for="date">Pay Date</s:label>
				<span class="clrredmandatory"><s:label>*</s:label></span>
			</div>
			<div style="display: inline-block; width: 60%; margin-top: 1%;">

				<s:textfield type="date" id="paydate" class="registerdatebox"
					name="paydate"></s:textfield>
				<s:fielderror cssClass="inb customErrorMessage">
					<s:param>paydate</s:param>
				</s:fielderror>
			</div>
		</div>
		<fieldset class="mt1per">
			<div style="width: 100%; display: block;">
				<div style="width: 48%; display: inline-block;">
					<s:submit class="btn btn-green" value="+ Register"></s:submit>
				</div>
				<div style="width: 40%; display: inline-block;">
					<a href="javascript:;" class="btn btn-red"
						onclick="fnCancel('<%=sid%>','<%=cid%>')">Cancel</a>
				</div>
			</div>
		</fieldset>
	</form>
</body>
</html>