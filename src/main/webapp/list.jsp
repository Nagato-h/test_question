<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
    import="java.util.ArrayList"
    import="test_question.QuestionsBean"
    import="test_question.QuestionsDao"
    import="test_question.CorrectAnswersBean"
    import="test_question.CorrectAnswersDao"%>
<%
	// Servletのデータ受け取り
	response.setCharacterEncoding("UTF-8");
	ArrayList<QuestionsBean> q_list = (ArrayList<QuestionsBean>)request.getAttribute("question_list");
	ArrayList<CorrectAnswersBean> a_list = (ArrayList<CorrectAnswersBean>)request.getAttribute("answer_list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QUIZ LIST</title>
<%@ include file="common_parts/link.jsp"%>
</head>
<body>
<div align="center">
	<%@ include file="common_parts/header.jsp"%>
	<div class="content">
		<button class="register_button" onclick="location.href='./Register'">問題登録</button>
		<ul class="quiz_list">
			<% for (int q_i = 0; q_i < q_list.size(); q_i++ ){
				QuestionsBean que = q_list.get(q_i); %>
				<li>
					<div class="quiz">
						<div class="question">
							<span>Q<%= que.getId() %></span>
							<p><%= que.getQuestion() %></p>
						</div>
						<ol class="answers_list">
							<% int cnt = 1; %>
							<% for (int a_i = 0; a_i < a_list.size(); a_i++ ){
								CorrectAnswersBean ans = a_list.get(a_i); %>
								<% if (que.getId() == ans.getQuestionId()){ %>
								<li>
									<span>A<%= cnt %></span>
									<p><%= ans.getAnswer() %></p>
								</li>
								<% cnt = cnt + 1; %>
								<% } %>
							<% } %>
						</ol>
					</div>
					<div class="edit">
						<a href="./Edit?question_id=<%= que.getId() %>">編集</a>
						<a href="./Delete?question_id=<%= que.getId() %>">削除</a>
					</div>
				</li>
			<% } %>
		</ul>
	</div>
</div>
</body>
</html>