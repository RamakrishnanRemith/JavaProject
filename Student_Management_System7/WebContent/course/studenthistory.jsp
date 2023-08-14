
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String courseId = (String) session.getAttribute("sessioncourseId");
String cid=(String) session.getAttribute("CD");
String sid = (String) session.getAttribute("SD");
String bid = (String) session.getAttribute("BD");

	int sessionFilterId = (Integer) session
			.getAttribute("sessionFilterId");
	int sessionNOfPages = (Integer) session.getAttribute("nOfPages");
	int sessionCurrentPage = (Integer) session
			.getAttribute("currentPage");
	int sessionsno = (Integer) session.getAttribute("sno");
	String sessionSorting = (String) session.getAttribute("sorting");
	String sessionSearchText = (String) session
			.getAttribute("sessionSearchText");
%>
<html>
<head>
<link href='css/list.css' rel="stylesheet">
<link href='css/style.css' rel="stylesheet">
<link href='css/Batch.css' rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student History</title>
<script>
	
function fnCancel(singleId) {
	
	$('#courseId').val(singleId);
	$('#stuh').submit();
}
</script>
</head>
<body>
	<%@ include file="/common/header.jsp"%>

	<fieldset class="mt1per">
		<div style="width: 100%; display: block;">
			<div style="width: 2%; display: inline-block;">
				<img class="imgheader" alt="User" src="image/student.png" width="42">
			</div>
			<div style="width: 94%; display: inline-block;">
				<h1 class="h1header">Student History</h1>
			</div>			
	</fieldset>
	 <s:form name="stuh" id="stuh" action="stuhissiviewform"
		method="post" accept-charset="UTF_8">
		<input type='hidden' name="courseId" id="courseId">
	</s:form> 

	<form name="historyviewForm" id="historyviewForm"
		action="historyviewForm" method="post" accept-charset="UTF-8">
		<!-- <input type='hidden' name='hdnuserid' id='hdnuserid'> -->
		<input type='hidden' name='userId' id='userId'>
		<s:hidden name='sort' id='sort'></s:hidden>
		<s:hidden name='filterId' id='filterId'></s:hidden>
		<input type="hidden" name="currentPage" id="currentPage">


		
	</form>
		<div style="width: 10%; display: inline-block;">
			<a href="javascript:;" class="btnblack btn-cyan"
				onclick="fnCancel('<%=courseId%>')">Back</a>
		</div>

		
		<div>
	
			<div >
				<div style="text-align: left;" class="registerlbl">

					<label>course Name : <s:property value="courseName" />
							 </label>
				</div>

			
			</div>
			

		</div>

		<div style="height: 313px; overflow: auto;">
			<table>
				<thead>
					<tr>
						
						<th>S.NO</th>
						<th>Student ID</th>
						<th>Student Name</th>
						<th>Total Fees</th>
						<th>Paid Fees</th>
						<th>Remain Fees</th>
					</tr>
				</thead>
				
				<tbody id="myTable">
					<s:if test="%{list1.size>0}">
						<s:iterator value="list1" status="listStatus">
							<tr>
								
								<td><%=sessionsno++%></td>
					<td><a><s:property
								value="studentId" /></a></td>
								
								<td class="td"><s:property value="studentName" /></td>
								<td style="text-align: right"><s:property value="totalFees" /></td>
								<td style="text-align: right"><s:property value="paidFees" /></td>
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