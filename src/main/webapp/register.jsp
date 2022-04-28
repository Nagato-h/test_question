<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// Servletのデータ受け取り
	request.setCharacterEncoding("UTF8");
	String question = (String)session.getAttribute("question");
	String[] answers = (String[])session.getAttribute("answers");
	String errorMessage_question = (String) request.getAttribute("error_1");
	String errorMessage_answers = (String) request.getAttribute("error_2");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>REGISTER</title>
<%@ include file="common_parts/link.jsp"%>
</head>
<body>
<div align="center">
	<%@ include file="common_parts/header.jsp"%>
	<div class="content">
		<form action="./Confirm" method="post" class="quiz_form">
			<% if(errorMessage_question != null || errorMessage_answers != null) { %>
				<% if(errorMessage_question != null) { %>
					<p class="error_message"><%= errorMessage_question %></p>
				<% } %>
				<div class="question error">
					<label>問題</label>
					<textarea name="question" placeholder="問題文を入力してください"><%= question %></textarea>
				</div>
			<% } else { %>
				<div class="question">
					<label>問題</label>
					<textarea name="question" placeholder="問題文を入力してください"></textarea>
				</div>
			<% } %>
			<% if(errorMessage_question != null || errorMessage_answers != null) { %>
				<% if(errorMessage_answers != null) { %>
					<p class="error_message"><%= errorMessage_answers %></p>
				<% } %>
				<div class="answers error">
					<label>答え</label>
					<ul class="answer_list">
						<% for(int i = 0; i < answers.length; i++) { %>
							<li>
								<div class="answer"><input type="text" name="answers" value="<%= answers[i] %>" placeholder="答えを入力してください"></div>
								<% if(i == 0) { %>
									<button class="btn_add" type="button">追加</button>
								<% } else { %>
									<button type="button" class="btn_remove">削除</button>
								<% } %>
							</li>
						<% } %>
					</ul>
				</div>
			<% } else { %>
				<div class="answers">
					<label>答え</label>
					<ul class="answer_list">
						<li>
							<div class="answer"><input type="text" name="answers" placeholder="答えを入力してください"></div>
							<button class="btn_add" type="button">追加</button>
						</li>
					</ul>
				</div>
			<% } %>
			<div class="quiz_form_button">
				<button onClick="javascript:window.history.back(-1);return false;">戻る</button>
				<button type="submit">確認</button>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript" src="js/add_answers.js"></script>
</body>
</html>