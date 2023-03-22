<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>稟議書(承認画面)</title>
</head>
<body>
<h1>稟議書</h1>
<c:out value="${registErrMsg }" />
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
	<td>
		<c:choose>
			<c:when test="${Ringisho.getMApprover() == null }">
				未承認
			</c:when>
			<c:otherwise>
				<c:out value="${Uic.getName(Ringisho.getMApprover()) }" />
			</c:otherwise>
		</c:choose>
	</td>
	<td>
		<c:choose>
			<c:when test="${Ringisho.getGMApprover() == null }">
				未承認
			</c:when>
			<c:otherwise>
				<c:out value="${Uic.getName(Ringisho.getGMApprover()) }" />
			</c:otherwise>
		</c:choose>
	</td>
</tr>
</table>
<form action="DocumentApprove" method="post">
<input type="radio" name="result" value="1">承認<br>
<input type="radio" name="result" value="-1">否認<br>
<input type="submit" value="確定">
<input type="hidden" name="approver" value="${Account.getId() }">
<input type="hidden" name="formID" value="${Ringisho.getFormID() }">
<input type="hidden" name="documentTable" value="${Ringisho.getDocumentTable() }">
</form>
</body>
</html>