<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>
	<h1>Add friend</h1>
	<s:form action="addFriend">
		<s:textfield key="name" label="User Name" />
		<s:submit />
	</s:form>

	<h3>You friends</h3>

	<s:iterator value="#session.user.friends" var="user">
		<li><s:property value="#user.userName" /></li>
	</s:iterator>
</body>
</html>