<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript">
	$(function() {		
		//등록폼 초기화
		$("#initValue").click(function() { 
			initValue();
			$("#menuNm").focus();
			btnDisplay(true);
		});
		
		//메뉴 등록
		$("#createMenu").click(function() { 
			if(validationCheck()){
				$("#grpId").val($("#grpSelectId").val());
				$("#menuForm").attr("action", "/menu/createMenu.json");
				$("#menuForm").ajaxSubmit(createMenuAjax);
			}
		});
		//메뉴 수정
		$("#updateMenu").click(function() { 
			if(validationCheck()){
				$("#grpId").val($("#grpSelectId").val());
				$("#menuForm").attr("action", "/menu/updateMenu.json");
				$("#menuForm").ajaxSubmit(createMenuAjax);
			}
		});
		
		//검색
		$("#searchList").click(function(){
			$("#menuForm").attr("action", "/menu/selectMenuList.doo");
			$("#currentPage").val(1);
			$("#menuForm").submit();
		});
		
		//초기화
		$("#initSearchValue").click(function(){
			initSearchValue();			
		});
		
		/*
		$("#menuLev").change(function(){
			if($(this).val()=="2"){
				$("#grpId").removeAttr("readonly");
			}else{
				$("#grpId").val("00000000");
				$("#grpId").attr("readonly","true");
			}
		});
		*/
		btnDisplay(true);
	});
	
	//메뉴 상세 function
	var readMenu = function(menuId){
		$("#menuId").val(menuId);
		$("#menuForm").attr("action", "/menu/selectMenu.json");
		$("#menuForm").ajaxSubmit(selectMenuAjax);
		btnDisplay(false);
		
		$("#menuListTbody tr").each(function(){
			if(menuId == $(this).find("td:eq(0)").text()) $(this).css("background-color","#e5edfe");
			else  $(this).css("background-color","");
		});
	};

	var readSubMenu = function(grpId){
		$("#grpId").val(grpId);
		$("#menuForm").attr("action", "/menu/selectSubMenuList.doo");
		$("#currentPage").val(1);
		$("#menuForm").submit();
	};
	
	// 메뉴 등록Ajax
	var selectParentMenuAjax = {
		success : function(resultData) {
			var result="<option value=''>신규</option>";
			for(var i=0; i<resultData.length; i++){
				result += "<option value='"+resultData[i].grpId+"' >";
				result += resultData[i].menuNm + "</option>";
			}
			$("#grpSelectId").html(result);
		},
		type : "post",
		dataType : "json",
		error : function() {
			alert("서버와의 통신에 실패 하였습니다.");
		}
	};

	// 메뉴 등록Ajax
	var createMenuAjax = {
		success : function(resultData) {
			if( resultData == 1 ) {
				alert("등록되었습니다.");
				$("#searchList").click();
			} else {
				alert("등록에 실패하였습니다.");
			}
		},
		type : "post",
		dataType : "json",
		error : function() {
			alert("서버와의 통신에 실패 하였습니다.");
		}
	};
	
	// 메뉴 상세Ajax 
	var selectMenuAjax = {
		success : function(resultData) {
			if( resultData != null ) {
				valueSetting(resultData);
			} else {
				alert("조회된 내용이 없습니다.");
			}
		},
		type : "post",
		dataType : "json",
		error : function() {
			alert("서버와의 통신에 실패 하였습니다.");
		}
	};
	
	//값 세팅
	valueSetting = function(data){
		$("#menuId").val(data.menuId);
		$("#menuNm").val(data.menuNm);
		$("#linkUrl").val(data.linkUrl);
		$("#userLevCd").val(data.userLevCd);
		$("#imgPath").val(data.imgPath);
		$("#menuLev").val(data.menuLev);
		$("#menuYn").val(data.menuYn);
		$("#sortSeq").val(data.sortSeq);
		$("#loginNeedYn").val(data.loginNeedYn);
		$("#grpSelectId").val(data.grpId);		
		$("#useYn").val(data.useYn);
		$("#siteType").val(data.siteType); 
	}
	
	//버튼 표현
	btnDisplay = function(type){
		if(type){//등록
			$("#btnMode2").hide();
			$("#btnMode1").show();
		}else{//수정
			$("#btnMode1").hide();
			$("#btnMode2").show();
		}
	}
	
	//value검증
	validationCheck = function(){
		if(!blankCheck("#menuNm",  "#menuForm", "메뉴명은 필수입력 사항입니다.")) { return false; }
		if(!blankCheck("#linkUrl", "#menuForm", "메뉴URL는 필수입력 사항입니다.")) { return false; }
		if(!blankCheck("#menuLev", "#menuForm", "메뉴레벨은 필수입력 사항입니다.")) { return false; }
		if(!onlyNumber("#menuLev", "#menuForm", "메뉴레벨은 숫자만 입력가능합니다.")) { return false; }
		if(!blankCheck("#sortSeq", "#menuForm", "정렬순서는 필수입력 사항입니다.")) { return false; }
		if(!onlyNumber("#sortSeq", "#menuForm", "정렬순서는 숫자만 입력가능합니다.")) { return false; }
		if(!blankCheck("#loginNeedYn", "#menuForm", "로그인여부는 필수입력 사항입니다.")) { return false; }
		if(!blankCheck("#useYn", "#menuForm", "사용여부는 필수입력 사항입니다.")) { return false; }
		return true;
	}
	
	//입력필드 초기화
	initValue = function(){
		$("#menuNm").val("");
		$("#linkUrl").val("");
		$("#imgPath").val("");
		$("#menuLev").val("");
		$("#sortSeq").val("");
		$("#loginNeedYn").val("");
		$("#useYn").val(""); 
		$("#siteType").val(""); 
	}
	
	//검색필드 초기화
	initSearchValue = function(){
		$("#searchType").val("");
		$("#searchText").val("");
		$("#sLoginNeedYn").val("");
		$("#sUseYn").val("");
		$("#sortSeq").val("");
		$("#sSortOrder").val("UPDATE_DTHMS");
		$('input:radio[name=sSortType]:input[value=DESC]').prop("checked", true);
	}
