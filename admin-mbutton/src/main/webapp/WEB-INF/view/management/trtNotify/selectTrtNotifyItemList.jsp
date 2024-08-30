<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript">
$(function() {
	//등록폼 초기화
	$("#initValue").click(function() { 
		initValue();
		$("#trtCd",  "#createTrtNotifyItemForm").focus();
		btnDisplay(true);
	});
	//검색
	$("#searchList").click(function(){
		$("#currentPage").val("1");
		$("#notifyItemForm").attr("action", "/trtNotify/selectTrtNotifyItemList.doo");
		$("#notifyItemForm").submit();
	});
	
	//초기화
	$("#initSearchValue").click(function(){
		initSearchValue();			
	});
	
	$("#createTrtNotifyItem").click(function(){
		$("#trtCd","#createTrtNotifyItemForm").val($.trim($("#trtCd","#createTrtNotifyItemForm").val()));
		if(!blankLenCheck("#trtCd",  "#createTrtNotifyItemForm", "치료대코드는 필수입력 사항입니다.",9)) { return false; }
		if(!blankCheck("#useYn",  "#createTrtNotifyItemForm", "사용여부 필수선택 사항입니다.")) { return false; }
		$("#createTrtNotifyItemForm").attr("action", "/trtNotify/createTrtNotifyItem.json");
		$("#createTrtNotifyItemForm").ajaxSubmit(createTrtNotifyItemAjax);
		btnDisplay(true);
	});
	
	$("#updateTrtNotifyItem").click(function(){
		if(!blankCheck("#trtCd",  "#createTrtNotifyItemForm", "치료대코드는 필수입력 사항입니다.",9)) { return false; }
		if(!blankCheck("#useYn",  "#createTrtNotifyItemForm", "사용여부 필수선택 사항입니다.")) { return false; }
		$("#createTrtNotifyItemForm").attr("action", "/trtNotify/updateTrtNotifyItem.json");
		$("#createTrtNotifyItemForm").ajaxSubmit(updateTrtNotifyItemAjax);
		//btnDisplay(true);
	});
	
	$("#deleteNotifyItem").click(function() {
		
		if(!$(".arrDel").is(":checked")) {
			alert("삭제 체크박스에 한개이상이라도 체크 되어야 삭제 할 수 있습니다.");
			return false;
		}
		
		var checkConfirm = confirm("정말로 삭제하시겠습니까?");
		if(checkConfirm) {
			$("#notifyItemForm").attr("action", "/trtNotify/deleteTrtNotifyItem.json");
			$("#notifyItemForm").ajaxSubmit(deleteAjax);
		}
		
	});
	
	
	$("#addMsg").click(function(){
		idx=0;
		$("#msgTbody tr").each(function(){
			idx = $(this).attr("id");
		});
		fTag = "<tr id='msg"+(Number(idx.replace('msg',''))+1)+"'>";
		tTag = "</tr>";
		$("#msgTbody").append(fTag+$("#msg0").html()+tTag);
	});
	
	$("#delMsg").click(function(){
		
		$("input[name='msgBox']").each(function(){
			if($(this).is(":checked")) {
				if($(this).parent().parent().attr("id")!="msg0") $(this).parent().parent().remove();
			}
		});
	});

	btnDisplay(true);

	// 체크 박스 모두 체크
	$("#checkAll").click(function() {
		if ($("#checkValue").val() == 'N') {
			$("input[name=checkNotify]:checkbox").each(function() {
				$(this).prop("checked", true);
				$("#M" + $(this).attr("id")).html("적용");
			});
			$("#checkValue").val("Y");
		} else {
			$("input[name=checkNotify]:checkbox").each(function() {
				$(this).prop("checked", false);
				$("#M" + $(this).attr("id")).html("미적용");
			});	
			$("#checkValue").val("N");
		}
	});
	
	// 체크 박스 모두 체크
	$("#updateUseYn").click(function() {
		var cnt = 0;
		$("input[name=checkNotify]:checkbox").each(function() {
			if ($(this).prop("checked") == 'checked' && $(this).val() == 'Y') {
				// 변경점없음 1
				// 체크가 되어있고 해당값이 Y이면 변경있음
			} else if ($(this).prop("checked") != 'checked' && $(this).val() == 'N') {
				// 체크가 안되어있고 해당값이 N이면 변경없음
				// 변경점없음 2
			} else {
				
				var useYn = "N";
				
				if ($(this).prop("checked") == 'checked') {
					 useYn = "Y";
					 $(this).val("Y");
					 $("#M" + $(this).attr("id")).html("적용");
				} else {
					 $(this).val("N");
					 $("#M" + $(this).attr("id")).html("미적용");
				}
				
				$("#trtCd","#updateTrtNotifyuseYnItem").val($(this).attr("id"));
				$("#useYn","#updateTrtNotifyuseYnItem").val(useYn);
				$("#updateTrtNotifyuseYnItem").attr("action", "/trtNotify/updateTrtNotifyuseYnItem.json");
				$("#updateTrtNotifyuseYnItem").ajaxSubmit(updateTrtNotifyuseYnItem);
				cnt++;
			}
			
			//$(this).prop("checked", true);
		});
		
		if (cnt > 0) {
			alert("변경되었습니다.");
		}
	});
});


