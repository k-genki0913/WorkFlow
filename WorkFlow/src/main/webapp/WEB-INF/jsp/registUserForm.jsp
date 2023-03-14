<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録画面</title>
</head>
<body>
<h1>ユーザー登録</h1>
<form action="RegistUser" method="post">
ログインID：<input type="text" name="ID"><br>
(IDはローマ字(小文字)とハイフンのみ利用可能)<br>
パスワード：<input type="text" name="password"><br>
(パスワードはローマ字(大文字、小文字どちらも利用可能)、数字のみ利用可能)<br>
氏名：<input type="text" name="name"><br>
部署：
<select name="department">
	<option value="1">営業部</option>
	<option value="2">技術部</option>
	<option value="3">管理部</option>
</select><br>
役職：
<select name="position">
	<option value="1">部長</option>
	<option value="2">課長</option>
	<option value="3">係員</option>
</select><br>
<input type="submit" value="登録">

</form>
</body>
</html>