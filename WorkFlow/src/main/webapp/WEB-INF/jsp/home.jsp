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
	<td><a href="http://localhost:8080/WorkFlow/NewDocument">申請書新規作成</a></td>
	<td><a href="Search">検索</a></td>
	<td><a href="RemandList">差し戻し</a></td>
	<td><a href="ApprovedList">承認済み</a>
	<c:if test="${Account.getPosition() == 1 }">
		<td><a href="http://localhost:8080/WorkFlow/AdminWindow">管理者機能</a></td>
	</c:if>
	<td><a href="http://localhost:8080/WorkFlow/Logout">ログアウト</a></td>
</tr>
</table>
<br>
<p>承認待ち一覧</p>
<table border="1">
<tr>
	<th>フォームNo.</th><th>申請書類</th><th>申請者</th><th>承認</th>
</tr>
<c:forEach var="document" items="${HomeList}">
	<tr>
		<td><c:out value="${document.getFormID() }" /></td>
		<td><c:out value="${document.getDocumentName()}" /></td>
		<td><c:out value="${Uic.getName(document.getApplicantName())}"/></td>
		<td><form name="approve" method="get" action="DocumentApprove">
			<input type="submit" value="承認画面へ">
			<input type="hidden" name="formID" value="${document.getFormID() }">
			<input type="hidden" name="documentTable" value="${document.getDocumentTable() }">
			</form>
		</td>
	</tr>
</c:forEach>
</table>
</body>
</html>