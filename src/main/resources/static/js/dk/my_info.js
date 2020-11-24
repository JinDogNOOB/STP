function personal_info_modify() {

  var form = document.my_info_frame;
  var reg = /[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,2}$/;

  /*
  닉네error유효성 검사
   */
  if (form.uname.value == "") {
    alertify.error("닉네임을 적어주세요.");
    form.uname.focus();
    return false;
  }

  if (form.uname.value.length < 2 || form.uname.value.length > 8) {
    alertify.confirm("닉네임은 2글자 이상 8글자 이하로 적어주세요!");
    form.uname.select();
    return false;
  }
  /*
비밀번호 유효성 검사
 */
  if (form.upassword.value == "") {
    alertify.error("비밀번호를 입력하세요.");
    form.upassword.focus();
    return false;
  }
  if (form.upassword.value.length < 4 || form.upassword.value.length > 12) {
    alertify.confirm("비밀번호는 4~12자 이내로 입력 가능 합니다!");
    form.upassword.select();
    return false;
  }
  /*
비밀번호 재입력 유효성 검사
 */
  if (form.password_retry.value == "") {
    alertify.error("비밀번호를 재입력하세요.");
    form.password_retry.focus();
    return false;
  }
  if(form.password_retry.value.length < 4 || form.password_retry.value.length > 12){
    alertify.confirm("비밀번호 재입력도 4~12자 이내로 입력 가능 합니다.");
    form.password_retry.focus();
  }
  if (form.upassword.value != form.password_retry.value) {
    alertify.confirm("비밀번호가 같지 않습니다!");
    return false;
  }
  if (form.email.value == '') {
    alertify.error("이메일을 입력해주세요.");
    form.email.focus();
    return false;
  }
  if(!form.email.value.match(reg)){
    alertify.error('이메일 형식에 맞지 않습니다.');
    form.email.select();
    return false;
  }
  form.submit();
  return true;
}
