<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// Servletのデータ受け取り
	request.setCharacterEncoding("UTF8");
	String userName = (String)session.getAttribute("user_name");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MYPAGE</title>
<%@ include file="common_parts/link.jsp"%>
</head>
<body>
<div align="center">
	<%@ include file="common_parts/header.jsp"%>
	<div class="content">
		<p class="login_message">ようこそ <%= userName %> さん</p>
		<ol class="mypage_menu">
			<li>
				<button onclick="location.href='./MypageEdit'">
					ユーザー情報の確認・変更
					<span class="material-symbols-outlined">settings</span>
				</button>
			</li>
			<li>
				<button onclick="location.href='./MypageDelete'">
					ユーザー情報の削除
					<span class="material-symbols-outlined">delete</span>
				</button>
			</li>
		</ol>
	</div>
</div>
</body>
</html>