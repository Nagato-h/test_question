<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// Servletのデータ受け取り
	response.setCharacterEncoding("UTF-8");
	String edit_question = (String)session.getAttribute("edit_question");
	String[] edit_answers = (String[])session.getAttribute("edit_answers");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EDIT CONFIRM</title>
<%@ include file="common_parts/link.jsp"%>
</head>
<body>
<div align="center">
	<%@ include file="common_parts/header.jsp"%>
	<div class="content">
		<form action="./EditCompleted" method="post" class="quiz_form edit confirm">
			<p class="confirm_message">
				<span class="material-symbols-outlined">error</span> 下記の問題を編集します
			</p>
			<div class="question">
				<label>問題</label>
				<p><%= edit_question %></p>
			</div>
			<div class="answers">
				<label>答え</label>
				<ul class="answer_list">
					<% for(int i = 0; i < edit_answers.length; i++) { %>
						<li>
							<div class="answer"><p><%= edit_answers[i] %></p></div>
						</li>
					<% } %>
				</ul>
			</div>
			<div class="quiz_form_button">
				<button onClick="javascript:window.history.back(-1);return false;">戻る</button>
				<button type="submit">編集</button>
			</div>
		</form>
	</div>
</div>
</body>
</html>