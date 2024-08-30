<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script>
$(document).ready(function() {
	
	$("#userId, #userPwd", "#loginFrm").keypress(function(e) {
		if((e.which && e.which == 13) || (e.keyCode && e.keyCode == 13)) {
			login();
		}
	});
	$("#userId", "#loginFrm").focus();
	
});

function updateUser(){
	if(commonValidationCheck("#updateUserForm",1)){
	$("#updateUserForm").attr("action", "/user/updateUser.json");
	$("#updateUserForm").ajaxSubmit(userCheckAjax);
	}
};

var userCheckAjax = {
		success : function(resultData) {
			if (resultData >= 0) {
				alert("수정하였습니다.");
				$.unblockUI();
			} else {
				alert("수정 실패하였습니다.");
				$.unblockUI();
			}
		},
		type : "post",
		dataType : "json",
		error : function() {
			alert("서버와의 통신에 실패 하였습니다.");
			$.unblockUI();
		}
};


reset = function(){
	$("#userId","#userForm").val("");
	$("#userPwd1","#userForm").val("");
	$("#userPwd2","#userForm").val("");
	$("#hspId","#userForm").val("");
	$("#hspNm","#userForm").val("");
	$("#hspDtNm","#userForm").val("");
	$("#licenNum","#userForm").val("");
	$("#zip1","#userForm").val("");
	$("#zip2","#userForm").val("");
	$("#addr","#userForm").val("");
	$("#phone1","#userForm").val("");
	$("#phone2","#userForm").val("");
	$("#phone3","#userForm").val("");
	$("#email1","#userForm").val("");
	$("#email2","#userForm").val("");
};


loginDiv = function(){
	form="loginFrm";
	setTimeout("setUserId("+form+")",50);
	$.blockUI({message:$("#loginContener"),css:{border: 'none',width: '455px', left:'35%',top:'100px',backgroundColor:'none',cursor:'pointer'}, overlayCSS : {opacity:0.1,border: 'none'}});
};

createMemberDiv = function(){
	$.blockUI({message:$("#memberContener"),css:{border: 'none',width:'514px;',left:'35%',top:'100px',backgroundColor:'none',cursor:'pointer'}, overlayCSS : {opacity:0.1,border: 'none'}});
};

updateMemberDiv = function(){				
	
	$("#updateUserForm").attr("action", "/user/userInfo.json");
	$("#updateUserForm").ajaxSubmit(userInfoAjax);	
};

//회원정보 Ajax
var userInfoAjax = {
	success : function(resultData) {
		if( resultData != null ) {
			$("#userId","#updateUserForm").val(resultData.userId);
			$("#userPwd1","#updateUserForm").val(resultData.userPwd);
			$("#userPwd2","#updateUserForm").val(resultData.userPwd);
			$("#hspId","#updateUserForm").val(resultData.hspId);
			$("#hspNm","#updateUserForm").val(resultData.hspNm);			
			$("#hspDtNm","#updateUserForm").val(resultData.hspDtNm);
			$("#licenNum","#updateUserForm").val(resultData.licenNum);
			$("#zip1","#updateUserForm").val(resultData.zip.split("-")[0]);					
			$("#zip2","#updateUserForm").val(resultData.zip.split("-")[1]);
			$("#addr","#updateUserForm").val(resultData.addr);
			$("#phone1","#updateUserForm").val(resultData.phone.split("-")[0]);
			$("#phone2","#updateUserForm").val(resultData.phone.split("-")[1]);
			$("#phone3","#updateUserForm").val(resultData.phone.split("-")[2]);
			$("#email1","#updateUserForm").val(resultData.email.split("@")[0]);
			$("#email2","#updateUserForm").val(resultData.email.split("@")[1]);
			$("#msgLev","#updateUserForm").val(resultData.msgLev);
			$.blockUI({message:$("#updateMemberContener"),css:{border: 'none',width:'514px;',left:'35%',top:'100px',backgroundColor:'none'}, overlayCSS : {opacity:0.1,border: 'none'}});
		} else {
			alert("회원정보 조회에 실패 하였습니다.");
		}
	},
	type : "post",
	dataType : "json",
	error : function() {
		alert("서버와의 통신에 실패 하였습니다");
		$.unblockUI();
	}
};

//로그인 처리
login = function() {
	saveId("loginFrm");//쿠키 처리
	if(!blankCheck("#userId", "#loginFrm", "아이디를 입력해 주세요.")) { return false; }
	if(!blankCheck("#userPwd", "#loginFrm", "비밀번호를 입력해 주세요.")) { return false; }
	
	$("#loginFrm").attr("action", "/index/login.json");
	$("#loginFrm").ajaxSubmit( loginAjax );
};