//치료대 치료대 등록Ajax
var createTrtNotifyItemAjax = {
	success : function(resultData) {
		if( resultData == 1 ) {
			alert("등록되었습니다.");
			$("#searchList","#notifyItemForm").click();
		} else if( resultData == 99){
			alert("치료대 코드가 이미 존재 합니다.");
		} else {
			alert("등록에 실패하였습니다.");
		}
	},
	type : "post",
	dataType : "json",
	error:function(request,status,error){
		    alert("code:"+request.status+"\n"+"\n"+"error:"+error);
		alert("서버와의 통신에 실패 하였습니다.");
	}
};

//치료대 치료대 수정Ajax
var updateTrtNotifyuseYnItem = {
	success : function(resultData) {
	},
	type : "post",
	dataType : "json",
	error:function(request,status,error){
	    alert("code:"+request.status+"\n"+"\n"+"error:"+error);
	}
};

//치료대 치료대 수정Ajax
var updateTrtNotifyItemAjax = {
	success : function(resultData) {
		if( resultData == 1 ) {
			alert("등록되었습니다.");
			//$("#searchList","#notifyItemForm").click();
		} else {
			alert("등록에 실패하였습니다.");
		}
	},
	type : "post",
	dataType : "json",
	error:function(request,status,error){
		    alert("code:"+request.status+"\n"+"\n"+"error:"+error);
		alert("서버와의 통신에 실패 하였습니다.");
	}
};

var readNotifyItem = function(trtCd){
	$("#trtCd","#notifyItemForm").val(trtCd);
	
	$("#notifyItemForm").attr("action", "/trtNotify/selectTrtNotifyItem.json");
	$("#notifyItemForm").ajaxSubmit(selectTrtNotifyItemAjax);
	

	$("#notifyItemTbody tr").each(function(){
		
		if(trtCd == $.trim($(this).find("td:eq(2)").text()))  $(this).css("background-color","#e5edfe");
		else  $(this).css("background-color","");
	});
	
	btnDisplay(false);
}

var selectTrtNotifyItemAjax = {
	success : function(resultData) {
		if( resultData != null ) {
			list = resultData.msgList;
			
			$("#msgTbody tr").each(function(){
				idx = $(this).attr("id");
				if(idx != "msg0") $(this).remove();
				else {
					$("#msg0 > td").eq(1).find("select").val('');
					$("#msg0 > td").eq(2).find("select").val('');
					$("#msg0 > td").eq(3).find("select").val('');
					$("#msg0 > td").eq(4).find("input").val('');
				}
			});
			
			if(list!=null){
				for(i=0; i<list.length; i++){
					if(i!=0){
						$("#addMsg").click();
					}
					$("#msg"+i+" > td").eq(1).find("select").val(list[i].msgLev);
					$("#msg"+i+" > td").eq(2).find("select").val(list[i].specialCd);
					$("#msg"+i+" > td").eq(3).find("select").val(list[i].msgSort);
					$("#msg"+i+" > td").eq(4).find("input").val(list[i].msg);
				}
			}
			$("#trtCd","#createTrtNotifyItemForm").val(resultData.trtCd);
			$("#useYn","#createTrtNotifyItemForm").val(resultData.useYn);
			
			//oEditors1.getById["drugDosage"].exec("PASTE_HTML", [resultData.drugDosage]);
			//oEditors2.getById["drugEfficacy"].exec("PASTE_HTML", [resultData.drugEfficacy]);
		} else {
			$("#trtCd","#createTrtNotifyItemForm").val($("#trtCd","#notifyItemForm").val());
			$("#useYn","#createTrtNotifyItemForm").val("N");
		}
	},
	type : "post",
	dataType : "json",
	error:function(request,status,error){
		    alert("code:"+request.status+"\n"+"\n"+"error:"+error);
		alert("서버와의 통신에 실패 하였습니다.");
	}
};

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

