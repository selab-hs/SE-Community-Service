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
        $('#post_title').text(result.data.title);
        $('#post_writer').text(result.data.memberId);
        $('#post_content').val(result.data.content);
      }
    });
    const urlParams = new URLSearchParams("?postId=" + contestPath)
    $.ajax({
      url: 'http://localhost:8080/api/v1/comments?' + urlParams,
      type: 'GET',
      headers: {
        'X-SELAB-AUTH-TOKEN': authToken
      },
      success: function (result) {
        var arr = result.data;
        var commentsContainer = $('#comments');

        for (var i = arr.length - 1; 0 <= i; i--) {
          // Create elements
          var ul = $('<ul>').addClass('posts');
          var li = $('<li>');
          var article = $('<article>');
          var header = $('<header>');
          var h3 = $('<h3>').attr('id', 'comment_member_id').text(
              arr[i].comment);
          var p = $('<p>').addClass('published').attr('id',
              'comment_content').text(arr[i].commentId);
          var a = $('<a>').attr('href', 'page/single.html').addClass('image');
          var img = $('<img>').attr('src', '/images/user-post.svg').attr('alt', '');

          // Append elements
          header.append(h3);
          header.append(p);
          a.append(img);
          article.append(header);
          article.append(a);
          li.append(article);
          ul.append(li);

          // Append the created elements to the commentsContainer
          commentsContainer.append(ul);
        }
      }
    })
  }
});

function create_comment_submit() {
  const pathArray = window.location.pathname.split("/");
  const contestPath = pathArray[2];
  var authToken = window.localStorage.getItem('X-SELAB-AUTH-TOKEN');
  let data = JSON.stringify({
    "postId": contestPath,
    "comment": $("#comment-message").val()
  })

  $.ajax({
    url: "http://localhost:8080/api/v1/comments",
    data: data,
    headers: {
      'Content-Type': 'application/json',
      'X-SELAB-AUTH-TOKEN': authToken
    },
    type: "POST",
    success: function () {
      location.href = "http://localhost:8080/viewPost/" + contestPath;
    },
    error: function (request) {
      alert("code = " + request.status + "\nmessage = " + request.responseText);
    }
  });
}

function create_post_submit() {
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
      "title": $("#title").val(),
      "content": content
    })
    let result ={};
    const postType = document.getElementById('post-type');
    result.postType = postType.options[postType.selectedIndex].value;
    const urlParams = new URLSearchParams("?boardId=" + postType.options[postType.selectedIndex].value)
    console.log(postType.options[postType.selectedIndex].value);
    $.ajax({
      url: "http://localhost:8080/api/v1/posts?"+urlParams,
      data: data,
      headers: {
        'Content-Type': 'application/json',
        'X-SELAB-AUTH-TOKEN': authToken
      },
      type: "POST",
      success: function () {
        location.href = "http://localhost:8080/boards";

      },
      error: function (request) {
        alert(
            "code = " + request.status + "\nmessage = " + request.responseText);
      }
    });
  }
}

function updateButton() {
  const pathArray = window.location.pathname.split("/");
  const contestPath = pathArray[2];
  console.log(contestPath)
  location.href = "http://localhost:8080/updatePost/" + contestPath;
}


function deleteButton() {

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
      type: 'DELETE',
      headers: {
        'X-SELAB-AUTH-TOKEN': authToken
      },
      success: function () {
        location.href = "http://localhost:8080/boards";
      }
    });
  }
}