<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// Servletのデータ受け取り
	request.setCharacterEncoding("UTF8");
	String errorMessage_user = (String) request.getAttribute("error_1");
	String errorMessage_pass = (String) request.getAttribute("error_2");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>USER REGISTER</title>
<%@ include file="common_parts/link.jsp"%>
</head>
<body>
<div align="center">
	<%@ include file="common_parts/header.jsp"%>
	<div class="content">
		<form action="./UserRegisterConfirm" method="post" class="user_register">
			<div class="form_input">
				<input type="text" name="user_name" placeholder="USER NAME">
				<span class="material-symbols-outlined">face</span>
			</div>
			<% if(errorMessage_user != null) { %>
			<p class="error_message"><%= errorMessage_user %></p>
			<% } %>
			<div class="form_input">
				<input type="password" name="password" placeholder="PASSWORD">
				<span class="material-symbols-outlined">vpn_key</span>
			</div>
			<% if(errorMessage_pass != null) { %>
			<p class="error_message"><%= errorMessage_pass %></p>
			<% } %>
			<div class="user_register_button">
				<button type="submit">確認</button>
			</div>
		</form>
	</div>
</div>
</body>
</html>