// 로그인 Ajax
var loginAjax = {
	success : function(resultData) {
		var result = resultData.resultCd;
		if( result == "00000" ) {
			location.href = resultData.resultUrl;
		} else if( result == "00001" ) {
			alert("존재 하지 않는 ID 입니다.");
			$("#userId", "#loginFrm").val('');
			$("#userPwd", "#loginFrm").val('');$("#userId", "#loginFrm").focus();
		} else if( result == "00002" ) {
			alert("비밀번호가 일치 하지 않습니다.");
			$("#userPwd", "#loginFrm").val('');$("#userPwd", "#loginFrm").focus();
		} else if( result == "00003" ) {
			alert("사용자의 ID는 사용불가능 상태 입니다.");
			$("#userId", "#loginFrm").val('');
			$("#userPwd", "#loginFrm").val('');$("#userId", "#loginFrm").focus();
		} else {
			alert("존재하지 않는 아이디이거나 비밀번호가 일치하지 않습니다.");
			$("#userId", "#loginFrm").val('');
			$("#userPwd", "#loginFrm").val('');$("#userId", "#loginFrm").focus();
		}
	},
	type : "post",
	dataType : "json",
	error : function() {
		alert("오류가 발생하였습니다.");
	}
};

</script>
<spring:eval expression="@applicationProp['site.type']" var="siteType"/>
<div class="headerTop">
	<div class="headerTopInnerLogo">
		<a href="/index/main.doo"><img src="/resources/img/logo2.gif"></a>
	</div>
	<div class="headerTopInner"> <!-- 메뉴리스트 높이 조절 -->
		<ul>
			<li class="userNm">
				<c:if test="${USER.userNm != null}">
					<p><a onclick="javascript:updateMemberDiv();" style="cursor: pointer;">${USER.userNm}&nbsp;님&nbsp;&nbsp;</a><a href="/index/logout.doo"><img src="/resources/img/logout.gif" align="absmiddle" style="margin-top: 1px;"></a></p>
				</c:if>
				<c:if test="${USER.userNm == null}" >
					<p><a href="javascript:loginDiv();"><img src="/resources/img/login.gif" align="absmiddle" style="margin-top: 1px;"></a><a href="javascript:createMemberDiv();reset();"><img src="/resources/img/join.gif"  align="absmiddle" style="margin-top: 1px;"></a></p>
				</c:if>
			</li>
			
			
			<c:if test="${hMenuList != null}">
				<c:forEach var="menu" items="${hMenuList}" varStatus="idx">
					<c:if test="${menu.menuLev == '1'}">
						<c:if test="${USER.userLevCd != '00001' && USER.userLevCd eq menu.userLevCd && (siteType == menu.siteType || menu.siteType == 'ALL')}">
							<li class="headerSideMenuId" onclick="location.href='${menu.linkUrl}'"><span>${menu.menuNm}</span></li>
						</c:if>
					</c:if>
				</c:forEach>
			</c:if>
		</ul>
	</div>
</div>
	
<div id="loginContener" style="display:none;cursor:default;">
	<form id="loginFrm" name="loginFrm" method="post">
	<div class="mainTable">
		<div style="border: 0px; height: 37px;">
			<img src="/resources/img/login_bar.png" /><img src="/resources/img/mid_bar.png" /><img src="/resources/img/close_bar.png" onclick="javascript:$.unblockUI();" style="cursor:pointer;">
		</div>
		<div style="padding: 15px; background: #fff;">
			<table class="table7 table7_main">
				<tr>
					<th class="id" valign="top">ID</th>
					<td class="textId"><input type="text" id="userId" name="userId" size="20" maxlength="14" tabindex="1" value="" style="width:215px; height:19px;"/></td>
					<td rowspan="2" class="img" valign="top">
						<a href="#" onclick="login(); return false;"  id="imgLogin" ></a>
					</td>
				</tr>
				<tr>
					<th class="pass" valign="top">패스워드</th>
					<td class="textPass">
						<input type="password" id="userPwd" name="userPwd" size="20" maxlength="14" tabindex="2" value="" style="width:215px; height:19px;"><br/>
						<div class="chk"><input type="checkbox" id="checkId" name="checkId" style="position: relative; top:2px;cursor:pointer;" >&nbsp;아이디저장</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
	</form>
</div>