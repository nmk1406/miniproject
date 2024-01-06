<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>login</title>
</head>
<body>
	${error}
	<form action="login" method="post">
		<input type="text" name="username"><br>
		<input type="password" name="password"><br>
		<input type="submit" value="login">
	</form>
</body>
</html>