<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DELETE COMPLETED</title>
<%@ include file="common_parts/link.jsp"%>
</head>
<body>
<div align="center">
	<%@ include file="common_parts/header.jsp"%>
	<div class="content">
		<div class="quiz_form delete complete">
			<p class="complete_message">削除完了しました。</p>
			<div class="quiz_form_button">
				<button onclick="location.href='./List'">一覧に戻る</button>
			</div>
		</div>
	</div>
</div>
</body>
</html>