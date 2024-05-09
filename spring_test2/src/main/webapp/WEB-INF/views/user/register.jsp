<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../layout/header.jsp" />
	<div class="container-md">
	
		<h4>UserRegisterPage</h4>
		
		<form action="/user/register" method="post">
			
			<div class="mb-3">
			  <label for="e" class="form-label">E-mail</label>
			  <input type="text" class="form-control" name="email" id="e" placeholder="E-mail@E-mail.com">
			</div>
			<div class="d-grid gap-2 d-md-flex justify-content-md-end">
				<button type="button" class="btn btn-secondary btn-sm" id="chkEmailBtn">중복체크</button>
			</div>			
			
		
					
			<div class="mb-3">
			  <label for="p" class="form-label">PassWord</label>
			  <input type="password" class="form-control" name="pwd" id="p" placeholder="Password">
			</div>
			<div class="mb-3">
			  <label for="n" class="form-label">Nick_Name</label>
			  <input type="text" class="form-control" name="nickName" id="n" placeholder="nickName">
			</div>


			<div class="d-grid gap-2 d-md-flex justify-content-md-end">
			<button type="submit" class="btn btn-secondary">회원가입</button>
			<a href="/"><button type="button" class="btn btn-secondary">홈으로</button></a>
			</div>
			
		</form>
	</div>
	
	
<script type="text/javascript" src="/re/js/userRegister.js" ></script>

<script type="text/javascript">

	const register_msg = `<c:out value="${register_msg }"></c:out>`; 
	if(register_msg === "1"){
		alert("회원가입 실패");
	}
	
</script>
	
	
<jsp:include page="../layout/footer.jsp" />