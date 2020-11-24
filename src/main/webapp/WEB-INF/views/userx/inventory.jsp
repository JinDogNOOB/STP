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
        
        <li> <a href="" style="color:black;"></a></li>

      </ul>
    </div>
    <div class="col-sm-5 col-sm-offset-1 col-md-11 col-md-offset-1 main">
      <h1 class="page-header">내 가방</h1>

      <div class="table-responsive">

<c:forEach var="I" items="${itemLists}">
        <figure class="snip1583">
         <img src="/static/shopitem/${I.imgName}"/>
          <figcaption>
            <h3>${I.itemName }</h3>
            <div class="price">${I.price}</div>
          </figcaption>
        </figure>
      </c:forEach>
      
     
    
    


      </div>
    </div>
  </div>
</div>

<script type="text/javascript" src="/static/js/mypage/bootstrap.js"></script>
</body>
</html>

