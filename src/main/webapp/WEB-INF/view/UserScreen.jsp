<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザーページ</title>
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

<!-- メイン画面 -->
<div class="main-links">
  <a href="EventServlet" class="link-card">
    <h2>イベントエントリー</h2>
    <p>イベントに参加登録する</p>
  </a>
  <a href="OrganizerServlet" class="link-card">
    <h2>オーガナイザー画面</h2>
    <p>イベントを作成・管理する</p>
  </a>
  <a href="JudgeServlet" class="link-card">
    <h2>ジャッジ画面</h2>
    <p>参加者に点数を付ける</p>
  </a>
</div>

</body>
</html>