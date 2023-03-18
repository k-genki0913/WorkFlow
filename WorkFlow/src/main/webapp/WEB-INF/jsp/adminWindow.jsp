<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者画面</title>
</head>
<body>
<!-- 管理者機能を追加する場合にこちらにリンクを作成していく予定 -->
<table border="1">
<tr>
	<td><a href="http://localhost:8080/WorkFlow/RegistUser">ユーザー登録</a></td>
	<td><a href="http://localhost:8080/WorkFlow/Home">ホーム画面へ戻る</a>
<tr>
<p>ユーザー一覧</p>
<table border="1">
<tr>
	<th>ユーザーID</th><th>名前</th><th>部署</th><th>役職</th>
</tr>
<c:forEach var="account" items="${AllUserList}">
	<tr>
		<td><c:out value="${account.getId() }" /></td>
		<td><c:out value="${account.getName() }"/></td>
		<td><c:out value="${Dc.departmentName(account.getDepartment()) }"/></td>
		<td><c:out value="${Pc.positionName(account.getPosition()) }"/></td>
	</tr>
</c:forEach>
</table>
</body>
</html>