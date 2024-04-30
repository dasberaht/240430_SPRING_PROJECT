<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../layout/header.jsp" />
	<div class="container-md">
	
		<h4>MemberLoginPage</h4>
		
		<form action="/member/login" method="post">
		
			<div class="mb-3">
			  <label for="i" class="form-label">ID</label>
			  <input type="text" class="form-control" name="id" id="i" placeholder="ID">
			</div>
			<div class="mb-3">
			  <label for="p" class="form-label">PassWord</label>
			  <input type="password" class="form-control" name="pw" id="p" placeholder="PW">
			</div>
			<div class="d-grid gap-2 d-md-flex justify-content-md-end">
			<button type="submit" class="btn btn-secondary">로그인</button>
			</div>
		</form>
		
	</div>
		
<jsp:include page="../layout/footer.jsp" />