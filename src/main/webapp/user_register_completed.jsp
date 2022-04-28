<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// Servletのデータ受け取り
	response.setCharacterEncoding("UTF-8");
	int user_id = (int)session.getAttribute("user_id");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>USER REGISTER COMPLETED</title>
<%@ include file="common_parts/link.jsp"%>
</head>
<body>
<div align="center">
	<%@ include file="common_parts/header.jsp"%>
	<div class="content">
		<div class="user_register complete">
			<p class="complete_message">
				ユーザー登録が完了しました。<br>
				あなたのユーザーIDは<span><%= user_id %></span>です。
			</p>
			<div class="user_register_button">
				<button onClick="location.href='login.jsp'">ログイン</button>
			</div>
		</div>
	</div>
</div>
</body>
</html>