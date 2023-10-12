document.addEventListener("DOMContentLoaded", function() {
    if (window.localStorage.getItem('X-SELAB-AUTH-TOKEN') != null) {
        alert('유저 정보가 이미 존재합니다.');
        // 여기에 로컬 스토리지 토큰 삭제 추가
        location.href = "http://localhost:8080/home";
    }
});

function login_submit() {
    let data = JSON.stringify({
        "email" :  $("#login_id").val(),
        "password" : $("#login_password").val()
    });

    $.ajax({
        url: "http://localhost:8080/api/v1/auth/login",
        data: data,
        contentType: 'application/json',
        type: "POST",
        success: function(result) {
            alert("로그인 성공!");
            window.localStorage.setItem("X-SELAB-AUTH-TOKEN", result.data);
            location.href = "http://localhost:8080/home";
        },
        error: function(request){
            if(request.status === 400) {
                alert("로그인 정보가 잘못되었습니다");
            } else {
                alert("code = "+ request.status + "\nmessage = " + request.responseText);
            }
        }
    })
}