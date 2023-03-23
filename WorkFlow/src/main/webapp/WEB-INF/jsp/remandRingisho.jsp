<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>稟議書(差し戻し)</title>
</head>
<body>
<h1>稟議書(差し戻し)</h1>
<c:out value="${errMsg }" />
<a href="Home">ホーム画面へ戻る</a>
<p>申請者：<c:out value="${Uic.getName(Ringisho.getApplicantName()) }" /></p>
<p>部署：<c:out value="${Dc.departmentName(Account.getDepartment())}"></c:out></p>
<form action="ReRingisho" method="post">
申請理由：<br>
<!-- textareaの初期値はJSTLを使うと<c:out/>から表示されてしまうので、EL式で対応 -->
<textarea name="contents" rows="10" cols="60">${Ringisho.getContents() }</textarea><br>
<input type="hidden" name="formID" value="${Ringisho.getFormID() }">
<input type="hidden" name="documentName" value="RINGISHO">
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