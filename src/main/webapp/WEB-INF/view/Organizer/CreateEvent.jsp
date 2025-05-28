<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>イベント作成</title>
<link rel="stylesheet" href="./css/style.css">
<style>
  .form-container {
    max-width: 600px;
    margin: 50px auto;
    background-color: #EEEEEE;
    padding: 30px;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  }

  .form-container h2 {
    text-align: center;
    margin-bottom: 30px;
    color: #333;
  }

  .form-group {
    margin-bottom: 20px;
    text-align: left;
  }

  .form-group label {
    display: block;
    margin-bottom: 8px;
    font-weight: bold;
  }

  .form-group input,
  .form-group textarea {
    width: 100%;
    padding: 10px;
    border-radius: 6px;
    border: 1px solid #ccc;
    font-size: 16px;
  }

  .form-group textarea {
    resize: vertical;
    height: 120px;
  }

  .form-button {
    text-align: center;
  }

  .form-button input {
    background-color: #4CAF50;
    color: white;
    border: none;
    padding: 12px 30px;
    font-size: 16px;
    border-radius: 6px;
    cursor: pointer;
    transition: background-color 0.3s ease;
  }

  .form-button input:hover {
    background-color: #45a049;
  }
</style>
</head>
<body>

<header class="header">
  <div class="logo">Create Event</div>
  <nav class="navbar">
    <ul class="nav-menu">
      <li><a href="UserServlet">Home</a></li>
      <li><a href="EventServlet">EventEntry</a></li>
      <li><a href="OrganizerServlet">Organizer</a></li>
      <li><a href="JudgeServlet">Judge</a></li>
    </ul>
  </nav>
</header>

<div class="form-container">
  <h2>イベント作成フォーム</h2>
  <form action="CreateEventServlet" method="post">
    <div class="form-group">
      <label for="eventName">イベント名</label>
      <input type="text" id="eventName" name="eventName" required>
    </div>

    <div class="form-group">
      <label for="eventDate">開催日</label>
      <input type="date" id="eventDate" name="eventDate" required>
    </div>

    <div class="form-group">
      <label for="organizerName">オーガナイザー名</label>
      <input type="text" id="organizerName" name="organizerName" required>
    </div>

    <div class="form-group">
      <label for="comment">コメント</label>
      <textarea id="comment" name="comment" placeholder="コメントを入力してください"></textarea>
    </div>

    <div class="form-button">
      <input type="submit" value="イベントを作成">
    </div>
  </form>
</div>

</body>
</html>
