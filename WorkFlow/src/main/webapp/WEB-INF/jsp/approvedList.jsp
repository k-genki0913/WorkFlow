<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>承認済み一覧</title>
</head>
<body>
<p>承認済み一覧</p>
<table border="1">
	<tr>
		<th>フォームNo.</th><th>申請書類</th><th>申請者</th>
	</tr>
	<c:forEach var="document" items="${ApprovedList }">
		<tr>
			<td><c:out value="${document.getFormID() }" /></td>
			<td><c:out value="${document.getDocumentName()}" /></td>
			<td><c:out value="${document.getApplicantName()}"/></td>
			<td><form name="confirm" method="get" action="ApprovedView">
				<input type="submit" value="確認">
				<input type="hidden" name="formID" value="${document.getFormID() }">
				<input type="hidden" name="documentTable" value="${document.getDocumentTable() }">
				</form>
			</td>
	</c:forEach>
</table>
</body>
</html>