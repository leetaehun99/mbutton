<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript">
	$(function() {
		//메시지 등록
		$("#createMsgItem").click(function() { 
			createMemberDiv();
			$("#msgForm").attr("action", "/msg/createMsgItem.json");
			$("#msgForm").ajaxSubmit(createMsgItemAjax);
		});
				
		//검색
		$("#searchList").click(function(){
			$("#msgForm").attr("action", "/msg/selectSubMsgList.doo");
			$("#msgForm").submit();
		});
		
		$("#selectMsgList").click(function(){
			$("#mode","#msgListForm").val("LIST");
			$("#msgListForm").attr("action", "/msg/selectMsgList.doo");
			$("#msgListForm").submit();
		});
		
		$("#deleteMsgItem").click(function() {
			if(!$(".arrDel").is(":checked")) {
				alert("삭제 체크박스에 한개이상이라도 체크 되어야 삭제 할 수 있습니다.");
				return false;
			}

			var checkConfirm = confirm("정말로 삭제하시겠습니까?");
			if(checkConfirm) {
				$("#msgForm").attr("action", "/msg/deleteMsgItem.json");
				$("#msgForm").ajaxSubmit(deleteAjax1);
			}
		});
	});
	
	/* var deleteMsgItem = function(notify,notifySub,cd){
		if (confirm("정말 삭제하시겠습니까?")) {
			$("#notify","#msgForm").val(notify);
			$("#notifySub","#msgForm").val(notifySub);
			$("#cd","#msgForm").val(cd);
			$("#msgForm").attr("action", "/msg/deleteMsgItem.json");
			$("#msgForm").ajaxSubmit(deleteMsgItemAjax);	
		}
	} */
	
	// 메시지 등록Ajax
	/* var deleteMsgItemAjax = {
		success : function(resultData) {
			if(resultData == 1){
				alert("삭제되었습니다.");
				$("#msgForm").attr("action", "/msg/selectSubMsgList.doo");
				$("#currentPage").val(1);
				$("#msgForm").submit();
			} else {
				alert("삭제에 실패했습니다!");
			}
		},
		type : "post",
		dataType : "json",
		error : function() {
			alert("서버와의 통신에 실패 하였습니다.");
		}
	}; */
	
	// 메시지 등록Ajax
	var createMsgItemAjax = {
		success : function(resultData) {
			var result = '';
			if(resultData!=null){
				if(resultData.length>0){
					for(var i=0; i<resultData.length; i++){
						result+="<tr>";
						result+="<td>"+resultData[i].notify+"</td>";
						result+="<td>"+resultData[i].notifySub+"</td>";
						result+="<td>"+resultData[i].cd+"</td>";
						result+="<td>"+resultData[i].korNm+"</td>";
						result+="</tr>";
					}
				}else{
					result+="<tr><td colspan='4'>등록된 정보가 없습니다.</td></tr>";
				}
			}
			$("#msgResultListTbody").html(result);
		},
		type : "post",
		dataType : "json",
		error : function() {
			alert("서버와의 통신에 실패 하였습니다.");
		}
	};
	
	createMemberDiv = function(){
		$.blockUI({message:$("#msgContener"),css:{border: 'none',width:'514px;',left:'35%',top:'100px',backgroundColor:'none'}, overlayCSS : {opacity:0.5,border: 'none'}});
	};
	
	var readNotifyItem = function(notify, notifySub){
		
		if ($("#type").val() == 1) {
			readMedicalNotifyItem (notify, notifySub);
		} else if ($("#type").val() == 3) {
			readDrugNotifyItem (notify, notifySub);
		} else {
			readTrtNotifyItem (notify, notifySub);
		}
	}
	
	var readDrugNotifyItem  = function(drugNotify, drugNotifySub){
		pop('','drugNotifyItem','950', '730');

		$("#drugNotify","#notifyItemForm").val(drugNotify);
		$("#drugNotifySub","#notifyItemForm").val(drugNotifySub);
		$("#notifyItemForm").attr("action", "/drugNotify/selectDrugNotifyItemList.doo");
		$("#notifyItemForm").attr("target", "drugNotifyItem");
		$("#notifyItemForm").submit();	
	}
	
	var readMedicalNotifyItem  = function(medicalNotify, medicalNotifySub){
		pop('','medicalNotifyItem','950', '730');

		$("#medicalNotify","#notifyItemForm").val(medicalNotify);
		$("#medicalNotifySub","#notifyItemForm").val(medicalNotifySub);
		$("#notifyItemForm").attr("action", "/medicalNotify/selectMedicalNotifyItemList.doo");
		$("#notifyItemForm").attr("target", "medicalNotifyItem");
		$("#notifyItemForm").submit();	
	}
	
	var readTrtNotifyItem  = function(trtNotify, trtNotifySub){
		pop('','trtNotifyItem','950', '730');

		$("#trtNotify","#notifyItemForm").val(trtNotify);
		$("#trtNotifySub","#notifyItemForm").val(trtNotifySub);
		$("#notifyItemForm").attr("action", "/trtNotify/selectTrtNotifyItemList.doo");
		$("#notifyItemForm").attr("target", "trtNotifyItem");
		$("#notifyItemForm").submit();	
	}
	
	var readNotify = function(notify, notifySub){
		
		if ($("#type").val() == 1) {
			readMedicalNotify (notify, notifySub);
		} else if ($("#type").val() == 3) {
			readDrugNotify (notify, notifySub);
		} else if($("#type").val() == 8) {
			readTrtNotify(notify, notifySub);
		}
	}
	
	var readDrugNotify  = function(drugNotify, drugNotifySub){
		pop('','selectDrugNotifyPop','1100', '600');

		$("#drugNotify","#notifyItemForm").val(drugNotify);
		$("#drugNotifySub","#notifyItemForm").val(drugNotifySub);
		$("#notifyItemForm").attr("action", "/drugNotify/selectDrugNotifyPop.doo");
		$("#notifyItemForm").attr("target", "selectDrugNotifyPop");
		$("#notifyItemForm").submit();	
	}
	
	var readMedicalNotify  = function(medicalNotify, medicalNotifySub){
		pop('','selectMedicalNotifyPop','1100', '800');

		$("#medicalNotify","#notifyItemForm").val(medicalNotify);
		$("#medicalNotifySub","#notifyItemForm").val(medicalNotifySub);
		$("#notifyItemForm").attr("action", "/medicalNotify/selectMedicalNotifyPop.doo");
		$("#notifyItemForm").attr("target", "selectMedicalNotifyPop");
		$("#notifyItemForm").submit();	
	}
	
	var readTrtNotify  = function(trtNotify, trtNotifySub){
		pop('','selectTrtNotifyPop','1100', '800');

		$("#trtNotify","#notifyItemForm").val(trtNotify);
		$("#trtNotifySub","#notifyItemForm").val(trtNotifySub);
		$("#notifyItemForm").attr("action", "/trtNotify/selectTrtNotifyPop.doo");
		$("#notifyItemForm").attr("target", "selectTrtNotifyPop");
		$("#notifyItemForm").submit();	
	}
	
	var closeReplesh = function() {
		$.unblockUI();
		$("#msgForm").attr("action", "/msg/selectSubMsgList.doo");
		$("#msgForm").submit();
	}
	
	//삭제 Ajax
	var deleteAjax1 = {
		success : function(resultData) {
			if( resultData > 0 ) {
				alert(resultData + "건이 삭제되었습니다.");
				$("#msgForm").attr("action", "/msg/selectSubMsgList.doo");
				$("#msgForm").submit();
			} else {
				alert("삭제에 실패하였습니다.");
			}
		},
		type : "post",
		dataType : "json",
		error : function() {
			alert("서버와의 통신에 실패 하였습니다.");
		}
	};
	
