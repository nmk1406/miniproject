<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>cart</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>no</th>
			<th>name</th>
			<th>num</th>
			<th>price</th>
			<th>money</th>
			<th>action</th>
		</tr>
		<c:set var="no" value="0"/>
		<c:forEach items="${cart.items}" var="item">
			<c:set var="no" value="${no+1}"/>
			<tr>
				<td>${no}</td>
				<td>${item.product.name}</td>
				<td>
					<a href="process?num=-1&id=${item.product.id}">-</a>
					${item.quantity}
					<a href="process?num=1&id=${item.product.id}">+</a>
				</td>
				<td>${item.price}</td>
				<td>${item.price * item.quantity}</td>
				<td>
					<form action="process" method="post">
						<input type="hidden" name="id" value="${item.product.id}">
						<input type="submit" value="remove">
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
	total money: ${cart.totalmoney}
	<form action="checkout" method="post">
		<input type="submit" value="check out">
	</form>
	<a href="shop">return home page</a>
</body>
</html>