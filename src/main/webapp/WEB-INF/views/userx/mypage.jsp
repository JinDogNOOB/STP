<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<!-- JSTL tag -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<link rel="stylesheet" href="/static/css/mypage/bootstrap.css">
<link rel="stylesheet" href="/static/css/mypage/Cart.css">
<head>
  <meta charset="UTF-8">
  <title>슬기로운 스템프 STP!</title>
</head>
<body>


<nav class="navbar navbar-inverse navbar-fixed-top">
      <a class="navbar-brand" href="/welcome"><strong>마이페이지(메인으로)</strong></a>
   
</nav>

<div class="container-fluid">
  <div class="row">
    <div class="sidebar">
      <ul class="nav nav-sidebar">
        <li> <a href="/user/info/mypage" style="color:black;">활동내역</a></li>
        <li> <a href="/user/info/inventory" style="color:black;">인벤토리</a></li>
        <li> <a href="/user/modify" style="color:black;">계정수정</a></li>
         <c:choose>
    	<c:when test="${user.auth eq 99 }">		
          <li> <a href="/admin/user/list" style="color:black;">관리자 페이지</a></li>
        </c:when>
        </c:choose>
        <li> <a href="" style="color:black;"></a></li>
      </ul>
    </div>
    <div class="col-sm-5 col-sm-offset-1 col-md-11 col-md-offset-1 main">
      <h1 class="page-header">${user.uname}('${user.uid}')의 정보</h1>

      <div class="row placeholders">

        <div class="col-xs-6 col-sm-3 placeholder">
          <img src="/static/backgroundimages/mypage/rank.png" width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail">
          
          
          <c:choose>
    	<c:when test="${user.auth eq 0 }">		
          <h4><mark>훈련병 </mark></h4>
        </c:when>
        <c:when test="${user.auth eq 1 }">		
          <h4><mark>일병 </mark></h4>
        </c:when>
         <c:when test="${user.auth gt 1 }">		
          <h4><mark>어드민 </mark></h4>
        </c:when>
        <c:otherwise>
            <h4><mark>${user.money} 돈이 왜이렇게 많아 너 해커냐</mark></h4>
        </c:otherwise>
        </c:choose>  
          
        </div>
        <div class="col-xs-6 col-sm-3 placeholder">
          <img src="/static/backgroundimages/mypage/point.png" width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail">
          <h4><mark>${user.money}</mark></h4>
        </div>
        <div class="col-xs-6 col-sm-3 placeholder">
          <img src="/static/backgroundimages/mypage/day.png" width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail">
          <h4><mark>${user.update_log }</mark></h4>
        </div>
        <div class="col-xs-6 col-sm-3 placeholder">
          <img src="/static/backgroundimages/mypage/email.png" width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail">
          <h4><mark>${user.email }</mark></h4>
        <c:choose>
    	<c:when test="${user.auth lt 1 }">		
          <span class="text-muted"><a href="/user/info/getauthurl" style="color:rebeccapurple;">..혹시 이메일 인증을 안하셨나요? (이메일 인증하기)</a></span>
        </c:when>
        <c:otherwise>
           <span class="text-muted">인증된 계정입니다. (정회원)</span>
        </c:otherwise>
        </c:choose>  
        </div>
      </div>

      <h2 class="sub-header">내가 쓴 게시글</h2>
      <div class="table-responsive">
        <table class="table table-striped" class="col-md-10">

          <thead>
          <tr>
          	<th>게시판ID</th>
            <th>번호</th>
            <th>제목</th>
            <th>글쓴이</th>
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
        <td>X</td>
        <td>deleted</td>
        <td>X</td>
        <td>${I.reg_date }</td>
        <td>${I.viewcount }</td>
    </tr>
    </c:when>
    
    <c:otherwise>
    <tr style="background-color:white;" onclick="location.href='/board/view?id=${I.boardname}&bno=${I.bno }'">
        <td>${I.boardname }</td>
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
      </div>
    </div>
  </div>
</div>

<script type="text/javascript" src="/static/js/mypage/bootstrap.js"></script>
</body>
</html>

