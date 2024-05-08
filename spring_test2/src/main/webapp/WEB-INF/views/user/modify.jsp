<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<jsp:include page="../layout/header.jsp" />
	<div class="container-md">
	
		<h4>UserModifyPage</h4>
		
        <sec:authentication property="principal.uvo.email" var="authEmail"/>
        <sec:authentication property="principal.uvo.nickName" var="authNick"/>
        <sec:authentication property="principal.uvo.regDate" var="authReg"/>
        <sec:authentication property="principal.uvo.lastLogin" var="authLastLogin"/>
		
		<form action="/user/modify" method="post">
		
	
			
			
			<div class="mb-3">
			  <label for="e" class="form-label">E-mail</label>
			  <input type="email" class="form-control" name="email" id="e" placeholder="E-mail@E-mail.com" value="${authEmail }" readonly="readonly">
			</div>
			<div class="mb-3">
			  <label for="n" class="form-label">NickName</label>
			  <input type="text" class="form-control" name="nickName" id="n" placeholder="NickName" value="${authNick }">
			</div>
			<div class="mb-3">
			  <label for="p" class="form-label">PassWord</label>
			  <input type="password" class="form-control" name="pw" id="p" placeholder="**********">
			</div>
			<div class="mb-3">
			  <label for="i" class="form-label">REG_DATE</label>
			  <input type="text" class="form-control"  id="r" placeholder="RegDate" value="${authReg }" readonly="readonly">
			</div>
			<div class="mb-3">
			  <label for="i" class="form-label">LAST_LOGIN</label>
			  <input type="text" class="form-control"  id="l" placeholder="LastLogin" value="${authLastLogin }" readonly="readonly">
			</div>




			<div class="d-grid gap-2 d-md-flex justify-content-md-end">
			<button type="submit" class="btn btn-secondary">수정</button>
			<a href="/"><button type="button" class="btn btn-secondary">홈으로</button></a>
			<br>
			<a href="/"><button type="button" class="btn btn-danger">회원탈퇴</button></a>
			</div>
			
		</form>
	</div>
	
		
<jsp:include page="../layout/footer.jsp" />


