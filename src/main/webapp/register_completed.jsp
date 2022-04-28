<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// Servletのデータ受け取り
	response.setCharacterEncoding("UTF-8");
	String question = (String)session.getAttribute("question");
	String[] answers = (String[])session.getAttribute("answers");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>REGISTER COMPLETED</title>
<%@ include file="common_parts/link.jsp"%>
</head>
<body>
<div align="center">
	<%@ include file="common_parts/header.jsp"%>
	<div class="content">
		<div class="quiz_form complete">
			<p class="complete_message">下記の問題を登録しました。</p>
			<div class="question">
				<label>問題</label>
				<p><%= question %></p>
			</div>
			<div class="answers">
				<label>答え</label>
				<ul class="answer_list">
					<% for(int i = 0; i < answers.length; i++) { %>
						<li>
							<div class="answer"><p><%= answers[i] %></p></div>
						</li>
					<% } %>
				</ul>
			</div>
			<div class="quiz_form_button">
				<button onclick="location.href='./List'">一覧に戻る</button>
				<button onClick="location.href='./Register'" class="add_register">追加登録</button>
			</div>
		</div>
	</div>
</div>
</body>
</html>