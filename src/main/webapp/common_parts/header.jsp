<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="header">
	<div class="header_logo">
		<h1>Quiz</h1>
		<p>Test scoring system</p>
	</div>
	<% String URI = request.getRequestURI(); %>
	<% if(URI.equals("/test_question/user_register.jsp") || URI.equals("/test_question/user_register_confirm.jsp") || URI.equals("/test_question/user_register_completed.jsp")) { %>
	<% } else { %>
		<div class="header_heading">
			<% if(URI.equals("/test_question/login.jsp")) { %>
				<button onclick="location.href='user_register.jsp'">ユーザー登録</button>
			<% } else if(URI.equals("/test_question/error.jsp") || URI.equals("/test_question/logout.jsp") || URI.equals("/test_question/mypage_delete_completed.jsp")) { %>
				<button onclick="location.href='login.jsp'">ログイン</button>
				<button onclick="location.href='user_register.jsp'">ユーザー登録</button>
			<% } else { %>
				<% if(!(URI.equals("/test_question/top.jsp"))) { %>
					<button onclick="location.href='top.jsp'">トップ</button>
				<% } %>
				<button onclick="location.href='./Logout'">ログアウト</button>
			<% } %>
		</div>
	<% } %>
</div>