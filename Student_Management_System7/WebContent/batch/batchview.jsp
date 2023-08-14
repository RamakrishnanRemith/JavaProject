<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String batchId = (String) session.getAttribute("sessionbatchId");
    String batch = (String) session.getAttribute("sessionuser");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Batch Single View Screen</title>
<link href='css/style.css' rel="stylesheet">
<link href='css/Batch.css' rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.10.2.js">
	
</script>
<meta http-equiv="content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width,initial-scale=1">



<script>

	function fnAdd(SingleId) {
		var modal = document.getElementById("mymodl");

		var btn = document.getElementById("mybtn");

		var span = document.getElementsByClassName("close")[0];

		modal.style.display = "block";
		$("#result").load("addstu");
		
		span.onclick = function() {
			modal.style.display = "none";
		}
		window.onclick = function(event) {

			if (event.target == modal) {
				modal.style.display = "none";
			}
		}
	}
</script>
<script type="text/javascript">


	function fnAddtec(SingleId) {
		var modal = document.getElementById("mymod");
		var btn = document.getElementById("teacherbtn");
		var span = document.getElementsByClassName("close1")[0];
		modal.style.display = "block";
		$("#result1").load("addtea");
		span.onclick = function() {
			modal.style.display = "none";
		}
		window.onclick = function(event) {

			if (event.target == modal) {
				modal.style.display = "none";
			}
		}
	}
</script>
<script type="text/javascript">
	
	function fnEdit(SingleId) {
		$('#userId').val(SingleId);
		$('#beditForm').submit();
	}
</script>
</head>
<body>
	<%@ include file="/common/header.jsp"%>
	<fieldset class="mt2per br4">
		<div style="width: 100%; display: block;">
			<div style="width: 2%; display: inline-block;">
				<img class="imgheader" alt="User" src="image/batch.png" width="42">
			</div>
			<div style="width: 94%; display: inline-block;">
				<h1 class="h1header">Batch View</h1>
			</div>
		</div>
	</fieldset>



	<s:form name="beditForm" id="beditForm" action="beditForm"
		method="post">
		<s:iterator value="list">
			<input type='hidden' name='userId' id='userId'>

			<s:textfield type="hidden" id="batch" name="batch"
				value="%{#session.sessionuser}"></s:textfield>

			<h2>
				<div class="maindiv1">
					<div class="sdiv5" style="text-align: left">
						<a href="Backbat" class="btnblack btn-cyan" id="batcback"
							name="batcback">Back</a>
					</div>
					<div class="sdiv4" style="text-align: right">
						<a href="javascript:;" class="btn btn-orange"
							onclick="fnEdit('${batchId}')">Edit</a> <a href="javascript:;"
							class="btn btn-grey" id="mybtn" onclick="fnAdd('${batchId}')">+Add
							Student</a>

						<!--  The modal -->

						<div id="mymodl" class="modal1">

							<!-- The modal content -->
							<div class="modal-content1" id="result">

								<span class="close">&times;</span>

							</div>
						</div>



						<a href="javascript:;" class="btn btn-grey" id="teacherbtn"
							onclick="fnAddtec('${batchId}')">+Add Teacher</a>

						<div id="mymod" class="modal1">

							<!-- The modal content -->
							<div class="modal-content1" id="result1">
								<div class="modal-header">
									<span class="close1">&times;</span>

								</div>
							</div>
						</div>


					</div>
			</h2>
		</s:iterator>
	</s:form>
	<fieldset class="br4">
		<form name="bviewForm" id="bviewForm" class="model-contant"
			action="bviewForm" method="post">


			<div style="width: 100%; display: block;">
				<s:iterator value="list">

					<div class="sdiv1">
						<label class="label fwb">Batch ID</label>
					</div>
					<div class="sdiv2">
						<label class="fwb pad brown"><s:property value="batchId" /></label>
					</div>
					<br>
					<div style="margin-top: 1%;" class="sdiv1">
						<label class="label fwb">Batch Name</label>
					</div>
					<div class="sdiv2">
						<label class="pad" ><s:property value="batchName" /></label>
					</div>
					<br>
					<div style="margin-top: 1%;" class="sdiv1">
						<label class="label fwb">Duration</label>
					</div>
					<div class="sdiv2">
						<label class="pad"><s:property value="startDate"></s:property>~<s:property
								value="endDate"></s:property> </label>
					</div>
					<br>

				</s:iterator>
			</div>
			<div style="width: 100%; display: block;">
				<div style="margin-top: 1%; width:20% " class="sdiv1">
					<label class="label fwb">Student</label>
				</div>
				<div class="sdiv2" style="width: 60%;">

					<label style="padding-left: 5%;"><s:property value="studentName" /></label>

				</div>

				<br>
				<div style="margin-top: 1%; width:20% " class="sdiv1">
					<label class="label fwb">Teacher</label>
				</div>
				<div class="sdiv2" style="width: 60%;">

					<label style="padding-left: 5%;"><s:property value="teacherName" /></label>

				</div>
			</div>

		</form>
	</fieldset>

</body>
</html>
