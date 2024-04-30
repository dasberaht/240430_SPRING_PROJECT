<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<jsp:include page="../layout/header.jsp" />

	<div class="container-md">
		<h4>BoardListpage</h4>
		
		<!-- 검색 영역 -->
		<form action="/board/list" method="get">
			<div class="input-group">
			<select class="form-select" name="type" id="inputGroupSelect04" aria-label="Example select with button addon">
			    <option ${ph.pgvo.type == null ? 'selected' : '' }>Type</option>
			    <option value="t" ${ph.pgvo.type eq 't' ? 'selected' : '' }>제목</option>
			    <option value="w" ${ph.pgvo.type eq 'w' ? 'selected' : '' }>작성자</option>
			    <option value="c" ${ph.pgvo.type eq 'c' ? 'selected' : '' }>내용</option>
			    <option value="tc" ${ph.pgvo.type eq 'tc' ? 'selected' : '' }>제목+내용</option>
			    <option value="wc" ${ph.pgvo.type eq 'wc' ? 'selected' : '' }>작성자+내용</option>
			    <option value="tw" ${ph.pgvo.type eq 'tw' ? 'selected' : '' }>제목+작성자</option>
			    <option value="twc" ${ph.pgvo.type eq 'twc' ? 'selected' : '' }>전체</option>
			</select>
			  <input type="text" name="keyword" value="${ph.pgvo.keyword }" class="form-control" aria-label="Text input with segmented dropdown button" placeholder="Search">
			  <input type="hidden" name="pagoNo" value="1">
			  <input type="hidden" name="qty" value="10">
			  
			  <button type="submit" class="btn btn-secondary position-relative">
			  Search
			  <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
			    ${ph.totalCount }
			    <span class="visually-hidden">unread messages</span>
			  </span>
			  </button>
			  
			</div>
		</form>
		
		<table class="table">
			<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col">title</th>
					<th scope="col">writer</th>
					<th scope="col">reg_date</th>
					<th scope="col">read_count</th>
					
					
					<th scope="col">cmt_qty</th>
					<th scope="col">has_file</th>
					
					
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list }" var="a">
					<tr>
						<td>${a.bno }</td>						
						<td><a href="/board/detail?bno=${a.bno }">${a.title }</a> ${a.cmt_qty } ${a.has_file } </td>						
						<td>${a.writer }</td>						
						<td>${a.reg_date }</td>						
						<td>${a.read_count }</td>
						
												
						<td>${a.cmt_qty }</td>						
						<td>${a.has_file }</td>
						
												
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="d-grid gap-2 d-md-flex justify-content-md-end">
		<c:if test="${ses.id ne null }">
		<a href="/board/register"><button type="button" class="btn btn-secondary">글쓰기</button></a>
		</c:if>
		<a href="/"><button type="button" class="btn btn-secondary">홈으로</button></a>
		</div>
		<!-- 페이지네이션 영역 -->
		<nav aria-label="Page navigation example">
		  <ul class="pagination justify-content-center">
		  	<!-- $type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword} -->
		  	<c:if test="${ph.prev }">
		    <li class="page-item">
		      <a class="page-link" href="/board/list?pageNo=${ph.startPage-1 }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" aria-label="Previous">
		      	<span aria-hidden="true">Previous</span>
		      </a>
		    </li>
		    </c:if>
		    
		    <c:forEach begin="${ph.startPage }" end="${ph.endPage }" var="i">  
		    <li class="page-item"><a class="page-link" href="/board/list?pageNo=${i }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">${i }</a></li>
		    </c:forEach>
		    
		    <c:if test="${ph.next }">
		    <li class="page-item">
		      <a class="page-link" href="/board/list?pageNo=${ph.endPage+1 }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" aria-label="Next">
		      	<span aria-hidden="true">Next</span>
		      </a>
		    </li>
		    </c:if>
		    
		  </ul>
		</nav>
		
	</div>

<jsp:include page="../layout/footer.jsp" />

