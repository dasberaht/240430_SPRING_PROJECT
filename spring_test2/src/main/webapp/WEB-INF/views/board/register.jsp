<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../layout/header.jsp" />
<div class="container-md">
	<h4>BoardRegisterPage</h4>
	
	<!-- 로그인 시 writer >>> nickName 출력 설정 -->
	<sec:authentication property="principal.uvo.nickName" var="authNick"/>
	
	<div>
		<form action="/board/insert" method="post" enctype="multipart/form-data">
			<div class="mb-3">
			  <label for="t" class="form-label">title</label>
			  <input type="text" class="form-control" name="title" id="t" placeholder="title">
			</div>
			
			

			<div class="mb-3">
			  <label for="w" class="form-label">writer</label>
			<input type="text" class="form-control" name="writer" id="w" placeholder="writer" value="${authNick }" readonly="readonly">
			</div>
			
			
			<div class="mb-3">
			  <label for="c" class="form-label">content</label>
			  <textarea class="form-control"  aria-label="With textarea" id="c" name="content" placeholder="content"></textarea>
			</div>
			
			
			<!-- file 입력 라인 -->
			<div class="mb-3">
			  <label for="file" class="form-label">files</label>
			  <input type="file" class="form-control" name="files" id="file" multiple="multiple" style="display: none"><br>
			  <button type="button" class="btn btn-success" id="trigger">upload</button>
			</div>
			
			<!-- file 목록 표시 영역 -->
			<div class="mb-3" id="fileZone">
				
			</div>
			
			
			<div class="d-grid gap-2 d-md-flex justify-content-md-end">
			<button type="submit" class="btn btn-secondary" id="regBtn">등록</button>
			<a href="/board/list"><button type="button" class="btn btn-secondary">게시글</button></a>
			<a href="/"><button type="button" class="btn btn-secondary">홈으로</button></a>
			</div>
		</form>
	</div>
</div>

<script type="text/javascript" src="/re/js/boardRegister.js"></script>



<jsp:include page="../layout/footer.jsp" />
</body>
</html>