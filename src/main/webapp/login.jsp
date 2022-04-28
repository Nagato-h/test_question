<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// Servletのデータ受け取り
	request.setCharacterEncoding("UTF8");
	String errorMessage_id = (String) request.getAttribute("error_1");
	String errorMessage_pass = (String) request.getAttribute("error_2");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOGIN</title>
<%@ include file="common_parts/link.jsp"%>
</head>
<body>
<div align="center">
	<%@ include file="common_parts/header.jsp"%>
	<div class="content">
		<form action="./login" method="post" class="login">
			<div class="form_input">
				<input type="text" name="id" placeholder="ID">
				<span class="material-symbols-outlined">face</span>
			</div>
			<% if(errorMessage_id != null) { %>
			<p class="error_message"><%= errorMessage_id %></p>
			<% } %>
			<div class="form_input">
				<input type="password" name="password" placeholder="PASSWORD">
				<span class="material-symbols-outlined">vpn_key</span>
			</div>
			<% if(errorMessage_pass != null) { %>
			<p class="error_message"><%= errorMessage_pass %></p>
			<% } %>
			<button type="submit" class="login">ログイン</button>
		</form>
	</div>
</div>
</body>
</html>