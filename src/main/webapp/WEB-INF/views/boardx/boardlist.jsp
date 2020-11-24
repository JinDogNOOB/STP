<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<!-- JSTL tag -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<link rel="stylesheet" href="/static/css/board/bootstrap.css">
<head>
  <meta charset="UTF-8">
 <title>슬기로운 스템프 STP!</title>
</head>

<body>

<!--상단 메뉴 바-->
<nav role="navigation" class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
      <a href="/welcome"><img src="/static/backgroundimages/board/logo.png" ></a>
    </div>
    <div id="navbarCollapse" class="collapse navbar-collapse">
      <ul class="nav navbar-nav">
        <li><a href="/board/lists?id=notice&page=1">공지사항</a></li>
        <li><a href="/board/lists?id=csgo&page=1">CS:GO 토론방</a></li>
        <li><a href="/board/lists?id=free&page=1">자유 토론방</a></li>
        <li><a href="/board/lists?id=pubg&page=1">PUBG 토론방</a></li>
        <li><a href="/board/lists?id=hots&page=1">시공의 폭풍</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="/user/info/mypage">마이페이지</a></li>
        <li><a href="/user/logout">로그아웃</a></li>
      </ul>
    </div>
  </div>
</nav>
<!--상단 메뉴 바-->
<br>
<br>

<div class="container" >
  <div class="jumbotron" style="background-color:rgba(0,0,0,0.8);">
  	<c:choose>
  		<c:when test="${boardName eq 'csgo' }">
    		<h2 class="sub-header" style="color:white">CS:GO 토론방</h2> <!-- 게시판 이름 -->
   		 </c:when>
    	<c:when test="${boardName eq 'notice' }">
    		<h2 class="sub-header" style="color:white">공지사항</h2> <!-- 게시판 이름 -->
   		 </c:when>
    	<c:when test="${boardName eq 'pubg' }">
    		<h2 class="sub-header" style="color:white">PUBG 토론방</h2> <!-- 게시판 이름 -->
   		 </c:when>
    	<c:when test="${boardName eq 'hots' }">
    		<h2 class="sub-header" style="color:white">시공의폭풍 토론방</h2> <!-- 게시판 이름 -->
   		 </c:when>
    	<c:when test="${boardName eq 'free' }">
    		<h2 class="sub-header" style="color:white">자유게시판</h2> <!-- 게시판 이름 -->
   		 </c:when>
   		 <c:otherwise>
   		 	<h2 class="sub-header" style="color:white">UNKNOWN</h2>
   		 </c:otherwise>
	</c:choose>
	
	
    <div class="table-responsive">

      <table class="table table-striped" class="col-md-10"> <!-- 게시판 폼 -->
        <thead>
        <tr style="background-color:darkorange;" style="color:ghostwhite" >
          <th>번호</th>
          <th>제목</th>
          <th>작성자</th>
          <th>날짜</th>
          <th>조회수</th>
        </tr>
        </thead>

        <tbody>
      <c:forEach var="I" items="${list }">
    <c:choose>
    <c:when test="${I.flag eq 0 }">
     <tr style="background-color:white;">
        <td>X</td>
        <td>deleted</td>
        <td>X</td>
        <td>${I.reg_date }</td>
        <td>${I.viewcount }</td>
    </tr>
    </c:when>
    
    <c:otherwise>
    <tr style="background-color:white;" onclick="location.href='/board/view?id=${boardName}&bno=${I.bno }'">
        <td>${I.bno }</td>
        <td>${I.subject }</td>
        <td>${I.writer }(${I.uid})</td>
        <td>${I.reg_date }</td>
        <td>${I.viewcount }</td>
    </tr>
    </c:otherwise>
    </c:choose>
    
    </c:forEach>


        </tbody>


      </table>
      <button onClick="location.href='/board/write?id=${boardName}'" class="btn btn-default">글작성</button>


      <center>
       <c:choose>
<c:when test="${empty query}">
<div class="text-center">
    <ul class="pagination">
    	<li><a href="#" onClick="paging(1)">처음으로</a></li>
        <li><a href="#" onClick="paging(${pagination.prevPage})">이전</a></li>
        
        <c:forEach var="pageNum" begin="${pagination.firPage }" end="${pagination.endPage }">
     		<c:choose>
     			<c:when test="$pagaNum eq pagination.curPage}">
     				<li><span style="font-weight: bold;"><a href="#" onClick="paging('${pageNum }')">${pageNum }</a></span></li>
     			</c:when>
     			<c:otherwise>
     				<li><a href="#" onClick="paging('${pageNum }')">${pageNum }</a></li>
     			</c:otherwise>
     		</c:choose>
        </c:forEach>
        
        <li><a href="#" onClick="paging(${pagination.nextPage})">다음</a></li>
        <li><a href="#" onClick="paging(${pagination.maxPage})">끝으로</a></li>
    </ul>
</div>
</c:when>

<c:otherwise>
<div class="text-center">
    <ul class="pagination">
    	<li><a href="#" onClick="pagingQuery(1, '${query}')">처음으로</a></li>
        <li><a href="#" onClick="pagingQuery(${pagination.prevPage}, '${query }')">이전</a></li>
        
        <c:forEach var="pageNum" begin="${pagination.firPage }" end="${pagination.endPage }">
     		<c:choose>
     			<c:when test="$pagaNum eq pagination.curPage}">
     				<li><span style="font-weight: bold;"><a href="#" onClick="pagingQuery('${pageNum }', '${query }')">${pageNum }</a></span></li>
     			</c:when>
     			<c:otherwise>
     				<li><a href="#" onClick="pagingQuery('${pageNum }', '${query }')">${pageNum }</a></li>
     			</c:otherwise>
     		</c:choose>
        </c:forEach>
        
        <li><a href="#" onClick="pagingQuery(${pagination.nextPage}, '${query }')">다음</a></li>
        <li><a href="#" onClick="pagingQuery(${pagination.maxPage}, '${query }')">끝으로</a></li>
    </ul>
</div>
</c:otherwise>
</c:choose>



	<form action="/board/lists" method="get">
        <div class="input-group">
          <input type="text" name="query" id="query">
          <input type="hidden" name="id" id="id" value="${boardName}">
          <input type="hidden" name="page" id="page" value="1">
          &nbsp;<button type="submit" class="btn btn-default"><i class="glyphicon glyphicon-search"></i></button>
        </div>
       </form>
      </center>

    </div>
  </div>
</div>

<script type="text/javascript">
function paging(Page){
	location.href="/board/lists?id=${boardName}&page="+Page;
}

function pagingQuery(Page, query){
	location.href="/board/lists?id=${boardName}&page="+Page+"&query="+query;
}	
</script>

<script type="text/javascript" src="/static/js/board/bootstrap.js"></script>
</body>
</html>
