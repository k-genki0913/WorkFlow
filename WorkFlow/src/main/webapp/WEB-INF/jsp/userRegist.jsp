<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録依頼書</title>
</head>
<body>
<h1>ユーザー登録依頼書</h1>
<c:out value="${errMsg }" />
<a href="NewDocument">申請書選択画面へ戻る</a>
<p>申請者：<c:out value="${Account.getName() }" /></p>
<p>部署：<c:out value="${Dc.departmentName(Account.getDepartment()) }" /></p>
<br>
<p>依頼内容</p>
<form action="NewUserRegist" method="post">
<input type="hidden" name="applicantName" value="${Account.getId() }">
<input type="hidden" name="applicantDepartment" value="${Account.getDepartment() }">
氏名：<input type="text" name="name"><br>
部署：<select name="department">
		<option value="1">営業部</option>
		<option value="2">技術部</option>
		<option value="3">管理部</option>
	</select><br>
役職：<select name="position">
		<option value="1">部長</option>
		<option value="2">課長</option>
		<option value="3">係員</option>
	</select><br>
<input type="submit" value="申請">
</form>
<table border="1">
<tr>
	<th>課長承認</th><th>部長承認</th>
</tr>
<tr>
	<td></td><td></td>
</tr>
</table>
</body>
</html>