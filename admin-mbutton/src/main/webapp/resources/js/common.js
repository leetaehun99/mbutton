/*팝업창*/
var objPopup;

var menuCalBack="";
/*숫자만 정규식*/
var expNumber = /[0-9]/;
/*콤마 정규식*/
var expComma =/(^[+-]?\d+)(\d{3})/;
var calendar; // 달력에 사용할 function

$(function() {
	calendar = function (type){
		$( type ).datepicker({
		    dateFormat: 'yy-mm-dd',
		    prevText: '이전 달',
		    nextText: '다음 달',
		    monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		    monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		    dayNames: ['일','월','화','수','목','금','토'],
		    dayNamesShort: ['일','월','화','수','목','금','토'],
		    dayNamesMin: ['일','월','화','수','목','금','토'],
		    showMonthAfterYear: true,
		    yearSuffix: '년'
		});
	};
	
	
	//삭제 데이터가 없는경우 hide
	//if($(".trFocus").html() == null || $(".trFocus").html() == undefined) {
	//	$("#allDel").hide();	//전체삭제 체크
	//	$("#delete").hide();	//삭제버튼
	//}
	
	//체크박스(전체체크)
	$("#allDel").click(function() {
		var chk = $(this).is(":checked");
		if(chk) {
			$("input[name=arrDel]").prop("checked", true);
			if($("input[name=historyMsg]") != undefined || $("input[name=historyMsg]") != null)  $("input[name=historyMsg]").prop("checked", true);
		}else {
			$("input[name=arrDel]").prop("checked", false);
			if($("input[name=historyMsg]") != undefined || $("input[name=historyMsg]") != null)  $("input[name=historyMsg]").prop("checked", false);
		}
	});
	
	$("input[name=arrDel]").click(function(){
		$("input[name=arrDel]").each(function(idx) {
			if($(this).is(":checked")) {
				if($("input[name=historyMsg]") != undefined || $("input[name=historyMsg]") != null)  {$("input[name=historyMsg]").eq(idx).prop("checked", true);}
			}else {
				if($("input[name=historyMsg]") != undefined || $("input[name=historyMsg]") != null)  {$("input[name=historyMsg]").eq(idx).prop("checked", false);}
			}
		});
	});
	

});


/*
 * 공백 체크
 * objname : 해당 필드 ID
 * formname : 폼 ID
 * msg : Alert 메세지
 */
function blankCheck(objname, formname, msg) {
	try	{
		var str = $(objname, formname).attr("value");
		str = $.trim(str);

		if( str == "" ) {
			if(msg != null && "" != msg){alert(msg);}
			$(objname, formname).focus();
			return false;
		} else {
			return true;
		}

	} catch (e) { 
		alert(e);
		return false;
	}
}

/*
* 길이 체크
* objname : 해당 필드 ID
* formname : 폼 ID
* msg : Alert 메세지
* msg : 길이 
*/
function blankLenCheck(objname, formname, msg,len) {
	try	{
		if(blankCheck(objname, formname, msg)){
			var str = $(objname, formname).attr("value");
			str = $.trim(str);
			if(str.length!=len){
				alert(len+"자리로 입력하셔야 합니다.");
				$(objname, formname).focus();
				return false;
			}else{
				return true;
			}			
		}else{
			return false;
		}

	} catch (e) { 
		alert(e);
		return false;
	}
}
/*
 * 숫자만 체크
 * objname : 해당 필드 ID
 * formname : 폼 ID
 * msg : Alert 메세지
 */
function onlyNumber(objname, formname, msg){
	try	{
		var str = $(objname, formname).attr("value");
		str = $.trim(str);
		if(!expNumber.test(str)){
			if(msg != null && "" != msg){alert(msg);}
			$(objname, formname).focus();
			return false;
		}else{
			return true;
		}
	} catch (e) { 
		alert(e);
		return false;
	}
}

/*
 * null 체크
 * objname : 해당 필드 ID
 * formname : 폼 ID
 * msg : Alert 메세지
 */
function nullCheck(val) {
	str = $.trim(val);

	if( str == "null" || str == "NULL") {
		
		return "";
	} else {
		return str;
	}
}

/*
 * 숫자 콤마 체크
 */
function disPlayComma(str){
	var n = nullCheck(str);
	while(expComma.test(n))
		n = n.replace(expComma,'$1'+','+'$2');
	return n;
}

/*
 * 주민번호
 */
function disPlayJumin(str){
	var n = nullCheck(str);
	return n.substr(0,6)+'-'+'*******';
}

/*
 * 소수점처리
 */
