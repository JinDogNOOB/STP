<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<!-- JSTL tag -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <title>AdminPage</title>
 
</head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
      <a class="navbar-brand" href="/user/info/mypage"><strong>마이페이지(메인으로)</strong></a>  
</nav>


      <h1 class="page-header">관리자 페이지</h1>

      <div class="row placeholders">
      <h2 class="sub-header">회원 목록 관리</h2>
      <h2>가입자 수 '${usercount}'</h2>
      <div class="table-responsive">
        <table class="table table-striped" class="col-md-10">
          <thead>
          <tr>
            <th>아이디</th>
            <th>닉네임</th>
            <th>이메일</th>
            <th>권한</th>
            <th>포인트</th>
            <th>가입날짜</th>
            <th>수정</th>
            <th>삭제</th>
          </tr>
          </thead>
          <tbody id="user_list_tbody">
            <c:forEach var="I" items="${userlist }">
   			 <tr>
        		<td>${I.uid }</td>     			
        		<td>${I.uname}</td>
       			<td>${I.email}</td>
       			<td>${I.auth }</td>
       			<td>${I.money }</td>
       			<td>${I.update_log }
       			<td><button type="button" value="유저 수정" onclick="location.href='/admin/user/modify?uno=${I.uno}'"></button></td>
             	<td><button type="button" value="유저 삭제" onclick="location.href='/admin/user/deleteProc?uno=${I.uno}'"></button></td>
   			 </tr>
    		</c:forEach>
            
         

         
          </tbody>
        </table>
      </div>
    </div>



</body>
</html>

