<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Social Network</title>
</head>
<body>
	<h1>Welcome to our Social Network!</h1>
	<s:form action = "login">
		<s:textfield key = "user.userName" label = "User Name"/>
		<s:password key = "user.password" label = "Password"/>
		<s:submit/>				
	</s:form>
</body>
</html>