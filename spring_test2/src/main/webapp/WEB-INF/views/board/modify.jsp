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
		  <input type="text" class="form-control" name="reg_date" id="r" placeholder="reg_date" value="${bvo.regDate }" readonly="readonly">
		</div>
		<div class="mb-3">
		  <label for="rc" class="form-label">read_count</label>
		  <input type="text" class="form-control" name="read_count" id="rc" placeholder="read_count" value="${bvo.readCount }" readonly="readonly">
		</div>
		
		<br>
		
		
		
	<!-- upload file 출력 영역 -->
		<c:set value="${bdto.flist }" var="flist" />
		<div class="mb-3">
			<ul class="list-group">
				<!-- 파일 개수만큼 li를 반복하여 파일 표시 > 타입이 1인 경우만 표시 -->
				<!-- li >>> div >>> img >>> div >>> 파일이름, 작성일, span size -->
				<c:forEach items="${flist }" var="fvo">
		  			<li class="list-group-item">
		  				<c:choose>
		  					<c:when test="${fvo.fileType > 0 }">
		  						<div>
		  							<!-- src : /uplaod/는 servlet-context에서 설정한 경로 -->
		  							<!-- ex. 파일명 : 467615d0-d63b-4397-a682-b50a6ea9c77b_testimage.jpg -->
		  							<img alt="No image" src="/up/${fvo.saveDir }/${fvo.uuid}_th_${fvo.fileName}">
		  						</div>
		  					</c:when>
		  					<c:otherwise>
		  						<div>
		  							<!-- 파일이 없는 경우(파일타입이 0인 경우) : 보통 icon 모양 넣는 것이 보편적 -->
		  							<!-- download 속성 사용 시 파일이름은 생략 가능 -->
		  							<a href="/up/${fvo.saveDir }/${fvo.uuid}_${fvo.fileName}" download="${fvo.fileName}">
		  							<svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-files" viewBox="0 0 16 16">
									  <path d="M13 0H6a2 2 0 0 0-2 2 2 2 0 0 0-2 2v10a2 2 0 0 0 2 2h7a2 2 0 0 0 2-2 2 2 0 0 0 2-2V2a2 2 0 0 0-2-2m0 13V4a2 2 0 0 0-2-2H5a1 1 0 0 1 1-1h7a1 1 0 0 1 1 1v10a1 1 0 0 1-1 1M3 4a1 1 0 0 1 1-1h7a1 1 0 0 1 1 1v10a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1z"/>
									</svg>
									</a>
		  						</div>
		  					</c:otherwise>
		  				</c:choose>
		  				<div>
		  					<!-- 파일상세내용 : 파일이름, 작성일, 파일사이즈 -->
		  					<div>${fvo.fileName }</div>
		  					${fvo.regDate }
		  					<span class="badge text-bg-warning">${fvo.fileSize } Byte</span>
		  					<button type="button" data-uuid="${fvo.uuid }" class="btn btn-outline-danger file-x">X</button>
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
			<button type="submit" class="btn btn-warning" id="regBtn">수정</button>
			<a href="/board/detail?bno=${bvo.bno }"><button type="button" class="btn btn-secondary">돌아가기</button></a>
			<a href="/board/list"><button type="button" class="btn btn-secondary">게시글</button></a>
		</div>
	</div>
	</form>
</div>



<script type="text/javascript" src="/re/js/boardModify.js"></script>
<script type="text/javascript" src="/re/js/boardRegister.js"></script>



<jsp:include page="../layout/footer.jsp" />




