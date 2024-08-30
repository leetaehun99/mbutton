<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript">
var idDuplicat=false; // ID 중복체크
	$(function() {
		$("#createUser").click(function(){ //사용자 등록 폼 이동
			if(validationCheck()){
				$("#userForm").attr("action", "/user/updateUser.doo");
				$("#userForm").submit( );
			}
		});

		$("#selectUser").click(function(){ //사용자 등록 폼 이동
			$("#userForm").attr("action", "/user/selectUserList.doo");
			$("#userForm").submit();
		});
		
	});
	
	passwdCheck = function(){
		if(!blankCheck("#userPwd1", "#userForm", "패스워드는 필수입력 사항입니다.")) { return false; }
		if(!blankCheck("#userPwd2", "#userForm", "패스워드 확인은 필수입력 사항입니다.")) { return false; }
		if($("#userPwd1").val() != $("#userPwd2").val()){alert("패스워드 와 패스워드 확인의 값이 일치하지 않습니다.");return false;}
		$("#userPwd").val($("#userPwd1").val());
		return true;
	}
	
	validationCheck = function(){
		if(passwdCheck()){
			if(!blankCheck("#userPwd", "#userForm", "패스워드는 필수입력 사항입니다.")) { return false; }
			if(!blankCheck("#hspNm", "#userForm", "요양기관명칭은 필수입력 사항입니다.")) { return false; }
			if(!blankCheck("#hspDtNm", "#userForm", "병원장명은 필수입력 사항입니다.")) { return false; }
			if(!blankCheck("#licenNum", "#userForm", "의사면허번호는 필수입력 사항입니다.")) { return false; }
			
			/* if(!blankCheck("#zip1", "#userForm", "우편번호는 필수 입력사항입니다.")) { return false; }
			if(!blankCheck("#zip2", "#userForm", "우편번호는 필수 입력사항입니다.")) { return false; }
			if(!blankCheck("#addr", "#userForm", "병원주소 필수 입력사항입니다.")) { return false; }
			$("#zip").val($("#zip1").val()+"-"+$("#zip2").val());
			if(!blankCheck("#phone1", "#userForm", "병원연락처는 필수 입력사항입니다.")) { return false; }
			if(!blankCheck("#phone2", "#userForm", "병원연락처는 필수 입력사항입니다.")) { return false; }
			if(!blankCheck("#phone3", "#userForm", "병원연락처는 필수 입력사항입니다.")) { return false; }
			$("#phone").val($("#phone1").val()+"-"+$("#phone2").val()+"-"+$("#phone3").val());
			if(!blankCheck("#email1", "#userForm", "병원이메일은 필수 입력사항입니다.")) { return false; }
			if(!blankCheck("#email2", "#userForm", "병원이메일은 필수 입력사항입니다.")) { return false; }
			$("#email").val($("#email1").val()+"@"+$("#email2").val());
			 */
			if(!blankCheck("#userLevCd", "#userForm", "유저 권한을 선택하십시오.")) { return false; }
			if(!blankCheck("#userStatCd", "#userForm", "유저 상태를 선택하십시오.")) { return false; }
			return true;
		}else{
			return false;
		}
	}
</script>


