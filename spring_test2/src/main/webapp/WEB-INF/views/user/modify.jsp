<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="../layout/header.jsp" />
	<div class="container-md">
	
		<h4>MemberModifyPage</h4>
		
		<form action="/member/modify" method="post">
		
			<div class="mb-3">
			  <label for="i" class="form-label">ID</label>
			  <input type="text" class="form-control" name="id" id="i" placeholder="ID" value="${ses.id }" readonly="readonly">
			</div>		
			<div class="mb-3">
			  <label for="i" class="form-label">REG_DATE</label>
			  <input type="text" class="form-control"  id="i" placeholder="ID" value="${ses.reg_date }" readonly="readonly">
			</div>
			<div class="mb-3">
			  <label for="i" class="form-label">LAST_LOGIN</label>
			  <input type="text" class="form-control"  id="i" placeholder="ID" value="${ses.last_login }" readonly="readonly">
			</div>
			
			
			<div class="mb-3">
			  <label for="p" class="form-label">PassWord</label>
			  <input type="password" class="form-control" name="pw" id="p" placeholder="**********">
			</div>
			<div class="mb-3">
			  <label for="n" class="form-label">Name</label>
			  <input type="text" class="form-control" name="name" id="n" placeholder="Name" value="${ses.name }">
			</div>
			<div class="mb-3">
			  <label for="e" class="form-label">E-mail</label>
			  <input type="email" class="form-control" name="email" id="e" placeholder="E-mail@E-mail.com" value="${ses.email }">
			</div>
			<div class="mb-3">
			  <label for="h" class="form-label">Home</label>
			  <input type="text" class="form-control" name="home" id="h" placeholder="Home" value="${ses.home }">
			</div>
			<div class="mb-3">
			  <label for="a" class="form-label">Age</label>
			  <input type="text" class="form-control" name="age" id="a" placeholder="Age" value="${ses.age }">
			</div>

			<div class="d-grid gap-2 d-md-flex justify-content-md-end">
			<button type="submit" class="btn btn-secondary">수정</button>
			<a href="/"><button type="button" class="btn btn-secondary">홈으로</button></a>
			<a href="/member/remove"><button type="button" class="btn btn-danger">회원탈퇴</button></a>
			</div>
			
		</form>
	</div>
	
		
<jsp:include page="../layout/footer.jsp" />


