<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html> <!--  this is HTML5 doctype html4 is very long -->
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Detail</title>
</head>




<body>

<h2>Detailed Post</h2>

<button class="btn btn-primary" onclick="location.href='/update/${detail.bno}'">update</button>
<button class="btn btn-primary" onclick="location.href='/delete/${detail.bno}'">delete</button>


<div class="container">
	<!--  <form action="/insertProc" method="post"> -->  <!-- this is useless thing -->
		<div class="form-group">
			<label>Subject</label>
			<p>${detail.subject }</p>
		</div>
		<div class="form-group">
			<label>Writer</label>
			<p>${detail.writer }</p>
		</div>
		<div class="form-group">
			<label>Date</label>
			<p>${detail.reg_date }</p>
		</div>
		<div class="form-group"> <!-- download files -->
			<label>attachments</label>
			<p><a href="/fileDown/${files.bno }">${files.fileOriName }</a></p>
		</div>
		<div class="form-group">
			<label>Content</label>
			<p>${detail.content }</p>
		</div>
		<button class="btn btn-primary" onClick="location.href='/list'">go back</button>
	<!--  </form> -->
</div>


<%@ include file="bootstrap.jsp" %>
</body>
</html>