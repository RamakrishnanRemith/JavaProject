
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String sessionbatchId = (String) session
			.getAttribute("sessionbatchId");
String sessionteacherId = (String) session
.getAttribute("sessionteacherId");
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
<title>Add Teacher Page</title>
<script>
$(document).ready(function(){
	$("#myInput").on("keyup",function(){
		var value=$(this).val().toLowerCase();
		$("#myTable tr").filter(function(){	
		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)	
		});	
	});
});
 function fnaddtea(){
	var Select=$("input[name='checkboxval1']").serializeArray();
	if(Select.length==0){
		alert("Please Select Atleast one Teacher");
		return false
	}
	else{
		$("#addteacherForm").submit();
		return
	}
 } 
 </script>
</head>
<body>
	<div>
		<h3 style="text-align: left; margin-top: 0px; margin-bottom: 0px;">
			Teacher Add For Batch <span style="margin-left: 470px"> <a
				href="markX11" class="close1">&times;</a></span>
		</h3>
	</div>
	<form name="addteacherForm" id="addteacherForm" action="addteacherForm"
		method="post" accept-charset="UTF-8">

		<s:iterator value="list" status="listStatus">
			<div>
				<div align="left">

					<label>Batch :<s:property value="batchId" />-><s:property
							value="batchName" /></label>
				</div>
				<div>
					<input type="text" id="myInput" placeholder="Search..."
						style="margin-top: -32px; padding-bottom: 8px" />
				</div>
			</div>
		</s:iterator>

		<div id="mymodl" class="modal1">

			<!-- The modal content -->
			<div class="modal-content1" id="result">
				<div class="modal-header">
					<span class="close">&times;</span>
				</div>
			</div>
		</div>
		<div style="height: 313px; overflow: auto;">
			<table>
				<thead>
					<tr>
						<th></th>
						<th>S.NO</th>
						<th>Teacher ID</th>
						<th>Teacher Name</th>
					</tr>
				</thead>
				<tbody id="myTable">
					<s:if test="%{listsss.size>0}">
						<s:iterator value="listsss" status="listStatus">

							<tr>
								<td><s:set var="teaId" value="teacherId"></s:set> <s:set
										var="addteaId" value="addteachId"></s:set> <s:property
										value="existTeacher" /> <s:if test="%{#addteaId == 1}">

										<input type="checkbox" checked="checked" name="checkboxval1"
											value="<s:property value="teacherId" />">
									</s:if> <s:else>

										<input type="checkbox" name="checkboxval1"
											value="<s:property value="teacherId" />">
									</s:else></td>
								<td><%=sessionsno++%></td>
								<td class="td"><s:property value="teacherId" /></td>
								<td class="td"><s:property value="teacherName" /></td>
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
						<a href="javascript:;" class="btn btn-green" onclick="fnaddtea()">+Add</a>
						<a href="addteacancel" class="btn btn-red">x Cancel</a>
					</div>
				</div>
			</fieldset>
		</div>
	</form>
</body>
</html>