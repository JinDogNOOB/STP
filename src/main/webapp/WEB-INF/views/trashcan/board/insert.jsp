<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert Form</title>
</head>
<body>

<h2>writing post</h2>

<div class="container">
	<form action="/board/writeProc?id=${boardName}" method="post" enctype="multipart/form-data">
		<div clas="form-group">
			<label for="subject">title of post</label>
			<input type="text class="form-control" id="subject" name="subject" placeholder="write the blank">
		</div>	
		
		
		<div class="form-group">
			<label for=content">content</label>
			<textarea class="form-control" id="content" name="content" rows="3"></textarea>
		</div>
		
		<input type="file" name="files">
		
		<button type="submit" class="btn btn-primary">submit</button>
	</form>
</div>
















<%@ include file="bootstrap.jsp" %>
</body>
</html>