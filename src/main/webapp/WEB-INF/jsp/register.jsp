<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規ユーザー登録</title>
</head>
<body>
<h1>新規ユーザー登録</h1>
<form action="<%= request.getContextPath() %>/Register" method="post">
    ユーザ名：<input type="text" name="name"><br>
    パスワード：<input type="password" name="pass"><br>
    <input type="submit" value="登録">
</form>
</body>
</html>
