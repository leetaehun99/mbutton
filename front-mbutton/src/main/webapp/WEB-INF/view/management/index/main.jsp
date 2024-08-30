<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript" src="/resources/js/cookie.js"></script>
<script type="text/javascript">
	var idDuplicat=false; // ID 중복체크
	var tempUserId=""; //임시 id
	$(function() {
		$("#createUser").click(function(){ //사용자 등록 폼 이동
			if(commonValidationCheck("#userForm",0)){
				$("#userForm").attr("action", "/index/createUser.json");
				$("#userForm").ajaxSubmit(createUserAjax);
			}
		});
		
		$("#duplicatCheck").click(function(){ //사용자 중복 체크
			idDuplicat = false;
			tempUserId = $("#userId", "#userForm").val();
			if(!blankCheck("#userId", "#userForm", "사용자ID는 필수입력 사항입니다.")) { return false; }
			$("#userForm").attr("action", "/user/duplicatCheck.json");
			$("#userForm").ajaxSubmit(duplicatCheckjax);
		});
		
	});
	
	//사용자 등록
	var createUserAjax = {
		success : function(resultData) {
			if( resultData >=0 ) {
				alert("등록성공");
				reset();
				$.unblockUI();
			} else {
				alert("등록실패");
			}
		},
		type : "post",
		dataType : "json",
		error : function() {
			alert("서버와의 통신에 실패 하였습니다.");
		}
	};
	
	// 사용자 중복 체크
	var duplicatCheckjax = {
		success : function(resultData) {
			if( $("#userId","#userForm").val().length<6 || $("#userId","#userForm").val().length>15 ){
				$("#userId","userForm").focus();
				alert("ID는 최소 6자 이상 14자 이하 입니다.");
				return false;
			}
			if( resultData == null ) {
				alert("사용가능");
				$("#userPwd1","#userForm").focus();
				idDuplicat=true;
			} else {
				alert("이미 존재하는 사용자ID는 입니다.");
				$("#userId","#userForm").val('');
				idDuplicat=false;
			}
		},
		type : "post",
		dataType : "json",
		error : function() {
			alert("서버와의 통신에 실패 하였습니다.");
			idDuplicat = false;
			$("#userId","#userForm").val('');
		}
	};
	
</script>
	<div class="contents123" style="background-image:url(/resources/img/background.png)"></div>
	<div class="spaceBox20"></div>
	<!-- 
	<div class="contents456">
		<div class="contents456Inner">
			<div class="buttons">    
				<ul>
					<li><a href="javascript:alert('준비중입니다.')"><img src="/resources/img/qna.png"></a></li>
					<li><a href="/screenGuide/selectScreenGuideList.doo"><img src="/resources/img/guide.png"></a></li>
					<li><a href="/notice/selectNoticeList.doo"><img src="/resources/img/notice.png"></a></li>
				</ul>
			</div>
		</div>
	</div>
 -->
<div id="memberContener" style="display:none;cursor:default;">
	<form id="userForm" name="userForm" action="/user/selectUserList.doo"method="post">
		<input type="hidden" id="userPwd" name="userPwd" value=""/>	
		<input type="hidden" id="zip" 	  name="zip"     value=""/>	
		<input type="hidden" id="phone"   name="phone" value=""/>	
		<input type="hidden" id="email"   name="email" value=""/>	
		<div style="border: 0px; height: 37px;">
			<img src="/resources/img/register_bar.png" /><img src="/resources/img/mid_bar.png" /><img src="/resources/img/close_bar.png" onclick="javascript:$.unblockUI();" style="cursor:pointer;">
		</div>
		<div style="padding: 20px; background: #f8f8f8; height:340px;">
			<table class="table7 table7_main" style="border-left: 1px solid #b5b5b5;border-bottom: 1px solid #b5b5b5;">
				<tbody>
					<tr>
						<th style="text-align:left; padding-left:30px;">사용자ID</th>
						<td style="text-align:left; padding-left:10px;padding-top:5px;padding-bottom:5px;border-right: 0px;">
						<input type="text" id="userId" name="userId" value="" size="20" height="28px" maxlength="14"/><span style="padding-left:30px;"><img id="duplicatCheck" align="absmiddle"/></span>
						</td>
						<td></td>
					</tr>
					<tr>
						<th style="text-align:left; padding-left:30px;">패스워드</th>
						<td style="text-align:left;padding-left:10px;padding-top:5px;padding-bottom:5px;" colspan="2"><input type="password" id="userPwd1"  value="" size="20"  maxlength="14" height="28px" /></td>
					</tr>
					<tr>
						<th style="text-align:left; padding-left:30px;">패스워드 확인</th>
						<td style="text-align:left;padding-left:10px;padding-top:5px;padding-bottom:5px;" colspan="2"><input type="password" id="userPwd2"  value="" size="20"  maxlength="14" height="28px" /></td>
					</tr>
					<tr>
						<th style="text-align:left; padding-left:30px;">요양기관기호</th>
						<td style="text-align:left;padding-left:10px;padding-top:5px;padding-bottom:5px;" colspan="2"><input type="text" id="hspId" name="hspId" value="" size="20"  maxlength="8" height="28px" /></td>
					</tr>
					<tr>
						<th style="text-align:left; padding-left:30px;">요양기관명칭</th>
						<td style="text-align:left;padding-left:10px;padding-top:5px;padding-bottom:5px;" colspan="2"><input type="text" id="hspNm" name="hspNm" value="" size="20"  maxlength="20" height="28px" /></td>
					</tr>
					<tr>
						<th style="text-align:left; padding-left:30px;">병원장명</th>
						<td style="text-align:left;padding-left:10px;padding-top:5px;padding-bottom:5px;" colspan="2"><input type="text" id="hspDtNm" name="hspDtNm" value="" size="20"  maxlength="8" height="28px" /></td>
					</tr>
				</tbody>
			</table>
			<div style="padding-top:30px;padding-left: 98px;"><a id="createUser" class="trRegisterFocus" onclick="javascript:createUser();"></a></div>
		</div>
	</form>	
</div>