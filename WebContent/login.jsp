<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style>
	*{
		margin:0;
		padding:0;
	}
	div{
		width:600px;
		margin:250px auto;
	}
</style>
</head>
<body>
	<div>
		<form action="login.do" method="get">
			XX회사 인사관리프로그램<br>
			이름 : <input type="text" id="name" name="name"><br>
			사번 : <input type="password" id="pass" name="eno"><br>
			<button>로그인</button>
		</form>
	</div>
</body>
</html>