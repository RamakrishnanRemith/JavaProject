<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link href="css/list.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="center">
		<s:if test="%{#session.nOfPages !=0}">
			<s:set var="checkPrevPage" name="checkPrevPage"
				value="#session.currentPage-1" />
			<s:set var="checkNextPage" name="checkNextPage"
				value="#session.currentPage+1" />
			<s:set var="currentPage" name="currentPage"
				value="#session.currentPage" />
			<s:set var="maxPage" name="maxPage" value="#session.nOfPages" />
			<ul class="pagination">
				<!-- First -->
				<li class="page-item"><s:if
						test="%{#session['currentPage'] !=1}">
						<a class="page-link" href="javascript:;" onclick="fnPage(1)"><<</a>
					</s:if> <s:else>
						<div class="page-link"><<</div>
					</s:else></li>
				<!-- Previous -->
				<li class="page-item"><s:if test="%{#session.currentPage>1}">
						<a class="page-link" href="javascript:;"
							onclick="fnPage('${checkPrevPage}')"><</a>
					</s:if> <s:else>
						<div class="page-link"><</div>
					</s:else></li>
				<!--Number Links -->
				<li class="page-item"><s:iterator begin="%{#session.loadStart}"
						end="%{#session.loadEnd}" status="countStatus">
						<s:if test="%{#session['currentPage'] ==top}">
							<div class="active">
								<s:property value="top" />
							</div>
						</s:if>
						<s:else>
							<a class="page-link" href="javascript:;"
								onclick="fnPage('<s:property value="top"/>')"> <s:property
									value="top" /></a>
						</s:else>
					</s:iterator></li>
				<!-- Next -->
				<li class="page-item"><s:if
						test="%{#session['currentPage']<#session['nOfPages']}">
						<a class="page-link" href="javascript:;"
							onclick="fnPage('${checkNextPage}')">></a>
					</s:if> <s:else>
						<div class="page-link">></div>
					</s:else></li>
				<!-- Last -->
				<li class="page-item"><s:if
						test="%{#session['currentPage']!=#session['nOfPages']}">
						<a class="page-link" href="javascript:;"
							onclick="fnPage(${maxPage})">>></a>
					</s:if> <s:else>
						<div class="page-link">>></div>
					</s:else></li>
			</ul>
		</s:if>
	</div>
</body>
</html>