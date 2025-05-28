<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Organizer</title>
<link rel="stylesheet" href="./css/style.css">
</head>
<body>
<h1>DanceEvent</h1>
<!-- Top menu -->
 <header class="header">
    <div class="logo">Organizer</div>
    <nav class="navbar">
      <ul class="nav-menu">
        <li><a href="UserServlet">Home</a></li>
        <li><a href="EventServlet">EventEntry</a></li>
        <li><a href="OrganizerServlet">Organizer</a></li>
        <li><a href="JudgeServlet">Judge</a></li>
      </ul>
    </nav>
  </header>
  <div class="content-container">
  <h2>オーガナイザー管理画面</h2>
  <a href="CreateEventServlet" class="btn-link">イベント作成</a>
  <a href="OrganizerEventServlet" class="btn-link">イベント一覧</a>
</div>
  
  
</body>
</html>