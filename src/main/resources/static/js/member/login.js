document.addEventListener("DOMContentLoaded", function() {
    if (window.localStorage.getItem('X-SELAB-AUTH-TOKEN') != null) {
        alert('유저 정보가 이미 존재합니다.');
        location.href = "http://www.se-community.net/home";
    }
});

function login_submit() {
    let data = JSON.stringify({
        "email" :  $("#login_id").val(),
        "password" : $("#login_password").val()
    });

    $.ajax({
        url: "http://www.se-community.net/api/v1/auth/login",
        data: data,
        contentType: 'application/json',
        type: "POST",
        success: function(result) {
            alert("로그인 성공!");
            window.localStorage.setItem("X-SELAB-AUTH-TOKEN", result.data);
            location.href = "http://www.se-community.net/boards";
        },
        error: function(response){
            if(response.status === 400) {
                alert("로그인 정보가 잘못되었습니다");
            } else {
                alert("[ " + response.status + " ] " + JSON.parse(response.responseText).message);
            }
        }
    })
}