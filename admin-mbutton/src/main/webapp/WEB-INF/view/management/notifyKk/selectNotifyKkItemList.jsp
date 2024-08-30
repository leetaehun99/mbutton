<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript">
$(function() {
	//검색
	$("#searchList").click(function(){
		$("#currentPage").val("1");
		$("#notifyKkItemForm").attr("action", "/notifyKk/selectNotifyKkItemList.doo");
		$("#notifyKkItemForm").submit();
	});
	
	//초기화
	$("#initSearchValue").click(function(){
		initSearchValue();			
	});
	
	//등록폼 초기화
	$("#initValue").click(function() { 
		initValue();
		btnDisplay(true);
	});
	
	//메시지 등록
	$("#createNotifyKk").click(function() { 
		if(validationCheck()){
			$("#notifyKkItemForm").attr("action", "/notifyKk/insertNotifyKk.json");
			$("#notifyKkItemForm").ajaxSubmit(createNotifyKkAjax);
		}
	});
	
	// 목록으로 이동
	$("#selectNotifyKkList").click(function() { 
		$("#mode","#notifyKkItemForm").val("LIST");
		$("#notifyKkItemForm").attr("action", "/notifyExt/selectNotifyExtList.doo");
		$("#notifyKkItemForm").submit();
	});
	
	//메시지 수정
	$("#updateNotifyKk").click(function() { 
		if(validationCheck()){
			$("#notifyKkItemForm").attr("action", "/notifyKk/updateNotifyKk.json");
			$("#notifyKkItemForm").ajaxSubmit(createNotifyKkAjax);
		}
	});

	//삭제
	$("#deleteNotifyKk").click(function() {
		if(!$(".arrDel").is(":checked")) {
			alert("삭제 체크박스에 한개이상이라도 체크 되어야 삭제 할 수 있습니다.");
			return false;
		}
		
		//arrDel에 있는 주성분코드를 사용하기 때문에 historyMsg가 필요없음
		
		var deleteCheck = confirm("정말 삭제하시겠습니까?");
		if(deleteCheck) {
			$("#notifyKkItemForm").attr("action", "/notifyKk/deleteNotifyKk.json");
			$("#notifyKkItemForm").ajaxSubmit(deleteAjax);
		}
	});
	
	//초기화
	$("#initSearchValue").click(function(){
		initSearchValue();			
	});
	
	$("#delete").hide();
	
});

