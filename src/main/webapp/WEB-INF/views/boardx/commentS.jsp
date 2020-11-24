<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script>

var bno = '${detail.bno}';
var boardName = '${boardName}';

$('[name=commentInsertBtn]').click(function(){ //댓글 등록 버튼 클릭시
	var insertData = $('[name=commentInsertForm]').serialize(); //commentInsertForm의 내용을 가져옴 
	commentInsert(insertData); // insert 함수호출 (아래)
});

//댓글 목록
function commentList(){
	$.ajax({
		url : '/comment/list',
		type : 'get',
		data : {'bno' : bno, 'boardName' : boardName},
		success : function(data){
			var a='';
			$.each(data, function(key, value){
				a += '<div class="commentArea" style="border-bottom:1px solid darkgray; color:white; margin-bottom: 15px;">';
				a += '<div class="commentInfo'+value.cno+'">'+'댓글번호 : '+value.cno+' / 작성자 : '+value.writer;
				a += '<a onclick="commentUpdate('+value.cno+',\''+value.content+'\');"> 수정 </a>';
				a += '<a onclick="commentDelete('+value.cno+');"> 삭제 </a> </div>';
				a += '<div class="commentContent'+value.cno+'"> <p> 내용 : '+value.content +'</p>';
				a += '</div></div>';
			});
			$(".commentList").html(a);
		}
	});
}

//댓글 등록
function commentInsert(insertData){
	$.ajax({
		url : '/comment/insert',
		type : 'post',
		data : insertData,
		success : function(data){
			if(data == 1){
				commentList(); // 댓글작성후 댓글목록 리로드 
				$('[name=content]').val('');
			}
		}
	});
}


//댓글 수정 내용 출력을 input 폼으로 변경 
function commentUpdate(cno, content){
	var a='';
	
	a += '<div class="input-group">';
	a += '<input type="text" class="form-control" name="content_'+cno+'" value="'+content+'"/>';
	a += '<span class="input-group-btn"><button class="btn btn-default" type="button" onClick="commentUpdateProc('+cno+');">수정</button></span>';
	a += '</div>';
	
	$('.commentContent'+cno).html(a);
}

//댓글 수정 
function commentUpdateProc(cno){
	var updateContent = $('[name=content_'+cno+']').val();
	
	$.ajax({
		url : '/comment/update',
		type : 'post',
		data : {'content' : updateContent, 'cno' : cno},
		success : function(data){
			if(data == 1) commentList(bno);
			if(data == 11) alert("error code : " + data + "댓글의 작성자가 아니십니다.");
		}
	});
}

//댓글 삭제 
function commentDelete(cno){
	$.ajax({
		url : '/comment/delete/'+cno,
		type : 'post',
		success : function(data){
			if(data == 1) commentList(bno); // 삭제후 목록 출력
			if(data == 11) alert("error code : " + data + "댓글의 작성자가 아니십니다.");
		}
	});
}

$(document).ready(function(){
	commentList(); // 페이지 로딩시 댓글 목록 출력 
});








</script>
<body>

</body>
