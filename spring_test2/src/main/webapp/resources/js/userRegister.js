console.log("userRegister in");

document.getElementById('chkEmailBtn').addEventListener('click', ()=>{
    const userEmail = document.getElementById('e').value;
    console.log(userEmail);

    if(userEmail == null || userEmail == ""){
        alert("이메일을 입력 후 시도해주세요.");
        document.getElementById('e').focus();
        return false;
    } else {

        sendChkEmailFromServer(userEmail).then(result=>{
            console.log(result);
            if(result == '0'){
                alert("중복된 이메일입니다. 다시 입력해주세요.");
            }else{
                return true;
            }
        })


    }

})



async function sendChkEmailFromServer(email){
    try {
        url = "/user/chkemail/" + email;
        config = {
            method : "get"
        }
        const resp = await fetch(url, config);
        const result = await resp.text();

        return result;

    } catch (error) {
        console.log(error);
    }
}