//검색필드 초기화
initSearchValue = function(){
	$("#searchType").val("");
	$("#searchText").val("");
};

//입력필드 초기화
initValue = function(){
	$("#trtCd",  "#createTrtNotifyItemForm").val('');
}

//적용 미적용 변경
changeId = function(id){
	if ($("#"+id).prop("checked") == true) {
		 $("#M" + id).html("적용");
	} else {
		$("#M" + id).html("미적용");
	}
};

</script>



<form id="notifyItemForm" name="notifyItemForm" action="/trtNotify/selectTrtNotifyItemList.doo"method="post">
	<input type="hidden" id="trtNotify"  name="trtNotify" value="${trtNotifyVo.trtNotify}"/>
	<input type="hidden" id="trtNotifySub"  name="trtNotifySub" value="${trtNotifyVo.trtNotifySub}"/>
	<input type="hidden" id="trtCd" name="trtCd" value="" />
	<input type="hidden" id="checkValue" value="N" />
	

	
	<div>
		<table class="table0" >
		<caption><img src="/resources/img/search.gif"></caption>
		<col width="15%" />
		<col width="35%" />
		<col width="*" />
		<tbody>
			<tr>
				<th>구분</th>
				<td>
					<select name="searchType" id="searchType" >
						<option value="" <c:if test="${trtNotifyVo.searchType==''}">selected</c:if>>선택해주십시오</option>
						<option value="TRT_CD" <c:if test="${trtNotifyVo.searchType=='TRT_CD'}">selected</c:if>>치료대코드</option>
					</select>
					<input type="text" id="searchText" name="searchText" value="${trtNotifyVo.searchText }"/>
				</td>
				<td class="center">
					<span class="button medium icon"><span class="check"></span><a id="searchList">조회</a></span>
					<span class="button medium icon"><span class="check"></span><a id="initSearchValue">초기화</a></span> 
				</td>
			</tr>
		</tbody>
		</table>
	</div>
	
	
	<table class="table5">
		<caption><!-- img src="/resources/img/Notify2.gif" --><span class="divAr"><ct:code name="rowPerPage" type="select" groupCode="00009"  selectCode="${trtNotifyVo.rowPerPage}" eventNm="onchange" eventFn="javascript:commonRowPerChange(document.notifyItemForm,'/trtNotify/selectTrtNotifyItemList.doo');"/></span></caption>
		<colgroup>
			<col width="40px;">
			<col width="90px;">
			<col width="70px;">
			<col width="100px;">
			<col width="*">
		</colgroup>
		<thead>
			<tr>
				<th><input type="checkBox" id="allDel" name="allDel"/></th>
				<th>적용여부<input type="checkbox" id="checkAll" style="vertical-align:-4px" /></th>	
				<th>상세</th>	
				<th>치료대코드</th>
				<th>치료대명</th>
			</tr>
		</thead>
		<tbody id="notifyItemTbody">
			<c:if test="${notifyItemList != null}">
				<c:forEach var="notifyItem" items="${notifyItemList}">
					<tr class="trFocus">
						<td>
							<input type="checkBox" id="arrDel" name="arrDel" class="arrDel" value="${notifyItem.trtCd}" />
							<input type="checkBox" id="historyMsg" name="historyMsg" class="historyMsg" style="display:none;" value="${notifyItem.trtNm}" />
						</td>
						<td>
							<input type="checkbox" 
							style="width:13px;height:13px;vertical-align:text-top" 
							value="${notifyItem.useYn}" id="${notifyItem.trtCd}" onclick="javascript:changeId('${notifyItem.trtCd}')" name="checkNotify" <c:if test="${notifyItem.useYn eq 'Y'}" > checked </c:if> />
							<span id="M${notifyItem.trtCd}" style="vertical-align:-3px">
								<c:if test="${notifyItem.useYn eq 'Y'}" >적용</c:if>
								<c:if test="${notifyItem.useYn ne 'Y'}" >미적용</c:if>
							</span>
						</td>
						<td><span class="button medium icon"><span class="check"></span><a onclick="javascript:readNotifyItem('${notifyItem.trtCd}');">상세</a></span></td>
						<td> ${notifyItem.trtCd}</td>
						<td><span class="dot400">${notifyItem.trtNm}</span></td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${fn:length(notifyItemList) == 0}">
				<tr>
					<td colspan="5" align="center">등록된 게시물이 없습니다.</td>
				</tr>
			</c:if>
		</tbody>
		<tfoot>
				<tr><td colspan="5">
					<span class="button medium icon"><span class="check"></span><a id="updateUseYn">적용</a></span>
					<span class="button medium icon"><span class="check"></span><a id="initValue">신규</a></span>
					<span id="delete"><span class="button medium icon"><span class="check"></span><a id="deleteNotifyItem">삭제</a></span></span>
				</td></tr>
		</tfoot>
	</table>
	<!-- 페이지번호 -->
	<div class="paging">${trtNotifyVo.pagingHtml}</div>
