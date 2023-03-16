<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログアウト画面</title>
</head>
<body>
<form action="Logout" method="post">
ログアウトしますか？<br>
<input type="radio" name="logout" value="1">はい<br>
<input type="radio" name="logout" value="2">いいえ<br>
<input type="submit" value="確定">
</form>
</body>
</html>