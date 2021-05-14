
<%@page import="com.user"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Users Management</title>
<link rel="stylesheet" href="views/bootstrap.min.css">
<script src="components/jquery-3.2.1.min.js"></script>
<script src="components/user.js"></script>
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Users Management</h1>
				<form id="formUser" name="formuser">
					User code: <input id="userCode" name="userCode" type="text"
						class="form-control form-control-sm"> <br> User name:
					<input id="userName" name="userName" type="text"
						class="form-control form-control-sm"> <br> User
					email: <input id="userEmail" name="userEmail" type="text"
						class="form-control form-control-sm"> <br> User Phone
					: <input id="userPhone" name="userPhone" type="text"
						class="form-control form-control-sm"> <br> <input
						id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> <input type="hidden"
						id="hidUserIDSave" name="hidUserIDSave" value="">
				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divusersGrid">
					<%
					user userObj = new user();
					out.print(userObj.readUsers());
					%>
				</div>
			</div>
		</div>
	</div>

</body>
</html>