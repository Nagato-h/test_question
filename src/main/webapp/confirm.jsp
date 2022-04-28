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
<title>CONFIRM</title>
<%@ include file="common_parts/link.jsp"%>
</head>
<body>
<div align="center">
	<%@ include file="common_parts/header.jsp"%>
	<div class="content">
		<form action="./RegisterCompleted" method="post" class="quiz_form confirm">
			<p class="confirm_message">下記の問題を登録します。</p>
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
				<button onClick="javascript:window.history.back(-1);return false;">戻る</button>
				<button type="submit">登録</button>
			</div>
		</form>
	</div>
</div>
</body>
</html>