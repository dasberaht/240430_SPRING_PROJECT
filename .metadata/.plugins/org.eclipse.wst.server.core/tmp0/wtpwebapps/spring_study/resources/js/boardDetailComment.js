console.log("boardDetailCommnet in")
console.log(bnoVal);

// 댓글 객체 생성(detail.jsp의 댓글 등록 영역 처리 / input = value / span = innertext)
document.getElementById('cmtAddBtn').addEventListener('click', () => {
    const cmtText = document.getElementById('cmtText').value;
    const cmtWriter = document.getElementById('cmtWriter').innerText;

    if(cmtText==null || cmtText=="") {
        alert("댓글을 입력해주세요.");
        document.getElementById('cmtText').focus();
        return false;
    } else {
        let cmtData = {
            bno : bnoVal,
            writer : cmtWriter,
            content : cmtText
        }
        console.log(cmtData);
        // 비동기 설정
        postCommentToServer(cmtData).then(result => {
            console.log(result);
            if(result=='1') {
                alert("댓글등록 완료");
                document.getElementById('cmtText').value="";

                // 화면에 뿌리기///////////////////////////////
                spreadCommentList(bnoVal);

            }
        })
    }


})




// 비동기 통신 restful 
// post : 데이터 삽입 (insert / update ...  : 데이터를 컨트롤러로 보낼 때 사용)
// get : 조회(생략가능)
// put(patch) : 데이터 수정
// delete : 데이터 삭제

// 가는(보내는) 구문(비동기 함수)
async function postCommentToServer(cmtData) {
    try {
        
        const url = "/comment/post";
        const config = {
            method : "post",
            headers : {
                "content-type" : "application/json; charset=utf-8"
            },
            body : JSON.stringify(cmtData)
        };

        const resp = await fetch(url, config);
        const result = await resp.text();   // isOk를 리턴받으므로, body만 text로 가져오기
        
        return result;

    } catch (error) {

        console.log(error);

    }
}


// 댓글 뿌리는 function (일반 함수)
function spreadCommentList(bno) {
    getCommentListFromServer(bno).then(result =>{
        console.log(result);

        //댓글 뿌리는 구문
        const div = document.getElementById('accordionPanelsStayOpenExample');
        if(result.length > 0) {
            // 값이 있다면, 기존 댓글 출력 구문을 버리기
            div.innerHTML = "";
            // 반복출력하도록 처리
            for(let i=0; i<result.length; i++) {

                let add = `<div class="accordion-item">`;
                add += `<h2 class="accordion-header">`;
                add += `<button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapse${i}" aria-expanded="true" aria-controls="collapse${i}">`;
                add += `No.${result[i].cno}  |  ${result[i].writer}  |  ${result[i].reg_date}`;
                add += `</button></h2>`;
                add += `<div id="collapse${i}" class="accordion-collapse collapse show" data-bs-parent="#accordionExample">`;
                add += `<div class="accordion-body d-grid gap-2 d-md-flex justify-content-md-end">`;
                add += `<input type="text" class="form-control cmtText" value="${result[i].content}">`;
                if(id === result[i].writer){
                add += `<button type="button" class="btn btn-secondary btn-sm cmtModBtn" data-cno="${result[i].cno}" >%</button>`;
                add += `<button type="button" class="btn btn-danger btn-sm cmtDelBtn" data-cno="${result[i].cno}">X</button>`;
                }
                add += `</div></div></div>`;

                div.innerHTML += add;

            };


        } else {    // result값이 없는 경우 
            div.innerHTML = `<div class="accordion-body"> Comment LIst Empty </div>`;
        }
    })
}

/*
<div class="accordion" id="accordionExample">
    <div class="accordion-item">
        <h2 class="accordion-header">
            <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                cno, ${bvo.writer }, ${bvo.reg_date }
            </button>
        </h2>
        <div id="collapseOne" class="accordion-collapse collapse show" data-bs-parent="#accordionExample">
            <div class="accordion-body">

                // 수정, 삭제 버튼 추가
                <button type="button" class="btn btn-secondary btn-sm">등록</button>
                <button type="button" class="btn btn-danger btn-sm">삭제</button>
                // input으로 댓글 내용 표시하도록 추가
                <input type="text" class="form-control">
            </div>
        </div>
    </div>
</div>
*/


// 가져오는 구문(비동기 함수)
async function getCommentListFromServer(bno) {
    try {
        // 표시되는 경로 : ex) /comment/306  
        const resp = await fetch("/comment/"+bno);
        const result = await resp.json();

        return result;

    } catch (error) {
        console.log(error);
    }
}


////////////////////////////////////////////
// 댓글 수정 / 삭제

document.addEventListener('click', (e) => {
    console.log(e.target);
    // 수정
    if(e.target.classList.contains('cmtModBtn')){
        console.log("e.target cmtModBtn in")
        let cnoVal = e.target.dataset.cno;
        console.log(cnoVal);
        let div = e.target.closest('div');
        console.log(div);
        let cmtText = div.querySelector(".cmtText").value;
        console.log(cmtText);
        let cmtData = {
            cno : cnoVal,
            content : cmtText
        };
        console.log(cmtData);
        
        // 수정(비동기 호출)
        updateCommentToServer(cmtData).then(result => {
            if(result == '1') {
                alert('댓글 수정 성공');
                spreadCommentList(bnoVal);
            }
        })
    }

    // 삭제
    if(e.target.classList.contains('cmtDelBtn')){
        let cnoVal = e.target.dataset.cno;
        // 비동기 호출
        removeCommentFromServer(cnoVal, bnoVal).then(result => {
            if(result=='1'){
                alert('댓글 삭제 성공');
                spreadCommentList(bnoVal);
            }
        })

    }

})


async function updateCommentToServer(cmtData) {
    try {
        
        const url = "/comment/modify";
        const config = {
            method : "put",
            headers : {
                'content-type' : 'application/json; charset=utf-8'
            },
            body : JSON.stringify(cmtData)
        }

        const resp = await fetch(url, config);
        const result = resp.text();

        return result;

    } catch (error) {
        console.log(error);
    }
}

//////////////////////////
// 댓글 삭제 (delete mapping)

async function removeCommentFromServer(cno, bno) {
    try {
        const url = "/comment/"+cno + "/" + bno;
        const config = {
            method : "delete"
        }

        const resp = await fetch(url, config);
        const result = await resp.text();

        return result;

    } catch (error) {
        console.log(error);
    }
}













