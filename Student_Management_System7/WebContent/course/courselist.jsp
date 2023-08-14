<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String sessioncourseId = (String) session
			.getAttribute("sessioncourseId");
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
<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CourseList Page</title>
<script>
	function fnUse(courseId) {
		$('#hdnuserid').val(courseId);
		$('#courseviewForm').attr('action', 'CourseUse');
		$('#courseviewForm').submit();
	}
	function fnNotUse(courseId) {
		$('#hdnuserid').val(courseId);
		$('#courseviewForm').attr('action', 'CourseNotUse');
		$('#courseviewForm').submit();
	}
	function fnSearch() {
		$("#searchText").val($("#searchText").val());
		$("#courseviewForm").submit();
	}
	function fnFilter(filter) {
		$("#filterId").val(filter);
		$("#courseviewForm").submit();
	}
	$(document).ready(function() {
		$(".sortField").change(function() {
			if ($('#sort').val() != "ASC") {
				$('#sort').val("ASC");
			} else {
				$('#sort').val("DESC");
			}
			$('#courseviewForm').submit();
		});
	});
	function fnPage(pageid) {
		$('#currentPage').val(pageid);
		$('#courseviewForm').submit();
	}
	function fnSingleView(singleId) {
		
		$('#userId').val(singleId);
		$('#coviewform').submit();
	}
</script>
</head>
<body>
	<%@ include file="/common/header.jsp"%>

	<fieldset class="mt1per">
		<div style="width: 100%; display: block;">
			<div style="width: 2%; display: inline-block;">
				<img class="imgheader" alt="User" src="image/course.png" width="42">
			</div>
			<div style="width: 94%; display: inline-block;">
				<h1 class="h1header">Course List</h1>
			</div>
		</div>
	</fieldset>
	<s:form name="coviewform" id="coviewform" action="coviewform"
		method="post" accept-charset="UTF_8">
		<input type='hidden' name="userId" id="userId">
	</s:form>

	<form name="courseviewForm" id="courseviewForm"
		action="courseListRedirect" method="post" accept-charset="UTF-8">
		<input type='hidden' name='hdnuserid' id='hdnuserid'>
		<s:hidden name='sort' id='sort'></s:hidden>
		<s:hidden name='filterId' id='filterId'></s:hidden>
		<input type="hidden" name="currentPage" id="currentPage">


		<div class="maindiv">
			<div class="regbtndiv">
				<a href="courseregisterbtn" class="regbtn">+ Register</a>
			</div>
			<div style="width: 18%; display: inline-block; float: right">
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
					list="#{'1':'Course Id','2':'Course Name'}" id="sortField"
					name="sortField" class="sortField"></s:select>
			</div>

		</div>
	</form>
	<table>
		<tr>
			<th>S.NO</th>
			<th>Course ID</th>
			<th>Course Name</th>

			<th>Duration</th>
			<th>Fees</th>

			<th>Use/Not Use</th>
		</tr>
		<s:if test="%{list.size>0}">
			<s:iterator value="list" status="listStatus">
				<tr>
					<td><%=sessionsno++%></td>
					<td><a href="javascript:;"
						onclick="fnSingleView('${courseId}')"><s:property
								value="courseId" /></a></td>
					<td class="td"><s:property value="courseName" /></td>

					<td style="text-align: center"><s:property value="duration" /> </td>
                     <td style="text-align: right"><s:property value="fees" /></td>

					<td><s:set name="delflag" value="delflag" /> <s:if
							test="%{#delflag==1}">
							<a class="green" href="javascript:;"
								onclick="fnUse('${courseId}')"><%="Use"%></a>
						</s:if> <s:else>
							<a class="red" href="javascript:;"
								onclick="fnNotUse('${courseId}')"><%="Not Use"%></a>
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