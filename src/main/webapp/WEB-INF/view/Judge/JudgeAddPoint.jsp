<%@page import="java.util.ArrayList"%>
<%@page import="model.dto.UsersDTO"%>
<%@page import="model.dto.EventDTO"%>
<%@page import="model.dto.EntryListDTO"%>
<%@page import="java.util.List"%>
<%@page import="jakarta.servlet.http.HttpSession" %>
<%@page import="jakarta.servlet.http.HttpServletRequest" %>
<%@page import="jakarta.servlet.http.HttpServletResponse" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<EntryListDTO> list = (List<EntryListDTO>)request.getAttribute("list");
List<EventDTO> eventdtolist = new ArrayList<>();
List<UsersDTO> userlist = new ArrayList<>();
List<Integer> points = new ArrayList<>();

for(EntryListDTO entry : list){
	eventdtolist.add(entry.getEvent());
	userlist.add(entry.getUser());
	points.add(entry.getPoints());
	
}
EventDTO event = null;
String err = null;
if (!eventdtolist.isEmpty()) {
    event = eventdtolist.get(0);
} else {
    err = "まだエントリーがありません";
}
session.setAttribute("event",event);

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>エントリー一覧</title>
<link rel="stylesheet" href="./css/style.css">
</head>
<body>
	<h1>DanceEvent</h1>
	<!-- メニュー -->
	<header class="header">
		<div class="logo">Home</div>
		<nav class="navbar">
			<ul class="nav-menu">
				<li><a href="UserServlet">Home</a></li>
				<li><a href="EventServlet">EventEntry</a></li>
				<li><a href="OrganizerServlet">Organizer</a></li>
				<li><a href="JudgeServlet">Judge</a></li>
			</ul>
		</nav>
	</header>
<% if (event == null) { %>
    <h2><%= err %></h2>
<% } else { %>
    <h2>イベント名: <%= event.getName() %></h2>
    <h3>エントリー者一覧</h3>

    <table class="design01">
        <tr>
            <th>No</th>
            <th>ID:Name</th>
        </tr>

        <%
        int no = 1;
        for(UsersDTO users : userlist){ %>
        <tr>
            <td><%= no %></td>
            <td><%= users.getId()%>：<%= users.getDancerName() %></td>
        </tr>
        <%
        no++;
        } 
        %>
    </table>
<% } %>
	<h3>＜採点＞</h3>
	<form action="AddPointServlet" method="post">
		<div class="form-group">
			<p>採点ポイント：<input type="text" name="point" required>
			<p>
			<br>ユーザーID：<input type="text" name="userId" required>
			 
			<p> <input type="submit" value="決定">
		</div>
	</form>

</body>
</html>