</script>


<form id="menuForm" name="menuForm" action="/menu/selectMenuList.doo"method="post">
	<input type="hidden" id="menuId" name="menuId" value=""/>
	<input type="hidden" id="grpId" name="grpId" value=""/>
	
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
					<ct:code name="searchType" type="select" groupCode="00010"  selectCode="${menuVo.searchType }" defaultCode="MENU_NM"/>
					<input type="text" id="searchText" name="searchText" value="${menuVo.searchText }"/>
				</td>
				<th>로그인여부</th>
				<td>
					<ct:code name="sLoginNeedYn" type="select" groupCode="00002"  selectCode="${menuVo.sLoginNeedYn }"/>
				</td>
				<td class="center" rowspan="2">
					<span class="button medium icon"><span class="check"></span><a id="searchList">조회</a></span>
					<span class="button medium icon"><span class="check"></span><a id="initSearchValue">초기화</a></span> 
				</td>
			</tr>
			<tr>
				<th>사용여부</th>
				<td>
					<ct:code name="sUseYn" type="select" groupCode="00002"  selectCode="${menuVo.sUseYn }"/>
				</td>
				<th>정렬</th>
				<td>
					<ct:code name="sSortOrder" type="select" groupCode="00011"  selectCode="${menuVo.sSortOrder }" />
					<ct:code name="sSortType" type="radio" groupCode="00014"  selectCode="${menuVo.sSortType }" />
				</td>
			</tr>
		</tbody>
		</table>
	</div>
	<table class="table5">
		<caption><img src="/resources/img/menuList.gif"><span class="divAr"><ct:code name="rowPerPage" type="select" groupCode="00009"  selectCode="${menuVo.rowPerPage}" eventNm="onchange" eventFn="javascript:commonRowPerChange(document.menuForm,'/menu/selectMenuList.doo');"/></span></caption>
		<colgroup>
			<col width="11%">
			<col width="*">
			<col width="7%">
			<col width="7%">
			<col width="7%">
			<col width="8%">
			<col width="14%">
			<col width="20%">
		</colgroup>
		<thead>
			<tr>
				<th>메뉴명</th>
				<th>메뉴URL</th>
				<th>정렬순서</th>
				<th>사용여부</th>
				<th>로그인여부</th>
				<th>수정자</th>
				<th>수정일</th>	
				<th>상세</th>	
			</tr>
		</thead>
		<tbody id="menuListTbody">
			<c:if test="${menuList != null}">
				<c:forEach var="menu" items="${menuList}">
					<tr class="trFocus">
						<td style="text-align: left; padding-left: 10px;">${menu.menuNm}</td>
						<td style="text-align: left; padding-left: 10px;">${menu.linkUrl}</td>
						<td>${menu.sortSeq}</td>
						<td>${menu.useYn}</td>
						<td>${menu.loginNeedYn }</td>
						<td>${menu.updaterId}</td>
						<td>${menu.updateDthms}</td>
						<td>
							<span class="button medium icon"><span class="check"></span><a onclick="javascript:readMenu('${menu.menuId}');">메뉴상세</a></span>
							<span class="button medium icon"><span class="check"></span><a onclick="javascript:readSubMenu('${menu.menuId}');">URL상세</a></span>
						</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${fn:length(menuList) == 0}">
				<tr>
					<td colspan="8" align="center">등록된 메뉴가 없습니다.</td>
				</tr>
			</c:if>
		</tbody>
		<tfoot>
			<tr><td colspan="8"><span class="button medium icon"><span class="check"></span><a id="initValue">신규</a></span></td></tr>
		</tfoot>
	</table>
	<!-- 페이지번호 -->
	<div class="paging">${menuVo.pagingHtml}</div>
	
	<table class="table5">
		<caption><img src="/resources/img/reg.gif"></caption>
		<colgroup>
			<col width="7%">
			<col width="*">
			<col width="15%">
			<col width="10%">
			<col width="6%">
			<col width="10%">
			<col width="10%">
			<col width="10%">
			<col width="10%">
		</colgroup>
		<thead>
			<tr>
				<th>메뉴명</th>
				<th>메뉴URL</th>
				<th>접근권한</th>
				<th>메뉴레벨</th>
				<th>정렬순서</th>
				<th>메뉴YN</th>
				<th>로그인여부</th>	
				<th>사용여부</th>			
				<th>사이트</th>		
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><input type="text" id="menuNm"  	name="menuNm" 		value="" size="10" maxlength="20"/></td>
				<td><input type="text" id="linkUrl" 	name="linkUrl" 		value="" size="40" maxlength="60"/></td>
				<td><ct:code name="userLevCd" type="select" groupCode="00005" nonSelect="Y" selectCode=""/></td>
				<td><ct:code name="menuLev" type="select" groupCode="00052" nonSelect="Y" selectCode=""/></td>
				<td><input type="text" id="sortSeq" name="sortSeq" value="" size="4" maxlength="2"/></td>
				<td><ct:code name="menuYn" type="select" groupCode="00053" nonSelect="Y" selectCode=""/></td>
				<td><ct:code name="loginNeedYn" type="select" groupCode="00008" nonSelect="Y" selectCode=""/></td>
				<td><ct:code name="useYn" type="select" groupCode="00002" nonSelect="Y" selectCode=""/></td>
				<td><ct:code name="siteType" type="select" groupCode="00069" nonSelect="Y" selectCode=""/></td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="9">
					<span id="btnMode1"><span class="button medium icon"><span class="check"></span><a id="createMenu">등록</a></span></span>
					<span id="btnMode2"><span class="button medium icon"><span class="check"></span><a id="updateMenu">수정</a></span></span>
				</td>
			</tr>
		</tfoot>
	</table>
</form>