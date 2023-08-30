// 이메일 방식 입력
var emailCheck = /[0-9a-zA-Z]+(.[_a-z0-9-]+)*@(?:\w+\.)+\w+$/;

 // 비밀번호 영문자+숫자+특수조합(8~16자리 입력) 정규식
var pwdCheck = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$/;

// 이름 한글로 1~5 글자 입력
var nameCheck = /^[ㄱ-ㅎ가-힣]{1,5}$/;

// 숫자 2개 + '학번' 입력
var studentIdCheck = /^[0-9]{2}학번$/;

function email_check() {
    var email = document.getElementById("email_input");
    var email_error_text = document.getElementById("email_error_text");

    if(!emailCheck.test(email.value)) {
        email_error_text.style.display = 'block';
        return false;
    } else {
        email_error_text.style.display = 'none';
    }
}

function pwd_check() {
    var pwd = document.getElementById("password_input");
    var pwd_error_text = document.getElementById("password_error_text");

    if(!pwdCheck.test(pwd.value)) {
        pwd_error_text.style.display = 'block';
        return false;
    } else {
        pwd_error_text.style.display = 'none';
    }
}

function pwd_check2() {
    var pwd = document.getElementById("password_input");
    var pwd_check = document.getElementById("password_check_input");
    var pwd_check_error_text = document.getElementById("password_check_error_text");

    if(pwd.value !== pwd_check.value) {
        pwd_check_error_text.style.display = 'block';
        return false;
    } else {
        pwd_check_error_text.style.display = 'none';
    }
}

function name_check() {
    var name = document.getElementById("name_input");
    var name_error_text = document.getElementById("name_error_text");

    if(!nameCheck.test(name.value)) {
        name_error_text.style.display = 'block';
        return false;
    } else {
        name_error_text.style.display = 'none';
    }
}

function student_id_check() {
    var student_id = document.getElementById("student_id_input");
    var student_id_error_text = document.getElementById("student_id_error_text");

    if(!studentIdCheck.test(student_id.value)) {
        student_id_error_text.style.display = 'block';
        return false;
    } else {
        student_id_error_text.style.display = 'none';
    }
}

const maxLength = (e) =>{
    if(e.target.value.length > e.target.maxLength){
        e.target.value = e.target.value.slice(0, e.target.maxLength);
    }
}

document.getElementById("email_input").addEventListener("blur", function() {
    email_check();
});

document.getElementById("password_input").addEventListener("blur", function() {
    pwd_check();
});

document.getElementById("password_check_input").addEventListener("blur", function() {
    pwd_check2();
});

document.getElementById("name_input").addEventListener("blur", function() {
    name_check();
});

document.getElementById("student_id_input").addEventListener("blur", function() {
    student_id_check();
});