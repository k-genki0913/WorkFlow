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
<table border="1">
	<tr>
		<th>フォームNo.</th><th>申請書類</th><th>申請者</th>
	</tr>
	<c:forEach var="document" items="${RemandList}">
		<tr>
			<td><a href="#" onclick="document.hidden.submit();"><c:out value="${document.getFormID() }" /></a></td>
			<form name="hidden" method="get" action="RemandView">
			<input type="hidden" name="formID" value="${document.getFormID() }">
			</form>
			<td><c:out value="${document.getDocumentName()}" /></td>
			<td><c:out value="${document.getApplicantName()}"/></td>
	</c:forEach>
</table>
</body>
</html>