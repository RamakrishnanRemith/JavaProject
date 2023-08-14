<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String teacherId = (String) session
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
<link href="css/list.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Page</title>
<script>
	function fnUse(teacherId) {
		$('#hdnuserid').val(teacherId);
		$('#teacherviewForm').attr('action', 'TeacherUse');
		$('#teacherviewForm').submit();
	}
	function fnNotUse(teacherId) {
		$('#hdnuserid').val(teacherId);
		$('#teacherviewForm').attr('action', 'TeacherNotUse');
		$('#teacherviewForm').submit();
	}
	function fnSearch() {
		$("#searchText").val($("#searchText").val());
		$("#teacherviewForm").submit();
	}
	function fnFilter(filter) {
		$("#filterId").val(filter);
		$("#teacherviewForm").submit();
	}
	$(document).ready(function() {
		$(".sortField").change(function() {
			if ($('#sort').val() != "ASC") {
				$('#sort').val("ASC");
			} else {
				$('#sort').val("DESC");
			}
			$('#teacherviewForm').submit();
		});
	});
	function fnPage(pageid) {
		$('#currentPage').val(pageid);
		$('#teacherviewForm').submit();
	}
	function fnSingleView(singleId) {
		$('#userId').val(singleId);
		$('#tviewform').submit();
	}
</script>
</head>
<body>
	<%@ include file="/common/header.jsp"%>

	<fieldset class="mt1per">
		<div style="width: 100%; display: block;">
			<div style="width: 2%; display: inline-block;">
				<img class="imgheader" alt="User" src="image/teacher.png" width="42">
			</div>
			<div style="width: 94%; display: inline-block;">
				<h1 class="h1header">Teacher List</h1>
			</div>
		</div>
	</fieldset>
	<s:form name="tviewform" id="tviewform" action="tviewform"
		method="post" accept-charset="UTF_8">
		<input type='hidden' name="userId" id="userId">
	</s:form>
	<form name="teacherviewForm" id="teacherviewForm"
		action="TeacherListRedirect" method="post" accept-charset="UTF-8">
		<input type='hidden' name='hdnuserid' id='hdnuserid'>
		<s:hidden name='sort' id='sort'></s:hidden>
		<s:hidden name='filterId' id='filterId'></s:hidden>
		<input type="hidden" name="currentPage" id="currentPage">


		<div class="maindiv">
			<div class="regbtndiv">
				<a href="teacherregisterbtn" class="regbtn">Register</a>
			</div>
			<div class="searchbox">
				<s:textfield cssStyle="width:78%;padding-top:10px;"
					name="searchText" id="searchText" placeholder="Search..."
					value="%{#session.sessionSearchText}">
				</s:textfield>
				<button class="imgbtn" name="searchbtn" id="searchbtn"
					onclick="fnSearch()" style="padding-bottom: 5px;">
					<img alt="search" src="image/search.png" height="20" width="22">
				</button>
			</div>
		</div>
		<div class="maindiv">
			<div class="filter">
				<s:if test="%{#session['sessionFilterId']==0}">
					<a href="javascript:;" onclick="fnFilter(2)">All</a> |
					Use |
					<a href="javascript:;" onclick="fnFilter(1)">Not Use</a>
				</s:if>
				<s:elseif test="%{#session['sessionFilterId']== 1}">
					<a href="javascript:;" onclick="fnFilter(2)">All</a> |
						<a href="javascript:;" onclick="fnFilter(0)">Use</a> |
						Not Use
					</s:elseif>
				<s:else>
						All |
						<a href="javascript:;" onclick="fnFilter(0)">Use</a> |
						<a href="javascript:;" onclick="fnFilter(1)">Not Use</a>
				</s:else>

			</div>
			<div class="selectbox">
				<s:select cssStyle="width:100%;padding-top:8px;"
					list="#{'1':'Teacher Id','2':'Teacher Name'}" id="sortField"
					name="sortField" class="sortField"></s:select>
			</div>

		</div>
	</form>
	<table>

		<tr>
			<th>S.NO</th>
			<th>Teacher ID</th>
			<th>Teacher Name</th>
			<th>Gender</th>
			<th>DOB</th>
			<th>Email</th>
			<th>Contact No</th>
			<th>Use/Not Use</th>
		</tr>
		<s:if test="%{list.size>0}">
			<s:iterator value="list" status="listStatus">
				<tr>
					<td><%=sessionsno++%>
					<td><a href="javascript:;"
						onclick="fnSingleView('${teacherId}')"><s:property
								value="teacherId" /></a></td>
					<td class="td"><s:property value="teacherName" /></td>
					<td><s:set name="checkGender" value="gender" />
						<s:if test="%{#checkGender==1}"><%="M"%>
						</s:if> <s:else><%="F"%></s:else></td>
					<td><s:property value="dob" /></td>
					<td class="td"><s:property value="mailId" /></td>

					<td><s:property value="ContactNo" /></td>
					<td><s:set name="delflag" value="delflag" /> <s:if
							test="%{#delflag==1}">
							<a class="green" href="javascript:;"
								onclick="fnUse('${teacherId}')"><%="Use"%></a>
						</s:if> <s:else>
							<a class="red" href="javascript:;"
								onclick="fnNotUse('${teacherId}')"><%="Not Use"%></a>
						</s:else></td>
				</tr>
			</s:iterator>
		</s:if>
		<s:else>
			<tr>
				<td class="noRecord" colspan=8>No data Found</td>
			</tr>
		</s:else>
	</table>
	<s:include value="/common/Pagination.jsp"></s:include>

</body>
</html>