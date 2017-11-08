<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Posty</title>
</head>
<body>
	<form action="/post/add" method="POST">
		<input type="textarea" name="content" placeholder="Put your post hier!"> 
		<input type="hidden" name="token" value="${token}">
			<input type="submit"
			value="ok">
	</form>


	<ul>
		<c:forEach var="post" items="${posts}">
			<li>${post.content}</br> <b>${post.author}</b>
			</li>
		</c:forEach>
	</ul>
</body>
</html>