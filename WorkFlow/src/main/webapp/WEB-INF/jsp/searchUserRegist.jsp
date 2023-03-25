<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>確認画面</title>
</head>
<body>
<h1>ユーザー登録依頼書</h1>
<a href="Home">ホーム画面へ戻る</a>
<p>申請者：<c:out value="${Uic.getName(UserRegist.getApplicantName()) }" /></p>
<p>部署：<c:out value="${Dc.departmentName(UserRegist.getDepartmentID()) }" /></p>
<br>
<p>依頼内容</p>
<p>氏名：<c:out value="${UserRegist.getRegistName() }" /></p>
<p>部署：<c:out value="${Dc.departmentName(UserRegist.getRegistDepartment()) }"/></p>
<p>役職：<c:out value="${Pc.positionName(UserRegist.getRegistPosition()) }" /></p>
<br>
<table border="1">
<tr>
	<th>課長承認</th><th>部長承認</th>
</tr>
<tr>
	<td><c:out value="${Uic.getName(UserRegist.getMApprover()) }" /></td>
	<td><c:out value="${Uic.getName(UserRegist.getGMApprover()) }" /></td>
</tr>
</table>
</body>
</html>