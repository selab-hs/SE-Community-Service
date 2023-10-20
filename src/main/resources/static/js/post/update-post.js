document.addEventListener("DOMContentLoaded", function() {
  var authToken = window.localStorage.getItem('X-SELAB-AUTH-TOKEN');
  if(window.localStorage.getItem('X-SELAB-AUTH-TOKEN') == null) {
    alert('유저 토큰이 존재하지 않습니다');
    // 여기에 로컬 스토리지 토큰 삭제 추가
    location.href = "http://localhost:8080/login";
  }

  const pathArray = window.location.pathname.split("/");
  const contestPath = pathArray[2];
  console.log(contestPath)

  $.ajax({
    url: 'http://localhost:8080/api/v1/posts/'+ contestPath,
    method: 'GET',
    headers :{
      'X-SELAB-AUTH-TOKEN': authToken
    },
    success: function(result) {
      $('#post_title').val(result.data.title);
      $('#editorTxt').val(result.data.content);
    },
    error: function() {
      console.log('유저 토큰 정보를 불러오는데 실패하였습니다. 다시 로그인을 진행해주세요.');
      // 여기에 로컬 스토리지 토큰 삭제 추가
      location.href = "http://localhost:8080/login";
    }
  });
});


function update_post_submit() {
  var authToken = window.localStorage.getItem('X-SELAB-AUTH-TOKEN');
  console.log(authToken)
  if (authToken === null) {
    alert('유저 토큰이 존재하지 않습니다');
    // 여기에 로컬 스토리지 토큰 삭제 추가
    location.href = "http://localhost:8080/login";
  } else {
    oEditors.getById["editorTxt"].exec("UPDATE_CONTENTS_FIELD", [])
    let content = document.getElementById("editorTxt").value

    //if(content == '') {
    if(content == '<p>&nbsp;</p>') { //비어있어도 기본 P태그가 붙더라.
      alert("내용을 입력해주세요.")
      oEditors.getById["editorTxt"].exec("FOCUS")
      return
    } else {
      console.log(content)
    }

    let data = JSON.stringify({
      "title": $("#post_title").val(),
      "content": content
    })
    const pathArray = window.location.pathname.split("/");
    const contestPath = pathArray[2];
    $.ajax({
      url: "http://localhost:8080/api/v1/posts/"+contestPath,
      data: data,
      headers: {
        'Content-Type': 'application/json',
        'X-SELAB-AUTH-TOKEN': authToken
      },
      type: "PATCH",
      success: function () {
        location.href = "http://localhost:8080/viewPost/"+contestPath;

      },
      error: function (request) {
        alert(
            "code = " + request.status + "\nmessage = " + request.responseText);
      }
    });
  }
}