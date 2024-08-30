<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript">
var check = true;
$(function() {

	$("#createNotify").click(function(){ //치료대 등록
	
		if ($.trim($("#mainDrugCd1").val()) == "") {
			alert("주성분코드1을 입력해주시기 바랍니다.");
			return;
		}
		

		if ($.trim($("#mainDrugCd2").val()) == "") {
			alert("주성분코드2을 입력해주시기 바랍니다.");
			return;
		}
		
		$("#notifyCbItemForm").attr("action", "/notifyCb/selectNotifyCbItemChk.json");
		$("#notifyCbItemForm").ajaxSubmit(selectNotifyCbItemChkAjax);
		
		if (check) {
			$("#notifyCbItemForm").attr("action", "/notifyCb/createNotifyCbItem.json");
			$("#notifyCbItemForm").ajaxSubmit(createNotifyCbItemAjax);	
		}
	});

	$("#selectNotify").click(function(){ //치료대 등록 
		$("#notifyCbItemForm").attr("action", "/notifyCb/selectNotifyCbItemList.doo");
		//$("#notifyCbItemForm").attr("target", "notifyCbItem");
		$("#notifyCbItemForm").submit();
	});
	
});

// Ajax
var createNotifyCbItemAjax = {
	success : function(resultData) {
		if( resultData == 0 ) {
			alert("등록된 주성분코드가 없습니다.");
		} else {
			alert("등록되었습니다.");
			$("#selectNotify","#notifyCbItemForm").click();	
		}
	},
	type : "post",
	dataType : "json",
	error:function(request,status,error){
		alert("이미 등록된 코드 입니다.");
	}
};

createMemberDiv = function(){
	$.blockUI({message:$("#msgContener"),css:{border: 'none',width:'514px;',left:'35%',top:'100px',backgroundColor:'none'}, overlayCSS : {opacity:0.5,border: 'none'}});
};

var closeReplesh = function() {
	$.unblockUI();
};

// 메시지 등록Ajax
var selectNotifyCbItemChkAjax = {
	success : function(resultData) {
		var result = '';
		if(resultData!=null){
			if(resultData.length>0){
				createMemberDiv();
				for(var i=0; i<resultData.length; i++){
					result+="<tr>";
					result+="<td>"+resultData[i].cbNotify+"</td>";
					result+="<td>"+resultData[i].cbNotifySub+"</td>";
					result+="<td>"+resultData[i].mainDrugCd1+"</td>";
					result+="<td>"+resultData[i].mainDrugCd2+"</td>";
					result+="<td>"+resultData[i].msg+"</td>";
					result+="</tr>";
				}
				check = false;
			}else{
				result+="<tr><td colspan='4'>등록된 정보가 없습니다.</td></tr>";
			}
		} else {
			check = true;
		}
		$("#msgResultListTbody").html(result);
	},
	async : false,
	type : "post",
	dataType : "json",
	error : function() {
		alert("서버와의 통신에 실패 하였습니다.");
	}
};
</script>



<form id="notifyCbItemForm" name="notifyCbItemForm" action="/drugNotify/selectDrugNotifyItemList.doo"method="post">
	<input type="hidden" id="cbNotify" name="cbNotify" value="${notifyCbVo.cbNotify}"/>
	<input type="hidden" id="cbNotifySub" name="cbNotifySub" value="${notifyCbVo.cbNotifySub}" />	

	<table class="table5">
		<caption><!-- img src="/resources/img/DrugNotify2.gif" --><span class="divAr"><ct:code name="rowPerPage" type="select" groupCode="00009"  selectCode="${notifyCbVo.rowPerPage}" eventNm="onchange" eventFn="javascript:commonRowPerChange(document.notifyCbItemForm,'/drugNotify/selectDrugNotifyItemList.doo');"/></span></caption>
		<colgroup>
			<col width="100px;">	
			<col width="100px;">
		</colgroup>
		<thead>
			<tr>
				<th>주성분코드1</th>
				<th>주성분코드2</th>
			</tr>
		</thead>
		<tbody id="notifyCbItemTbody">
			<tr>
				<td>
					<textarea style="height:200px;" name="mainDrugCd1" id="mainDrugCd1"><c:forEach var="cb1" items="${cbList1}">${cb1.mainDrugCd1}
</c:forEach></textarea>
				</td>
				<td>
					<textarea style="height:200px;" name="mainDrugCd2" id="mainDrugCd2"><c:forEach var="cb2" items="${cbList2}">${cb2.mainDrugCd2}
</c:forEach></textarea>
				</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="8">
				<span class="button medium icon"><span class="check"></span><a id="createNotify">등록</a></span>
				<span class="button medium icon"><span class="check"></span><a id="selectNotify">목록</a></span>
				</td>
			</tr>
		</tfoot>
	</table>
</form>

<div id="msgContener" style="display:none;">
	<div style="border: 0px; height: 37px;">
		<img src="/resources/img/result_bar_2.png" /><img src="/resources/img/mid_bar.png" /><img src="/resources/img/close_bar.png" onclick="javascript:closeReplesh();" style="cursor:pointer;">
	</div>
	<div style="padding: 20px; background: #f8f8f8; height: 200px; overflow: auto;">
		<table class="table5" style="width: 412px; ">
			<colgroup>
				<col width="7%">
				<col width="9%">
				<col width="10%">
				<col width="10%">
				<col width="*%">
			</colgroup>
			<thead>
				<tr>
					<th>고시번호</th>
					<th>고시서브번호</th>
					<th>주성분코드1</th>
					<th>주성분코드2</th>
					<th>결과</th>
				</tr>
			</thead>
			<tbody id="msgResultListTbody">
				<tr>
					<td colspan="4" align="center">등록된 정보가 없습니다.</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
