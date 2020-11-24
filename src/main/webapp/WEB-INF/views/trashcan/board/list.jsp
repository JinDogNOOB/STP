<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSTL tag -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List</title>

<script type="text/javascript">
function paging(Page){
location.href="/board/lists/?id=${boardName}&page="+Page;
}
</script>

</head>

<body>
<h2> List Of Post</h2>

<button class="btn btn-primary" onclick="location.href='/board/write?id=${boardName}'">Writing the Post</button>
<h2>'hello id : ${sessionuid}'</h2>
<h2> '${boardName}'</h2>
<button class="btn btn-primary" onClick="location.href='/logoutProc'">logout</button>
<div class="container">
	<table class="table table-hover">
		<tr> <!-- tr means Table Row -->
			<th>No</th> <!-- th means Table Head -->
			<th>Subject</th>
			<th>Writer</th>
			<th>Date</th>	 
		</tr>
		<c:forEach var="I" items="${list}"> <!-- JSTL for  -->
			<tr onclick="location.href='/board/view?id=${boardName}&bno=${I.bno}'"> <!-- going to detail -->
				<td>${I.bno }</td> <!-- td means Table Data -->
				<td>${I.subject }</td>
				<td>${I.writer }</td>
				<td>${I.reg_date }</td>
			</tr>
		</c:forEach> 
		<!-- JSTL 사용한거임 이거  -->
	</table>
</div>
<div>
<table text-align:center>
	<c:if test="${pagination.curPage ne 1 }">
		<a href="#" onClick="paging(1)">[처음]</a>
	</c:if>
	<c:if test="${pagination.curPage ne 1 }">
		<a href="#" onClick="paging('${pagination.prevPage}')">[이전]</a>
	</c:if>
	<c:forEach var="pageNum" begin="${pagination.firPage }" end="${pagination.endPage }">
		<c:choose>
			<c:when test="${pageNum eq pagination.curPage }">
				<span style="font-weight: bold;"><a href="#" onClick="paging('${pageNum}')">${pageNum }</a></span>
			</c:when>
			<c:otherwise>
				<a href="#" onClick="paging('${pageNum}')">${pageNum }</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	
	<c:if test="${pagination.curPage ne pagination.maxPage }">
		<a href="#" onClick="paging('${pagination.nextPage}')">[다음]</a>
	</c:if>
	<c:if test="${pagination.curPage ne pagination.maxPage }">
		<a href="#" onClick="paging('${pagination.maxPage }')">[끝]</a>
	</c:if>
	</table>
</div>
	








<%@ include file="bootstrap.jsp" %>
</body>
</html>