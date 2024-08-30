<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript">
	$(function() {
		
		//사용자 등록
		$("#createUser").click(function() { 
			$("#userForm").attr("action", "/user/createUserForm.doo");
			$("#userForm").submit();
		});
		
		//검색
		$("#searchList").click(function(){
			$("#userForm").attr("action", "/user/selectUserList.doo");
			$("#currentPage").val(1);
			$("#userForm").submit();
		});
		
		$("#deleteUser").click(function() {
			if(!$(".arrDel").is(":checked")) {
				alert("삭제 체크박스에 한개이상이라도 체크 되어야 삭제 할 수 있습니다.");
				return false;
			}
			
			var checkConfirm = confirm("정말로 삭제하시겠습니까?");
			if(checkConfirm) {
				$("#userForm").attr("action", "/user/deleteUser.json");
				$("#userForm").ajaxSubmit(deleteAjax);
			}
			
		});
		//초기화
		$("#initSearchValue").click(function(){
			initSearchValue();			
		});
		
		$("#delete").hide();
	});
	
	//사용자 상세 function
	readUser = function(userId){
		
		$("#userListTbody tr").each(function(idx){
			
			if(userId == $(this).find("td:eq(0)").text()) $(this).css("background-color","#e5edfe");
			else  $(this).css("background-color","");
			
		}); 
		
		$("#userId","#userForm").val(userId);
		$("#userForm").attr("action", "/user/selectUser.doo");
		$("#userForm").submit();
		
		
		
	}
	
	
	//검색필드 초기화
	initSearchValue = function(){
		$("#searchType").val("");
		$("#searchText").val("");
		$("#sUseYn").val("");
		$("#sSortOrder").val("UPDATE_DTHMS");
		$('input:radio[name=sSortType]:input[value=DESC]').prop("checked", true);
	}
</script>


<form id="userForm" name="userForm" action="/user/selectUserList.doo" method="post">
	<input type="hidden" id="userId" name="userId" value=""/>
	
	<div>
		<table class="table0" >
		<caption><img src="/resources/img/search.gif"></caption>
		<col width="13%" />
		<col width="29%" />
		<col width="13" />
		<col width="30%" />
		<col width="15%" />
		<tbody>
			<tr>
				<th>검색어</th>
				<td>
					<ct:code name="searchType" type="select" groupCode="00015"  selectCode="${userVo.searchType }" defaultCode="USER_NM"/>
					<input type="text" id="searchText" name="searchText" value="${userVo.searchText }"/>
				</td>
				<th>사용여부</th>
				<td>
					<ct:code name="sUseYn" type="select" groupCode="00002"  selectCode="${userVo.sUseYn }"/>
				</td>
				<td class="center" rowspan="2">
					<span class="button medium icon"><span class="check"></span><a id="searchList">조회</a></span>
					<span class="button medium icon"><span class="check"></span><a id="initSearchValue">초기화</a></span> 
				</td>
			</tr>
			<tr>
				<th>정렬</th>
				<td colspan="3">
					<ct:code name="sSortOrder" type="select" groupCode="00011"  selectCode="${userVo.sSortOrder }" />
					<ct:code name="sSortType" type="radio" groupCode="00014"  selectCode="${userVo.sSortType }" />
				</td>
			</tr>
		</tbody>
		</table>
	</div>
	
	
	<table class="table5">
		<caption><img src="/resources/img/userList.gif"><span class="divAr"><ct:code name="rowPerPage" type="select" groupCode="00009"  selectCode="${userVo.rowPerPage}" eventNm="onchange" eventFn="javascript:commonRowPerChange(document.userForm,'/user/selectUserList.doo');"/></span></caption>
		<colgroup>
			<col width="4%">
			<col width="10%">
			<col width="10%">
			<col width="15%">
			<col width="10%">
			<col width="10%">
			<col width="10%">
			<col width="15%">
			<col width="*">
		</colgroup>
		<thead>
			<tr>
				<th><input type="checkBox" id="allDel" name="allDel"/></th>
				<th>유저아이디</th>
				<th>요양기관코드</th>
				<th>병원명</th>
				<th>청구인</th>
				<th>유저레벨</th>
				<th>유저상태</th>
				<th>수정자</th>
				<th>수정일</th>	
			</tr>
		</thead>
		<tbody id="userListTbody">
			<c:if test="${userList != null}">
				<c:forEach var="user" items="${userList}">
					<tr class="trFocus" >
						<td>
							<input type="checkBox" id="arrDel" name="arrDel" class="arrDel" value="${user.userId}" />
							<input type="checkBox" id="historyMsg" name="historyMsg" class="historyMsg" style="display:none;" value="${user.hspId}_${user.hspNm}" />
						</td>
						<td onclick="javascript:readUser('${user.userId}');">${user.userId}</td>
						<td onclick="javascript:readUser('${user.userId}');">${user.hspId}</td>
						<td onclick="javascript:readUser('${user.userId}');">${user.hspNm}</td>
						<td onclick="javascript:readUser('${user.userId}');">${user.hspDtNm }</td>
						<td onclick="javascript:readUser('${user.userId}');"><ct:code name="userLevCd" type="value" groupCode="00005"  selectCode="${user.userLevCd}"/></td>
						<td onclick="javascript:readUser('${user.userId}');"><ct:code name="userStatCd" type="value" groupCode="00002"  selectCode="${user.userStatCd}"/></td>
						<td onclick="javascript:readUser('${user.userId}');">${user.updaterId}</td>
						<td onclick="javascript:readUser('${user.userId}');">${user.updateDthms}</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${fn:length(userList) == 0}">
				<tr>
					<td colspan="9" align="center">등록된 사용자가 없습니다.</td>
				</tr>
			</c:if>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="9">
					<span class="button medium icon"><span class="check"></span><a id="createUser">사용자등록</a></span>
					<span id="delete"><span class="button medium icon"><span class="check"></span><a id="deleteUser">삭제</a></span></span>
				</td>
			</tr>
		</tfoot>
	</table>
	<!-- 페이지번호 -->
	<div class="paging">${userVo.pagingHtml}</div>
</form>	
