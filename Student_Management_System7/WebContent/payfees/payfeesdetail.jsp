<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String sid = (String) session.getAttribute("SD");
	String cid = (String) session.getAttribute("CD");
	String bid = (String) session.getAttribute("BD");
	int sessionsno = (Integer) session.getAttribute("sno");
%>
<html>
<head>
<link href='css/list.css' rel="stylesheet">
<link href='css/style.css' rel="stylesheet">
<link href='css/Batch.css' rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>feesdetail</title>
<script>
	function fnCancel(singleId, studentid) {
	  /*alert(studentid)
		alert(singleId)*/
		$('#userId').val(singleId);
		$('#sid').val(studentid);
		$('#fdetailregisterviewform').submit();
	}
</script>
</head>
<body>
	<%@ include file="/common/header.jsp"%>
	<fieldset class="mt1per">
		<div style="width: 100%; display: block;">
			<div style="width: 2%; display: inline-block;">
				<img class="imgheader" alt="User" src="image/fees.png" width="42">
			</div>
			<div style="width: 94%; display: inline-block;">
				<h1 class="h1header">Pay Fees Detail</h1>
			</div>
	</fieldset>
	<s:form name="fdetailregisterviewform" id="fdetailregisterviewform"
		action="feviewform" method="post" accept-charset="UTF_8">
		<input type='hidden' name="userId" id="userId">
		<input type='hidden' name="sid" id="sid">
	</s:form>
	<div>
		<div style="width: 10%; display: inline-block;">
			<a href="javascript:;" class="btnblack btn-cyan"
				onclick="fnCancel('<%=sid%>','<%=cid%>')">Back</a>
		</div>
		<s:if test="remainFees==0"></s:if><s:else><div align="right" style="width: 88%; display: inline-block;">
			<a href="detailregisterbtn" class="regbtn"
				id="detailregisterbtn">+ Register</a>
		</div></s:else>
		
	</div>
	<div>
		<div>
			<div style="text-align: left;" class="registerlbl">
				<label>Student Name : <s:property value="studentName" /></label>
			</div>
			<div style="text-align: right; padding-left: 250px;"
				class="registerlbl">
				<label>course Name : <s:property value="courseName" /></label>
			</div>
			<div style="text-align: center; padding-left: 350px;"
				class="registerlbl">
				<label>Batch Name :<s:property value="batchName" />
				</label>
			</div>
		</div>
		<div>
			<div style="text-align: left;" class="registerlbl">
				<label>Total Fees : <s:property value="totalFees" />
				</label>
			</div>
			<div style="text-align: right; padding-left: 250px;"
				class="registerlbl">
				<label>Remain Fees :<s:property value="remainFees" /></label>
			</div>
		</div>
	</div>
	<div style="height: 313px; overflow: auto;">
		<table>
			<thead>
				<tr>
					<th>S.NO</th>
					<th>Paid Date</th>
					<th>Paid Amount</th>
				</tr>
			</thead>
			<tbody id="myTable">
				<s:if test="%{listdetailtable.size>0}">
					<s:iterator value="listdetailtable" status="listStatus">
						<tr>
							<td><%=sessionsno++%></td>
							<td class="td"><s:property value="paidDate" /></td>
							<td class="td"><s:property value="paidFees" /></td>
						</tr>
					</s:iterator>
				</s:if>
				<s:else>
					<tr>
						<td class="noRecord" colspan=8>No data Found</td>
					</tr>
				</s:else>
			</tbody>
		</table>
		<div style="text-align: right; padding-left: 750px;"
			class="registerlbl">
			<label>Total Paid Fees : <s:property value="totalpaidFees" /></label>
		</div>
	</div>
</body>
</html>