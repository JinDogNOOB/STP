function Login() {

  var form = document.join;
  var i;

  //아이디에서 입력 필수 조건문

  if (form.uid.value == '') {
    alertify.error('아이디를 입력해야 합니다!');
    form.uid.focus();//포커스를 id박스로 이동.
    return false;
  }
  //아이디 입력 문자수를 4~12자로 제한하는 조건문

  if (form.uid.value.length < 4 || form.uid.value.length > 12) {
    alertify.confirm('아이디는 4~12자 이내로 입력 가능합니다!');

    form.uid.select();//입력한 문자를 선택 상태로 만듬.
    return false;
  }

  //입력된 문자의 길이만큼 루프를 돌면서 검사

  for (i = 0; i < form.uid.value.length; i++) {
    var ch = form.uid.value.charAt(i);//문자를 반환(정수형), 범위 검사 가능

    //입력된 문자를 검사

    if ((ch < 'a' || ch > 'z') && (ch < 'A' || ch > 'Z') && (ch < '0' || ch > '9')) {
      alertify.confirm('아이디는 영문 대소문자로만 입력 가능 합니다!');
      form.uid.select();
      form.uid.focus();
      return false;
    }
  }

  //입력된 첫번째 문자가 숫자인지 검사하는 조건문

  if (!isNaN(form.uid.value.substr(0, 1))) {
    alertify.confirm('아이디는 숫자로 시작할 수 없습니다!');
    form.uid.select();
    return false;
  }

  //패스워드 검사

  if (form.upassword.value == '') {
    alertify.error('패스워드를 입력 해야 합니다!');
    form.upassword.focus();//포커스를 Password박스로 이동.
    return false;
  }
  if (form.upassword.value.length < 4 || form.upassword.value.length > 12) {
    alertify.confirm('비밀번호는 4~12자 이내로 입력 가능 합니다!');
    form.upassword.select();
    return false;
  }
  return true;
}
