<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String sessionbatchId = (String) session
			.getAttribute("sessionbatchId");
	String sessionstudentId = (String) session
			.getAttribute("sessionstudentId");
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
<title>Add student Page</title>
<script>
$(document).ready(function(){
	$("#myInput").on("keyup",function(){
		var value=$(this).val().toLowerCase();
		$("#myTable tr").filter(function(){	
		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)	
		});	
	});
});
 function fnaddstu(){
	var Select=$("input[name='checkboxval']").serializeArray();
	if(Select.length==0){
		alert("Please Select Atleast one Student");
		return false
	}
	else{
		$("#addstudentForm").submit();
		return
    }	
} 
</script>
</head>
<body>
	<div>
		<h3 style="text-align: left; margin-top: 0px; margin-bottom: 0px;">
			Student Add For Batch <span style="margin-left: 470px"> <a
				href="markX11" class="close1">&times;</a></span>
		</h3>
	</div>
	<form name="addstudentForm" id="addstudentForm" action="addstudentForm"
		method="post" accept-charset="UTF-8">
		<div>
			<s:iterator value="list" status="listStatus">
				<div align="left">
					<label>Batch :<s:property value="batchId" />-><s:property
							value="batchName" /></label>
				</div>
				<input type="text" id="myInput" placeholder="Search..."
					style="margin-top: -32px; padding-bottom: 8px" />
			</s:iterator>
		</div>
		<div id="mymodl" class="modal1">
			<!-- The modal content -->
			<div class="modal-content1" id="result">
				<span class="close">&times;</span>
			</div>
		</div>
		<div style="height: 313px; overflow: auto;">
			<table>
				<thead>
					<tr>
						<th></th>
						<th>S.NO</th>
						<th>Student ID</th>
						<th>Student Name</th>
					</tr>
				</thead>
				
				<tbody id="myTable">
					<s:if test="%{listsss.size>0}">
						<s:iterator value="listsss" status="listStatus">
							<tr>
								<td><s:set id="stuId" name="stuId" value="studentId"></s:set>
									<s:set id="addstuId" name="addstuId" value="addstuId"></s:set>
									<s:if test="%{#stuId == #addstuId}">

										<input type="checkbox" checked="checked" name="checkboxval"
											value="<s:property value="studentId" />">
									</s:if> <s:else>

										<input type="checkbox" name="checkboxval"
											value="<s:property value="studentId" />">
									</s:else></td>
								<td><%=sessionsno++%></td>
								<td class="td"><s:property value="studentId" /></td>
								<td class="td"><s:property value="studentName" /></td>
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
		<div align="center">
			<fieldset class="mt1per">

				<div style="width: 100%; display: block;">
					<div style="width: 48%; display: inline-block;">
						<a href="javascript:;" class="btn btn-green" onclick="fnaddstu()">+Add</a>
						<a href="addstucancel" class="btn btn-red">x Cancel</a>
					</div>
				</div>
			</fieldset>
		</div>
	</form>
</body>
</html>