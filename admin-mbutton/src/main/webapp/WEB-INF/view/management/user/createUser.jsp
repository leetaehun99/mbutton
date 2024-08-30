<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript">
var idDuplicat=false; // ID 중복체크
	$(function() {
		$("#createUser").click(function(){ //사용자 등록 폼 이동
			if(validationCheck()){
				$("#userForm").attr("action", "/user/createUser.doo");
				$("#userForm").submit();
			}
		});

		$("#selectUser").click(function(){ //사용자 등록 폼 이동
			$("#userForm").attr("action", "/user/selectUserList.doo");
			$("#userForm").submit();
		});
		
		$("#duplicatCheck").click(function(){ //사용자 중복 체크
			idDuplicat = false;
			if(!blankCheck("#userId", "#userForm", "사용자ID는 필수입력 사항입니다.")) { return false; }
			$("#userForm").attr("action", "/user/duplicatCheck.json");
			$("#userForm").ajaxSubmit( duplicatCheckjax );
		});
		
	});
	
	// 사용자 중복 체크
	var duplicatCheckjax = {
		success : function(resultData) {
			if( resultData == null ) {
				alert("사용가능");
				$("#userPwd1").focus();
			} else {
				alert("이미 존재하는 사용자ID 입니다.");
				$("#userId").val('');
			}
			idDuplicat=true;
		},
		type : "post",
		dataType : "json",
		error : function() {
			alert("서버와의 통신에 실패 하였습니다.");
			idDuplicat = false;
			$("#userId").val('');
		}
	};
	
	passwdCheck = function(){
		if(!blankCheck("#userPwd1", "#userForm", "패스워드는 필수입력 사항입니다.")) { return false; }
		if(!blankCheck("#userPwd2", "#userForm", "패스워드 확인은 필수입력 사항입니다.")) { return false; }
		if($("#userPwd1").val() != $("#userPwd2").val()){alert("패스워드 와 패스워드 확인의 값이 일치하지 않습니다.");return false;}
		$("#userPwd").val($("#userPwd1").val());
		return true;
	}
	
	validationCheck = function(){
		if(!blankCheck("#userId", "#userForm", "사용자ID는 필수입력 사항입니다.")) { return false; }
		
		
		if(!idDuplicat){alert("ID중복체크를 하십시오");return false;}
		if(passwdCheck()){
			if(!blankCheck("#userPwd", "#userForm", "패스워드는 필수입력 사항입니다.")) { return false; }
			
			if(!blankCheck("#hspId", "#userForm", "요양기관 코드는 필수입력 사항입니다.")) { return false; }
			if($("#hspId").val().length!=8){$("#hspId").focus();alert("요양기관코드를 바르게 입력하십시오");return false;}
			
			if(!blankCheck("#hspNm", "#userForm", "요양기관명칭은 필수입력 사항입니다.")) { return false; }
			if(!blankCheck("#hspDtNm", "#userForm", "병원장명은 필수입력 사항입니다.")) { return false; }
			if(!blankCheck("#licenNum", "#userForm", "의사면허번호는 필수입력 사항입니다.")) { return false; }
			
		/* 	if(!blankCheck("#zip1", "#userForm", "우편번호는 필수 입력사항입니다.")) { return false; }
			if(!blankCheck("#zip2", "#userForm", "우편번호는 필수 입력사항입니다.")) { return false; }
			if(!blankCheck("#addr", "#userForm", "병원주소 필수 입력사항입니다.")) { return false; }
			$("#zip").val($("#zip1").val()+"-"+$("#zip2").val());
			if(!blankCheck("#phone1", "#userForm", "병원연락처는 필수 입력사항입니다.")) { return false; }
			if(!blankCheck("#phone2", "#userForm", "병원연락처는 필수 입력사항입니다.")) { return false; }
			if(!blankCheck("#phone3", "#userForm", "병원연락처는 필수 입력사항입니다.")) { return false; }
			$("#phone").val($("#phone1").val()+"-"+$("#phone2").val()+"-"+$("#phone3").val());
			if(!blankCheck("#email1", "#userForm", "이메일은 필수 입력사항입니다.")) { return false; }
			if(!blankCheck("#email2", "#userForm", "이메일은 필수 입력사항입니다.")) { return false; }
			$("#email").val($("#email1").val()+"@"+$("#email2").val());
			 */
			return true;
		}else{
			return false;
		}
	}
</script>

<form id="userForm" name="userForm" action="/user/selectUserList.doo"method="post">
	<input type="hidden" id="userPwd" name="userPwd" value=""/>	
	<input type="hidden" id="zip" 	  name="zip"     value=""/>	
	<input type="hidden" id="phone"   name="phone" value=""/>	
	<input type="hidden" id="email"   name="email" value=""/>	
	
	<table class="table5">
		<caption><img src="/resources/img/userReg.gif"></caption>
		<colgroup>
			<col width="30%">
			<col width="*">
		</colgroup>
		<tbody>
			<tr>
				<th class="r">사용자ID</th>
				<td class="l"><input type="text" id="userId" name="userId" value="" size="20"  maxlength="20"/><span style="padding-left:30px;"><img id="duplicatCheck" align="absmiddle"/></span></td>
			</tr>
			<tr>
				<th class="r">패스워드</th>
				<td class="l"><input type="password" id="userPwd1"  value="" size="20"  maxlength="20"/></td>
			</tr>
			<tr>
				<th class="r">패스워드 확인</th>
				<td class="l"><input type="password" id="userPwd2"  value="" size="20"  maxlength="20"/></td>
			</tr>
			<tr>
				<th class="r">요양기관기호</th>
				<td class="l"><input type="text" id="hspId" name="hspId" value="" size="20"  maxlength="8"/></td>
			</tr>
			<tr>
				<th class="r">요양기관명칭</th>
				<td class="l"><input type="text" id="hspNm" name="hspNm" value="" size="20"  maxlength="20"/></td>
			</tr>
			<tr>
				<th class="r">병원장명</th>
				<td class="l"><input type="text" id="hspDtNm" name="hspDtNm" value="" size="20"  maxlength="8"/></td>
			</tr>
			<tr>
				<th class="r">의사면허번호</th>
				<td class="l"><input type="text" id="licenNum" name="licenNum" value="" size="20"  maxlength="8"/></td>
			</tr>
			<tr>
				<th class="r">병원주소</th>
				<td class="l"><input type="text" id="zip1" value="" size="4"  maxlength="4"/>-<input type="text" id="zip2" value="" size="4"  maxlength="4"/><input type="text" id="addr" name="addr" value="" size="20"  maxlength="50"/></td>
			</tr>
			<tr>
				<th class="r">병원연락처</th>
				<td class="l">
					<ct:code name="phone1" type="select" groupCode="00049" selectCode="" />-
					<input type="text" id="phone2" value="" size="4"  maxlength="4"/>-<input type="text" id="phone3" value="" size="4"  maxlength="4"/></td>
			</tr>
			<tr>
				<th class="r">이메일</th>
				<td class="l">
					<input type="text" id="email1" value="" size="20"  maxlength="20"/>@
					<ct:code name="email2" type="select" groupCode="00050" selectCode="" />
				</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="2" style="text-align:right; vertical-align: middle; padding-right:10px;">
				<span class="button medium icon"><span class="check"></span><a id="createUser">등록</a></span>
				<span class="button medium icon"><span class="check"></span><a id="selectUser">목록</a></span>
				</td>
			</tr>
		</tfoot>
	</table>
</form>	