function disPlayFloat(str){
	var n = nullCheck(str);
	var temp = n.split(".");
	if(Number(temp[1])==0) return temp[0];
	else return Number(n);
}


function divAlert(title,content,w,h,callBack){
	$("#popTitle").text(title);
	$("#popCntnts").text(content);
	menuCalBack = callBack;
	$.blockUI({message:$("#popWrap"),css:{width:w+'px',height:h+'px'}});
}
//	divAlert("알림창","등록되었습니다","400","250","M1");
function divAlertCallBack(){
	if(menuCalBack == "M1"){
		$("#searchList").click();
	}
	
	$.unblockUI();
}
//팝업
function pop(url,name,w,h){
	if(objPopup!= null) objPopup.close(); 
	objPopup = window.open(url,name,'width='+w+',height='+h+',scrollbars=no,resizable=no,status=no,menubar=no,location=no');
} //Popup(스크롤바없음)

//브라우저  Check
function checkBrowser(){
	var agt = navigator.userAgent.toLowerCase();
	if (agt.indexOf("chrome") != -1) return 'CH'; 
	else if (agt.indexOf("opera") != -1) return 'OP'; 
	else if (agt.indexOf("msie") != -1) return 'IE'; 
	else if (agt.indexOf("firefox") != -1) return 'FF'; 
	else if (agt.indexOf("safari") != -1) return 'SF';
	else return 'IE'; 
}



passwdChecks = function(form,reverse){
	if(!blankCheck("#userPwd1", form, "패스워드는 필수입력 사항입니다."))  return false; 
	
	if($("#userPwd1",form).val().length<6 || $("#userPwd1",form).val().length>15){
		$("#userPwd1",form).focus();
		alert("패스워드는 최소 6자 이상 14자 이하 입력하세요");
		return false;
	}
	
	if(!blankCheck("#userPwd2", form, "패스워드 확인은 필수입력 사항입니다."))  return false; 
	
	if($("#userPwd1",form).val() != $("#userPwd2",form).val()){
		alert("패스워드 와 패스워드 확인의 값이 일치하지 않습니다.");
		return false;
	}
   
	$("#userPwd",form).val($("#userPwd1",form).val());
	return true;
};

idChecks = function(form,reverse){
	if(!blankCheck("#userId", form, "사용자ID는 필수입력 사항입니다.")) { return false; }
	if(reverse==0){
		if( $("#userId",form).val().length<6 || $("#userId",form).val().length>15 ){
			$("#userId",form).focus();
			alert("ID는 최소 6자 이상 14자 이하 입니다.");
			return false;
		}
		
		if(tempUserId != $("#userId", form).val()){
			alert("중복체크하세요");
			idDuplicat=false; 
			return false;
		}
		
		if(!idDuplicat) {
			alert("중복체크하세요"); 
			return false;
		}
		
	}
	return true;
};
/*
 * form formName
 * reverse 0:회원가입 1:회원정보수정
 * */
