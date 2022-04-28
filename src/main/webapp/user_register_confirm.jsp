<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// Servletのデータ受け取り
	response.setCharacterEncoding("UTF-8");
	String user_name = (String)session.getAttribute("user_name");
	String password = (String)session.getAttribute("password");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CONFIRM</title>
<%@ include file="common_parts/link.jsp"%>
</head>
<body>
<div align="center">
	<%@ include file="common_parts/header.jsp"%>
	<div class="content">
		<form action="./UserRegisterCompleted" method="post" class="user_register confirm">
			<p class="confirm_message">下記の情報を登録します。</p>
			<div class="form_input">
				<span class="material-symbols-outlined">face</span>
				<p><%= user_name %></p>
			</div>
			<div class="form_input">
				<span class="material-symbols-outlined">vpn_key</span>	
				<p class="password"><%= password %></p>
			</div>
			<div class="user_register_button">
				<button onClick="javascript:window.history.back(-1);return false;">戻る</button>
				<button type="submit">登録</button>
			</div>
		</form>
	</div>
</div>
<script>
$(function() {
	var password = $('.password').html(),
		hidden = "***",
		start = password.slice(0,1),
		end = password.slice(-1),
		hidden_password = start + hidden + end;
	$('.password').html(hidden_password);
});
</script>
</body>
</html>