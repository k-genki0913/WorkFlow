<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>稟議書(承認済み)</title>
</head>
<body>
<h1>稟議書(承認済み)</h1>
<a href="Home">ホーム画面へ戻る</a>
<p>申請者：<c:out value="${Uic.getName(Ringisho.getApplicantName()) }" /></p>
<p>部署：<c:out value="${Dc.departmentName(Ringisho.getDepartmentID())}"></c:out>
<p>申請理由：</p>
<p style="border-width:1px; border-style:solid;" width:1000px;
  height:250px;
  border:1px solid black;
  word-wrap: break-word;>
  <c:out value="${Ringisho.getContents() }" />
</p>
<table border="1">
<tr>
	<th>課長承認</th><th>部長承認</th>
</tr>
<tr>
	<td><c:out value="${Uic.getName(Ringisho.getMApprover()) }" /></td>
	<td><c:out value="${Uic.getName(Ringisho.getGMApprover()) }" /></td>
</tr>
</body>
</html>