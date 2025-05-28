<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Top</title>
<link rel="stylesheet" href="./css/style.css">
</head>
<body>
<h1>DanceEvent</h1>

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

<!-- メイン -->
<div class="content-container">
  <h2>イベント参加者トップ画面</h2>
  <a href="NotPage.jsp" class="btn-link">参加中のイベント(未実装)</a>
  <a href="EntryListServlet" class="btn-link">イベント一覧へ（エントリーに進む）</a>
</div>

</body>
</html>