</form>
<form id="createTrtNotifyItemForm" name="createTrtNotifyItemForm" action="/trtNotify/createTrtNotifyItem.json"method="post">
<input type="hidden" id="trtNotify" name="trtNotify" value="${trtNotifyVo.trtNotify}"/>
<input type="hidden" id="trtNotifySub" name="trtNotifySub" value="${trtNotifyVo.trtNotifySub}" />	

	<table class="table5">
		<caption><img src="/resources/img/reg.gif"></caption>
		<colgroup>
			<col width="5%">
			<col width="20%">
			<col width="25%">
			<col width="25%">
			<col width="25%">
		</colgroup>
		<tbody>
			<tr>
				<th colspan="2">치료대코드</th>	
				<td><input type="text" id="trtCd" name="trtCd" value="" maxlength="9"/></td>
				<th>적용여부</th>	
				<td><ct:code name="useYn" type="select" groupCode="00002"  defaultCode="Y" selectCode=""/></td>
			</tr>
			<tr>
				<td colspan="5" >
					<table class="table5">
						<thead>
							<tr>
								<th>/</th>
								<th>메세지Lev.</th>
								<th>특정내역</th>
								<th>순서</th>
								<th>메세지</th>
							</tr>
						</thead>
						<tbody id="msgTbody">
						<tr id="msg0">
							<td><input type="checkbox" name="msgBox" value=""/></td>
							<td>
								<select  name="msgLevArray" >
									<option value="">선택해주세용</option>
									<option value="1">필수</option>
									<option value="2">선택</option>									
								</select>
							</td>
							<td><ct:code name="msgSpecialCd" type="select" groupCode="00046" displayType="onlyCD" selectCode=""/></td>
							<td>
								<select  name="msgSortArray" >
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>									
								</select>
							</td>
							<td><input type="text"  name="msgArray" value="" readonly="readonly" size="70" maxlength="200"/></td>
						</tr>
						</tbody>
						<!-- 
						<tfoot class="l">
						<tr>
							<td colspan="5" style="text-align:left;">
							<span class="button medium icon"><span class="check"></span><a id="addMsg">추가</a></span>
							<span class="button medium icon"><span class="check"></span><a id="delMsg">삭제</a></span>
							</td>
						</tr>
						</tfoot> -->
					</table>
				</td>
			</tr>
		</tbody>
		<!-- 
		<tfoot>
			<tr>
				<td colspan="7" >
					<span id="btnMode1" ><span class="button medium icon"><span class="check"></span><a id="createTrtNotifyItem">등록</a></span></span>
					<span id="btnMode2" ><span class="button medium icon"><span class="check"></span><a id="updateTrtNotifyItem">수정</a></span></span>
				</td>
			</tr>
		</tfoot> -->
	</table>
</form>
<form id="updateTrtNotifyuseYnItem" name="updateTrtNotifyuseYnItem" action="/trtNotify/updateTrtNotifyuseYnItem.json"method="post">
	<input type="hidden" id="trtNotify"  name="trtNotify" value="${trtNotifyVo.trtNotify}"/>
	<input type="hidden" id="trtNotifySub"  name="trtNotifySub" value="${trtNotifyVo.trtNotifySub}"/>
	<input type="hidden" id="trtCd" name="trtCd" value="" />
	<input type="hidden" id="useYn" name="useYn" value="N" />
</form>