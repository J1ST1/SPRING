<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>readMP3.jsp</title>
<style>
	*{ font-family: gulim; font-size: 24px;}
</style>
<link href="../css/style.css" rel="stylesheet" type="text/css">
</head>
<body>

	<div class="title">MP3 듣기</div>
	
	<div class="content">
		<p><strong>${dto.title}</strong></p>
		<img src="../storage/${dto.poster}" width="400"><br>
		<audio src="../storage/${dto.filename}" controls></audio>	
	</div>
	
	<div class="bottom">
     	<input type="button" value="음원목록" onclick="location.href='list.do?mediagroupno=${dto.mediagroupno}'">
     	<input type="button" value="HOME" onclick="location.href='/home.do'"><!-- 절대경로 -->
    </div>
</body>
</html>