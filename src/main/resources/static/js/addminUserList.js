
$(document).ready(function(){
	console.log('시작 = ',$('input:radio[name="radioSale"]:checked').val());
    // 라디오버튼 클릭시 이벤트 발생
    $("input:radio[name=radioSale]").click(function(){
        if($("input:radio[name=radioSale]:checked").val() == 'true'){
            $(".radioSale").attr("disabled",false);
            // radio 버튼의 value 값이 1이라면 활성화
        	console.log($('input:radio[name="radioSale"]:checked').val());
        }else if($("input:radio[name=radioSale]:checked").val() == 'false'){
              $(".radioSale").attr("disabled",true);
          	console.log($('input:radio[name="radioSale"]:checked').val());
            // radio 버튼의 value 값이 0이라면 비활성화
        }
    });
});



let index = {
		init: function(){
			$("#btn-save").on("click", ()=>{ // function(){} , ()=>{} this를
												// 바인딩하기 위해서!!
				this.save();
			});
			$(".btn-update").on("click", (e)=>{ // function(){} , ()=>{} this를
												// 바인딩하기 위해서!!

				this.update(e);
			});
			$(".btn-delete").on("click", (e)=>{ // function(){} , ()=>{} this를
												// 바인딩하기 위해서!!
				this.userDelete(e);
			});
			$("#start").on("click",()=>{
				this.start();
			});
		},


		save: function(){


			// alert('user의 save함수 호출됨');
			let data = {
					parentProductId:$("#parentProductId").val(),
					relateProductId:$("#relateProductId").val(),
					title: $("#title").val(),
					thumb: $("#thumb").val(),
					price: $("#price").val(),
					disc: $("#disc").val(),
					discounted: $("#discounted").val(),
					content: $("#content").val(),
					bgImg: $("#fuck").val(),
					radioSale : $('input:radio[name="radioSale"]:checked').val(),
					radioAd : $('input:radio[name="radioAd"]:checked').val(),
					radioParentTypeId : $('input:radio[name="radioParentTypeId"]:checked').val(),
					radioBest : $('input:radio[name="radioBest"]:checked').val(),
					radioNew : $('input:radio[name="radioNew"]:checked').val(),

			};
			console.log('data = ',data);
			if(data.bgImg.length < 444){
				alert('사진미리보기를 누르지 않으셨습니다.');

					return;

			}

			if(fuck=="null"){
				alert('사진을 등록하시지않으셨습니다.');
					return;
			}
			console.log(data);


			// console.log(data);

			// ajax호출시 default가 비동기 호출
			// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!
			// ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해주네요.
			$.ajax({
				type: "POST",
				url: "/crud",
				data: JSON.stringify(data), // http body데이터
				contentType: "application/json; charset=utf-8",
			       // enctype: 'multipart/form-data',// body데이터가 어떤 타입인지(MIME)
				dataType: "text" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게
									// json이라면) => javascript오브젝트로 변경
			}).done(function(resp){
				console.log(resp);
				alert('성공');
//
					// location.href = "/list";


			}).fail(function(error){
				alert(JSON.stringify(error));
			});

		},

		update: function(e){

			console.log('e =',e);
			let temp = (e.target.id).replace("bt-update-","");

			let data2 = {
					id: temp
			};
			console.log('data의 id = ',data2.id);

			var formData = new FormData();
			formData.append('id', $("#id"+data2.id).val());
			formData.append('username', $("#modalUsername"+data2.id).val());
			formData.append('email', $("#modalEmail"+data2.id).val());
			formData.append('name', $("#modalName"+data2.id).val());
			formData.append('gender',  $("#modalGender"+data2.id).val());
			formData.append('phone', $("#modalPhone"+data2.id).val());
			formData.append('providerId', $("#modalProviderId"+data2.id).val());
			formData.append('total_amount', $("#modalTotal_amount"+data2.id).val());
			formData.append('role', $("#modalRole"+data2.id).val());
			//formData.append('cancel', $("#modalCancel"+data2.id).val());
			formData.append('createDate', $("#modalCreateDate"+data2.id).val());
			formData.append('password', $("#modalPassword"+data2.id).val());
			formData.append('provider', $("#modalProvider"+data2.id).val());
			formData.append('birthday', $("#modalBirthday"+data2.id).val());
			formData.append('address', $("#modalAddress"+data2.id).val());
			formData.append('detail_address', $("#modalDetail_address"+data2.id).val());
			formData.append('profile2', $("#modalProfile"+data2.id)[0].files[0]);


console.log('사진은? = ',$("#modalProfile"+data2.id)[0].files[0]);
console.log('이름은? = ',$("#modalUsername"+data2.id).val());
console.log('name은 ? = ',$("#modalName"+data2.id).val());
//
//if($("#file"+data2.id)[0].files[0]==undefined){
//
//}
console.log('뽐데이터 = ',formData);


//console.log('file = '+$("#file2"+data2.id)[0].files[0]);
//console.log('formData = '+$("#fuck"+data2.id).val());
			$.ajax({
			type: "PUT",
			url: "/changeUser/"+data2.id,
			contentType: 'multipart/form-data',
			data: formData, // http body데이터
			 processData: false,
			 contentType: false,
		// ssunsband.tistory.com/entry/Jquery-Ajax를-이용한-파일-업로드FormData-사용
		// [ssunsband] // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게 json이라면) =>
		// javascript오브젝트로 변경
			}).done(function(resp){
				alert("물품변경이 완료되었습니다.");
				// console.log(resp);
				// location.href = "/list";
			}).fail(function(error){
				alert(JSON.stringify(error));
			});
		},




		start: function(){


			// alert('user의 save함수 호출됨');
			let data = {
					changebgImg : $("#imgs").val(),

			};


			$.ajax({
				 url : '/instar',
			        type : 'POST',
			        data : data,
			        contentType : false,
			        processData : false

			}).done(function(resp){
				console.log(resp);
				alert('성공');
//
					// location.href = "/list";


			}).fail(function(error){
				alert(JSON.stringify(error));
			});

		},





		// delete 하는곳
		userDelete: function(e){
			// alert('user의 save함수 호출됨');
			let temp = (e.target.id).replace("bt-delete-","");
			let data = {
					id: temp
			};
			console.log('최종 = ',data.id);
			$.ajax({
				type: "delete",
				url: "/listUserDelete/"+data.id,
				data: JSON.stringify(data), // http body데이터
				contentType: "application/json; charset=utf-8",// body데이터가 어떤
																// 타입인지(MIME)
				dataType: "text" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게
									// json이라면) => javascript오브젝트로 변경
			}).done(function(resp){
				alert("회원삭제가 완료되었습니다.");
				console.log(resp);
				location.href = "/list";

			}).fail(function(error){
				alert(JSON.stringify(error));
			});

		},
}

function encodeImageFileAsURL(e) {
	var file = $('#file'+e).prop('files')[0];
	console.log('file = ',file);

console.log('e = ',e)

	var reader = new FileReader();
	reader.onloadend = function() {
		console.log('RESULT', reader.result);
		$('#fuck'+e).attr('value', reader.result);
		$('#test'+e).attr('src', reader.result);
	};

	reader.readAsDataURL(file);
}

function encodeImageFileAsURL2(e) {
	var file = $('#file2'+e).prop('files')[0];
	console.log('file = ',file);

console.log('e = ',e)

	var reader = new FileReader();
	reader.onloadend = function() {
		console.log('RESULT', reader.result);
		$('#fuck'+e).attr('value', reader.result);
		$('#test2'+e).attr('src', reader.result);
	};

	reader.readAsDataURL(file);
}





index.init();