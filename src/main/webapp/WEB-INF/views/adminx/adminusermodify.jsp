<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>usermodifyPage</title>
</head>
<body>





        <table class="table table-striped" class="col-md-10">
          <thead>
          <tr>
            <th>닉네임</th>
            <th>비밀번호</th>
            <th>권한</th>
            <th>포인트</th>
            <th>확인</th>
          </tr>
          </thead>
          <tbody id="user_list_tbody">
           
   			 <tr>
   			 <form action="/admin/user/modifyProc?uno=${user.uno }" method="post">
        		<td><input type="text" id="uname" name="uname" value=${user.uname }></td>
        		<td><input type="text" id="upassword" name="upassword"></td> 
        		<td>
        			<select name="auth">
        				<option value=${user.auth } selected="selected">원래권한</option>
        				<option value="0">새싹회원</option>
        				<option value="1">정회원</option>
        				<option value="99">어드민</option>
        			</select>
        		</td>
				<td><input type="text" id="money" name="money" value=${user.money }></td>
        		<td><button type="submit" value="확인"></button></td> 	
   			</form>
   			 </tr>
   			 
    		</tbody>
    		</table>


















</body>
</html>