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
	QuestionsBean edit_q = (QuestionsBean)request.getAttribute("edit_question");
	ArrayList<CorrectAnswersBean> edit_a = (ArrayList<CorrectAnswersBean>)request.getAttribute("edit_answer");
	String edit_question = (String)session.getAttribute("edit_question");
	String[] edit_answers = (String[])session.getAttribute("edit_answers");
	String errorMessage_question = (String) request.getAttribute("error_1");
	String errorMessage_answers = (String) request.getAttribute("error_2");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EDIT</title>
<%@ include file="common_parts/link.jsp"%>
</head>
<body>
<div align="center">
	<%@ include file="common_parts/header.jsp"%>
	<div class="content">
		<form action="./EditConfirm" method="post" class="quiz_form edit">
			<% if(errorMessage_question != null || errorMessage_answers != null) { %>
				<% if(errorMessage_question != null) { %>
					<p class="error_message"><%= errorMessage_question %></p>
				<% } %>
				<div class="question error">
					<label>問題</label>
					<textarea name="question" placeholder="問題文を入力してください"><%= edit_question %></textarea>
				</div>
			<% } else { %>
				<div class="question">
					<label>問題</label>
					<textarea name="question" placeholder="問題文を入力してください"><%= edit_q.getQuestion() %></textarea>
				</div>
			<% } %>
			<% if(errorMessage_question != null || errorMessage_answers != null) { %>
				<% if(errorMessage_answers != null) { %>
					<p class="error_message"><%= errorMessage_answers %></p>
				<% } %>
				<div class="answers error">
					<label>答え</label>
					<ul class="answer_list">
						<% for(int s_i = 0; s_i < edit_answers.length; s_i++) { %>
							<li>
								<div class="answer"><input type="text" name="answers" value="<%= edit_answers[s_i] %>" placeholder="答えを入力してください"></div>
								<button type="button" class="btn_remove">削除</button>
							</li>
						<% } %>
					</ul>
				</div>
			<% } else { %>
				<div class="answers">
					<label>答え</label>
					<ul class="answer_list">
						<% for (int i = 0; i < edit_a.size(); i++ ){
							CorrectAnswersBean ans = edit_a.get(i); %>
							<li>
								<div class="answer"><input type="text" name="answers" value="<%= ans.getAnswer() %>" placeholder="答えを入力してください"></div>
								<button type="button" class="btn_remove">削除</button>
							</li>
						<% } %>
					</ul>
				</div>
			<% } %>
			<div class="quiz_form_button">
				<button onClick="javascript:window.history.back(-1);return false;">戻る</button>
				<button class="btn_add" type="button">答えを追加</button>
				<button type="submit">確認</button>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript" src="js/add_answers.js"></script>
</body>
</html>