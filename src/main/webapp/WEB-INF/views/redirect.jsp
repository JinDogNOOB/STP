<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="html/text; charset=UTF-8">
<title>redirect</title>
</head>
<body>

	<script type="text/javascript">
		var message='${msg}';
		var returnUrl='${url}';
		alert(message);
		document.location.href = returnUrl;
	</script>


</body>
</html>