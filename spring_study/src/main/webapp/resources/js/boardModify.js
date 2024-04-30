console.log("boardModify.js in")

document.addEventListener('click', (e)=>{
    if(e.target.classList.contains('file-x')){
        let uuid=e.target.dataset.uuid;
        console.log(uuid);
        removeFileToServer(uuid).then(result=>{
            if(result == 1){
                alert('파일 삭제');
                e.target.closest('li').remove();        // e.target의 'li'를 지우기
            }
        })
    }
})


// 비동기 통신 restful 
// post : 데이터 삽입 (insert / update ...  : 데이터를 컨트롤러로 보낼 때 사용)
// get : 조회(생략가능)
// put(patch) : 데이터 수정
// delete : 데이터 삭제

async function removeFileToServer(uuid){
    try {
        const url = "/board/" + uuid;
        const config = {
            method : 'delete'
        }
        const resp = await fetch(url, config);
        const result = resp.text();
        return result;
    } catch (error) {
        console.log(error)
    }
}









