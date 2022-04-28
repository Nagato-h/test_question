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
	QuestionsBean delete_q = (QuestionsBean)session.getAttribute("delete_question");
	ArrayList<CorrectAnswersBean> delete_a = (ArrayList<CorrectAnswersBean>)session.getAttribute("delete_answers");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DELETE</title>
<%@ include file="common_parts/link.jsp"%>
</head>
<body>
<div align="center">
	<%@ include file="common_parts/header.jsp"%>
	<div class="content">
		<form action="./DeleteCompleted" method="post" class="quiz_form delete">
			<p class="confirm_message">
				<span class="material-symbols-outlined">error</span> 下記の問題を削除します
			</p>
			<div class="question">
				<label>問題</label>
				<p><%= delete_q.getQuestion() %></p>
			</div>
			<div class="answers">
				<label>答え</label>
				<ul class="answer_list">
					<% for (int i = 0; i < delete_a.size(); i++ ){
						CorrectAnswersBean ans = delete_a.get(i); %>
						<li>
							<div class="answer"><p><%= ans.getAnswer() %></p></div>
						</li>
					<% } %>
				</ul>
			</div>
			<div class="quiz_form_button">
				<button onClick="javascript:window.history.back(-1);return false;">戻る</button>
				<button type="submit">削除</button>
			</div>
		</form>
	</div>
</div>
</body>
</html>