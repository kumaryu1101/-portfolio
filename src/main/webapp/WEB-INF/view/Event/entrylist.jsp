<%@page import="java.util.ArrayList"%>
<%@page import="model.dto.UsersDTO"%>
<%@page import="model.dto.EventDTO"%>
<%@page import="model.dto.EntryListDTO"%>
<%@page import="java.util.List"%>
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
}
EventDTO event = null;
String err = null;
if (!eventdtolist.isEmpty()) {
    event = eventdtolist.get(0);
} else {
    err = "まだエントリーがありません";
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>エントリー一覧</title>
<link rel="stylesheet" href="./css/style.css">
</head>
<body>

<!-- ヘッダー -->
<header class="header">
  <div class="logo">EventEntry</div>
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
    <h1><%= err %></h1>
<% } else { %>
    <h1>イベント名: <%= event.getName() %></h1>
    <h2>エントリー者一覧</h2>

    <table class="design01">
        <tr>
            <th>No</th>
            <th>dancer name</th>
        </tr>

        <%
        int no = 1;
        for(UsersDTO users : userlist){ %>
        <tr>
            <td><%= no %></td>
            <td><%= users.getDancerName() %></td>
        </tr>
        <%
        no++;
        } 
        %>
    </table>
<% } %>


</body>
</html>
