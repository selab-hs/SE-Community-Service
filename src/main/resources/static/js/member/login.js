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
            alert("성공 : " + result.data);
        },
        error: function(request){
            alert("code = "+ request.status + "\nmessage = " + request.responseText);
        }
    })
}

