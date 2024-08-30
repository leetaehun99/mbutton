<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript">
$(function() {
	//등록폼 초기화
	$("#initValue").click(function() { 
		initValue();
		$("#medicalCd",  "#createMedicalNotifyItemForm").focus();
		btnDisplay(true);
	});
	//검색
	$("#searchList").click(function(){
		$("#currentPage").val("1");
		$("#notifyItemForm").attr("action", "/medicalNotify/selectMedicalNotifyItemList.doo");
		$("#notifyItemForm").submit();
	});
	
	//초기화
	$("#initSearchValue").click(function(){
		initSearchValue();			
	});
	
	//삭제
	$("#deleteNotifyItem").click(function() {
		if(!$(".arrDel").is(":checked")) {
			alert("삭제 체크박스에 한개이상이라도 체크 되어야 삭제 할 수 있습니다.");
			return false;
		}

		//arrDel에 있는 수가코드를 사용하기 때문에 historyMsg가 필요없음
		
		var checkConfirm = confirm("정말로 삭제하시겠습니까?");
		if(checkConfirm) {
			$("#notifyItemForm").attr("action", "/medicalNotify/deleteMedicalNotifyItem.json");
			$("#notifyItemForm").ajaxSubmit(deleteAjax);
		}
	});
	
	$("#createMedicalNotifyItem").click(function(){
		$("#medicalCd","#createMedicalNotifyItemForm").val($.trim($("#medicalCd","#createMedicalNotifyItemForm").val()));
		if(!blankLenCheck("#medicalCd",  "#createMedicalNotifyItemForm", "수가코드는 필수입력 사항입니다.",9)) { return false; }
		if(!blankCheck("#useYn",  "#createMedicalNotifyItemForm", "사용여부 필수선택 사항입니다.")) { return false; }
		$("#createMedicalNotifyItemForm").attr("action", "/medicalNotify/createMedicalNotifyItem.json");
		$("#createMedicalNotifyItemForm").ajaxSubmit(createMedicalNotifyItemAjax);
		btnDisplay(true);
	});
	
	$("#updateMedicalNotifyItem").click(function(){
		if(!blankCheck("#medicalCd",  "#createMedicalNotifyItemForm", "수가코드는 필수입력 사항입니다.",9)) { return false; }
		if(!blankCheck("#useYn",  "#createMedicalNotifyItemForm", "사용여부 필수선택 사항입니다.")) { return false; }
		$("#createMedicalNotifyItemForm").attr("action", "/medicalNotify/updateMedicalNotifyItem.json");
		$("#createMedicalNotifyItemForm").ajaxSubmit(updateMedicalNotifyItemAjax);
		//btnDisplay(true);
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
			if ($(this).prop("checked") == true && $(this).val() == 'Y') {
				// 변경점없음 1
				// 체크가 되어있고 해당값이 Y이면 변경있음
			} else if ($(this).prop("checked") != true && $(this).val() == 'N') { 
				// 체크가 안되어있고 해당값이 N이면 변경없음
				// 변경점없음 2
			} else {
				// 변경점있음
				var useYn = "N";
				
				if ($(this).prop("checked") == true) {
					 useYn = "Y";
					 $(this).val("Y");
					 $("#M" + $(this).attr("id")).html("적용");
				} else {
					 $(this).val("N");
					 $("#M" + $(this).attr("id")).html("미적용");
				}
				
				$("#medicalCd","#updateMedicalNotifyuseYnItem").val($(this).attr("id"));
				$("#useYn","#updateMedicalNotifyuseYnItem").val(useYn);
				$("#updateMedicalNotifyuseYnItem").attr("action", "/medicalNotify/updateMedicalNotifyuseYnItem.json");
				$("#updateMedicalNotifyuseYnItem").ajaxSubmit(updateMedicalNotifyuseYnItem);
				cnt++;
			}
		});
		
		if (cnt > 0) {
			alert("변경되었습니다.");
		}
	});
});


