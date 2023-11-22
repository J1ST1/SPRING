<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <title>detail.jsp</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
  <script src="/js/jquery-3.7.1.min.js"></script>
  <link href="/css/main.css" rel="stylesheet" type="text/css">
  
  <script>
  	function product_update() {
		document.productfrm.action="/product/update";
		document.productfrm.submit();
	}//product_update() end
	
  	function product_delete() {
		if(confirm("첨부된 파일은 영구히 삭제됩니다\n진행할까요?")){
		document.productfrm.action="/product/delete";
		document.productfrm.submit();
		}//if end
	}//product_update() end
  </script>
  
</head>
<body>

<div class="p-5 bg-info text-white text-center">
  <h1>My Shop</h1>
</div>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <div class="container-fluid">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link active" href="/product/list">상품</a>
      </li>
      <li class="nav-item">
        <a class="nav-link active" href="/cart/list">장바구니</a>
      </li>
    </ul>
  </div>
</nav>

<div class="container text-center">
<!-- 본문 시작 -->

  <div class="row">
    <div class="col-sm-12">
     	<br><p><h3>상품 상세보기 | 상품 수정 | 상품 삭제</h3></p><br>
     	<p>
     		<button type="button" onclick="location.href='/product/list'" class="btn btn-primary">상품전체목록</button>
     	</p>
    </div><!-- col end -->
  </div><!-- row end -->
  
  <div class="row">
  	<div class="col-sm-12">
  		<form name="productfrm" id="productfrm" method="post" enctype="multipart/form-data">
  		<input type="hidden" name="product_code" value="${product_code}">
  			<table class="table table-hover">
			   <tbody style="text-align: left;">
			   <tr>
					<td>상품명</td>
					<td> <input type="text" name="product_name" class="form-control" value="${product.PRODUCT_NAME}"> </td>
			    </tr>
			    <tr>
					<td>상품가격</td>
					<td> <input type="number" name="price" class="form-control" value="${product.PRICE}"> </td>
			    </tr>
				<tr>
					<td>상품설명</td>
					<td> 
				    	<textarea rows="5" cols="60" name="description" class="form-control">${product.DESCRIPTION}</textarea>     
				 	</td>
			    </tr>
			    <tr>
					<td>상품사진</td>
					<td>
						<c:if test="${product.FILENAME != '-'}">
							<img src="/storage/${product.FILENAME}" width="100px">
						</c:if>
						<br> <br>
						<input type="file" name="img" class="form-control"> 
					</td>
			    </tr>
			    <tr>
					<td colspan="2" align="center">
				    	<input type="button" value="상품수정" class="btn btn-success" onclick="product_update()"> 
						<input type="button" value="상품삭제" class="btn btn-danger" onclick="product_delete()"> 
						<input type="button" value="장바구니담기" class="btn btn-warning" onclick="product_cart()"> 
					</td>
			    </tr>   
			    </tbody> 
		    </table>
  		</form>
    </div><!-- col end -->
  </div><!-- row end -->
  <br>
  <!-- 댓글 시작 -->
  <div class="row">
  	<div class="col-sm-12"><!-- 댓글 등록 -->
  			<form name="commentInsertForm" id="commentInsertForm">
  				<!-- 부모글 번호 -->
  				<input type="hidden" name="product_code" id="product_code" value="${product.PRODUCT_CODE}">
					<table class="table table-borderless">
					<tr>
						<td>
							<input type="text" name="content" id="content" placeholder="댓글 내용 입력해 주세요" class="form-control">
						</td>
						<td>
							<button type="button"  name="commentInsertBtn" id="commentInsertBtn" class="btn btn-secondary">댓글등록</button>
						</td>
					</table>
  			</form>
    </div><!-- col end -->
  </div><!-- row end -->

    <div class="row">
  	<div class="col-sm-12"><!-- 댓글목록 -->
  		<div class="commentList">

  		</div>
    </div><!-- col end -->
  </div><!-- row end -->
  <!-- 댓글 끝 -->
  
  <!-- 댓글 관련 자바스크립트 -->
  <script>
  	let product_code = '${product.PRODUCT_CODE}'; //부모글 번호
  	
  	$(document).ready(function(){ //페이지 로딩시 댓글 목록 출력
  		commentList();
  	});//ready() end
  	
  	//댓글등록 버튼을 클릭했을 때
  	$("#commentInsertBtn").click(function() {
		//alert($);
		let content = $("#content").val();
		content = content.trim();
		if(content.length == 0){
			alert("댓글 내용 입력해 주세요");
		   $("#content").focus();
		}else{
			//serialize함수 -> <form id="commentInsertForm"></form>의 컨트롤 요소를 전부 가져옴
			let insertData = $("#commentInsertForm").serialize();
			//alert(insertData); //product_code=85&content=apple
			commentInsert(insertData); //댓글등록 함수 호출
		}//if end
	});//click end
	
	function commentInsert(insertData) {//댓글등록 함수
		//alert("댓글등록함수호출:" + insertData); //댓글등록함수호출:product_code=85&content=apple
		$.ajax({
			url: '/comment/insert'//요청명령어
		   ,type: 'post'
		   ,data: insertData      //전달값
		   ,error: function(error) {
			   alert(error);
		   }//error() end
		   ,success: function(result) {
			   //alert(result);
			   
			if(result==1) {//댓글등록 성공
				alert("댓글이 등록되었습니다");
				commentList();	//댓글 등록 후 댓글목록 함수 호출
				$("#content").val(''); //기존 댓글내용을 빈 문자열로 대입(초기화)
			}//if end
			   
		   }//success() end
		});//ajax() end
	}//commentInsert() end
	
	function commentList() {
		//alert("댓글목록 함수 호출함");
		$.ajax({
			url: '/comment/list'
		   ,type: 'get'
		   ,data: {'product_code' : product_code}//부모글번호(전역변수로 선언되어 있음)
		   ,error: function(error) {
		    	alert(error);
		    	console.log(error);
			}//error() end
			,success: function(result){
				//alert(result);//댓글 갯수에 따라 object 수가 달라짐 ex)댓글 4개 object 4개 -> [object Object],[object Object],[object Object],[object Object]
				//console.log(result);
				let a=''; //출력할 결과값
				$.each(result, function(key, value) {
					//console.log(key);//순서 0 1 2
					//console.log(value);//[object Object]
					//console.log(value.cno);
					//console.log(value.content);
					//console.log(value.wname);
					//console.log(value.regdate);
					//console.log(value.product_code);
					
					a += '<div class="commentArea" style = "border-bottom:1px solid darkgray; margin-bottom:15px;">';
					a += '	<div class="commentInfo' + value.cno + '">';
					a += '		댓글번호 : ' + value.cno + ' / 작성자 : ' + value.wname + ' ' + value.regdate;
					a += '		<a href="javascript:commentUpdate(' + value.cno + ',\'' + value.content + '\')">[수정]</a>';
					a += '		<a href="javascript:commentDelete(' + value.cno + ')">[삭제]</a>';
					a += '	</div>';
					a += '	<div class="commentContent' + value.cno + '">';
					a += '		<p>내용 : ' + value.content + '</p>';
					a += '	</div>';
					a += '</div>';
				}); //each() end
				
				$(".commentList").html(a);
				
			}//success() end
		});//ajax() end
	}//commentList() end
	
	//댓글수정 - 댓글내용을 <input=text>에 출력함
	function commentUpdate(cno, content) {
		//alert(cno + content);
		let a = '';
		a += '<div class="input-group">';
		a += '	<input type="text" value="' + content +'" id="content_' + cno + '">';
		a += '	<button type="button" onclick="commentUpdateProc(' + cno + ')">수정</button>';
		a += '</div>';
		//alert(a);
		$(".commentContent" + cno).html(a);
	}//commentUpdate() end
	
	//댓글 수정 후 댓글목록함수 호출
	function commentUpdateProc(cno) {
		//alert("댓글수정" + cno);
		let updateContent = $("#content_" + cno).val();
		//alert(updateContent);
		
			$.ajax({
					url:'/comment/update'
				   ,type:'post'
				   ,data:{'cno':cno, 'content':updateContent} //또는 "cno="+cno+"&content="+updateContent
				   ,success: function(result) {
					 if(result==1){
						 alert("댓글이 수정되었습니다");
						 commentList(); //댓글수정 후 목록 출력
					 }//if end
				}//success() end
			})//ajax()end
	}//commentUpdateProc() end
	
	
	function commentDelete(cno) {
		//alert("댓글삭제" + cno);
		$.ajax({
				url:'/comment/delete/' + cno   //RESTful방식으로 웹서비스 요청. 예)/comment/delete/5
			   ,type:'post'
				//,data:{'cno':cno} 
				,success: function(result) {
				if(result==1){
					alert("댓글이 삭제되었습니다");
					commentList(); //댓글삭제 후 목록 출력
				}else{
					alert("댓글 삭제 실패 : 로그인 후 사용이 가능합니다");
					 }//if end
				}//success() end
			})//ajax()end
	}//commentDelete() end
	
  </script>
  
  <!-- 본문 끝 -->
</div><!-- container end -->

<div class="mt-5 p-4 bg-dark text-white text-center">
  <p>ITWILL 아이티윌 교육센터</p>
</div>

</body>
</html>
