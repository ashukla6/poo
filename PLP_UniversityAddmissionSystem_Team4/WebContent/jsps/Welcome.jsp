<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
</head>
<body>
<center>
<h1>Welcome to University Admission System</h1>
</center>
<center>
<div align="right">
<form action="" >
<input type="text" name="loginId" placeholder="User Name"> &nbsp
<input type="text" name="password" placeholder="Password"> &nbsp
<input type="submit" value="Login">
</form>
</div>
<a href="applicantSheduledProgramlist.do">Scheduled Programs</a> &nbsp &nbsp
<a href="allProgramslist.do">All Programs</a>
<a href="allProgramslist.do">All Programs</a>

<table>
	<tr>
		<td>
			
		</td>
		<td><a href="allProgramslist.do">All Programs</a>
		</td>
		
		<td>
			<select>
				<option value="login">Apply</option> 
				<option value="getRegisterationPage.do">New User</option>
				<option value="mac">Already Registered</option>
			</select>
			</td>
			<td>
		
			<select name="login" >
			<option value="login">Login</option> 
				<option value="admin">Admin</option>
				<option value="mac">MAC</option>
			</select>
		</td>
	</tr>
</table>

<table width="700" align="center" border="1" bgcolor="#CCCCCC">
	<tr>
		<th>Scheduled Program Id</th>
		<th>Program Name</th>
		<th>Location</th>
		<th>Start Date</th>
		<th>End Date</th>
		<th>Session Per Week</th>
	</tr>
	<c:forEach var="sch" items="${scheduledProgramList}">
		<tr>
			<td>${sch.scheduledProgramId}</td>
			<td>${sch.programName}</td>
			<td>${sch.location}</td>
			<td>${sch.startDate}</td>
			<td>${sch.endDate}</td>
			<td>${sch.sessionPerWeek}</td>
		</tr>
	</c:forEach>
</table>
</center>
	<div align="left">
	<a href="getRegisterationPage.do">New Applicant</a><br/>
	<a href="sfs">Already Applied</a>
	</div>

</body>
</html>