<form id="userForm" name="userForm" action="/user/selectUserList.doo"method="post">
	<!-- 검색 필드 필수 -->
	<input type="hidden" id="currentPage"   name="currentPage" 	value="${userVo.currentPage}" />
	<input type="hidden" id="rowPerPage" 	name="rowPerPage" 	value="${userVo.rowPerPage}" />
	<input type="hidden" id="sUseYn" 		name="sUseYn" 		value="${userVo.sUseYn}" />
	<input type="hidden" id="sSortOrder" 	name="sSortOrder" 	value="${userVo.sSortOrder}" />
	<input type="hidden" id="sSortType" 	name="sSortType" 	value="${userVo.sSortType}" />
	<input type="hidden" id="searchText" 	name="searchText" 	value="${userVo.searchText}" />
	<input type="hidden" id="searchType" 	name="searchType" 	value="${userVo.searchType}" />
	<!-- //검색 필드 필수 -->
	
	<input type="hidden" id="userPwd" name="userPwd" value="${user.userPwd}"/>
	<input type="hidden" id="userId" name="userId" value="${user.userId}"/>
	<input type="hidden" id="zip" 	  name="zip"     value="${user.zip}"/>	
	<input type="hidden" id="phone"   name="phone" value="${user.phone}"/>	
	<input type="hidden" id="email"   name="email" value="${user.email}"/>	
	
	<c:set var="zip" value="${fn:split(user.zip, '-')}" />
	<c:set var="phone" value="${fn:split(user.phone, '-')}" />
	<c:set var="email" value="${fn:split(user.email, '@')}" />
	<c:set var="zip1" value="${zip[0] }"/>
	<c:set var="zip2" value="${zip[1] }"/>
	<c:set var="phone1" value="${phone[0] }"/>
	<c:set var="phone2" value="${phone[1] }"/>
	<c:set var="phone3" value="${phone[2] }"/>
	<c:set var="email1" value="${email[0] }"/>
	<c:set var="email2" value="${email[1] }"/>
	
	<table class="table5">
		<caption><img src="/resources/img/userReg.gif"></caption>
		<colgroup>
			<col width="30%">
			<col width="*">
		</colgroup>
		<tbody>
			<tr>
				<th class="r">요양기관ID</th>
				<td class="l">${user.userId}</td>
			</tr>
			<tr>
				<th class="r">패스워드</th>
				<td class="l"><input type="password" id="userPwd1"  value="${user.userPwd}" size="20"  maxlength="15"/></td>
			</tr>
			<tr>
				<th class="r">패스워드 확인</th>
				<td class="l"><input type="password" id="userPwd2"  value="${user.userPwd}" size="20"  maxlength="15"/></td>
			</tr>
			<tr>
				<th class="r">요양기관기호</th>
				<c:choose>
					<c:when test="${user.userId eq 'ssssy55'}">
						<td class="l"><input type="text" id="hspId" name="hspId" size="20" maxlength="15" value="${user.hspId}"/></td>
					</c:when>
					<c:otherwise>
						<td class="l">${user.hspId}</td>
					</c:otherwise>
				</c:choose>				
			</tr>
			<tr>
				<th class="r">요양기관명칭</th>
				<td class="l"><input type="text" id="hspNm" name="hspNm" value="${user.hspNm}" size="20"  maxlength="20"/></td>
			</tr>
			<tr>
				<th class="r">병원장명</th>
				<td class="l"><input type="text" id="hspDtNm" name="hspDtNm" value="${user.hspDtNm}" size="20"  maxlength="8"/></td>
			</tr>
			
			<tr>
				<th class="r">의사면허번호</th>
				<td class="l"><input type="text" id="licenNum" name="licenNum" value="11111" size="8"  maxlength="5"/></td>
			</tr>
			<tr>
				<th class="r">병원주소</th>
				<td class="l"><input type="text" id="zip1" value="${zip1}" size="3"  maxlength="3"/>-<input type="text" id="zip2" value="${zip2}" size="3"  maxlength="3"/>&nbsp;&nbsp;<input type="text" id="addr" name="addr" value="${user.addr}" size="20"  maxlength="50"/></td>
			</tr>
			<tr>
				<th class="r">병원연락처</th>
				<td class="l">
					<ct:code name="phone1" type="select" groupCode="00049" selectCode="${phone1 }" />-
					<input type="text" id="phone2" value="${phone2}" size="4"  maxlength="4"/>-<input type="text" id="phone3" value="${phone3}" size="4"  maxlength="4"/></td>
			</tr>
			<tr>
				<th class="r">병원이메일</th>
				<td class="l">
					<input type="text" id="email1" value="${email1}" size="20"  maxlength="20"/>@
					<ct:code name="email2" type="select" groupCode="00050" selectCode="${email2 }" />
				</td>
			</tr>
			<tr>
			<th class="r">사용자 권한</th>
				<td class="l"><ct:code name="userLevCd" type="select" groupCode="00005"  selectCode="${user.userLevCd}"/></td>
			</tr>
			<tr>
			<th class="r">청구서 메시지 레벨</th>
				<td class="l"><ct:code name="msgLev" type="select" groupCode="00068"  selectCode="${user.msgLev}"/></td>
			</tr>
			<tr>
				<th class="r">사용자 상태</th>
				<td class="l"><ct:code name="userStatCd" type="select" groupCode="00002"  selectCode="${user.userStatCd}"/></td>
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
