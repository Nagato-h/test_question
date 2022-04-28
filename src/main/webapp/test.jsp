<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
    import="java.util.ArrayList"
    import="test_question.QuestionsBean"
    import="test_question.QuestionsDao"%>
<%
	// Servletのデータ受け取り
	response.setCharacterEncoding("UTF-8");
	ArrayList<QuestionsBean> q_list = (ArrayList<QuestionsBean>)request.getAttribute("question_list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TEST</title>
<%@ include file="common_parts/link.jsp"%>
</head>
<body>
<div align="center">
	<%@ include file="common_parts/header.jsp"%>
	<div class="content">
		<section class="test_content">
			<form action="./Scoring" method="post" class="test_form">
				<ul class="test_list">
					<% int cnt = 1; %>
					<% for (int q_i = 0; q_i < q_list.size(); q_i++ ){
						QuestionsBean que = q_list.get(q_i); %>
						<% if (cnt == 1){ %>
						<li class="test active">
						<% } else { %>
						<li class="test">
						<% } %>
							<h3>Q<%= que.getId() %></h3>
							<div class="question">
								<p><%= que.getQuestion() %></p>
							</div>
							<div class="answer">
								<input type="text" name="answers" placeholder="答えを入力してください">
							</div>
						</li>
						<% cnt = cnt + 1; %>
					<% } %>
				</ul>
				<div class="test_form_button">
					<button type="button" class="change_btn prev inactive">
						<span class="material-symbols-outlined">arrow_back_ios_new</span>
					</button>
					<button type="button" class="change_btn next">
						<span class="material-symbols-outlined">arrow_forward_ios</span>
					</button>
				</div>
				<button type="submit">採点</button>
			</form>
		</section>
	</div>
</div>
<script type="text/javascript" src="js/test_slide.js"></script>
</body>
</html>