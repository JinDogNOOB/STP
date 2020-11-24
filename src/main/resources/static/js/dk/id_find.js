function id_find(){

  var form = document.idfind;
  var reg = /[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,2}$/;

  if(form.email.value==""){
    alertify.error('이메일을 입력하지 않았습니다!');
    form.email.focus();
    return false;
  }

  if(!form.email.value.match(reg)) {
    alertify.confirm('적합하지 않은 이메일 형식입니다.');
    form.email.focus();
    form.email.select();
    return false;
  }

  return true;
}
