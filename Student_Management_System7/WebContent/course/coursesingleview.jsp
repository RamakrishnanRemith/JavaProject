<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String courseId = (String) session.getAttribute("sessioncourseId");
    String course = (String) session.getAttribute("sessionuser");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>course Single View Screen</title>
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
		$("#result").load("addbtc");
		
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
<%-- <script type="text/javascript">
	
	function fnSTUH(SingleId) {
		$('#userId').val(SingleId);
		$('#ceditForm').submit();
	} 
</script> --%>

<script type="text/javascript">
	
	function fnEdit(SingleId) {
		$('#userId').val(SingleId);
		$('#ceditForm').submit();
	} 
</script>
</head>
<body>
	<%@ include file="/common/header.jsp"%>
	<fieldset class="mt2per br4">
		<div style="width: 100%; display: block;">
			<div style="width: 2%; display: inline-block;">
				<img class="imgheader" alt="User" src="image/course.png" width="42">
			</div>
			<div style="width: 94%; display: inline-block;">
				<h1 class="h1header">Course View</h1>
			</div>
		</div>
	</fieldset>



	<s:form name="ceditForm" id="ceditForm" action="ceditForm"
		method="post">
		<s:iterator value="list">
			<input type='hidden' name='userId' id='userId'>


			<h2>
				<div class="maindiv1">
					<div class="sdiv5" style="text-align: left">
						<a href="courseback" class="btnblack btn-cyan" id="courseback"
							name="courseback">Back</a>
					</div>
					<div class="sdiv4" style="text-align: right">
						 <a href="javascript:;" class="btn btn-orange"
							onclick="fnEdit('${courseId}')">Edit</a>  
							<a href="javascript:;"
							class="btn btn-grey" id="mybtn" onclick="fnAdd('${courseId}')">+Add
							Batch</a>

						<!--  The modal -->

						<div id="mymodl" class="modal1">

							<!-- The modal content -->
							<div class="modal-content1" id="result">

								<span class="close">&times;</span>

							</div>
						</div>



						<a href="studenthistory" class="btn btn-grey" id="studenthistory">Student History</a>


					</div>
			</h2>
		</s:iterator>
	</s:form>
	<fieldset class="br4">
		<form name="cviewForm" id="cviewForm" class="model-contant"
			action="cviewForm" method="post">


			<div style="width: 100%; display: block;">
				<s:iterator value="list">

					<div class="sdiv1">
						<label class="label fwb">Course ID</label>
					</div>
					<div class="sdiv2">
						<label class="fwb pad brown"><s:property value="courseId" /></label>
					</div>
					<br>
					<div style="margin-top: 1%;" class="sdiv1">
						<label class="label fwb">Course Name</label>
					</div>
					<div class="sdiv2">
						<label class="pad"><s:property value="courseName" /></label>
					</div>
					<br>
					<div style="margin-top: 1%;" class="sdiv1">
						<label class="label fwb">Duration</label>
					</div>
					<div class="sdiv2">
						<label class="pad"><s:property value="duration"></s:property> </label>
					</div>
					<br>
					<div style="margin-top: 1%;" class="sdiv1">
						<label class="label fwb">Fees</label>
					</div>
					<div class="sdiv2">
						<label class="pad"><s:property value="fees" /></label>
					</div>

				</s:iterator>
			</div>
			<div style="width: 100%; display: block;">
				<div style="margin-top: 1%; width:20% " class="sdiv1">
					<label class="label fwb">Batch</label>
				</div>
				<div class="sdiv2" style="width: 60%;">

					<label style="padding-left: 5%;"><s:property value="batchName" /></label>

				</div>

				<%-- <br>
				<div style="margin-top: 1%; width:20% " class="sdiv1">
					<label class="label fwb">Teacher</label>
				</div>
				<div class="sdiv2" style="width: 60%;">

					<label style="padding-left: 5%;"><s:property value="teacherName" /></label>

				</div> --%>
			</div>

		</form>
	</fieldset>

</body>
</html>
