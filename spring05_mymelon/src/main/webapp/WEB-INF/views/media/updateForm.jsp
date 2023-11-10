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
<title>updateForm.jsp</title>
<style>
	*{ font-family: gulim; font-size: 24px; }
</style>
<link href="../css/style.css" rel="stylesheet" type="text/css">
	<script>
		function updateCheck() {
			let message="파일을 수정하면 기존 첨부파일은 삭제됩니다\n수정할까요?";
			if(confirm(message)){
				return true;
			}else{
				return false;
			}//if end
		}//updateCheck() end
	</script>
</head>
<body>

	<div class="title">음원 수정</div>
	
	<form name="frm" method="post" action="update.do" enctype="multipart/form-data" onsubmit="return updateCheck()">
		<input type="hidden" name="mediagroupno" value="${dto.mediagroupno}">
		<input type="hidden" name="mediano" value="${dto.mediano}">
		<table class="table">
		<tr>
          <th>제목</th>
          <td><input type='text' name='title' size='50' value="${dto.title}"></td>
        </tr>
        <tr>
          <th>포스터</th>
          <td>
          <img src="../storage/${dto.poster}" width="100"><br>
          <input type='file' name='posterMF' size='50'>
          </td>    
        </tr>
        <tr>
          <th>미디어 파일</th>
          <td>
          등록된 파일명 : ${dto.filename}<br>
          <input type='file' name='filenameMF' size='50'>
          </td>    
        </tr>  
		</table>
	
		<div class="bottom">
			<input type="submit" value="음원수정">
			<input type="button" value="음원목록" onclick="location.href='list.do?mediagroupno=${dto.mediagroupno}'">
			<input type="button" value="HOME" onclick="location.href='/home.do'"><!-- 절대경로 -->
		</div>
	</form>
	
</body>
</html>