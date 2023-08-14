<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<link href="css/style.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PayFees Register</title>
<script type="text/javascript">
$(document).ready(function() {
	$("span.errorMessage").hide();
$("#courseId").change(function(){
	var v=$(this).val();
	/* alert(v) */
	$.ajax({
		type:"post",
		url:"regfee",
		data:{"courseId":v
			
		},
		success:function (msg){	
			 var Data=JSON.parse(msg);
			 $("#batchId").empty();
			 $("#batchId").append('<option value="">Select Batch</option>');
		$.each(Data,function(index,item){
           //alert(JSON.stringify(item.batchId));
          //alert(JSON.stringify(item.batchName));
						$("#batchId").append('<option value="'+item.batchId+'">'+item.batchName+'</option>');
				console.log(Data);	
			 }) 
		},
			error:function(){
				console.log(msg);
				alert("ERROR")
		}
	});
	
	});
$("#batchId").change(function(){
	var v=$(this).val();
	var v1=$(this).val();
	
	/* alert(v) */
	$.ajax({
		type:"post",
		url:"stufee",
		data:{"batchId":v,
			"courseId":v1
		},
		success:function (msg){	
			 var Data=JSON.parse(msg); 
			 $("#studentId").empty();
			 $("#studentId").append('<option value="">Select Student</option>');
		$.each(Data,function(index,item){
        //alert(JSON.stringify(item.studentId));
       // alert(JSON.stringify(item.studentName));
			
					$("#studentId").append('<option value="'+item.studentId+'">'+item.studentName+'</option>');	
				console.log(Data);	
			 }) 
		},
			error:function(){
				console.log(msg);
				alert("ERROR")
		}
	});
	
	});

$("#courseId").change(function(){
	var v=$(this).val();
	/* alert(v) */
	$.ajax({
		type:"post",
		url:"feessss",
		data:{"courseId":v
			
		},
		success:function (msg){	
			 var Data=JSON.parse(msg);
		$.each(Data,function(index,item){
						$("#totalfees").val(item.fees);
				console.log(Data);	
			 }) 
		},
			error:function(){
				console.log(msg);
				alert("ERROR")
		}
	});
	
	});

});

</script>
<script>
	$(document).ready(function() {
		$("span.errorMessage").hide();
		$("paydate").datepicker({
			dateFormat : 'yyyy-mm-dd',
			maxDate : "-1y",
			changeYear : true,
			changeMonth : true
		});
		
	});
</script>
</head>
<body>
	<%@ include file="/common/header.jsp"%>
	<form name="courseregisterForm" method="post" action="payfeesregisterR">

		<fieldset class="mt1per">
			<div style="width: 100%; display: block;">
				<div style="width: 2%; display: inline-block;">
					<img class="imgheader" alt="User" src="image/fees.png" width="42">
				</div>
				<div style="width: 94%; display: inline-block;">
					<h1 class="h1header">Fees . Register</h1>
				</div>
			</div>
		</fieldset>
		<fieldset class="mt1per">
			<div>

				<div class="registerlbl">
					<label for="Course Name">Course Name</label><span
						class="clrredmandatory"><s:label>*</s:label></span>
				</div>
				<div style="display: inline-block; width: 60%; margin-top: 1%;">
					<s:select cssStyle="height:30px; width:140px;" headerKey="select"
						headerValue="Select course" class="registertextbox"
						list="courseNameList" listKey="key" listValue="value"
						name="courseId" id="courseId">

					</s:select>
					<s:fielderror cssClass="inb customErrorMessage">
						<s:param>courseId</s:param>
					</s:fielderror>
				</div>
			</div>

			<div>

				<div class="registerlbl">
					<label for="batch Name">Batch Name</label><span
						class="clrredmandatory"><s:label>*</s:label></span>
				</div>
				<div style="display: inline-block; width: 60%; margin-top: 1%;">
					<s:select cssStyle="height:30px; width:140px;" headerKey="select"
						headerValue="Select batch" class="registertextbox"
						list="batchNameList" listKey="key" listValue="value"
						name="batchId" id="batchId">

					</s:select>
					<s:fielderror cssClass="inb customErrorMessage">
						<s:param>batchId</s:param>
					</s:fielderror>
				</div>
			</div>
			<div>

				<div class="registerlbl">
					<label for="Course Name">Student Name</label><span
						class="clrredmandatory"><s:label>*</s:label></span>
				</div>
				<div style="display: inline-block; width: 60%; margin-top: 1%;">
					<s:select cssStyle="height:30px; width:140px;"
						class="registertextbox" headerKey="select"
						headerValue="Select Student" list="studentNameList" listKey="key"
						listValue="value" name="studentId" id="studentId">

					</s:select>
					<s:fielderror cssClass="inb customErrorMessage">
						<s:param>studentId</s:param>
					</s:fielderror>
				</div>
			</div>
			<div>
				<div class="registerlbl">
					<s:label for="fees">Total Fees</s:label>
					<span class="clrredmandatory"><s:label>*</s:label></span>
				</div>
				<div style="display: inline-block; width: 60%; margin-top: 1%;">
				<!-- 	<input id="totalfees" name="totalfees" value=""
						class="registerdatebox" readonly="readonly" /> -->
						<s:textfield   class="registerdatebox"  name="totalfees" id="totalfees"></s:textfield> 
					<s:fielderror cssClass="inb customErrorMessage">
						<s:param>totalfees</s:param>
					</s:fielderror>
				</div>
			</div>
			<div>
				<div class="registerlbl">
					<s:label for="fees">Pay Fees</s:label>
					<span class="clrredmandatory"><s:label>*</s:label></span>
				</div>
				<div style="display: inline-block; width: 60%; margin-top: 1%;">
					<s:textfield type="fees" id="paidfees" class="registerdatebox"
						name="paidfees"></s:textfield>
					<s:fielderror cssClass="inb customErrorMessage">
						<s:param>paidfees</s:param>
					</s:fielderror>
				</div>
			</div>
			<div>
				<div class="registerlbl">
					<s:label for="date">Pay Date</s:label>
					<span class="clrredmandatory"><s:label>*</s:label></span>
				</div>
				<div style="display: inline-block; width: 60%; margin-top: 1%;">

					<s:textfield type="date" id="paydate" class="registerdatebox"
						name="paydate"></s:textfield>
					<s:fielderror cssClass="inb customErrorMessage">
						<s:param>paydate</s:param>
					</s:fielderror>

				</div>
			</div>

		</fieldset>
		<fieldset class="mt1per">
			<div style="width: 100%; display: block;">
				<div style="width: 48%; display: inline-block;">
					<s:submit class="btn btn-green" value="+ Register"></s:submit>
				</div>
				<div style="width: 10%; display: inline-block;">
					<a href="feesCancel" class="btn btn-red">x Cancel</a>
				</div>
			</div>
		</fieldset>
	</form>
</body>
</html>