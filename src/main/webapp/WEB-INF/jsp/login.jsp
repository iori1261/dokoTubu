<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>
<h1>ログイン</h1>
<% String registerSuccess = (String) request.getAttribute("registerSuccess"); %>
<% if (registerSuccess != null) { %>
    <p><%= registerSuccess %></p>
<% } %>
<form action="<%= request.getContextPath() %>/Login" method="post">
    ユーザ名：<input type="text" name="name"><br>
    パスワード：<input type="password" name="pass"><br>
    <input type="submit" value="ログイン">
</form>
<a href="<%= request.getContextPath() %>/Register">新規ユーザー登録</a>
</body>
</html>
