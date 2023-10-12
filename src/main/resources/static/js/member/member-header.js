document.addEventListener("DOMContentLoaded", function() {
  // 로컬 스토리지에서 토큰 가져오기
  const token = localStorage.getItem('X-SELAB-AUTH-TOKEN');

  if (token) {
  // 토큰이 있을 때
  $("#not_exist_token").hide(); // Log In 버튼 숨기기
  $("#exist_token").show(); // Log In 버튼 숨기기
  } else{
  // 토큰이 없을 때
  $("#not_exist_token").show(); // Log In 버튼 숨기기
  $("#exist_token").hide(); // Log In 버튼 숨기기
  }
});

function clear_token() {
  window.localStorage.clear();
  location.reload();
}
