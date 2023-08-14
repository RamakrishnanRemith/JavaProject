<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String sid = (String) session.getAttribute("SD");
	String bid = (String) session.getAttribute("BD");
	String cid = (String) session.getAttribute("CD");
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
<title> Fees list Page</title>
<script>
	function fnSearch() {
		$("#searchText").val($("#searchText").val());
		$("#feesviewForm").submit();
	}
	function fnFilter(filter) {
		$("#filterId").val(filter);
		$("#feesviewForm").submit();
	}
	$(document).ready(function() {
		$(".sortField").change(function() {
			if ($('#sort').val() != "ASC") {
				$('#sort').val("ASC");
			} else {
				$('#sort').val("DESC");
			}
			$('#feesviewForm').submit();
		});
	});
	function fnPage(pageid) {
		$('#currentPage').val(pageid);
		$('#feesviewForm').submit();
	}
	function fnSingleView(singleId) {
	/* 	alert(singleId) */
		$('#userId').val(singleId);
		$('#feviewform').submit();
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
				<h1 class="h1header">Fees List</h1>
			</div>
		</div>
	</fieldset>
	<s:form name="feviewform" id="feviewform" action="feviewform"
		method="post" accept-charset="UTF_8">
		<input type='hidden' name="userId" id="userId">
		<input type='hidden' name="cid" id="cid">
	</s:form>
	<form name="feesviewForm" id="feesviewForm" action="feesListRedirect"
		method="post" accept-charset="UTF-8">
		<input type='hidden' name='hdnuserid' id='hdnuserid'>
		<s:hidden name='sort' id='sort'></s:hidden>
		<s:hidden name='filterId' id='filterId'></s:hidden>
		<input type="hidden" name="currentPage" id="currentPage"> <input
			type='hidden' name="studentId" id="studentId">
		<div class="maindiv">
			<div class="regbtndiv">
				<a href="feesregisterbtn" class="regbtn">+ Register</a>
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
			<div class="selectbox">
				<s:select cssStyle="width:100%;padding-top:8px;"
					list="#{'1':'Student Id','2':'Student Name','3':'Batch Name'}"
					id="sortField" name="sortField" class="sortField"></s:select>
			</div>
		</div>
	</form>
	<table>
		<tr>
			<th>S.NO</th>
			<th>Student ID</th>
			<th>Student Name</th>
			<th>Batch Name</th>
			<th>Total Fees</th>
			<th>Paid Fees</th>
			<th>Remain Fees</th>

		</tr>
		<s:if test="%{list.size>0}">
			<s:iterator value="list" status="listStatus">
				<tr>
					<td><%=sessionsno++%></td>
					<td><a href="javascript:;"
						onclick="fnSingleView('${studentId}')"><s:property
								value="studentId" /></a></td>
					<td class="td"><s:property value="studentName" /></td>
					<td class="td"><s:property value="batchName" /></td>
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
	</table>
	<s:include value="/common/Pagination.jsp"></s:include>
</body>
</html>