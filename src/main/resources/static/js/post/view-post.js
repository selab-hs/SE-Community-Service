document.addEventListener('DOMContentLoaded', function () {
  var authToken = window.localStorage.getItem('X-SELAB-AUTH-TOKEN');
  console.log(authToken)
  if (authToken === null) {
    alert('유저 토큰이 존재하지 않습니다');
    // 여기에 로컬 스토리지 토큰 삭제 추가
    location.href = "http://localhost:8080/login";
  } else {
    // 요청 헤더에 토큰 추가
    var requestHeaders = {
      'Content-Type': 'application/json',
      'X-SELAB-AUTH-TOKEN': authToken
    };
    const pathArray = window.location.pathname.split("/");
    const contestPath = pathArray[2];
    console.log(contestPath)

    $.ajax({
      url: 'http://localhost:8080/api/v1/posts/' + contestPath,
      type: 'GET',
      headers: {
        'X-SELAB-AUTH-TOKEN': authToken
      },
      success: function (result) {
        $('#post_title').val(result.data.title);
        $('#post_content').val(result.data.content);
      }
    });
    const urlParams = new URLSearchParams("?boardId="+contestPath)
    $.ajax({
      url: 'http://localhost:8080/api/v1/comments/'+ contestPath,
      type: 'GET',
      headers: {
        'X-SELAB-AUTH-TOKEN': authToken
      },
      success: function (result) {
        $('#comment_member_id_title').val(result.data.title);
        $('#post_content').val(result.data.content);
      }
    });
  }
});
