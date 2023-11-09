<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>readMP4.jsp</title>
<style>
	*{ font-family: gulim; font-size: 24px;}
</style>
<link href="../css/style.css" rel="stylesheet" type="text/css">
</head>
<body>

	<div class="title">MP4 보기</div>
	
	<div class="content">
		<p><strong>${dto.title}</strong></p>
		<video src="../storage/${dto.filename}" poster="../storage/${dto.poster}" width="500px" controls></video>		
	</div>
	
</body>
</html>