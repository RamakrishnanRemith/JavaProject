<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String cid = (String) session.getAttribute("CD");
	String sid = (String) session.getAttribute("SD");
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
<title>Fees history Page</title>
<script>
	function fnSingleView(singleId, studId) {
		/* alert(singleId)
		alert(studId) */
		$('#userId').val(singleId);
		$('#sid').val(studId);
		$('#fDviewform').submit();
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
				<h1 class="h1header">Pay Fees History</h1>
			</div>
	</fieldset>
	<s:form name="fDviewform" id="fDviewform" action="fDviewform"
		method="post" accept-charset="UTF_8">
		<input type='hidden' name="userId" id="userId">
		<input type='hidden' name="sid" id="sid">
	</s:form>
	<div class="sdiv5" style="text-align: left">
		<a href="hisback" class=" btnblack btn-cyan" id="hisback" name="hisback">Back</a>
	</div>
	<div style="margin-top: 20px;" >
		<s:iterator value="list">
			<div>
				<div style="text-align: left;" class="registerlbl">
					<label>Student Name : <s:property value="studentName" />
					</label>
				</div>
				<div style="text-align: right; padding-left: 250px;"
					class="registerlbl">
					<label>Batch Name : <s:property value="batchName" />
					</label>
				</div>
			</div>
			<div style="margin-top: 15px;" >
				<div style="text-align: left;" class="registerlbl">
					<label>Total Fees : <s:property value="totalFees" />
					</label>
				</div>
				<div style="text-align: right; padding-left: 250px;"
					class="registerlbl">
					<label>Total Fees Paid : <s:property value="totalpaidFees" /></label>
				</div>
			</div>
		</s:iterator>
	</div>
	<div style="height: 313px; overflow: auto;">
		<table>
			<thead>
				<tr>
					<th>S.NO</th>
					<th>Course ID</th>
					<th>Course Name</th>
					<th>Total Fees</th>
					<th>Remain Fees</th>
				</tr>
			</thead>
			<tbody id="myTable">
				<s:if test="%{list1.size>0}">
					<s:iterator value="list1" status="listStatus">
						<tr>
							<td><%=sessionsno++%></td>
							<td><a href="javascript:;"
								onclick="fnSingleView('${courseId}','${sid}')"><s:property
										value="courseId" /></a></td>
							<td class="td"><s:property value="courseName" /></td>
							<td style="text-align: right"><s:property value="totalFees" /></td>
							<td style="text-align: right"><s:property value="remainFees" /></td>
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
	</div>
</body>
</html>