document.addEventListener('DOMContentLoaded', function () {
  var authToken = window.localStorage.getItem('X-SELAB-AUTH-TOKEN');
  console.log(authToken)
  if (authToken === null) {
    alert('유저 토큰이 존재하지 않습니다');
    // 여기에 로컬 스토리지 토큰 삭제 추가
    location.href = "http://www.se-community.net/login";
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
      url: 'http://www.se-community.net/api/v1/posts/' + contestPath,
      type: 'GET',
      headers: {
        'X-SELAB-AUTH-TOKEN': authToken
      },
      success: function (result) {
        $('#post_title').text(result.data.title);
        $('#post_content').val(result.data.content);

        if(result.data.boardId === 1){
          $('#post_boardId').text("공지 게시판");
        }
        if(result.data.boardId === 2){
          $('#post_boardId').text("자유 게시판");
        }
      }
    });

    $.ajax({
      url: 'http://www.se-community.net/api/v1/auth/info',
      method: 'GET',
      headers : {
        'X-SELAB-AUTH-TOKEN': authToken
      },
      success: function(result) {
        $('#post_writer').text(result.data.name);
      },
      error: function(response) {
        if (response.status === 401) {
          alert('유저 토큰이 만료되었거나 잘못되었습니다. 다시 로그인을 진행해주세요');
          window.localStorage.clear();
          location.href = "http://www.se-community.net/login";
        } else {
          alert('유저 토큰 정보를 불러오는데 실패하였습니다. 다시 로그인을 진행해주세요.');
          window.localStorage.clear();
          location.href = "http://www.se-community.net/login";
        }
      }
    });

    const urlParams = new URLSearchParams("?postId=" + contestPath)
    $.ajax({
      url: 'http://www.se-community.net/api/v1/comments?' + urlParams,
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
              'comment_content').text(arr[i].commentWriteMemberName);
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
      },
      error: function(response) {
        handleCommonError(response);
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
    url: "http://www.se-community.net/api/v1/comments",
    data: data,
    headers: {
      'Content-Type': 'application/json',
      'X-SELAB-AUTH-TOKEN': authToken
    },
    type: "POST",
    success: function () {
      location.href = "http://www.se-community.net/viewPost/" + contestPath;
    },
    error: function(response) {
      handleCommonError(response);
    }
  });
}

function create_post_submit() {
  var authToken = window.localStorage.getItem('X-SELAB-AUTH-TOKEN');
  console.log(authToken)
  if (authToken === null) {
    alert('유저 토큰이 존재하지 않습니다');
    // 여기에 로컬 스토리지 토큰 삭제 추가
    location.href = "http://www.se-community.net/login";
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
      url: "http://www.se-community.net/api/v1/posts?"+urlParams,
      data: data,
      headers: {
        'Content-Type': 'application/json',
        'X-SELAB-AUTH-TOKEN': authToken
      },
      type: "POST",
      success: function () {
        location.href = "http://www.se-community.net/boards";

      },
      error: function(response) {
        handleCommonError(response);
      }
    });
  }
}

function updateButton() {
  const pathArray = window.location.pathname.split("/");
  const contestPath = pathArray[2];
  console.log(contestPath)
  location.href = "http://www.se-community.net/updatePost/" + contestPath;
}


function deleteButton() {

  var authToken = window.localStorage.getItem('X-SELAB-AUTH-TOKEN');
  console.log(authToken)
  if (authToken === null) {
    alert('유저 토큰이 존재하지 않습니다');
    // 여기에 로컬 스토리지 토큰 삭제 추가
    location.href = "http://www.se-community.net/login";
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
      url: 'http://www.se-community.net/api/v1/posts/' + contestPath,
      type: 'DELETE',
      headers: {
        'X-SELAB-AUTH-TOKEN': authToken
      },
      success: function () {
        location.href = "http://www.se-community.net/boards";
      },
      error: function(response) {
        handleCommonError(response);
      }
    });
  }
}