<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	 <meta name="viewport" content="width=device-width, initial-scale=1">
 	 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
 	 <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
  	 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
 	 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<title>msgView.jsp</title>
	<style> 
      *{ font-family: gulim; font-size: 24px; } 
    </style>
    <link href="../css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	
    <div class="title">알림</div>
    
    <div class="content">
	    <dl>
		    <dd>${msg1 != null ? img : ""} ${msg1}</dd>
	  	    <dd>${msg2 != null ? img : ""} ${msg2}</dd>
		    <dd>${msg3 != null ? img : ""} ${msg3}</dd>
	    </dl>
	    <p>
		    ${link1} ${link2} ${link3}
	    </p>
    </div>
    
</body>
</html>