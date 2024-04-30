<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    
<jsp:include page="../layout/header.jsp" />
<div class="container-md">
	<h4>BoardModifyPage</h4>
	
	<c:set value="${bdto.bvo }" var="bvo"></c:set>
	
	<form action="/board/modify" method="post" enctype="multipart/form-data">
	<div>
		<div class="mb-3">
		  <label for="n" class="form-label">bno</label>
		  <input type="text" class="form-control" name="bno" id="n" placeholder="bno" value="${bvo.bno }" readonly="readonly">
		</div>
		<div class="mb-3">
		  <label for="t" class="form-label">title</label>
		  <input type="text" class="form-control" name="title" id="t" placeholder="title" value="${bvo.title }">
		</div>
		<div class="mb-3">
		  <label for="w" class="form-label">writer</label>
		  <input type="text" class="form-control" name="writer" id="w" placeholder="writer" value="${bvo.writer }" readonly="readonly">
		</div>
		<div class="mb-3">
		  <label for="c" class="form-label">content</label>
		  <textarea class="form-control"  aria-label="With textarea" id="c" name="content" placeholder="content">${bvo.content }</textarea>
		</div>
		<div class="mb-3">
		  <label for="r" class="form-label">reg_date</label>
		  <input type="text" class="form-control" name="reg_date" id="r" placeholder="reg_date" value="${bvo.reg_date }" readonly="readonly">
		</div>
		<div class="mb-3">
		  <label for="rc" class="form-label">read_count</label>
		  <input type="text" class="form-control" name="read_count" id="rc" placeholder="read_count" value="${bvo.read_count }" readonly="readonly">
		</div>
		





		
		
		
		<!-- upload file 출력 영역 -->
		<c:set value="${bdto.flist }" var="flist" />
		<div class="mb-3">
			<ul class="list-group">
				<!-- 파일 개수만큼 li를 반복하여 파일 표시 > 타입이 1인 경우만 표시 -->
				<!-- li >>> div >>> img >>> div >>> 파일이름, 작성일, span size -->
				<c:forEach items="${flist }" var="fvo">
		  			<li class="list-group-item">
		  				<c:choose>
		  					<c:when test="${fvo.file_type > 0 }">
		  						<div>
		  							<!-- src : /uplaod/는 servlet-context에서 설정한 경로 -->
		  							<!-- ex. 파일명 : 467615d0-d63b-4397-a682-b50a6ea9c77b_testimage.jpg -->
		  							<img alt="No image" src="/upload/${fvo.save_dir }/${fvo.uuid}_th_${fvo.file_name}">
		  						</div>
		  					</c:when>
		  					<c:otherwise>
		  						<div>
		  							<!-- 파일이 없는 경우(파일타입이 0인 경우) : 보통 icon 모양 넣는 것이 보편적 -->
		  							
		  						</div>
		  					</c:otherwise>
		  				</c:choose>
		  				<div>
		  					<!-- 파일상세내용 : 파일이름, 작성일, 파일사이즈 -->
		  					<div>${fvo.file_name }</div>
		  					${fvo.reg_date }
		  					<span class="badge text-bg-warning">${fvo.file_size } Byte</span>
		  					<button type="button" data-uuid="${fvo.uuid }" class="btn btn-outline-danger file-x"><strong>X</strong></button>
		  				</div>
		  			</li>
	  			</c:forEach>
			</ul>
		</div>
		
		
		<!-- 파일 추가 영역 -->
		<div class="mb-3">
		  <label for="file" class="form-label">files</label>
		  <input type="file" class="form-control" name="files" id="file" multiple="multiple" style="display: none"><br>
		  <button type="button" class="btn btn-success" id="trigger">upload</button>
		</div>
		
		<!-- file 목록 표시 영역 -->
		<div class="mb-3" id="fileZone">
			
		</div>
		
		
		
		<div class="d-grid gap-2 d-md-flex justify-content-md-end">
		<button type="submit" class="btn btn-secondary" id="regBtn">수정</button>
		<a href="/board/detail?bno=${bvo.bno }"><button type="button" class="btn btn-secondary">돌아가기</button></a>
		<a href="/board/list"><button type="button" class="btn btn-secondary">게시글</button></a>
		</div>
	</div>
	</form>
</div>



<script type="text/javascript" src="/resources/js/boardModify.js"></script>
<script type="text/javascript" src="/resources/js/boardRegister.js"></script>



<jsp:include page="../layout/footer.jsp" />



