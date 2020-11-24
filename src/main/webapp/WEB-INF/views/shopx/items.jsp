<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<link rel="stylesheet" href="/static/css/shop/Cart.css">
<link rel="stylesheet" href="/static/css/shop/bootstrap.css">

<style type="text/css">
  a:link {color:#007cd0;text-decoration:none}
  a:visited {color:#007cd0;text-decoration:none}
  a:active {color:#007cd0;text-decoration:none}
  a:hover {color:#007cd0;text-decoration:none}
</style>

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
        <li><a href="/board/shop/lists?shopId=sale">할인</a></li>
        <li><a href="/board/shop/lists?shopId=skin">스킨</a></li>
        <li><a href="/board/shop/lists?shopId=cloth">의류</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="/user/info/inventory">인벤토리</a></li>
        <li><a href="/user/logout">로그아웃</a></li>
      </ul>
    </div>
  </div>
</nav>
<!--상단 메뉴 바-->
<br>

<div class="container">
<input type="button" value="물품추가" id="item_add" onclick="location.href='/board/shop/insert?shopId=${shopId}'">
  <br><br>
</div>
<div class="container">
  <div class="jumbotron">
	
	
<c:forEach var="I" items="${itemLists}">
    <figure class="snip1583">
      <img src="/static/shopitem/${I.imgName}"/>
      <div class="icons"><a><i class="ion-android-cart" onClick="location.href='/board/shop/buy?shopId=${shopId}&sno=${I.sno}'"></i></a></div>
      <figcaption>
        <h3>${I.itemName }</h3>
        <div class="price">${I.price}</div>
      </figcaption>
    </figure>
</c:forEach>


 
    

  </div>
</div>
<script type="text/javascript" src="/static/js/shop/Cart.js"></script>
</body>
</html>
