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
	points.add(entry.getPoints());
	
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
<h1>DanceEvent</h1>
<% if (event == null) { %>
    <h2><%= err %></h2>
<% } else { %>
    <h2>イベント名: <%= event.getName() %></h2>
    <h3>エントリー者一覧</h3>

    <table class="design01">
        <tr>
            <th>No</th>
            <th>ID:Name</th>
            <th>point</th>
        </tr>

        <%
        int no = 1;
        for(UsersDTO users : userlist){ %>
        <tr>
            <td><%= no %></td>
            <td><%= users.getId()%>:<%= users.getDancerName() %></td>
            <td><%= points.get(no - 1) %></td>
        </tr>
        <%
        no++;
        } 
        %>
    </table>
<% } %>

</body>
</html>
