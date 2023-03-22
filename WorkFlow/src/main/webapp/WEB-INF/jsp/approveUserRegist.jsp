<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録依頼書(承認画面)</title>
</head>
<body>
<h1>ユーザー登録依頼書</h1>
<c:out value="${errMsg }" />
<a href="NewDocument">申請書選択画面へ戻る</a>
<p>申請者：<c:out value="${Account.getName() }" /></p>
<p>部署：<c:out value="${Dc.departmentName(Account.getDepartment()) }" /></p>
<br>
<p>依頼内容</p>
<p>氏名：<c:out value="${UserRegist.getRegistName() }" /></p>
<p>部署：<c:out value="${Dc.departmentName(UserRegist.getRegistDepartment()) }" /></p>
<p>役職：<c:out value="${Pc.positionName(UserRegist.getRegistPosition()) }" /></p>
<br>
<table border="1">
<tr>
	<th>課長承認</th><th>部長承認</th>
</tr>
<tr>
	<td>
		<c:choose>
			<c:when test="${UserRegist.getMApprover() == null }">
				未承認
			</c:when>
			<c:otherwise>
				<c:out value="${Uic.getName(UserRegist.getMApprover()) }" />
			</c:otherwise>
		</c:choose>
	</td>
	<td>
		<c:choose>
			<c:when test="${UserRegist.getGMApprover() == null }">
				未承認
			</c:when>
			<c:otherwise>
				<c:out value="${Uic.getName(UserRegist.getGMApprover()) }"/>
			</c:otherwise>
		</c:choose>
	</td>
</table>
<br>
<form action="DocumentApprove" method="post">
<input type="radio" name="result" value="1">承認<br>
<input type="radio" name="result" value="-1">否認<br>
<input type="submit" value="確定">
<input type="hidden" name="approver" value="${Account.getId() }">
<input type="hidden" name="formID" value="${UserRegist.getFormID() }">
<input type="hidden" name="documentTable" value="${UserRegist.getDocumentTable() }">
</form>
</body>
</html>