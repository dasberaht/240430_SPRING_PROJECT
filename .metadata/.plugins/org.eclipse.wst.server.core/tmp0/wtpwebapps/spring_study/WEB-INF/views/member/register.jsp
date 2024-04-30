<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="../layout/header.jsp" />
	<div class="container-md">
	
		<h4>MemberJoin(register)Page</h4>
		
		<form action="/member/register" method="post">
		
			<div class="mb-3">
			  <label for="i" class="form-label">ID</label>
			  <input type="text" class="form-control" name="id" id="i" placeholder="ID">
			</div>
			<div class="mb-3">
			  <label for="p" class="form-label">PassWord</label>
			  <input type="password" class="form-control" name="pw" id="p" placeholder="PW">
			</div>
			<div class="mb-3">
			  <label for="n" class="form-label">Name</label>
			  <input type="text" class="form-control" name="name" id="n" placeholder="Name">
			</div>
			<div class="mb-3">
			  <label for="e" class="form-label">E-mail</label>
			  <input type="email" class="form-control" name="email" id="e" placeholder="E-mail@E-mail.com">
			</div>
			<div class="mb-3">
			  <label for="h" class="form-label">Home</label>
			  <input type="text" class="form-control" name="home" id="h" placeholder="Home">
			</div>
			<div class="mb-3">
			  <label for="a" class="form-label">Age</label>
			  <input type="text" class="form-control" name="age" id="a" placeholder="Age">
			</div>

			<div class="d-grid gap-2 d-md-flex justify-content-md-end">
			<button type="submit" class="btn btn-secondary">회원가입</button>
			<a href="/"><button type="button" class="btn btn-secondary">홈으로</button></a>
			</div>
			
		</form>
	</div>
<jsp:include page="../layout/footer.jsp" />