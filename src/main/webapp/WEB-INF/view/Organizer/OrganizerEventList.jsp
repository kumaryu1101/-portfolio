<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="model.dto.EventDTO" %>

<%
ArrayList<EventDTO> eventlist = (ArrayList<EventDTO>) request.getAttribute("eventlist");
SimpleDateFormat sdf = new SimpleDateFormat("yyyy年 MM月 dd日");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>イベント一覧</title>
<link rel="stylesheet" href="./css/style.css">
</head>
<body>

	<h1>DanceEvent</h1>
	<h2>イベント一覧</h2>

	<table class="design01">
		<tr>
			<th>ID</th>
			<th>イベント名</th>
			<th>開催日</th>
			<th>オーガナイザー</th>
			<th>コメント・メモ</th>
		</tr>

		<%
    if (eventlist == null) {
    %>
		<tr><td>イベントがありません</td></tr>
		<%
    } else {
      for (EventDTO event : eventlist) {
    %>
		<tr>
			<td><%= event.getId() %></td>
			<td><%= event.getName() %></td>
			<td><%= sdf.format(event.getDate()) %></td>
			<td><%= event.getOrganizerName() %></td>
			<td><%= event.getComment() %></td>
		</tr>
		<%
      }
    }
    %>
	</table>

	<h3>＜エントリー者のスコアを確認する＞</h3>
	<form action="OrganizerEventServlet" method="post">
		<div class="form-group">
			<p>
				イベントID：<input type="text" name="eventId" required>
			</p>
			</label> 
			<p> <input type="submit" value="結果表示">
		</div>
	</form>


</body>
</html>