// 메시지 등록Ajax
var createNotifyKkAjax = {
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

//입력필드 초기화
initValue = function(){
	$("#mainDrugCd").val("");
	$("#div1").val("");
	$("#div2").val("");
	$("#etc1").val("");
	$("#etc2").val("");
	$("#etc3").val("");
	$("#etc4").val("");
	$("#etc5").val("");
	$("#etc6").val("");
	$("#etc7").val("");
	$("#etc8").val("");
	$("#etc9").val("");
	$("#etc10").val("");
	$("#etc11").val("");
	$("#etc12").val("");
	$("#etc13").val("");
	$("#etc14").val("");
	$("#etc15").val("");
	$("#etc16").val("");
	$("#etc17").val("");
}

//검색필드 초기화
initSearchValue = function(){
	$("#searchType").val("");
	$("#searchText").val("");
	$("#sortSeq").val("");
}

//메시지 상세 function
var readNotifyKk = function(text,div1,div2,etc1,etc2,etc3,etc4,etc5,etc6,etc7,etc8,etc9,etc10,etc11,etc12,etc13,etc14,etc15,etc16,etc17){
	$("#mainDrugCd").val(text);
	$("#div1").val(div1);
	$("#div2").val(div2);
	$("#etc1").val(etc1);
	$("#etc2").val(etc2);
	$("#etc3").val(etc3);
	$("#etc4").val(etc4);
	$("#etc5").val(etc5);
	$("#etc6").val(etc6);
	$("#etc7").val(etc7);
	$("#etc8").val(etc8);
	$("#etc9").val(etc9);
	$("#etc10").val(etc10);
	$("#etc11").val(etc11);
	$("#etc12").val(etc12);
	$("#etc13").val(etc13);
	$("#etc14").val(etc14);
	$("#etc15").val(etc15);
	$("#etc16").val(etc16);
	$("#etc17").val(etc17);
	btnDisplay(false);
};


//value검증
validationCheck = function(){
	if(!blankCheck("#mainDrugCd", "#notifyKkItemForm", "주성분코드는 필수입력 사항입니다.")) { return false; }
	if(!blankCheck("#div1", "#notifyKkItemForm", "원내는 필수입력 사항입니다.")) { return false; }
	if(!blankCheck("#div2", "#notifyKkItemForm", "원외는 필수입력 사항입니다.")) { return false; }
	if(!blankCheck("#etc1", "#notifyKkItemForm", "KK010는 필수입력 사항입니다.")) { return false; }
	if(!blankCheck("#etc2", "#notifyKkItemForm", "KK020는 필수입력 사항입니다.")) { return false; }
	if(!blankCheck("#etc3", "#notifyKkItemForm", "KK054는 필수입력 사항입니다.")) { return false; }
	if(!blankCheck("#etc4", "#notifyKkItemForm", "MIX(KK051,KK052,KK53)는 필수입력 사항입니다.")) { return false; }
	if(!blankCheck("#etc5", "#notifyKkItemForm", "KK090(관절강내)는 필수입력 사항입니다.")) { return false; }
	if(!blankCheck("#etc6", "#notifyKkItemForm", "KK062(건초내)는 필수입력 사항입니다.")) { return false; }
	if(!blankCheck("#etc7", "#notifyKkItemForm", "KK061(신경간내)는 필수입력 사항입니다.")) { return false; }
	if(!blankCheck("#etc8", "#notifyKkItemForm", "KK058는 필수입력 사항입니다.")) { return false; }
	if(!blankCheck("#etc9", "#notifyKkItemForm", "KK051(100ML미만)는 필수입력 사항입니다.")) { return false; }
	if(!blankCheck("#etc10", "#notifyKkItemForm", "KK052는 필수입력 사항입니다.")) { return false; }
	if(!blankCheck("#etc11", "#notifyKkItemForm", "KK053는 필수입력 사항입니다.")) { return false; }
	if(!blankCheck("#etc12", "#notifyKkItemForm", "KK032는 필수입력 사항입니다.")) { return false; }
	if(!blankCheck("#etc13", "#notifyKkItemForm", "KK033는 필수입력 사항입니다.")) { return false; }
	if(!blankCheck("#etc14", "#notifyKkItemForm", "기타는 필수입력 사항입니다.")) { return false; }
	if(!blankCheck("#etc15", "#notifyKkItemForm", "KK155는 필수입력 사항입니다.")) { return false; }
	if(!blankCheck("#etc16", "#notifyKkItemForm", "KK156는 필수입력 사항입니다.")) { return false; }
	if(!blankCheck("#etc17", "#notifyKkItemForm", "KK151는 필수입력 사항입니다.")) { return false; }
	return true;
}
</script>

<form id="notifyKkItemForm" name="notifyKkItemForm" action="/drugNotify/selectDrugNotifyItemList.doo"method="post">
	<input type="hidden" id="extNotify" name="extNotify" value="${notifyKkVo.extNotify}"/>
	<input type="hidden" id="extNotifySub" name="extNotifySub" value="${notifyKkVo.extNotifySub}" />	
	
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
					<ct:code name="searchType" type="select" groupCode="00079"  selectCode="${notifyKkVo.searchType }" defaultCode="MAIN_DRUG_CD"/>
					<input type="text" id="searchText" name="searchText" value="${notifyKkVo.searchText }"/>
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
		<caption><!-- img src="/resources/img/DrugNotify2.gif" --><span class="divAr"><ct:code name="rowPerPage" type="select" groupCode="00009"  selectCode="${notifyKkVo.rowPerPage}" eventNm="onchange" eventFn="javascript:commonRowPerChange(document.notifyKkItemForm,'/drugNotify/selectDrugNotifyItemList.doo');"/></span></caption>
		<thead>
			<tr>
				<th><input type="checkBox" id="allDel" name = "allDel" /></th>
				<th>주성분코드</th>
				<th>원내</th>
				<th>원외</th>
				<th>KK010</th>
				<th>KK020</th>
				<th>KK054</th>
				<th>MIX(KK051<br>,KK052,KK53)</th>
				<th>KK090<br>(관절강내)</th>
				<th>KK062<br>(건초내)</th>
				<th>KK061<br>(신경간내)</th>
				<th>KK058</th>
				<th>KK051<br>(100ML<br>미만)</th>
				<th>KK052</th>
				<th>KK053</th>
				<th>KK032</th>
				<th>KK033</th>
				<th>기타</th>
				<th>KK155</th>
				<th>KK156</th>
				<th>KK151</th>
				<th>상세</th>
			</tr>
		</thead>
		<tbody id="notifyKkItemTbody">
			<c:if test="${kkList != null}">
				<c:forEach var="kk" items="${kkList}">
					<tr class="trFocus">
						<td>
							<input type="checkBox" id="arrDel" name="arrDel" class="arrDel" value="${kk.mainDrugCd }"/>
						</td>
						<td>${kk.mainDrugCd}</td>
						<td>${kk.div1}</td>
						<td>${kk.div2}</td>
						<td>${kk.etc1}</td>
						<td>${kk.etc2}</td>
						<td>${kk.etc3}</td>
						<td>${kk.etc4}</td>
						<td>${kk.etc5}</td>
						<td>${kk.etc6}</td>
						<td>${kk.etc7}</td>
						<td>${kk.etc8}</td>
						<td>${kk.etc9}</td>
						<td>${kk.etc10}</td>
						<td>${kk.etc11}</td>
						<td>${kk.etc12}</td>
						<td>${kk.etc13}</td>
						<td>${kk.etc14}</td>
						<td>${kk.etc15}</td>
						<td>${kk.etc16}</td>
						<td>${kk.etc17}</td>
						<td>
							<span class="button medium icon"><span class="check"></span>
							<a onclick=
							"javascript:readNotifyKk('${kk.mainDrugCd}','${kk.div1}','${kk.div2}','${kk.etc1}','${kk.etc2}','${kk.etc3}','${kk.etc4}','${kk.etc5}','${kk.etc6}','${kk.etc7}','${kk.etc8}','${kk.etc9}','${kk.etc10}','${kk.etc11}','${kk.etc12}','${kk.etc13}','${kk.etc14}','${kk.etc15}','${kk.etc16}','${kk.etc17}');">상세</a></span>
						</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${fn:length(kkList) == 0}">
				<tr>
					<td colspan="22" align="center">등록된 게시물이 없습니다.</td>
				</tr>
			</c:if>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="22">
					<span class="button medium icon"><span class="check"></span><a id="initValue">신규</a></span>
					<span id="delete"><span class="button medium icon"><span class="check"></span><a id="deleteNotifyKk">삭제</a></span></span>
				</td>
			</tr>
		</tfoot>
	</table>
	<!-- 페이지번호 -->
	<div class="paging">${notifyKkVo.pagingHtml}</div>
	<table class="table5" style="width: 1000px;">
		<thead>
			<tr>
				<th>주성분코드</th>
				<th>원내</th>
				<th>원외</th>
				<th>KK010</th>
				<th>KK020</th>
				<th>KK054</th>
				<th>MIX<br/>(KK051<br/>,KK052<br/>,KK53)</th>
				<th>KK090<br/>(관절강내)</th>
				<th>KK062<br/>(건초내)</th>
				<th>KK061<br/>(신경간내)</th>
				<th>KK058</th>
				<th>KK051<br/>(100ML미만)</th>
				<th>KK052</th>
				<th>KK053</th>
				<th>KK032</th>
				<th>KK033</th>
				<th>기타</th>
				<th>KK155</th>
				<th>KK156</th>
				<th>KK151</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><input type="text" id="mainDrugCd" name="mainDrugCd" value="" size="7" /></td>
				<td><input type="text" id="div1" name="div1" value="" size="1" /></td>
				<td><input type="text" id="div2" name="div2" value="" size="1" /></td>
				<td><select id="etc1" name="etc1"><option>N</option><option>Y</select></td>
				<td><select id="etc2" name="etc2"><option>N</option><option>Y</select></td>
				<td><select id="etc3" name="etc3"><option>N</option><option>Y</select></td>
				<td><select id="etc4" name="etc4"><option>N</option><option>Y</select></td>
				<td><select id="etc5" name="etc5"><option>N</option><option>Y</select></td>
				<td><select id="etc6" name="etc6"><option>N</option><option>Y</select></td>
				<td><select id="etc7" name="etc7"><option>N</option><option>Y</select></td>
				<td><select id="etc8" name="etc8"><option>N</option><option>Y</select></td>
				<td><select id="etc9" name="etc9"><option>N</option><option>Y</select></td>
				<td><select id="etc10" name="etc10"><option>N</option><option>Y</select></td>
				<td><select id="etc11" name="etc11"><option>N</option><option>Y</select></td>
				<td><select id="etc12" name="etc12"><option>N</option><option>Y</select></td>
				<td><select id="etc13" name="etc13"><option>N</option><option>Y</select></td>
				<td><select id="etc14" name="etc14"><option>N</option><option>Y</select></td>
				<td><select id="etc15" name="etc15"><option>N</option><option>Y</select></td>
				<td><select id="etc16" name="etc16"><option>N</option><option>Y</select></td>
				<td><select id="etc17" name="etc17"><option>N</option><option>Y</select></td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="20">
					<span id="btnMode1"><span class="button medium icon"><span class="check"></span><a id="createNotifyKk">등록</a></span></span>
					<span id="btnMode2"><span class="button medium icon"><span class="check"></span><a id="updateNotifyKk">수정</a></span></span>
					<span class="button medium icon"><span class="check"></span><a id="selectNotifyKkList">목록</a></span>
				</td>
			</tr>
		</tfoot>
	</table>
</form>