commonValidationCheck = function(form,reverse){
	if(idChecks(form,reverse)){
		if(passwdChecks(form,reverse)){
			
			if(!blankCheck("#userPwd", form, "패스워드는 필수입력 사항입니다."))  return false; 
	   
			if(!blankCheck("#hspId", form, "요양기관 코드는 필수입력 사항입니다."))  return false; 
			
			if(!expNumber.test($("#hspId",form).val())){
				alert("요양기관 코드는 숫자만 입력가능합니다.");$("#hspId",form).val('');$("#hspId",form).focus();return false;
			}
			
			if($("#hspId",form).val().length!=8){
				$("#hspId",form).focus();alert("요양기관코드를 바르게 입력하십시오. 8자리");
				return false;
			}
		
			if(!blankCheck("#hspNm", form, "요양기관명칭은 필수입력 사항입니다."))  return false; 
			
			if(!blankCheck("#hspDtNm", form, "병원장명은 필수입력 사항입니다."))  return false; 
			
			if(!blankCheck("#licenNum", form, "의사면허번호는 필수입력 사항입니다."))  return false; 
			
			if(!expNumber.test($("#licenNum",form).val())){
				alert("의사면허번호는 숫자만 입력가능합니다.");$("#licenNum",form).val('');$("#licenNum",form).focus();return false;
			}
			
			if($("#licenNum",form).val().length!=5){
				$("#licenNum",form).focus();alert("의사면허번호를 바르게 입력하십시오. 5자리");
				return false;
			}	
			
			
			/*if(!blankCheck("#zip1", form, "우편번호는 필수 입력사항입니다.")) { return false; }
			
			if(!expNumber.test($("#zip1",form).val())){
				alert("우편번호는 숫자만 입력가능합니다.");$("#zip1",form).val('');$("#zip1",form).focus();return false;
			}
			
			if($("#zip1",form).val().length!=3){
				$("#zip1",form).focus();alert("우편번호를 3자리씩 바르게 입력하십시오.");
				return false;
			}
			if(!blankCheck("#zip2", form, "우편번호는 필수 입력사항입니다.")) { return false; }
			
			if(!expNumber.test($("#zip2",form).val())){
				alert("우편번호는 숫자만 입력가능합니다.");$("#zip2",form).val('');$("#zip2",form).focus();return false;
			}
			
			if($("#zip2",form).val().length!=3){
				$("#zip2",form).focus();alert("우편번호를 3자리씩 바르게 입력하십시오.");
				return false;
			}
			if(!blankCheck("#addr", form, "병원주소 필수 입력사항입니다.")) { return false; }
			
			$("#zip",form).val($("#zip1",form).val()+"-"+$("#zip2",form).val());
			
			if(!blankCheck("#phone1", form, "병원연락처는 필수 입력사항입니다.")) { return false; }
			
			if(!blankCheck("#phone2", form, "병원연락처는 필수 입력사항입니다.")) { return false; }
			
			if(!expNumber.test($("#phone2",form).val())){
				alert("병원연락처는 숫자만 입력가능합니다.");$("#phone2",form).val('');$("#phone2",form).focus();return false;
			}
			
			if(!expNumber.test($("#phone3",form).val())){
				alert("병원연락처는 숫자만 입력가능합니다.");$("#phone3",form).val('');$("#phone3",form).focus();return false;
			}
			
			if($("#phone2",form).val().length!=3 && $("#phone2",form).val().length!=4){
				$("#phone2",form).focus();alert("전화번호 중간자리는 3자리이상입니다. 바르게 입력하십시오.");
				return false;
			}
			if(!blankCheck("#phone3", form, "병원연락처는 필수 입력사항입니다.")) { return false; }
			if($("#phone3",form).val().length!=4){
				$("#phone3",form).focus();alert("전화번호 마지막자리는 4자리입니다. 바르게 입력하십시오.");
				return false;
			}
			$("#phone",form).val($("#phone1",form).val()+"-"+$("#phone2",form).val()+"-"+$("#phone3",form).val());
			
			if(!blankCheck("#email1", form, "이메일은 필수 입력사항입니다.")) { return false; }
			if(!blankCheck("#email2", form, "이메일은 필수 입력사항입니다.")) { return false; }
			
			$("#email",form).val($("#email1",form).val()+"@"+$("#email2",form).val());*/
			
			if(reverse==1){
				if(!blankCheck("#msgLev",form, "메세지 레벨을 선택하십시오.")) { return false; }
		}
		return true;
	 }
	}
};

function commonRowPerChange(form,actionValue){
	form.action=actionValue;
	form.currentPage.value=1;
	form.submit();
}

/*
 * 진료자 나이
 */
function getAge(birthy){
	var aged=0;
		//진료일
		sy = parseInt($("#trtStartDt").val().substring(0, 4));
		sm = parseInt($("#trtStartDt").val().substring(4, 6));
		sd = parseInt($("#trtStartDt").val().substring(6, 8));
		//환자나이
		dy = parseInt(birthy.substring(0, 4));
		dm = parseInt(birthy.substring(4, 6));
		dd = parseInt(birthy.substring(6, 8));
		
		if(dm==sm){
			aged = (dd<=sd) ? sy-dy:sy-dy-1;
		}else if(dm<sm){
			aged =  sy-dy;
		}else{
			aged =  sy-dy-1;
		}
		if(aged<1) {
			var st = new Date(sy,sm,sd);
			var dt = new Date(dy,dm,dd);
			var getDiffTime = st.getTime() - dt.getTime();
			var getDay = Math.floor(getDiffTime/(1000*60*60*24));
			aged = getDay+"일";
		}else{
			aged = aged+"세";
		}
	return aged;
}

//삭제 Ajax
var deleteAjax = {
	success : function(resultData) {
		if( resultData > 0 ) {
			alert(resultData + "건이 삭제되었습니다.");
			if(popSearchList == "" && popSearchList == null && popSearchList == "undefined") {
				$("#searchList").click();
			} else {
				popSearchList();
			}
		} else {
			alert("삭제에 실패하였습니다.");
		}
	},
	type : "post",
	dataType : "json",
	error : function() {
		alert("서버와의 통신에 실패 하였습니다.");
	}
};

function replaceAll(str, before, after) {
	return str.split(before).join(after);
}