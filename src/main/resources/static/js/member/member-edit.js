// 비밀번호 영문자+숫자+특수조합(8~16자리 입력) 정규식
const pwdCheck = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$/;

// 이름 한글로 1~5 글자 입력
const nameCheck = /^[ㄱ-ㅎ가-힣]{1,5}$/;

let password_boolean = false;
let password_check_boolean = false;
let name_boolean = false;

function edit_pwd_check() {
    const pwd = document.getElementById("edit_password_input");
    const pwd_error_text = document.getElementById("edit_password_error_text");

    if(!pwdCheck.test(pwd.value)) {
        pwd_error_text.style.display = 'block';
        password_boolean = false;
    } else {
        pwd_error_text.style.display = 'none';
        password_boolean = true;
    }
}

function edit_pwd_check2() {
    const pwd = document.getElementById("edit_password_input");
    const pwd_check = document.getElementById("edit_password_check_input");
    const pwd_check_error_text = document.getElementById("edit_password_check_error_text");

    if(pwd.value !== pwd_check.value) {
        pwd_check_error_text.style.display = 'block';
        password_check_boolean = false;
    } else {
        pwd_check_error_text.style.display = 'none';
        password_check_boolean = true;
    }
}

function edit_name_check() {
    const name = document.getElementById("edit_name_input");
    const name_error_text = document.getElementById("edit_name_error_text");

    if(!nameCheck.test(name.value)) {
        name_error_text.style.display = 'block';
        name_boolean = false;
    }else {
        name_error_text.style.display = 'none';
        name_boolean = true;
    }
}

const maxLength = (e) =>{
    if(e.target.value.length > e.target.maxLength){
        e.target.value = e.target.value.slice(0, e.target.maxLength);
    }
}

document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("edit_password_input").addEventListener("blur", function() {
        edit_pwd_check();
    });

    document.getElementById("edit_password_check_input").addEventListener("blur", function() {
        edit_pwd_check2();
    });

    document.getElementById("edit_name_input").addEventListener("blur", function() {
        edit_name_check();
    });
});

let requestHeaders = {
    'Content-Type': 'application/json',
    'X-SELAB-AUTH-TOKEN': window.localStorage.getItem("X-SELAB-AUTH-TOKEN")
}

function edit_submit() {
    if(password_boolean && password_check_boolean
        && name_boolean) {
        let data = JSON.stringify({
            "email" :  $("#edit_email_input").val(),
            "password" : $("#edit_password_input").val(),
            "name" : $("#edit_name_input").val(),
            "grade" : $("#edit_grade_select").val(),
        });

        $.ajax({
            url: "http://localhost:8080/api/v1/members/edit",
            data: data,
            headers : requestHeaders,
            type: "PATCH",
            success: function() {
                alert("회원 정보 변경 성공 ! 다시 로그인을 진행해주세요");
                window.localStorage.clear();
                location.href = "http://localhost:8080/login";
            },
            error: function(request){
                alert("code = "+ request.status + "\nmessage = " + request.responseText);
            }
        })
    } else {
        alert("변경할 회원 정보가 올바르지 않습니다.");
        return false;
    }
}

document.addEventListener("DOMContentLoaded", function() {
    if(window.localStorage.getItem('X-SELAB-AUTH-TOKEN') == null) {
        alert('유저 토큰이 존재하지 않습니다');
        // 여기에 로컬 스토리지 토큰 삭제 추가
        location.href = "http://localhost:8080/login";
        return;
    }

    $.ajax({
        url: 'http://localhost:8080/api/v1/auth/info',
        method: 'GET',
        headers : requestHeaders,
        success: function(result) {
            $('#edit_email_input').val(result.data.userEmail);
            $('#edit_name_input').val(result.data.name);
            $('#edit_student_id_input').val(result.data.studentID);
            $('#edit_grade_select').val(result.data.grade);
        },
        error: function(response) {
            if (response.status === 401) {
                alert('유저 토큰이 만료되었거나 잘못되었습니다. 다시 로그인을 진행해주세요');
                window.localStorage.clear();
                location.href = "http://localhost:8080/login";
            } else {
                alert('유저 토큰 정보를 불러오는데 실패하였습니다. 다시 로그인을 진행해주세요.');
                window.localStorage.clear();
                location.href = "http://localhost:8080/login";
            }
        }
    });
});
