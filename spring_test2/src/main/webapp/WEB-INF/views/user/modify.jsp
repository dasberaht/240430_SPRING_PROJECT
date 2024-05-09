<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<jsp:include page="../layout/header.jsp" />
	<div class="container-md">
	
		<h4>UserModifyPage</h4>
		
        <sec:authentication property="principal.uvo.email" var="authEmail"/>
        <sec:authentication property="principal.uvo.nickName" var="authNick"/>
        <sec:authentication property="principal.uvo.regDate" var="authReg"/>
        <sec:authentication property="principal.uvo.pwd" var="authPwd"/>
        <sec:authentication property="principal.uvo.lastLogin" var="authLastLogin"/>
        <sec:authentication property="principal.uvo.authList" var="auths"/>
		
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
			  <input type="password" class="form-control" name="pwd" id="p" placeholder="*******">
			</div>
			
			<div class="mb-3">
			  <label for="au" class="form-label">AUTH</label>
			<c:forEach items="${auths }" var="a">
			  <input type="text" class="form-control"  id="au" placeholder="Auth" value="${a.auth }" readonly="readonly">
			</c:forEach>
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
			<!-- <a href="/user/delete"><button type="button" class="btn btn-danger">회원탈퇴</button></a> -->
			<button type="button" class="btn btn-danger" id="delBtn">회원탈퇴</button>
			</div>
			
		</form>
	</div>
	
	
<script type="text/javascript">
	
	//confirm : yes / no 를 결정하도록 출력
	document.getElementById('delBtn').addEventListener('click', ()=>{
	    let check = confirm("정말 탈퇴하시겠습니까?");
	    console.log(check);
	
	    if(check){
	        location.href="/user/delete";
	    }
	
	})
	
</script>	
	
		
<jsp:include page="../layout/footer.jsp" />


