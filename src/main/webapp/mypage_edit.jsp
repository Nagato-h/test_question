<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// Servletのデータ受け取り
	request.setCharacterEncoding("UTF8");
	int userId = (int)session.getAttribute("user_id");
	String userName = (String)session.getAttribute("user_name");
	String password = (String)session.getAttribute("password");
	String edit_userName = (String)session.getAttribute("edit_user_name");
	String edit_password = (String)session.getAttribute("edit_password");
	String errorMessage_UserPassword = (String) request.getAttribute("error_user_password");
	String errorMessage_PassPassword = (String) request.getAttribute("error_pass_password");
	String errorMessage_NewUserName = (String) request.getAttribute("error_NewUserName");
	String errorMessage_NewPassword = (String) request.getAttribute("error_NewPassword");
	String completed_NewUserName = (String) request.getAttribute("edit_completed_user");
	String completed_NewPassword = (String) request.getAttribute("edit_completed_pass");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MYPAGE EDIT</title>
<%@ include file="common_parts/link.jsp"%>
</head>
<body>
<div align="center">
	<%@ include file="common_parts/header.jsp"%>
	<div class="content">
		<% if(completed_NewUserName != null) { %>
			<p class="completed_message"><%= completed_NewUserName %></p>
		<% } else if(completed_NewPassword != null) { %>
			<p class="completed_message"><%= completed_NewPassword %></p>
		<% } %>
		<dl class="mypage_edit_list">
			<dt class="mypage_edit_heading">
				<label><span class="material-symbols-outlined">numbers</span><b><%= userId %></b></label>
				<label class="not_edit"><span class="material-symbols-outlined">error</span>ユーザーIDは変更できません</label>
			</dt>
			<dt class="mypage_edit_heading">
				<label><span class="material-symbols-outlined">face</span><b><%= userName %></b></label>
				<button class="edit_btn">編集</button>
			</dt>
			<% if(errorMessage_NewUserName != null || errorMessage_UserPassword != null) { %>
			<dd class="mypage_edit_content" style="display: block;">
			<% } else { %>
			<dd class="mypage_edit_content">
			<% } %>
				<form action="./MypageEditUser" method="post" class="mypage_edit">
					<div class="form_input">
						<label>新しいユーザー名</label>
						<% if(errorMessage_NewUserName != null || errorMessage_UserPassword != null) { %>
							<input type="text" name="new_user_name" value="<%= edit_userName %>" placeholder="NEW USER NAME">
						<% } else { %>
							<input type="text" name="new_user_name" placeholder="NEW USER NAME">
						<% } %>
					</div>
					<% if(errorMessage_NewUserName != null) { %>
						<p class="error_message"><%= errorMessage_NewUserName %></p>
					<% } %>
					<div class="form_input">
						<label>パスワード</label>
						<input type="password" name="user_password" placeholder="PASSWORD">
					</div>
					<% if(errorMessage_UserPassword != null) { %>
						<p class="error_message"><%= errorMessage_UserPassword %></p>
					<% } %>
					<button type="submit" class="mypage_edit_button">変更</button>
				</form>
			</dd>
			<dt class="mypage_edit_heading">
				<label><span class="material-symbols-outlined">vpn_key</span><b class="password"><%= password %></b></label>
				<button class="edit_btn">編集</button>
			</dt>
			<% if(errorMessage_NewPassword != null || errorMessage_PassPassword != null) { %>
			<dd class="mypage_edit_content" style="display: block;">
			<% } else { %>
			<dd class="mypage_edit_content">
			<% } %>
				<form action="./MypageEditPassword" method="post" class="mypage_edit">
					<div class="form_input">
						<label>新しいパスワード</label>
						<% if(errorMessage_NewPassword != null || errorMessage_PassPassword != null) { %>
							<input type="password" name="new_password" value="<%= edit_password %>" placeholder="NEW PASSWORD">
						<% } else { %>
							<input type="password" name="new_password" placeholder="NEW PASSWORD">
						<% } %>
					</div>
					<% if(errorMessage_NewPassword != null) { %>
						<p class="error_message"><%= errorMessage_NewPassword %></p>
					<% } %>
					<div class="form_input">
						<label>パスワード</label>
						<input type="password" name="pass_password" placeholder="PASSWORD">
					</div>
					<% if(errorMessage_PassPassword != null) { %>
						<p class="error_message"><%= errorMessage_PassPassword %></p>
					<% } %>
					<button type="submit" class="mypage_edit_button">変更</button>
				</form>
			</dd>
		</dl>
		<button onclick="location.href='./Mypage'" class="mypage_return_button">マイページに戻る</button>
	</div>
</div>
<script>
$(function() {
	// 変更トグル
	$('.edit_btn').click(function(){
		$(this).parent().next('.mypage_edit_content').slideToggle(editBtnToggle);
	});
	// 編集・閉じるの切り替え
	function editBtnToggle(){
		if ($(this).css('display') == 'block') {
			$(this).prev().children('.edit_btn').text('閉じる');
		} else {
			$(this).prev().children('.edit_btn').text('編集');
		}
	};
	// パスワード伏字
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