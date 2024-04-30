<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

	<jsp:include page="layout/header.jsp"></jsp:include>
		<div class="container-md">
			<h4>
				Board Spring Project, ${serverTime}
			</h4>
			<c:if test="${ses.id ne null }">
				<span class="badge text-bg-dark">${ses.id }　 　 </span> <span class="badge text-bg-dark">${ses.last_login }</span>
			</c:if>
		</div>

	

	<script type="text/javascript">
		const msg_login = `<c:out value="${msg_login }" />`; 
		if(msg_login === "1"){
			alert("로그인 실패");
		}
		
		const msg_remove = `<c:out value="${msg_remove }" />`; 
		if(msg_remove === "1"){
			alert("회원탈퇴 완료");
		}
		
		const msg_logout = `<c:out value="${msg_logout }" />`; 
		if(msg_logout === "1"){
			alert("로그아웃");
		}
		
		
	</script>
	
	
	<jsp:include page="layout/footer.jsp"></jsp:include>
		