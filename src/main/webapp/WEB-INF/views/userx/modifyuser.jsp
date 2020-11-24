<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>슬기로운 스템프 STP!</title>
  <link rel="stylesheet" href="/static/css/dk/my_info.css">
  <link rel="stylesheet" href="/static/css/dk/alertify.core.css">
  <link rel="stylesheet" href="/static/css/dk/alertify.default.css">
  <script src="/static/js/dk/alertify.min.js"></script>
  <script src="/static/js/dk/my_info.js">
  </script>
</head>
<body>
  <div class="container">
    <form id="page-wrap" method="post" name="my_info_frame" onsubmit="return personal_info_modify();" action="/user/modifyProc" enctype="multipart/form-data">
      <div class="row">
        <h4>Account</h4>
        <div class="input-group input-group-icon">
          <input type="text" id="uname" name="uname" placeholder="Full Name"/>
          <div class="input-icon"><i class="fa fa-user"></i></div>
        </div>
        <div class="input-group input-group-icon">
          <input type="password" id="upassword" name="upassword" placeholder="Password"/>
          <div class="input-icon"><i class="fa fa-key"></i></div>
        </div>
        <div class="input-group input-group-icon">
          <input type="password" id="password_retry" name="password_retry" placeholder="Password Retype"/>
          <div class="input-icon"><i class="fa fa-key"></i></div>
        </div>
        <div>
        	<h3>썸네일 이미지</h3>
    		<input type="file" name="files">
  		</div>
      </div>
      <div class="row">
        <div class="col-half">
          <h4>Date of Birth</h4>
          <div class="input-group">
            <div class="col-third">
              <input type="text" placeholder="DD"/>
            </div>
            <div class="col-third">
              <input type="text" placeholder="MM"/>
            </div>
            <div class="col-third">
              <input type="text" placeholder="YYYY"/>
            </div>
          </div>
        </div>
        <div class="col-half">
          <h4>Gender</h4>
          <div class="input-group">
            <input type="radio" name="gender" value="male" id="gender-male"/>
            <label for="gender-male">Male</label>
            <input type="radio" name="gender" value="female" id="gender-female"/>
            <label for="gender-female">Female</label>
          </div>
        </div>
      </div>
      <div class="row">
        <h4>Terms and Conditions</h4>
        <div class="input-group">
          <input type="checkbox" id="terms"/>
          <label for="terms">I accept the terms and conditions for to this service, and hereby confirm I have read the privacy policy.</label>
        </div>
      </div>
      <input type="submit" id="info_modify" name="info_modify" value="modify">
      <input type="reset" id="reset" name="reset" value="reset">
      <input type="button" id="withdrawal" name="withdrawal" value="withdrawal">
    </form>
  </div>
</body>
</html>

