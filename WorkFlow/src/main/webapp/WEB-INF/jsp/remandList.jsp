<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>否認一覧</title>
</head>
<body>
<h1>否認一覧</h1>
<a href="Home">ホーム画面へもどる</a>
<table border="1">
	<tr>
		<th>フォームNo.</th><th>申請書類</th><th>申請者</th>
	</tr>
	<c:forEach var="document" items="${RemandList}">
		<tr>
			<td><c:out value="${document.getFormID() }" /></td>
			<td><c:out value="${document.getDocumentName()}" /></td>
			<td><c:out value="${document.getApplicantName()}"/></td>
			<td><form name="remand" method="get" action="RemandView">
				<input type="submit" value="確認画面へ">
				<input type="hidden" name="formID" value="${document.getFormID() }">
				<input type="hidden" name="documentTable" value="${document.getDocumentTable() }">
				</form>
			</td>
	</c:forEach>
</table>
</body>
</html>