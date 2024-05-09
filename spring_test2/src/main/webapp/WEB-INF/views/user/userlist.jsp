<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<jsp:include page="../layout/header.jsp" />

<div class="container-md">
	<h4>UserListpage</h4>

	<sec:authentication property="principal.uvo.authList" var="auths"/>

	<div class="card-group">
		<c:forEach items="${userList }" var="uvo">
			<div class="card" style="width: 18rem;">
				  <img src="/re/image/rock.png"  alt="...">
				  				 
					  <ul class="list-group list-group-flush">
						    <li class="list-group-item">${uvo.nickName }</li>
						    <li class="list-group-item">${uvo.email }</li>
						    <li class="list-group-item">${uvo.regDate }</li>
						    <li class="list-group-item">${uvo.lastLogin }</li>
		
						    <li class="list-group-item">
						    	<c:forEach items="${uvo.authList }" var="authList">
						    		${authList.auth }
						    	</c:forEach>
						    </li>							
					  </ul>
					<button type="button" class="btn btn-secondary btn-sm">회원권한설정</button>
			</div>
		</c:forEach>
	</div>

</div>

<jsp:include page="../layout/footer.jsp" />


