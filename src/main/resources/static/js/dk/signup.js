
function sign() {

  var form = document.sign_up;
  var i;
  var reg = /[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,2}$/;


  //출처: https://sukkyu.tistory.com/83 [썩구노트]

  //닉네임 수정 유효성 검사

  if (form.uid.value == '') {
    // alert('아이디를 적어주세요.');
    alertify.error("아이디를 적어주세요!");
    form.uid.focus();
    return false;
  }

  if (form.uid.value.length < 4 || form.uid.value.length > 12) {
    alertify.confirm('아이디는 4글자 이상 12글자 이하로 적어주세요!');
    form.uid.select();
    return false;
  }

  //입력된 문자의 길이만큼 루프를 돌면서 검사

  for (i = 0; i < form.uid.value.length; i++) {
    var ch = form.uid.value.charAt(i);//문자를 반환(정수형), 범위 검사 가능

    //입력된 문자를 검사

    if (!(ch >= 'a' && ch <= 'z') && !(ch >= 'A' && ch <= 'Z') && !(ch >= '0' && ch <= '9')) {
      alertify.error('아이디는 영문 대소문자와 숫자만 입력 가능 합니다!');
      form.uid.focus();
      form.uid.select();
      return false;
    }
  }

  //입력된 첫번째 문자가 숫자인지 검사하는 조건문

  if (!isNaN(form.uid.value.substr(0, 1))) {
    alertify.error('아이디는 숫자부터 시작할 수 없습니다.');
    form.uid.focus();
    form.uid.select();
    return false;
  }

  /*
  닉네임 검사
   */

  if(form.uname.value == ''){
    alertify.error('닉네임을 입력하세요');
    form.uname.focus();
    return false;
  }

  if (form.uname.value.length < 4 || form.uname.value.length > 12) {
    alertify.confirm('닉네임는 4~12자 이내로 입력 가능 합니다!');
    form.uname.select();
    return false;
  }

  /*
비밀번호 유효성 검사
*/
  if (form.upassword.value == '') {
    alertify.error('비밀번호를 입력하세요.');
    form.upassword.focus();
    return false;
  }
  if (form.upassword.value.length < 4 || form.upassword.value.length > 12) {
    alertify.confirm('비밀번호는 4~12자 이내로 입력 가능 합니다!');
    form.upassword.select();
    return false;
  }
  /*
비밀번호 재입력 유효성 검사
*/
  if (form.password2.value == '') {
    alertify.error('비밀번호를 재입력하세요.');
    form.password2.focus();
    return false;
  }
  if (form.password2.length < 4 || form.password2.value.length > 12) {
    alertify.confirm('비밀번호 재입력도 4~12자 이내로 입력 가능 합니다.');
    form.password2.focus();
  }
  if (form.upassword.value != form.password2.value) {
    alertify.confirm('비밀번호가 같지 않습니다!');
    return false;
  }
  if (form.email.value == '') {
    alertify.error('이메일을 입력해주세요.');
    form.email.focus();
    return false;
  }
  if(!form.email.value.match(reg)){
    alertify.error('형식에 맞지 않습니다');
    form.email.select();
    return false;
  }

  form.submit();
  return true;
}
