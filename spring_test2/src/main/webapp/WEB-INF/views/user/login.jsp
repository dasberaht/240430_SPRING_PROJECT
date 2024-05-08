<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<jsp:include page="../layout/header.jsp" />
	<div class="container-md">
	
		<h4>MemberLoginPage</h4>
		
		<form action="/user/login" method="post">
		
			<div class="mb-3">
			  <label for="e" class="form-label">Email</label>
			  <input type="text" class="form-control" name="email" id="e" placeholder="Email">
			</div>
			<div class="mb-3">
			  <label for="p" class="form-label">Password</label>
			  <input type="password" class="form-control" name="pwd" id="p" placeholder="PW">
			</div>
			
			<!-- error 메세지 처리 : 잘 인지하지 못하는 경우가 있으므로, param.을 처리해주는 것이 좋다. -->
			<!-- not empty : errMsg != ''; 와 같은 의미 -->
			<c:if test="${not empty param.errMsg}">
				<div class="mb-3 text-danger">
					<c:choose>
						<c:when test="${param.errMsg eq 'Bad credentials' }">
							<c:set value="이메일 또는 비밀번호가 일치하지 않습니다." var="errText"></c:set>
						</c:when>	
						<c:otherwise>
							<c:set value="관리자에게 문의해주세요." var="errText"></c:set>
						</c:otherwise>
					</c:choose>
						${errText }
				</div>
			</c:if>
			
			<div class="d-grid gap-2 d-md-flex justify-content-md-end">
			<button type="submit" class="btn btn-secondary">로그인</button>
			</div>
		</form>
		
	</div>
		
<jsp:include page="../layout/footer.jsp" />