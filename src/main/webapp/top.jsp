<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// Servletのデータ受け取り
	request.setCharacterEncoding("UTF8");
	int userId = (int)session.getAttribute("user_id");
	String userName = (String)session.getAttribute("user_name");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TOP</title>
<%@ include file="common_parts/link.jsp"%>
</head>
<body>
<div align="center">
	<%@ include file="common_parts/header.jsp"%>
	<div class="content">
		<p class="login_message">ようこそ <%= userName %> さん</p>
		<ol class="top_menu">
			<% if (userId == 1){ // 管理者(id:1)しか確認・登録できないように設定 %>
			<li>
				<button onclick="location.href='./List'">
					問題と答えの確認・登録
					<span class="material-symbols-outlined">quiz</span>
				</button>
			</li>
			<% } %>
			<li>
				<button onclick="location.href='./Test'">
					テスト開始
					<span class="material-symbols-outlined">import_contacts</span>
				</button>
			</li>
			<li>
				<button onclick="location.href='history.jsp'">
					過去の採点結果
					<span class="material-symbols-outlined">history</span>
				</button>
			</li>
			<li>
				<button onclick="location.href='./Mypage'">
					マイページ
					<span class="material-symbols-outlined">face</span>
				</button>
			</li>
		</ol>
	</div>
</div>
</body>
</html>