//고시 수가 등록Ajax
var createMedicalNotifyItemAjax = {
	success : function(resultData) {
		if( resultData == 1 ) {
			alert("등록되었습니다.");
			$("#searchList","#notifyItemForm").click();
		} else if( resultData == 99){
			alert("수가 코드가 이미 존재 합니다.");
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

//고시 수가 수정Ajax
var updateMedicalNotifyuseYnItem = {
	success : function(resultData) {
	},
	type : "post",
	dataType : "json",
	error:function(request,status,error){
	    alert("code:"+request.status+"\n"+"\n"+"error:"+error);
	}
};

//고시 수가 수정Ajax
var updateMedicalNotifyItemAjax = {
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

var readNotifyItem = function(medicalCd){
	$("#medicalCd","#notifyItemForm").val(medicalCd);
	
	$("#notifyItemForm").attr("action", "/medicalNotify/selectMedicalNotifyItem.json");
	$("#notifyItemForm").ajaxSubmit(selectMedicalNotifyItemAjax);
	

	$("#notifyItemTbody tr").each(function(){
		if(medicalCd == $.trim($(this).find("td:eq(2)").text()))  $(this).css("background-color","#e5edfe");
		else  $(this).css("background-color","");
	});
	
	btnDisplay(false);
}

msgDel = function(medicalCd , mSeq){
	if(confirm("메세지를 삭제하기겠습니까?")==true){
		$("#medicalCd","#updateMedicalNotifyuseYnItem").val(medicalCd);
		$("#mSeq","#updateMedicalNotifyuseYnItem").val(mSeq);
		$("#updateMedicalNotifyuseYnItem").attr("action", "/medicalNotify/deleteMedicalMsg.json");
		$("#updateMedicalNotifyuseYnItem").ajaxSubmit(deleteMedicalMsgAjax);
	}
}

//고시 약가 수정Ajax
var deleteMedicalMsgAjax = {
	success : function(resultData) {
		if( resultData == 1 ) {
			alert("삭제 되었습니다.");
			readNotifyItem($("#medicalCd","#updateMedicalNotifyuseYnItem").val());
		} else {
			alert("삭제에 실패하였습니다.");
		}
	},
	type : "post",
	dataType : "json",
	error:function(request,status,error){
		    alert("code:"+request.status+"\n"+"\n"+"error:"+error);
		alert("서버와의 통신에 실패 하였습니다.");
	}
};

var selectMedicalNotifyItemAjax = {
	success : function(resultData) {
		if( resultData != null ) {
			
			$("#medicalCd","#createMedicalNotifyItemForm").val(resultData.medicalCd);
			$("#useYn","#createMedicalNotifyItemForm").val(resultData.useYn);
			
			//oEditors1.getById["drugDosage"].exec("PASTE_HTML", [resultData.drugDosage]);
			//oEditors2.getById["drugEfficacy"].exec("PASTE_HTML", [resultData.drugEfficacy]);
		} else {
			$("#medicalCd","#createMedicalNotifyItemForm").val($("#medicalCd","#notifyItemForm").val());
			$("#useYn","#createMedicalNotifyItemForm").val("N");
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
};

//검색필드 초기화
initSearchValue = function(){
	$("#searchType").val("");
	$("#searchText").val("");
};

//입력필드 초기화
initValue = function(){
	$("#medicalCd",  "#createMedicalNotifyItemForm").val('');
};

//적용 미적용 변경
changeId = function(id){
	if ($("#"+id).prop("checked") == true) {
		 $("#M" + id).html("적용");
	} else {
		$("#M" + id).html("미적용");
	}
};
</script>



<form id="notifyItemForm" name="notifyItemForm" action="/medicalNotify/selectMedicalNotifyItemList.doo"method="post">
	<input type="hidden" id="medicalNotify"  name="medicalNotify" value="${medicalNotifyVo.medicalNotify}"/>
	<input type="hidden" id="medicalNotifySub"  name="medicalNotifySub" value="${medicalNotifyVo.medicalNotifySub}"/>
	<input type="hidden" id="medicalCd" name="medicalCd" value="" />
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
						<option value="" <c:if test="${medicalNotifyVo.searchType==''}">selected</c:if>>선택해주십시오</option>
						<option value="MEDICAL_CD" <c:if test="${medicalNotifyVo.searchType=='MEDICAL_CD'}">selected</c:if>>수가코드</option>
					</select>
					<input type="text" id="searchText" name="searchText" value="${medicalNotifyVo.searchText }"/>
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
		<caption><!-- img src="/resources/img/Notify2.gif" --><span class="divAr"><ct:code name="rowPerPage" type="select" groupCode="00009"  selectCode="${medicalNotifyVo.rowPerPage}" eventNm="onchange" eventFn="javascript:commonRowPerChange(document.notifyItemForm,'/medicalNotify/selectMedicalNotifyItemList.doo');"/></span></caption>
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
				<th>수가코드</th>
				<th>수가명</th>
			</tr>
		</thead>
		<tbody id="notifyItemTbody">
			<c:if test="${notifyItemList != null}">
				<c:forEach var="notifyItem" items="${notifyItemList}">
					<tr class="trFocus">
						<td><input type="checkBox" id="arrDel" name="arrDel" class="arrDel" value="${notifyItem.medicalCd }" /></td>
						<td>
							<input type="checkbox" 
							style="width:13px;height:13px;vertical-align:text-top"
							value="${notifyItem.useYn}" id="${notifyItem.medicalCd}" onclick="javascript:changeId('${notifyItem.medicalCd}')" name="checkNotify" <c:if test="${notifyItem.useYn eq 'Y'}" > checked </c:if> />
							<span id="M${notifyItem.medicalCd}" style="vertical-align:-3px">
								<c:if test="${notifyItem.useYn eq 'Y'}" >적용</c:if>
								<c:if test="${notifyItem.useYn ne 'Y'}" >미적용</c:if>
							</span>
						</td>
						<td><span class="button medium icon"><span class="check"></span><a onclick="javascript:readNotifyItem('${notifyItem.medicalCd}');">상세</a></span></td>
						<td> ${notifyItem.medicalCd}</td>
						<td><span class="dot400">${notifyItem.medicalNm}</span></td>
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
	<div class="paging">${medicalNotifyVo.pagingHtml}</div>
</form>
<form id="createMedicalNotifyItemForm" name="createMedicalNotifyItemForm" action="/medicalNotify/createMedicalNotifyItem.json" method="post">
	<input type="hidden" id="medicalNotify" name="medicalNotify" value="${medicalNotifyVo.medicalNotify}" /> 
	<input type="hidden" id="medicalNotifySub" name="medicalNotifySub" value="${medicalNotifyVo.medicalNotifySub}" />

	<table class="table5">
		<caption>
			<img src="/resources/img/reg.gif">
		</caption>
		<colgroup>
			<col width="5%">
			<col width="20%">
			<col width="25%">
			<col width="25%">
			<col width="25%">
		</colgroup>
		<tbody>
			<tr>
				<th colspan="2">수가코드</th>
				<td><input type="text" id="medicalCd" name="medicalCd" value=""
					maxlength="9" /></td>
				<th>적용여부</th>
				<td><ct:code name="useYn" type="select" groupCode="00002" selectCode=""
						defaultCode="Y" /></td>
			</tr>
		</tbody>

		<tfoot>
			<tr>
				<td colspan="7">
				<span id="btnMode1"><span class="button medium icon"><span class="check"></span><a id="createMedicalNotifyItem">등록</a></span></span> 
				<span id="btnMode2"><span class="button medium icon"><span class="check"></span><a id="updateMedicalNotifyItem">수정</a></span></span>
				</td>
			</tr>
		</tfoot>
	</table>
</form>

<form id="updateMedicalNotifyuseYnItem" name="updateMedicalNotifyuseYnItem" action="/medicalNotify/updateMedicalNotifyuseYnItem.json"method="post">
	<input type="hidden" id="medicalNotify"  name="medicalNotify" value="${medicalNotifyVo.medicalNotify}"/>
	<input type="hidden" id="medicalNotifySub"  name="medicalNotifySub" value="${medicalNotifyVo.medicalNotifySub}"/>
	<input type="hidden" id="medicalCd" name="medicalCd" value="" />
	<input type="hidden" id="mSeq" name="mSeq" value="" />
	<input type="hidden" id="useYn" name="useYn" value="N" />
</form>