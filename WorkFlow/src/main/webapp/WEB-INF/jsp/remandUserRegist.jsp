<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録依頼書(差し戻し)</title>
</head>
<body>
<h1>ユーザー登録依頼書(差し戻し)</h1>
<c:out value="${errMsg }"/>
<a href="Home">ホーム画面へ戻る</a>
<p>申請者：<c:out value="${Uic.getName(UserRegist.getApplicantName()) }" /></p>
<p>部署：<c:out value="${Dc.departmentName(UserRegist.getDepartmentID()) }" /></p>
<br>
<p>依頼内容</p>
<form action="ReUserRegist" method="post">
氏名：<input type="text" name="name" value="${UserRegist.getRegistName() }"><br>
部署：<select name="department">
		<option value="1">営業部</option>
		<option value="2">技術部</option>
		<option value="3">管理部</option>
	</select><br>
(前回申請部署：<c:out value="${Dc.departmentName(UserRegist.getDepartmentID()) }" />)<br>
役職：<select name="position">
		<option value="1">部長</option>
		<option value="2">課長</option>
		<option value="3">係員</option>
	</select><br>
(前回申請部署：<c:out value="${Pc.positionName(UserRegist.getRegistPosition()) }" />)<br>
<input type="hidden" name="formID" value="${UserRegist.getFormID() }">
<input type="hidden" name="documentName" value="UserRegist">
<input type="submit" value="再申請">
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