console.log("boardRegister.js in")


// 파일입력(트리거) 버튼 기능 구현
document.getElementById('trigger').addEventListener('click', () =>{
    document.getElementById('file').click();
})

// 실행파일에 대한 정규표현식 작성
const regExp = new RegExp("\.(exe | sh | bat | dll | jar | msi)$");
// 파일 최대 사이즈 설정(20M)
const maxSize = 1024*1024*20;

function fileValidation(fileName, fileSize){
    if(regExp.test(fileName)){
        return 0;
    }else if(fileSize > maxSize){
        return 0;
    }else{
        return 1;
    }
}


// <ul class="list-group list-group-flush">
// <li class="list-group-item">An item</li>

document.addEventListener('change', (a)=>{
    if(a.target.id == 'file'){     // file에 변화가 생겼다면~
        // input type='file' element에 저장된file의 정보를 가져오는 property
        const fileObj = document.getElementById('file').files;
        console.log(fileObj);

        // 업로드 버튼 활성화
        document.getElementById('regBtn').disabled = false;

        // 등록된 file의 정보를 filezone에 기록
        let div = document.getElementById('fileZone');
        // ul > li로 파일 목록 추가
        div.innerHTML = "";         // 기존에 추가되어 있던 값 지우기
        //여러 개의 등록파일이 모두 검증을 통과해야하기 때문에, isOk *로 각각 파일이 통과할 때마다 연산을 실행
        // > 통과 여부를 확인
        let isOk = 1;
        let ul =`<ul class="list-group list-group-flush">`;
        for(let file of fileObj){
            // 개별적으로 파일이 통과하는지 결과 받기
            let validResult = fileValidation(file.name, file.size);
            isOk *= validResult;
            ul += `<li class="list-group-item">`;
            ul += `<div class="mb-3">`;
            ul += `${validResult ? '<div class="fw-bold">업로드 가능</div>' : '<div class="fw-bold text-danger">업로드 불가</div>'}`;
            ul += `${file.name}</div>`;
            ul += `<span class="badge rounded-pill text-bg-${validResult ? 'success' : 'danger'}">${file.size} Byte</span>`;
            ul += `</li>`;
        }
        ul += `</ul>`;

        div.innerHTML = ul;

        if(isOk == 0){
            // 하나라도 파일이 검증을 통과하지 못한다면, 업로드 버튼을 비활성화
            document.getElementById('regBtn').disabled = true;
        }

    }
})


