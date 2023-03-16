<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ホーム画面</title>
</head>
<body>
<table border="1">
<tr>
	<td><a href="">申請書新規作成</a></td>
	<td><a href="">検索</a></td>
	<td><a href="">申請中</a></td>
	<c:if test="${Account.getPosition() == 1 }">
		<td><a href="http://localhost:8080/WorkFlow/RegistUser">管理者機能</a></td>
	</c:if>
	<td><a href="http://localhost:8080/WorkFlow/Logout">ログアウト</a></td>
</tr>
</table>
</body>
</html>