</script>
<form id="msgListForm" name="msgListForm" action="/msg/selectMsgList.doo"method="post">
	<!--검색 조건  -->
	${subPagingParam}
</form>
<form id="msgForm" name="msgForm" action="/msg/selectMsgList.doo"method="post">
	<input type="hidden" id="notifySub"  name="notifySub" value="0"/>
	<input type="hidden" id="notify"  name="notify" value="0"/>
	<input type="hidden" id="cd"  name="cd" value="0"/>
	<input type="hidden" id="seq" name="seq" value="${msgVo.seq }"/>
	<input type="hidden" id="mSeq" name="mSeq" value="${msgVo.seq }"/>
	<input type="hidden" id="type" name="type" value="${msgVo.type }"/>
	${subPagingParam}
	<div>
		<table class="table0" >
			<col width="13%" />
			<col width="*" />
			<tbody>
				<tr>
					<th>메시지</th>
					<td>${msgVo.msg}</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div style='width:70%; float:left'> 
		<table class="table5">
			<caption><span class="divAr"><ct:code name="rowPerPage" type="select" groupCode="00009"  selectCode="${msgVo.rowPerPage}" eventNm="onchange" eventFn="javascript:commonRowPerChange(document.msgForm,'/msg/selectMsgList.doo');"/></span></caption>
			<colgroup>
				<col width="6%">
				<col width="7%">
				<col width="9%">
				<col width="9%">
				<col width="*%">
				<col width="7%">
				<col width="8%">
				<col width="11%">
				<%-- <col width="5%"> --%>
			</colgroup>
			<thead>
				<tr>
					<th><input type="checkBox" id="allDel" name="allDel"/></th>
					<th>고시번호</th>
					<th>서브번호</th>
					<th>코드</th>
					<th>명칭</th>
					<th>정렬순서</th>
					<th>수정자</th>
					<th>수정일</th>
					<!-- <th>삭제</th> -->
				</tr>
			</thead>
			<tbody id="msgListTbody">
				<c:if test="${msgList != null}">
					<c:forEach var="msg" items="${msgList}">
						<tr class="trFocus" >
							<td>
								<input type="checkBox" id="arrDel" name="arrDel" class="arrDel" class="arrDel" value="${msg.notify}_${msg.notifySub}_${msg.cd}" />
								<input type="checkBox" id="historyMsg" name="historyMsg" class="historyMsg" style="display:none;" value="${msg.cd}_${msg.korNm}" />
							</td>
							<td>
								<c:if test="${msgVo.type != 0}">
								<span class="button medium icon"><span class="check"></span><a onclick="javascript:readNotify('${msg.notify}','${msg.notifySub}');">${msg.notify}</a></span>
								</c:if>
								<c:if test="${msgVo.type == 0}">${msg.notify}</c:if>
							</td>
							<td><span class="button medium icon"><span class="check"></span><a onclick="javascript:readNotifyItem('${msg.notify}','${msg.notifySub}');">${msg.notifySub}</a></span></td>
							<td>${msg.cd}</td>
							<td>${msg.korNm }</td>
							<td>${msg.msgSort }</td>
							<td>${msg.updaterId}</td>
							<td>${msg.updateDthms}</td>
							<%-- <td><span class="button medium icon"><span class="check"></span><a href="javascript:deleteMsgItem('${msg.notify}','${msg.notifySub}','${msg.cd}');">삭제</a></span></td> --%>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${fn:length(msgList) == 0}">
					<tr>
						<td colspan="8" align="center">등록된 메시지가 없습니다.</td>
					</tr>
				</c:if>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="8">
						<span class="button medium icon"><span class="check"></span><a id="selectMsgList">목록</a></span>
						<span id="delete"><span class="button medium icon"><span class="check"></span><a id="deleteMsgItem">삭제</a></span></span>
					</td>
				</tr>
			</tfoot>
		</table>
	<!-- 페이지번호 -->
	<div class="paging">${msgVo.pagingHtml}</div>
	</div>
	<div style='width:30%; float:right;'> 
		<textarea rows="29" cols="34" id="msg" name="msg"></textarea>
		<span id="btnMode1"><span class="button medium icon"><span class="check"></span>
			<a id ="createMsgItem">등록</a>
		</span></span>
	</div>
</form>
<form id="notifyItemForm" name="notifyItemForm" action="/trtNotify/selectTrtNotifyItemList.doo"method="post">
	<input type="hidden" id="drugNotify"  name="drugNotify" value="0"/>
	<input type="hidden" id="drugNotifySub"  name="drugNotifySub" value="0"/>
	<input type="hidden" id="trtNotify"  name="trtNotify" value="0"/>
	<input type="hidden" id="trtNotifySub"  name="trtNotifySub" value="0"/>
	<input type="hidden" id="medicalNotify"  name="medicalNotify" value="0"/>
	<input type="hidden" id="medicalNotifySub"  name="medicalNotifySub" value="0"/>
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
				<col width="11%">
				<col width="*%">
			</colgroup>
			<thead>
				<tr>
					<th>고시번호</th>
					<th>고시서브번호</th>
					<th>코드</th>
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