<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>稟議書(新規作成)</title>
</head>
<body>
<h1>稟議書</h1>
<a href="NewDocument">申請書選択画面へ戻る</a>
<c:out value="${errMsg }" />
<p>申請者：<c:out value="${Account.getName() }"></c:out></p>
<p>部署：<c:out value="${Dc.departmentName(Account.getDepartment())}"></c:out></p>
<form action="NewRingisho" method="post">
申請理由：<br>
<textarea name="contents" rows="10" cols="60"></textarea><br>
<input type="hidden" name="applicantName" value="${Account.getId() }">
<input type="hidden" name="departmentID" value="${Account.getDepartment() }">
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