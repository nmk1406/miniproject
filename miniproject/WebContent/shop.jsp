<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>shop</title>
</head>
<body>
	<jsp:include page="header.jsp"/>
	<form action="shop">
		<input type="text" name="txt">
		<input type="submit" value="search">
	</form>
	<c:forEach begin="${1}" end="${num}" var="i">
		<a href="shop?page=${i}&txt=${txt}">${i}</a>
	</c:forEach>
	<table border="1">
		<tr>
			<th>id</th>
			<th>name</th>
			<th>price</th>
			<th>view</th>
		</tr>
		<c:forEach items="${products}" var="product">
			<tr>
				<td>${product.id}</td>
				<td>${product.name}</td>
				<td>${product.price*2}</td>
				<td>
					<a href="detail?id=${product.id}">view</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>