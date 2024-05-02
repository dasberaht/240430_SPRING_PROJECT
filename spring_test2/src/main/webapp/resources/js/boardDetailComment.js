console.log("boardDetailComment in");

// (비동기) cmtAddBtn 클릭 시 bno, writer, content를 비동기로 DB에 넣기
document.getElementById('cmtAddBtn').addEventListener('click', ()=>{
    const cmtText = document.getElementById('cmtText').value;
    const cmtWriter = document.getElementById('cmtWriter').innerText;

    if(cmtText == null || cmtText == ""){
        alert("댓글을 입력 후 시도해주세요.")
        document.getElementById('cmtText').focus();
        return false;
    }else{
            let cmtData = {
                bno : bnoVal,
                writer : cmtWriter,
                content : cmtText
            }
            console.log(cmtData);

            postCommentToServer(cmtData).then(result => {
                if(result == '1'){
                    alert("등록완료");
                    document.getElementById('cmtText').value="";
                    spreadCommentList(bnoVal);
                }
            })

    }

})

// 보내기
async function postCommentToServer(cmtData){
    try {
        const url = "/comment/post";
        const config = {
            method : "post",
            headers : {
                "content-type" : "application/json; charset=utf-8"
            },
            body : JSON.stringify(cmtData)
        }

        const resp = await fetch(url, config);
        const result = await resp.text();

        return result;

    } catch (error) {
        console.log(error);
    }

}

// 가져오기
async function postCommentFromServer(bno, page){
    try {
        const resp = await fetch("/comment/" + bno + "/" + page);
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}


// <ul class="list-group list-group-flush" id="cmtListArea">
// <li class="list-group-item">
//   <div class="input-group mb-3">
//       <div class="fw-bold">Writer</div>
//       comment-content
//   </div>
//   <span class="badge rounded-pill text-bg-warning">regDate</span>
// <button type="button" class="btn btn-outline-danger">Danger</button>
// <button type="button" class="btn btn-outline-warning">Warning</button>
// </li>
// </ul>

// 출력하기
function spreadCommentList(bno, page=1){
    postCommentFromServer(bno, page).then(result =>{
        const ul = document.getElementById('cmtListArea');
        // ul.innerHTML="";
        if(result.cmtList.length > 0 ){
            if(page==1){
                ul.innerHTML="";
            }

            // for(let i=0; i<result.length; i++){

            //     let str = `<li class="list-group-item">`;
            //     str += `<div class="input-group mb-3">`;
            //     str += `<div class="fw-bold">${result[i].writer}</div><br>`;
            //     str += `${result[i].content}</div>`;
            //     str += `<span class="badge rounded-pill text-bg-warning">${result[i].regDate}</span>`;
            //     str += `<button type="button" class="btn btn-warning btn-sm mod" data-bs-toggle="modal" data-bs-target="#myModal">수정</button> `;
            //     str += `<button type="button" class="btn btn-danger btn-sm del">삭제</button>`;
            //     str += `</li>`;
                
            //     ul.innerHTML += str;
            // }

            for(let cvo of result.cmtList){
                let str = `<li class="list-group-item">`;
                str += `<div class="input-group mb-3"> No.${cvo.cno}　|`;
                str += `<div class="fw-bold">　${cvo.writer}</div>　`;
                str += `${cvo.content}`;
                str += `</div>`
                str += `<span class="badge rounded-pill text-bg-warning">${cvo.regDate}</span> `;
                // 수정, 삭제 버튼 영역
                str += `<button type="button" class="btn btn-outline-warning btn-sm mod" data-bs-toggle="modal" data-bs-target="#myModal">수정</button> `;
                str += `<button type="button" class="btn btn-outline-danger btn-sm del">삭제</button>`;
                str += `</li>`;
                
                ul.innerHTML += str;
            }
            console.log(result.cmtList);
            console.log(result);
            // 더보기 버튼 작업
            let moreBtn = document.getElementById('moreBtn');
            console.log(moreBtn);
            // moreBtn 표시되는 조건(페이지의 값이 5개 이상이되어야 나타남)


        } else {
            ul.innerHTML = `<div class="input-group mb-3">CommentList Empty</div>`;
        }

    })

}


document.addEventListener('click', (e)=>{
    if(e.target.id == 'moreBtn'){
        let page = parseInt(e.target.dataset.page);
        spreadCommentList(bnoVal, page);
    }

})




