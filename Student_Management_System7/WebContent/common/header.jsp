<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/style.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap/min.css">
<title>Menu</title>
</head>

<%
	String userid = (String) session.getAttribute("sessionadminId");
	String menu = (String) session.getAttribute("sessionuser");
	String userName = (String) session.getAttribute("sessionuserName");
%>
<script>
	function fnProfile(userid) {
		alert(userid);
		$("#userid").val(userid);
		$("#profileform").submit();
	}
</script>
<body>
	<form name="profileform" id="profileform" action="profileform">
		<input type='hidden' name='userid' id='userid' value="<%=userid%>">
	</form>
	<div Style="width: 100%; display: block; height: 10%">
		<div Style="width: 85%; display: inline-block;">
			<h1>STUDENT MANAGEMENT SYSTEM</h1>
		</div>
		<div style="width: 14%; display: inline-block;" id="AdminHover">
			<div style="margin-right: 30px; text-align: center;" class="dropdown">
				<img alt="User" src="image/user.png" width="20" height="20"><br>
				<span><%=userName%></span>
				<div class="dropdown-content">
					<a href="javascript:;" onclick="fnProfile('${userid}')">Profile</a><br>
					<a href="ChangePassword">Change Password</a><br> <a
						href="Logout">Logout</a>
				</div>
			</div>
		</div>
	</div>
	<div id="menudiv">
		<hr>
		<div class="btn-paingreen">
			<%
				if (menu == "Admin") {
			%>
			<a href="AdminBtn" class="btn font btn-coral">Admin</a> <a
				href="TeacherBtn" class="btn font btn-paingreen">Teacher</a> <a
				href="StudentBtn" class="btn btn-paingreen font">Student</a> <a
				href="CourseBtn" class="btn btn-paingreen font">Course</a> <a
				href="BatchBtn" class="btn btn-paingreen font">Batch</a> <a
				href="FeesBtn" class="btn btn-paingreen font">Fees</a>
			<%
				} else if (menu == "Teacher") {
			%>
			<a href="AdminBtn" class="btn btn-paingreen font">Admin</a> <a
				href="TeacherBtn" class="btn btn-coral font">Teacher</a> <a
				href="StudentBtn" class="btn btn-paingreen font">Student</a> <a
				href="CourseBtn" class="btn btn-paingreen font">Course</a> <a
				href="BatchBtn" class="btn btn-paingreen font">Batch</a> <a
				href="FeesBtn" class="btn btn-paingreen font">Fees</a>
			<%
				} else if (menu == "Student") {
			%>
			<a href="AdminBtn" class="btn btn-paingreen font">Admin</a> <a
				href="TeacherBtn" class="btn btn-paingreen font">Teacher</a> <a
				href="StudentBtn" class="btn btn-coral font">Student</a> <a
				href="CourseBtn" class="btn btn-paingreen font">Course</a> <a
				href="BatchBtn" class="btn btn-paingreen font">Batch</a> <a
				href="FeesBtn" class="btn btn-paingreen font">Fees</a>
			<%
				} else if (menu == "Batch") {
			%>
			<a href="AdminBtn" class="btn btn-paingreen font">Admin</a> <a
				href="TeacherBtn" class="btn btn-paingreen font">Teacher</a> <a
				href="StudentBtn" class="btn btn-paingreen font">Student</a> <a
				href="CourseBtn" class="btn btn-paingreen font">Course</a> <a
				href="BatchBtn" class="btn font btn-coral">Batch</a> <a
				href="FeesBtn" class="btn btn-paingreen font">Fees</a>
			<%
				} else if (menu == "Course") {
			%>
			<a href="AdminBtn" class="btn btn-paingreen font">Admin</a> <a
				href="TeacherBtn" class="btn btn-paingreen font">Teacher</a> <a
				href="StudentBtn" class="btn btn-paingreen font">Student</a> <a
				href="CourseBtn" class="btn btn-coral font">Course</a> <a
				href="BatchBtn" class="btn btn-paingreen font">Batch</a> <a
				href="FeesBtn" class="btn btn-paingreen font">Fees</a>
			<%
				} else if (menu == "Fees") {
			%>
			<a href="AdminBtn" class="btn btn-paingreen font">Admin</a> <a
				href="TeacherBtn" class="btn btn-paingreen font">Teacher</a> <a
				href="StudentBtn" class="btn btn-paingreen font">Student</a> <a
				href="CourseBtn" class="btn btn-paingreen font">Course</a> <a
				href="BatchBtn" class="btn btn-paingreen font">Batch</a> <a
				href="FeesBtn" class="btn btn-coral font">Fees</a>
			<%
				} else {
			%>
			<a href="AdminBtn" class="btn btn-paingreen font">Admin</a> <a
				href="TeacherBtn" class="btn btn-paingreen font">Teacher</a> <a
				href="StudentBtn" class="btn btn-paingreen font">Student</a> <a
				href="CourseBtn" class="btn btn-paingreen font">Course</a> <a
				href="BatchBtn" class="btn btn-paingreen font">Batch</a> <a
				href="FeesBtn" class="btn btn-paingreen font">Fees</a>
			<%
				}
			%>
		</div>
	</div>
</body>
</html>