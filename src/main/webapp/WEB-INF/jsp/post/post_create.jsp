<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center">
	<div class="w-50">
		<h1>글쓰기</h1>
		<input type="text" id="subject" class="form-control"
			placeholder="제목을 입력하세요">
		<textarea id="content" class="form-control" rows="10"
			placeholder="내용을 입력하세요"></textarea>

		<div class="d-flex justify-content-end my-4">
			<input type="file" id="file" accept=".jpg,.png,.gif,.jpeg">
		</div>

		<div class="d-flex justify-content-between">
			<button id="postListBtn" type="button" class="btn btn-primary">목록</button>
			<div>
				<button id="clearBtn" type="button" class="btn btn-secondary">모두지우기</button>
				<button id="saveBtn" type="button" class="btn btn-pirmary">저장</button>
			</div>
		</div>





	</div>
</div>


<script>
	$(document).ready(function() {
		$('#postListBtn').on('click', function() {
			location.href = "/post/post_list_view";

		});

		//모두 지우기
		$('#clearBtn').on('click', function() {
			$('#subject').val(""); //내용을 공백으로
			$('#content').val("");
		});

		//글 내용 저장
		$('#saveBtn').on('click', function() {
			let subject = $('#subject').val().trim();
			if (subject.length < 1) {
				alert("제목을 입력하세요");
				return;
			}

			let content = $('#content').val();
			if (content == "") {
				alert("내용을 입력하세요");
				return;
			}
			//이미지 확장자 검증 - 파일이 업로드 된 경우
			let file = $('#file').val(); //pc 파일의 경로를 가져옴
			console.log(file)

			if (file != "") { //파일이 비어있지 않는 경우만 검증
				let ext = file.split(".").pop().toLowerCase(); //파일 경로를 .으로 나누고 마지막 문자열 배열 저장후 소문자로 변경
				if ($.inArray(ext, [ "gif", "jpg", "jpeg", "png" ]) == -1) { //해당문자와 같지 않으면 -1로 표시가 된다
					//js에서는 []안에 값만 넣으면 바로 배열이 된다.
					alert("gif, jpg, jpeg, png 파일만 업로드 할 수 있습니다")
					$("#file").val(""); //파일을 비운다

					return;
				}

			}
			//파일이 있는 경우에는 String만으로 ajax로 보낼 수 없다	

			//form태그를 js에서 만든다
			let formData = new FormData();
			formData.append("subject", subject); //<input type="" name="subject">와 같은 모양이다
			formData.append("content", content);
			//이미지 파일 저장(이미지 경로가 아닌 파일 그 자체를 저장한다)
			formData.append("file", $('#file')[0].files[0]); //파일을 배열에 저장 0번방 배열의 0번째(이것도 배열이다)에 저장.
			// $('file')[0]: 첫번째 input file태그, files[0]: 업로드 된 첫번째 파일

			$.ajax({
				type:"POST"
				,url:"/post/create"
				,data: formData
				,encType:"multipart/form-data" //파일 업로드 필수 설정, 무조건 넣어야한다(이미지 넣을때만)
				,processData: false //파일 업로드 필수 설정, data에 있는 것을 string으로 바꿔주는 것을 비활성화
				,contentType: false //파일 업로드 필수 설정
				
				,success:function(data) {
					if(data.result == "success") {
						alert("메모가 저장되었습니다.")
						location.href="/post/post_list_view";
					} else {
						alert(data.errorMessage);
					}
					
				}
				,error:function(e) {
					alert("메모 저장에 실패했습니다.")
				}
			});

		});

	});
</script>
