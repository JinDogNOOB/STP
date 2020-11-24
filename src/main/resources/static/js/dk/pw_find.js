function pw_find() {

  var form = document.pwfind;
  var reg = /[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,2}$/;

  if(!form.uid.value){
    alertify.error("아이디를 입력하지 않았습니다!");
    form.uid.focus();
    return false;
  }

  if(form.email.value==""){
    alertify.error("이메일을 입력하지 않았습니다!");
    form.email.focus();
    return false;
  }

  if(!form.email.value.match(reg)) {
    alertify.error('적합하지 않은 이메일 형식입니다.');
    form.email.select();
    return false;
  }
  form.submit();
  return true;
}
