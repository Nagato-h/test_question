<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// Servletのデータ受け取り
	request.setCharacterEncoding("UTF8");
	int userId = (int)session.getAttribute("user_id");
	String userName = (String)session.getAttribute("user_name");
	String password = (String)session.getAttribute("password");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MYPAGE DELETE</title>
<%@ include file="common_parts/link.jsp"%>
</head>
<body>
<div align="center">
	<%@ include file="common_parts/header.jsp"%>
	<div class="content">
		<form action="./MypageDeleteCompleted" method="post" class="mypage_delete">
			<p class="confirm_message">
				<span class="material-symbols-outlined">error</span> 下記のユーザー情報を削除します
			</p>
			<dl class="mypage_delete_list">
				<dt class="mypage_edit_heading">
					<label><span class="material-symbols-outlined">numbers</span><b><%= userId %></b></label>
				</dt>
				<dt class="mypage_edit_heading">
					<label><span class="material-symbols-outlined">face</span><b><%= userName %></b></label>
				</dt>
				<dt class="mypage_edit_heading">
					<label><span class="material-symbols-outlined">vpn_key</span><b class="password"><%= password %></b></label>
				</dt>
			</dl>
			<div class="mypage_delete_button">
				<button onClick="javascript:window.history.back(-1);return false;">マイページに戻る</button>
				<button type="submit">削除</button>
			</div>
		</form>
	</div>
</div>
</body>
</html>