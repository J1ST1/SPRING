<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>searchTest.jsp</title>
	<script src="../js/jquery-3.7.1.min.js"></script>
	<script src="../js/jquery.cookie.js"></script>
</head>
<body>

	<h3>교재 검색</h3>
	
	<!-- https://www.w3schools.com/jquery/jquery_ref_ajax.asp -->
	
	<form name="search" id="search">
		<input type="text" name="keyword" id="keyword">
		<input type="button" value="검색">
	</form>

	<!-- 검색 결과 출력 -->
	<div id="panel" style="display:none"></div>
	
	<script>
		$("#keyword").keyup(function(){
			//alert("test");
			
			if($("#keyword").val()==""){//검색어가 존재하지 않으면
				$("#panel").hide();		//출력결과 숨기기
			}//if end
			
			//1) id="keyword" 값 가져오기
			//let params = $("#keyword").val();
			//alert(params);
			
			//2) <form id="search"></form>
			//	 폼안의 모든 컨트롤 요소를 가져오기
			let params = $("#search").serialize();
			//alert(params); //keyword=java
			
			$.post("searchproc.do", params, responseProc);
			
		});//keyup end
		
		
		function responseProc(data) {
			//alert(data);
			//예)3|자바, 자바 프로그래밍, 자바 안드로이드
			
			if(data.length>0){ //응답받은 내용이 있는지?
				// |기호를 기준으로 문자열 분리
				let result = data.split("|");
				//alert(result[0]); //3
				//alert(result[1]); //자바, 자바 프로그래밍, 자바 안드로이드
				let str = result[1].split(",");
				let title=""; //최종값
				$.each(str, function (index, key) {
					//alert(index); 순서
					//alert(key);   내용
					title += "<a href='#'>" + key + "</a>";
					title += "<br>";
				});//each() end
				
				$("#panel").html(title);
				$("#panel").show();
				
			}else {
				$("#panel").hide();
			}//if end
			
		}//responseProc() end
	
	</script>
	
</body>
</html>