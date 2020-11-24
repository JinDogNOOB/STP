<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type content="text/html; charset=UTF-8">
<title>Update</title>
</head>
<body>

<h2> Update the Post</h2>

<div class="container">

	<form action="/updateProc" method="post"> <!-- form -->
		<div class="form-group">
			<label for="subject">Subject</label>
			<input type="text" class="form-control" id="subject" name="subject" value="${detail.subject }">
		</div>
		<div class="form-group">
			<label for="content">Content</label>
			<textarea type="text" class="form-control" id="content" name="content" rows="3">${detail.content }</textarea>
		</div>
		<input type="hidden" name="bno" value="${bno}"/>
		<button type="submit" class="btn btn-primary">Update</button>
	</form>
</div>



<%@ include file="bootstrap.jsp" %>
</body>
</html>