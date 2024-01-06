<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>detail</title>
</head>
<script type="text/javascript">
	function buy(id) {
		document.f.action = "buy?id=" + id;
		document.f.submit();
	}
</script>
<body>
	<jsp:include page="header.jsp"/>
	<form name="f" action="" method="post">
		<table border="1">
		<tr>
			<th>id</th>
			<th>name</th>
			<th>quantity</th>
			<th>price</th>
			<th>num</th>
			<th>add</th>
		</tr>
		<tr>
			<td>${product.id}</td>
			<td>${product.name}</td>
			<td>${product.quantity}</td>
			<td>${product.price*2}</td>
			<td>
				<select name="num">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
				</select>
			</td>
			<td>
				<input type="submit" value="add" onclick="buy('${product.id}')">
			</td>
		</tr>
	</table>
	</form>
</body>
</html>