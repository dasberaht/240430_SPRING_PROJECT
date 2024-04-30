console.log("boardRegister.js in");

// 트리거를 클릭하면 file을 클릭하도록 설정
document.getElementById('trigger').addEventListener('click', ()=>{
    document.getElementById('file').click();
    
    
})

// > 정규표현식을 사용하여, 파일의 형식을 제한
// > 실행파일 막기(exe, bat, sh, mis, dll, jar..)
// > 파일 사이즈 체크(max : 20MB 보다 크면 막기)
// > 이미지 파일만 올리기(jpg, jpeg, gif, png, bmp ,bmo?)
const regExp = new RegExp("\.(exe|sh|bat|mis|dll|jar)$");
const regExpImg = new RegExp("\.(jpg|jpeg|gif|png|bmo)$");
const maxSize = 1024*1024*20;

// Validation : 규칙설정
// return 형식이 맞지 않는 경우 : 0 / 맞는 경우 : 1로 받기
function fileValidation(name, fileSize){
    // 파일형식명이 대문자/소문자로 들어오더라도, 소문자로 변경(ex. apple.JPG > apple.jpg)
    let fileName = name.toLowerCase();
    // (test 속성)파일 확장자에 실행파일이 존재한다면~ 을 조건으로 설정
    if(regExp.test(fileName)){  
        return 0;
    }else if(fileSize>maxSize){
        return 0;
    }else if(!regExpImg.test(fileName)){    // 이미지 파일형식이 아닐 경우 0을 리턴
        return 0;
    }else{
        return 1;
    }
}

// 첨부파일에 따라 등록이 가능한지 여부를 체크하는 함수 생성
document.addEventListener('change', (e)=>{
    console.log(e.target);
    if(e.target.id === 'file'){
        // 여러개의 파일이 배열로 들어온다.
        const fileObject = document.getElementById('file').files;
        console.log(fileObject);


        // 한번 true가 된 disabled는 다시 false로 돌아오지 않으므로, 활성화되도록 처리
        document.getElementById('regBtn').disabled = false;


        let div = document.getElementById('fileZone');
        // 기존에 등록한 파일이 있다면, 지우는 처리
        div.innerHTML="";

        // 각각의 파일은 fileValidation 함수에 의해 리턴(0/1) 여부를 체크
        let ul = `<ul class="list-group">`;
        // isOk는 모든 파일의 return의 값이 1이어야 업로드가 가능하므로, 
        // isOk 값에 *(곱하기)를 통해 그 값이 1인지 체크
        let isOk = 1;
        for(let file of fileObject){
            let ValidResult = fileValidation(file.name, file.size);     // 하나의 파일에 대한 결과
            isOk *= ValidResult;
            ul += `<li class="list-group-item">`;
            ul += `<div>${ValidResult ? '업로드 가능' : '업로드 불가'}</div> ${file.name}`;
            ul += `<span class="badge text-bg-${ValidResult ? 'success' : 'danger'}">${file.size}</span>`;
            ul += `</li>`;
        }

        ul+= '</ul>';
        div.innerHTML=ul;
        
        // 업로드가 불가능한 파일이 1개라도 존재한다면, 등록버튼 비활성화 처리
        if(isOk == 0){
            document.getElementById('regBtn').disabled = true;  // disabled : 버튼 비활성화 속성 > 단, 한번 비활성화 된 버튼은 페이지를 새로고침하지 않은 한, 바뀌지 않는다.
        }

    }
})


// <ul class="list-group">
//   <li class="list-group-item"> 
//      <div>업로드 가능</div> 파일이름
//      <span class="badge text-bg-success">파일 사이즈</span>
//   </li>
//   <li class="list-group-item"> <div>업로드 불가능</div> 파일이름
//      <span class="badge text-bg-danger">파일 사이즈</span>
//   </li>
// </ul>
