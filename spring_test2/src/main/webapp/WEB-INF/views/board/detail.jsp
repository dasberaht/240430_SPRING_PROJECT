<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    
<jsp:include page="../layout/header.jsp" />
<div class="container-md">
	<h4>BoardDetailPage</h4>
		
		<c:set value="${bdto.bvo }" var="bvo"></c:set>
		
		<div class="mb-3">
		  <label for="n" class="form-label">bno</label>
		  <input type="text" class="form-control" name="bno" id="n" placeholder="bno" value="${bvo.bno }" readonly="readonly">
		</div>
		<div class="mb-3">
		  <label for="t" class="form-label">title</label>
		  <input type="text" class="form-control" name="title" id="t" placeholder="title" value="${bvo.title }" readonly="readonly">
		</div>
		<div class="mb-3">
		  <label for="w" class="form-label">writer</label>
		  <input type="text" class="form-control" name="writer" id="w" placeholder="writer" value="${bvo.writer }" readonly="readonly">
		</div>
		<div class="mb-3">
		  <label for="c" class="form-label">content</label>
		  <textarea class="form-control"  aria-label="With textarea" id="c" name="content" placeholder="content" readonly="readonly">${bvo.content }</textarea>
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
		  							<!-- 파일이 없는 경우(파일타입이 0인 경우; image 파일이 아닌 경우) : 보통 icon 모양 넣는 것이 보편적 -->
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
		  				</div>
		  			</li>
	  			</c:forEach>
			</ul>
		</div>
		
		
		<div class="d-grid gap-2 d-md-flex justify-content-md-end">
			<a href="/board/modify?bno=${bvo.bno }"><button type="button" class="btn btn-warning">수정</button></a>
			<a href="/board/delete?bno=${bvo.bno }"><button type="button" class="btn btn-danger">삭제</button></a>
			<a href="/board/list"><button type="button" class="btn btn-secondary">게시글</button></a>
		</div>
		
		<br>
		<hr>
		<br>
	
		<!-- Comment line -->
 		<!-- 댓글 등록  -->
		<div class="input-group mb-3">
		  <span class="input-group-text" id="cmtWriter">Tester</span>
		  <input type="text" id="cmtText" class="form-control" placeholder="add comment" aria-label="Username" aria-describedby="basic-addon1">
		  <button type="button" id="cmtAddBtn" class="btn btn-secondary">등록</button>
		</div>
		
		<!-- 댓글 출력 -->
		<ul class="list-group list-group-flush" id="cmtListArea">
		  <li class="list-group-item">
			<div class="input-group mb-3">
				<div class="fw-bold">Writer</div><br>
				comment-content
			</div>
			<span class="badge rounded-pill text-bg-success">regDate</span>
		  </li>
		</ul>
		
		<!-- 댓글 더보기 버튼 -->
		<div class="d-grid gap-2 d-md-flex justify-content-md-center">
			<button type="button" id="moreBtn" data-page="1" class="btn btn-dark btn-sm" style="visibility:hidden"> More + </button>
		</div>
		
		<!-- 모달 창 -->
		<div class="modal" id="myModal" tabindex="-1">
		  <div class="modal-dialog modal-dialog-centered">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title">Writer</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		        comment-content
		        <input type="text" class="form-label" id="cmtTextMod">
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
		        <button type="button" class="btn btn-primary" id="cmtModBtn">Save changes</button>
		      </div>
		    </div>
		  </div>
		</div>
		
		
		
		


		<!-- 댓글 출력(아코디언) -->
<!--/* <div class="accordion" id="accordionPanelsStayOpenExample">
		  <div class="accordion-item">
		    <h2 class="accordion-header">
		      <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
		        -
		      </button>
		    </h2>
		    <div id="collapseOne" class="accordion-collapse collapse show" data-bs-parent="#accordionExample">
		      <div class="accordion-body">
		        <input type="text" class="form-control cmtText" value="-">

		      </div>
		    </div>
		  </div>
		</div> -->
</div>



<script type="text/javascript">
	const bnoVal = `<c:out value="${bvo.bno}" />`;
	console.log(bnoVal);
	
	/* const id = `<c:out value="${ses.id}" />`; */ 
</script>

<script type="text/javascript" src="/re/js/boardDetailComment.js" ></script>

<script type="text/javascript">
	spreadCommentList(bnoVal);
</script>



<jsp:include page="../layout/footer.jsp" />




