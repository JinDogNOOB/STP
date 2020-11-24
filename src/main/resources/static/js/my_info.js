function personal_info_modify() {

  var form = document.my_info_frame;

  if (form.uname.value == "" || form.upassword.value == "" || form.password_retry.value == "" || form.email.value == "") {
    alert("닉네임 또는 비밀번호 또는 이메일을 입력하지 않았습니다.");
    return false;
  }
  /*
  닉네임 수정 유효성 검사
   */
  if (form.uname.value == "") {
    alert("닉네임을 적어주세요.");
    form.nickname.focus();
    return false;
  }

  if (form.uname.value.length < 2 || form.uname.value.length > 8) {
    alert("닉네임은 2글자 이상 8글자 이하로 적어주세요!");
    form.uname.select();
    return false;
  }
  /*
비밀번호 유효성 검사
 */
  if (form.upassword.value == "") {
    alert("비밀번호를 입력하세요.");
    form.password.focus();
    return false;
  }
  if (form.upassword.value.length < 4 || form.upassword.value.length > 12) {
    alert("비밀번호는 4~12자 이내로 입력 가능 합니다!");
    form.password.select();
    return false;
  }
  /*
비밀번호 재입력 유효성 검사
 */
  if (form.password_retry.value == "") {
    alert("비밀번호를 재입력하세요.");
    form.password.focus();
    return false;
  }
  if(form.password_retry.value.length < 4 || form.password_retry.value.length > 12){
    alert("비밀번호 재입력도 4~12자 이내로 입력 가능 합니다.");
    form.password_retry.focus();
  }
  if (form.upassword.value != form.password_retry.value) {
    alert("비밀번호가 같지 않습니다!");
    return false;
  }
  if (form.email.value == "") {
    alert("이메일을 입력해주세요.");
    form.email.focus();
    return false;
  }

  return true;
}
