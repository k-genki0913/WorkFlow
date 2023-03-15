<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
</head>
<body>
<h1>ワークフロー　ログイン</h1>
<c:out value="${errMsg }" />
<form action="Login" method="post">
ログインID：<input type="text" name="userId"><br>
パスワード：<input type="password" name="password"><br>
<input type="submit" value="ログイン">
</form>
</body>
</html>